package com.sxq.rpc.transport.client;

import java.util.concurrent.TimeUnit;

import com.sxq.rpc.transport.channel.Channel;
import com.sxq.rpc.transport.channel.impl.ChannelContext;
import com.sxq.rpc.transport.event.EventLoop;
import com.sxq.rpc.transport.handler.ChannelHandler;

/**
 * Created by s-xq on 2019-09-01.
 */

public class ReadEventLoop implements EventLoop {

    private ChannelHandler channelHandler;

    private ChannelContext channelContext;

    /**
     * TODO schedule this {@link ReadEventLoop} in
     * {@link java.util.concurrent.ScheduledExecutorService#schedule(Runnable, long, TimeUnit)}
     *
     * @param channelHandler
     * @param channelContext
     */
    public ReadEventLoop(ChannelHandler channelHandler, ChannelContext channelContext) {
        this.channelHandler = channelHandler;
        this.channelContext = channelContext;
    }

    @Override
    public void run() {
        if (checkReadble()) {
            Channel channel = channelContext.channel();
            Object readResult = null;
            channelHandler.onChannelRead(channelContext, readResult);
        }
    }

    private boolean checkReadble() {
        return false;
    }
}
