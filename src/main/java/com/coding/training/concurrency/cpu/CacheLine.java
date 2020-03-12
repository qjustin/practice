package com.coding.training.concurrency.cpu;

public class CacheLine {
    public static void main(String[] args) {
        // Demo1();


        Demo2();
    }

    public static void Demo1() {
        int[] arr = new int[64 * 1024 * 1024];

        long beginTime = System.currentTimeMillis();

        // Loop 1
        for (int i = 0; i < arr.length; i++) {
            arr[i] *= 3;
        }

        long endTime = System.currentTimeMillis();

        System.out.println(endTime - beginTime);


        beginTime = System.currentTimeMillis();
        // Loop 2
        for (int i = 0; i < arr.length; i += 16) {
            arr[i] *= 3;
        }
        endTime = System.currentTimeMillis();

        System.out.println(endTime - beginTime);
    }


    public static void Demo2() {
        int steps = 256 * 1024 * 1024;
        int[] arr = new int[2];

        long beginTime = System.currentTimeMillis();

        // Loop 1
        for (int i = 0; i < steps; i += 16) {
            arr[0]++;
            arr[0]++;
        }

        long endTime = System.currentTimeMillis();

        System.out.println(endTime - beginTime);


        beginTime = System.currentTimeMillis();
        // Loop 2
        for (int i = 0; i < steps; i += 16) {
            arr[0]++;
            arr[1]++;
        }
        endTime = System.currentTimeMillis();

        System.out.println(endTime - beginTime);
    }
}
