package com.sxq.rpc.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sxq.rpc.protocol.export.RpcExporter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RpcServerTests {

    private static final Logger logger = LoggerFactory.getLogger(RpcServerTests.class);

    @Autowired
    RpcExporter rpcExporter;

    @Test
    public void contextLoads() {
    }

    @Test
    public void exportTest() throws Exception {
        rpcExporter.export(8080);
    }
}