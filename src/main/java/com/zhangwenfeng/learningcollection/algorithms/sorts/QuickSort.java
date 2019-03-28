package com.zhangwenfeng.learningcollection.algorithms.sorts;

import com.zhangwenfeng.learningcollection.algorithms.common.AlgorithmsUtils;

public class QuickSort {
    public static <T extends Comparable<? super T>> void sortByTwopoint(T[] array) {
        // 1.
        sortByTwopoint(array, 0, array.length - 1);
    }

    private static <T extends Comparable<? super T>> void sortByTwopoint(T[] array, int left, int right) {
        // 1. 难点一: 会忘记这个
        if (left >= right) return;
        int index = partition(array, left, right);
        sortByTwopoint(array, left, index);
        sortByTwopoint(array, index + 1, right);
    }

    /**
     * 这个分组： 左边选择大于val， 右边选择小于val
     */
    private static <T extends Comparable<? super T>> int partition(T[] array, int left, int right) {
        // 1. 选取最左边为切分点
        T val = array[left];
        int i = left;
        int j = right + 1;
        for (;;) {
            // ++i： 排除第一项
            // val < 左边数据
            while (AlgorithmsUtils.less(array[++i], val)) {
                // 左指针元素小于val， 则只需要判断指针是否越界就行了
                if (i == right) break;
            }

            // val > 右边数据
            while (AlgorithmsUtils.less(val, array[--j])) {
                if (j == left) break;
            }

            if (i >= j) break;

            AlgorithmsUtils.exchange(i, j ,array);

        }

        AlgorithmsUtils.exchange(left, j, array);
        return j;
    }
}
