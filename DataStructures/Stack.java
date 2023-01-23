import java.io.*;
import java.util.Scanner;

public class Stack {

    // Pointer to top of stack
    Node top;

    // Node class for creating nodes of stack.
    static class Node {
        int data;
        Node next;

        // Constructor
        Node(int d) {
            data = d;
            next = null;
        }
    }

    // Method to push a new element in the stack
    public static Stack push(Stack stack, int value) {
        Node temp = new Node(value);

        // If stack is empty, then new node is top of stack
        if (stack.top == null) {
            stack.top = temp;
            return stack;
        }

        // Add the new node at the top of stack and change top
        temp.next = stack.top;
        stack.top = temp;

        return stack;
    }

    // Method to pop an element from the stack
    public static Stack pop(Stack stack) {
        if (stack.top == null)
            return stack;

        Node temp = stack.top;
        stack.top = stack.top.next;

        // Return the list by head
        return stack;
    }

    // update a value in the stack
    public static Stack update(Stack stack, int s_value, int n_value) {
        // Store top node
        if (stack.top == null)
            return stack;

        // Store previous top and move top one node ahead
        Node temp = stack.top;
        while (temp != null) {
            if (temp.data == s_value) {
                temp.data = n_value;
            }
            temp = temp.next;
        }

        // Return the list by head
        return stack;
    }

    // method to print the stack
    public static void printStack(Stack stack) {
        Node currNode = stack.top;
        System.out.print("Stack: ");

        // Traverse through the Stack
        while (currNode != null) {
            // Print the data at current node
            System.out.print(currNode.data + " ");
            // Go to next node
            currNode = currNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Create a Stack
        Stack stack = new Stack();
        Boolean flag = true;
        while (flag) {
            System.out.println("Select an option from the menu below");
            System.out.println("1. Push a new element in the stack");
            System.out.println("2. Pop an element from the stack");
            System.out.println("3. Update an element in stack");
            System.out.println("4. Exit");
            Scanner input = new Scanner(System.in);
            int choice = input.nextInt();
            if (choice < 1 || choice > 4) {
                System.out.println("Invalid choice");
            } else {
                switch (choice) {
                    case 1:
                        System.out.println("Enter the value to be inserted");
                        int i_value = input.nextInt();
                        stack = push(stack, i_value);
                        break;
                    case 2:
                        stack = pop(stack);
                        break;
                    case 3:
                        System.out.println("Enter the value to be updated and the new value");
                        int s_value = input.nextInt();
                        int n_value = input.nextInt();
                        stack = update(stack, s_value, n_value);
                        break;
                    case 4:
                        flag = false;
                        break;
                }
                printStack(stack);
                if (flag == false) {
                    System.out.println("Exiting the program");
                }
            }
        }

    }
}
