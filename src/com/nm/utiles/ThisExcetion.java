package com.nm.utiles;

public class ThisExcetion extends  RuntimeException {

    public ThisExcetion() {
    }

    public ThisExcetion(String message) {
        super(message);
    }

    public ThisExcetion(Throwable cause) {
        super(cause);
    }
}
