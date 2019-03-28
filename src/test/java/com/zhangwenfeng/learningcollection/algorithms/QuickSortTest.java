package com.zhangwenfeng.learningcollection.algorithms;

import com.zhangwenfeng.learningcollection.algorithms.common.AlgorithmsUtils;
import com.zhangwenfeng.learningcollection.algorithms.sorts.QuickSort;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class QuickSortTest {
    Integer[] demoArray ;

    @Before
    public void dataReady() {
//        demoArray = AlgorithmsUtils.dataReadyReturnIntArray(10);
        demoArray = AlgorithmsUtils.dataReadySmallIntArray();
    }


    @Test
    public void testQuickSort() {
        QuickSort.sortByTwopoint(demoArray);
        AlgorithmsUtils.printArray(demoArray);
    }
}
