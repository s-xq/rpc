package com.sxq.rpc.transport.handler;

import com.sxq.rpc.transport.channel.impl.ChannelContext;

/**
 * Created by s-xq on 2019-09-01.
 */

public interface ChannelHandler {

    void onChannelActive(ChannelContext channelContext);

    void onChannelInActive(ChannelContext channelContext);

    void onChannelRead(ChannelContext channelContext, Object object);

    void onChannelClose(ChannelContext channelContext);

}
