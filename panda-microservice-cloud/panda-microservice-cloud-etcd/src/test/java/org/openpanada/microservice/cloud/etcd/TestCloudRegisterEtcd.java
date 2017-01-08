package org.openpanada.microservice.cloud.etcd;

import org.junit.Test;
import org.openpanda.pandalib.cloud.model.CloudMember;
import org.openpanda.pandalib.cloud.model.CloudMemberBuilder;
import org.openpanda.pandalib.cloud.resiger.CloudRegister;

/**
 * Created by lingen on 2017/1/7.
 */
public class TestCloudRegisterEtcd {
    @Test
    public void testRegister(){

        CloudMember cloundMember = CloudMemberBuilder.newInstance().name("Account").ip("127.0.0.1").port(8080).weight(1).builer();
        CloudRegister cloundRegister = new CloudRegisterEtcd();
        cloundRegister.register("http://23.83.241.91:4001",cloundMember);

    }
}
