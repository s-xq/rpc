package com.sxq.rpc.common.dynamic;

/**
 * Created by s-xq on 2019-08-31.
 */

public interface DynamicProxyFactory {

    <T> T getProxy(Class<T> interfaceCls, InvokerInvocationHandler invokerInvocationHandler);

}
