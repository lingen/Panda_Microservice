package org.openpanda.microservice.ddd.rule;

import java.io.Serializable;

/**
 * Created by lingen on 2016/12/24.
 */
public class PandaRestResponse implements Serializable {

    private String responseCode;

    private String description;

    public static PandaRestResponse createInstance(String errorCode, String description){
        PandaRestResponse pandaRestResponse = new PandaRestResponse();
        pandaRestResponse.responseCode = errorCode;
        pandaRestResponse.description = description;
        return pandaRestResponse;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getDescription() {
        return description;
    }
}
