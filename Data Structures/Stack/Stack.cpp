#include <iostream>

class Node {
public:
    int data;
    Node* next;

    Node(int value) {
        data = value;
        next = nullptr;
    }
};

class Stack {
private:
    Node* top; // pointer to the top element of the stack

public:
    Stack() {
        top = nullptr;
    }

    bool isEmpty() {
        return top == nullptr;
    }

    void push(int value) {
        Node* newNode = new Node(value);
        newNode->next = top;
        top = newNode;
        std::cout << value << " pushed to stack.\n";
    }

    void pop() {
        if (isEmpty()) {
            std::cout << "Stack is empty. Cannot pop element.\n";
            return;
        }

        Node* temp = top;
        int value = temp->data;
        top = top->next;
        delete temp;
        std::cout << value << " popped from stack.\n";
    }

    int peek() {
        if (isEmpty()) {
            std::cout << "Stack is empty. No element to peek.\n";
            return -1;
        }

        return top->data;
    }

    void display() {
        if (isEmpty()) {
            std::cout << "Stack is empty.\n";
            return;
        }

        std::cout << "Stack contents: ";
        Node* temp = top;
        while (temp != nullptr) {
            std::cout << temp->data << " ";
            temp = temp->next;
        }
        std::cout << std::endl;
    }
};

int main() {
    Stack stack;
    int choice, value;

    do {
        // Display menu
        std::cout << "\nStack Menu\n";
        std::cout << "====================\n";
        std::cout << "1. Push\n";
        std::cout << "2. Pop\n";
        std::cout << "3. Peek\n";
        std::cout << "4. Display\n";
        std::cout << "5. Exit\n";
        std::cout << "Enter your choice: ";
        std::cin >> choice;

        switch (choice) {
            case 1:
                std::cout << "Enter value to push: ";
                std::cin >> value;
                stack.push(value);
                break;
            case 2:
                stack.pop();
                break;
            case 3:
                std::cout << "Top element of stack: " << stack.peek() << std::endl;
                break;
            case 4:
                stack.display();
                break;
            case 5:
                std::cout << "Exiting program...\n";
                break;
            default:
                std::cout << "Invalid choice. Please try again.\n";
        }
    } while (choice != 5);

    return 0;
}
