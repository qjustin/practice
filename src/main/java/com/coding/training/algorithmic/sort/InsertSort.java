package com.coding.training.algorithmic.sort;

public class InsertSort {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 5, 1, 23, 6, 78, 34, 23, 4, 5, 78, 34, 65, 32, 65, 76, 32, 76, 1, 9};

        sort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int selectedValue = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > selectedValue) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = selectedValue;
        }
    }

























    /*
    public static void sort(int arr[]) {
        if (arr.length < 2) return;

        for (int i = 1; i < arr.length; i++) {
            int selectedValue = arr[i];
            int j = i - 1;

            while (j >= 0 && selectedValue <= arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = selectedValue;
        }
    }
    */
}
