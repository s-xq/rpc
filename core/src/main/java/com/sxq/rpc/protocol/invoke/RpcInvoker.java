package com.sxq.rpc.protocol.invoke;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sxq.rpc.common.dynamic.jdk.JdkDynamicProxyFactory;
import com.sxq.rpc.common.dynamic.jdk.JdkInvokerInvocationHandler;

/**
 * Created by s-xq on 2019-08-31.
 */

@Service
public class RpcInvoker implements Invoker {

    private static final Logger logger = LoggerFactory.getLogger(RpcInvoker.class);

    /**
     * create service stub
     *
     * @param interfaceClass
     * @param host
     * @param port
     * @param <T>
     *
     * @return
     */
    @Override
    public <T> T invoke(Class<T> interfaceClass, String host, int port) {
        JdkInvokerInvocationHandler invokerInvocationHandler
                = new JdkInvokerInvocationHandler(interfaceClass, host, port);
        JdkDynamicProxyFactory jdkDynamicProxyFactory = new JdkDynamicProxyFactory();
        return jdkDynamicProxyFactory.getProxy(interfaceClass, invokerInvocationHandler);
    }
}
