### 1, General Note

1.1 All the tests in TDD follow a typical four phase sequence: **setup, exercise, verify, teardown**.

1.2 How can I adopt TDD if the frame has been already designed? 

​	  You can use test doubles instead of the function which have already existed and adopt TDD for new   requirement.

[Overlook TDD](../note-images/The frame of TDD.jpg)

### 2,  Behaviour and state verification

 [Mocks Aren't Stubs](https://martinfowler.com/articles/mocksArentStubs.html)

 [Behaviour vs State](https://coderstower.com/2019/09/24/unit-testing-behavior-vs-state/)

#### 2.1 State verification

What is state verification?



#### 2.2 Behaviour verification

Behaviour validation is not suitable as the default way of TDD. 

Note: Mock is not always behaviour validation.   [TDD in Practice](https://time.geekbang.org/column/article/496698)

But behaviour is useful when validating whether a method of sending SMS is called since it is impossible to verify the state-if the user receive the message or not. 

