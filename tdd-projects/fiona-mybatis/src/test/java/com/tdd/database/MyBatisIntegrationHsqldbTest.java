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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Integration test with in-memory database and MyBatis.
 *
 * 1, HSQLDB is available without installation and just configuring its driver in configuration file of MyBatis.
 * 2, However, all the database should be built from scratch. Fortunately, we can initialise a database in the
 *    fixture of the test.
 * */
public class MyBatisIntegrationHsqldbTest {


    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private PersonDao dao;

    @Before
    public void setUp() throws Exception {
        in = Resources.getResourceAsStream("mybatis-config-hsqldb.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        dao = session.getMapper(PersonDao.class);

        // Create table in a in-memory database.
        dao.createTable("people");
    }

    /**
     * 2, Test in-memory database-HSQLDB.
     * */
    @Test
    public void integrationTestWithHsqldb() throws Exception {
        Person expected= new Person("Adam", "Clay");
        expected.setId(99);
        int result = dao.save(expected);
        //session.commit();
        assertEquals(1, result);

        Person clay = dao.findById(99);
        assertTrue(expected.equals(clay));

    }

    @After
    public void tearDown() throws Exception {
        session.rollback();
        session.close();
    }



}
