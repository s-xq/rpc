package com.sxq.rpc.remoting;

/**
 * Created by s-xq on 2019-09-02.
 */

public enum PacketType {

    /**
     * Request
     */
    REQUEST((short) (1)),
    /**
     * Response
     */
    RESPONSE((short) (2));

    private short value;

    PacketType(short value) {
        this.value = value;
    }

    public static PacketType valueOf(short value) {
        switch (value) {
            case 1:
                return REQUEST;
            case 2:
                return RESPONSE;
            default:
                break;
        }
        throw new IllegalArgumentException("Unknown remoting code value:" + value);
    }

    public short value() {
        return this.value;
    }
}
