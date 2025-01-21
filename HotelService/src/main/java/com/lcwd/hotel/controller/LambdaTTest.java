package com.lcwd.hotel.controller;

public class LambdaTTest{

    public static void main(String[] args) {

        int arr[] = {100,100,4,5,22,100,323,10,15};

        int largest = arr[0];
        int secondLargest = 0;

        for (int i = 1; i < arr.length; i++) {

            if(arr[i] > largest){
                secondLargest = largest;
                largest = arr[i];
        }

        }

        System.out.println("Second largest element is :" + secondLargest);

        InterfaceA ob = () -> {
           // System.out.println("Inside the Show method ");
        };

        ob.show();
    }

}
