package com.sxq.rpc.transport.client;

import java.io.IOException;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sxq.rpc.common.TypeUtil;
import com.sxq.rpc.serliaze.RemotingCodec;
import com.sxq.rpc.transport.channel.Channel;
import com.sxq.rpc.transport.channel.impl.SocketChannel;

/**
 * Created by s-xq on 2019-08-31.
 */

public class RpcClient implements Client {

    private static final Logger logger = LoggerFactory.getLogger(RpcClient.class);

    private String host;
    private int port;
    private Channel channel;
    private RemotingCodec remotingCodec;

    public RpcClient(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        init();
    }

    private void init() throws IOException {
        reconnect(this.host, this.port);
        remotingCodec = new RemotingCodec();
        if (channel == null) {
            /**
             * TODO handle connect fail
             */
        }
    }

    /**
     * send data
     *
     * @param data
     */
    @Override
    public void send(Object data) {
        byte[] encodedData = remotingCodec.encode(data);
        this.channel.writeAndFlush((TypeUtil.toByteArr(encodedData.length)));
        logger.info("=>[{}]{}", encodedData.length, data);
        this.channel.writeAndFlush(encodedData);
    }

    @Override
    public Channel reconnect(String host, int port) throws IOException {
        Socket socket = new Socket(host, port);
        Channel result = new SocketChannel(socket);
        channel = result;
        return result;
    }

    @Override
    public void close() {
        if (channel != null) {
            channel.close();
        }
    }

    /**
     * TODO delete this API
     *
     * @return
     */
    public Channel channel() {
        return channel;
    }
}
