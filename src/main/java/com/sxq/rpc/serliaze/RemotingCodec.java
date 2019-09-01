package com.sxq.rpc.serliaze;

/**
 * Created by s-xq on 2019-09-01.
 */

public class RemotingCodec {

    /**
     * todo extend codec algorithm
     */

    private RemotingDecoder remotingDecoder;

    private RemotingEncoder remotingEncoder;

    public RemotingCodec() {
        this.remotingDecoder = new RemotingDecoder();
        this.remotingEncoder = new RemotingEncoder();
    }

    public <T> T decode(byte[] content, Class<T> cls) {
        return remotingDecoder.decode(content, cls);
    }

    public <T> byte[] encode(T object) {
        return remotingEncoder.encode(object);
    }
}
