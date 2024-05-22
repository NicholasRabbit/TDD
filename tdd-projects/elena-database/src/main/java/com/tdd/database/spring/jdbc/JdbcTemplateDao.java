package com.tdd.database.spring.jdbc;

import com.tdd.database.dao.PersonDao;
import com.tdd.database.entity.Person;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

public class JdbcTemplateDao extends JdbcDaoSupport implements PersonDao {
    @Override
    public List<Person> findByLastName(String lastName) {
        String sql = "SELECT * FROM	people WHERE last_name = ?";
        String[] args = new String[]{lastName};  // query condition: "where last_name = ${lastName}"
        RowMapper rowMapper = new PersonRowMapper();
        return getJdbcTemplate().query(sql, args, rowMapper);
    }

}
