#include <iostream>
#include <cstdlib> // for rand() and srand()
#include <ctime> // for time()
using namespace std;

#define MAX 10

class RandomProbingHash {
    int table[MAX];
    int size;

public:
    RandomProbingHash() {
        for(int i = 0; i < MAX; i++)
            table[i] = -1;
        size = 0;
        srand(time(NULL)); // initialize random seed
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
            int randomOffset;

            do {
                randomOffset = rand() % MAX; // generate random number from 0 to MAX-1
            } while(randomOffset == 0); // make sure offset is not 0

            int newIndex = (index + i*randomOffset) % MAX;

            while(table[newIndex] != -1 && newIndex != index) {
                i++;
                newIndex = (index + i*randomOffset) % MAX;
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
            int randomOffset;

            do {
                randomOffset = rand() % MAX; // generate random number from 0 to MAX-1
            } while(randomOffset == 0); // make sure offset is not 0

            int newIndex = (index + i*randomOffset) % MAX;

            while(table[newIndex] != key && newIndex != index) {
                i++;
                newIndex = (index + i*randomOffset) % MAX;
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
    RandomProbingHash rph;

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
                rph.insert(key);
                break;
            case 2:
                cout << "Enter the key to remove: ";
                cin >> key;
                rph.remove(key);
                break;
            case 3:
                rph.display();
                break;
            case 4:
                break;
            default:
                cout << "Invalid choice!" << endl;
        }
    } while(choice != 4);

    return 0;
}

 
