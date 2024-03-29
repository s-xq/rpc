package com.sxq.rpc.protocol.export;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;
import com.sxq.rpc.common.ClassUtil;
import com.sxq.rpc.common.ReflectionUtil;
import com.sxq.rpc.common.TypeUtil;
import com.sxq.rpc.protocol.invoke.Invocation;
import com.sxq.rpc.serliaze.RemotingCodec;

/**
 * Created by s-xq on 2019-08-31.
 */

public class RpcExporter implements Exporter {
    private static final Logger logger = LoggerFactory.getLogger(RpcExporter.class);

    private static ExecutorService executorService
            = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    private RemotingCodec remotingCodec = new RemotingCodec();

    @Override
    public void export(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            final Socket socket = serverSocket.accept();
            logger.info("socket accept...");
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        try {
                            logger.info("start read...");
                            InputStream inputStream = socket.getInputStream();
                            try {
                                byte[] dataLengthByte = new byte[4];
                                inputStream.read(dataLengthByte, 0, 4);
                                int dataLength = TypeUtil.toInt(dataLengthByte);
                                logger.info("dataLength:[{}]{}", dataLength, dataLengthByte);
                                byte[] data = new byte[dataLength];
                                inputStream.read(data, 0, dataLength);
                                logger.info("data:{}", data);
                                Invocation invocation = remotingCodec.decode(data, Invocation.class);
                                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                                try {
                                    Class<?> interfaceCls = ReflectionUtil.getClass(invocation.getInterfaceCls());
                                    List interfaceImplClss = ClassUtil.getAllClassByInterface(interfaceCls);
                                    /**
                                     * mapping {@link com.sxq.rpc.example.service.HelloService} to
                                     * {@link HelloServiceImpl}
                                     */
                                    Class<?> interfaceImplCls = (Class<?>) interfaceImplClss.get(0); // must cast
                                    logger.info("interface impl:{}", interfaceImplClss);
                                    Object instance = interfaceImplCls.getConstructor().newInstance();
                                    Class<?>[] parameterTypes = new Class<?>[invocation.getParameterTypes().length];
                                    for (int index = 0; index < invocation.getParameterTypes().length; index++) {
                                        parameterTypes[index]
                                                = ReflectionUtil.getClass(invocation.getParameterTypes()[index]);
                                    }
                                    Method method = ReflectionUtil.method(
                                            interfaceCls, invocation.getMethodName(), parameterTypes);
                                    Object result = ReflectionUtil.invoke(method, instance, invocation.getArgs());
                                    output.writeObject(result);
                                } catch (Throwable throwable) {
                                    output.writeObject(throwable);
                                } finally {
                                    output.close();
                                }
                            } catch (Throwable throwable) {
                                inputStream.close();
                            }
                        } catch (Throwable throwable) {
                            logger.error(Throwables.getStackTraceAsString(throwable));
                        } finally {
                            socket.close();
                        }
                    } catch (Throwable throwable) {
                        logger.error(Throwables.getStackTraceAsString(throwable));
                    }

                }
            });
        }
    }
}
