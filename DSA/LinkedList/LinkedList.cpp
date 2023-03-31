#include <iostream>
#include <bits/stdc++.h>
using namespace std;

// Create Node which has two fields data and next
// Data field = store data or value 
// Node field = store the address of the next node
class Node
{
public:
    int data;
    Node *next;
    Node(int value)
    {
        data = value;
        next = NULL;
    }
};

// Create custom DataStructure LinkedList
class linkedList
{
public:
     // Insert Node in the List 
    void insert(Node *&head, int value)
    {
        // Create new Node
        Node *newNode = new Node(value);
        if (!head)
        {
            head = newNode;
            return;
        }
        Node *node_pointer = head;
        // go to the last Node
        while (node_pointer->next != NULL)
        {
            node_pointer = node_pointer -> next;
        }
        node_pointer->next = newNode;
    }

    // Function to delete the Node By value
    void deleteNodeByValue(Node *&head, int value)
    {
        if(head==NULL)
        {
            cout<<"List is Empty"<<endl;
            return ;
        }
        Node * node_pointer = head;

        if (node_pointer->data == value)
        {
            head = node_pointer->next;
            delete (node_pointer);
            return;
        }
        while (node_pointer->next != NULL && node_pointer->next->data != value)
        {
            node_pointer = node_pointer->next;
        }
        if(node_pointer->next != NULL && node_pointer->next->data == value)
        {
            Node * to_delete_node = node_pointer->next;
            node_pointer->next = node_pointer->next->next;
            delete (to_delete_node);
            cout<<"Operation Successful"<<endl;
        }
        else
        {
            cout<<"Node Not present "<<endl;
        }

    }
    int list_Length(Node * head)
    {
        int size = 0 ;
        while(head!=NULL)
        {
            head=head->next ;
            size ++ ;
        }
        return size ;
    }

    // Function to delete the Node By position or index
    void deleteNodeByPosition(Node *&head, int position)
    {
        // If there is no node in list
        if(head==NULL)
        {
            cout<<"list is Empty"<<endl;
            return ;
        }
        // if given position larger than list length 
        if(position>list_Length(head))
        {
            cout<<"Invalid Position"<<endl;
            return ;
        }
        Node *node_pointer = head;
        if (position == 1)
        {
            head = node_pointer->next;
            delete (node_pointer);
            cout<<"Operation Successful"<<endl;
            return;
        }
        // find position
        for (int index = 1; index < position-1 ; index++)
        {
            node_pointer = node_pointer->next;
        }
        Node * to_delete_node = node_pointer->next;
        node_pointer->next = node_pointer->next->next;
        delete (to_delete_node);
        cout<<"Operation Successful"<<endl;
    }

    // Function to Update the data of the Node
    void update(Node *&head, int previous_value, int new_value )
    {
        if(head==NULL)
        {
            cout<<"List is Empty"<<endl;
            return ;
        }
        Node * node_pointer = head ;
        // Find Node
        while (node_pointer != NULL && node_pointer->data != previous_value)
        {
            node_pointer = node_pointer->next;
        }
        if(node_pointer == NULL)
        cout<<"Node not Found"<<endl;
        else if (node_pointer->data == previous_value)
        {
            node_pointer->data = new_value;
            cout<<"Operation Successful"<<endl;

        }

        return ;

    }
    // Merge the lists 
    Node *merge(Node * first_half , Node *second_half)
    {
        // base case
        if (first_half == NULL)
            return second_half;
        if (second_half == NULL)
            return first_half;

        // recursive case
        // take a head pointer
        Node *merged_list;

        if (first_half->data < second_half->data)
        {
            merged_list = first_half;
            merged_list->next = merge(first_half->next, second_half);
        }
        else
        {
            merged_list = second_half;
            merged_list->next = merge(first_half, second_half->next);
        }

        return merged_list;
    }

    // find mid point of list
    Node *mid_point(Node *head)
    {
        if (head == NULL || head->next == NULL)
            return head;

        Node *fast = head;
        Node *slow = head;

        while (fast != NULL && fast->next != NULL)
        {
            fast = fast->next;

            if (fast->next == NULL)
                break;

            fast = fast->next;
            slow = slow->next;
        }

        return slow;
    }

    // Function to Sort the List

    Node *merge_sort(Node *head)
    {
        // base case
        if (head == NULL || head->next == NULL)
            return head;

        // recursive case
        // Step 1: divide the linked list into
        // two equal linked lists
        Node *mid = mid_point(head);
        Node *first_half = head;
        Node *second_half = mid->next;

        mid->next = NULL;

        // Step 2: recursively sort the smaller
        // linked lists
        first_half = merge_sort(first_half);
        second_half = merge_sort(second_half);

        // Step 3: merge the sorted linked lists
        Node *merged_list = merge(first_half, second_half);

        return merged_list;
    }

    // Function to Print the list 
    void print(Node *head)
    {
        if(head==NULL)
        {
            cout<<"List Empty"<<endl;
            return ;
        }
        while (head->next != NULL)
        {
            cout << head->data << "->";
            head = head->next;
        }
        cout<<head->data;
        cout << endl;
    }
};
int main()
{
    Node *head = NULL;
    linkedList ll;
    int choice = 0;
    while (true)
    {
        // Menu
        cout << "Linked List" << endl
             << "Select Option" << endl;
        cout << "1. Add Element" << endl
             << "2. Delete Element" << endl
             << "3. Update List " << endl
             << "4. Sort the List" << endl
             << "5. Print List" << endl
             << "6. Exit" << endl;
        
        cout<<"Enter Your Choice"<<endl;
        cin >> choice;
        switch (choice)
        {
        case 1:
        {
            cout << "Enter the value to be inserted" << endl;
            int value;
            cin >> value;
            ll.insert(head, value);
            break;
        }

        case 2:
        {
            cout << "Delete Element by" << endl
                 << "1. position" << endl
                 << "2. Value" << endl;
            int choice1 = 0;
            cin >> choice1;
            if (choice1 == 2)
            {
                cout << "Enter the Value of the Element to be deleted" << endl;
                int value1;
                cin >> value1;
                ll.deleteNodeByValue(head, value1);
            }
            else if (choice1 == 1)
            {
                cout << "Enter the Positon of the Element to be deleted" << endl;
                int position;
                cin >> position;
                ll.deleteNodeByPosition(head, position);
            }
            else
            {
                cout << "invalid Choice" << endl;
            }
            break;
        }

        case 3:
        {
            cout << "Enter previous Value and New value to update the list ( Note : values should be separated by space ) " << endl;
            int previous_value, new_value;
            cin >> previous_value >> new_value;
            ll.update(head, previous_value, new_value);
            break;
        }

        case 4:
        {

            cout << "Sort the List" << endl;
            if(head==NULL)
            {
                cout<<"List Is Empty"<<endl;
                break ;
            }
            Node * node_pointer = ll.merge_sort(head);
            head  = node_pointer ;
            cout<<"Listed Sorted SuccessFully"<<endl;
            ll.print(head);
            break;
        }

        case 5:
        {
            ll.print(head);
            break;
        }
        case 6 :
            cout<<"EXIT"<<endl;
            return 0 ;
        default:
            cout<<"Invalid Choice"<<endl;
            break;
        }
    }
}
