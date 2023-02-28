#include <iostream>
using namespace std;

const int MAX_SIZE = 100; // maximum size of stack

class Stack {
    private:
        int top;  // index of top element in stack
        int data[MAX_SIZE]; // array to store elements

    public:
        Stack() { top = -1; } // constructor

        bool isEmpty() {
            return top == -1;
        }

        bool isFull() {
            return top == MAX_SIZE - 1;
        }

        void push(int value) {
            if (isFull()) {
                cout << "Stack is full. Cannot push element.\n";
                return;
            }
            data[++top] = value;
            cout << value << " pushed to stack.\n";
        }

        void pop() {
            if (isEmpty()) {
                cout << "Stack is empty. Cannot pop element.\n";
                return;
            }
            int value = data[top--];
            cout << value << " popped from stack.\n";
        }

        int peek() {
            if (isEmpty()) {
                cout << "Stack is empty. No element to peek.\n";
                return -1;
            }
            return data[top];
        }

        void display() {
            if (isEmpty()) {
                cout << "Stack is empty.\n";
                return;
            }
            cout << "Stack contents: ";
            for (int i = top; i >= 0; i--) {
                cout << data[i] << " ";
            }
            cout << endl;
        }
};

int main() {
    Stack stack;
    int choice, value;

    do {
        // Display menu
        cout << "\nStack Menu\n";
        cout << "====================\n";
        cout << "1. Push\n";
        cout << "2. Pop\n";
        cout << "3. Peek\n";
        cout << "4. Display\n";
        cout << "5. Exit\n";
        cout << "Enter your choice: ";
        cin >> choice;

        switch(choice) {
            case 1:
                cout << "Enter value to push: ";
                cin >> value;
                stack.push(value);
                break;
            case 2:
                stack.pop();
                break;
            case 3:
                cout << "Top element of stack: " << stack.peek() << endl;
                break;
            case 4:
                stack.display();
                break;
            case 5:
                cout << "Exiting program...\n";
                break;
            default:
                cout << "Invalid choice. Please try again.\n";
        }
    } while(choice != 5);

    return 0;
}
