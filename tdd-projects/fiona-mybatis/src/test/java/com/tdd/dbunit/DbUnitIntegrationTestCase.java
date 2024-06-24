package com.tdd.dbunit;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A demo of DbUnit.
 *
 * */
public abstract class DbUnitIntegrationTestCase extends DatabaseTestCase {

    // 1, Create a jdbc connection.
    protected Connection getJdbcConnection() {
        //String url = "jdbc:mysql://localhost:3306/tdd";
        String url = "jdbc:hsqldb:mem:tddtest";
        String user = "sa";
        String password = "sa";
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    protected IDatabaseConnection getConnection() throws Exception {
        return new DatabaseConnection(getJdbcConnection());
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        String resource = getClass().getSimpleName() + ".initial.xml";
        InputStream stream = getClass().getResourceAsStream(resource);
        assertNotNull("The resource " + resource + " is not found."  + stream);
        return new FlatXmlDataSet(stream);
    }
}
