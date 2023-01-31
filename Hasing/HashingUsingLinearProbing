#include <iostream>
#include <bits/stdc++.h>
using namespace std;

// A node in map which has three part key,value

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
// HashMap Data Structure with User Given Capacity
class Hashmap
{
    Node **hash_table;
    // capacity of the Hashmap
    int CAPACITY;
    // Size of hashMap
    int size;
    // Count number of collisions
    int collisions;

public:
    // contructor which initialize the members
    Hashmap(int CAPACITY)
    {
        this->CAPACITY = CAPACITY;
        collisions = 0;
        size = 0;
        hash_table = new Node *[CAPACITY];

        // Initialise all the value with NULL ;

        // i = index of hash_table
        for (int i = 0; i < CAPACITY; i++)
        {
            hash_table[i] = NULL;
        }
    }

    // hash function which brings key in range
    int HashFunction(int key)
    {
        return key % CAPACITY;
    }

    // Insert method to Put key value pair in HashMap.
    // To handle Collisions I used Linear Probing
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
        //  if there is already a element present at the hashed index then this is collision
        //  TO HANDLE COLLISION HERE I USE LINEAR PROBING
        while (hash_table[hash_position] != NULL && hash_table[hash_position]->key != key)
        {
            collisions++;
            // incrementing hashIndex and again making a hashcode for indexing
            hash_position++;
            hash_position = hash_position % CAPACITY;
        }
        if (hash_table[hash_position] == NULL)
        {
            // Increasing the size of the map
            size++;
            hash_table[hash_position] = newNode;
            return;
        }
        // if already exist the key Update its Value
        if (hash_table[hash_position]->key == key)
        {
            hash_table[hash_position]->value = value;
            return;
        }
    }

    // Function to delete the Given key  if present
    void deleteKey(int Key)
    {
        int hash_position = HashFunction(Key);

        // Find the node
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

                hash_position++;
                hash_position %= CAPACITY;
            }
        }
        cout << "Key Not found" << endl;
    }
    // Function to Search the Key in Hashmap
    void searchKey(int Key)
    {
        int hash_position = HashFunction(Key);

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

                hash_position++;
                hash_position %= CAPACITY;
            }
        }
        cout << "Key Not found" << endl;
    }

    // Show all the Keys value of HashMap
    void display()
    {
        // i = index of array hashmap
        for (int i = 0; i < CAPACITY; i++)
        {
            if (hash_table[i] != NULL)
            {
                cout << hash_table[i]->key << " -> " << hash_table[i]->value;
                cout << endl;
            }
        }
    }

    // Get the Value of the Key
    void getValue(int Key)
    {
        int hash_position = HashFunction(Key);

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
                hash_position++;
                hash_position %= CAPACITY;
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
    // used to Calculating time
    auto starting_time = std::chrono::high_resolution_clock::now();

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
        cout << endl;
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

    auto ending_time = std::chrono::high_resolution_clock::now();

    auto ms = std::chrono::duration_cast<std::chrono::microseconds>(ending_time - starting_time);
    cout << "Time taken by the programs is " << ms.count() << " microseconds" << endl;
    return 0;
}
