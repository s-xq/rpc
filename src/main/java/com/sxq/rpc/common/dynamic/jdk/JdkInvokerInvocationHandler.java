package com.sxq.rpc.common.dynamic.jdk;

import java.io.ObjectInputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;
import com.sxq.rpc.common.IOUtil;
import com.sxq.rpc.common.dynamic.InvokerInvocationHandler;
import com.sxq.rpc.transport.client.RpcClient;

/**
 * Created by s-xq on 2019-08-31.
 * TODO:泛化
 */
public class JdkInvokerInvocationHandler<T> implements InvokerInvocationHandler, InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(JdkInvokerInvocationHandler.class);

    private String host;

    private int port;

    /**
     * TODO 泛化，添加Exchanger层, private Exchanger exchanger
     *
     * @param host
     * @param port
     */
    public JdkInvokerInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcClient rpcClient = new RpcClient(host, port);
        rpcClient.send(method.getName());
        rpcClient.send(method.getParameterTypes());
        rpcClient.send(args);
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
