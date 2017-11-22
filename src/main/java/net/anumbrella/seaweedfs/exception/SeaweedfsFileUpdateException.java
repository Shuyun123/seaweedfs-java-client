package net.anumbrella.seaweedfs.exception;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class SeaweedfsFileUpdateException extends IOException{
    private Map<String, IOException> exceptionMap = new LinkedHashMap<String, IOException>();

    public SeaweedfsFileUpdateException(String fileId, IOException exception) {
        super();
        putExceptionInMap(fileId, exception);
    }

    @Override
    public String getMessage() {
        return String.valueOf(exceptionMap);
    }

    @Override
    public String getLocalizedMessage() {
        return String.valueOf(exceptionMap);
    }

    public int putExceptionInMap(String fileId, IOException exception) {
        exceptionMap.put(fileId, exception);
        return exceptionMap.size();
    }
}
