#include <iostream>
#include <bits/stdc++.h>
using namespace std;

// // Create Custom Stack DataStructure
class Stack
{

public:
    // Capacity of Stack
    int capacity = 1000;

    // Create stack Array 
    int stack[1000];
    // Create top variable which points to the first element of the stack
     // Initially top is -1 when there is no element in the list
    int top = -1;

    // function to push the element in the stack 
    void push(int value)
    {
        if (top >= capacity - 1)
            cout << "Stack Overflow" << endl;
        else
        {
            top++;
            stack[top] = value;
            cout<<value<<" Inserted Sucessfully "<<endl; 
        }
    }
    
    // pop the element from the stack
    void pop()
    {
        if (top <= -1)
            cout << "Stack Underflow" << endl;
        else
        {
            cout << "The popped element is " << stack[top] << endl;
            top--;
        }
    }

    // Show all the element of the stack
    void display()
    {
        if (top >= 0)
        {
            cout << "Stack elements are:";
            // i = index  , to iterate
            for (int i = top; i >= 0; i--)
                cout << stack[i] << " ";
            cout << endl;
        }
        else
            cout << "Stack is empty";
        cout<<endl;
    }

    // return element at the top of the stack
    void topp()
    {
        if (top == -1)
        {
            cout << "Stack is Empty" << endl;
            return;
        }
        cout <<"Element at Top is "<<stack[top] << endl;
    }
};

int main()
{
    int choice, value;
    // Create Instance of the Stack class
    Stack st;
    
    do
    {
        cout << "1) Push" << endl;
        cout << "2) Pop " << endl;
        cout << "3) Display stack" << endl;
        cout << "4) Top" << endl;
        cout << "5) Exit" << endl;
        cout << "Enter choice: " << endl;
        cin >> choice;
        switch (choice)
        {
        case 1:
        {
            cout << "Enter value to be pushed:" << endl;
            cin >> value;
            st.push(value);
            break;
        }
        case 2:
        {
            st.pop();
            break;
        }
        case 3:
        {
            st.display();
            break;
        }
        case 4:
        {
            st.topp();
            break;
        }
        case 5:
        {
            cout << "Exit" << endl;
            return 0;
        }
        default:
        {
            cout << "Invalid Choice" << endl;
            break ;
        }
        }
    } while (true);
    return 0;
}
