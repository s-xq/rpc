package com.sxq.rpc.serliaze;

import com.sxq.rpc.common.GsonWrap;

/**
 * Created by s-xq on 2019-09-01.
 */

public class RemotingEncoder {

    /**
     * encode
     *
     * @param object
     * @param <T>
     *
     * @return
     */
    public <T> byte[] encode(T object) {
        return GsonWrap.toJson(object).getBytes();
    }

}
