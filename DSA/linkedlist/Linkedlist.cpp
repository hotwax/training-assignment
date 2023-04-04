#include <iostream>
using namespace std;

// Node class
class Node {
public:
    int data;
    Node* next;
    Node(int data) {
        this->data = data;
        this->next = NULL;
    }
};

// Linked list class
class LinkedList {
private:
    Node* head;
public:
    LinkedList() {
        head = NULL;
    }

    // Add node to the end of the list
    void add(int data) {
        Node* newNode = new Node(data);
        if (head == NULL) {
            head = newNode;
            return;
        }
        Node* current = head;
        while (current->next != NULL) {
            current = current->next;
        }
        current->next = newNode;
    }

    // Delete node at given index
    void remove(int index) {
        if (head == NULL) {
            cout << "List is empty" << endl;
            return;
        }
        Node* current = head;
        if (index == 0) {
            head = current->next;
            delete current;
            return;
        }
        for (int i = 0; current != NULL && i < index-1; i++) {
            current = current->next;
        }
        if (current == NULL || current->next == NULL) {
            cout << "Index out of range" << endl;
            return;
        }
        Node* next = current->next->next;
        delete current->next;
        current->next = next;
    }

    // Update value of node at given index
    void update(int index, int data) {
        if (head == NULL) {
            cout << "List is empty" << endl;
            return;
        }
        Node* current = head;
        for (int i = 0; current != NULL && i < index; i++) {
            current = current->next;
        }
        if (current == NULL) {
            cout << "Index out of range" << endl;
            return;
        }
        current->data = data;
    }

    // Sort the linked list in ascending order
    void sort() {
        if (head == NULL) {
            return;
        }
        bool swapped;
        Node* ptr1;
        Node* lptr = NULL;
        do {
            swapped = false;
            ptr1 = head;
            while (ptr1->next != lptr) {
                if (ptr1->data > ptr1->next->data) {
                    swap(ptr1->data, ptr1->next->data);
                    swapped = true;
                }
                ptr1 = ptr1->next;
            }
            lptr = ptr1;
        } while (swapped);
    }

    // Search for node with given data and return its index
    int search(int data) {
        if (head == NULL) {
            cout << "List is empty" << endl;
            return -1;
        }
        Node* current = head;
        int index = 0;
        while (current != NULL) {
            if (current->data == data) {
                return index;
            }
            current = current->next;
            index++;
        }
        cout << "Data not found" << endl;
        return -1;
    }

    // Print the linked list
    void print() {
        Node* current = head;
        while (current != NULL) {
            cout << current->data << " ";
            current = current->next;
        }
        cout << endl;
    }
};

int main() {
    LinkedList list;
    int choice, data, index;

    do {
        cout << "\nChoose an option:\n";
        cout << "1. Add a node\n";
        cout << "2. Remove a node\n";
        cout << "3. Update a node\n";
        cout << "4. Sort the list\n";
        cout << "5. Search for a node\n";
        cout << "6. Print the list\n";
        cout << "7. Exit\n";
        cin >> choice;

        switch (choice) {
            case 1:
                cout << "Enter data to add: ";
                cin >> data;
                list.add(data);
                break;
            case 2:
                cout << "Enter index to remove: ";
                cin >> index;
                list.remove(index);
                break;
            case 3:
                cout << "Enter index to update: ";
                cin >> index;
                cout << "Enter new data: ";
                cin >> data;
                list.update(index, data);
                break;
            case 4:
                list.sort();
                cout << "List sorted in ascending order" << endl;
                break;
            case 5:
                cout << "Enter data to search: ";
                cin >> data;
                index = list.search(data);
                if (index != -1) {
                    cout << "Data found at index " << index << endl;
                }
                break;
            case 6:
                list.print();
                break;
            case 7:
                cout << "Exiting program" << endl;
                break;
            default:
                cout << "Invalid choice, please try again" << endl;
                break;
        }
    } while (choice != 7);

    return 0;
}


