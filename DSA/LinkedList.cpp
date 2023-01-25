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
    Node(int val)
    {
        data = val;
        next = NULL;
    }
};

// Create custom DataStructure LinkedList
class LinkedList
{
public:
     // Insert Node in the List 
    void insert(Node *&head, int val)
    {
        // Create new Node
        Node *newNode = new Node(val);
        if (!head)
        {
            head = newNode;
            return;
        }
        Node *temp = head;
        // go to the last Node
        while (temp->next != NULL)
        {
            temp = temp->next;
        }
        temp->next = newNode;
    }

    // Function to delete the Node By value
    void deleteNodeByValue(Node *&head, int val)
    {
        Node *temp = head;
        while (temp->next->data != val && temp != NULL)
        {
            if (temp == head)
            {
                head = temp->next;
                delete (temp);
                return;
            }
            temp = temp->next;
        }
        Node *n = temp->next;
        temp->next = temp->next->next;
        delete (n);
    }

    // Function to delete the Node By position or index
    void deleteNodeByPosition(Node *&head, int n)
    {
        Node *temp = head;
        if (n == 1)
        {
            head = temp->next;
            delete (temp);
            return;
        }
        for (int i = 1; i < n - 1; i++)
        {
            temp = temp->next;
        }
        Node *temp2 = temp->next;
        temp->next = temp2->next;
        delete (temp2);
    }

    // Function to Update the data of the Node
    void update(Node *&head, int pval, int nval)
    {
        Node *temp = head;
        while (temp->data != pval && temp != NULL)
        {
            temp = temp->next;
        }
        temp->data = nval;
    }
    Node *merge(Node *a, Node *b)
    {
        // base case
        if (a == NULL)
            return b;
        if (b == NULL)
            return a;

        // recursive case
        // take a head pointer
        Node *c;

        if (a->data < b->data)
        {
            c = a;
            c->next = merge(a->next, b);
        }
        else
        {
            c = b;
            c->next = merge(a, b->next);
        }

        return c;
    }

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
        Node *a = head;
        Node *b = mid->next;

        mid->next = NULL;

        // Step 2: recursively sort the smaller
        // linked lists
        a = merge_sort(a);
        b = merge_sort(b);

        // Step 3: merge the sorted linked lists
        Node *c = merge(a, b);

        return c;
    }

    // Function to Print the list 
    void print(Node *head)
    {
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
    LinkedList ll;
    while (1)
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
        int choice = 0;
        cout<<"Enter Your Choice"<<endl;
        cin >> choice;
        switch (choice)
        {
        case 1:
        {
            cout << "Enter the value to be inserted" << endl;
            int val;
            cin >> val;
            ll.insert(head, val);
            break;
        }

        case 2:
        {
            cout << "Delete Element by" << endl
                 << "1. position" << endl
                 << "2. Value" << endl;
            int ch = 0;
            cin >> ch;
            if (ch == 2)
            {
                cout << "Enter the Value of the Element to be deleted" << endl;
                int val1;
                cin >> val1;
                ll.deleteNodeByValue(head, val1);
            }
            else if (ch == 1)
            {
                cout << "Enter the Positon of the Element to be deleted" << endl;
                int pos;
                cin >> pos;
                ll.deleteNodeByPosition(head, pos);
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
            int p_val, n_val;
            cin >> p_val >> n_val;
            ll.update(head, p_val, n_val);
            break;
        }

        case 4:
        {
            cout << "Sort the List" << endl;
            Node *f = ll.merge_sort(head);
            head = f;
            break;
        }

        case 5:
        {
            ll.print(head);
            break;
        }

        case 6:
            break;
        }
    }
    return 0;
}
