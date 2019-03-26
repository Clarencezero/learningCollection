package com.zhangwenfeng.learningcollection.encodeanddecode;

import org.junit.Test;

import java.util.Arrays;

/**
 * 编码问题,可能为GBK编码,所以解码的时候需要设置编码格式为GBK
 */
public class EncodeAndDecode {
    @Test
    public void testChinese() throws Exception{
        //eclipse在以GBK语言的情况下测试
        //编码：
        byte[] b = "这个数据是中文！可能会出现乱码".getBytes();
        System.out.println(Arrays.toString(b));

        byte[] b2 = "这个数据是中文！可能会出现乱码".getBytes("UTF-8");
        System.out.println(Arrays.toString(b2));

        //解码：
        System.out.println(new String(b));

        System.out.println(new String(b, "utf-8"));//编码和解码规则不一致 ==》出现乱码问题

        System.out.println(new String(b2,"GBK"));//编码和解码规则不一致 ==》出现乱码问题

        System.out.println(new String(b2,"utf-8"));//编码和解码规则一致 ==》不会出现乱码问题
    }
}
