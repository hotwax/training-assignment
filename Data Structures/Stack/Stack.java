 /*
     *  Implementation of a Stack for Integers.
     *  This can be also be converted into a generic Stack by changing the
     *  type of the data to a generic type.
 */

//Importing Scanner class for user input
import java.util.InputMismatchException;
import java.util.Scanner;

    public class Stack {
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
        //  Scanner object to take input from the user
        Scanner sc = new Scanner(System.in);
        public static void main(String[] args) {
            // Create a blank stack
            Node head = null;
            //  Scanner object to take input from the user
            Scanner sc = new Scanner(System.in);
            // Variable to store the choice of the user
            int choice = 0;
                do {
                    //  Printing the menu for the user
                    System.out.println("--------- MENU ---------");
                    System.out.println("Press 0 : Create a New Stack");
                    System.out.println("Press 1 : Push an element");
                    System.out.println("Press 2 : Pop an Element");
                    System.out.println("Press 3 : Display/Traversal the Stack");
                    System.out.println("Press 4 : Peek Element");
                    System.out.println("Press 5 : Program Terminated");
                    System.out.println("Enter your choice : ");
                    try{
                    choice = sc.nextInt();
                    //  Taking the choice from the user and performing the corresponding operation
                        switch (choice) {
                            case 0:
                                System.out.print("Please Enter the first Element: ");
                                head = createStack(sc.nextInt());
                                break;
                            case 1:
                                if (head != null) {
                                    System.out.print("Please Enter the Element Value: ");
                                    head = push(head, sc.nextInt());
                                } else {
                                    System.out.println("Please create a Stack first");
                                }
                                break;
                            case 2:
                                head = pop(head);
                                break;
                            case 3:
                                displayStack(head);
                                break;
                            case 4:
                                peek(head);
                                break;
                            case 5:
                                System.out.println("Program Terminated Successfully");
                                break;
                            default:
                                System.out.println("Invalid Input");
                        }
                        } catch (InputMismatchException e){
                        System.out.println("Enter Integer Only");
                        sc.next();
                        }
                } while (choice != 5);

        }

        private static void peek(Node head) {
            if(head == null)
                System.out.println("Stack is empty");
            else
                System.out.println("The top element is: " + head.data);
        }

        //  Method to create a new Stack
        private static Node createStack(int nextInt) {
            Node head = new Node(nextInt);
            return head;
        }

//        Method to push an element into the Stack
        private static Node push(Node head, int nextInt) {
            Node temp = new Node(nextInt);
            temp.next = head;
            head = temp;
            return head;
        }
//        Method to display the stack
        private static void displayStack(Node head) {
            Node temp = head;
            if(temp == null)
                System.out.println("Stack is empty");
            else {
                System.out.println("The Stack is: ");
                while (temp != null) {
                    System.out.print(temp.data + " ");
                    temp = temp.next;
                }
                System.out.println();
            }
        }
        // Method to pop an element from the Stack
        private static Node pop(Node head) {
           if(head == null){
               System.out.println("Stack is empty");
               return null;
           }
           else{
               System.out.println("The deleted element is: " + head.data);
               head = head.next;
               return head;
           }
        }

    }
