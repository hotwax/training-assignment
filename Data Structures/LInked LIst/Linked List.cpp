#include <iostream>
using namespace std;

// Linked list node structure
struct Node {
    int data;
    Node* next;
};

// Function prototypes
void display(Node* head);
void insert(Node** head, int data);
void deleteNode(Node** head, int key);
Node* search(Node* head, int key);
void update(Node* head, int oldVal, int newVal);
void sort(Node* head);

int main() {
    Node* head = NULL;
    int choice, data, key, oldVal, newVal;

    do {
        // Display menu
        cout << "\n\nLinked List Menu\n";
        cout << "==============================\n";
        cout << "1. Display\n";
        cout << "2. Insert\n";
        cout << "3. Delete\n";
        cout << "4. Search\n";
        cout << "5. Update\n";
        cout << "6. Sort\n";
        cout << "7. Exit\n";
        cout << "Enter your choice: ";
        cin >> choice;

        switch(choice) {
            case 1:
                display(head);
                break;
            case 2:
                cout << "Enter data to be inserted: ";
                cin >> data;
                insert(&head, data);
                break;
            case 3:
                cout << "Enter key to be deleted: ";
                cin >> key;
                deleteNode(&head, key);
                break;
            case 4:
                cout << "Enter key to be searched: ";
                cin >> key;
                if (search(head, key) != NULL) {
                    cout << key << " found in the list.\n";
                } else {
                    cout << key << " not found in the list.\n";
                }
                break;
            case 5:
                cout << "Enter value to be updated: ";
                cin >> oldVal;
                cout << "Enter new value: ";
                cin >> newVal;
                update(head, oldVal, newVal);
                break;
            case 6:
                sort(head);
                cout << "List sorted in ascending order.\n";
                break;
            case 7:
                cout << "Exiting program...\n";
                break;
            default:
                cout << "Invalid choice. Please try again.\n";
        }
    } while(choice != 7);

    return 0;
}

// Function to display the linked list
void display(Node* head) {
    Node* temp = head;

    if (temp == NULL) {
        cout << "List is empty.\n";
        return;
    }

    cout << "Linked List: ";
    while (temp != NULL) {
        cout << temp->data << " ";
        temp = temp->next;
    }
    cout << endl;
}

// Function to insert a node at the end of the linked list
void insert(Node** head, int data) {
    Node* newNode = new Node;
    newNode->data = data;
    newNode->next = NULL;

    if (*head == NULL) {
        *head = newNode;
    } else {
        Node* temp = *head;
        while (temp->next != NULL) {
            temp = temp->next;
        }
        temp->next = newNode;
    }
    cout << data << " inserted into the list.\n";
}

// Function to delete a node with the given key from the linked list
void deleteNode(Node** head, int key) {
    Node* temp = *head;
    Node* prev = NULL;
    
    if (temp != NULL && temp->data == key) {
        *head = temp->next;
        delete temp;
        cout << key << " deleted from the list.\n";
        return;
    }

    while (temp != NULL && temp->data != key) {
        prev = temp;
        temp = temp->next;
    }

    if (temp == NULL) {
        cout << key << " not found in the list.\n";
        return;
    }

    prev->next = temp->next;
    delete temp;
    cout << key << " deleted from the list.\n";
}

// Function to search for a node with the given key in the linked list
Node* search(Node* head, int key) {
    Node* temp = head;

    while (temp != NULL) {
        if (temp->data == key) {
            return temp;
        }
        temp = temp->next;
    }
    return NULL;
}

// Function to update a node with the given key in the linked list
void update(Node* head, int key, int newValue) {
    Node* temp = head;
    bool found = false;

    while (temp != NULL) {
        if (temp->data == key) {
            temp->data = newValue;
            found = true;
            break;
        }
        temp = temp->next;
    }

    if (found) {
        cout << key << " updated to " << newValue << " in the list.\n";
    } else {
        cout << key << " not found in the list.\n";
    }
}

// Function to sort a linked list  
void sort(Node* head) {
    if (head == NULL || head->next == NULL) {
        return;
    }

    Node* i, *j, *minNode;
    for (i = head; i != NULL; i = i->next) {
        minNode = i;
        for (j = i->next; j != NULL; j = j->next) {
            if (j->data < minNode->data) {
                minNode = j;
            }
        }
        if (minNode != i) {
            int temp = i->data;
            i->data = minNode->data;
            minNode->data = temp;
        }
    }
}