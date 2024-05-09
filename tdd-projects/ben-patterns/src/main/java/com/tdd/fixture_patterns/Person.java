package com.tdd.fixture_patterns;

public class Person {


    private long id;
    private String firstName;
    private String lastName;
    private String ssn;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getSsn() {
        return ssn;
    }


    public void proposeTo(Person person) {
        person.isEngagedWith(this);
    }

    public boolean isEngagedWith(Person person) {
        return true;  // hard coded
    }
}
