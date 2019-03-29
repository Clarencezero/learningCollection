package com.zhangwenfeng.learningcollection.algorithms;

import com.zhangwenfeng.learningcollection.algorithms.common.AlgorithmsUtils;
import com.zhangwenfeng.learningcollection.algorithms.sorts.CountingSort;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CountingSortTest {
    Integer[] demoArray ;

    @Before
    public void dataReady() {
//        demoArray = AlgorithmsUtils.dataReadyReturnIntArray(100);
//        demoArray = AlgorithmsUtils.dataReadySmallIntArray();
//        demoArray = new Integer[5];
//        demoArray[0] = 3;
//        demoArray[1] = 2;
//        demoArray[2] = 1;
//        demoArray[3] = 4;
//        demoArray[4] = 0;
    }

    @Test
    public void sort() {
        CountingSort.sort(demoArray);
        AlgorithmsUtils.printArray(demoArray);
    }
}