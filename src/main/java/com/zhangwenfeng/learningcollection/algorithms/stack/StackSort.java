package com.zhangwenfeng.learningcollection.algorithms.stack;

import com.zhangwenfeng.learningcollection.algorithms.common.AlgorithmsUtils;

import java.util.Random;

public class StackSort {
    public static void main(String[] args) {
        int[] demo = new int[5];
//        int[] demo = new int[1000];
//        for (int i = 0; i < 1000; i++) {
//            demo[i] = new Random().nextInt(100000);
//        }
        demo[1] = 444;
        demo[2] = 333;
        demo[3] = 3;
        demo[4] = 4;
        System.out.println(demo.length);
        System.out.println("排序前： ");
        AlgorithmsUtils.printIntArray(demo);

        sort(demo);

        System.out.println("排序后： ");
        AlgorithmsUtils.printIntArray(demo);

    }

    /**
     * 上浮: 相当于第k元素一级一级向上比较,如果大于,则交换。直到到达顶点为止
     * 难点:
     *  1. 每次循环后,需要改变k值为k/2
     * @param k
     */
    public static void swim(int k, int[] array) {
        while (k > 1) {
            if (AlgorithmsUtils.less(array[k/2], array[k])) {
                AlgorithmsUtils.exchange(k/2,k,array);
            }
            k = k/2;
        }

        // 简写
//        while (k > 1 && AlgorithmsUtils.less(array[k/2], array[k])) {
//            AlgorithmsUtils.exchange(k/2, k, array);
//            k = k/2;
//        }
    }

    /**
     *
     * 下沉： 由上至下的堆有序化的实现。这个是从顶节点开始的。
     *
     * 通过将它和它的两个子结点中的较大者交换来恢复堆。交换可能会在子结点处继续打破堆的有序状态，因此我们不断地用相同的方式将其修复，将结点向下移动直到它的子节点都比它更小或者达到了堆的底部。
     *
     * 思路： 1. 获取
     *
     * 难点一： 下沉操作是建立在堆有序的情况下，才会把顶点元素放到合适的位置（此时堆无序）
     * 难点二： j<N： 保证数组不越界
     * 难点三： 数组长度需要-1
     */
    public static void sink_M(int k, int[] array) {
        int N = array.length - 1;
        while (N >= 2 * k) {
            int j = 2*k;
            //
            if (j < N && AlgorithmsUtils.less(array[j], array[j+1])) j = j+1;
            if (AlgorithmsUtils.less(array[k], array[j]))
                AlgorithmsUtils.exchange(k, j, array);

            k = j;

        }
    }

    public static void sink(int k, int N, int[] array) {

        while (N >= 2*k) {
            int j = 2*k;
            if (j < N && AlgorithmsUtils.less(array[j], array[j+1])) j+=1;
            if (!AlgorithmsUtils.less(array[k], array[j])) break;
            AlgorithmsUtils.exchange(k, j, array);
            k = j;
        }
    }




    /**
     * 堆排序： 因为在数据中，所以是有序的，因此，下沉不是从叶子节点开始遍历，而是获取数组的长度，然后除以2，再--
     * 难点一： 数组长度把控。N对应的是最后一个元素的下标，而不是数组的大小，因为下标0是不存储数据。
     * 难点二： 堆排序一直使用下沉操作。
     *  第一步是： 从非叶子节点逐渐下沉，使堆有序
     *  第二步是： 顶点元素和 N-- 元素交换，并使顶点元素下沉，继续此前步骤
     *
     * @param array
     */
    public static void sort(int[] array) {
        int N = array.length - 1;
        // 从非叶子节点逐渐下沉
        for (int i = N/2; i >=1 ; i--) {
            sink(i, N, array);
        }
        // 交换顶点和尾结点
        while (N > 1) {
            AlgorithmsUtils.exchange(1, N--, array);
            sink(1, N, array);
        }
    }
}






































