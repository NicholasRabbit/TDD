package com.tdd.database.dao;

import com.tdd.database.entity.Person;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcPersonDao implements PersonDao{

    private DataSource dataSource;

    @Override
    public List<Person> findByLastName(String lastName) {
        try {
            Connection conn = dataSource.getConnection();
            String sql = "SELECT * FROM	people WHERE last_name = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, lastName);
            ResultSet resultSet = stmt.executeQuery();
            List<Person> people = new ArrayList<>();
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String last = resultSet.getString("last_name");
                people.add(new Person(firstName, last));
            }

            resultSet.close();
            stmt.close();
            conn.close();

            return people;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
