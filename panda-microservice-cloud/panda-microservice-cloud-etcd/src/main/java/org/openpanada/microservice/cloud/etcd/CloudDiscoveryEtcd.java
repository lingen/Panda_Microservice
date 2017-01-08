package org.openpanada.microservice.cloud.etcd;

import org.boon.etcd.ClientBuilder;
import org.boon.etcd.Etcd;
import org.boon.etcd.Node;
import org.boon.etcd.Response;
import org.openpanada.microservice.cloud.etcd.choice.CloudChoiceWeightForEtcd;
import org.openpanda.pandalib.cloud.choice.CloudChoice;
import org.openpanda.pandalib.cloud.discovery.CloudDiscoverListener;
import org.openpanda.pandalib.cloud.discovery.CloudDiscovery;
import org.openpanda.pandalib.cloud.model.CloudMember;

import java.net.URI;
import java.util.List;

/**
 * Created by lingen on 2017/1/7.
 */
public class CloudDiscoveryEtcd implements CloudDiscovery {

    private static int TTL = 10;

    private Etcd etcdClient;

    public CloudDiscoveryEtcd(String etcdServeUrl){
        etcdClient = ClientBuilder.builder().hosts(
                URI.create(etcdServeUrl)
        ).createClient();
    }

    @Override
    public void discovery(String myName,String serverName, CloudDiscoverListener cloundDiscoverListener) {
        String serverKey = CloudMember.PANDA_SERVER + serverName;

        Response response =  etcdClient.list(serverKey);
        this.response(myName,response,cloundDiscoverListener);

    }

    private void response(String myName,Response response,CloudDiscoverListener cloundDiscoverListener){
        if (response.wasError()){
            cloundDiscoverListener.discoverValue(null);
        }
        else{
            List<Node> nodes = response.node().getNodes();
            if (nodes==null || nodes.size() == 0){
                cloundDiscoverListener.discoverValue(null);
            }else{
                //select which node should return
                CloudChoice choice = new CloudChoiceWeightForEtcd(nodes,etcdClient);
                CloudMember selectedCloundMember = choice.choice();

                //注册Caller
                etcdClient.setTemp(selectedCloundMember.callerKey()+"/"+myName,myName,TTL);

                cloundDiscoverListener.discoverValue(selectedCloundMember);


            }
        }
    }

}
