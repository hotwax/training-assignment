#include <iostream>
#include<bits/stdc++.h>
using namespace std;


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

class Hashmap
{
    Node **hash_table;
    // capacity
    int CAPACITY;
    // Size 
    int size;
    // Count number of collisions
    int collisions;
    int Prime;
    int *isPrime;

public:
    // contructor 
    Hashmap(int CAPACITY)
    {
        this->CAPACITY = CAPACITY;
        collisions = 0;
        size = 0;
        hash_table = new Node *[CAPACITY];
        isPrime = new int[CAPACITY];
        // Initialise all values with NULL ;

        for (int index = 0; index < CAPACITY; index++)
        {
            hash_table[index] = NULL;
        }
        for(int index = 0 ; index < CAPACITY ; index++)
        {
            isPrime[index]=-1 ;
        }
        Prime = CAPACITY - 1;
        sieveAlgo();
        while (isPrime[Prime] != 1)
            Prime--;
    }

    void sieveAlgo()
    {
        isPrime[0] = isPrime[1] = 1;
        for (long long i = 2; i * i <= CAPACITY; i++)
            if (isPrime[i] == 0)
                for (long long j = i * i; j <= CAPACITY; j += i)
                    isPrime[j] = 1;
    }

    // hash function 
    int HashFunction1(int key)
    {
        return key % CAPACITY;
    }

    // hash function 2
    int HashFunction2(int key)
    {
        return Prime - (key % Prime);
    }

    void Insert(int key, int value)
    {
       
        if (this->size >= CAPACITY)
        {
            cout << "HashMap is Full" << endl;
            return;
        }
        int hash_position = (HashFunction1(key) + HashFunction2(key)) % CAPACITY;

        // create newnode
        Node *newNode = new Node(key, value);
        bool Collide = false ;
        while (hash_table[hash_position] != NULL && hash_table[hash_position]->key != key)
        {
            Collide=true ;
            hash_position++;
            hash_position = hash_position % CAPACITY;
        }
        if(Collide==true)
        collisions++ ;
        if (hash_table[hash_position] == NULL)
        {
            // Increasing size of map
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
        int hash_position = (HashFunction1(Key) + HashFunction2(Key)) % CAPACITY;

        // Find node
        while (hash_table[hash_position] != NULL)
        {
            if (hash_table[hash_position] && hash_table[hash_position]->key == Key)
            {
                // removing key value pair node
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
    // Function to Search the Key
    void searchKey(int Key)
    {
        int hash_position = (HashFunction1(Key) + HashFunction2(Key)) % CAPACITY;

        // Find node
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
        int hash_position = (HashFunction1(Key) + HashFunction2(Key)) % CAPACITY;

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