package com.zhangwenfeng.learningcollection.algorithms;

import com.zhangwenfeng.learningcollection.algorithms.tree.AvlTree;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AvlTreeTest {
    AvlTree<Integer> valTree = null;

    /**
     * 		4
     * 	   / \
     * 	  1   7
     * 	/  \   \
     * 0    3   8
     */
    @Before
    public void testInsert() {
        valTree = new AvlTree<>();
        valTree.insert(3);
        valTree.insert(1);
        valTree.insert(0);
        valTree.insert(4);
        valTree.insert(7);
        valTree.insert(8);
    }

    /**
     * 前序遍历 4 1 0 3 7 8
     */
    @Test
    public void testPreOrder() {
        valTree.printTreeByPreOrder();
    }

    /**
     * 中序遍历 0 3 1 4 7 8
     */
    @Test
    public void testMidOrder() {
        valTree.printTreeByMidOrder();
    }

    /**
     * 后序遍历 0 3 1 8 7 4
     */
    @Test
    public void testBackOrder() {
        valTree.printTreeByBackOrder();
    }

    @Test
    public void testContain() {
        Assert.assertTrue(valTree.contains(3));
    }


    @Test
    public void testRemove() {
        valTree.remove(8);
        Assert.assertFalse(valTree.contains(8));

    }


}
