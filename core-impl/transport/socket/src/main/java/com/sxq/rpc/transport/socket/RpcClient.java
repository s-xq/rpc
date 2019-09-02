package com.sxq.rpc.transport.socket;

import java.io.IOException;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sxq.rpc.common.TypeUtil;
import com.sxq.rpc.serliaze.RemotingCodec;
import com.sxq.rpc.transport.channel.Channel;
import com.sxq.rpc.transport.client.Client;

/**
 * Created by s-xq on 2019-08-31.
 */

public class RpcClient implements Client {

    private static final Logger logger = LoggerFactory.getLogger(RpcClient.class);

    private String host;
    private int port;
    private Channel channel;
    private RemotingCodec remotingCodec = new RemotingCodec();

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
    @Override
    public Channel channel() {
        return channel;
    }
}
