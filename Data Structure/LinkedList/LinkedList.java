import java.util.Scanner;

class LinkedList {
    Node head;

    static class Node {
        int data;
        Node next;
        //constructor for Node class
        Node(int d) {
            data = d;
            next = null;
        }
    }
    //Method to insert New Node in LinkedList
    public void insert(int data) {
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
    // Method to delete node from LinkedList
    public void delete(int data) {
        Node currNode = head, prev = null;
        if (currNode != null && currNode.data == data) {
            head = currNode.next;
            return;
        }
        while (currNode != null && currNode.data != data) {
            prev = currNode;
            currNode = currNode.next;
        }
        if (currNode != null) {
            prev.next = currNode.next;
        }
    }
    //Method to sort LinkedList
    public void sortList() {
        Node currNode = head, index = null;
        int temp;
        if (head == null) {
            return;
        } else {
            while (currNode != null) {
                index = currNode.next;
                while (index != null) {
                    if (currNode.data > index.data) {
                        temp = currNode.data;
                        currNode.data = index.data;
                        index.data = temp;
                    }
                    index = index.next;
                }
                currNode = currNode.next;
            }
        }
    }
    //Method to update Linkedlist
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
    //Method to display LinkedList
    public void display() {
        Node currNode = head;
        if (currNode == null) {
            System.out.println("List is empty");
            return;
        }
        System.out.print("LinkedList: ");
        while (currNode != null) {
            System.out.print(currNode.data + " ");
            currNode = currNode.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        Scanner sc = new Scanner(System.in);
        int choice, data, oldData, newData;
        do{
            System.out.println("1. Insert element");
            System.out.println("2. Delete element");
            System.out.println("3. Sort list");
            System.out.println("4. Update element");
            System.out.println("5. Display list");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter the element to be inserted: ");
                    data = sc.nextInt();
                    list.insert(data);
                    break;
                case 2:
                    System.out.print("Enter the element to be deleted: ");
                    data = sc.nextInt();
                    list.delete(data);
                    break;
                case 3:
                    list.sortList();
                    break;
                case 4:
                    System.out.print("Enter the old element: ");
                    oldData = sc.nextInt();
                    System.out.print("Enter the new element: ");
                    newData = sc.nextInt();
                    list.update(oldData, newData);
                    break;
                case 5:
                list.display();
                break;
                case 6:
                System.out.println("Exit done");
                break;
                default:
                System.out.println("Invalid choice");
            }
        }while(choice!=6);
    }
}