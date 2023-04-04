#include <iostream>
using namespace std;

const int MAX_SIZE = 100; // Maximum size of the queue
int queue[MAX_SIZE], size = MAX_SIZE, front_index = -1, rear_index = -1; // Queue implementation using array

void insert() {
   int value;
   if (rear_index == size - 1) { // If rear index is equal to maximum size of queue
      cout << "Queue Overflow" << endl; // Display overflow error message
   } else {
      if (front_index == -1) { // If front index is equal to -1, which indicates empty queue
         front_index = 0; // Set front index to 0
      }
      cout << "Insert the element in queue: "; // Prompt user to enter value to insert
      cin >> value; // Read value from user
      rear_index++; // Increment rear index
      queue[rear_index] = value; // Assign value to queue at rear index
   }
}

void remove() {
   if (front_index == -1 || front_index > rear_index) { // If front index is equal to -1, which indicates empty queue or front index is greater than rear index
      cout << "Queue Underflow" << endl; // Display underflow error message
   } else {
      cout << "Element removed from queue: " << queue[front_index] << endl; // Display removed element from queue
      front_index++; // Increment front index to remove element from queue
   }
}

void display() {
   if (front_index == -1) { // If front index is equal to -1, which indicates empty queue
      cout << "Queue is empty" << endl; // Display empty queue message
   } else {
      cout << "Queue elements are: ";
      for (int i = front_index; i <= rear_index; i++) { // Iterate through queue from front index to rear index
         cout << queue[i] << " "; // Display each element of queue
      }
      cout << endl;
   }
}

int front() {
   if (front_index == -1 || front_index > rear_index) { // If front index is equal to -1, which indicates empty queue or front index is greater than rear index
      cout << "Queue is empty" << endl; // Display empty queue message
      return -1;
   } else {
      return queue[front_index]; // Return front element of queue
   }
}

int main() {
   int choice;
   cout << "1) Insert element to queue" << endl;
   cout << "2) Delete element from queue" << endl;
   cout << "3) Display all the elements of queue" << endl;
   cout << "4) Get the front element of queue" << endl;
   cout << "5) Exit" << endl;
   do {
      cout << "Enter your choice: ";
      cin >> choice;
      switch (choice) {
         case 1:
            insert(); // Call insert function
            break;
         case 2:
            remove(); // Call remove function
            break;
         case 3:
            display(); // Call display function
            break;
         case 4:
            cout << "Front element of queue is: " << front() << endl; // Call front function and display front element of queue
            break;
         case 5:
            cout << "Exiting..." << endl; // Display exit message
            break;
         default:
            cout << "Invalid choice" << endl; // Display invalid choice message
      }
   } while(choice != 5); // Repeat until user enters 

   return 0;
}
