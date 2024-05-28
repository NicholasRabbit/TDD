### 1,  TestCase in JUnit

path: `junit.framework.TestCase;`

What is "TestCase" used for? 

### 2,  expect(), replay(), verify()

The four steps mentioned above relate to methods in [*org.easymock.EasyMock*](http://easymock.org/api/org/easymock/EasyMock.html):

1. [mock(…)](http://easymock.org/api/org/easymock/EasyMock.html#mock-java.lang.Class-): generates a mock of the target class, be it a concrete class or an interface. Once created, a mock is in “recording” mode, meaning that EasyMock will record any action the Mock Object takes, and replay them in the “replay” mode
2. [expect(…)](http://easymock.org/api/org/easymock/EasyMock.html#expect-T-): with this method, we can set expectations, including calls, results, and exceptions, for associated recording actions
3. [replay(…)](http://easymock.org/api/org/easymock/EasyMock.html#replay-java.lang.Object...-): switches a given mock to “replay” mode. Then, any action triggering previously recorded method calls will replay “recorded results”
4. [verify(…)](http://easymock.org/api/org/easymock/EasyMock.html#verify-java.lang.Object...-): verifies that all expectations were met and that no unexpected call was performed on a mock

[replay](https://stackoverflow.com/questions/5987149/what-is-easymock-replay-used-for) 

[easymock introduction](https://www.baeldung.com/easymock)

### 3, *createMock*(..)

Don't forget to receive the return value of  `createMock(...)`

```java
// createMock(SessionFactory.class);  // Wrong!!
factory = createMock(SessionFactory.class);
```

