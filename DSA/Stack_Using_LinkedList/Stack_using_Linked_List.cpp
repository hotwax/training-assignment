#include<iostream>
using namespace std;

// Create Node 
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

// Create Custom Stack DataStructure
class Stack
{
public:
    // Create top pointer which points to the first element of the stack
    Node *top; 
    Stack()
    {
        // Initially top is null when there is no element in the list
        top = NULL;
    }

    // function to push the element in the stack 
    void push(int data)
    {
        Node *newNode = new Node(data);
        if (!newNode)
        {
            cout << "Stack Overflow." << endl;
            return;
        }
        newNode->next = top;
        top = newNode;  // points top pointer to newNode
        cout<<data<<" Inserted Sucessfully "<<endl; 
    }

    // return the top element of the stack 
    void topp()
    {
        if (top == NULL)
        {
            cout << "Stack is empty." << endl;
        }
        else
        {
            cout << endl
                 << "The element at top is  : " << this->top->data << endl;
        }
    }

    // Pop the element which is present at top 
    void pop()
    {
        if (top == NULL)
        {
            cout << "Stack Underflow." << endl;
        }
        else
        {
            cout << "The popped element is " << top->data << endl;
            Node *to_delete_node = top;
            top = top->next;
            delete to_delete_node;
        }
    }
    
    // Print all the element of the stack
    void display()
    {
        Node * nodePtr = top;

        if (!nodePtr)
        {
            cout << "Stack is empty" << endl;
            return ;
        }
        cout << endl;
        while (nodePtr != NULL)
        {
            cout << nodePtr->data;
            nodePtr = nodePtr->next;
            if (nodePtr)
            {
                cout << "->";
            }
        }
        cout<<endl;
    }
};

int main()
{

    Stack s;
    int choice = 0;
    while (true)
    {
        cout << "1. push" << endl;
        cout << "2. pop" << endl;
        cout << "3. top" << endl;
        cout << "4. Display the stack" << endl;
        cout << "5. Exit" << endl;
        cout << endl;
        cout << "Enter Your Choice" << endl;
        cin >> choice;
        switch (choice)
        {
            case 1:
            {
                cout << "Enter Element to Insert" << endl;
                int value = 0;
                cin >> value;
                s.push(value);
                break;
            }
            case 2:
                s.pop();
                break;
            case 3:
                s.topp();
                break;
            case 4:
                s.display();
                break ;
            case 5:
                cout << "Exit" << endl;
                return 0;
            default:
                cout << "Invalid Choice" << endl;
                break ;
        }
    }
}
