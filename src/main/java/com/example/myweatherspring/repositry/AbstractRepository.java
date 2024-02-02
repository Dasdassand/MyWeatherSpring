package com.example.myweatherspring.repositry;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import org.hibernate.cfg.Configuration;

@Repository
@Scope("singleton")
public class AbstractRepository implements DisposableBean {
    private final SessionFactory factory;

    {
        Configuration configuration = new Configuration();
        configuration.configure();
        factory = configuration.buildSessionFactory();
    }

    protected Session getSession() {
        return factory.openSession();
    }


    @Override
    public void destroy() throws Exception {
        this.factory.close();
    }
}
