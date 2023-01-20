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

        Node *temp = front;
        front = front->next;

        if (front == NULL)
        {
            rear = NULL;
        }

        delete temp;
    }

    // Update Function

    void updateWithKey()
    {
        // Taking input from the user

        int key;
        print("Enter the key: ");
        cin >> key;
        int data;
        print("Enter the data: ");
        cin >> data;

        Node *temp = front;

        while (temp->data != key && temp != NULL)
        {
            temp = temp->next;
        }

        if (temp == NULL)
        {
            print("No such key present.");
        }
        else
        {
            temp->data = data;
        }
    }

    // Search Function

    void search()
    {

        int data;
        print("Enter the data to search: ");
        cin >> data;
        Node *temp = front;

        while (temp != NULL)
        {
            if (temp->data == data)
            {
                cout << endl
                     << "Present";
                return;
            }
            temp = temp->next;
        }

        print("Not Present");
    }

    // to display

    void display()
    {
        Node *temp = front;

        if (!temp)
        {
            print("Queue is empty");
        }
        cout << endl;
        while (temp != NULL)
        {
            cout << temp->data;
            temp = temp->next;
            if (temp)
            {
                cout << "->";
            }
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
        print("Enter 3 for updateWithKey operation");
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
            q.updateWithKey();
            break;
        case 4:
            q.search();
            break;

        default:
            return 0;
        }
    }
}