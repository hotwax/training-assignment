#include <iostream>
#include <cassert>
using namespace std;
// Class Node
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
        if (hash_table[hash_position] == NULL)
        {
            size++;
            hash_table[hash_position] = newNode;
        }
        else
        {

             
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
                   // cout << "hello" << endl;
                    array_node->next->value = value;
                    return;
                }
                array_node = array_node->next;
            }
            array_node->next = newNode;
        }
    }

    // Function to delete key 
    void deleteKey(int Key)
    {
        int hash_position = HashFunction(Key);
        if (hash_table[hash_position] != NULL)
        {
            Node *array_node = hash_table[hash_position];

            // check if first element is the key
            if (array_node->key == Key)
            {

                hash_table[hash_position] = array_node->next;
                delete array_node;
                return;
            }

            while (array_node->next->key != Key && array_node->next != NULL)
            {
                array_node = array_node->next;
            }

            // find key and delete 
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

    // Function to Search the Key
    int searchKey(int Key)
    {
        int hash_position = HashFunction(Key);

        // Find node
        Node * pointer_to_node = hash_table[hash_position] ;
        while (pointer_to_node != NULL)
        {
            if (pointer_to_node!=NULL && pointer_to_node->key == Key)
            {
                cout<< "Key Found and its Value is"+pointer_to_node->value <<endl;
                //return;
                return pointer_to_node->value;
            }
            else
            {
                // if not found

                pointer_to_node=pointer_to_node->next ;
            }
        }
        cout<< "Key Not found"<<endl ;
        return -1;
    }

    // Show all the Keys value of HashMap
    void display()
    {
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

    int getValue(int Key)
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
                return -1;
            }
            else if (array_node->key == Key)
            {
                cout << "The Value of the Key " << Key << " is " << array_node->value << endl;
            }
        }
        return array_node->value;
    }
    int getCollision()
    {
        cout <<"collisions are"<< collisions << endl;
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

    // Test Hashmap::Insert with duplicate keys and Hashmap::deleteKey
    h.Insert(1, 3);
    h.Insert(11, 33);
    assert(h.getValue(1) == 3);
    assert(h.getValue(11) == 33);
    h.deleteKey(1);
    assert(h.getValue(1) == -1);

    // Test Hashmap::searchKey
    assert(h.searchKey(11) == 33);
    assert(h.searchKey(1) == -1);

    // Test Hashmap::getCollision
    assert(h.getCollision() == 37);

    cout << "All tests passed\n";
    return 0;
}
