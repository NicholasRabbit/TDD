### 1，写测试的好处

1. 写测试会加快开发；
2. 写测试会减少bug；
3. 写测试会提升写程序的幸福感。!!

### 2，The definition of a good test

参考：

1.  [Unit Test-Martin Fowler](https://martinfowler.com/bliki/UnitTest.html)  
2. https://reflectoring.io/unit-testing-spring-boot/
3.  [【保姆级教程】Spring Boot 单元测试_springboot单元测试教程_暗涧幽火的博客-CSDN博客](https://blog.csdn.net/wangxi06/article/details/114630426)  

- 单元测试只是针对的项目中最小的单元，即方法或单个类，进行测试，测试这个方法是否是执行你预期的功能。
- 好的单元测试耗时仅几毫秒(A good unit test only takes milliseconds.)
- 单元测试时不要每次都启动整个项目注入属性，当整个项目增大后会很耗费时间。

What are the properties of a good test?

- A good test is atomic.
- A good test is isolated.

### 3，如何写好单元测试

1，不是为了写测试而写，要清楚代码哪些地方需要重点测，才去针对这些地方写，**一般单元测试的覆盖率是40-60%**；

2，写好的单元测试在不同的环境中都要运行；

单元测试的特点：

- 运行时间必须要很短，毫秒级别；
- 可以独立运行；
- 可重复，运行期不做更改，每次在不同环境运行得到的结果必须一致；
- 自检查，测试应该在没有任何人工交互的情况下自动检测是否通过。
- Test code should also be refactored if they are lack of readability, maintainability.   [xUnit](http://xunitpatterns.com/)

### 4, in memory database

While writing unit test, you can use in-memory database(not Redis). The nice thing about these tools is that they allow you to use regular SQL to query them.

<a href="https://martinfowler.com/bliki/InMemoryTestDatabase.html">In-memory Database (Marting Fowler)</a>

<a href="http://hsqldb.org/">HSQLDB(Java In-memory Database)</a>

### 5, TDD

In Test driven development, you should write test first, and then write codes to pass the test. The last and darn important step is refactoring.

**TDD cycle:**  test--code--refactor (or red-green-green)

1. Specification by example

   > In other words, instead of the traditional “the system shall calculate tax” pattern familiar in
   > requirements documents, specification by example promotes expressing the requirement through examples such as “for a \$20 subscription with a tax rate of 10%, the system charges a total of ​\$22 from the user’s account.”  

   -- Quoted from "Test-Driven-TDD-and-Acceptance-TDD-for-Java-Developers" P64. 

2. From requirements to tests.

   What makes TDD different from what we used to do is that we decompose requirements into tests, not production codes. Namely we write tests first.  (  2.1.1 Decomposing requirements  ).

3. How can we write tests when there is not any code?
   
   We create classes which even doesn't exist and then generate them automatically by IDE.
   

### 6, Unit Test

##### 6.1 Basic Knowledge

1. What is a fixture in unit test?

   Such as a method with an annotation name @Before. A fixture is a starting point for all methods in the same class.
   
2. What is test doubles?

   [Test Double](./Materials\Test Double.docx)

   It means that we use some tools to mock the environment  or objects so that we don't rely on web server or database.

### 7, Reading Note

1. The meaning of the regular expression used in Listing 3.6.

   [meaning of reges](./note-images\explanation regex.txt)

2. How to start the first test?   Chapter 4.1

   It is a good idea to start an easy test which you think can be pass quickly. Once you got the first test nailed down, it could be much easier to think about what the next test would be.

   2.1 Implementation Strategies

   ​      There are three ways to make progress. 

   ​	   1) Faking It

   ​	   2) Triangulation. [a map to explain](./note-images/triangulation.png)

   ​       3)  Obvious Implementation

### 8, Prime Guidelines for Test-driving

#####  8.1. Introduction

- Do not skip refactoring.
- Get to green fast.
- Slow down after a mistake.

#####  8.2. Explanation

- **Refactoring**

1.  Learn how to use IDE's automated refactoring.  
2. Read "Refactoring..." by Martin Fowler.

- **Get to green fast**

   We don't go for the simplest design immediately. Instead, we should strive to get back to green fast. 

-  **Slow down after a mistake**

   It is common for developers practising TDD to start taking slightly bigger and bigger steps as time goes by. 

### 9, Fixture and Test  Doubles

Chapter 4.2.1

##### 9.1 Fixture:

1. Fixtures remove duplication
   Test code would be clean and readable.
2. Fixtures allow for focused tests
   We don't have to spend our precious time  wadding through hundreds lines of code so that we can easily find where fail tests happen.

##### 9.2 Test doubles 

Chapter 4.3

Test doubles stand in for dependencies.
It means that test doubles are used as replacement for the actual dependencies that code relies on during testing.

###### **9.2.1 Stubs, fakes, and mocks**

Chapter 4.3.2

Table 4.1 Categorization of different types of mock objects

| Type of mock | Description                                                  |
| ------------ | ------------------------------------------------------------ |
| Stubs        | Stubs are essentially the simplest possible implementation of a given interface you can think of. For example, stubs’ methods typically return hardcoded, meaningless values. |
| Fakes        | Fakes are a degree more sophisticated than stubs in that they can be considered an alternative implementation of the interface. In other words, a fake looks like a duck and walks like a duck even though it isn’t a real duck. In contrast, a stub only looks like a duck. |
| Mocks        | Mocks can be considered even more sophisticated in terms of their implementation, because they incorporate assertions for verifying expected collaboration with other objects during a test. Depending on the implementation of a mock, it can be set up either to return hardcoded values or to provide a fake implementation of the logic. Mocks are typically generated dynamically with frameworks and libraries, such as EasyMock, but they can also be implemented by hand. |



### 10, State and interaction-based testing

##### 10.1 State-based testing

What is state-based testing?

It focus on the state changes of an object or a system.

##### 10.2 Interaction-based testing

What is interaction-based testing?

Interaction-based tests verify that the object under test interacted with its collaborators as we expected.

**Quote:** To paraphrase J. B. Rainsberger, author of JUnit Recipes (Manning Publications, 2005),

>  “We lean on interaction-based testing to verify how an object
> talks to its collaborators; and we lean on state-based testing to verify how well the
> object listens.”  



### 11, Guidelines for testable designs

Chapter 4.4

- Choose composition over inheritance.
- Avoid static and singleton.    [demo](./note-images/avoid-static.java)
- Isolate dependencies.
- Inject dependencies.

Why do we need the above four approaches to guide us?

The reason it that we should write code which is testable. When it is testable we can adopt TDD easily so that our programme will be under the harness of test and will be free of error.

### 12, Unit-testing Patterns

Chapter 4.5

##### 12.1  Assertion Patterns

4.5.1 

Assertions are the essence of unit tests. A unit test without assertions is not worth writing. 

