package org.openpanda.microservice.ddd.rule;


import javax.ws.rs.core.Response;

/**
 * Created by lingen on 2016/12/24.
 */
@FunctionalInterface
public interface PandaResource {
    Response execute();
}
