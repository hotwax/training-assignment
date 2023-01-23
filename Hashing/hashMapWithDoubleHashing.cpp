#include <bits/stdc++.h>
using namespace std;
using namespace std::chrono;

// Creating HashMap with quadratic Probing

// Double hashing is a collision resolution technique used in hash
//  tables. It works by using two hash functions to compute two
//  different hash values for a given key. The first hash function
//  is used to compute the initial hash value, and the second hash
//  function is used to compute the step size for the probing sequence.

/*
Double hashing can be done using :
(hash1(key) + i * hash2(key)) % TABLE_SIZE
Here hash1() and hash2() are hash functions and TABLE_SIZE
is size of hash table.
(We repeat by increasing i when collision occurs)
*/

/*

A popular second hash function is
hash2(key) = PRIME – (key % PRIME)
where PRIME is a prime smaller than the TABLE_SIZE.

*/

// hash Node Class

class HashNode
{
public:
    int value;
    int key;

    HashNode(int key, int value)
    {
        this->key = key;
        this->value = value;
    }
};

// HashMap Class

class HashMap
{

    // Hash elements array

    HashNode **arr;

    // hash capacity

    int capacity;

    // size
    int size;

    // counting collisions

    int collisions;

    // prime number less than the capacity

    int prime;

    // sieve of eratosthenes for marking all prime number less than the capcity

    vector<bool> isPrime;

public:
    // Constructor
    HashMap(int capacity)
    {
        this->capacity = capacity;
        size = 0;
        collisions = 0;
        // creating hashNode array
        arr = new HashNode *[capacity];

        // initializing all values with NULL

        for (int i = 0; i < capacity; i++)
        {
            arr[i] = NULL;
        }

        // seting prime value

        this->setSieve();

        prime = capacity - 1;
        while (isPrime[prime] != 1)
            prime--;
    }

    // print function

    void print(string message)
    {
        cout << endl
             << message;
    }

    // making sieve for prime numbers

    void setSieve()
    {

        vector<bool> temp(capacity, true);
        this->isPrime = temp;

        isPrime[0] = isPrime[1] = 1;
        for (long long i = 2; i * i <= this->capacity; i++)
            if (isPrime[i] == 1)
                for (long long j = i * i; j <= capacity; j += i)
                    isPrime[j] = 0;
    }

    // first hash function

    int hash1(int key)
    {
        return key % capacity;
    }

    // second hash function

    int hash2(int key)
    {
        return (prime - (key % prime));
    }

    // function for value insertion --

    void insertNode(int key, int value)
    {

        // if hashmap is full
        if (this->size >= capacity)
        {
            print("HashMap is Full");
            return;
        }

        HashNode *temp = new HashNode(key, value);

        int hashIndex = (hash1(key) + hash2(key)) % capacity;

        // finding the free space
        // bool if collision detected
        bool collisionExist = false;

        // Using Double hashing

        while (arr[hashIndex] != NULL && arr[hashIndex]->key != key)
        {
            collisionExist = true;
            // incrementing hashIndex and again making a hashcode for indexing
            hashIndex++;
            hashIndex = hashIndex % capacity;
        }

        if (collisionExist)
        {
            this->collisions++;
        }

        if (arr[hashIndex] == NULL)
        {
            // increasing the size of HashMap
            size++;
            arr[hashIndex] = temp;
        }

        // updating the value on particular key

        if (arr[hashIndex]->key == key)
        {
            arr[hashIndex]->value = value;
        }
    }

    // switch case function for insertion

    void insertForMenu()
    {
        // taking Input

        int key;
        print("Enter the Key: ");
        cin >> key;
        int value;
        print("Enter the value: ");
        cin >> value;

        insertNode(key, value);
    }

    // for deleting a key value pair

    int deleteNode(int key)
    {
        // (hash1(key) + i * hash2(key)) % TABLE_SIZE  --> HashIndex

        int hashIndex = (hash1(key) + hash2(key)) % capacity;

        // finding the node

        while (arr[hashIndex] != NULL)
        {
            if (arr[hashIndex] && arr[hashIndex]->key == key)
            {
                // removing the key value pair node
                HashNode *temp = arr[hashIndex];

                arr[hashIndex] = NULL;

                // decrementing size
                size--;

                return temp->value;
            }
            else
            {
                // if not found

                // using quadratic probing

                hashIndex++;
                hashIndex = hashIndex % capacity;
            }
        }
        cout << "endl"
             << "Key Not Found.";
        return 0;
    }

    // void function for deleting

    void deleteNodeVoid()
    {
        // Taking input from the user

        int key;
        print("Enter the Key: ");
        cin >> key;

        print("The key deleted with value : ");
        cout << deleteNode(key);
    }

    int getValueInMap(int key)
    {

        int hashIndex = (hash1(key) + hash2(key)) % capacity;
        // finding the key for value

        while (arr[hashIndex] != NULL)
        {
            if (arr[hashIndex] && arr[hashIndex]->key == key)
            {
                return arr[hashIndex]->value;
            }

            hashIndex++;
            hashIndex = hashIndex % capacity;
        }
        cout << endl
             << "Key Not found.";
        return 0;
    }

    // void get value

    void getValue()
    {
        // taking Input from the user

        int key;
        print("Enter the key to find the value: ");
        cin >> key;

        print("Value is : ");
        cout << getValueInMap(key);
    }

    // Getting a size

    int sizeOfMap()
    {
        return size;
    }

    // void function to get a size so that it does not return something menu can run soomthly

    void getSize()
    {
        print("The size of Map is : ");
        cout << sizeOfMap();
    }

    void display()
    {
        //			cout<<endl<<"Your HashMap -- ";
        cout << endl;
        for (int i = 0; i < capacity; i++)
        {
            cout << i << " -- ";
            if (arr[i] != NULL)
            {
                cout << arr[i]->key << " -> " << arr[i]->value;
            }
            cout << endl;
        }
    }

    // function to see collision

    void seeCollision()
    {
        cout << this->collisions;
    }
};

// Print function

void print(string message)
{
    cout << endl
         << message;
}

int main()
{
    // For Calculating Time

    auto start = high_resolution_clock::now();

    // -- end For Calculating Time
    int capacity;
    print("Enter the capacity for which the key will be moded to and array will crested on this - ");
    cin >> capacity;
    HashMap map(capacity);

    // while for menu --
    bool menu = true;
    while (menu)
    {
        print("Collisions - ");
        map.seeCollision();
        print("Your Hash Map -- ");
        map.display();

        cout << endl;

        print("Enter 1 for Add Key Value operation");
        print("Enter 2 for Delete with key operation");
        print("Enter 3 for Size of Map operation");
        print("Enter 4 for Get value From Map operation");

        print("Enter 5 to exit");
        cout << endl;
        int input;
        cin >> input;
        int data;

        switch (input)
        {
        case 1:
            map.insertForMenu();
            break;
        case 2:
            map.deleteNodeVoid();
            break;
        case 3:
            map.getSize();
            break;
        case 4:
            map.getValue();
            break;

        default:
            menu = false;
        }
    }

    // For calculating Time

    auto stop = high_resolution_clock::now();

    auto duration = duration_cast<microseconds>(stop - start);

    cout << "Time taken by function: "
         << duration.count() << " microseconds" << endl;

    // end For Calculating Time

    return 0;
}