package com.zhangwenfeng.learningcollection.algorithms;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class Sorts {
    int[] demoArray = new int[5];
//    @Before
    public void dataReady() {
        for (int i = 0; i < 1000; i++) {
            Random random = new Random();
            demoArray[i] = random.nextInt(10000000);
        }
    }

    @Before
    public void dataReady2() {
        demoArray[0] = 5;
        demoArray[1] = 3;
        demoArray[2] = 1;
        demoArray[3] = 7;
        demoArray[4] = 6;
    }

    /**
     * 插入排序。最简单的排序算法。时间复杂度O(n^2)
     * △ 忘记一: j需要和i进行关联
     */
    @Test
    public void testInsertSort() {
        int arrayLength = demoArray.length;
        for (int i = 0; i < arrayLength ; i++) {
            // △ 忘记一: j需要和i进行关联
            for (int j = i + 1; j < arrayLength; j++) {
                if (demoArray[i] > demoArray[j]) {
                    // swap
                    int temp = demoArray[i];
                    demoArray[i] = demoArray[j];
                    demoArray[j] = temp;
                }
            }
        }
        printArray(demoArray);
    }


    /**
     * 希尔排序 [5,3,1,7,6]
     * 思想: 使数组中任意间隔为h的元素都是有序的。这样的数组被称为h有序数组。递增序列 1/2(3^k-1)
     * 难点一: 这个递增序列如果用代码表示出来:
     *      int N = a.length;
     *      int h = 1;
     *      while (h < N/3) h = 3*h + 1;
     *      while (h >= 1) {
     *          h = h / 3;
     *      }
     * 难点二: 遍历思路
     *   当h=4:
     *   0  4   8   12
     *   1  5   9   13
     *   2  6   10  14
     *   3  7   11  15
     *
     *   4 0
     *   1 5
     *   2 6
     *   3 7
     *   8 4 0
     *   9 5 1
     *
     * 难点三: 重置h
     *  h = h/3;而且很重要的是需要放到for循环外面
     *
     *  希尔排序,最坏复杂度为O(n^2)
     */
    @Test
    public void testShellSort() {
        int arrayLength = demoArray.length;
        int h = 1;
        while (h < arrayLength / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < arrayLength; i++) {
                for (int j = i; j >= h && lessThan(demoArray[j], demoArray[j - h]); j-=h) {
                    // SWAP
                    int temp = demoArray[j];
                    demoArray[j] = demoArray[j - h];
                    demoArray[j-h] = temp;
                }
            }
            h = h/3;
        }
        printArray(demoArray);
    }


    private boolean lessThan(Integer a, Integer b) {
        return a.compareTo(b) < 0;
    }



    private void printArray(int [] array) {
        if (array.length < 1)
            System.out.println("Array Empty!!!");
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i] + " ");
        }
    }


    /**
     * 堆排序。堆是具有以下性质的完全二叉树：每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆；或者每个结点的值都小于或等于其左右孩子结点的值，称为小顶堆。
     * 时间复杂度: O(nlongn)。不稳定排序。
     *      * 		 50
     *      * 	   /   \
     *      * 	  45   40
     *      * 	/  \   / \
     *      *  20  25 35 30
     *  同时,我们对堆中的结点按层进行编号,将这种逻辑结构映射到数组中
     *  0   1   2   3   4   5   6   7
     *      50  45  40  20  25  35  30
     *
     *  该数组从逻辑上讲就是一个堆结构，我们用简单的公式来描述一下堆的定义就是：
     *      大顶堆：arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2]
     *      小顶堆：arr[i] <= arr[2i+1] && arr[i] <= arr[2i+2]
     *
     *  思想: 将待排序序列构造成一个大顶堆此时,整个序列的最大值就是堆顶的根节点。将其与末尾元素进行交换,此时末尾元素就为最大值。然后将剩余n-1个元素重新构造一个堆,这样会得到n个元素的次小值。
     *  步骤一:  构造初始堆。将给定无序序列构造成一个大顶堆（一般升序采用大顶堆，降序采用小顶堆)。
     *      * 		 4
     *      * 	   /   \
     *      * 	  6     8
     *      * 	/  \
     *         5   9
     *      arr 0   1   2   3   4   5
     *              4   6   8   5   9     *      a.从最后一个非叶子节点开始(第一个非叶子结点 arr.length/2-1),从左至右,从下至上进行调整。
     *
     *
     *        b.找到第二个非叶子节点4,比较并交换 4和9交换
     *            4
     *  	   /   \
     *  	  9     8
     *  	/  \
     *    5    6
     *
     *           9
     *  	   /   \
     *  	  4     8
     *  	/  \
     *    5    6
     *
     *
     *  调整 4 5 6。交换4和6
     *           9
     *  	   /   \
     *  	  6     8
     *  	/  \
     *    5    4
     *
     *  步骤二: 将堆顶元素与末尾元素进行交换,使末尾元素最大。然后继续调整堆,再将堆顶元素末尾元素交换,得到第二大元素。如此反复进行交换、重建、交换。使得整个堆有序。
     *
     *  再简单总结下堆排序的基本思路：
     * 　　a.将无需序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;
     * 　　b.将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
     * 　　c.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
     *
     *
     */

}
