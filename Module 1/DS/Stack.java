import java.io.*;
import java.util.Scanner;

public class Stack {

    
    Node top; // This node points to the top of the stack.

    // Node class
    static class Node {
        int data;
        Node next;

        // Constructor
        Node(int d) {
            data = d;
            next = null;
        }
    }

    // Push Method
    public static Stack push(Stack s, int v) {
        Node temp = new Node(v);

        // Case 1: If stack is empty
        if (s.top == null) {
            s.top = temp;
            return s;
        }

        // Case 2: If stack is not empty
        temp.next = s.top;
        s.top = temp;

        return s;
    }

    // Pop Method
    public static Stack pop(Stack s) {
        if (s.top == null) {
            return s;
        }
        s.top = s.top.next;
        return s;
    }

    // Update Method 
    public static Stack update(Stack s, int v1, int v2) {
        if (s.top == null)
            return s;

        // Store previous top and move top one node ahead
        Node val = s.top;
        while (val != null) {
            if (val.data == v1) {
                val.data = v2;
            }
            val = val.next;
        }

        return s;
    }

    // Print Method: To print stack after every operation. 
    public static void print(Stack s) {
        Node currNode = s.top;
        System.out.print("Stack: ");

        // Traverse through the Stack
        while (currNode != null) {
            System.out.print(currNode.data + " ");
            currNode = currNode.next;
        }
        System.out.println();
    }

    // Main Method

    public static void main(String[] args) {
        // Creating a Stack
        Stack s = new Stack();
        Boolean flag = true;
        while (flag) {
            System.out.println("Choose an option from the Dashboard given below:");
            System.out.println("1. Push ");
            System.out.println("2. Pop ");
            System.out.println("3. Update ");
            System.out.println("4. Exit");
            System.out.println();
            Scanner sc = new Scanner(System.in);
            int ch = sc.nextInt();
            if (ch < 1 || ch > 4) {
                System.out.println("Kindly enter the right option.");
            } else {
                switch (ch) {
                    case 1:
                        System.out.println("Enter the value to be inserted");
                        int i_value = sc.nextInt();
                        s = push(s, i_value);
                        break;
                    case 2:
                        s = pop(s);
                        break;
                    case 3:
                        System.out.println("Enter the value to be updated ");
                        int s_value = sc.nextInt();
                        int n_value = sc.nextInt();
                        s = update(s, s_value, n_value);
                        break;
                    case 4:
                        flag = false;
                        break;
                }
                System.out.println();
                print(s);
                System.out.println();
                if (flag == false) {
                    System.out.println("Exiting the program");
                }
            }
        }

    }
}