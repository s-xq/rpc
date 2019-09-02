package com.sxq.rpc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sxq.rpc.common.TypeUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RpcCoreApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void typeTest() {
        byte[] bytes = TypeUtil.toByteArr(129);
        int num = TypeUtil.toInt(bytes);
        Assert.assertEquals(129, num);

    }

}
