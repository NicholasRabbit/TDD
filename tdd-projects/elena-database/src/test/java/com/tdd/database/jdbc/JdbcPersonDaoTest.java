package com.tdd.database.jdbc;


import com.mockobjects.sql.MockMultiRowResultSet;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.tdd.database.dao.JdbcPersonDao;
import com.tdd.database.entity.Person;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.*;


/**
 * To test persistent layer maybe the most difficult thing in TDD.
 * Let's first start with testing JDBC.
 * */
public class JdbcPersonDaoTest {

    /**
     * What does this test do?
     * We abandoned the access to a database and faked or mocked some data for "findByLastName". If an error occurred
     * in the process of querying the database, it would not be detected.
     * The purpose of this test is just to test "findByLastName" independently without an interruption from the outside.
     * That is the meaning of the unit test, which is absolutely independent.
     * */
    @Test
    public void testFindByLastName() throws Exception {
        // Create all of the mock objects of JDBC.
        DataSource dataSource = createMock(DataSource.class);
        Connection connection = createMock(Connection.class);
        expect(dataSource.getConnection()).andReturn(connection);
        String sql = "SELECT * FROM	people WHERE last_name = ?";
        PreparedStatement stmt = createMock(PreparedStatement.class);
        expect(connection.prepareStatement(sql)).andReturn(stmt);

        stmt.setString(1, "Smith");

        // Create a mock of ResultSet and fake the query result.
        MockMultiRowResultSet resultSet = new MockMultiRowResultSet();
        String[] columns = {"first_name", "last_name"};
        // Initiate columns.
        resultSet.setupColumnNames(columns);
        // Create fake data.
        List<Person> smiths = createPeopleWithLastName("Smith");
        // Initiate rows.
        resultSet.setupRows(asResultSetArray(smiths));
        expect(stmt.executeQuery()).andReturn(resultSet);

        resultSet.setExpectedCloseCalls(1);
        stmt.close();
        connection.close();

        replay(dataSource, connection, stmt);

        // The test for DAO really begins.
        JdbcPersonDao dao = new JdbcPersonDao();
        // Here is the fake "dataSource" mocked by EasyMock, so the real dao doesn't need to connect to database.
        dao.setDataSource(dataSource);
        List<Person> people =  dao.findByLastName("Smith");
        assertEquals(smiths, people);
        verify(dataSource, connection, stmt);
        resultSet.verify();

    }

    private List<Person> createPeopleWithLastName(String lastName) {
        List<Person> expected = new ArrayList<>();
        expected.add(new Person("Alice", lastName));
        expected.add(new Person("Ben", lastName));
        expected.add(new Person("Clark", lastName));
        return expected;
    }

    private Object[][] asResultSetArray(List<Person> people) {
        Object[][] array = new Object[people.size()][2];
        for (int i = 0; i < array.length; i++) {
            Person person = people.get(i);
            array[i] = new Object[]{person.getFirstName(), person.getLastName()};
        }
        return array;
    }

    /**
     * Just for comparison, in this test a real datasource is used to connect the real database.
     * */
    @Test
    public void testFindByNameWithRealDataSource() throws Exception {
        String url = "jdbc:mysql://localhost:3306/tdd?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull" +
                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false" +
                "&serverTimezone=GMT%2B8&nullCatalogMeansCurrent=true&allowPublicKeyRetrieval=true";
        String user = "root";
        String password = "root";
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setPortNumber(3306);
        dataSource.setServerName("localhost");
        JdbcPersonDao dao = new JdbcPersonDao();
        dao.setDataSource(dataSource);
        // What we expected
        List<Person> smiths = createPeopleWithLastName("Smith");
        // What we get from the database.
        List<Person> people = dao.findByLastName("Smith");

        assertEquals(smiths, people);

    }
}
