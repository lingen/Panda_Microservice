package org.openpanda.microservice.cloud.resiger;

import org.openpanda.microservice.cloud.model.CloudMember;

/**
 * Created by lingen on 2017/1/7.
 */
public interface CloudRegister {


    void register(String cloudUrl, CloudMember cloundMember);

}
