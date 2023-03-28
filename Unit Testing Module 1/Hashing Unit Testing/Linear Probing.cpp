#include <iostream>
#include <cassert>
using namespace std;

// Class Node

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
// Class Hashmap
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

        // Initialise all the value with NULL ;

        for (int i = 0; i < CAPACITY; i++)
        {
            hash_table[i] = NULL;
        }
    }

    // hash function 
    int HashFunction(int key)
    {
        return key % CAPACITY;
    }

    // Insert method 
    void Insert(int key, int value)
    {
        // find hashed indx
        if (this->size >= CAPACITY)
        {
            cout << "HashMap is Full" << endl;
            return;
        }
        int hash_position = HashFunction(key);

        // create newnode
        Node *newNode = new Node(key, value);
        while (hash_table[hash_position] != NULL && hash_table[hash_position]->key != key)
        {
            collisions++;
            hash_position++;
            hash_position = hash_position % CAPACITY;
        }
        if (hash_table[hash_position] == NULL)
        {
          
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

    // Function to delete key
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
    // Function to Search key
    int searchKey(int Key)
    {
        int hash_position = HashFunction(Key);

        // Find the node
        while (hash_table[hash_position] != NULL)
        {
            if (hash_table[hash_position] && hash_table[hash_position]->key == Key)
            {
                cout << "Key Found and its Value is " << hash_table[hash_position]->value << endl;
                return  hash_table[hash_position]->value;
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

    // Display function
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

    
    int getValue(int Key)
    {
        int hash_position = HashFunction(Key);

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
        cout<<endl<<collisions<<endl;
        return collisions;
    }
};


int main() {

    // Test Hashmap::Insert and Hashmap::getValue
    Hashmap h(10);
    h.Insert(1, 2);
    h.Insert(11, 22);
    h.Insert(21, 42);
    h.Insert(31, 62);
    h.Insert(41, 82);
    h.Insert(51, 102);
    h.Insert(61, 122);
    h.Insert(71, 142);
    h.Insert(81, 162);
    h.Insert(91, 182);
    assert(h.getValue(1) == 2);
    assert(h.getValue(11) == 22);
    assert(h.getValue(21) == 42);
    assert(h.getValue(31) == 62);
    assert(h.getValue(41) == 82);
    assert(h.getValue(51) == 102);
    assert(h.getValue(61) == 122);
    assert(h.getValue(71) == 142);
    assert(h.getValue(81) == 162);
    assert(h.getValue(91) == 182);

    // Test Hashmap::getCollision
    assert(h.getCollision() == 45);

    cout << "All tests passed\n";
    return 0;
}
