package com.tdd.database.entity;

public class Person {


    private long id;
    private String firstName;
    private String lastName;
    private String ssn;
    private Person inLoveWith;
    private Person engagedWith;

    public Person() {

    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

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


    public void proposeTo(Person beloved) {
        if (beloved.acceptProposalFrom(this)) {
            this.engagedWith = beloved;
            beloved.engagedWith = this;
        }
    }

    public void isInLoveWith(Person beloved) {
        this.inLoveWith = beloved;
    }

    public boolean acceptProposalFrom(Person person) {
        return inLoveWith.equals(person);
    }

    public boolean isEngagedWith(Person person) {
        return person.equals(engagedWith);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Person))
            return false;
        Person p = (Person)obj;
        return  this.firstName.equals(p.firstName) && this.lastName.equals(p.lastName);
    }


}
