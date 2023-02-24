package com.java.demo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class QuadraticProbing {
    // Declare array to store hash table
    int arr[];

    // Declare size of hash table
    int size;

    // Declare collision count
    int collision = 0;

    // Constructor to initialize hash table with -1
    QuadraticProbing(int size) {
        arr = new int[size];
        this.size = size;
        for (int i = 0; i < size; i++) {
            arr[i] = -1;
        }
    }

    // Method to calculate hash code for given value
    int hashcode(int value) {
        return Math.abs(value) % size;
    }

    // Method to insert a value into hash table
    void insert(int value) {
        // Calculate hash code for given value
        int hash = hashcode(value);

        // If value is already present in hash table, return
        if (arr[hash] == value) {
            return;
        }

        // If hash table slot for given value is empty, insert value into that slot
        if (arr[hash] == -1) {
            arr[hash] = value;
            return;
        } else {
            // If slot is not empty, use quadratic probing to find next empty slot for given
            // value
            boolean istrue = false;
            int i = 1;
            int c = 0;
            while (!istrue) {
                if (arr[hash] == -1) {
                    arr[hash] = value;
                    istrue = true;
                    return;
                }

                // Calculate new hash code using quadratic probing
                int newhash = (Math.abs(hashcode(hash + i)) % size);

                // Update value of i for next iteration of quadratic probing
                i = i * i;
                c++;

                // If value is already present in hash table, return
                if (arr[newhash] == value) {
                    istrue = true;
                    return;
                }

                // If hash table slot for given value is empty, insert value into that slot
                if (arr[newhash] == -1) {
                    arr[newhash] = value;
                    istrue = true;
                    return;
                }

                // If number of iterations exceeds 1000, return with failure message
                if (c == 1000) {
                    istrue = false;

                    System.out.println("Sorry, not able to insert " + value + " in a hashmap.");
                    return;
                }
            }
        }
    }

    // Method to delete a value from hash table
    void delete(int value) {
        // Calculate hash code for given value
        int hash = hashcode(value);

        // If value is present in hash table, delete it and return
        for (int i = hash + 1; i < size; i++) {
            if (arr[i] == value) {
                arr[i] = -1;
                return;
            }
        }
        for (int i = 0; i < hash; i++) {
            if (arr[i] == value) {
                arr[i] = -1;
                return;
            }
        }

        // If value is not present in hash table, update hash table slot for given value
        // as -1
        arr[hash] = -1;
    }

    // Method to display hash code for given value
    void hashvalue(int value) {
        System.out.println(hashcode(value));
    }

    // method to display the data
    void display() {
        System.out.println("Hashmap :");
        for (int i = 0; i < size; i++) {
            if (arr[i] != -1) {
                System.out.println(i + " ->" + arr[i]);

            }
        }
    }

    // method to get value by it's key
    void GetValuebyKey(int key) {
        System.out.println(arr[key]);
    }

    // method to search the value in the hashmap and print it's index
    void search_by_value(int value) {
        int hash = hashcode(value);
        for (int i = hash + 1; i < size; i++) {
            if (arr[i] == value) {
                System.out.println("value present at " + i + " index");
                return;
            }
        }
        for (int i = 0; i < size; i++) {
            if (arr[i] == value) {
                System.out.println("value present at " + i + " index");
                return;
            }
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the map");
        int sizeofmap = sc.nextInt();
        QuadraticProbing qp = new QuadraticProbing(sizeofmap);
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
                            qp.insert(value);
                            qp.display();
                            break;

                        case 2:
                            System.out.println("Enter value to delete from the hashmap");
                            int key_delete = sc.nextInt();
                            qp.delete(key_delete);
                            qp.display();
                            break;

                        case 3:
                            System.out.println("Enter the key whose value you want");
                            int key_value = sc.nextInt();
                            qp.GetValuebyKey(key_value);
                            break;

                        case 4:
                            qp.display();
                            break;

                        case 5:
                            int search_value = sc.nextInt();

                            qp.search_by_value(search_value);
                            break;

                        case 6:
                            System.out.println(qp.collision);
                            break;

                        case 7:
                            long t2 = System.currentTimeMillis();
                            System.out.println("Time in milli seconds is " + (t2 - t1));

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
