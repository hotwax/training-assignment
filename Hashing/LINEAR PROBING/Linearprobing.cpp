#include <iostream>
using namespace std;

// Define the size of the hash table and the maximum number of values it can hold
const int TABLE_SIZE = 10;
const int MAX_VALUES = 10;

// Define the structure for each element in the hash table
struct HashElement {
    int key;
    int value;
};

// Define the hash table
HashElement hashTable[TABLE_SIZE];

// Define the hash function
int hashFunction(int key) {
    return key % TABLE_SIZE;
}

// Define the function to insert a value into the hash table
void insertValue() {
    int key, value, hash, i, collisions = 0;

    // Check if the hash table is full
    for (i = 0; i < MAX_VALUES; i++) {
        if (hashTable[i].key == -1) {
            break;
        }
    }
    if (i == MAX_VALUES) {
        cout << "Hash table is full." << endl;
        return;
    }

    // Get the key and value from the user
    cout << "Enter key and value: ";
    cin >> key >> value;

    // Calculate the hash
    hash = hashFunction(key);

    // Linear probing
    while (hashTable[hash].key != -1) {
        hash = (hash + 1) % TABLE_SIZE;
        collisions++;
    }

    // Insert the value into the hash table
    hashTable[hash].key = key;
    hashTable[hash].value = value;

    cout << "Value inserted successfully." << endl;
    cout << "Number of collisions: " << collisions << endl;
}

// Define the function to search for a value in the hash table
void searchValue() {
    int key, hash, i;

    // Get the key from the user
    cout << "Enter key to search: ";
    cin >> key;

    // Calculate the hash
    hash = hashFunction(key);

    // Linear probing
    i = 0;
    while (hashTable[hash].key != key && i < TABLE_SIZE) {
        hash = (hash + 1) % TABLE_SIZE;
        i++;
    }

    // Check if the value was found
    if (hashTable[hash].key == key) {
        cout << "Value found: " << hashTable[hash].value << endl;
    } else {
        cout << "Value not found." << endl;
    }
}

// Define the function to display the hash table
void displayTable() {
    cout << "Hash table:" << endl;
    for (int i = 0; i < TABLE_SIZE; i++) {
        if (hashTable[i].key != -1) {
            cout << "Key: " << hashTable[i].key << ", Value: " << hashTable[i].value << endl;
        }
    }
}

// Define the function to get the collision count
void getCollisionCount() {
    int collisions = 0;
    for (int i = 0; i < TABLE_SIZE; i++) {
        if (hashTable[i].key != -1) {
            int hash = hashFunction(hashTable[i].key);
            if (hash != i) {
                collisions++;
            }
        }
    }
    cout << "Number of collisions: " << collisions << endl;
}


int main() {
    int choice;

    // Initialize the hash table
    for (int i = 0; i < TABLE_SIZE; i++) {
        hashTable[i].key = -1;
    }

    // Display the menu
    while (true) {
        cout << "1. Insert a value into the hash table" << endl;
        cout << "2. Search for a value in the hash table" << endl;
        cout << "3. Display the hash table" << endl;
        cout << "4. Get the collision count" << endl;
        cout << "5. Exit" << endl;
        cout << "Enter your choice: ";
        cin >> choice;

        switch (choice) {
            case 1:
                insertValue();
                break;
            case 2:
                searchValue();
                break;
            case 3:
                displayTable();
                break;
            case 4:
                getCollisionCount();
                break;
            case 5:
                return 0;
            default:
                cout << "Invalid choice. Please try again." << endl;
                break;
        }

        cout << endl;
    }

    return 0;
} 
