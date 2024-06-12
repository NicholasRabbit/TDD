package com.ut.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class NumberServiceTest {

    /**
     * 注意：
     * mock一个实体类时需要模拟getter的返回值，当调用这个getter的时候才会获得这个模拟的值，实际实体类中对应的属性值还是null.
     * 例： when(compInfo.getSortOrder()).thenReturn(1);
     * debug查看时，sortOrder的属性值还是null，但是如果调用getSortOrder()就会获取模拟的值。
     * */

    @Test
    public void testAdd(){
        //1,第一种获取待测试类的方式：Mockito.mock(..)
        //使用Mockito模拟一个类
        NumberService numberService = Mockito.mock(NumberService.class);
        //注意：模拟好的类，调用其中的方法时，返回的是返回值数据类型的默认值，需要打桩才可
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
