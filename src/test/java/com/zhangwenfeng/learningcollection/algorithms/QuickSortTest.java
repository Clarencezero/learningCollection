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
//        demoArray = AlgorithmsUtils.dataReadySmallIntArray();
        demoArray = new Integer[5];
        demoArray[0] = 3;
        demoArray[1] = 2;
        demoArray[2] = 1;
        demoArray[3] = 4;
        demoArray[4] = 0;
    }


    @Test
    public void testQuickSort() {
        QuickSort.sortByTwopoint(demoArray);
        AlgorithmsUtils.printArray(demoArray);
    }

    @Test
    public void testQuickSortBythreePoints() {
        QuickSort.sortByThreepoint(demoArray);
        AlgorithmsUtils.printArray(demoArray);
    }
}
