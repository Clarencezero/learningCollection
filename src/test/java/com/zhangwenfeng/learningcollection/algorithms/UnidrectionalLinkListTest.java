package com.zhangwenfeng.learningcollection.algorithms;

import com.zhangwenfeng.learningcollection.algorithms.tree.UnidrectionalLinkList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UnidrectionalLinkListTest {
    @Test
    public void ArrayListTest() {
        List list = new ArrayList<>();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void testNodePut() {
        UnidrectionalLinkList<String> list = new UnidrectionalLinkList<>();
        for (int i = 0; i < 10; i++) {
            list.put("Miss Zhang, I miss you " + i + " times ~");
        }
        printElement(list);
//        list.reverseLinkList();
//        printElement(list);

        list.reverseLinkListByRecur();
        printElement(list);

    }




    private void printElement(UnidrectionalLinkList list) {
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
