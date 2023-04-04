#include <iostream>
#include <cstdlib>
#include <string>
#include <cstdio>

using namespace std;

const int TABLE_SIZE = 200;

class HashTableEntry {
public:
    int key;
    int value;
    HashTableEntry(int key, int value) {
        this->key = key;
        this->value = value;
    }
};

class HashMapTable {
private:
    HashTableEntry **table;
public:
    HashMapTable() {
        table = new HashTableEntry*[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            table[i] = NULL;
        }
    }

    int hashFunc(int key) {
        return key % TABLE_SIZE;
    }

    void insert(int key, int value) {
        int hash = hashFunc(key);
        while (table[hash] != NULL && table[hash]->key != key) {
            hash = hashFunc(hash + 1);
        }
        if (table[hash] != NULL)
            delete table[hash];
        table[hash] = new HashTableEntry(key, value);
    }

    int getValue(int key) {
        int hash = hashFunc(key);
        while (table[hash] != NULL && table[hash]->key != key) {
            hash = hashFunc(hash + 1);
        }
        if (table[hash] == NULL)
            return -1;
        else
            return table[hash]->value;
    }

    void remove(int key) {
        int hash = hashFunc(key);
        while (table[hash] != NULL) {
            if (table[hash]->key == key)
                break;
            hash = hashFunc(hash + 1);
        }
        if (table[hash] == NULL) {
            cout << "No element found at key " << key << endl;
            return;
        } else {
            delete table[hash];
        }
        cout << "Element Deleted" << endl;
    }

    ~HashMapTable() {
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (table[i] != NULL)
                delete table[i];
        }
        delete[] table;
    }
};

int main() {
    HashMapTable hashMap;

    int key, value, choice;
    while (1) {
        cout << "1.Insert element into the table" << endl;
        cout << "2.Search element from the key" << endl;
        cout << "3.Delete element at a key" << endl;
        cout << "4.Get value at a key" << endl;
        cout << "5.Exit" << endl;
        cout << "Enter your choice: ";
        cin >> choice;

        switch (choice) {
        case 1:
            cout << "Enter element to be inserted: ";
            cin >> value;
            cout << "Enter key at which element to be inserted: ";
            cin >> key;
            hashMap.insert(key, value);
            break;

        case 2:
            cout << "Enter key of the element to be searched: ";
            cin >> key;
            value = hashMap.getValue(key);
            if (value == -1) {
                cout << "No element found at key " << key << endl;
            } else {
                cout << "Value at key " << key << " : " << value << endl;
            }
            break;

        case 3:
            cout << "Enter key of the element to be deleted: ";
            cin >> key;
            hashMap.remove(key);
            break;

        case 4:
            cout << "Enter key of the element to get value: ";
            cin >> key;
            value = hashMap.getValue(key);
            if (value == -1) {
                cout << "No element found at key " << key << endl;
            } else {
                cout << "Value at key " << key << " : " << value << endl;
            }
            break;

        case 5:
            exit(1);

        default:
            cout << "\nEnter correct option\n";
        }
    }

    return 0;
}

