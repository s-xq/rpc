package com.sxq.rpc.example.client;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sxq.rpc.RpcClient;
import com.sxq.rpc.example.service.HelloService;
import com.sxq.rpc.protocol.invoke.RpcInvoker;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RpcClientTests {

    private static final Logger logger = LoggerFactory.getLogger(RpcClientTests.class);

    RpcInvoker rpcInvoker;

    @Before
    public void setUp() throws Exception {
        rpcInvoker = new RpcInvoker(RpcClient.class);
    }

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
