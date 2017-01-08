package org.openpanda.microservice.cloud.discovery;

/**
 * Created by lingen on 2017/1/7.
 */
public interface CloudDiscovery {

    /**
     * 查找一个服务
     * @param serverName
     * @return
     */
    void discovery(String myName, String serverName, CloudDiscoverListener cloundDiscoverListener);

}
