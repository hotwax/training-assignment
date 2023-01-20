import java.io.*;
import java.util.*;
import java.util.Scanner;

class LinearProbingHashTable {
    // Member variables of this class
    private int currentSize, maxSize;
    private String[] keys;
    private String[] vals;

    // Constructor of this class
    public LinearProbingHashTable(int capacity) {
        currentSize = 0;
        maxSize = capacity;
        keys = new String[maxSize];
        vals = new String[maxSize];
    }

    // Function to clear hash table
    public void makeEmpty() {
        currentSize = 0;
        keys = new String[maxSize];
        vals = new String[maxSize];
    }

    // Function to get size of hash table
    public int getSize() {
        return currentSize;
    }

    // Function to check if hash table is full
    public boolean isFull() {
        return currentSize == maxSize;
    }

    // Function to check if hash table is empty
    public boolean isEmpty() {
        return getSize() == 0;
    }

    // Function to check if hash table contains a key
    public boolean contains(String key) {
        return get(key) != null;
    }

    // Function to get hash code of a given key
    private int hash(String key) {
        return key.hashCode() % maxSize;
    }

    // Function to insert key-value pair
    public void insert(String key, String val) {
        int tmp = hash(key);
        int i = tmp;
        // Do part for performing actions
        do {
            if (keys[i] == null) {
                keys[i] = key;
                vals[i] = val;
                currentSize++;
                return;
            }

            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }

            i = (i + 1) % maxSize;

        }

        // while part for condition check
        while (i != tmp);
    }

    // Function to get value for a given key
    public String get(String key) {
        int i = hash(key);
        while (keys[i] != null) {
            if (keys[i].equals(key))
                return vals[i];
            i = (i + 1) % maxSize;
        }
        return null;
    }

    // Function to remove key and its value
    public void remove(String key) {
        if (!contains(key))
            return;

        // Find position key and delete
        int i = hash(key);
        while (!key.equals(keys[i]))
            i = (i + 1) % maxSize;
        keys[i] = vals[i] = null;

        // rehash all keys
        for (i = (i + 1) % maxSize; keys[i] != null; i = (i + 1) % maxSize) {
            String tmp1 = keys[i], tmp2 = vals[i];
            keys[i] = vals[i] = null;
            currentSize--;
            insert(tmp1, tmp2);
        }
        currentSize--;
    }

    // Function to print HashTable
    public void printHashTable() {
        System.out.println("\nHash Table: ");
        for (int i = 0; i < maxSize; i++)
            if (keys[i] != null)
                System.out.println(keys[i] + " " + vals[i]);
        System.out.println();
    }
}

// Main testing class
// Main Class for LinearProbingHashTableTest
public class LinearProbing {
    // Main driver method
    public static void main(String[] args) {
        // Creating a scanner object
        // to take input from user
        Scanner sc = new Scanner(System.in);

        // Display messages
        System.out.println("Hash Table Test\n\n");
        System.out.println("Enter size");

        // maxSizeake object of LinearProbingHashTable
        LinearProbingHashTable lpht = new LinearProbingHashTable(sc.nextInt());

        char ch;

        // Do-while loop
        // Do part for performing actions
        do

        {
            // Menu
            System.out.println("\nHash Table Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. remove");
            System.out.println("3. get");
            System.out.println("4. clear");
            System.out.println("5. size");

            int ch = sc.nextInt();

            // Switch case
            switch (ch) {

                // Case 1
                case 1:

                    // Display message
                    System.out.println("Enter key and value");
                    lpht.insert(sc.next(), sc.next());
                    // Break statement to terminate a case
                    break;

                // Case 2
                case 2:

                    // Display message
                    System.out.println("Enter key");
                    lpht.remove(sc.next());
                    // Break statement to terminate a case
                    break;

                // Case 3
                case 3:

                    // Print statements
                    System.out.println("Enter key");
                    System.out.println("Value = "
                            + lpht.get(sc.next()));
                    // Break statement to terminate a case
                    break;

                // Case 4
                case 4:

                    lpht.makeEmpty();
                    // Print statement
                    System.out.println("Hash Table Cleared\n");
                    // Break statement to terminate a case
                    break;

                // Case 5
                case 5:

                    // Print statement
                    System.out.println("Size = "
                            + lpht.getSize());
                    break;

                // Default case
                // Executed when mentioned switch cases are not
                // matched
                default:
                    // Print statement
                    System.out.println("Wrong Entry \n ");
                    // Break statement
                    break;
            }

            // Display hash table
            lpht.printHashTable();

            // Display message asking the user whether
            // he/she wants to continue
            System.out.println(
                    "\nDo you want to continue (Type y or n) \n");

            // Reading character using charAt() method to
            // fetch
            ch = sc.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }
}
