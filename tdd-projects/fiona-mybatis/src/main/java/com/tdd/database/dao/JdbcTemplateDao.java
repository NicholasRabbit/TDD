package com.tdd.database.dao;

import com.tdd.database.entity.Person;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

public class JdbcTemplateDao extends JdbcDaoSupport implements PersonDao {
    @Override
    public int save(Person person) {
        return 0;
    }

    @Override
    public List<Person> findByLastName(String lastName) {
        String sql = "SELECT * FROM	people WHERE last_name = ?";
        String[] args = new String[]{lastName};  // query condition: "where last_name = ${lastName}"
        RowMapper rowMapper = new PersonRowMapper();
        return getJdbcTemplate().query(sql, args, rowMapper);
    }

    @Override
    public int createTable(String tableName) {
        return 0;
    }

    @Override
    public Person findById(long id) {
        return null;
    }

    @Override
    public List<Person> findAll() {
        String sql = "SELECT * FROM	employee";
        RowMapper rowMapper = new PersonRowMapper();
        return getJdbcTemplate().query(sql, rowMapper);
    }

}
