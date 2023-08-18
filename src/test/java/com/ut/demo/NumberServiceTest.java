package com.ut.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class NumberServiceTest {



    @Test
    public void testAdd(){
        //1,第一种获取待测试类的方式：Mockito.mock(..)
        //使用Mockito模拟一个类
        NumberService numberService = Mockito.mock(NumberService.class);
        //模拟好的类，调用其中的方法时，返回的是返回值数据类型的默认值，需要打桩才可
        int defaultResult =  numberService.add(10,20);
        System.out.println("defaultResult ==> " + defaultResult);
        //这里是进行打桩，即规定好预想的返回值
        Mockito.when(numberService.add(10,20)).thenReturn(30);
        int newResult = numberService.add(10, 20);
        System.out.println("new result==> " + newResult);

        //设置测试不通过
        Assertions.assertEquals(30,numberService.add(10,20));

    }



}
