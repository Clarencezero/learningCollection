package com.zhangwenfeng.learningcollection.algorithms;

import com.zhangwenfeng.learningcollection.algorithms.common.AlgorithmsUtils;
import com.zhangwenfeng.learningcollection.algorithms.sorts.MergeSort;
import org.junit.Before;
import org.junit.Test;

public class MergeSortTest {
    Integer[] demo;
    @Before
    public void dateReady() {
        demo = AlgorithmsUtils.dataReadyReturnIntArray(100);
//        demo = AlgorithmsUtils.dataReadySmallIntArray();
    }

    @Test
    public void test() {
        AlgorithmsUtils.printArray(demo);
        MergeSort.mergeSort(demo);
        System.out.println("排序后。。。。");
        AlgorithmsUtils.printArray(demo);
    }
}
