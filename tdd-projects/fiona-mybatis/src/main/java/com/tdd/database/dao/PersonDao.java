package com.tdd.database.dao;

import com.tdd.database.entity.Person;

import java.util.List;

public interface PersonDao {

    public abstract int save(Person person);

    public abstract List<Person> findByLastName(String lastName);

}
