package com.tdd.database;

import com.tdd.database.dao.PersonDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class MyBatisIntegrationTest {

    /**
     * Connect to the real database successfully.
     * */
    @Test
    public void integrationTestWithMyBatis() throws Exception {

        InputStream in = Resources.getResourceAsStream("mybatis-config-test.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession session = factory.openSession();

        PersonDao dao = session.getMapper(PersonDao.class);
        dao.findByLastName("Smith");

    }
}
