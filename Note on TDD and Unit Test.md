#### 1，写测试的好处

1. 写测试会加快开发；
2. 写测试会减少bug；
3. 写测试会提升写程序的幸福感。

#### 2，The definition of a good test

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

#### 3，如何写好单元测试

1，不是为了写测试而写，要清除代码哪些地方需要重点测，才去针对这些地方写，**一般单元测试的覆盖率是40-60%**；

2，写好的单元测试在不同的环境中都要运行；

单元测试的特点：

- 运行时间必须要很短，毫秒级别；
- 可以独立运行；
- 可重复，运行期不做更改，每次在不同环境运行得到的结果必须一致；
- 自检查，测试应该在没有任何人工交互的情况下自动检测是否通过。

#### 4, in memory database

While writing unit test, you can use in-memory database(not Redis). The nice thing about these tools is that they allow you to use regular SQL to query them.

<a href="https://martinfowler.com/bliki/InMemoryTestDatabase.html">In-memory Database (Marting Fowler)</a>

<a href="http://hsqldb.org/">HSQLDB(Java In-memory Database)</a>

#### 5, TDD

In Test driven development, you should write test first, and then write codes to pass the test. The last and darn important step is refactoring.

1. Specification by example

   > In other words, instead of the traditional “the system shall calculate tax” pattern familiar in
   > requirements documents, specification by example promotes expressing the requirement through examples such as “for a \$20 subscription with a tax rate of 10%, the system charges a total of ​\$22 from the user’s account.”  

   -- Quoted from "Test-Driven-Tdd-and-Acceptance-TDD-for-Java-Developers" P64. 

2. From requirements to tests.

   What makes TDD different from what we used to do is that we decompose requirements into tests, not production codes. Namely we write tests first.  (  2.1.1 Decomposing requirements  ).

3. How can we write tests when there is not any code?
   
4. 


#### 6, Unit Test

1. What is a fixture in unit test?

   Such as a method with an annotation name @Before. A fixture is a starting point for all methods in the same class.

#### 7, Reading Note

1. **The meaning of the regular expression used in Listing 3.6.** 

   1. `Pattern.compile`: This is a method used in Java to compile a regular expression into a `Pattern` object. This object can then be used to match patterns in strings.

   2. `"\$\{[^}]*\}"`:

      - `\`: Backslash is an escape character in Java and in regular expressions. It's used to indicate that the following character has a special meaning. So, `\$` matches a literal dollar sign `$`.

      - `\$`: This part of the expression matches a literal dollar sign.

      - `\{`: This matches a literal opening curly brace `{`.

      - ```
        [^}]*
        ```

        : This part is a bit more complex:

        - `[^}]`: The `^` inside the square brackets (`[]`) means negation, and `}` is the character being negated. So, `[^}]` matches any character except `}`.
        - `*`: This quantifier means "zero or more times", so `[^}]*` matches zero or more occurrences of any character except `}`.

      - `\}`: This matches a literal closing curly brace `}`.

   So, altogether, the regular expression `"\$\{[^}]*\}"` matches a pattern that starts with a dollar sign `$`, followed by an opening curly brace `{`, then zero or more characters except `}`, and finally a closing curly brace `}`. This pattern is commonly used to match placeholders in strings, typically found in templating languages or configuration files. For example, `${variable}` is a placeholder that might be replaced with the value of the variable during runtime.

