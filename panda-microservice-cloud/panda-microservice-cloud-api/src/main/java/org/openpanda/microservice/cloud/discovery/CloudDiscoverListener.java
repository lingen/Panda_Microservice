package org.openpanda.microservice.cloud.discovery;


import org.openpanda.microservice.cloud.model.CloudMember;

/**
 * Created by lingen on 2017/1/7.
 */
@FunctionalInterface
public interface CloudDiscoverListener {

    void discoverValue(CloudMember cloundMember);
}
