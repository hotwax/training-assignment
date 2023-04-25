package com.java.demo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Queue {
    // creating data member like arr to store the data , back data member to store
    // the back index of queue and front to store the front index
    int arr[];
    int back;
    int front;
    int capacity;

    // constructor to Initialize Stack of size size
    public Queue(int size) {
        arr = new int[size];
        back = -1;
        front = -1;
        capacity = size;
    }

    // creating a enqueue method to insert the data into the queue
    void enQueue(int x) {
        // checking if the queue is overflow
        if (back == capacity - 1) {
            System.out.println("Overflow");
            return;
        }
        // storing the data into queue
        back++;
        arr[back] = x;
        if (front == -1) {
            front++;
        }
    }

    // creating peak method to find the front element in a queue
    int peak() {
        // checking if the queue is empty
        if (front == -1 || back < front) {
            System.out.println("No element in a Queue");
            return -1;
        }
        // returning the front element in a queue
        return arr[front];

    }

    // creating dequeue method to delete the element from the queue
    void deQueue() {
        // checking if the queue is empty
        if (front == -1 || back < front) {
            System.out.println("No element in a Queue");
        }
        // pointing to the next element
        front++;
    }

    // creating empty method to check if queue is empty or not
    boolean empty() {
        return ((front == -1) || back < front);
    }

    // creating print method to print the data of queue
    void print() {
        System.out.print("Queue : ");
        for (int i = front; i <= back; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

    }

    public static void main(String[] args) {
        // creating q named object of queue class
        Queue q = new Queue(10);

        boolean flag = true;
        // running the while loop till flag is false
        while (flag) {
            System.out.println("Choose an option from the Dashboard given below:\n");
            System.out.println("1. Enqueue ");
            System.out.println("2. Dequeue ");
            System.out.println("3. Is Queue is empty ");
            System.out.println("4. Peak ");
            System.out.println("5. EXIT");
            System.out.println();
            Scanner sc = new Scanner(System.in);
            int ch = sc.nextInt();
            if (ch < 1 || ch > 5) {
                System.out.println("Kindly enter the correct option.");
            } else {
                // Taking the choice from the user and performing the corresponding operation
                try {
                    switch (ch) {
                        case 1:
                            System.out.println("Enter the value to be inserted");
                            int input_value = sc.nextInt();
                            q.enQueue(input_value);
                            q.print();
                            break;

                        case 2:
                            q.deQueue();
                            q.print();
                            break;

                        case 3:
                            System.out.println(q.empty());
                            break;

                        case 4:
                            int peak_value = q.peak();
                            System.out.println(peak_value);
                            break;

                        case 5:
                            flag = false;
                            break;

                    }

                    if (flag == false) {
                        System.out.println("Exiting program");
                    }
                } catch (InputMismatchException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        }

    }

}
