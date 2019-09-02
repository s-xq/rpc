package com.sxq.rpc.protocol.invoke;

public interface Invoker {

    <T> T invoke(Class<T> interfaceClass, String host, int port);
}
