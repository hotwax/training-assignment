#include <bits/stdc++.h>
using namespace std;

// Class for linked list node

class Node
{
public:
    int data;
    Node *next;

    Node(int data)
    {
        this->data = data;
        next = NULL;
    }
};

// A simple print function

void print(string message)
{
    cout << endl
         << message;
}

// print linked list

void printLinkedList(Node *head)
{
    Node *temp = head;

    while (temp)
    {
        cout << temp->data << " ";
        temp = temp->next;
    }
}

// taking data from user

int takeData()
{
    // Taking input from user

    print("Enter the data:");
    int data;
    cin >> data;
    return data;
}

// Insertion at head

void insertToHead(Node *&head, Node *&tail, int data)
{

    // Creating a new Node

    Node *newNode = new Node(data);

    if (!head)
    {
        head = newNode;
        tail = head;
        return;
    }

    // Referencing the head to newNode's next pointer

    newNode->next = head;

    //  newNode becomes new head

    head = newNode;
}

// Insertion at tail

void insertAtTail(Node *&head, Node *&tail, int data)
{

    // Creating a new Node

    Node *newNode = new Node(data);

    // Checking whether tail exist or not

    if (!tail)
    {
        head = newNode;
        tail = head;
        return;
    }

    tail->next = newNode;
    tail = newNode;
}

// Insertion At Position

void insertAtPos(Node *&head, Node *&tail)
{

    // Taking Input

    print("Enter the position: ");
    int pos;
    cin >> pos;
    print("Enter the data: ");
    int data;
    cin >> data;

    // if position is 1

    if (pos == 1)
    {
        insertToHead(head, tail, data);
        return;
    }

    // Creating new Node

    Node *newNode = new Node(data);

    int count = 1;

    Node *temp = head;
    Node *prev = NULL;

    while (temp != NULL && count < pos)
    {
        prev = temp;
        temp = temp->next;
        count++;
    }

    if (temp == NULL)
    {
        print("No such position exists.");
    }
    else
    {
        prev->next = newNode;
        newNode->next = temp;
    }
}

void deleteByKey(Node *&head, Node *&tail)
{

    // Taking input from the user

    print("Enter the key: ");
    int toDelete;
    cin >> toDelete;

    Node *temp = head;
    Node *prev = NULL;

    while (temp != NULL && temp->data != toDelete)
    {
        prev = temp;
        temp = temp->next;
    }

    if (temp == head)
    {
        head = temp->next;
        delete temp;
    }
    else if (temp == tail)
    {
        prev->next = NULL;
        delete temp;
    }
    else
    {
        prev->next = temp->next;
        delete temp;
    }
}

// Delete Node from head

void deleteFromHead(Node *&head, Node *&tail)
{

    // Head does not exist

    if (head == NULL)
    {
        print("List is already empty.");
        return;
    }

    // if head is tail

    Node *temp = head;

    if (head == tail)
    {
        head = NULL;
        tail = NULL;
        delete temp;
    }
    else
    {
        // just deleting the head

        head = temp->next;
        delete temp;
    }
}

// delete from tail

void deleteFromTail(Node *&head, Node *&tail)
{
    // if tail is NULL

    if (!tail)
    {
        print("Tail does not exist.");
        return;
    }

    // if tail == head

    if (tail == head)
    {
        head = NULL;
        tail = NULL;
        return;
    }

    // if exists

    Node *prev = head;

    while (prev->next != tail)
    {
        prev = prev->next;
    }

    delete tail;

    tail = prev;
}

// delete Node with position

void deleteBypos(Node *&head, Node *&tail)
{

    // Taking input from user

    int pos;
    print("Enter the position: ");
    cin >> pos;

    // if position is 1

    if (pos == 1)
    {
        deleteFromHead(head, tail);
        return;
    }

    int count = 1;

    Node *temp = head;
    Node *prev = NULL;

    while (temp != NULL && pos < count)
    {
        prev = temp;
        temp = temp->next;
        count++;
    }

    // if temp is tail

    if (temp == tail)
    {
        deleteFromTail(head, tail);
        return;
    }

    prev->next = temp->next;
    delete temp;
}

// update by key

void updateByKey(Node *&head, Node *&tail)
{
    // Taking input

    print("Enter the key: ");
    int key;
    cin >> key;
    print("Enter the data to Update: ");
    int updateWith;
    cin >> updateWith;

    Node *temp = head;
    // finding the key
    while (temp->data != key && temp != NULL)
    {
        temp = temp->next;
    }

    // if temp is NULL

    if (!temp)
    {
        print("Key does not exist.");
    }
    else
    {
        temp->data = updateWith;
    }
}

// update with position

void updateWithPos(Node *&head, Node *tail)
{

    // Taking input

    print("Enter the position: ");
    int pos;
    cin >> pos;
    print("Enter the data to update: ");
    int updateWith;
    cin >> updateWith;

    int count = 1;
    Node *temp = head;
    while (count < pos && temp != NULL)
    {
        temp = temp->next;
        count++;
    }

    // if temp becomes null
    if (!temp)
    {
        print("Position does not exist.");
    }
    else
    {
        temp->data = updateWith;
    }
}

// sorting using merge sort

Node *findMid(Node *head)
{
    Node *slow = head;
    Node *fast = head->next;

    while (fast != NULL && fast->next != NULL)
    {
        slow = slow->next;
        fast = fast->next->next;
    }
    return slow;
}

Node *merge(Node *left, Node *right)
{

    if (left == NULL)
        return right;

    if (right == NULL)
        return left;

    Node *ans = new Node(-1);
    Node *temp = ans;

    // merge 2 sorted Linked List
    while (left != NULL && right != NULL)
    {
        if (left->data < right->data)
        {
            temp->next = left;
            temp = left;
            left = left->next;
        }
        else
        {
            temp->next = right;
            temp = right;
            right = right->next;
        }
    }

    while (left != NULL)
    {
        temp->next = left;
        temp = left;
        left = left->next;
    }

    while (right != NULL)
    {
        temp->next = right;
        temp = right;
        right = right->next;
    }

    ans = ans->next;
    return ans;
}

Node *mergeSort(Node *head)
{
    // base case
    if (head == NULL || head->next == NULL)
    {
        return head;
    }

    // break linked list into 2 halves
    Node *mid = findMid(head);

    Node *left = head;
    Node *right = mid->next;
    mid->next = NULL;

    // sorting both halves
    left = mergeSort(left);
    right = mergeSort(right);

    // merge both left and right halves
    Node *result = merge(left, right);

    return result;
}

// A simple sort function to change the address of head

void sort(Node *&head, Node *&tail)
{

    head = mergeSort(head);

    Node *temp = head;

    while (temp->next != NULL)
    {
        temp = temp->next;
    }

    tail = temp;
}

int main()
{
    // Initializing linked list head and tail
    Node *head = NULL;
    Node *tail = NULL;

    // While for menu

    while (true)
    {
        print("Your Linked List -- ");
        printLinkedList(head);

        cout << endl;

        print("Enter 1 for InsertToHead");
        print("Enter 2 for insertAtTail");
        print("Enter 3 for InsertionAtPosition");
        print("Enter 4 for deleteByKey");
        print("Enter 5 for deleteFromHead");
        print("Enter 6 for deleteFromTail");
        print("Enter 7 for deleteByPosition");
        print("Enter 8 for updateByKey");
        print("Enter 9 for updateAtPosition");
        print("Enter 10 for sort");

        print("Enter 11 to exit");
        cout << endl;
        int input;
        cin >> input;
        int data;

        switch (input)
        {
        case 1:
            data = takeData();
            insertToHead(head, tail, data);
            break;
        case 2:
            data = takeData();
            insertAtTail(head, tail, data);
            break;
        case 3:
            insertAtPos(head, tail);
            break;
        case 4:
            deleteByKey(head, tail);
            break;
        case 5:
            deleteFromHead(head, tail);
            break;
        case 6:
            deleteFromTail(head, tail);
            break;
        case 7:
            deleteBypos(head, tail);
            break;
        case 8:
            updateByKey(head, tail);
            break;
        case 9:
            updateWithPos(head, tail);
            break;
        case 10:
            sort(head, tail);
            break;
        default:
            return 0;
        }
    }
}