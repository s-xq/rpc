package com.sxq.rpc.transport.channel;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by s-xq on 2019-08-31.
 */

public interface Channel {

    /**
     * TODO delete this API
     *
     * @return
     */
    InputStream getInputStream() throws IOException;

    void writeAndFlush(Object data);

    void writeAndFlush(String data);

    /**
     * todo receive
     */

    void close();
}
