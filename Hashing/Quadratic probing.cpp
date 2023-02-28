#include <iostream>
#include <bits/stdc++.h>
using namespace std;

// Node Class
class Node
{
public:
    int key;
    int value;
    Node(int key, int value)
    {
        this->key = key;
        this->value = value;
    }
};
// Hashmap Class
class Hashmap
{
    Node **hash_table;
    // capacity 
    int CAPACITY;
    // Size 
    int size;
    // Count number of collisions
    int collisions;

public:
    // contructor
    Hashmap(int CAPACITY)
    {
        this->CAPACITY = CAPACITY;
        collisions = 0;
        size = 0;
        hash_table = new Node *[CAPACITY];

        // Initialise all values with NULL ;
        for (int index = 0; index < CAPACITY; index++)
        {
            hash_table[index] = NULL;
        }
    }

   
    int HashFunction(int key)
    {
        return key % CAPACITY;
    }

    // Insert method 
    void Insert(int key, int value)
    {
        // find the hashed indx
        if (this->size >= CAPACITY)
        {
            cout << "HashMap is Full" << endl;
            return;
        }
        int hash_position = HashFunction(key);

        // create newnode
        Node *newNode = new Node(key, value);
        int initialHash = hash_position;
        int arr_index = 1;
        while (hash_table[hash_position] != NULL && hash_table[hash_position]->key != key)
        {
            collisions++;
            hash_position = (initialHash + (arr_index * arr_index)) % CAPACITY;
            arr_index++;
        }
        if (hash_table[hash_position] == NULL)
        {
            // Increasing the size of the map
            size++;
            hash_table[hash_position] = newNode;
            return;
        }
        // if already exist 
        if (hash_table[hash_position]->key == key)
        {
            hash_table[hash_position]->value = value;
            return;
        }
    }

    // Function to delete key
    void deleteKey(int Key)
    {
        int hash_position = HashFunction(Key);
        int initialHash = hash_position;
        int arr_index = 1;
        // Find node
        while (hash_table[hash_position] != NULL)
        {
            if (hash_table[hash_position] && hash_table[hash_position]->key == Key)
            {
                // removing the key value pair node
                Node *to_delete_node = hash_table[hash_position];

                hash_table[hash_position] = NULL;

                delete to_delete_node;
                // decrementing size
                size--;
                return;
            }
            else
            {
                // if not found
                hash_position = (initialHash + (arr_index * arr_index)) % CAPACITY;
                arr_index++;
            }
        }
        cout << "Key Not found" << endl;
    }
    // Function to Search key 
    void searchKey(int Key)
    {
        int hash_position = HashFunction(Key);
        int initialHash = hash_position;
        int arr_index = 1;
        // Find the node
        while (hash_table[hash_position] != NULL)
        {
            if (hash_table[hash_position] && hash_table[hash_position]->key == Key)
            {
                cout << "Key Found and its Value is " << hash_table[hash_position]->value << endl;
                return;
            }
            else
            {
                // if not found

                hash_position = (initialHash + (arr_index * arr_index)) % CAPACITY;
                arr_index++;
            }
        }
        cout << "Key Not found" << endl;
    }

    // display method
    void display()
    {
        
        for (int i = 0; i < CAPACITY; i++)
        {
            if (hash_table[i] != NULL)
            {
                cout << hash_table[i]->key << " -> " << hash_table[i]->value;
                cout << endl;
            }
        }
    }

    
    void getValue(int Key)
    {
        int hash_position = HashFunction(Key);
        int initialHash = hash_position;
        int arr_index = 1;
        while (hash_table[hash_position] != NULL)
        {
            if (hash_table[hash_position] && hash_table[hash_position]->key == Key)
            {
                cout << "The value of the Key " << Key << " is " << hash_table[hash_position]->value << endl;
                return;
            }
            else
            {
                // if not found

                hash_position = (initialHash + (arr_index * arr_index)) % CAPACITY;
                arr_index++;
            }
        }
        cout << "Key Not found" << endl;
    }
    void getCollision()
    {
        cout << collisions << endl;
    }
};
int main()
{
   
    int capacity;
    // take the capacity of th hashmap
    cout << "Enter the Capacity of the Hashmap " << endl;
    cin >> capacity;

    // Create the Instance of the Class
    Hashmap mp(capacity);
    int choice = 0;
    while (choice != 7)
    {
        // Menu
       
        cout << "\n\nLinked List Menu\n";
        cout << "==============================\n";
        cout << "1. Insert the Key Value Pair in Hashmap" << endl;
        cout << "2. Delete the key from the hashmap" << endl;
        cout << "3. Search the Key in the HashMap" << endl;
        cout << "4. Preview the Hashmap" << endl;
        cout << "5. Get the Value of the key in HashMap" << endl;
        cout << "6. Get the Collisions" << endl;
        cout << "7. Exit" << endl;

        cout << "Enter Your Choice" << endl;
        cin >> choice;
        switch (choice)
        {
        case 1:
        {
            int key, value;
            cout << "Enter the Key and Value to insert in the map ( Note : Enter key and its value separated by space )" << endl;
            cin >> key >> value;
            mp.Insert(key, value);
            break;
        }
        case 2:
        {
            int key;
            cout << "Enter the Key to be Deleted" << endl;
            cin >> key;
            mp.deleteKey(key);
            break;
        }
        case 3:
        {
            int key;
            cout << "Enter the Key to search" << endl;
            cin >> key;
            mp.searchKey(key);
            break;
        }
        case 4:
        {
            mp.display();
            break;
        }
        case 5:
        {
            int key;
            cout << "Enter the Key to get its Value" << endl;
            cin >> key;
            mp.getValue(key);
            break;
        }
        case 6:
        {
            mp.getCollision();
            break;
        }
        case 7:
            cout << "Exit" << endl;
            break;
        }
    }

    
}