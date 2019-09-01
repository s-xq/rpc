package com.sxq.rpc.protocol.export;

import java.io.IOException;

public interface Exporter {

    void export(int port) throws IOException;

}
