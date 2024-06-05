package com.tdd.database.integration;

import com.tdd.database.entity.Person;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

public class HibernateIntegrationPersonDao {

    private SessionFactory factory;

    public void setSessionFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public void save(Person person) {
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(person);
        transaction.commit();
    }

}
