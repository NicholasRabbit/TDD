### 1, General Note

All the tests in TDD follow a typical four phase sequence: **setup, exercise, verify, teardown**.

### 2,  Behaviour and state verification

 [Mocks Aren't Stubs](https://martinfowler.com/articles/mocksArentStubs.html)

 [Behaviour vs State](https://coderstower.com/2019/09/24/unit-testing-behavior-vs-state/)

#### 2.1 State verification

What is state verification?



#### 2.2 Behaviour verification

Behaviour validation is not suitable as the default way of TDD. 

Note: Mock is not always behaviour validation.   [TDD in Practice](https://time.geekbang.org/column/article/496698)

But behaviour is useful when validating whether a method of sending SMS is called since it is impossible to verify the state-if the user receive the message or not. 

