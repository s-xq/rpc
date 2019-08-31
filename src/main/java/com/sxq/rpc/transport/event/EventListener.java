package com.sxq.rpc.transport.event;

/**
 * Created by s-xq on 2019-09-01.
 */

public interface EventListener<T> {

    void onEvent(Event<T> event);

}
