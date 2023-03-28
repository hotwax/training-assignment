#include <iostream>
#include <cassert>
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
    int searchKey(int Key)
    {
        int hash_position = (HashFunction1(Key) + HashFunction2(Key)) % CAPACITY;

        // Find node
        while (hash_table[hash_position] != NULL)
        {
            if (hash_table[hash_position] && hash_table[hash_position]->key == Key)
            {
                cout << "Key Found and its Value is " << hash_table[hash_position]->value << endl;
                return hash_table[hash_position]->value;
            }
            else
            {
                // if not found

                hash_position++;
                hash_position %= CAPACITY;
            }
        }
        cout << "Key Not found" << endl;
        return -1;
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
    int getValue(int Key)
    {
        int hash_position = (HashFunction1(Key) + HashFunction2(Key)) % CAPACITY;

        while (hash_table[hash_position] != NULL)
        {
            if (hash_table[hash_position] && hash_table[hash_position]->key == Key)
            {
                cout << "The value of the Key " << Key << " is " << hash_table[hash_position]->value << endl;
                return hash_table[hash_position]->value;
            }
            else
            {
                // if not found

                hash_position++;
                hash_position %= CAPACITY;
            }
        }
        cout << "Key Not found" << endl;
        return -1;
    }
    int getCollision()
    {
        cout << collisions << endl;return collisions;
    }
};

int main()
{
    // create a hashmap with capacity 5
    Hashmap map(5);

    // test the Insert function
    map.Insert(1, 10);
    map.Insert(2, 20);
    map.Insert(3, 30);
    map.Insert(4, 40);
    map.Insert(5, 50);

    // test the getValue function
    assert(map.getValue(1) ==  10);
    assert(map.getValue(2) == 20);
    assert(map.getValue(3) == 30);
    assert(map.getValue(4) == 40);
    assert(map.getValue(5) ==  50);
   // assert(map.getValue(6) == -1);

    // test the searchKey function
    assert(map.searchKey(1) ==  10);
    assert(map.searchKey(2) == 20);
    assert(map.searchKey(3) ==  30);
    assert(map.searchKey(4) ==  40);
    assert(map.searchKey(5) ==  50);
   // assert(map.searchKey(6) == -1);

    // test the deleteKey function
    map.deleteKey(1);
    assert(map.getValue(1) == -1);
    assert(map.searchKey(1) == -1);

    // test the getCollision function
    assert(map.getCollision() == 0);
    cout<<"All Test Cases Passed";
    return 0;
}