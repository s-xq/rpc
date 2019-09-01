package com.sxq.rpc.common;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by s-xq on 2019-09-01.
 */

public class ClassUtil {

    private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * get all of impl of this interfaceCls
     *
     * @param interfaceCls
     * @param <T>
     *
     * @return
     *
     * @see
     * <a href="https://www.cnblogs.com/heaveneleven/p/9125228.html>https://www.cnblogs.com/heaveneleven/p/9125228.html</a>
     */
    public static <T> List<Class<T>> getAllClassByInterface(Class<T> interfaceCls) {
        logger.info("interface:{}", interfaceCls);
        ServiceLoader<T> loader = ServiceLoader.load(interfaceCls);
        List<Class<T>> result = new ArrayList<>();
        for (T item : loader) {
            result.add((Class<T>) item.getClass());
        }
        logger.info("interface impl:{}", result);
        return result;
    }

}
