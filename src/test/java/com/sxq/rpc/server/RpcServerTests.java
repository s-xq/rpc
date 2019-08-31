package com.sxq.rpc.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sxq.rpc.protocol.export.RpcExporter;
import com.sxq.rpc.service.HelloService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RpcServerTests {

    private static Logger logger = LoggerFactory.getLogger(RpcServerTests.class);

    @Autowired
    RpcExporter rpcExporter;

    @Autowired
    HelloService helloService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void exportTest() throws Exception {
        rpcExporter.export(helloService, 8080);
    }
}
