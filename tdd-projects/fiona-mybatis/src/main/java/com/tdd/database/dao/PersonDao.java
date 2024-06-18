package com.tdd.database.dao;

import com.tdd.database.entity.Person;

import java.util.List;

public interface PersonDao {

    public abstract int save(Person person);

    public abstract List<Person> findByLastName(String lastName);

    public abstract int createTable(String tableName);

    public abstract Person findById(long id);

    public abstract List<Person> findAll();

}
