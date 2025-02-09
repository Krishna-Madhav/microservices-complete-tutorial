package com.lcwd.user.service.controller;

public class TestClass {

    // ({})
    // ({)

    public static void main(String[] args) {

        int arr[] = {1, 2, 3, 68, 100};
        int arr2[] = {100, 7, 8, 9};

        // Merge these array
        int newArrSize = arr.length + arr2.length;

        int outputArr[] = new int[newArrSize];
        int outputArrIndex = 0;

        for (int i = 0; i < arr.length; i++) {
            outputArr[outputArrIndex] = arr[i];
            outputArrIndex++;
        }

        for (int i = 0; i < arr2.length; i++) {
            outputArr[outputArrIndex] = arr2[i];
            outputArrIndex++;
        }

        System.out.println("Final Output:");
        for(int value : outputArr){
            System.out.print(value + " ");
        }
    }
}
