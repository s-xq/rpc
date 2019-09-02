package com.sxq.rpc.remoting;

/**
 * Created by s-xq on 2019-09-02.
 */

public enum Version {

    /**
     * 1.0
     */
    VERSION1_0((short) 1);

    private short value;

    Version(short value) {
        this.value = value;
    }

    public static Version valueOf(short value) {
        switch (value) {
            case 1:
                return VERSION1_0;
            default:
                break;
        }
        throw new IllegalArgumentException("Unknown version code value:" + value);
    }

    public short value() {
        return this.value;
    }
}
