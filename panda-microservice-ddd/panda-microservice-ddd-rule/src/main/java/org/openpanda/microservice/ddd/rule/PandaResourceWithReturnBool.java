package org.openpanda.microservice.ddd.rule;


/**
 * Created by lingen on 2016/12/24.
 */
@FunctionalInterface
public interface PandaResourceWithReturnBool {

    boolean execute();

}
