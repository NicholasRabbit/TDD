package com.tdd.database.spring.jdbc;

import com.mockobjects.sql.MockSingleRowResultSet;
import com.tdd.database.entity.Person;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Alleviating pain with Spring's Jdbc template.
 *
 * 1, Let' test a RowMapper first.
 * */
public class PersonRowMapperTest {

    @Test
    public void testMappingRow() throws Exception {
        Person expected = new Person("Omar", "Little");
        // Populate a mock ResultSet object. We pretend to get data from a database.
        Map<String, Object> data = new HashMap<>();
        data.put("first_name", expected.getFirstName());
        data.put("last_name", expected.getLastName());
        MockSingleRowResultSet rs = new MockSingleRowResultSet();
        rs.addExpectedNamedValues(data);

        assertEquals(expected, new PersonRowMapper().mapRow(rs, 1));

    }


}
