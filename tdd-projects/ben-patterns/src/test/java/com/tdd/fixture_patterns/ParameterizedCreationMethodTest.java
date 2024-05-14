package com.tdd.fixture_patterns;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.*;

public class ParameterizedCreationMethodTest {

    private Person alice;
    private Person billy;
    private Person clark;

    /**
     * 4.5.2 Fixture Patterns
     * 1, Parameterized Creation Method.
     * */
    /*@Before   //The following fixture is cluttered so we should adopt parameterized creation method.
    public void setUp() {
        alice = new Person();
        alice.setId(1L);
        alice.setFirstName("Alice");
        alice.setLastName("Walker");
        alice.setSsn("11111");

        billy = new Person();
        billy.setId(2L);
        billy.setFirstName("Billy");
        billy.setLastName("Burke");
        billy.setSsn("22222");

        clark = new Person();
        clark.setId(3L);
        clark.setFirstName("Clark");
        clark.setLastName("White");
        clark.setSsn("33333");

    }*/

    @Before
    public void setUp() {
        alice = createPerson("Alice", "Walker");  //Parameterized Creation Method.
        billy = createPerson("Billy", "Burke");
        clark = createPerson("Clark", "White");
        alice.isInLoveWith(billy);
    }

    private Person createPerson(String firstName, String lastName) {
        Person person = new Person();
        person.setId(new Random().nextLong());
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setSsn(UUID.randomUUID().toString());
        return person;
    }

    @Test
    public void aliceShouldAcceptWhenProposedToByBilly() throws Exception {
        billy.proposeTo(alice);
        assertTrue(alice.isEngagedWith(billy));
    }

    @Test
    public void aliceShouldNotAcceptProposalFromClark() throws Exception {
        clark.proposeTo(alice);
        assertFalse(alice.acceptProposalFrom(clark));
    }

}
