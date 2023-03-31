#include <iostream>
#include <bits/stdc++.h>
using namespace std;

// A node in map which has three part key,value and address of next node

// create node with next pointer linkedList
class Node
{
public:
    int key;
    int value;
    Node *next;
    Node(int key, int value)
    {
        this->key = key;
        this->value = value;
        next = NULL;
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
        for (int index = 0; index < CAPACITY; index++)
        {
            hash_table[index] = NULL;
        }
    }

    // hash function which brings key in range
    int HashFunction(int key)
    {
        return key % CAPACITY;
    }

    // Insert method to Put key value pair in HashMap.
    // To handle Collisions I used Separate Chaining method
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
        if (hash_table[hash_position] == NULL)
        {
            size++;
            hash_table[hash_position] = newNode;
        }
        else
        {
            //  if there is already a element present at the hashed index then this is collision
            //  TO HANDLE COLLISION HERE I USE CHAINING METHOD
            collisions++;

             
            Node *array_node = hash_table[hash_position];
            if (array_node->key == key)
            {
                array_node->value = value;
                return;
            }
            while (array_node->next != NULL)
            {
                collisions++;
                if (array_node->next->key == key)
                {
                    cout << "hello" << endl;
                    array_node->next->value = value;
                    return;
                }
                array_node = array_node->next;
            }
            array_node->next = newNode;
        }
        cout << "Insertion Done" << endl;
    }

    // Function to delete the Given key  if present
    void deleteKey(int Key)
    {
        int hash_position = HashFunction(Key);
        if (hash_table[hash_position] != NULL)
        {
            Node *array_node = hash_table[hash_position];

            // check if the first element is the key
            if (array_node->key == Key)
            {

                hash_table[hash_position] = array_node->next;
                cout << "Operation Successful" << endl;
                delete array_node;
                return;
            }

            while (array_node->next->key != Key && array_node->next != NULL)
            {
                array_node = array_node->next;
            }

            // found the key and delete it
            if (array_node->next != NULL)
            {
                Node *to_delete_Node = array_node->next;
                array_node->next = array_node->next->next;
                delete (to_delete_Node);
                cout << "Operation Successful" << endl;
            }
            else
            {
                cout << "Key is Not Present in the HashMap" << endl;
            }
        }
        else
        {
            cout << "Key is Not Present in the HashMap" << endl;
        }
    }

    // Function to Search the Key in Hashmap
    void searchKey(int Key)
    {
        int hash_position = HashFunction(Key);

        // Find the node
        Node * pointer_to_node = hash_table[hash_position] ;
        while (pointer_to_node != NULL)
        {
            if (pointer_to_node!=NULL && pointer_to_node->key == Key)
            {
                cout << "Key Found and its Value is " << pointer_to_node->value << endl;
                return;
            }
            else
            {
                // if not found

                pointer_to_node=pointer_to_node->next ;
            }
        }
        cout << "Key Not found" << endl;
    }

    // Show all the Keys value of HashMap
    void display()
    {
        // i = index of array (hash_map)
        for (int i = 0; i < CAPACITY; i++)
        {
            Node *array_node = hash_table[i];
            cout << "Position " << i << " is connected to the keys are : ";
            while (array_node != NULL)
            {
                cout << array_node->key << "->" << array_node->value << "  ";
                array_node = array_node->next;
            }
            cout << endl;
        }
    }

    // Get the Value of the Key
    void getValue(int Key)
    {
        int hash_position = HashFunction(Key);
        Node *array_node = hash_table[hash_position];
        if (array_node == NULL)
        {
            cout << "Key Not Found" << endl;
        }
        else
        {
            while (array_node != NULL && array_node->key != Key)
            {
                array_node = array_node->next;
            }
            if (array_node == NULL || array_node->key != Key)
            {
                cout << "Key Not Found" << endl;
            }
            else if (array_node->key == Key)
            {
                cout << "The Value of the Key " << Key << " is " << array_node->value << endl;
            }
        }
    }
    void getCollision()
    {
        cout <<"The Number of Collision Occured is "<<collisions << endl;
    }
};
int main()
{
    // used to Calculating time
    auto starting_time = std::chrono::high_resolution_clock::now();

    int capicity;
    // take the capacity of th hashmap
    cout << "Enter the Capacity of the Hashmap " << endl;
    cin >> capicity;

    // Create the Instance of the Class
    Hashmap mp(capicity);
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
            return 0 ;
        default:
            cout<<"Invalid choice"<<endl;
            break ;
        }
    }

    auto ending_time = std::chrono::high_resolution_clock::now();

    auto ms = std::chrono::duration_cast<std::chrono::microseconds>(ending_time - starting_time);
    cout << "Time taken by the programs is " << ms.count() << " microseconds" << endl;
    return 0;
}
