
#include <iostream>
#include <cassert>
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
// HashMap Class
class Hashmap
{
    Node **hash_table;
    // capacity 
    int CAPACITY;
    // Size 
    int size;
    // Count number of collisions
    int collisions;
    int random_number ;

public:
    // contructor which initialize the members
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
        random_number = rand()%CAPACITY  ; 
    }

    // hash function
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
        while (hash_table[hash_position] != NULL && hash_table[hash_position]->key != key)
        {
            collisions++;
            // incrementing hashIndex and again making a hashcode for indexing
            hash_position=hash_position + (random_number*collisions) ;
            hash_position = hash_position % CAPACITY;
        }
        if (hash_table[hash_position] == NULL)
        {
            // Increasing the size of the map
            size++;
            hash_table[hash_position] = newNode;
            return;
        }
        // if already exists
        if (hash_table[hash_position]->key == key)
        {
            hash_table[hash_position]->value = value;
            return;
        }
    }

    // Function to delete the Given key 
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

                hash_position = hash_position + (random_number * collisions);
                hash_position %= CAPACITY;
            }
        }
        cout << "Key Not found" << endl;
    }
    // Function to Search the key
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

                hash_position=hash_position + (random_number * collisions) ;
                hash_position %= CAPACITY;
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
            cout<<"At position "<<i<<" : ";
            if (hash_table[i] != NULL)
            {
                cout << hash_table[i]->key << " -> " << hash_table[i]->value;
            }
            cout << endl;
        }
    }

    // Get the Value of the Key
    int getValue(int Key)
    {
        int hash_position = HashFunction(Key);

        while (hash_table[hash_position] != NULL)
        {
            if (hash_table[hash_position] && hash_table[hash_position]->key == Key)
            {
                cout << "The value of the Key " << Key << " is " << hash_table[hash_position]->value << endl;
                return hash_table[hash_position]->value ;
            }
            else
            {
                // if not found

                hash_position = hash_position + (random_number * collisions);
                hash_position %= CAPACITY;
            }
        }
        cout << "Key Not found" << endl;
        return -1;
    }
    int getCollisions()
    {
        cout << collisions << endl;
        return collisions;
    }
    int getSize(){
        return size;
    }
};

// Include the Hashmap and Node classes here

int main()
{
    // Create a Hashmap object with capacity 5
    Hashmap h(10);

    // Insert some key-value pairs
    h.Insert(1, 10);
    h.Insert(2, 20);
    h.Insert(3, 30);
    h.Insert(4, 40);

    // Test case 1: Insertion
    assert(h.getSize() == 4);
    assert(h.getCollisions() == 0);
    
    assert(h.getValue(1) == 10);
    
    assert(h.getValue(2) == 20);
    
    assert(h.getValue(3) == 30);
   
    assert(h.getValue(4) == 40);

    // Insert a key that already exists
    h.Insert(2, 25);

    // Test case 2: Updating an existing key-value pair
    assert(h.getSize() == 4);
    assert(h.getCollisions() == 0);

    // Insert more key-value pairs to cause collisions
    h.Insert(6, 60);
    h.Insert(11, 110);

    // Test case 3: Collisions
   // cout<<h.getSize();
    assert(h.getSize() == 6);
    assert(h.getCollisions() == 2);


    // Test case 4: Deletion
    h.deleteKey(3);
    assert(h.getSize() == 5);
    assert(h.getCollisions() == 2);
    

    // Test case 5: Searching
    h.searchKey(6); // Should print "Key Found and its Value is 60"
    h.searchKey(7); // Should print "Key Not found"

    // Test case 6: Get value of key
    h.getValue(4); // Should print "The value of the Key 4 is 40"
    h.getValue(7); // Should print "Key Not found"

    // Test case 7: Display
    h.display(); // Should print all the key-value pairs
    cout<<"All Test Cases Passed";
    return 0;
}
