package com.java.demo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LinearProbing {

    int arr[]; // array to hold the hash table
    int size; // size of the hash table
    int collision = 0; // variable to keep track of the number of collisions occurred during insertion

    LinearProbing(int size) {
        arr = new int[size];
        this.size = size;
        for (int i = 0; i < size; i++) {
            arr[i] = -1; // initializing all elements of the array to -1, indicating they are empty
        }
    }

    // method to calculate hashcode for a given value
    int hashcode(int value) {
        return Math.abs(value) % size; // using absolute value to handle negative input values
    }

    // method to insert a value in the hash table
    void insert(int value) {
        int hash = hashcode(value);
        if (arr[hash] == value) // if the value already exists in the hash table, return
        {
            return;
        }
        if (arr[hash] == -1) // if the slot corresponding to the hash code is empty, insert the value
        {
            arr[hash] = value;
            return;
        } else // if the slot corresponding to the hash code is occupied, handle collision by
               // probing for the next empty slot
        {
            collision++;
            for (int i = hash + 1; i < size; i++) // probing towards the right of the hash code
            {
                if (arr[hash] == value) // if the value already exists in the hash table, return
                {
                    return;
                }
                if (arr[i] == -1) // if an empty slot is found, insert the value
                {
                    arr[i] = value;
                    return;
                }
            }
            for (int i = 0; i < hash; i++) // probing towards the left of the hash code
            {
                if (arr[hash] == value) // if the value already exists in the hash table, return
                {
                    return;
                }
                if (arr[i] == -1) // if an empty slot is found, insert the value
                {
                    arr[i] = value;
                    return;
                }
            }
        }

        System.out.println("No value inserted" + value); // if no empty slot is found, the value cannot be inserted
        collision--;

    }

    // method to delete a value from the hash table
    void delete(int value) {
        int hash = hashcode(value);
        for (int i = hash + 1; i < size; i++) // searching towards the right of the hash code
        {
            if (arr[i] == value) // if the value is found, delete it and return
            {
                arr[i] = -1;
                return;
            }
        }
        for (int i = 0; i < hash; i++) // searching towards the left of the hash code
        {
            if (arr[i] == value) // if the value is found, delete it and return
            {
                arr[i] = -1;
                return;
            }
        }
        arr[hash] = -1; // if the value is not found in any of the slots, the hash code slot can be
                        // emptied
    }

    // method to calculate hash code for a given value and display it
    void hashvalue(int value) {
        System.out.println(hashcode(value));
    }

    // Method to display the hashmap
    void display() {
        System.out.println("Hashmap :");
        for (int i = 0; i < size; i++) {
            if (arr[i] != -1) {
                System.out.println(i + " ->" + arr[i]);
            }
        }
    }

    // Method to get value by key
    void GetValuebyKey(int key) {
        System.out.println(arr[key]);
    }

    // Method to search for a value in the hashmap
    void search_by_value(int value) {
        // Get the hashcode for the value
        int hash = hashcode(value);

        // Search for the value starting from the next index in the array
        for (int i = hash + 1; i < size; i++) {
            if (arr[i] == value) {
                System.out.println("value present at " + i + " index");
                return;
            }
        }

        // If value is not found after reaching the end of the array, search from the
        // beginning
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

        LinearProbing lp = new LinearProbing(sizeofmap);
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
                            lp.insert(value);
                            lp.display();
                            break;

                        case 2:
                            System.out.println("Enter value to delete from the hashmap");
                            int key_delete = sc.nextInt();
                            lp.delete(key_delete);
                            lp.display();
                            break;

                        case 3:
                            System.out.println("Enter the key whose value you want");
                            int key_value = sc.nextInt();
                            lp.GetValuebyKey(key_value);
                            break;

                        case 4:
                            lp.display();
                            break;

                        case 5:
                            int search_value = sc.nextInt();

                            lp.search_by_value(search_value);
                            break;

                        case 6:
                            System.out.println(lp.collision);
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
