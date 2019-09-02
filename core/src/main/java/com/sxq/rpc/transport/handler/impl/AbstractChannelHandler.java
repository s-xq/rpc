package com.sxq.rpc.transport.handler.impl;

import com.sxq.rpc.transport.channel.impl.ChannelContext;
import com.sxq.rpc.transport.handler.ChannelHandler;

/**
 * Created by s-xq on 2019-09-01.
 */

public abstract class AbstractChannelHandler implements ChannelHandler {

    @Override
    public void onChannelActive(ChannelContext channelContext) {
        /**
         * TODO
         */
    }

    @Override
    public void onChannelInActive(ChannelContext channelContext) {
        /**
         * TODO
         */
    }

    @Override
    public void onChannelRead(ChannelContext channelContext, Object object) {
        /**
         * TODO
         */
    }

    @Override
    public void onChannelClose(ChannelContext channelContext) {
        /**
         * TODO
         */
    }

}
