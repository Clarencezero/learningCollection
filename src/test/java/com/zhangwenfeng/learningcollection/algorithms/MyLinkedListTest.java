package com.zhangwenfeng.learningcollection.algorithms;

import com.zhangwenfeng.learningcollection.algorithms.tree.MyLinkedList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class MyLinkedListTest {
    MyLinkedList<String> linkedList;
    @Before
    public void testBefore() {
        linkedList = new MyLinkedList<>();
        linkedList.add("张汶沣");
        linkedList.add("姜家俊");
    }

    @Test
    public void testListAdd() {
        linkedList.add("张汶沣");
        Assert.assertFalse(linkedList.isEmpty());
    }

    @Test
    public void testListRemove() {
        linkedList.add("张汶沣");
        Assert.assertEquals("张汶沣", linkedList.remove(0));
    }

    @Test
    public void testIterator() {
        linkedList.add("张汶沣");
        linkedList.add("姜家俊");
        Iterator iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void testContain() {
        Assert.assertTrue(linkedList.contains("张汶沣"));
        Assert.assertTrue(linkedList.contains("姜家俊"));
        Assert.assertFalse(linkedList.contains("姜家俊2"));
        linkedList.remove(0);
        Assert.assertFalse(linkedList.contains("张汶沣"));
    }

    @Test
    public void test() {
        System.out.println(1/5);
        System.out.println(1%5);
    }
}
