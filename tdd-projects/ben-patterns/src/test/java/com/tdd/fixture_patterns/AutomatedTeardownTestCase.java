package com.tdd.fixture_patterns;

import org.easymock.classextension.EasyMock;
import org.junit.After;
import org.junit.Before;
import static org.easymock.classextension.EasyMock.*;

import java.util.ArrayList;
import java.util.List;



public class AutomatedTeardownTestCase<T> {

    // The author declared as below.
    //private List<Object> mockObjects;
    // I declared like the following code. I just want to know if it works
    // when I declared variables with generic type.
    private List<T> mockObjects;


    protected T createMock(Class<T> type) {
        T mock = EasyMock.createMock(type);
        mockObjects.add(mock);
        return mock;
    }

    @Before
    public void setUp() throws Exception {
        mockObjects = new ArrayList<>();
    }

    public void replayAll() {
        for (Object mockObj : mockObjects) {
            replay(mockObj);
        }
    }

    // This is the "Automated Teardown";
    // All the subclass of tests will inherit this method and execute it automatically
    // so that we teardown something we don't need automatically.
    @After
    public void tearDown() throws Exception {
        verifyAll();
    }

    private void verifyAll() {
        for (Object mockObj : mockObjects) {
            verify(mockObj);
        }
    }

}
