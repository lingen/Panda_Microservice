package org.openpanda.microservice.cloud.model;

/**
 * Created by lingen on 2017/1/4.
 */
public class CloudMember {

    public static String PANDA_SERVER = "Panda_Server_";

    public static String PANDA_Server_CALLER = "Server_Caller_";

    private String name;

    private String ip;

    private int port = 80;

    private int weight = 1;

    private CloudMemberType type = CloudMemberType.Http;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String memberKey(){
        return type+":"+ip+":"+port;
    }

    public CloudMemberType getType() {
        return type;
    }

    public void setType(CloudMemberType type) {
        this.type = type;
    }

    public String serverKey(){
        return PANDA_SERVER + name;
    }

    public String callerKey(){
        return PANDA_Server_CALLER + memberKey();
    }
}
