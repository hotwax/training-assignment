import java.util.InputMismatchException;
import java.util.Scanner;


class Queue {
    Node head;
    int len;

    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    int peek() {
        if (head == null) {
            return -1;
        } else {
            return head.data;
        }
    }

    void enqueue(int data) {
        Node newnode = new Node(data);

        if (head == null) {
            head = newnode;
            len += 1;
            return;
        }

        Node ptr = head;
        while (ptr.next != null) {
            ptr = ptr.next;
        }

        ptr.next = newnode;
        len += 1;
    }

    boolean dequeue() {
        if (head == null) {
            return false;
        }

        head = head.next;
        len -= 1;
        return true;
    }

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

    void display() {
        Node ptr = head;
        if (ptr==null){
            System.out.println("Queue is empty");
        }
        while (ptr != null) {
            System.out.print(ptr.data + " ");
            ptr = ptr.next;
        }
    }


    void update(int index, int value) {
        if (index < 0 || index >= len) {
            System.out.println("Invalid index");
            return;
        }

        Node ptr = head;
        for (int i = 0; i < index; i++) {
            ptr = ptr.next;
        }

        ptr.data = value;

    }

    boolean isEmpty() {
        return head == null;
    }

    public static void main(String[] args) {

        try{
        Scanner sc = new Scanner(System.in);
        Queue queue = new Queue();

        while (true) {
            
            System.out.println("\nChoose an operation:");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek");
            System.out.println("4. Search");
            System.out.println("5. Display");
            System.out.println("6. Update");
            System.out.println("7. Check if empty");
            System.out.println("8. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter data to enqueue: ");
                    int data = sc.nextInt();
                    queue.enqueue(data);
                    break;

                case 2:
                    boolean resp= queue.dequeue();
                    if (resp == false) {
                        System.out.println("Queue is empty");
                    } else {
                        System.out.println("Element dequed");
                    }
                    break;

                case 3:
                    int peekValue = queue.peek();
                    if (peekValue == -1) {
                        System.out.println("Queue is empty");
                    } else {
                        System.out.println("Peek value is " + peekValue);
                    }
                    break;

                case 4:
                    System.out.println("Enter key to search: ");
                    int key = sc.nextInt();
                    boolean isFound = queue.search(key);
                    if (isFound) {
                        System.out.println(key + " is present in the queue");
                    } else {
                        System.out.println(key + " is not present in the queue");
                    }
                    break;

                case 5:
                    System.out.println("Queue elements:");
                    queue.display();
                    break;

                    case 6:
                    if (queue.isEmpty()) {
                        System.out.println("Queue is empty");
                    } else {
                        System.out.println("Enter index to update: ");
                        int index = sc.nextInt();
                        System.out.println("Enter value to update: ");
                        int value = sc.nextInt();
                        queue.update(index, value);                    }
                    break;

                case 7:
                    if (queue.isEmpty()) {
                        System.out.println("Queue is empty");
                    } else {
                        System.out.println("Queue is not empty");
                    }
                    break;

                case 8:
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }}catch(InputMismatchException e){
            System.out.println("Invalid input, Please Enter a valid input. " );
            System.out.println("Exception: " + e + "");
        }

        catch(Exception e){
            System.out.println("Exception: " + e + "");
        }
    }
}

