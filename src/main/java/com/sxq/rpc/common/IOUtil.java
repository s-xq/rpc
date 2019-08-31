package com.sxq.rpc.common;

import java.io.Closeable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;

/**
 * Created by s-xq on 2019-08-31.
 */

public class IOUtil {

    private static final Logger logger = LoggerFactory.getLogger(IOUtil.class);

    /**
     * close safely
     *
     * @param closeable
     */
    public static void safeClose(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable throwable) {
                logger.info(Throwables.getStackTraceAsString(throwable));
            }
        }
    }
}
