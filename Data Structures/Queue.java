/*
 * Implementation of a linked list for Integers.
 *  This can be also be converted into a generic LinkedList by changing the
 *  type of the data to a generic type.
 */


//Importing Scanner class for user input
import java.util.Scanner;

public class Queue {
    static class Node {
        //        Data of the node
        int data;
        // Pointer to the next node
        Node next;
        Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    //    Create a scanner to take input from the user
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        // Create a blank linked list
        Node head = null;
        // Variable to store the choice of the user
        int choice;
        do {
            //  Printing the menu for the user
            System.out.println(" --------- MENU --------- ");
            System.out.println("Press 0 : Create a New Queue");
            System.out.println("Press 1 : Enqueue an element");
            System.out.println("Press 2 : Dequeue an element");
            System.out.println("Press 3 : Update an element");
            System.out.println("Press 4 : Display/Traversal the LinkedList");
            System.out.println("Press 5 : Exit");
            System.out.println("Enter your choice : ");
            choice = sc.nextInt();
            //  Taking the choice from the user and performing the corresponding operation
            switch (choice) {
                case 0:
                    System.out.print("Please Enter the first Node Value: ");
                    head = createQueue(sc.nextInt());
                    break;
                case 1:
                    if (head != null) {
                        System.out.print("Please Enter the Node Value: ");
                        head = enqueue(head, sc.nextInt());
                    } else {
                        System.out.println("Please create a LinkedList first");
                    }
                    break;
                case 2:
                    System.out.println("Please Enter the Node Value: ");
                    int dequeuedElement = dequeue(head);
                    break;
                case 3:
                    System.out.print("Please Enter the Node Value: ");
                    updateElement(head, sc.nextInt());
                    break;
                case 4:
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


    private static Node enqueue(Node head, int nextInt) {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(nextInt);
        return head;
    }
    private static int dequeue(Node head) {
        if(head == null){
            System.out.println("Queue is empty");
            return -1;
        }
        int dequeuedElement = head.data;
        head = head.next;
        return dequeuedElement;
    }
    private static void updateElement(Node head, int nextInt) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == nextInt) {
                System.out.print("Please Enter the new Node Value: ");
                temp.data = sc.nextInt();
                break;
            }
            temp = temp.next;
        }
    }

    private static void display(Node head) {
        Node temp = head;
        System.out.println("The Queue is: ");
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    private static Node dequeue(Node head, int nextInt) {
        if(head ==null){
            System.out.println("Queue is empty");
            return null;
        }
        else{
            Node temp = head;
            head = head.next;
            System.out.println("The deleted element is: " + temp.data);
            return head;
        }
    }

    private static Node createQueue(int nextInt) {
      Node head = new Node(nextInt);
        return head;
    }


}


