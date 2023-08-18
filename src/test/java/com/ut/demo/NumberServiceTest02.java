package com.ut.demo;

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

    @Test
    public void testAdd2(){
        //开启注解，一般写在@Before注解里
        MockitoAnnotations.openMocks(this);

        /*
         * 3,@Mock和@Spy注解的区别：
         * @Mock跟上面Mockito.mock(..)的作用一样，模拟一个类，调用模拟的方法，返回“返回值”数据类型的默认值
         * @Spy注解生成的对象，调用的是真正的方法。
         * */
        //@Mock注入的对象，返回默认值0
        int mockResult = numberServiceMock.add(20, 30);
        System.out.println("@Mock result==>" + mockResult);

        int spyResult = numberServiceSpy.add(20, 30);
        System.out.println("spyResult==>" + spyResult);


    }


}
