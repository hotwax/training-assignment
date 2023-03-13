#include <bits/stdc++.h>
using namespace std;
using namespace std::chrono;

// hash Node Class with next pointer so it can act as a linked list

class HashNode
{
public:
    int value;
    int key;
    HashNode *next;

    HashNode(int key, int value)
    {
        this->key = key;
        this->value = value;
        this->next = NULL;
    }
};

// HashMap Class

class HashMap
{

    // Hash elements array

    HashNode **hashNodeArray;

    // hash capacity

    int capacity;

    // size
    int size;

    // counting collisions

    int collisions;

public:
    // Constructor
    HashMap(int capacity)
    {
        this->capacity = capacity;
        size = 0;
        collisions = 0;
        // creating hashNode array
        hashNodeArray = new HashNode *[capacity];

        // initializing all values with NULL

        for (int i = 0; i < capacity; i++)
        {
            hashNodeArray[i] = NULL;
        }
    }

    // print function

    void print(string message)
    {
        cout << endl
             << message;
    }

    // hash function to find index for a key

    int hashCode(int key)
    {
        return key % capacity;
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

        HashNode *newNode = new HashNode(key, value);
        // Generating HashIndex
        int hashIndex = hashCode(key);

        // finding the free space

        // Using Chaining collision resolution technique

        if (!hashNodeArray[hashIndex])
        {
            hashNodeArray[hashIndex] = newNode;
            size++;
            return;
        }
        else
        {
            collisions++;
            HashNode *tempNodeRef = hashNodeArray[hashIndex];

            // Traversing through the linkedlist

            while (tempNodeRef->next != NULL)
            {
                this->collisions++;
                if (tempNodeRef->key == key)
                {
                    tempNodeRef->value = value;
                    return;
                }
                tempNodeRef = tempNodeRef->next;
            }

            // checking if first node itself has the key

            if (tempNodeRef->key == key)
            {
                tempNodeRef->value = value;
                return;
            }
            size++;
            tempNodeRef->next = newNode;
        }
    }

    // function for insertion for use in Menu

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
        int hashIndex = hashCode(key);

        HashNode *tempNodeRef = hashNodeArray[hashIndex];
        HashNode *prev = NULL;

        // if it exists on the head

        if (tempNodeRef->key == key)
        {
            hashNodeArray[hashIndex] = tempNodeRef->next;
            int value = tempNodeRef->value;
            delete tempNodeRef;
            return value;
        }

        // if it does not exists on the head

        while (tempNodeRef != NULL)
        {
            if (tempNodeRef->key == key)
            {

                int value = tempNodeRef->value;
                prev->next = tempNodeRef->next;
                delete tempNodeRef;
                return value;
            }
            prev = tempNodeRef;
            tempNodeRef = tempNodeRef->next;
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
        int hashIndex = hashCode(key);

        // finding the key for value

        HashNode *tempNodeRef = hashNodeArray[hashIndex];

        while (tempNodeRef != NULL)
        {
            if (tempNodeRef->key == key)
            {
                return tempNodeRef->value;
            }
            tempNodeRef = tempNodeRef->next;
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

    // for displaying the map

    void display()
    {
        cout << endl;
        for (int i = 0; i < capacity; i++)
        {
            cout << i << " -- ";
            if (hashNodeArray[i] != NULL)
            {
                HashNode *tempNodeRef = hashNodeArray[i];

                while (tempNodeRef)
                {
                    cout << "[" << tempNodeRef->key << "," << tempNodeRef->value << "]";
                    tempNodeRef = tempNodeRef->next;

                    if (tempNodeRef)
                    {
                        cout << " -> ";
                    }
                }
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

    // Takin the capacity from the user for the hashMap and iniatilizing hasmap
    // object from HashMap class

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
        print("Enter 4 for Get Value From Map operation");

        print("Enter 5 to exit");
        cout << endl;
        int input;
        cin >> input;
        int data;

        // on different input values

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
}