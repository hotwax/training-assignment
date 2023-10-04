
//Importing Scanner class for input
import java.util.Scanner;
import java.util.InputMismatchException;

//LinkedList class
public class LinkedList {
    Node head;

    // Node class
    static class Node {
        int data;
        Node next;

        // constructor for Node class
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Method to insert New Node at the End of LinkedList
    public void insertEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node currNode = head;
        while (currNode.next != null) {
            currNode = currNode.next;
        }
        currNode.next = newNode;
    }

    // method to insert New Node at the beginning of Linkedlist
    public Node insertBegin(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        return head;
    }

    // method to insert at any position
    public void insertPosition(int position, int data) {
        Node newNode = new Node(data);
        newNode.data = data;
        newNode.next = null;

        // if user give negative numbers or zero as position
        if (position < 1)
            System.out.println("Invalid");

        // if Linkedlist is Empty
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        // inserting first node
        else if (position == 1) {
            newNode.next = head;
            head = newNode;
        }

        else {
            Node temprefhead = head;

            // traverse till the given position node
            while (--position > 1) {
                temprefhead = temprefhead.next;
            }
            newNode.next = temprefhead.next;
            temprefhead.next = newNode;
        }
    }

    // Method to delete node from the end of LinkedList
    public void deleteEnd() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        // LinkedList have one node
        if (head.next == null) {
            head = head.next;
        }
        Node prev = null;
        Node temprefhead = head;

        while (temprefhead.next != null) {
            prev = temprefhead;
            temprefhead = temprefhead.next;
        }
        prev.next = null;
    }

    // Method to delete node from Beginning LinkedList
    public void deleteBegin() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        // move head to next node
        head = head.next;
    }

    // Method to delete node from any position of LinkedList
    public void deletePosition(int position) {
        Node temprefhead = head;
        Node prevNode = null;

        if (position < 1) {
            System.out.println("Invalid");
            return;
        }

        // delete the first node
        if (position == 1) {
            head = head.next;
            return;
        }

        // traverse to the given position node
        while (--position > 0) {
            prevNode = temprefhead;
            temprefhead = temprefhead.next;
        }
        prevNode.next = temprefhead.next;
    }

    // Method to sort LinkedList
    public void sortList() {
        Node currNode = head, index = null;
        int tempsort;
        if (head == null) {
            return;
        } else {
            while (currNode != null) {
                index = currNode.next;
                while (index != null) {
                    if (currNode.data > index.data) {
                        tempsort = currNode.data;
                        currNode.data = index.data;
                        index.data = tempsort;
                    }
                    index = index.next;
                }
                currNode = currNode.next;
            }
        }
    }

    // Method to update Linkedlist
    public void update(int oldData, int newData) {
        Node currNode = head;
        while (currNode != null) {
            if (currNode.data == oldData) {
                currNode.data = newData;
                return;
            }
            currNode = currNode.next;
        }
    }

    // Method to display LinkedList
    public void display() {
        Node currNode = head;
        if (currNode == null) {
            System.out.println("List is empty");
            return;
        }
        while (currNode != null) {
            System.out.print(currNode.data + " ");
            currNode = currNode.next;
        }
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        try (Scanner input = new Scanner(System.in)) {
            int choice, data, oldData, newData;
            do {
                System.out.println();
                System.out.println("1. Insert element at End");
                System.out.println("2. Insert at Begining");
                System.out.println("3. Insert at any Position");
                System.out.println("4. Delete element at End");
                System.out.println("5. Delete element at Begin");
                System.out.println("6. Delete element at any position");
                System.out.println("7. Sort list");
                System.out.println("8. Update element");
                System.out.println("9. Display list");
                System.out.println("10. Exit");
                System.out.print("Enter your choice: ");
                choice = input.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter the element to be insert at End: ");
                        data = input.nextInt();
                        list.insertEnd(data);
                        break;
                    case 2:
                        System.out.print("Enter the element to be inserted in begin: ");
                        data = input.nextInt();
                        list.insertBegin(data);
                        break;
                    case 3:
                        System.out.print("Enter the position and element to be inserted: ");
                        int position = input.nextInt();
                        data = input.nextInt();
                        list.insertPosition(position, data);
                        break;
                    case 4:
                        list.deleteEnd();
                        break;
                    case 5:
                        list.deleteBegin();
                        break;
                    case 6:
                        System.out.println("Enter the position from which element to be deleted: ");
                        position = input.nextInt();
                        list.deletePosition(position);
                        break;
                    case 7:
                        list.sortList();
                        break;
                    case 8:
                        System.out.print("Enter the old element: ");
                        oldData = input.nextInt();
                        System.out.print("Enter the new element: ");
                        newData = input.nextInt();
                        list.update(oldData, newData);
                        break;
                    case 9:
                        list.display();
                        break;
                    case 10:
                        System.out.println("Exit done");
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } while (choice != 10);
        } catch (InputMismatchException exception) {
            System.out.println(exception);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}