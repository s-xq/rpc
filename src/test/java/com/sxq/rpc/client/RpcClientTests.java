package com.sxq.rpc.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sxq.rpc.protocol.invoke.RpcInvoker;
import com.sxq.rpc.service.HelloService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RpcClientTests {

    private static final Logger logger = LoggerFactory.getLogger(RpcClientTests.class);

    @Autowired
    RpcInvoker rpcInvoker;

    @Test
    public void contextLoads() {
    }

    @Test
    public void invokeTest() {
        for (int i = 0; i < 10; i++) {
            HelloService helloService = rpcInvoker.invoke(HelloService.class, "127.0.0.1", 8080);
            String result = helloService.hello("client" + i);
            logger.info("result:{}", result);
        }
    }
}
