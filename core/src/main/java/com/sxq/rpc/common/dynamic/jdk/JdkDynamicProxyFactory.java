package com.sxq.rpc.common.dynamic.jdk;

import java.lang.reflect.Proxy;

import com.sxq.rpc.common.dynamic.DynamicProxyFactory;
import com.sxq.rpc.common.dynamic.InvokerInvocationHandler;

/**
 * Created by s-xq on 2019-08-31.
 */

public class JdkDynamicProxyFactory implements DynamicProxyFactory {

    @Override
    public <T> T getProxy(Class<T> interfaceCls, InvokerInvocationHandler invokerInvocationHandler) {
        return (T) Proxy.newProxyInstance(
                interfaceCls.getClassLoader(),
                new Class[] {interfaceCls},
                (JdkInvokerInvocationHandler) invokerInvocationHandler);
    }

}
