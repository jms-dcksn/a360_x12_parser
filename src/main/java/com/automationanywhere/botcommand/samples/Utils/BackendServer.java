package com.automationanywhere.botcommand.samples.Utils;

import com.imsweb.x12.reader.X12Reader;

public class BackendServer {

    public X12Reader getReader() {
        return reader;
    }

    X12Reader reader;

    public BackendServer(X12Reader reader) {
        this.reader = reader;
    }

}
