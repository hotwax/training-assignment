#include <iostream>
using namespace std;

// Create a Node for the Linked List 
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

// Create Custom Queue Datastructures
class Queue
{
public:
    // Front and Rear Pointer Which points the Element of Queue
    Node *front;
    Node *rear;

    Queue()
    {
        front = NULL;
        rear = NULL;
    }
    // insert the element in queue
    void enqueue()
    {
        int data;
        cout << "Enter the data: " << endl;
        cin >> data;

        Node *newNode = new Node(data); // Create a NewNode

        if (rear == NULL)
        {
            rear = front = newNode;
            return;
        }

        rear->next = newNode;
        rear = newNode;
    }

    // Pop the element from queue
    void dequeue()
    {
        if (front == NULL)
        {
            cout<<"Queue is Empty"<<endl;
            return;
        }

        Node * To_delete_Node = front;
        front = front->next;

        if (front == NULL)
        {
            rear = NULL;
        }

        delete To_delete_Node ; // delete the element
        cout<<"Operation Successful"<<endl;
    }
    
    // print the Front of the Queue
    void Front()
    {
        cout<<front->data<<endl;
    }
    
    // print the Queue
    void display()
    {
        Node *nodePtr = front;

        if (!nodePtr)
        {
            cout << "Queue empty" << endl;
        }
        cout << endl;
        while (nodePtr != NULL)
        {
            cout << nodePtr->data << " ";
            nodePtr = nodePtr->next;
        }
        cout << endl;
    }
};

// Driver Function
int main()
{
    Queue q;
    int choice;
    while (choice != 5)
    {
        cout << "1. enqueue " << endl;
        cout << "2. dequeue " << endl;
        cout<<  "3. Front   " <<endl;
        cout << "4. Display" << endl;
        cout << "5. exit" << endl;
        cout << "Enter the Choice" << endl;
        cin >> choice;

        switch (choice)
        {
        case 1:
            q.enqueue();
            break;
        case 2:
            q.dequeue();
            break;
        case 3:
            q.Front();
            break ;
        case 4:
            q.display();
            break;
        default:
            return 0  ;
        }
    }
}
