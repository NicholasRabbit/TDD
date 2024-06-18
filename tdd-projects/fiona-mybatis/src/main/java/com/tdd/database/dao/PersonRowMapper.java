package com.tdd.database.dao;

import com.tdd.database.entity.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {
        return new Person(rs.getString("first_name"), rs.getString("last_name"));
    }
}
