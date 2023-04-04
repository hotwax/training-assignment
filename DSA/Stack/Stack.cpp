#include <iostream>
using namespace std;

// declare the stack and its variables as global
int stack[100], capacity = 100, top = -1;

// function to add an element to the top of the stack
void push(int value) {
   if (top >= capacity - 1) // check if the stack is full
       cout << "Stack Overflow" << endl;
   else {
      top++; // increment top to move it to the next empty position
      stack[top] = value; // add the value to the top of the stack
   }
}

// function to remove the element at the top of the stack
void pop() {
   if (top <= -1) // check if the stack is empty
       cout << "Stack Underflow" << endl;
   else {
      cout << "The popped element is " << stack[top] << endl;
      top--; // decrement top to move it to the next position
   }
}

// function to display the elements of the stack
void display() {
   if (top >= 0) { // check if the stack is not empty
      cout << "Stack elements are: ";
      for(int i = top; i >= 0; i--)
         cout << stack[i] << " ";
      cout << endl;
   } else
      cout << "Stack is empty" << endl;
}

// function to get the element at the top of the stack
int get_top() {
   if (top >= 0) // check if the stack is not empty
      return stack[top];
   else {
      cout << "Stack is empty" << endl;
      return -1; // return an invalid value to indicate an empty stack
   }
}

// main function to call the stack functions based on user input
int main() {
   int choice, value;
   cout << "1) Push in stack" << endl;
   cout << "2) Pop from stack" << endl;
   cout << "3) Display stack" << endl;
   cout << "4) Get top of stack" << endl;
   cout << "5) Exit" << endl;
   do {
      cout << "Enter choice: ";
      cin >> choice;
      switch(choice) {
         case 1: {
            cout << "Enter value to be pushed: ";
            cin >> value;
            push(value);
            break;
         }
         case 2: {
            pop();
            break;
         }
         case 3: {
            display();
            break;
         }
         case 4: {
            int top_value = get_top();
            if (top_value != -1)
               cout << "The top element is " << top_value << endl;
            break;
         }
         case 5: {
            cout << "Exiting program" << endl;
            break;
         }
         default: {
            cout << "Invalid choice" << endl;
         }
      }
   } while (choice != 5);
   return 0;
}
