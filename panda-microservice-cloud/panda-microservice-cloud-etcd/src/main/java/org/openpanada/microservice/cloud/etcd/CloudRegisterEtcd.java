package org.openpanada.microservice.cloud.etcd;

import com.google.gson.Gson;
import org.boon.core.Sys;
import org.boon.etcd.ClientBuilder;
import org.boon.etcd.Etcd;
import org.boon.etcd.Response;
import org.openpanda.microservice.cloud.PandaCloudException;
import org.openpanda.microservice.cloud.model.CloudMember;
import org.openpanda.microservice.cloud.resiger.CloudRegister;

import java.net.URI;

/**
 * Created by lingen on 2017/1/7.
 */
public class CloudRegisterEtcd implements CloudRegister {

    private static int TTL = 10;

    private Etcd etcdClient;

    public CloudRegisterEtcd(){

    }

    @Override
    public void register(String cloudUrl,CloudMember cloundMember) {
        etcdClient = ClientBuilder.builder().hosts(
                URI.create(cloudUrl)
        ).createClient();


        registerServerDir(cloundMember);

        registerSelf(cloundMember);

        registerCallerDir(cloundMember);

        Sys.println("Register Cloud Success:");
    }

    private void registerServerDir(CloudMember cloundMember){

        Response response = etcdClient.createTempDir(cloundMember.serverKey(),TTL);

        if (response.wasError()){
            etcdClient.updateDirTTL(cloundMember.serverKey(),TTL);
        }
    }


    private void registerSelf(CloudMember cloundMember){

        String value = new Gson().toJson(cloundMember);

        Response response = etcdClient.setTemp(cloundMember.serverKey()+"/"+cloundMember.memberKey(),value,TTL);

        if (response.wasError()){
            throw new PandaCloudException("Set Server Value Fail:"+response.toString());
        }

    }

    private void registerCallerDir(CloudMember cloundMember){
        //register a caller dir
        Response response = etcdClient.createTempDir(cloundMember.callerKey(),TTL);

        if (response.wasError()){
            etcdClient.updateDirTTL(cloundMember.callerKey(),TTL);
        }

    }
}
