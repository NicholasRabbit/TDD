package com.tdd.database.hibernate;

import com.tdd.database.entity.Person;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import java.util.List;

public class HibernatePersonDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Person> findByLastName(String lastName) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Person p where p.last_name = :lastName";
        Query query = session.createQuery(hql);
        query.setParameter("last_name", lastName);
        return query.list();
    }

}
