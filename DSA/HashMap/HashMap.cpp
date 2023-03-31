#include <iostream>
using namespace std;

// A node in Hashmap which has three part key,value and address of next node
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

// Create Custom Hashmap Datastructure
// HashMap Data Structure with User Given Capacity
class Hashmap
{
    Node **hashTable;
    int CAPACITY;

public:
    // contructor which initialize the members
    Hashmap(int CAPACITY)
    {
        this->CAPACITY = CAPACITY;
        hashTable = new Node *[CAPACITY];

        // i = index , to iterate
        for (int i = 0; i < CAPACITY; i++)
        {
            hashTable[i] = NULL;
        }
    }

    // hash function which brings key in range
    // more formally it convert a big value into small values whose range lies between 0 < i < Capacity of HashMap
    int HashFunction(int key)
    {
        return key % CAPACITY;
    }

    // Insert method to Put key value pair in HashMap.
    // To handle Collisions I used Separate Chaining method
    void Insert(int key, int value)
    {
        // Hash Code of the Given Key
        int hash_position = HashFunction(key);
        Node *newNode = new Node(key, value); // Create New Node

        // if the slot at hash position is NULL
        if (hashTable[hash_position] == NULL)
        {
            hashTable[hash_position] = newNode;
        }
        // first node key is equal to the key , so update its value
        else if(hashTable[hash_position]->key == key)
        {
            hashTable[hash_position]->value = value ;
        }
        else
        {
            // Make a Pointer which initially points to the First node of the HashMap
            // Used to Interate Over the HashMap
            Node *pointer_to_node = hashTable[hash_position];

            while (pointer_to_node->next != NULL && pointer_to_node -> next -> key != key )
            {
                pointer_to_node = pointer_to_node->next;
            }
            // if key not exist 
            if (pointer_to_node->next == NULL )
            {
                pointer_to_node->next = newNode;
            }
            // if key already exist then we update its value
            else if(pointer_to_node -> next -> key == key)
            {
                pointer_to_node->next->value = value ;
            }
        }
    }
    
    // Function to delete the Given key  if present
    void deleteKey(int Key)
    {
        int hash_position = HashFunction(Key);

        if (hashTable[hash_position] != NULL)
        {
            Node *pointer_to_node = hashTable[hash_position];

            // to Delete first node
            if (pointer_to_node->key == Key)
            {
                hashTable[hash_position] = pointer_to_node->next;
                cout << "Operation Successful" << endl;
                return;
            }
            // Find the Node to delete
            while (pointer_to_node->next->key != Key && pointer_to_node->next != NULL)
            {
                pointer_to_node = pointer_to_node->next;
            }

            // Node Found
            if (pointer_to_node->next != NULL)
            {
                pointer_to_node->next = pointer_to_node->next->next;
                cout << "Operation Successful" << endl;
            }

            // Node not Present in the hashMap
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

    void searchKey(int Key)
    {
        int hash_position = HashFunction(Key);

        // Find the node
        Node * pointer_to_node = hashTable[hash_position] ;
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

    //  Function to Display the HashMap
    void display()
    {
        // i = index , to iterate
        for (int i = 0; i < CAPACITY; i++)
        {
            // Make a Pointer which initially points to the First node of the HashMap
            // Used to Interate Over the HashMap
            Node *pointer_to_node = hashTable[i];

            cout << "Position " << i << " is connected to the keys are : ";

            // Print all the Nodes at i'th position
            while (pointer_to_node != NULL)
            {
                cout << pointer_to_node->key << "->" << pointer_to_node->value << "  ";
                pointer_to_node = pointer_to_node->next;
            }

            cout << endl;
        }
    }

    // Function to Print the Value Of the Key
    void getKey(int Key)
    {
        int hash_position = HashFunction(Key);
        Node *pointer_to_node = hashTable[hash_position];
        if (pointer_to_node == NULL)
        {
            cout << "Key Not Found" << endl;
        }
        else
        {
            while (pointer_to_node != NULL && pointer_to_node->key != Key)
            {
                pointer_to_node = pointer_to_node->next;
            }
            if (pointer_to_node == NULL)
            {
                cout << "Key Not Found" << endl;
                return ;
            }
            if (pointer_to_node->key == Key)
            {
                cout << "The Value of the Key " << Key << " is " << pointer_to_node->value << endl;
            }
            else
            {
                cout << "Key Not Found" << endl;
                return ;
            }
        }
    }
}; // End of class
int main()
{
    // Capacity Of the HashMap
    int capacity;
    cout << "Enter the Capacity of the Hashmap " << endl;
    cin >> capacity;

    // Create a Object / Instance of Class hashmap
    Hashmap mp(capacity);

    int choice = 0;
    while (true)
    {
        cout << endl;
        cout << "1. Insert the Key Value Pair in Hashmap" << endl;
        cout << "2. Delete the key from the hashmap" << endl;
        cout << "3. Search the Key in the HashMap" << endl;
        cout << "4. Preview the Hashmap" << endl;
        cout << "5. Get the Value of the key in HashMap" << endl;
        cout << "6. Exit" << endl;

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
            cout<<"Insertion Done"<<endl;
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
            mp.getKey(key);
            break;
        }
        case 6:
            cout << "Exit" << endl;
            return 0;
        default:
            cout<<"Invalid Choice"<<endl;
            break;
        }
    }
    return 0;
}
