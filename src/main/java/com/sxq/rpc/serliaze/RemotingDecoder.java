package com.sxq.rpc.serliaze;

import com.sxq.rpc.common.GsonWrap;

/**
 * Created by s-xq on 2019-09-01.
 */

public class RemotingDecoder {

    /**
     * decode
     *
     * @param content
     * @param cls
     * @param <T>
     *
     * @return
     */
    public <T> T decode(byte[] content, Class<T> cls) {
        if (content == null || content.length <= 0 || cls == null) {
            return null;
        }
        return GsonWrap.toModel(new String(content), cls);
    }
}
