package org.openpanda.microservice.ddd.rule;

import java.io.Serializable;

/**
 * Created by lingen on 2016/12/24.
 */
public class PandaRestResponse implements Serializable {

    private String response;

    private String msg;

    public static PandaRestResponse createInstance(String response, String msg){
        PandaRestResponse pandaRestResponse = new PandaRestResponse();
        pandaRestResponse.response = response;
        pandaRestResponse.msg = msg;
        return pandaRestResponse;
    }

    public String getResponse() {
        return response;
    }

    public String getMsg() {
        return msg;
    }
}
