package net.anumbrella.seaweedfs.exception;

import java.io.IOException;

public class SeaweedfsException extends IOException {
    public SeaweedfsException(String message) {
        super(message);
    }

    public SeaweedfsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeaweedfsException(Throwable cause) {
        super(cause);
    }
}
