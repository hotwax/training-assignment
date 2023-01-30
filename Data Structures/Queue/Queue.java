/*
 * Implementation of a linked list for Integers.
 *  This can be also be converted into a generic LinkedList by changing the
 *  type of the data to a generic type.
 */

//Importing Scanner class for user input
import java.util.Scanner;

public class Queue {
    static class Node {
        // Data of the node
        int data;
        // Pointer to the next node
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Create a scanner to take input from the user
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Create a blank linked list
        Node head = null;
        // Variable to store the choice of the user
        int choice;
        do {
            // Printing the menu for the user
            System.out.println(" --------- MENU --------- ");
            System.out.println("Press 0 : Create a New Queue");
            System.out.println("Press 1 : Enqueue an element");
            System.out.println("Press 2 : Dequeue an element");
            System.out.println("Press 3 : Front of Queue");
            System.out.println("Press 4 : Display/Traversal the Queue");
            System.out.println("Press 5 : Exit");
            System.out.println("Enter your choice : ");
            choice = sc.nextInt();
            // Taking the choice from the user and performing the corresponding operation
            switch (choice) {
                case 0:
                    System.out.print("Please Enter the first Element: ");
                    head = createQueue(sc.nextInt());
                    break;
                case 1:
                    if (head != null) {
                        System.out.print("Please Enter the Element: ");
                        enqueue(head, sc.nextInt());
                    } else {
                        System.out.println("Please create a Queue first");
                    }
                    break;
                case 2:
                    if(head!=null) {
                        head = dequeue(head);
                    }
                    break;
                case 3:
                    if(head!=null)
                        front(head);
                    break;
                case 4:
                    if(head!=null)
                        display(head);
                    break;
                case 5:
                    System.out.println("Program Terminated Successfully");
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        } while (choice != 5);

    }
    // Method to find the front of the queue
    private static void front(Node head) {
        if (head == null) {
            System.out.println("Queue is empty");
        } else {
            System.out.println("The front element is: " + head.data);
        }
    }
    // Method to enqueue an element
    private static Node enqueue(Node head, int value) {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(value);
        return head;
    }
    // Method to dequeue an element
    private static Node dequeue(Node head) {
        if (head == null) {
            System.out.println("Queue is empty");
            return head;
        }
        System.out.println("Dequeued Element: " + head.data);
        head = head.next;
        return head;
    }
    // Method to display the queue
    private static void display(Node head) {
        Node temp = head;
        System.out.println("The Queue is: ");
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
    // Method to create a queue
    private static Node createQueue(int firstValue) {
        Node head = new Node(firstValue);
        return head;
    }

}
