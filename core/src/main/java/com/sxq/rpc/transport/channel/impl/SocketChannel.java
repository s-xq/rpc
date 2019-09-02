package com.sxq.rpc.transport.channel.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;
import com.sxq.rpc.common.IOUtil;
import com.sxq.rpc.transport.channel.Channel;

/**
 * Created by s-xq on 2019-08-31.
 */

public class SocketChannel implements Channel {

    private static final Logger logger = LoggerFactory.getLogger(SocketChannel.class);

    private Socket socket;

    private OutputStream outputStream;

    public SocketChannel(Socket socket) throws IOException {
        this.socket = socket;
        this.outputStream = socket.getOutputStream();
    }

    @Override
    public void writeAndFlush(byte[] data) {
        try {
            this.outputStream.write(data);
        } catch (Throwable throwable) {
            logger.error(Throwables.getStackTraceAsString(throwable));
        }
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return this.socket.getInputStream();
    }

    @Override
    public void close() {
        IOUtil.safeClose(outputStream);
        IOUtil.safeClose(socket);
    }
}
