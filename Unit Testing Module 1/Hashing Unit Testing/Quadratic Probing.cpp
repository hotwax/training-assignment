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
    int getCollision()
    {
        cout << collisions << endl;
        return collisions;
    }
    
    int getSize(){
        return size;
    }
};



int main() {
  Hashmap h(10);

  // Test insertion
  h.Insert(1, 10);
  h.Insert(11, 20);
  h.Insert(21, 30);
  assert(h.getSize() == 3);

  // Test deletion
  h.deleteKey(11);
  assert(h.getSize() == 2);

  // Test searching for a key
  h.searchKey(1);
  h.searchKey(11); // this should not be found after deletion
  h.searchKey(21);

  // Test collision count
  h.Insert(31, 40);
  h.Insert(41, 50);
  h.Insert(51, 60);
  h.Insert(61, 70);
  assert(h.getCollision() == 16);
  cout<<"All test Cases Passed"<<endl;
  return 0;
}
