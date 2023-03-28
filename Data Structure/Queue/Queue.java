import java.util.Scanner;
import java.util.InputMismatchException;
class Queue
{
    private int[] array;
    private int front;
    private int rear;
    private int size;

    // Constuctor for Queue classs  
    public Queue(int size) {
        this.array = new int[size];
        this.front = -1;
        this.rear = -1;
        this.size = size;
    }
    // Method to add elements in queue
    public void enqueue(int item) { 
        // check for is queue is full
        if (isFull()) {
            return;
        } 
        // check if queue is empty
        if (isEmpty()) {
            front = 0;
        }
        rear++;
        array[rear] = item;  // adding element
    }
    //method to remove and element
    public int dequeue() {
        if (isEmpty()) { // check if queue is Empty
            return -1;
        }
        int item = array[front];
        if (front == rear) {
            front = -1;
            rear = -1;
        } else {
            front++;
        }
        return item;
    }
    // method to check if queue is empty
    public boolean isEmpty() {
        return (front == -1 && rear == -1);
    }

    //method to check if queue is full
    public boolean isFull() {
        return (rear == size - 1);
    }
    // method to display queue
    public void display() {
        if (isEmpty()) {
            return;
        }
        for (int i = front; i <= rear; i++) { // traversing from front to rear and print
            System.out.print(array[i] + " ");
        }
    }

    // method to check front of Queue
    public int Front()  
    {  if(front== -1)
        {
            return -1;
        }        
      return array[front];  
    } 

    //method to check size of Queue
    public int size() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
        }
        return (rear - front + 1);
    }
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in))
        {
        System.out.print("Enter the size of the Queue:");
        int size = sc.nextInt(); 
        Queue queue = new Queue(size);
        int choice;
        do {
            System.out.println();
            System.out.println("1.Enqueue");
            System.out.println("2.Dequeue");
            System.out.println("3.Front");
            System.out.println("4.Display");
            System.out.println("5.isEmpty");
            System.out.println("6.Size");
            System.out.println("7.Exit");

            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter the elements: ");
                    int element = sc.nextInt();
                    queue.enqueue(element);
                    break;

                case 2:
                if(queue.isEmpty())
                    {
                        System.out.println("Queue is empty");
                    }
                    int dequeuedElement = queue.dequeue();
                    
                    if (dequeuedElement != -1) {
                        System.out.println("Dequeued element is " + dequeuedElement);
                    }
                    break;
                
                case 3:
                {   
                    System.out.println("Front element is " + queue.Front());  
                    break;
                }

                case 4:
                if( queue.isEmpty())
                {
                    System.out.println("Queue is empty");
                    break;
                }
                System.out.println("Elements in the queue are:");
                    queue.display();
                    break;
                case 5:
                    if(queue.isEmpty())
                    {
                        System.out.println("Queue is Empty");
                    }
                    else{
                        System.out.println("Queue is not empty");
                    }
                    break;
                case 6:
                    int queueSize = queue.size();
                    System.out.println("Size of the queue is" +queueSize);
                    break;
                case 7:
                    System.out.println("Exit done");
                    break;

                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (choice != 7);
    }catch(InputMismatchException ex)
    {
        System.out.println(ex);
    }
}
}