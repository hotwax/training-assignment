/*
 * Implementation of a linked list for Integers.
 *  This can be also be converted into a generic LinkedList by changing the
 *  type of the data to a generic type.
 */

// Importing Exception class for handling exceptions
import java.util.InputMismatchException;
//Importing Scanner class for user input
import java.util.Scanner;

public class linkedList {
    static class Node {
        //  Data of the node
        int data;
        // Pointer to the next node
        Node next;
        Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    public static void main(String[] args) {
        // Create a blank linked list
        Node head = null;
        //  Creating a infinite loop to keep the program running
        while(true) {
            //  Printing the menu for the user
            System.out.println("--------- MENU ---------");
            System.out.println("Press 0 : Create a New LinkedList");
            System.out.println("Press 1 : Add a new Node at End");
            System.out.println("Press 2 : Add a new Node at First");
            System.out.println("Press 3 : Add a new Node at Position");
            System.out.println("Press 4 : Delete a Node");
            System.out.println("Press 5 : Update a Node");
            System.out.println("Press 6 : Sort LinkedList");
            System.out.println("Press 7 : Display/Traversal the LinkedList");
            System.out.println("Press 8 : Search in LinkedList");
            System.out.println("Press 9 : Exit");
            System.out.println("Enter your choice : ");
            try {
                //  Create a scanner to take input from the user
                Scanner sc = new Scanner(System.in);
                int  choice = sc.nextInt();
                //  Taking the choice from the user and performing the corresponding operation
                switch (choice) {
                    case 0:
                        System.out.print("Please Enter the first Node Value: ");
                        head = createList(sc.nextInt());
                        break;
                    case 1:
                        if (head != null) {
                            System.out.print("Please Enter the Node Value: ");
                            head = insertAtEnd(head,sc.nextInt());
                        } else {
                            System.out.println("Please create a LinkedList first");
                        }
                            break;
                    case 2:
                            if (head != null) {
                                System.out.print("Please Enter the Node Value: ");
                                head = insertAtFront(head,sc.nextInt());
                            } else {
                                System.out.println("Please create a LinkedList first");
                            }
                            break;
                    case 3:
                        if (head != null) {
                            System.out.print("Please Enter the Node Value and Position respectively: ");
                            head = insertAtPosition(head, sc.nextInt(), sc.nextInt());
                        } else {
                            System.out.println("Please create a LinkedList first");
                        }
                        break;
                    case 4:
                        if(head == null){
                            System.out.println("Please create a LinkedList first");
                        } else {
                            System.out.print("Please Enter the Node Value: ");
                            head = deleteNode(head, sc.nextInt());
                        }
                        break;
                    case 5:
                        if (head == null) {
                            System.out.println("Please create a LinkedList first");
                        } else {
                            System.out.print("Please Enter the Node Value: ");
                            System.out.println("Please Enter the new value of the node: ");
                            updateNode(head, sc.nextInt(), sc.nextInt());
                        }
                        break;
                    case 6:
                        if (head == null) {
                            System.out.println("Please create a LinkedList first");
                        } else {
                            head = sortList(head);
                            System.out.println("The Sorted Linked List is : ");
                            displayList(head);
                        }
                        break;
                    case 7:
                        if (head == null) {
                            System.out.println("Please create a LinkedList first");
                        } else {
                            System.out.println("The Linked List is : ");
                            displayList(head);
                        }
                        break;
                    case 8:
                        if (head == null) {
                            System.out.println("Please create a LinkedList first");
                        } else {
                            System.out.print("Please Enter the Node Value: ");
                            searchNode(head, sc.nextInt());
                        }
                        break;
                    case 9:
                        System.out.println("Program Terminated Successfully");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid Input");
                }
            }
            //  Catching the exception if the user enters a non integer value
            catch(InputMismatchException e){
                System.out.println("Please Enter Integer Choice Only");
            }
        }
    }
    //  Method to create a new linked list
    static Node createList(int value){
        return new Node(value);
    }
    //    Method to add a new node to the linked list
    static Node insertAtEnd(Node head, int data){
        Node temp  = new Node(data);
        Node node = head;
        while(node.next!=null)
        {
            node = node.next;
        }
        node.next = temp;
        return head;
    }
    // Method to insert at front
    static Node insertAtFront(Node head, int data){
        Node temp = new Node(data);
        temp.next = head;
        return temp;
    }
    // Method to insert at position
    static Node insertAtPosition(Node head, int data, int position){
        Node temp = new Node(data);
        Node node = head;
        if(position == 0) {
            temp.next = head;
            return temp;
        }
        for(int i=0;i<position-1;i++){
            node = node.next;
        }
        temp.next = node.next;
        node.next = temp;
        return head;
    }
    //  Method to delete a node from the linked list
    static void displayList(Node head){
        Node temp =  head;
        while(temp != null){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
    //  Method to update a node in the linked list
    static Node deleteNode(Node head,int data){
        Node temp = head;
        while(temp.next != null){
            if(temp.next.data == data){
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }
        displayList(head);
        return head;
    }
    // Method of update note in linked list
    static void updateNode(Node head,int data,int newValue){
        Node temp = head;
        while(temp.next != null){
            if(temp.next.data == data){
                temp.next.data = newValue;
                break;
            }
            temp = temp.next;
        }
        displayList(head);
    }
    // Merge sort on Linked List
    static Node sortList(Node head){
        if(head==null || head.next==null){
            return head;
        }
        Node mid = getMid(head);
        Node nextToMid = mid.next;
        mid.next = null;
        Node left = sortList(head);
        Node right = sortList(nextToMid);
        return merge(left,right);
    }
    //  Function to get the middle node of the linked list : Used in merge sort
    static Node getMid(Node head){
        if(head==null){
            return head;
        }
        Node slow = head;
        Node fast = head.next;
        while(fast != null){
            fast = fast.next;
            if(fast != null){
                slow = slow.next;
                fast = fast.next;
            }
        }
        return slow;
    }
    //    Function to merge two sorted linked lists : Used in merge sort
    static Node merge(Node left, Node right){
        Node result = null;
        if(left == null){
            return right;
        }
        if(right == null){
            return left;
        }
        if(left.data <= right.data){
            result = left;
            result.next = merge(left.next, right);
        }
        else{
            result = right;
            result.next = merge(left, right.next);
        }
        return result;
    }
    //    Function to search a node in the linked list : Linear Search
    static void searchNode(Node head,int data){
        Node temp = head;
        while(temp!=null){
            if(temp.data==data){
                System.out.println("Node Found");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Node Not Found");
    }
}


