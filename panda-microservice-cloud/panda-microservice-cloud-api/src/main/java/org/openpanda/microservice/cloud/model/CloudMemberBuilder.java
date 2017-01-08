package org.openpanda.microservice.cloud.model;

/**
 * Created by lingen on 2017/1/5.
 */
public class CloudMemberBuilder {


    private CloudMember cloundMember;

    private CloudMemberBuilder(){

    }

    public static CloudMemberBuilder newInstance(){
        CloudMemberBuilder cloundMemberBuilder = new CloudMemberBuilder();
        cloundMemberBuilder.cloundMember = new CloudMember();
        return cloundMemberBuilder;
    }


    public CloudMemberBuilder name(String name){
        cloundMember.setName(name);
        return this;
    }

    public CloudMemberBuilder ip(String ip){
        cloundMember.setIp(ip);
        return this;
    }

    public CloudMemberBuilder port(int port){
        cloundMember.setPort(port);
        return this;
    }

    public CloudMemberBuilder type(CloudMemberType type){
        cloundMember.setType(type);
        return this;
    }

    public CloudMemberBuilder weight(int weight){
        cloundMember.setWeight(weight);
        return this;
    }

    public CloudMember builer(){
        return cloundMember;
    }


}
