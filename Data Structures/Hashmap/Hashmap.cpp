#include <iostream>
using namespace std;

// Class Node
class Node
{
public:
    int key;
    int value;
    Node *next;
    // Constructor
    Node(int key, int value)
    {
        this->key = key;
        this->value = value;
        next = NULL;
    }
};

// Class Hahmap
class Hashmap
{
    Node **hashTable;
    int CAPACITY;

public:
    // contructor 
    Hashmap(int CAPACITY)
    {
        this->CAPACITY = CAPACITY;
        hashTable = new Node *[CAPACITY];

        
        for (int i = 0; i < CAPACITY; i++)
        {
            hashTable[i] = NULL;
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
        // Hash Code 
        int hash_position = HashFunction(key);
        Node *newNode = new Node(key, value); 

        
        if (hashTable[hash_position] == NULL)
        {
            hashTable[hash_position] = newNode;
        }
        
        else if(hashTable[hash_position]->key == key)
        {
            hashTable[hash_position]->value = value ;
        }
        else
        {
            
            Node *pointer_to_node = hashTable[hash_position];

            while (pointer_to_node->next != NULL && pointer_to_node -> next -> key != key )
            {
                pointer_to_node = pointer_to_node->next;
            }
            
            if (pointer_to_node->next == NULL )
            {
                pointer_to_node->next = newNode;
            }
           
            else if(pointer_to_node -> next -> key == key)
            {
                pointer_to_node->next->value = value ;
            }
        }
    }
    
    // method to delete key
    void deleteKey(int Key)
    {
        int hash_position = HashFunction(Key);

        if (hashTable[hash_position] != NULL)
        {
            Node *pointer_to_node = hashTable[hash_position];

            // Delete first node
            if (pointer_to_node->key == Key)
            {
                hashTable[hash_position] = pointer_to_node->next;
                return;
            }
            
            while (pointer_to_node->next->key != Key && pointer_to_node->next != NULL)
            {
                pointer_to_node = pointer_to_node->next;
            }

            
            if (pointer_to_node->next != NULL)
            {
                pointer_to_node->next = pointer_to_node->next->next;
                cout << "Operation Successful" << endl;
            }

            // No Node Present
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

        // Find node
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
                // Node not found

                pointer_to_node=pointer_to_node->next ;
            }
        }
        cout << "Key Not found" << endl;
    }

    //  Display Method
    void display()
    {
        
        for (int i = 0; i < CAPACITY; i++)
        {
           
            Node *pointer_to_node = hashTable[i];

            cout << "Position " << i << " is connected to the keys are : ";

           
            while (pointer_to_node != NULL)
            {
                cout << pointer_to_node->key << "->" << pointer_to_node->value << "  ";
                pointer_to_node = pointer_to_node->next;
            }

            cout << endl;
        }
    }

    
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
            }
            if (pointer_to_node->key == Key)
            {
                cout << "The Value of the Key " << Key << " is " << pointer_to_node->value << endl;
            }
            else
            {
                cout << "Key Not Found" << endl;
            }
        }
    }
}; 


int main(){
    
    int size;
    cout << "Enter the Capacity of the Hashmap " << endl;
    cin >> size;

    
    Hashmap mp(size);

    int choice = 0;
    while (choice != 6)
    {
        cout << "\n\nLinked List Menu\n";
        cout << "==============================\n";
        cout << "1. Insert the Key Value Pair in Hashmap" << endl;
        cout << "2. Delete the key from Hashmap" << endl;
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
            cout << "Enter the Key " << endl;
            cin >> key ;
            cout<<"Enter the value"<<endl;
            cin>>value;
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
            mp.getKey(key);
            break;
        }
        case 6:
            cout << "Exit" << endl;
            break;
        default:
            return 0;
        }
    }
    return 0;
}