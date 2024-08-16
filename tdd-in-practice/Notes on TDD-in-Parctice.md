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

To test whether the state of SUT(system under test) is changed or not. State verification main approach in TDD.

#### 2.2 Behaviour verification

What is behaviour verification?

It verifies that whether a method is called with the correct arguments.  Normally, `verify(...) ` of Mockito framework is used in "Behaviour verification".

**Behaviour validation is not suitable as the default way of TDD.** 

Note: Mock is not always behaviour validation.   [TDD in Practice](https://time.geekbang.org/column/article/496698)

But behaviour is useful when validating whether a method of sending SMS is called since it is impossible to verify the state-if the user receive the message or not. 

**Note:** It is not necessarily "behaviour verification" when using `Mockito.mock(...)`.  `mock(...)` could be used in "State verification". （**注意：**1， 使用Mock实际也是状态验证，只要不是使用Mockito的verify()来验证行为，被验证的类和方法不要mock。）

### 3, Write a Restful Framework with TDD

Note: The requirement is to write a framework such as "Spring MVC" not to  write a common web application based on a framework. 

### 4, Note from the Course

1,

> Question:
>
> 我认为保证测试有效性有两点： 1、不能对待测方法的实现使用测试替身，可以对测试方法调用的外部组件使用测试替身 2、不能行为验证绑定到实现方法的内部 另外我有个问题，最后一个视频演示的是 Controller -> Dao 的场景，这种直接对 Dao 使用测试替身，这种方法毋庸置疑，但如果是多层构架，如 Controller -> Service -> Dao ，那么我们在测 Controller 时应该是对 Dao 使用测试替身还对 Service 使用测试替身
>
> 作者(徐昊)回复: 驱动controller的时候 double service 驱动service的时候 double dao

2, 

> 需要提醒一下，经典学派和伦敦学派是 TDD 中都需要掌握的基本功。在功能上下文内，以经典学派为主；而跨功能上下文时，可以使用伦敦学派对不同的功能上下文进行隔离。