package com.sxq.rpc.transport.event;

/**
 * Created by s-xq on 2019-09-01.
 */

public interface Event<T> {

    T data();

    Class<T> type();

}
