package com.java.demo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class QuadraticProbing {
    // Declare array to store hash table
    Node arr[];

    // Declare size of hash table
    int size;

    // Declare collision count
    int collision = 0;

    int noOfDataPresent = 0; // to calculate no. of data present in a hashmap

    // Constructor
    QuadraticProbing(int size) {
        arr = new Node[size];
        this.size = size;

    }

    // Node class to store key value
    static class Node {
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // Method to calculate hash code for given value
    int hashCode(int key) {
        return Math.abs(key) % size;
    }

    // Method to insert a value into hash table

    void insert(int key, int value) {
        Node valueToInsert = new Node(key, value);
        int hash = hashCode(key);
        if (arr.length <= noOfDataPresent) // if hashmap is full
        {
            System.out.println("Hashmap Full");
            return;
        }
        if (arr[hash] != null && arr[hash].key == key) // if key is already present then update the value.
        {
            arr[hash].value = value;
            return;
        } else if (arr[hash] == null) // if index value is null the insert the data
        {
            arr[hash] = valueToInsert;
            noOfDataPresent++;
            return;
        } else // find next available slot
        {
            int i = 1;
            int counter = 0; // to count the how many time counter running
            while (arr[hash] != null) {
                collision++;
                hash = (hash + i * i) % size; // re calculating hash
                if (arr[hash] != null && arr[hash].key == key) {
                    arr[hash].value = value;
                    return;
                }
                i++;
                counter++;
                if (counter > 10000) {
                    System.out.println("Unable to insert the value");
                    return;
                }
            }
            arr[hash] = valueToInsert;
            noOfDataPresent++;
        }
    }

    // Method to delete a value from hash table
    void delete(int key) {
        // Calculate hash code for given value
        int hash = hashCode(key);
        int i = 1;
        if (arr[hash] == null)
            return;
        if (arr[hash] != null && arr[hash].key == key) {
            arr[hash] = null;
            noOfDataPresent--;
            return;
        }
        int counter = 0; // to count the how many time counter running
        while (arr[hash] != null && arr[hash].key != key) {
            hash = (hash + i * i) % size;
            i++;
            counter++;
            if (counter > 10000) {
                System.out.println("Unable to delete the value");
                return;
            }
        }
        if (arr[hash] != null && arr[hash].key == key) {
            arr[hash] = null;
            noOfDataPresent--;
            System.out.println("value deleted from hashmap");
            return;
        } else {
            System.out.println("no such key is found in a hashmap");
        }
    }

    // method to display the data
    void display() {
        System.out.println("Hashmap :");
        for (int i = 0; i < size; i++) {
            if (arr[i] != null) {
                System.out.println(arr[i].key + " ->" + arr[i].value);

            }
        }
    }

    // method to get value by it's key
    void GetValuebyKey(int key) {
        int hash = hashCode(key);
        if (arr[hash] != null && arr[hash].key == key) {
            System.out.println(arr[hash].key + " -> " + arr[hash].value);
            return;
        }
        int i = 1;
        int counter = 0; // to count the how many time counter running
        while (arr[hash] != null && arr[hash].key != key) {
            hash = (hash + i * i) % size;
            i++;
            counter++;
            if (counter > 10000) {
                System.out.println("Unable to find the value");
                return;
            }
        }
        if (arr[hash] != null) {
            System.out.println(arr[hash].key + " -> " + arr[hash].value);
        } else {
            System.out.println("Value not found with this key");
        }
    }

    // method to search the value in the hashmap and print it's index
    void searchByValue(int value) {
        for (int i = 0; i < size; i++) {
            if (arr[i] != null && arr[i].value == value) {
                System.out.println(arr[i].key);
                return;
            }
        }
        System.out.println("Value not found in a hashmap");
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the map");
        try {
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

                                System.out.println("Enter the key and value to be inserted");
                                int key = sc.nextInt();
                                int value = sc.nextInt();
                                qp.insert(key, value);
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
                                System.out.println("Enter the value to be searched");
                                int search_value = sc.nextInt();

                                qp.searchByValue(search_value);
                                break;

                            case 6:
                                System.out.println(qp.collision);
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
