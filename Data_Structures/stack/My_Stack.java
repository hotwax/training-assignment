package Data_Structures.stack;

import java.util.Scanner;

// LINKED LIST BASED IMPLEMENTATION
public class My_Stack {

    Node head;

    public class Node {

        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public void push(int value_to_add) {
        //if head node is null then create one 
        if (head == null) {
            head = new Node(value_to_add);
            System.out.println("Added Successfully.");
            return;
        }
        //inset at the end of the linked list and make newNode head
        Node newNode = new Node(value_to_add);
        newNode.next = head;
        head = newNode;
        System.out.println("Added Successfully.");
    }

    public int pop() {
        //get the latest inserted item and delete it as well
        if (head == null) {
            System.out.println("stack is empty");
            return -1;
        }
        int removed = head.val;
        head = head.next;
        return removed;
    }

    public int peek() {
        //get the latest inserted item
        if (head == null) {
            System.out.println("stack is empty");
            return -1;
        } else {
            return head.val;
        }
    }

    public void update(int prev_val, int new_val) {
        //iterate all over stack and update the value if present
        Node curr = head;
        while (curr != null) {
            if (curr.val == prev_val) {
                curr.val = new_val;
                System.out.println("Updated Successfully.");
                return;
            }
            curr = curr.next;
        }
        System.out.println("Element to update is not found.");
    }

    public boolean search(int value_to_search) {
        //iterate all over stack and if value is present return true
        Node curr = head;
        while (curr != null) {
            if (curr.val == value_to_search) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public void traverseAll() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public void menu() {
        boolean terminationFlag = true;
        while (terminationFlag) {
            System.out.println("===========================");
            System.out.println("1: push");
            System.out.println("2: pop");
            System.out.println("3: peek");
            System.out.println("4: traverse");
            System.out.println("5: update");
            System.out.println("6: search");
            System.out.println("7: terminate the program");
            System.out.println();

            try {
                Scanner sc = new Scanner(System.in);
                int n = sc.nextInt();
                switch (n) {
                    case 1:// push
                        System.out.println("enter no. to push");
                        push(sc.nextInt());
                        break;
                    case 2:// pop
                        System.out.println("popped value= " + pop());
                        break;
                    case 3:// peek
                        System.out.println("peeked value= " + peek());
                        break;
                    case 4:// traverse
                        traverseAll();
                        break;
                    case 5:// update
                        System.out.println("enter no. to update and the value");
                        update(sc.nextInt(), sc.nextInt());
                        break;
                    case 6:// search
                        System.out.println("enter value to search");
                        System.out.println(search(sc.nextInt()));
                        break;
                    case 7:// end
                        terminationFlag = false;
                        break;
                    default:
                        System.out.println("invalid input");
                        break;
                }
            } catch (Exception e) {
                //if user enter any other value than integer
                System.out.println("Invalid input: please enter Integer");
            }
        }
    }

    public static void main(String args[]) {
        My_Stack stack = new My_Stack();
        stack.menu();
    }
}
