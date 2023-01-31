#include <iostream>
using namespace std;

// Create a Custom Queue DataStructure
class Queue
{
public:
    // Front and rear are variables which points the elements of Queue
    int front, rear, capacity;
    int queue[1000];

    // Constructor to initialize all the class variables
    Queue(int capacity)
    {
        front = rear = 0;
        this->capacity = capacity ;
    }

    // Method to push the element in the Queue
    void Enqueue()
    {
        cout << "Enter the element to be inserted" << endl;
        int data = 0;
        cin >> data;
        if (capacity == rear)
        {
            cout << "Queue is full" << endl;
            return;
        }

        else
        {
            queue[rear] = data;
            rear++;
        }
        return;
    }

    // Method to pop the element from the Queue
    void Dequeue()
    {
        if (front == rear)
        {
            cout << "Queue is  empty" << endl;
            return;
        }
        else
        {
        // i = index , to iterate
            for (int i = 0; i < rear - 1; i++)
            {
                queue[i] = queue[i + 1];
            }
            rear--;
        }
        cout<<"Operation Successful"<<endl;
    }

    // Method to print the Queue 
    void Display()
    {

        if (front == rear)
        {
            cout << "Queue is Empty" << endl;
            return;
        }
        // i = index , to iterate 
        for (int i = front; i < rear; i++)
        {
            cout << queue[i] << endl;
        }
        return;
    }

    // Print Front of the queue
    void Front()
    {
        if (front == rear)
        {
            cout << " Queue is Empty " << endl;
            return;
        }
        cout << queue[front] << endl;
        return;
    }
};

int main()
{
    int Size ; // Size of the Queue
    cout<<"Enter the Size of the Queue"<<endl;
    cin>>Size ;
    Queue q(Size);
    int choice = 0;
    while (choice != 5)
    {        
                  // MENU
        cout << "1. enqueue " << endl;
        cout << "2. dequeue " << endl;
        cout << "3. Front " << endl;
        cout << "4. Display" << endl;
        cout << "5. exit" << endl;
        cout << "Enter the Choice" << endl;
        cin >> choice;

        switch (choice)
        {
        case 1:
            q.Enqueue();
            break;
        case 2:
            q.Dequeue();
            break;
        case 3:
            q.Front();
            break;
        case 4:
            q.Display();
            break;
        case 5:
            cout<<"EXIT"<<endl;
            break ;
        default:
            cout<<"Invalid Choice"<<endl;
            return 0 ;
        }
    }
    return 0;
}
