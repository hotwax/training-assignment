import java.util.Scanner;

public class Queue {

    // front and rear of queue
    Node front, rear;

    /// Node class for creating nodes of queue.
    static class Node {
        int data;
        Node next;

        // Constructor
        Node(int d) {
            data = d;
            next = null;
        }
    }

    // enqueue a new element in the queue
    public static Queue enqueue(Queue queue, int value) {
        Node temp = new Node(value);

        // If queue is empty, then new node is front and rear both
        if (queue.front == null) {
            queue.front = queue.rear = temp;
            return queue;
        }

        // Add the new node at the end of queue and change rear
        queue.rear.next = temp;
        queue.rear = temp;
        return queue;
    }

    // dequeue an element from the queue
    public static Queue dequeue(Queue queue) {
        if (queue.front == null)
            return queue;

        // Store previous front and move front one node ahead
        queue.front = queue.front.next;

        // If front becomes NULL, then change rear also as NULL
        if (queue.front == null)
            queue.rear = null;

        return queue;
    }

    // update an element in the queue
    public static Queue update(Queue queue, int s_value, int n_value) {

        if (queue.front == null)
            return queue;

        Node temp = queue.front;

        // Traverse through the Queue and update the value
        while (temp != null) {
            if (temp.data == s_value) {
                temp.data = n_value;
            }
            temp = temp.next;
        }

        // If value not found
        if (temp == null) {
            System.out.println("Value not found");
        }

        return queue;
    }

    // print the queue
    public static void printQueue(Queue queue) {
        Node currNode = queue.front;
        System.out.print("Queue: ");

        // Traverse through the Queue
        while (currNode != null) {
            // Print the data at current node
            System.out.print(currNode.data + " ");
            currNode = currNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Create a Queue
        Queue queue = new Queue();
        Boolean flag = true;
        while (flag) {
            System.out.println("Select an option from the menu below");
            System.out.println("1. Enqueue a new element in the queue");
            System.out.println("2. Dequeue an element from the queue");
            System.out.println("3. Update an element in queue");
            System.out.println("4. Exit");
            Scanner input = new Scanner(System.in);
            int choice = input.nextInt();
            if (choice < 1 || choice > 4) {
                System.out.println("Invalid choice");
            } else {
                switch (choice) {
                    case 1:
                        System.out.println("Enter the value to be inserted");
                        int i_value = input.nextInt();
                        queue = enqueue(queue, i_value);
                        break;
                    case 2:
                        queue = dequeue(queue);
                        break;
                    case 3:
                        System.out.println("Enter the value to be updated and the new value");
                        int s_value = input.nextInt();
                        int n_value = input.nextInt();
                        queue = update(queue, s_value, n_value);
                        break;
                    case 4:
                        flag = false;
                        break;
                }
                printQueue(queue);
                if (flag == false) {
                    System.out.println("Exiting the program");
                }
            }
        }

    }
}
