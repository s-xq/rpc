package com.sxq.rpc.common;

import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * @author s-xq
 * @date 2018/10/26.
 */

public class GsonWrap {

    private static Gson gson = new Gson();
    private static Logger logger = LoggerFactory.getLogger(GsonWrap.class);

    /**
     * Parse json
     *
     * @param json
     * @param cls
     * @param <T>
     */
    public static <T> T toModel(String json, Class<T> cls) {
        try {
            return gson.fromJson(json, cls);
        } catch (Throwable e) {
            logger.error("gson toModel error", e);
        }
        return null;
    }

    public static final <T> T toModel(String json, Type type) {
        try {
            return gson.fromJson(json, type);
        } catch (Throwable e) {
            logger.error("gson toModel error", e);
        }

        return null;
    }

    /**
     * to json
     *
     * @param model
     *
     * @return
     */
    public static final String toJson(Object model) {
        try {
            return gson.toJson(model);
        } catch (Throwable e) {
            logger.error("gson toJson error", e);
        }
        return null;
    }
}
