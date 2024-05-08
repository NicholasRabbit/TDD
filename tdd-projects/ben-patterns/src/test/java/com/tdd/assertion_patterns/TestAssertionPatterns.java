package com.tdd.assertion_patterns;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TestAssertionPatterns {

    private List<String> strList;

    @Before
    public void setUp() {
        strList = new ArrayList<>();
    }

    /**
     * 4.5.1 Assertion patterns. * */
    /**
     * 1. Resulting state assertion.
     * */
    @Test
    public void sizeOfListReflectsItemsAddedToIt() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("foo");
        assertEquals(1, list.size());  // state verification
    }
    /**
     * 2. Guard assertion.
     * Sometimes, the purpose of adding a Guard assertion is to make sure an assumption about the
     * fixture's staring state is correct.
     * */
    @Test
    public void listIsNoLongerEmptyAfterAddingAnItemToIt() throws Exception {
        List<String> list = new ArrayList<>();
        assertTrue(list.isEmpty());     // guard assertion
        list.add("bar");
        assertFalse(list.isEmpty());    //state verification
    }

    /**
     * 3, Delta assertion.
     * Delta assertion means that we test whether the difference, or delta, between the initial and after states is
     * what we expected.
     * */
    @Test
    public void testDeltaAssertion() throws Exception {
        int sizeBefore = strList.size();   // record the "before" state
        strList.add("retaliate");
        assertEquals(sizeBefore + 1, strList.size());  // delta verification
    }

    /**
     * 4, Custom assertion.
     * Custom assertion is to encapsulate complex verification logic in a method which we can call from a test.
     * I could not find the source of this session. Later I will add code myself after knowing more about patterns
     * of assertion.
     * */
    @Test
    public void timeslotsAreOnWeekdays() throws Exception {
        MeetingCalendar calendar = new MeetingCalendar();
        // omitted: add appointments to calendar until
        // end of office hours next Friday
        Date time = calendar.nextAvailableStartingTime();
        assertIsDuringOfficeHoursOnWeekday(time);
        // encapsulate logic
    }
    private void assertIsDuringOfficeHoursOnWeekday(Date time) {
        // actual assertion logic omitted for brevity
    }


}
