import java.util.Scanner;

public class SinglyLinkedList {

    Node head; // head of list

    // Node class.
    static class Node {

        int data;
        Node next;

        // Constructor
        Node(int d) {
            data = d;
            next = null;
        }
    }

    // Insert Method
    public static SinglyLinkedList insert(SinglyLinkedList list, int d) {

        Node new_node = new Node(d);
        new_node.next = null;

        // Case 1: If Linked List is empty
        if (list.head == null) {
            list.head = new_node;
        } else {
            // Otherwise we traverse till the end of the linked list.
            Node last = list.head;
            while (last.next != null) {
                last = last.next;
            }
            // Insert at the end.
            last.next = new_node;
        }
        return list;
    }

    // Delete Method 
    public static SinglyLinkedList delete(SinglyLinkedList list, int d) {

        Node cNode = list.head, prev = null;

        // Case 1: If head node is to be deleted.
        if (cNode != null && cNode.data == d) {
            list.head = cNode.next;
            System.out.println(d + " Data is deleted.");
            return list;
        }

        // Case 2: Finding the element while traversing the list.
        while (cNode != null && cNode.data != d) {
            prev = cNode;
            cNode = cNode.next;
        }

        if (cNode != null) {
            prev.next = cNode.next;
            System.out.println(d + " Data is deleted.");
        }

        // Case 3: If element not present in the list.
        if (cNode == null) {
            System.out.println(d + " Element not found.");
        }

        return list;
    }

    // Update Method or Replace Method
    public static SinglyLinkedList update(SinglyLinkedList list, int d, int newD) {

        Node cNode = list.head, prev = null;

        // Case 1: If element is present at the head.
        if (cNode != null && cNode.data == d) {
            cNode.data = newD;
            System.out.println(d + " found and updated");
            return list;
        }

        // Case 2: Finding element by traversing the list.
        while (cNode != null && cNode.data != d) {
            prev = cNode;
            cNode = cNode.next;
        }

        if (cNode != null) {
            cNode.data = newD;
            System.out.println(d + " found and updated");
        }

        // Case 3: If element is not in the list.
        if (cNode == null) {
            System.out.println(d + " not found");
        }

        return list;
    }

    public static SinglyLinkedList sort(SinglyLinkedList list) {
        Node cNode = list.head;
        Node nNode = list.head.next;
        int temp;
        while (cNode != null) {
            while (nNode != null) {
                if (cNode.data > nNode.data) {
                    temp = cNode.data;
                    cNode.data = nNode.data;
                    nNode.data = temp;
                }
                nNode = nNode.next;
            }
            cNode = cNode.next;
            nNode = cNode.next;
        }
        return list;
    }

    // Print method: Basically prints linked list after every operation.
    public static void print(SinglyLinkedList list) {
        Node currNode = list.head;
        System.out.print(" LinkedList:  ");

        // Traverse through the LinkedList
        while (currNode != null) {
            // Print the data at current node
            System.out.print(currNode.data + "->");
            // Go to next node
            currNode = currNode.next;
        }
        System.out.println("null");
    }

    // Main Method

    public static void main(String[] args) {
        // Creating a LinkedList
        SinglyLinkedList list = new SinglyLinkedList();
        Boolean flag = true;
        while (flag) {
            System.out.println("Choose an option from the Dashboard given below: ");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Update");
            System.out.println("4. Sorting");
            System.out.println("5. Exit");
            System.out.println();
            Scanner sc = new Scanner(System.in);
            int ch = sc.nextInt();
            if (ch < 1 || ch > 5) {
                System.out.println("Invalid choice");
            } else {
                switch (ch) {
                    case 1:
                        System.out.println("Enter the value to be inserted");
                        int i_value = sc.nextInt();
                        list = insert(list, i_value);
                        break;
                    case 2:
                        System.out.println("Enter the value to be deleted");
                        int d_value = sc.nextInt();
                        list = delete(list, d_value);
                        break;
                    case 3:
                        System.out.println("Enter the value of the node which is to be updated.");
                        int s_value = sc.nextInt();
                        int n_value = sc.nextInt();
                        list = update(list, s_value, n_value);
                        break;
                    case 4:
                        System.out.println("Sorting the list");
                        list = sort(list);
                        break;
                    case 5:
                        flag = false;
                        break;
                }
                print(list);
                if (flag == false) {
                    System.out.println("Exiting the program.");
                }
            }
        }

    }
}