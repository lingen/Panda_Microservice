package org.openpanda.microservice.ddd.rule;

/**
 * Created by lingen on 2016/12/24.
 */
public class PandaRuntimeException extends RuntimeException {

    private String errorCode;

    private String description;

    public PandaRuntimeException(String errorCode,String description){
        super(errorCode);
        this.errorCode = errorCode;
        this.description = description;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }
}
