package com.sxq.rpc.transport.client;

import java.io.Closeable;
import java.io.IOException;

import com.sxq.rpc.transport.channel.Channel;

public interface Client extends Closeable {

    Channel reconnect(String host, int port) throws IOException;

    void send(Object data);

    Channel channel();

}
