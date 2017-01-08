package org.openpanada.microservice.cloud.etcd.choice;

import com.google.gson.Gson;
import org.boon.etcd.Etcd;
import org.boon.etcd.Node;
import org.boon.etcd.Response;
import org.openpanda.microservice.cloud.choice.CloudChoice;
import org.openpanda.microservice.cloud.model.CloudMember;

import java.util.List;

/**
 * Created by lingen on 2017/1/7.
 */
public class CloudChoiceWeightForEtcd implements CloudChoice {

    private List<Node> nodes;

    private Etcd etcdClient;

    public CloudChoiceWeightForEtcd(List<Node> nodes, Etcd etcdClient){
        this.nodes = nodes;
        this.etcdClient = etcdClient;
    }

    @Override
    public CloudMember choice() {
        double min = (double) Integer.MAX_VALUE;
        CloudMember selectedCloundMember = null;

        for (Node node:nodes){
            String value = node.getValue();
            CloudMember cloundMember = new Gson().fromJson(value,CloudMember.class);

            //find the cloud member's caller number
            Response callResponse = etcdClient.list(cloundMember.callerKey());

            int num = 0;
            if (callResponse.successful()){
                List<Node> nodes = callResponse.node().getNodes();
                if (nodes!=null){
                    num = nodes.size();
                }
            }

            double countWithWeight = (double)num / cloundMember.getWeight();
            if (countWithWeight < min){
                min = countWithWeight;
                selectedCloundMember = cloundMember;
            }
        }

        return selectedCloundMember;
    }




}
