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

class Stack
{
public:
    Node *top;

    Stack()
    {
        top = NULL;
    }

    // to push

    void push()
    {

        // Taking input
        print("Enter the data: ");
        int data;
        cin >> data;

        Node *newNode = new Node(data);

        if (!newNode)
        {
            print("Stack Overflow.");
            return;
        }

        newNode->next = top;
        top = newNode;
    }

    // to peek

    void peek()
    {
        if (top == NULL)
        {
            print("Stack is empty.");
        }
        else
        {
            cout << endl
                 << "The element is : " << this->top->data;
        }
    }

    // tot pop

    void pop()
    {
        if (top == NULL)
        {
            print("Stack Underflow.");
        }
        else
        {
            Node *tempNodeRef = top;
            top = top->next;
            delete tempNodeRef;
        }
    }

    // to search

    void search()
    {

        int data;
        print("Enter the data to search: ");
        cin >> data;
        Node *tempNodeRef = top;

        while (tempNodeRef != NULL)
        {
            if (tempNodeRef->data == data)
            {
                print("Present");
                return;
            }
            tempNodeRef = tempNodeRef->next;
        }

        print("Not Present");
    }

    // to display

    void display()
    {
        Node *tempNodeRef = top;

        if (!tempNodeRef)
        {
            print("Stack is empty");
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
};

int main()
{
    // Creating Stack Object

    Stack s;

    // while for menu --

    while (true)
    {
        print("Your Stack -- ");
        s.display();

        cout << endl;

        print("Enter 1 for push operation");
        print("Enter 2 for pop operation");
        print("Enter 3 for peek operation");
        print("Enter 4 for search operation");

        print("Enter 5 to exit");
        cout << endl;
        int input;
        cin >> input;
        int data;

        switch (input)
        {
        case 1:
            s.push();
            break;
        case 2:
            s.pop();
            break;
        case 3:
            s.peek();
            break;
        case 4:
            s.search();
            break;

        default:
            return 0;
        }
    }
}