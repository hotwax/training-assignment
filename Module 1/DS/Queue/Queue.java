import java.util.Scanner;
import java.util.InputMismatchException;

public class Queue {

    Node front, rear;

    /// Node class 
    static class Node {
        int data;
        Node next;

        // Constructor
        Node(int d) {
            data = d;
            next = null;
        }
    }

    // Enqueue Method or Insert Method in queue.
    public static Queue enqueue(Queue q, int v) {
        Node temp = new Node(v);

        // Case 1: If queue is empty
        if (q.front == null) {
            q.front = q.rear = temp;
            return q;
        }

        // Case 2: If queue is not empty then add the new node at the end of queue and change rear.
        q.rear.next = temp;
        q.rear = temp;
        return q;
    }

    // Dequeue Method or delete Method
    public static Queue dequeue(Queue q) {
        if (q.front == null)
            return q;

        // Move front one node ahead
        q.front = q.front.next;

        // If front equals NULL, then change rear also as NULL
        if (q.front == null)
            q.rear = null;

        return q;
    }

    // Update Method
    public static Queue update(Queue q, int v1, int v2) {

        if (q.front == null)
            return q;

        Node t = q.front;

        while (t != null) {
            if (t.data == v1) {
                t.data = v2;
            }
            t = t.next;
        }

        // If value not found
        if (t == null) {
            System.out.println("Value not found");
        }

        return q;
    }

    // Print Method 
    public static void printQueue(Queue q) {
        Node currNode = q.front;
        System.out.print("Queue: ");
        while (currNode != null) {
            System.out.print(currNode.data + " ");
            currNode = currNode.next;
        }
        System.out.println();
    }

    // Main Method

    public static void main(String[] args) {
        // Create a Queue
        Queue q = new Queue();
        Boolean flag = true;
        while (flag) {
            
            System.out.println("Choose an option from the Dashboard given below:");
            System.out.println("1. Enqueue ");
            System.out.println("2. Dequeue ");
            System.out.println("3. Update ");
            System.out.println("4. Exit ");
            System.out.println();
            Scanner sc = new Scanner(System.in);
            int ch = sc.nextInt();
            if (ch < 1 || ch > 4) {
                System.out.println("Kindly enter the correct options.");
            } else {
                try
                {
                switch (ch) {
                    case 1:
                        System.out.println("Enter the value to be inserted");
                        int i_value = sc.nextInt();
                        q = enqueue(q, i_value);
                        break;
                    case 2:
                        q = dequeue(q);
                        break;
                    case 3:
                        System.out.println("Enter the value to be updated ");
                        int s_value = sc.nextInt();
                        int n_value = sc.nextInt();
                        q = update(q, s_value, n_value);
                        break;
                    case 4:
                        flag = false;
                        break;
                }
                System.out.println();
                printQueue(q);
                System.out.println();
                if (flag == false) {
                    System.out.println("Exiting the program");
                }
            }
                catch(InputMismatchException except) {
                    System.out.println(except.getMessage());
                }
            }
        }

    }
    
}