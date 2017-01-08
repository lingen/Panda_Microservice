package org.openpanda.microservice.dropwizard.hibernate;

import com.codahale.metrics.health.HealthCheck;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.dayatang.domain.EntityRepository;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.domain.InstanceProvider;
import org.dayatang.ioc.guice.GuiceInstanceProvider;
import org.dayatang.persistence.hibernate.EntityRepositoryHibernate;
import org.dayatang.querychannel.QueryChannelService;
import org.dayatang.querychannel.impl.QueryChannelServiceImpl;
import org.hibernate.SessionFactory;
import org.openpanda.pandalib.cloud.resiger.CloudRegister;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lingen on 2016/12/21.
 */
public abstract class PandaApplication<P extends PandaConfiguration> extends Application<P> {


    public static String PANDA_HEALTH_CHECK = "Panda_HealthCheck";

    public abstract Class[] entities();

    public abstract PandaGuiceModule createModule();

    public abstract Map<String,HealthCheck> healthCheckList();

    public abstract List resources();

    public abstract List<String> cloudServers();

    public abstract CloudRegister cloudRegister();

    public void extendRun(P p,Environment environment){

    }

    public void extendInitialize(Bootstrap<P> bootstrap){

    }

    /**
     * hibernate
     */
    public final HibernateBundle<P> hibernate = new HibernateBundle<P>(firstEntity(),otherEntities()) {
        @Override
        public DataSourceFactory getDataSourceFactory(P configuration) {
            return configuration.getDatabase();
        }
    };


    @Override
    public void run(P p, Environment environment) throws Exception {

        //注册InstanceProvider
        InstanceProvider instanceProvider = new GuiceInstanceProvider(Guice.createInjector(createModule()));
        InstanceFactory.setInstanceProvider(instanceProvider);

        Map<String,HealthCheck> healthCheckList = healthCheckList();

        environment.healthChecks().register(PANDA_HEALTH_CHECK,new PandaHealthCheck());

        for (String healthName:healthCheckList.keySet()){
            environment.healthChecks().register(healthName,healthCheckList.get(healthName));
        }

        for (Object resource:resources()){
            environment.jersey().register(resource);
        }

        registerCloudMember(p);

        extendRun(p,environment);
    }

    @Override
    public void initialize(Bootstrap<P> bootstrap) {
        // nothing to do yet
        bootstrap.addBundle(hibernate);
        extendInitialize(bootstrap);
    }


    private void registerCloudMember(final P p){
        final CloudRegister cloudRegister = InstanceFactory.getInstance(CloudRegister.class);

        if (cloudRegister!=null){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    cloudRegister.register(p.getCloudUrl(),p.getCloudMember());
                }
            }, 0, 10 * 1000);
        }

    }

    public abstract class PandaGuiceModule extends AbstractModule {

        protected void configure(){
            EntityRepository entityRepository = new EntityRepositoryHibernate(PandaApplication.this.hibernate.getSessionFactory());
            QueryChannelService queryChannelService = new QueryChannelServiceImpl(entityRepository);
            //注册EntityRepository
            bind(EntityRepository.class).toInstance(entityRepository);
            //注册QueryChannelService
            bind(QueryChannelService.class).toInstance(queryChannelService);

            bind(SessionFactory.class).toInstance(hibernate.getSessionFactory());

            CloudRegister cloudRegister = cloudRegister();

            if (cloudRegister!=null){
                bind(CloudRegister.class).toInstance(cloudRegister);
            }

            bindIoc();
        }

        public abstract void bindIoc();
    }


    private Class firstEntity(){
        Class[] entities = entities();
        if (entities.length > 0){
            return entities[0];
        }
        return null;
    }

    public  Class[] otherEntities(){
        Class[] entities = entities();
        if (entities.length > 1){
            Class[] otherEntities = new Class[entities.length - 1];
            for (int i = 1;i < entities.length;i++){
                otherEntities[i-1] = entities[i];
            }
            return otherEntities;
        }
        return new Class[0];
    }

}
