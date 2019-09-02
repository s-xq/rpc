package com.sxq.rpc.remoting;

/**
 * Created by s-xq on 2019-09-02.
 */

public class RpcResponse extends BaseResponse {

    private int id;

    private byte[] body;

    public RpcResponse(int id, byte[] body) {
        this.id = id;
        this.body = body;
    }

    @Override
    public int getId() {
        return id;
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
