#include <bits/stdc++.h>
using namespace std;
using namespace std::chrono;
// Random Probing
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

        if (this->size >= capacity)
        {
            print("HashMap is Full");
            return;
        }

        HashNode *temp = new HashNode(key, value);

        // Generating HashIndex with a Random value

        int hashIndex = hashCode(rand());

        // finding the free space

        // Using Random Probing

        while (arr[hashIndex] != NULL && arr[hashIndex]->key != key)
        {
            this->collisions++;
            // incrementing hashIndex and again making a hashcode for indexing
            hashIndex++;
            hashIndex %= capacity;
        }

        if (arr[hashIndex] == NULL)
        {
            // increasing the size of HashMap
            size++;
            arr[hashIndex] = temp;
        }

        // if already exit update it

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
        int hashIndex = 0;

        // finding the node

        while (hashIndex < this->capacity)
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

                hashIndex++;
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
    }

    // void get value

    void getValue()
    {
        // taking Input from the user

        int key;
        print("Enter the key to find the value: ");
        cin >> key;
        print("Value is : ");

        // Traversing through all the array to find the value

        for (int i = 0; i < capacity; i++)
        {
            if (arr[i] && arr[i]->key == key)
            {
                cout << arr[i]->value;
                return;
            }
        }
        cout << endl
             << "Key Not found.";
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
}