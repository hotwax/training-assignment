import java.util.Scanner;

public class Stack {

    Node head;

    // create a Node class to represent each element in the stack
    class Node {
        int data;
        Node next;

        // constructor to initialize a new node
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }


    // method to remove the top element from the stack
    boolean pop() {
        if (head == null) {
            return false;
        } else {
            head = head.next;
            return true;
        }
    }

    // method to check if the stack is empty
    boolean isEmpty() {
        return head == null;
    }

    // method to add a new element to the top of the stack
    void push(int data) {
        Node newnode = new Node(data);
        if (head == null) {
            head = newnode;
            return;
        }
        newnode.next = head;
        head = newnode;
    }

    // method to display the elements of the stack
    void display() {
        Node ptr = head;
        while (ptr != null) {
            System.out.print(ptr.data + " ");
            ptr = ptr.next;
        }
        System.out.println();
    }

    // method to return the value of the top element of the stack without removing it
    int peek() {
        if (head == null) {
            return -1;
        } else {
            return head.data;
        }
    }


    // method to search for an element in the stack and return true if it exists, false otherwise
    boolean search(int key) {
        Node ptr = head;
        while (ptr != null) {
            if (ptr.data == key) {
                return true;
            }
            ptr = ptr.next;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack stack = new Stack();
        int choice = 0;
        do {
            System.out.println("Stack Operations:");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Search");
            System.out.println("5. Display");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter value to push: ");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case 2:
                    if (stack.pop()) {
                        System.out.println("Popped successfully.");
                    } else {
                        System.out.println("Stack is empty.");
                    }
                    break;
                case 3:
                    int top = stack.peek();
                    if (top == -1) {
                        System.out.println("Stack is empty.");
                    } else {
                        System.out.println("Top element: " + top);
                    }
                    break;
                case 4:
                    System.out.print("Enter value to search: ");
                    int key = scanner.nextInt();
                    if (stack.search(key)) {
                        System.out.println("Value found in stack.");
                    } else {
                        System.out.println("Value not found in stack.");
                    }
                    break;
                case 5:
                    System.out.print("Stack: ");
                    stack.display();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (choice != 6);
        scanner.close();
    }

}
