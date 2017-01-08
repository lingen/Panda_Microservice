package org.openpanda.microservice.dropwizard.hibernate;

import com.codahale.metrics.health.HealthCheck;

/**
 * Created by lingen on 2016/12/22.
 */
public class PandaHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }

}
