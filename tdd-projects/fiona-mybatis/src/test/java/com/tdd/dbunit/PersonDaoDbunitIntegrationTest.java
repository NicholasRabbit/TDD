package com.tdd.dbunit;

import com.tdd.database.dao.JdbcTemplateDao;
import com.tdd.database.entity.Person;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * */
public class PersonDaoDbunitIntegrationTest extends DbUnitIntegrationTestCase{

    private List<Person> expectedList;

    @Before
    @Override
    public void setUp() throws Exception {
        // Invoking superclass to populate data.
        super.setUp();  // The getConnection() will be called in the superclass.
        expectedList.add(new Person("Al", "Freeman"));
        expectedList.add(new Person("Bill", "Brewster"));
        expectedList.add(new Person("Juan", "Alvarez"));

    }

    @Test
    public void testFindAll() throws Exception {
        JdbcTemplateDao dao = new JdbcTemplateDao();
        dao.setDataSource(new BasicDataSource() {
            @Override
            public Connection getConnection() throws SQLException {
                return getJdbcConnection();  // call the getJdbcConnection() of the DbunitIntegrationTestCase.
            }
        });
        assertEquals(expectedList, dao.findAll());
    }


}
