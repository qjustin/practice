package com.coding.training;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 5, 1, 23, 6, 78, 34, 23, 4, 5, 78, 34, 65, 32, 65, 76, 32, 76, 1, 9};
        heapSort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void heapSort(int[] arr) {
        int size = arr.length;
        initHeap(arr);

        while(size > 1) {
            swap(arr, 0,  size - 1);
            size--;
            adjustHeap(arr, 0, size);
        }
    }
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void initHeap(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int currentIndex = i;
            int fatherIndex = currentIndex - 1 / 2;

            while (arr[currentIndex] > arr[fatherIndex]) {
                swap(arr, currentIndex, fatherIndex);

                currentIndex = fatherIndex;
                fatherIndex = currentIndex - 1 / 2;
            }
        }
    }

    private static void adjustHeap(int[] arr, int index, int size) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;

        while(left < size) {
            int largeIdx = 0;
            if (arr[left] < arr[right]) {
                largeIdx = right;
            }
            if (arr[largeIdx] < arr[index]) {
                largeIdx = index;
            }

            if (largeIdx == index) break;

            swap(arr, largeIdx, index);

            index = largeIdx;

            left = index * 2 + 1;
            right = index * 2 + 2;

        }
    }
}
