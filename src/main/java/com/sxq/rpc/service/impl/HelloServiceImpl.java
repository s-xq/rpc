package com.sxq.rpc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sxq.rpc.service.HelloService;

/**
 * Created by s-xq on 2019-08-31.
 */

@Service
public class HelloServiceImpl implements HelloService {

    private Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String hello(String userName) {
        logger.info("from {}", userName);
        return String.format("Hello %s", userName);
    }
}
