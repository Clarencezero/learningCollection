package com.zhangwenfeng.learningcollection.algorithms;

import com.zhangwenfeng.learningcollection.algorithms.tree.BinarySearchTree;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest  {
    BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
    @Before
    public void before() {
        binarySearchTree.insert(13);
        binarySearchTree.insert(7);
        binarySearchTree.insert(20);
        binarySearchTree.insert(16);
        binarySearchTree.insert(14);
        binarySearchTree.insert(19);
        binarySearchTree.insert(18);
    }

    @Test
    public void testIntegerCompareTo() {
        Integer a = new Integer(10);
        Integer b = new Integer(10);
        System.out.println(a.compareTo(b));
        System.out.println(a.equals(b));

    }



    @Test
    public void testRemove() {
        System.out.println(binarySearchTree.contains(16));
        binarySearchTree.remove(20);
        System.out.println(binarySearchTree.contains(16));
    }


}
