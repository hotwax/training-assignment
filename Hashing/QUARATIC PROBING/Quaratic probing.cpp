#include <iostream>
using namespace std;

#define MAX 10

class QuadraticProbingHash {
    int table[MAX];
    int size;

public:
    QuadraticProbingHash() {
        for(int i = 0; i < MAX; i++)
            table[i] = -1;
        size = 0;
    }

    int hash(int key) {
        return key % MAX;
    }

    void insert(int key) {
        if(size == MAX) {
            cout << "Table is full!" << endl;
            return;
        }

        int index = hash(key);

        if(table[index] == -1) {
            table[index] = key;
            size++;
        } else {
            int i = 1;
            int newIndex = (index + i*i) % MAX;

            while(table[newIndex] != -1 && newIndex != index) {
                i++;
                newIndex = (index + i*i) % MAX;
            }

            if(newIndex == index) {
                cout << "Key cannot be inserted!" << endl;
                return;
            }

            table[newIndex] = key;
            size++;
        }
    }

    void remove(int key) {
        int index = hash(key);

        if(table[index] == key) {
            table[index] = -1;
            size--;
        } else {
            int i = 1;
            int newIndex = (index + i*i) % MAX;

            while(table[newIndex] != key && newIndex != index) {
                i++;
                newIndex = (index + i*i) % MAX;
            }

            if(newIndex == index) {
                cout << "Key not found!" << endl;
                return;
            }

            table[newIndex] = -1;
            size--;
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
    QuadraticProbingHash qph;

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
                qph.insert(key);
                break;
            case 2:
                cout << "Enter the key to remove: ";
                cin >> key;
                qph.remove(key);
                break;
            case 3:
                qph.display();
                break;
            case 4:
                break;
            default:
                cout << "Invalid choice!" << endl;
        }
    } while(choice != 4);

    return 0;
}
 
