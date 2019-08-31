package com.sxq.rpc.protocol.invoke;

public interface Invoker {

    <REQ, RES> RES invoke(Class<REQ> interfaceClass, String host, int port);
}
