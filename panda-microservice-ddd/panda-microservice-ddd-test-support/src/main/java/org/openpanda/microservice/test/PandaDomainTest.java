package org.openpanda.microservice.test;

import io.dropwizard.hibernate.UnitOfWork;
import org.dayatang.domain.InstanceFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ManagedSessionContext;

/**
 * Created by lingen on 2016/12/21.
 */
public class PandaDomainTest {

    @UnitOfWork
    public static void testInDB(Runnable runnable){
        SessionFactory sessionFactory = InstanceFactory.getInstance(SessionFactory.class);
        Session session = sessionFactory.openSession();
        ManagedSessionContext.bind(session);
        Transaction transaction = session.beginTransaction();
        try{
            runnable.run();
        }finally {
            transaction.rollback();
            session.close();
        }
    }
}
