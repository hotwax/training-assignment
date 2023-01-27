#include <iostream>
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
class LinkedList
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
        Node *nodePtr = head;
        // go to the last Node
        while (nodePtr->next != NULL)
        {
            nodePtr = nodePtr->next;
        }
        nodePtr->next = newNode;
    }

    // Function to delete the Node By value
    void deleteNodeByValue(Node *&head, int value)
    {
        Node *nodePtr= head;
        while (nodePtr->next->data != value && nodePtr != NULL)
        {
            // if first node has to be the node which we want to delete
            if (nodePtr == head)
            {
                head = nodePtr->next;
                delete (nodePtr);
                return;
            }
            nodePtr = nodePtr->next;
        }
        // delete the node
        Node * to_delete_node = nodePtr->next;
        nodePtr->next = nodePtr->next->next;
        delete (to_delete_node);
    }

    // Function to delete the Node By position or index
    void deleteNodeByPosition(Node *&head, int position)
    {
        Node *nodePtr = head;
        if (position == 1)
        {
            head = nodePtr->next;
            delete (nodePtr);
            return;
        }
        // i = index , to Iterate
        for (int i = 1; i < n - 1; i++)
        {
            nodePtr = nodePtr->next;
        }
        Node * to_delete_node = nodePtr->next;
        nodePtr->next = nodePtr->next;
        delete (to_delete_node);
    }

    // Function to Update the data of the Node
    void update(Node *&head, int previous_val, int new_val)
    {
        Node * nodePtr = head;
        while (nodePtr->data != previous_val && nodePtr != NULL)
        {
            nodePtr = nodePtr->next;
        }
        nodePtr->data = new_val;
    }
    
    // Merge the Lists 
    Node *merge(Node * firstHalf , Node *secondHalf)
    {
        // base case
        if (firstHalf == NULL)
            return secondHalf;
        if (secondHalf == NULL)
            return firstHalf ;

        // recursive case
        // take a head pointer
        Node * mergedList;

        if (firstHalf->data < secondHalf ->data)
        {
            mergedList = firstHalf;
            mergedList->next = merge(firstHalf->next, secondHalf);
        }
        else
        {
            mergedList = secondHalf;
            mergedList->next = merge(firstHalf, secondHalf->next);
        }

        return mergedList;
    }
    
    // find Mid Point
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
        Node *firstHalf = head;
        Node *secondHalf = mid->next;

        mid->next = NULL;

        // Step 2: recursively sort the smaller
        // linked lists
        firstHalf = merge_sort(firstHalf);
        secondHalf = merge_sort(secondHalf);

        // Step 3: merge the sorted linked lists
        Node *mergedList = merge(firstHalf, secondHalf);

        return mergedList;
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
            Node *sortedList = ll.merge_sort(head);
            head = sortedList;
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
