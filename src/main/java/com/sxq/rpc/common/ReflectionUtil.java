package com.sxq.rpc.common;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by s-xq on 2019-09-01.
 */

public class ReflectionUtil {

    private static Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    public static Class getClass(final String name) {
        Class result = null;
        try {
            result = Class.forName(name);
        } catch (final ClassNotFoundException e) {
            logger.error("unable to find class {}", name);
        }
        return result;
    }

    public static Object invoke(final Method method, final Object object, final Object... parameters) {
        Object result = null;
        try {
            result = method.invoke(object, parameters);
        } catch (final Exception e) {
            logger.error("error while invoking method {} on object {} with parameters {} {}",
                    method, object, Arrays.toString(parameters), e.getMessage());
        }
        return result;
    }

    public static Method method(final Class clazz, final String methodName, final Class... parameterTypes) {
        Method result = null;
        try {
            result = clazz.getDeclaredMethod(methodName, parameterTypes);
            result.setAccessible(true);
        } catch (final Exception e) {
            logger.error("error while getting method {} on class {} with parameters types {} {}",
                    methodName, clazz, Arrays.toString(parameterTypes), e.getMessage());
        }
        return result;
    }

    public static Method method(final String className, final String method, final Class... parameterTypes) {
        return method(getClass(className), method, parameterTypes);
    }
}
