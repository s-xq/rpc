package com.sxq.rpc.protocol.export;

import java.io.IOException;

public interface Exporter<SERVICE> {

    void export(SERVICE service, int port) throws IOException;

}
