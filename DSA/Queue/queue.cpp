#include <bits/stdc++.h>
using namespace std;

// Creating Node Class

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

// function to print meassage

void print(string message)
{
    cout << endl
         << message;
}

// Creating stack class with its functions

class Queue
{
public:
    Node *front;
    Node *rear;

    Queue()
    {
        front = NULL;
        rear = NULL;
    }

    // for enqueue operation

    void enqueue()
    {

        // Taking input from the user
        int data;
        print("Enter the data: ");
        cin >> data;

        Node *newNode = new Node(data);

        if (rear == NULL)
        {
            rear = front = newNode;
            return;
        }

        rear->next = newNode;
        rear = newNode;
    }

    // Function for dequeue Operation

    void dequeue()
    {
        if (front == NULL)
        {
            return;
        }

        Node *tempNodeRef = front;
        front = front->next;

        if (front == NULL)
        {
            rear = NULL;
        }

        delete tempNodeRef;
    }

    // Search Function

    void search()
    {

        int data;
        print("Enter the data to search: ");
        cin >> data;
        Node *tempNodeRef = front;

        while (tempNodeRef != NULL)
        {
            if (tempNodeRef->data == data)
            {
                cout << endl
                     << "Present";
                return;
            }
            tempNodeRef = tempNodeRef->next;
        }

        print("Not Present");
    }

    // to display

    void display()
    {
        Node *tempNodeRef = front;

        if (!tempNodeRef)
        {
            print("Queue is empty");
        }
        cout << endl;
        while (tempNodeRef != NULL)
        {
            cout << tempNodeRef->data;
            tempNodeRef = tempNodeRef->next;
            if (tempNodeRef)
            {
                cout << "->";
            }
        }
    }

    // Display front

    void getFront()
    {
        if (front)
        {
            cout << endl
                 << front->data;
        }
        else
        {
            cout << "Queue is Empty!";
        }
    }
};

int main()
{
    // Creating queue Object

    Queue q;

    // while for menu --

    while (true)
    {
        print("Your Queue -- ");
        q.display();

        cout << endl;

        print("Enter 1 for enqueue operation");
        print("Enter 2 for dequeue operation");
        print("Enter 3 for get front operation");
        print("Enter 4 for search operation");

        print("Enter 5 to exit");
        cout << endl;
        int input;
        cin >> input;
        int data;

        switch (input)
        {
        case 1:
            q.enqueue();
            break;
        case 2:
            q.dequeue();
            break;
        case 3:
            q.getFront();
            break;

        case 4:
            q.search();
            break;

        default:
            return 0;
        }
    }
}