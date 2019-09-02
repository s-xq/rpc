package com.sxq.rpc.common;

/**
 * Created by s-xq on 2019-09-01.
 */

public class TypeUtil {

    public static byte[] toByteArr(int num) {
        byte[] result = new byte[4];
        result[0] = (byte) (num & 0xFF);
        result[1] = (byte) (num >> 8 & 0xFF);
        result[2] = (byte) (num >> 16 & 0xFF);
        result[3] = (byte) (num >> 24 & 0xFF);
        return result;
    }

    public static int toInt(byte[] bytes) {
        if (bytes == null || bytes.length != 4) {
            return 0;
        }
        int value = bytes[0] & 0xFF;
        value |= bytes[1] << 8;
        value |= bytes[2] << 16;
        value |= bytes[3] << 24;
        return value;

    }

}
