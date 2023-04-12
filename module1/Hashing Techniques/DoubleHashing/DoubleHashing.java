package com.java.demo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DoubleHashing {
    int size;
    int numberOfElement = 0;
    int collision;
    Node arr[];

    // Constructor for initializing hash map with a given size
    DoubleHashing(int size) {
        this.size = size;
        arr = new Node[size];
        this.numberOfElement = 0;
        this.collision = 0;
    }

    static class Node {
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // Function to find the largest prime number smaller than size
    int smallPrime() {
        for (int i = size - 1; i >= 1; i--) {
            if (isPrime(i)) {
                return i;
            }
        }
        return 1;
    }

    // Function to check if a number is prime or not
    boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Function to insert a value into the hash table
    void insert(int key, int value) {
        if (size == numberOfElement) {
            // If the hash table is full, print an error message
            System.out.println("Hash map is full");
            return;
        }
        int hash = hashCode(key);
        int hashSecond = hashCode2(key);
        Node to_insert = new Node(key, value);
        int counter = 0;
        boolean flag = true;

        if (arr[hash] == null) {
            arr[hash] = to_insert;
            numberOfElement++;
            return;
        }
        if (arr[hash] != null && arr[hash].key == key) {
            arr[hash].value = value;
            return;
        }
        collision++;
        // Handle collisions using double hashing
        while (arr[hash] != null) {
            if (arr[hash].key == key) {
                // If the value is already present in the hash table, update the value
                arr[hash].value = value;
                return;
            }

            hash = (hash + hashSecond) % size;
            counter++;
            if (counter >= 10000) {
                System.out.println("Unable to insert the data");

                return;
            }
        }
        // Insert the value into the hash table
        arr[hash] = to_insert;
        numberOfElement++;
    }

    // Function to delete a value from the hash table
    void delete(int key) {
        int hash = hashCode(key);
        int hashSecond = hashCode2(key);
        int counter = 0;
        while (arr[hash] != null && (!(arr[hash].key == key))) {
            // Search for the value to be deleted using double hashing
            hash = hash + hashSecond;
            counter++;
            hash = hash % size;

            if (counter >= 10000) {
                System.out.println("Unable to delete the data");
                return;
            }
        }
        // Delete the value from the hash table
        arr[hash] = null;
        numberOfElement--;
    }

    // Function to get the key of a value in the hash table
    int getValueofKey(int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].value == value) {
                return arr[i].key;
            }
        }
        return -1;

    }

    // Function to get the key of a value in the hash table
    int getValuebyKey(int key) {
        int hash = hashCode(key);
        int hashSecond = hashCode2(key);
        boolean issafe = true;
        int counter = 0;
        while (arr[hash] != null && (!(arr[hash].key == key) && issafe)) {
            // Search for the key of the given value using double hashing
            counter++;
            hash = hash + hashSecond;
            hash = hash % size;
            if (counter >= 1000) {
                // If the maximum number of iterations is reached, break the loop
                issafe = false;
            }

            if (arr[hash] != null && arr[hash].key == key) {
                return arr[hash].value;
            }
        }
        if (issafe == false) {
            // If the value is not found within the maximum number of iterations, return -1
            return -1;
        } else {
            // Return the key of the given value
            if (arr[hash] != null) {
                return arr[hash].value;
            } else {
                return -1;
            }
        }
    }

    // Function to display the contents of the
    void display() {
        System.out.println("Hashmap : ");
        for (int i = 0; i < size; i++) {
            if (arr[i] != null) {
                System.out.println(arr[i].key + "->" + arr[i].value);
            }
        }
    }

    // Computes the second hash code for the given value using double hashing
    // technique.

    int hashCode2(int key) {
        int primeNum = smallPrime();
        return primeNum - (key % primeNum);
    }

    // Computes the first hash code for the given value using modulo operator.
    int hashCode(int key) {
        return Math.abs(key) % size;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the map");
        try {
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

                                System.out.println("Enter the key and value to be inserted");
                                int key = sc.nextInt();
                                int value = sc.nextInt();
                                dh.insert(key, value);
                                dh.display();
                                break;

                            case 2:
                                System.out.println("Enter value to delete from the hashmap");
                                int key_delete = sc.nextInt();
                                dh.delete(key_delete);
                                dh.display();
                                break;

                            case 3:
                                System.out.println("Enter the key whose value you want");
                                int key_value = sc.nextInt();
                                System.out.println(dh.getValuebyKey(key_value));
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

        } catch (InputMismatchException ex) {
            System.out.println("Please re-run the program and enter the correct value");
        }
    }
}
