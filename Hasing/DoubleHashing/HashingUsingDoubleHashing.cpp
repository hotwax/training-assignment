#include <iostream>
#include<bits/stdc++.h>
using namespace std;

// To handle Collision here I use Double Hashing
// In Double Hashing we have two hash Function: (hash1(key) + i * hash2(key)) % CAPACITY (i=0 ,  i+1 , i+2 , i+3)
// Here hash1() and hash2() are hash functions and CAPACITY is size of hash table.

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
    int Prime;
    int *isPrime;

public:
    // contructor which initialize the members
    Hashmap(int CAPACITY)
    {
        this->CAPACITY = CAPACITY;
        collisions = 0;
        size = 0;
        hash_table = new Node *[CAPACITY];
        isPrime = new int[CAPACITY];
        // Initialise all the value with NULL ;

        // i = index
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

    // Here to find the prime numbers we use sieve algo
    void sieveAlgo()
    {
        isPrime[0] = isPrime[1] = 1;
        for (long long i = 2; i * i <= CAPACITY; i++)
            if (isPrime[i] == 0)
                for (long long j = i * i; j <= CAPACITY; j += i)
                    isPrime[j] = 1;
    }

    // hash function which brings key in range

    // hash function 1
    int HashFunction1(int key)
    {
        return key % CAPACITY;
    }

    // hash function 2
    int HashFunction2(int key)
    {
        return Prime - (key % Prime);
    }

    // Insert method to Put key value pair in HashMap.
    // To handle Collisions I used Double Hashing
    // A Second hash function can be hash2(key) = PRIME – (key % PRIME)
    // where PRIME is a prime smaller than the TABLE_SIZE.
    void Insert(int key, int value)
    {
        // find the hashed indx
        if (this->size >= CAPACITY)
        {
            cout << "HashMap is Full" << endl;
            return;
        }
        int hash_position = (HashFunction1(key) + HashFunction2(key)) % CAPACITY;

        // create newnode
        Node *newNode = new Node(key, value);
        //  if there is already a element present at the hashed index then this is collision
        //  TO HANDLE COLLISION HERE I USE Double Hashing
        bool Collide = false ;
        while (hash_table[hash_position] != NULL && hash_table[hash_position]->key != key)
        {
            Collide=true ;
            // incrementing hashIndex and again making a hashcode for indexing
            hash_position++;
            hash_position = hash_position % CAPACITY;
        }
        if(Collide==true)
        collisions++ ;
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
        cout<<"Insertion Done"<<endl;
    }

    // Function to delete the Given key  if present
    void deleteKey(int Key)
    {
        int hash_position = (HashFunction1(Key) + HashFunction2(Key)) % CAPACITY;

        // Find the node
        while (hash_table[hash_position] != NULL)
        {
            if (hash_table[hash_position] && hash_table[hash_position]->key == Key)
            {
                // removing the key value pair node
                Node *to_delete_node = hash_table[hash_position];

                hash_table[hash_position] = NULL;
                cout<<"Operation Successful"<<endl;
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
        int hash_position = (HashFunction1(Key) + HashFunction2(Key)) % CAPACITY;

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
        // i = index of array (hashmap)
        int flag = 0 ;
        for (int i = 0; i < CAPACITY; i++)
        {
            if (hash_table[i] != NULL)
            {
                flag = 1 ;
                cout << hash_table[i]->key << " -> " << hash_table[i]->value;
                cout << endl;
            }
        }
        if(!flag) cout<<"HashMap is Empty"<<endl;

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
        cout <<"The Number of Collision Occured is "<< collisions << endl;
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
