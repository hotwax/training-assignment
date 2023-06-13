#include <iostream>
#include <cassert>

using namespace std;

class Node {
public:
    int data;
    Node* next;
};

class LinkedList {
public:
    Node* head;

    LinkedList() {
        head = NULL;
    }

    // Function to insert a new node at the beginning of the list
    void insert(int new_data) {
        Node* new_node = new Node();
        new_node->data = new_data;
        new_node->next = NULL;
        if(head==NULL)head= new_node;
        else{
            Node* temp=head;
            while(temp->next!=NULL)temp=temp->next;
            temp->next=new_node;
        }
    }

    // Function to update the value of a node at a given position in the list
    void update(int position, int new_data) {
        Node* temp = head;
        for (int i = 1; i < position && temp != NULL; i++) {
            temp = temp->next;
        }
        if (temp != NULL) {
            temp->data = new_data;
        } else {
            cout << "Invalid position" << endl;
        }
    }

    // Function to delete a node at a given position in the list
    void remove(int position) {
        if (head == NULL) {
            cout << "List is empty" << endl;
            return;
        }

        Node* temp = head;
        if (position == 0) {
            head = temp->next;
            delete temp;
            return;
        }

        for (int i = 1; temp != NULL && i < position-1; i++) {
            temp = temp->next;
        }

        if (temp == NULL || temp->next == NULL) {
            cout << "Invalid position" << endl;
            return;
        }

        Node* next_node = temp->next->next;
        delete temp->next;
        temp->next = next_node;
    }

    // Function to search for a node with a given value in the list
    Node* search(int value) {
        Node* temp = head;
        while (temp != NULL) {
            if (temp->data == value) {
                return temp;
            }
            temp = temp->next;
        }
        return NULL;
    }

    // Function to sort the list in ascending order
    void sort() {
        Node* i, *j;
        int temp_data;
        for (i = head; i != NULL; i = i->next) {
            for (j = i->next; j != NULL; j = j->next) {
                if (i->data > j->data) {
                    temp_data = i->data;
                    i->data = j->data;
                    j->data = temp_data;
                }
            }
        }
    }

    // Function to display the list
    void display() {
        Node* temp = head;
        while (temp != NULL) {
            cout << temp->data << " ";
            temp = temp->next;
        }
        cout << endl;
    }
};

int main() {
    LinkedList list;

    // Test empty list
    assert(list.head == NULL);

    // Test insert
    list.insert(1);
    assert(list.head->data == 1);
    list.insert(2);
    assert(list.head->next->data == 2);
    cout<<"p2"<<endl;

    // Test update
    list.update(1, 3);
    assert(list.head->data == 3);cout<<"p3"<<endl;
    list.update(2, 4); // Invalid position
    assert(list.head->next->data == 4);
    //list.display();

    // Test remove
    list.remove(0);
    assert(list.head->data == 4);
    list.remove(1); // Invalid positioncout<<"p1"<<endl;
    assert(list.head->data == 4);

    // Test search
    list.insert(4);
    list.insert(5);
    assert(list.search(4)->data == 4);
    assert(list.search(6) == NULL);

    // Test sort
    list.insert(1);
    list.insert(3);
    list.sort();
    list.display();
    assert(list.head->data == 1);
    assert(list.head->next->data == 3);
    assert(list.head->next->next->data == 4);
    assert(list.head->next->next->next->next->data == 5);

    // Test display
    cout << "List contents: ";
    list.display();
    cout<<"All test cases passed";
    return 0;
}