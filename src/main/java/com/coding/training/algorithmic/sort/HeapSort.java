package com.coding.training.algorithmic.sort;

/**
 * https://blog.csdn.net/u010452388/article/details/81283998
 * <p>
 * 还有一个基本概念：查找数组中某个数的父结点和左右孩子结点，比如已知索引为i的数，那么
 * <p>
 * 1.父结点索引：(i-1)/2（这里计算机中的除以2，省略掉小数）
 * <p>
 * 2.左孩子索引：2*i+1
 * <p>
 * 3.右孩子索引：2*i+2
 * <p>
 * 所以上面两个数组可以脑补成堆结构，因为他们满足堆的定义性质：
 * <p>
 * 大根堆：arr(i)>arr(2*i+1) && arr(i)>arr(2*i+2)
 * <p>
 * 小根堆：arr(i)<arr(2*i+1) && arr(i)<arr(2*i+2)
 * <p>
 * 主要思路：
 * 第一次保证0~0位置大根堆结构（废话），
 * 第二次保证0~1位置大根堆结构，
 * 第三次保证0~2位置大根堆结构...
 * 直到保证0~n-1位置大根堆结构
 * （每次新插入的数据都与其父结点进行比较，如果插入的数比父结点大，
 * 则与父结点交换，否则一直向上交换，直到小于等于父结点，或者来到了顶端）
 * <p>
 * <p>
 * <p>
 * <p>
 * 注意 这里一定要是 '<' 即： arr[leftIndex] < arr[rightIndex]
 * if (arr[leftIndex] < arr[rightIndex] && rightIndex < size) {
 * largeIndex = rightIndex;
 * } else {
 * largeIndex = leftIndex;
 * }
 */

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 5, 1, 23, 6, 78, 34, 23, 4, 5, 78, 34, 65, 32, 65, 76, 32, 76, 1, 9};

        sort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void sort(int[] arr) {
        int size = arr.length;
        if (size < 2) return;

        initHeap(arr);
        while(size > 1) {
            swap(arr, 0, size - 1);
            size--;
            adjustHeap(arr, 0, size);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void initHeap(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int currentIndex = i;
            int fatherIndex = (currentIndex - 1) / 2;

            while (arr[currentIndex] > arr[fatherIndex]) {
                swap(arr, currentIndex, fatherIndex);
                currentIndex = fatherIndex;
                fatherIndex = (currentIndex - 1) / 2;
            }
        }
    }

    public static void adjustHeap(int[] arr, int index, int size) {
        int left = (index * 2) + 1;
        int right = (index * 2) + 2;

        while (left < size) {
            int largeIndex = 0;
            if (arr[left] <= arr[right] && right < size) {
                largeIndex = right;
            } else {
                largeIndex = left;
            }

            if (arr[index] > arr[largeIndex]) {
                largeIndex = index;
            }

            if (index == largeIndex) {
                break;
            }

            swap(arr, largeIndex, index);
            index = largeIndex;
            left = (index * 2) + 1;
            right = (index * 2) + 2;
        }
    }



























































/*

    //堆排序
    public static void heapSort(int[] arr) {
        //构造大根堆
        heapInsert(arr);
        int size = arr.length;
        while (size > 1) {
            //固定最大值
            swap(arr, 0, size - 1);
            size--;
            //构造大根堆
            heapify(arr, 0, size);
        }
    }

    //构造大根堆（通过新插入的数上升）
    public static void heapInsert(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //当前插入的索引
            int currentIndex = i;
            //父结点索引
            int fatherIndex = (currentIndex - 1) / 2;
            //如果当前插入的值大于其父结点的值,则交换值，并且将索引指向父结点
            //然后继续和上面的父结点值比较，直到不大于父结点，则退出循环
            while (arr[currentIndex] > arr[fatherIndex]) {
                //交换当前结点与父结点的值
                swap(arr, currentIndex, fatherIndex);
                //将当前索引指向父索引
                currentIndex = fatherIndex;
                //重新计算当前索引的父索引
                fatherIndex = (currentIndex - 1) / 2;
            }
        }
    }

    //将剩余的数构造成大根堆（通过顶端的数下降）
    public static void heapify(int[] arr, int index, int size) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        while (left < size) {
            int largestIndex;
            //判断孩子中较大的值的索引（要确保右孩子在size范围之内）
            if (arr[left] < arr[right] && right < size) {
                largestIndex = right;
            } else {
                largestIndex = left;
            }
            //比较父结点的值与孩子中较大的值，并确定最大值的索引
            if (arr[index] > arr[largestIndex]) {
                largestIndex = index;
            }
            //如果父结点索引是最大值的索引，那已经是大根堆了，则退出循环
            if (index == largestIndex) {
                break;
            }
            //父结点不是最大值，与孩子中较大的值交换
            swap(arr, largestIndex, index);
            //将索引指向孩子中较大的值的索引
            index = largestIndex;
            //重新计算交换之后的孩子的索引
            left = 2 * index + 1;
            right = 2 * index + 2;
        }
    }

    //交换数组中两个元素的值
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
*/
}
