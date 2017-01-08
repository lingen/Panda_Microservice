package org.openpanda.microservice.dropwizard.hibernate;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.openpanda.pandalib.cloud.model.CloudMember;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by lingen on 2016/12/21.
 */
public class PandaConfiguration extends Configuration {

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();


    public DataSourceFactory getDatabase(){
        return database;
    }

    private CloudMember cloudMember;

    @JsonProperty
    private String cloudUrl;

    public CloudMember getCloudMember() {
        return cloudMember;
    }

    public void setCloudMember(CloudMember cloudMember) {
        this.cloudMember = cloudMember;
    }

    public String getCloudUrl() {
        return cloudUrl;
    }

    public void setCloudUrl(String cloudUrl) {
        this.cloudUrl = cloudUrl;
    }
}
