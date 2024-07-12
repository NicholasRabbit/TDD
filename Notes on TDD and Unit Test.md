### 1，The benefits of writing unit tests.

1. 写测试会加快开发；
2. 写测试会减少bug；
3. 写测试会提升写程序的幸福感。!!

What is the difficult part of learning TDD?

It is to understand the requirements and convert it into modules.

> TDD 的学习难点首先在于理解需求，理解需求，并将需求分解为功能点。

### 2，The definition of the unit test.

**What is a unit test?**

For example, a unit test for a method in Java project serves for only one goal which is whether the method itself could run as we expected when we are providing some mock or fake data.

**What is a good unit test?**

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

#### 2.1. How to ensure a query for the database is correct ?

- We would rely on integration tests to verify that the sql is correct. Because we can't do that with TDD or unit tests. (P219 of the book named "TDD")
- We write in

### 3，How to write a good unit test.

1，不是为了写测试而写，要清楚代码哪些地方需要重点测，才去针对这些地方写，**一般单元测试的覆盖率是40-60%**；

2，写好的单元测试在不同的环境中都要运行；

单元测试的特点：

- 运行时间必须要很短，毫秒级别；
- 可以独立运行；
- 可重复，运行期不做更改，每次在不同环境运行得到的结果必须一致；
- 自检查，测试应该在没有任何人工交互的情况下自动检测是否通过。
- Test code should also be refactored if they are lack of readability, maintainability.   [xUnit](http://xunitpatterns.com/)

3,  对业务理解深度，逐个分解需求，才能写出好的单元测试。

### 4, in memory database

While writing unit test, you can use in-memory database(not Redis). The nice thing about these tools is that they allow you to use regular SQL to query them.

<a href="https://martinfowler.com/bliki/InMemoryTestDatabase.html">In-memory Database (Marting Fowler)</a>

<a href="http://hsqldb.org/">HSQLDB(Java In-memory Database)</a>

### 5, TDD

##### What is TDD? !!!!

Acutally, in TDD unit test should be named "Unit Level Functional Test", it is not necessary to use unit test in every test.  Contrary to popular belief,  the so called "unit test" is not frequently used in TDD; furthermore, Kent Beck  said that he had never used "unit test" in TDD. After reading the following artices, I realised that I had also misunderstood it.  Instead, it is necessary to write tests with different levels of granularity in TDD.

Related articles: [Unit Test by Martin Fowler](https://martinfowler.com/bliki/UnitTest.html),  [TDD in Practice](https://time.geekbang.org/column/article/496699)



**Tips**

In Test driven development, you should write test first, and then write codes to pass the test. The last and darn important step is refactoring.

**What is the most diffult thing on learning TDD?**

It is to understand the requirements which is the most difficult when you learn it.

##### 5.1 TDD Cycle:  

test--code--refactor (or red-green-green)

##### 5.2 From requirements to tests

1. Specification by example

   > In other words, instead of the traditional “the system shall calculate tax” pattern familiar in
   > requirements documents, specification by example promotes expressing the requirement through examples such as “for a \$20 subscription with a tax rate of 10%, the system charges a total of ​\$22 from the user’s account.”  

   -- Quoted from "Test-Driven-TDD-and-Acceptance-TDD-for-Java-Developers" P64. 

2. Decomposing requirements.

   When getting a requirement, which is too complex to write a single test for it, we decompose requirements into tests, not production codes. Namely we write tests first.  (  2.1.1 Decomposing requirements ).

   The reason of adopting TDD is to tell our customers that we completely understand the requirements by showing them these trivial tests for every functions.

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

   [meaning of regex](./note-images\explanation regex.txt)

2. How to start the first test?   Chapter 4.1

   It is a good idea to start an easy test which you think can be pass quickly. Once you got the first test nailed down, it could be much easier to think about what the next test would be.

   2.1 Implementation Strategies

   ​      There are three ways to make progress. 

   ​	   1) Faking It

   ​	   2) Triangulation. [a map to explain](./note-images/triangulation.png)

   ​       3)  Obvious Implementation 
   
3. **Note:** The book should not be read only one time and should definitely be read two or three times when you actually adopt TDD in a project.

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

### 9, Test  Doubles

##### 9.1 Test doubles 

Chapter 4.3

Test doubles stand in for dependencies.
It means that test doubles are used as replacement for the actual dependencies that code relies on during testing.

###### **9.1.1 Stubs, fakes, and mocks**

Chapter 4.3.2

Table 4.1 Categorization of different types of mock objects

| Type of mock | Description                                                  |
| ------------ | ------------------------------------------------------------ |
| Stubs        | Stubs are essentially the simplest possible implementation of a given interface you can think of. For example, stubs’ methods typically return hardcoded, meaningless values. |
| Fakes        | Fakes are a degree more sophisticated than stubs in that they can be considered an alternative implementation of the interface. In other words, a fake looks like a duck and walks like a duck even though it isn’t a real duck. In contrast, a stub only looks like a duck. |
| Mocks        | Mocks can be considered even more sophisticated in terms of their implementation, because they incorporate assertions for verifying expected collaboration with other objects during a test. Depending on the implementation of a mock, it can be set up either to return hardcoded values or to provide a fake implementation of the logic. Mocks are typically generated dynamically with frameworks and libraries, such as EasyMock, but they can also be implemented by hand. |



### 10, State and interaction-based testing

##### 10.1 State-based testing

1) What is state-based testing?

It focus on the state changes of an object or a system. 

2) How to do the state-based testing when there is substatntial amount of data in the database?

The approach is incrementally state-basted testing which is we insertone row to the database and then to verify that if there is only one row insert into it.

Before insert: 100 rows

Afater insert: 101 row.

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

Code : `assertion_patterns.TestAssertionPatterns/TestExpectedInteractions`

Assertions are the essence of unit tests. A unit test without assertions is not worth writing. 	

1. Resulting state assertion
2. Guard assertion
3. Delta assertion
4. Custom assertion
5. Interaction assertion 

##### 12.2 Fixture Patterns

1. Chapter 4.2.1
   
   **Code:** `fixture_patterns.*`
   
   Why shall we use fixture patterns?
   
   1. Fixtures remove duplication
      Test code would be clean and readable.
   2. Fixtures allow for focused tests
      We don't have to spend our precious time  wadding through hundreds lines of code so that we can easily find where fail tests happen.
   
   **Categories:**
   
   1. Parameterized Creation Method; 
   
   2. Object Mother; [Definition]("./note-images/Object Mother.html")  (It is a html file and can be opened directly in its directory.)
   
   3. Automated Teardown.
   
      **What is "Automated Teardown"?**
   
      The Automated Teardown pattern tackle problems such as the uncleaned objects from a database or generated file from a file system. All of these logic to teardown can be encapsulated in a class which is to invoke by  a fixture (such as @After) in tests classes so that we won't forget to clean up anything.
   
      **Why should we adopt this kind of fixture pattern?**
   
      Because we might easily forget to clean those objects and generated file for tests. 
   
      ​	

##### 12.3 Test Patterns

​    Code: `com.tdd.test_patterns.*`

1. **Parameterized Test**

   What is parameterized test? What is it used for?

   When we find ourselves writing almost identical tests, where only a few input values are different but the logic essentially the same. In this situation we should adopt parameterized test. 

2. **Self-Shunt**

   2.1 What is the self-shunt test? 

   The self-shunt is that the test class instance itself act as a test double in its test. 
   
   2.2 Where can I use it ? 
   
   We had better use it when the logic of code is not complex so that we can implement such a simple test-double near the tests which use it.
   
3. **Intimate Inner Class**

   Why do we use "Intimate Inner Class"? What is "Intimate Inner Class"? 

   There are some occasions where we would like to share some objects between the test class and a test-double. The solution is to use "Intimate Inner Class".

4. **Privilege Access**

   What is "Privilege Access"?

   When the legacy code is not testable it maybe a reasonable workaround to invade the legacy code's privacy and directly tweak it internals through Reflection API. Tools such as `PrivateAccessor` in JUnit, `Inject` in Laughing Panda are useful for us to approach this problem.
   
5. **Extra Constructor**

   What is "Extra Constructor"?

   When we test a product code which depends on a dozen of others classes we should adopt this kind of test pattern named "Extra Constructor". This proper solution would isolate these dependencies by using an extra constructor.  See `ExtractConstructorTest`.

6. **Test-Specific Subclass**

   There isn't any code for me to understand this kind of test pattern. With knowing more about TDD in the coming days, I will add details about it.

### 13, Test-driving Web Components

Chapter 5.1 and 5.2

Faking the request and response.

**Caution:** Only the dependencies and return value of these dependencies are mocked but the controller itself is real. 

### 14, Test-driving Data Access

Chapter 6.1

**14.1)  How to test database?**

(1) We are not able to hit the real database when we test persitent layer, so the effective way to test queries of the database is to use a database which is quite close to the one we use in production. In other words, we write integration tests to run against an in-memory database. 

> HSQLDB is an excellent in-memory database which is used to simulated MySQL.

(2) The database schema of in-memory database should be versioned with the rest of the system.

Chapter  6.4.2  Creating the database schema  

There is not any database in the HSQLDB when we start our first test, so it is necessary to create schemas as same as we use in the production environment with MySQL.

(3) Don't call "commit()" in test methods.

**Caution:**   See 6.4.4   Staying clean with transactional fixtures  (TDD)

(4)  While XuHao-the instructor of "TDD in Parctice" sugguested that it is better to hit the real database beacuse we can retrieve real feedback and it is worth spending more time. 

**14.2)  The database should be in its original state before every test and should be as it was after every test.   Why?**

There is no need to commit because we still can get the 'person' by call "findByLastName(..)"
The real reason we don't call "commit()" is that we should keep the database as it was so that the others tests won't be disturbed or affected. A simple example is that we save "new Person("Lily", ...)" in this test, but we still save the same person in another test, if we didn't rollback in this test, another test might be failed because the state of database has been changed.

**14.3) Populating Data for Integration Tests.**

Chapter 6.5 

A) Why should we populate data for integration tests?

Because we don't faking data like what we do in unit tests, we need some data from a real database or a test database instead. Although running SQL scripts is a feasible solution,  they are not user-friendly and not portable. Fortunately, there are couple of tools which help us. [DbUnit](https://www.dbunit.org/) is one of them.

 

