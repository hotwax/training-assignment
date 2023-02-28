#include <iostream>
using namespace std;

#define MAX 10

class DoubleHash {
    int table[MAX];
    int size;

public:
    DoubleHash() {
        for(int i = 0; i < MAX; i++)
            table[i] = -1;
        size = 0;
    }

    int hash1(int key) {
        return key % MAX;
    }

    int hash2(int key) {
        return 7 - (key % 7);
    }

    void insert(int key) {
        if(size == MAX) {
            cout << "Table is full!" << endl;
            return;
        }

        int index = hash1(key);

        if(table[index] == -1) {
            table[index] = key;
            size++;
        } else {
            int i = 1;
            int newIndex;

            while(true) {
                newIndex = (index + i*hash2(key)) % MAX;

                if(table[newIndex] == -1) {
                    table[newIndex] = key;
                    size++;
                    break;
                }

                i++;

                if(i == MAX) {
                    cout << "Key cannot be inserted!" << endl;
                    return;
                }
            }
        }
    }

    void remove(int key) {
        int index = hash1(key);

        if(table[index] == key) {
            table[index] = -1;
            size--;
        } else {
            int i = 1;
            int newIndex;

            while(true) {
                newIndex = (index + i*hash2(key)) % MAX;

                if(table[newIndex] == key) {
                    table[newIndex] = -1;
                    size--;
                    break;
                }

                i++;

                if(i == MAX) {
                    cout << "Key not found!" << endl;
                    return;
                }
            }
        }
    }

    void display() {
        for(int i = 0; i < MAX; i++) {
            if(table[i] != -1)
                cout << i << ": " << table[i] << endl;
            else
                cout << i << ": " << endl;
        }
    }
};

int main() {
    DoubleHash dh;

    int choice, key;

    do {
        cout << "1. Insert\n";
        cout << "2. Remove\n";
        cout << "3. Display\n";
        cout << "4. Exit\n";
        cout << "Enter your choice: ";
        cin >> choice;

        switch(choice) {
            case 1:
                cout << "Enter the key to insert: ";
                cin >> key;
                dh.insert(key);
                break;
            case 2:
                cout << "Enter the key to remove: ";
                cin >> key;
                dh.remove(key);
                break;
            case 3:
                dh.display();
                break;
            case 4:
                break;
            default:
                cout << "Invalid choice!" << endl;
        }
    } while(choice != 4);

    return 0;
}
 
