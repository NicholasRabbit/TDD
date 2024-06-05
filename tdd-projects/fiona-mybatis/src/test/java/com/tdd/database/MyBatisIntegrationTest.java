package com.tdd.database;

import com.tdd.database.dao.PersonDao;
import com.tdd.database.entity.Person;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class MyBatisIntegrationTest {


    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private PersonDao dao;

    @Before
    public void setUp() throws Exception {
        in = Resources.getResourceAsStream("mybatis-config-test.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        dao = session.getMapper(PersonDao.class);
    }

    /**
     * Connect to the real database successfully.
     * */
    @Test
    public void integrationTestWithMyBatis() throws Exception {
        List<Person> smiths = dao.findByLastName("Smith");
        session.commit();
        assertTrue(smiths.size() > 0);
    }

    /**
     * To test if it succeed to persist an object to the real MySQL database.
     * */
    @Test
    public void persistedObjectsExistInDatabase() throws Exception {
        in = Resources.getResourceAsStream("mybatis-config-test.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        dao = session.getMapper(PersonDao.class);

        Person person = new Person("Lily", "Bennett");
        int result = dao.save(person);

        session.commit();

        assertEquals(1, result);

    }

    @After
    public void tearDown() throws Exception {
        session.close();
    }

}
