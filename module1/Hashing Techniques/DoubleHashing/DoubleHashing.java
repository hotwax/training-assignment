package com.java.demo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DoubleHashing {
    int size;
    int numberofelement = 0;
    int collision;
    int arr[];

    // Constructor for initializing hash table with a given size
    DoubleHashing(int size) {
        this.size = size;
        arr = new int[size];
        this.numberofelement = 0;

        // Initialize all elements in the hash table as -1
        for (int i = 0; i < size; i++) {
            arr[i] = -1;
        }
        this.collision = 0;
    }

    // Function to find the largest prime number smaller than size
    int smallprime() {
        for (int i = size - 1; i >= 1; i--) {
            if (isprime(i)) {
                return i;
            }
        }
        return 1;
    }

    // Function to check if a number is prime or not
    boolean isprime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Function to insert a value into the hash table
    void insert(int value) {
        if (size == numberofelement) {
            // If the hash table is full, print an error message
            System.out.println("Hash map is full");
        }
        int hash = hashcode(value);
        int hashsecond = hashcode2(value);

        // Handle collisions using double hashing
        while (arr[hash] != -1) {
            if (arr[hash] == value) {
                // If the value is already present in the hash table, return
                return;
            }
            collision++;
            hash = (hash + hashsecond) % size;
        }
        // Insert the value into the hash table
        arr[hash] = value;
        numberofelement++;
    }

    // Function to delete a value from the hash table
    void delete(int value) {
        int hash = hashcode(value);
        int hashsecond = hashcode2(value);
        while (arr[hash] != -1 && (!(arr[hash] == value))) {
            // Search for the value to be deleted using double hashing
            hash = hash + hashsecond;
            hash = hash % size;
        }
        // Delete the value from the hash table
        arr[hash] = -1;
        numberofelement--;
    }

    // Function to get the key of a value in the hash table
    int getValueofKey(int value) {
        int hash = hashcode(value);
        int hashsecond = hashcode2(value);
        boolean issafe = true;
        int counter = 0;
        while (arr[hash] != -1 && (!(arr[hash] == value) && issafe)) {
            // Search for the key of the given value using double hashing
            counter++;
            hash = hash + hashsecond;
            hash = hash % size;
            if (counter >= 1000) {
                // If the maximum number of iterations is reached, break the loop
                issafe = false;
            }
        }
        if (issafe == false) {
            // If the value is not found within the maximum number of iterations, return -1
            return -1;
        } else {
            // Return the key of the given value
            return hash;
        }

    }

    // Function to get the value of a key in the hash table
    int getKeybyValue(int key) {
        return arr[key];
    }

    // Function to display the contents of the
    void display() {
        System.out.println("Hashmap : ");
        for (int i = 0; i < size; i++) {
            if (arr[i] != -1) {
                System.out.println(i + "->" + arr[i]);
            }
        }
    }

    // Computes the second hash code for the given value using double hashing
    // technique.

    int hashcode2(int value) {
        int primenum = smallprime();
        return primenum - (value % primenum);
    }

    // Computes the first hash code for the given value using modulo operator.
    int hashcode(int value) {
        return Math.abs(value) % size;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the map");
        int sizeofmap = sc.nextInt();
        DoubleHashing dh = new DoubleHashing(sizeofmap);
        long t1 = System.currentTimeMillis(); // Variable To calculate the time taken at the start.
        boolean flag = true;
        // running the while loop till flag is false
        while (flag) {
            System.out.println("Choose an option from the Dashboard given below:\n");
            System.out.println("1. Insert a value in Hashmap");
            System.out.println("2. Delete a key value in Hashmap");
            System.out.println("3. Get value of the key");
            System.out.println("4. Display the Hashmap ");
            System.out.println("5. Search the value in a Hashmap");
            System.out.println("6. Total collision ");
            System.out.println("7. Total time taken");
            System.out.println("8. EXIT");
            System.out.println();
            int ch = sc.nextInt();
            if (ch < 1 || ch > 8) {
                System.out.println("Kindly enter the correct option.");
            } else {
                // Taking the choice from the user and performing the corresponding operation
                try {
                    switch (ch) {
                        case 1:

                            System.out.println("Enter the value to be inserted");
                            int value = sc.nextInt();
                            dh.insert(value);
                            dh.display();
                            break;

                        case 2:
                            System.out.println("Enter value to delete from the hashmap");
                            int key_delete = sc.nextInt();
                            dh.delete(key_delete);
                            ;
                            dh.display();
                            break;

                        case 3:
                            System.out.println("Enter the key whose value you want");
                            int key_value = sc.nextInt();
                            System.out.println(dh.getKeybyValue(key_value));
                            break;

                        case 4:
                            dh.display();
                            break;

                        case 5:
                            System.out.println("Enter the value");
                            int search_value = sc.nextInt();

                            System.out.println(dh.getValueofKey(search_value));
                            break;

                        case 6:
                            System.out.println(dh.collision);
                            break;

                        case 7:
                            long t2 = System.currentTimeMillis();
                            System.out.println("Time in milli seconds is " + (t2 - t1));
                            break;

                        case 8:
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
