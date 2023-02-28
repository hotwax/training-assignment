import java.util.Scanner;
class Queue
{
    private int[] arr;
    private int front;
    private int rear;
    private int size;

    // Constuctor for Queue classs  
    public Queue(int size) {
        this.arr = new int[size];
        this.front = -1;
        this.rear = -1;
        this.size = size;
    }
    // Method to add elements in queue
    public void enqueue(int item) { 
        // check for is queue is full
        if (isFull()) {
            System.out.println("Queu is full");
            return;
        } 
        // check if queue is empty
        if (isEmpty()) {
            front = 0;
        }
        rear++;
        arr[rear] = item;  // adding element
        System.out.println(item + " Enqueued in the queue.");
    }
    //method to remove and element
    public int dequeue() {
        if (isEmpty()) { // check if queue is Empty
            System.out.println("Queue is empty");
            return -1;
        }
        int item = arr[front];
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
            System.out.println("Queue is empty");
            return;
        }
        System.out.println("Elements in the queue are:");
        for (int i = front; i <= rear; i++) { // traversing from front to rear and print
            System.out.println(arr[i] + " ");
        }
    }
    //method to check size of
    public int size() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
        }
        return (rear - front + 1);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of the Queue:");
        int size = sc.nextInt(); 
        Queue q = new Queue(size);
        int choice;
        do {
            System.out.println("1.Enqueue");
            System.out.println("2.Dequeue");
            System.out.println("3.Display");
            System.out.println("4.isEmpty");
            System.out.println("5.Size");
            System.out.println("6.Exit");

            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter the elements: ");
                    int element = sc.nextInt();
                    q.enqueue(element);
                    break;
                case 2:
                    int dequeuedElement = q.dequeue();
                    if (dequeuedElement != -1) {
                        System.out.println("Dequeued element is " + dequeuedElement);
                    }
                    break;
                case 3:
                    q.display();
                    break;
                case 4:
                    if(q.isEmpty())
                    {
                        System.out.println("Queue is Empty");
                    }
                    else{
                        System.out.println("Queue is not empty");
                    }
                    break;
                case 5:
                    int queueSize = q.size();
                    System.out.println("Size of the queue is" +queueSize);
                    break;
                case 6:
                    System.out.println("Exit done");
                    break;

                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (choice != 6);
    sc.close();
    }
}