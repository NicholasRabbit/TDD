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
import java.util.Arrays;
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
     * 1, Test the real database.
     * Connected to the real database successfully.
     *
     * Caution: Don't call "commit()" in the test.
     * See the following elaboration.
     * */
    @Test
    public void integrationTestWithMyBatis() throws Exception {
        List<Person> smiths = dao.findByLastName("Bennett");
        //session.commit();
        assertTrue(smiths.size() > 0);
    }

    /**
     * To test if it persists an object to the real MySQL database.
     * */
    @Test
    public void persistedObjectsExistInDatabase() throws Exception {
        Person person = new Person("Lily", "Bennett");
        int result = dao.save(person);
        /*
        * Caution:
        * There is no need to commit because we still can get the 'person' by call "findByLastName(..)"
        * The real reason we don't call "commit()" is that we should keep the database as it was so that
        * the others tests won't be disturbed or affected.
        * A simple example is that we save "new Person("Lily", ...)" in this test, but we still save the
        * same person in another test, if we didn't roll back in this test, another test might be failed
        * because the state of database has been changed.
        * */
        //session.commit();
        List<Person> actual = dao.findByLastName("Bennett");
        assertEquals(1, result);
        assertEquals(Arrays.asList(person), actual);
    }



    @After
    public void tearDown() throws Exception {
        session.rollback();
        session.close();
    }

}
