package com.ut.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class NumberServiceTest02 {

    //2,第二种获取待测试类的方式，使用Mock注解
    //注意：使用注解方式的时候，要调用MockitoAnnotations.openMocks(this)来启动注解形式的模拟，否则报错
    @Mock
    NumberService numberServiceMock;
    @Spy
    NumberService numberServiceSpy;

    //@BeforeEach注解，跟AOP中的@Before注解一样，在所有方法执行之前执行
    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);
        System.out.println("beforeEach execute!");
    }


    @Test
    public void testAdd2(){
        //开启注解，一般写在@BeforeEach注解里
        //MockitoAnnotations.openMocks(this);

        /*
         * 3,@Mock和@Spy注解的区别：
         * @Mock跟上面Mockito.mock(..)的作用一样，模拟一个类，调用模拟的方法，返回“返回值”数据类型的默认值
         * @Spy注解生成的对象，调用的是真正的方法。
         * */
        //@Mock注入的对象，返回默认值0
        int mockResult = numberServiceMock.add(20, 30);
        System.out.println("@Mock result==>" + mockResult);

        int spyResult = numberServiceSpy.add(20, 30);
        System.out.println("@Spy Result==>" + spyResult);


    }

    //在所有方法之后执行
    @AfterEach
    public void afterEach(){
        System.out.println("afterEach execute");

    }


}
