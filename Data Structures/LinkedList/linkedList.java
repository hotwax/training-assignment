/*
 * Implementation of a linked list for Integers.
 *  This can be also be converted into a generic LinkedList by changing the
 *  type of the data to a generic type.
 */


//Importing Scanner class for user input
import java.util.Scanner;

public class linkedList {
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
            System.out.println("--------- MENU ---------");
            System.out.println("Press 0 : Create a New LinkedList");
            System.out.println("Press 1 : Add a new Node");
            System.out.println("Press 2 : Delete a Node");
            System.out.println("Press 3 : Update a Node");
            System.out.println("Press 4 : Sort LinkedList");
            System.out.println("Press 5 : Display/Traversal the LinkedList");
            System.out.println("Press 6 : Search in LinkedList");
            System.out.println("Press 7 : Exit");
            System.out.println("Enter your choice : ");
            choice = sc.nextInt();
            //  Taking the choice from the user and performing the corresponding operation
            switch (choice) {
                case 0:
                    System.out.print("Please Enter the first Node Value: ");
                    head = createList(sc.nextInt());
                    break;
                case 1:
                    if (head != null) {
                        System.out.print("Please Enter the Node Value: ");
                        head = addNode(head, sc.nextInt());
                    } else {
                        System.out.println("Please create a LinkedList first");
                    }
                    break;
                case 2:
                    System.out.print("Please Enter the Node Value: ");
                    head = deleteNode(head, sc.nextInt());
                    break;
                case 3:
                    System.out.print("Please Enter the Node Value: ");
                    updateNode(head, sc.nextInt());
                    break;
                case 4:
                    head = sortList(head);
                    break;
                case 5:
                    displayList(head);
                    break;
                case 6:
                    System.out.print("Please Enter the Node Value: ");
                    searchNode(head, sc.nextInt());
                    break;
                case 7:
                    System.out.println("Program Terminated Successfully");
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        } while (choice != 7);

    }
    //  Method to create a new linked list
    static Node createList(int value){
        return new Node(value);
    }
    //    Method to add a new node to the linked list
    static Node addNode(Node head, int data){
        Node temp  = new Node(data);
        Node node = head;
        while(node.next!=null)
        {
            node = node.next;
        }
        node.next = temp;
        return head;
    }
    //  Method to delete a node from the linked list
    static void displayList(Node head){
        Node temp =  head;
        System.out.println("The Linked List is : ");
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
    static void updateNode(Node head,int data){
        Node temp = head;
        while(temp.next != null){
            if(temp.next.data == data){
                System.out.println("Please Enter the new value of the node: ");
                temp.next.data = sc.nextInt();
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
    //    Function to get the middle node of the linked list
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
    //    Function to merge two sorted linked lists
    static Node merge(Node a,Node b){
        Node result = null;
        if(a == null){
            return b;
        }
        if(b == null){
            return a;
        }
        if(a.data <= b.data){
            result = a;
            result.next = merge(a.next,b);
        }
        else{
            result = b;
            result.next = merge(a,b.next);
        }
        return result;
    }
    //    Function to search a node in the linked list
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
//    Class for the node of the linked list

}


