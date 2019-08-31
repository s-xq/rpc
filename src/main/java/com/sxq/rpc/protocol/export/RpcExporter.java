package com.sxq.rpc.protocol.export;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.base.Throwables;

/**
 * Created by s-xq on 2019-08-31.
 */

@Service
public class RpcExporter implements Exporter {
    private static Logger logger = LoggerFactory.getLogger(RpcExporter.class);

    private static ExecutorService executorService
            = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    @Override
    public void export(Object service, int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            final Socket socket = serverSocket.accept();
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        try {
                            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                            try {
                                String methodName = inputStream.readUTF();
                                Class<?>[] parameterTypes = (Class<?>[]) inputStream.readObject();
                                Object[] args = (Object[]) inputStream.readObject();
                                ObjectOutput output = new ObjectOutputStream(socket.getOutputStream());
                                try {
                                    Method method = service.getClass().getMethod(methodName, parameterTypes);
                                    Object result = method.invoke(service, args);
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
