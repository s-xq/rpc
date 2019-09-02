package com.sxq.rpc.remoting;

/**
 * Created by s-xq on 2019-09-02.
 */

public class RpcRequest extends BaseRequest {

    private byte[] body;

    public RpcRequest(byte[] body) {
        this.body = body;
    }

    @Override
    public int getId() {
        return IDGenerator.nextId();
    }

    @Override
    public long bodyLength() {
        return body.length;
    }

    @Override
    public byte[] body() {
        return body;
    }
}
