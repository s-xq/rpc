package com.sxq.rpc.protocol.invoke;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by s-xq on 2019-08-31.
 */

@Service
public class RpcInvoker implements Invoker {

    private static Logger logger = LoggerFactory.getLogger(RpcInvoker.class);

    @Override
    public <REQ, RES> RES invoke(Class<REQ> interfaceClass, String host, int port) {
        return (RES) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] {interfaceClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Socket socket = new Socket(host, port);
                        try {
                            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                            try {
                                outputStream.writeUTF(method.getName());
                                outputStream.writeObject(method.getParameterTypes());
                                outputStream.writeObject(args);
                                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                                try {
                                    Object result = inputStream.readObject();
                                    if (result instanceof Throwable) {
                                        throw (Throwable) result;
                                    }
                                    logger.info("reply:{}", result);
                                    return result;
                                } finally {
                                    inputStream.close();
                                }
                            } finally {
                                outputStream.close();
                            }
                        } finally {
                            socket.close();
                        }
                    }
                });
    }
}
