package com.sxq.rpc.remoting;

/**
 * Created by s-xq on 2019-09-02.
 */

public abstract class Packet {

    public abstract short version();

    public abstract short packetType();

    public abstract int getId();

    public abstract long bodyLength();

    public abstract byte[] body();

}
