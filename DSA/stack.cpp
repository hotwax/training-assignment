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
            Node *temp = top;
            top = top->next;
            delete temp;
        }
    }

    // to search

    void search()
    {

        int data;
        print("Enter the data to search: ");
        cin >> data;
        Node *temp = top;

        while (temp != NULL)
        {
            if (temp->data == data)
            {
                print("Present");
                return;
            }
            temp = temp->next;
        }

        print("Not Present");
    }

    // to update

    void updateWithKey()
    {

        // Taking input

        int key;
        print("Enter the key: ");
        cin >> key;
        int data;
        print("Enter the data: ");
        cin >> data;

        Node *temp = top;

        while (temp->data != key && temp != NULL)
        {
            temp = temp->next;
        }

        if (!temp)
        {
            print("No such key present");
        }
        else
        {
            temp->data = data;
            print("Updation Successful.");
        }
    }

    // to display

    void display()
    {
        Node *temp = top;

        if (!temp)
        {
            print("Stack is empty");
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