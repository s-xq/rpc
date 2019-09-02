package com.sxq.rpc.common.dynamic.jdk;

import java.io.ObjectInputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;
import com.sxq.rpc.common.IOUtil;
import com.sxq.rpc.common.dynamic.InvokerInvocationHandler;
import com.sxq.rpc.protocol.invoke.Invocation;
import com.sxq.rpc.transport.client.RpcClient;

/**
 * Created by s-xq on 2019-08-31.
 * TODO:泛化
 */
public class JdkInvokerInvocationHandler<T> implements InvokerInvocationHandler, InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(JdkInvokerInvocationHandler.class);

    private String host;

    private int port;

    private Class<?> interfaceCls;

    /**
     * TODO 泛化，添加Exchanger层, private Exchanger exchanger
     *
     * @param interfaceCls
     * @param host
     * @param port
     */
    public JdkInvokerInvocationHandler(Class<?> interfaceCls, String host, int port) {
        this.interfaceCls = interfaceCls;
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Invocation invocation = new Invocation();
        invocation.setInterfaceCls(interfaceCls.getCanonicalName());
        invocation.setMethodName(method.getName());
        String[] parameterTypes = new String[method.getParameterTypes().length];
        for (int index = 0; index < method.getParameterTypes().length; index++) {
            parameterTypes[index] = method.getParameterTypes()[index].getCanonicalName();
        }
        invocation.setParameterTypes(parameterTypes);
        invocation.setArgs(args);
        RpcClient rpcClient = new RpcClient(host, port);
        rpcClient.send(invocation);
        /**
         * TODO InvokeFuture or Callback
         */
        ObjectInputStream inputStream = null;
        Object result = null;
        try {
            inputStream = new ObjectInputStream(rpcClient.channel().getInputStream());
            result = inputStream.readObject();
            if (result instanceof Throwable) {
                throw (Throwable) result;
            }
            logger.info("reply:{}", result);
        } catch (Throwable throwable) {
            logger.error(Throwables.getStackTraceAsString(throwable));
        } finally {
            IOUtil.safeClose(inputStream);
            rpcClient.close();
        }
        return result;
    }
}
