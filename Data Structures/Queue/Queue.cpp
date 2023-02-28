#include <iostream>
using namespace std;

const int MAX_SIZE = 100; // maximum size of queue

class Queue {
    private:
        int front;  // index of front element in queue
        int rear;   // index of rear element in queue
        int data[MAX_SIZE]; // array to store elements

    public:
        Queue() { front = -1; rear = -1; } // constructor

        bool isEmpty() {
            return front == -1;
        }

        bool isFull() {
            return rear == MAX_SIZE - 1;
        }

        void enqueue(int value) {
            if (isFull()) {
                cout << "Queue is full. Cannot enqueue element.\n";
                return;
            }
            if (isEmpty()) {
                front = 0;
            }
            data[++rear] = value;
            cout << value << " enqueued to queue.\n";
        }

        void dequeue() {
            if (isEmpty()) {
                cout << "Queue is empty. Cannot dequeue element.\n";
                return;
            }
            int value = data[front++];
            if (front > rear) {
                front = rear = -1;
            }
            cout << value << " dequeued from queue.\n";
        }

        int peek() {
            if (isEmpty()) {
                cout << "Queue is empty. No element to peek.\n";
                return -1;
            }
            return data[front];
        }

        void display() {
            if (isEmpty()) {
                cout << "Queue is empty.\n";
                return;
            }
            cout << "Queue contents: ";
            for (int i = front; i <= rear; i++) {
                cout << data[i] << " ";
            }
            cout << endl;
        }
};

int main() {
    Queue queue;
    int choice, value;

    do {
        // Display menu
        cout << "\nQueue Menu\n";
        cout << "====================\n";
        cout << "1. Enqueue\n";
        cout << "2. Dequeue\n";
        cout << "3. Peek\n";
        cout << "4. Display\n";
        cout << "5. Exit\n";
        cout << "Enter your choice: ";
        cin >> choice;

        switch(choice) {
            case 1:
                cout << "Enter value to enqueue: ";
                cin >> value;
                queue.enqueue(value);
                break;
            case 2:
                queue.dequeue();
                break;
            case 3:
                cout << "Front element of queue: " << queue.peek() << endl;
                break;
            case 4:
                queue.display();
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
