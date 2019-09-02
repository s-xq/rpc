package com.sxq.rpc.remoting;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by s-xq on 2019-09-02.
 */

public class IDGenerator {

    private static AtomicInteger id = new AtomicInteger(0);

    public static int nextId() {
        return id.incrementAndGet();
    }
}
