package org.openpanada.microservice.cloud.etcd;

import com.google.gson.Gson;
import org.boon.core.Sys;
import org.boon.etcd.ClientBuilder;
import org.boon.etcd.Etcd;
import org.boon.etcd.Response;
import org.junit.Test;
import org.openpanda.microservice.cloud.model.CloudMember;
import org.openpanda.microservice.cloud.model.CloudMemberBuilder;

import java.net.URI;

import static org.boon.Boon.puts;

/**
 * Created by lingen on 2017/1/4.
 */
public class TestEtcd {

    @Test
    public void testB(){

        Etcd client = ClientBuilder.builder().hosts(
                URI.create("http://23.83.241.91:4001")
        ).createClient();

        CloudMember cloundMember = CloudMemberBuilder.newInstance().name("Account").ip("127.0.0.1").port(8080).weight(1).builer();

        String value = new Gson().toJson(cloundMember);

        Response response;

        response = client.createTempDir(cloundMember.serverKey(),5);

        puts(response);

        response = client.setTemp(cloundMember.serverKey()+"/"+cloundMember.memberKey(),value,5);

        puts(response);

        response = client.list(cloundMember.serverKey());

        puts(response);

        Sys.sleep(6000);

        response = client.list(cloundMember.serverKey());

        puts(response);


    }

    @Test
    public void testA() {

        Etcd client = ClientBuilder.builder().hosts(
                URI.create("http://23.83.241.91:4001")
        ).createClient();

        Response response;

        response = client.get("foo");

        puts(response);

        response = client.set("foo", "Rick Was here");

        puts("SET RESPONSE", response);


        response = client.get("foo");



        puts("GET FOO", response);


        response = client.delete("foo");


        puts(response);


        client.setTemp("tempKey", "tempValue", 5);
        Sys.sleep(1000);

        puts(client.get("tempKey").node().getValue());
        Sys.sleep(1000);


        puts(client.get("tempKey").node().getValue());

        Sys.sleep(1000);


        puts(client.get("tempKey").node().getValue());

        Sys.sleep(4000);


        puts(client.get("tempKey"));


        //Response waitOnKey = client.wait("waitOnKey");

        //puts("GOT KEY WE ARE WAITING ONE", waitOnKey);

        //puts("Create a dir");

        client.createDir("conf");


        client.createDir("conf/foo1");

        client.createDir("conf/foo2");


        client.createDir("conf/foo3");


        client.createDir("conf/foo1/bar1");

        client.addToDir("conf/foo1/bar1", "myKey", "myValue");

        response = client.listRecursive("");

        puts(response);


        response = client.deleteDir("conf");


        puts(response);

        response = client.deleteDirRecursively("conf");


        puts(response);


        response = client.listRecursive("");

        puts(response);

        response = client.createDir("queue");
        puts(response);

        response = client.createDir("queue/job1");
        puts(response);


        response = client.set("queue/job1/mom", "mom");
        puts(response);

        response = client.createDir("queue/job29");
        puts(response);


        response = client.createDir("queue/job3");
        puts(response);


        response = client.listSorted("queue");
        puts(response);


    }
}
