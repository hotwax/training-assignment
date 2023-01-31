import java.util.Scanner;
import java.util.InputMismatchException;

public class SinglyLinkedList {
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
    // Method to create a new linked list
    static Node createList(int value) {
        return new Node(value);
    }

    // Method to insert a new node to the linked list
    static Node insert(Node head, int data) {
        Node temp = new Node(data);
        Node node = head;
        while (node.next != null) {
            node = node.next;
        }
        node.next = temp;
        return head;
    }

    // Method to display the complete linked list.
    static void displayList(Node head) {
        Node temp = head;
        System.out.println("The Linked List is : ");
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // Method to delete a node in the linked list
    static Node deleteNode(Node head, int data) {
        Node temp = head;
        while (temp.next != null) {
            if (temp.next.data == data) {
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }
        displayList(head);
        return head;
    }

    // Sort Method : Used merge sort to sort the list. 
    static void updateNode(Node head, int data) {
        Node temp = head;
        while (temp.next != null) {
            if (temp.next.data == data) {
                System.out.println("Please Enter the new value of the node: ");
                temp.next.data = sc.nextInt();
                break;
            }
            temp = temp.next;
        }
        displayList(head);
    }

    // Merge sort on Linked List
    static Node sortList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node mid = getMid(head);
        Node nextToMid = mid.next;
        mid.next = null;
        Node left = sortList(head);
        Node right = sortList(nextToMid);
        return merge(left, right);
    }

    // Function to get the middle node of the linked list
    static Node getMid(Node head) {
        if (head == null) {
            return head;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return slow;
    }

    // Function to merge two sorted linked lists
    static Node merge(Node a, Node b) {
        Node result = null;
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        if (a.data <= b.data) {
            result = a;
            result.next = merge(a.next, b);
        } else {
            result = b;
            result.next = merge(a, b.next);
        }
        return result;
    }

    // Function to search a node in the linked list
    static void searchNode(Node head, int data) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == data) {
                System.out.println("Node Found");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Node Not Found");
    }
    // Class for the node of the linked list

    // For taking input.
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Create a blank linked list
        Node head = null;
        System.out.print("Please Enter the first Node Value: ");
        head = createList(sc.nextInt());
        // Variable to store the choice of the user
        int choice;
        do {
            // Printing the menu for the user
            System.out.println();
            System.out.println("Select an option from the dashboard givene below:");
            System.out.println("1 : Insert ");
            System.out.println("2 : Delete ");
            System.out.println("3 : Update ");
            System.out.println("4 : Sort LinkedList");
            System.out.println("5 : Display LinkedList");
            System.out.println("6 : Search in LinkedList");
            System.out.println("7 : Exit");
            System.out.println("------------------------------------------");
            System.out.println("Enter your choice : ");
            choice = sc.nextInt();
            // Taking the choice from the user and performing the corresponding operation
            try {
                switch (choice) {
                    case 1:
                        if (head != null) {
                            System.out.print("Enter the Node Value: ");
                            head = insert(head, sc.nextInt());
                        }
                        break;
                    case 2:
                        System.out.print("Enter the Node Value: ");
                        head = deleteNode(head, sc.nextInt());
                        break;
                    case 3:
                        System.out.print(" Enter the Node Value which is to be updated: ");
                        updateNode(head, sc.nextInt());
                        break;
                    case 4:
                        head = sortList(head);
                        break;
                    case 5:
                        displayList(head);
                        break;
                    case 6:
                        System.out.print(" Enter the value to be searched: ");
                        searchNode(head, sc.nextInt());
                        break;
                    case 7:
                        System.out.println("Exiting Program.");
                        break;
                    default:
                        System.out.println("Invalid Input");
                }
            } catch(InputMismatchException ex) {
                System.out.println(ex.getMessage());
            }
        } while (choice != 7);

    }

    

}
