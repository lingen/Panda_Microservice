package org.openpanada.microservice.cloud.etcd;

import org.boon.core.Sys;
import org.junit.Ignore;
import org.junit.Test;
import org.openpanda.microservice.cloud.discovery.CloudDiscovery;
import org.openpanda.microservice.cloud.model.CloudMember;
import org.openpanda.microservice.cloud.model.CloudMemberBuilder;
import org.openpanda.microservice.cloud.resiger.CloudRegister;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

/**
 * Created by lingen on 2017/1/7.
 */
@Ignore
public class TestCloudDiscoveryEtcd {

    private String cloudUrl = "http://23.83.241.91:4001";

    @Test
    public void test(){
        final CloudMember cloundMember = CloudMemberBuilder.newInstance().name("Account").ip("127.0.0.1").port(8080).weight(1).builer();
        final CloudMember cloundMember2 = CloudMemberBuilder.newInstance().name("Account").ip("127.0.0.2").port(7070).weight(2).builer();

        CloudRegister cloundRegister = new CloudRegisterEtcd();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                cloundRegister.register(cloudUrl,cloundMember);
                cloundRegister.register(cloudUrl,cloundMember2);

            }
        },0,5000);

        CloudDiscovery cloundDiscovery = new CloudDiscoveryEtcd("http://23.83.241.91:4001");

        while (true){
            cloundDiscovery.discovery(UUID.randomUUID().toString(),"Account", cloundMemberResponse -> {
                if (cloundMemberResponse!=null){
                    Sys.println(cloundMemberResponse.getIp());
                }else{
                    Sys.println("找不到服务");
                }
            });

            Sys.sleep(500);
        }

    }
}
