package com.java.demo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Stack {
    // Initializing data menber of stack like arr to store data , top to point top
    // value of stack
    int arr[];
    int top;
    int capacity;

    // constructor to Initialize the size of stack
    public Stack(int size) {
        arr = new int[size];
        capacity = size;
        top = -1;

    }

    // creating the push method to push the element in the stack
    void push(int value) {
        // checking if the stack is full
        if (top == capacity - 1) {
            System.out.println("Stackoverflow");
        }
        // inserting value in a stack
        top++;
        arr[top] = value;

    }

    // creating a top method to find the top element present in a stack
    int top() {
        // checking if the element present in a stack or not
        if (top == -1) {
            System.out.println("No element in stack");
            return -1;

        }
        // returning the top element
        return arr[top];

    }

    // creating a empty method of boolean type which return true or false
    boolean empty() {
        // return true if the stack is not empty else return false
        return top == -1;
    }

    // creating a pop method to pop the element from the top
    void pop() {
        // checking if the stack is empty
        if (top == -1) {
            System.out.println("No element to pop");
            return;
        }
        top--;
    }

    // creating a print method to print the element
    void print() {
        System.out.print("Stack :");
        int temp_value_of_top = top;
        // while stack is not empty
        while (top != -1) {
            // printing the element
            System.out.print(arr[top] + " ");
            // pop the element
            pop();
        }
        // again assign it's correct value to top data memeber
        top = temp_value_of_top;
        System.out.println("");
    }

    public static void main(String[] args) {
        // creating object of Stack class
        Stack s = new Stack(1000);
        boolean flag = true;
        // run the loop while the flag is false
        while (flag) {
            System.out.println("Choose an option from the Dashboard given below:\n");
            System.out.println("1. Push ");
            System.out.println("2. Peek or top ");
            System.out.println("3. Is stack is empty ");
            System.out.println("4. Pop ");
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
                            s.push(input_value);
                            s.print();
                            break;

                        case 2:
                            int top_value = s.top();
                            System.out.println(top_value);
                            break;

                        case 3:
                            System.out.println(s.empty());
                            break;

                        case 4:
                            s.pop();
                            s.print();
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
