#include <iostream>
#include <cassert>
using namespace std;

const int MAX_SIZE = 100;

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

void testStack() {
    Stack stack;

    // Test isEmpty() function
    assert(stack.isEmpty());

    // Test push() function
    stack.push(1);
    stack.push(2);
    assert(!stack.isEmpty());

    // Test peek() function
    assert(stack.peek() == 2);

    // Test pop() function
    stack.pop();
    assert(stack.peek() == 1);
    stack.pop();
    assert(stack.isEmpty());

    // Test isFull() function
    for (int i = 0; i < MAX_SIZE; i++) {
        stack.push(i);
    }
    assert(stack.isFull());
    stack.push(MAX_SIZE); // should not push anything
    assert(stack.isFull());

    // Test display() function
    stack.display();
    cout << "All tests passed!\n";
}

int main() {
    testStack();
    return 0;
}
