package com.tdd.database.hibernate;

import com.tdd.database.entity.Person;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.easymock.classextension.EasyMock.*;

/**
 * Although Hibernate is an obsolete ORM framework, it was used to write the demo in the book which was published
 * in 2004 when Hibernate was quite popular with Java programmers.
 * */
public class HibernatePersonDaoTest {

    private SessionFactory factory;
    private Session session;
    private Query query;


    @Before
    public void setUp() throws Exception {
        // Mock objects of Hibernate.
        factory = createMock(SessionFactory.class);
        session = createMock(Session.class);
        query = createMock(Query.class);
    }

    /**
     * 1, Test dao layer with Hibernate.
     * */
    @Test
    public void testFindByLastName() throws Exception {
        String hql = "from Person p where p.last_name = :lastName";
        String lastName = "Smith";

        // To make our mock objects to return a bunch of Smiths.
        List<Person> theSmiths = createPeopleWithLastName("Smith");

        // Mock all the objects of Hibernate.
        expect(factory.getCurrentSession()).andReturn(session);
        expect(session.createQuery(hql)).andReturn(query);
        expect(query.setParameter("last_name", lastName)).andReturn(query);
        expect(query.list()).andReturn(theSmiths);
        replay(factory, session, query);

        // Note: the HibernateDao, which is the object that is going to be tested, must be real.
        HibernatePersonDao dao = new HibernatePersonDao();
        dao.setSessionFactory(factory);
        assertEquals(theSmiths, dao.findByLastName(lastName));

        verify(factory, session, query);

    }


    private List<Person> createPeopleWithLastName(String lastName) {
        List<Person> expected = new ArrayList<>();
        expected.add(new Person("Alice", lastName));
        expected.add(new Person("Ben", lastName));
        expected.add(new Person("Clark", lastName));
        return expected;
    }


    /**
     * 2, Testing for exceptions which we expected.
     * I write a duplicated method of findByLastName and name it findByLastNameWithException for the sake of comparison.
     * If the return list is empty, an HibernateException should be thrown.
     * */
    @Test
    public void testFindByLastNameReturnsEmptyListUponException() throws Exception {
        String hql = "from Person p where p.last_name = :lastName";
        String lastName = "Smith";
        HibernateException hibernateError = new HibernateException("");

        expect(factory.getCurrentSession()).andReturn(session);
        expect(session.createQuery(hql)).andReturn(query);
        expect(query.setParameter("last_name", lastName)).andReturn(query);
        // Make list() throw an exception.
        expect(query.list()).andThrow(hibernateError);
        replay(factory, session, query);

        HibernatePersonDao dao = new HibernatePersonDao();
        dao.setSessionFactory(factory);

        try {
            dao.findByLastNameWithException(lastName);
            fail("should have thrown an exception");
        } catch (RuntimeException expected) {
            assertSame(hibernateError, expected.getCause());
        }

        verify(factory, session, query);


    }




}
