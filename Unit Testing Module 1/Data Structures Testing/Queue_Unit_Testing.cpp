#include <iostream>
using namespace std;

const int MAX_SIZE = 10; // maximum size of queue

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
#include <cassert>
#include <iostream>

int main() {
    Queue queue;
    
    // Test enqueue() function
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    queue.enqueue(4);
    queue.enqueue(5);
    queue.enqueue(6);
    queue.enqueue(7);
    queue.enqueue(8);
    queue.enqueue(9);
    queue.enqueue(10);
    assert(queue.isFull());  // Queue should be full now
    
    // Test dequeue() function
    queue.dequeue();  // Should remove 1
    queue.dequeue();  // Should remove 2
    queue.dequeue();  // Should remove 3
    assert(queue.peek() == 4);  // Should return 4
    
    // Test isEmpty() function
    while (!queue.isEmpty()) {
        queue.dequeue();
    }
    assert(queue.isEmpty());  // Queue should be empty now
    
    // Test edge case
    queue.enqueue(1);
    assert(queue.peek() == 1);  // Should return 1
    queue.dequeue();
    assert(queue.isEmpty());  // Queue should be empty now
    
    std::cout << "All tests passed!\n";
    return 0;
}