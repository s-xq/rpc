package com.sxq.rpc.remoting;

/**
 * Created by s-xq on 2019-09-02.
 */

public abstract class BaseResponse extends Packet {

    @Override
    public short version() {
        return Version.VERSION1_0.value();
    }

    @Override
    public short packetType() {
        return PacketType.RESPONSE.value();
    }

}
