package com.tdd.database.spring.jdbc;

import com.tdd.database.entity.Person;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.easymock.classextension.EasyMock.*;

/**
 * Alleviating pain wit Spring's JdbcTemplate
 * 2, JdbcTemplate
 * */
public class JdbcTemplatePersonDaoTest {

    @Test
    public void testFindByLastName() throws Exception {
        final String lastName = "Smith";
        final List<Person> smiths = createPeopleWithLastName(lastName);
        // Return hard-coded list of people from query.
        JdbcTemplate template = createMock(JdbcTemplate.class);
        String sql = "SELECT * FROM	people WHERE last_name = ?";
        template.query(eq(sql), aryEq(new Object[]{lastName}), isA(PersonRowMapper.class));
        expectLastCall().andReturn(smiths);
        replay(template);

        // Populate the mock template to JdbcTemplateDao.
        JdbcTemplateDao dao = new JdbcTemplateDao();
        dao.setJdbcTemplate(template);
        // DAO should return as expected.
        assertEquals(smiths, dao.findByLastName(lastName));

        verify(template);

    }


    private List<Person> createPeopleWithLastName(String lastName) {
        List<Person> expected = new ArrayList<>();
        expected.add(new Person("Alice", lastName));
        expected.add(new Person("Ben", lastName));
        expected.add(new Person("Clark", lastName));
        return expected;
    }

}
