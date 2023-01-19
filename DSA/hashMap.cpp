#include <bits/stdc++.h>
using namespace std;

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

    // Dummy Node

    HashNode *dummy;

public:
    // Constructor
    HashMap()
    {
        capacity = 20;
        size = 0;

        // creating hashNode array
        arr = new HashNode *[capacity];

        // initializing all values with NULL

        for (int i = 0; i < capacity; i++)
        {
            arr[i] = NULL;
        }

        // dummy node

        dummy = new HashNode(-1, -1);
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
        HashNode *temp = new HashNode(key, value);

        int hashIndex = hashCode(key);

        // finding the free space

        while (arr[hashIndex] != NULL && arr[hashIndex]->key != key)
        {

            // incrementing hashIndex and again making a hashcode for indexing
            hashIndex++;
            hashIndex %= capacity;
        }

        if (arr[hashIndex] == NULL)
        {
            // increasing the size of HashMap
            size++;
        }

        arr[hashIndex] = temp;
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
        int hashIndex = hashCode(key);

        // finding the node

        while (arr[hashIndex] != NULL)
        {
            if (arr[hashIndex]->key == key)
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
                hashIndex %= capacity;
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
        int hashIndex = hashCode(key);
        // checking if loop does not exeed the size
        int count = 0;

        // finding the key for value

        while (arr[hashIndex] != NULL && count < capacity)
        {
            if (arr[hashIndex]->key == key)
            {
                return arr[hashIndex]->value;
            }

            hashIndex++;
            hashIndex %= capacity;
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

            if (arr[i] != NULL)
            {
                cout << arr[i]->key << " -> " << arr[i]->value;
                cout << endl;
            }
        }
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

    HashMap map;

    // while for menu --

    while (true)
    {
        print("Your Hash Map -- ");
        map.display();

        cout << endl;

        print("Enter 1 for Add Key Value operation");
        print("Enter 2 for Delete with key operation");
        print("Enter 3 for Size of Map operation");

        print("Enter 4 to exit");
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

        default:
            return 0;
        }
    }
}