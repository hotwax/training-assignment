import java.util.Scanner;

// Class to implement Hash Table
class Chaining {
    public int collisions = 0;
    Node[] table;
    int size;
    int elementCount;

    // Node class for creating a node of the hash table
    static class Node {
        int data;
        Node next;

        // Constructor
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Constructor
    Chaining(int size) {
        this.size = size;
        this.table = new Node[size];
        this.elementCount = 0;
    }

    // Hash function to get the position of the element in the hash table
    int hashFunction(int element) {
        return element % this.size;
    }

    // Method to insert an element in the hash table
    void insert(int element) {
        int position = this.hashFunction(element);
        if (this.table[position] == null) {
            this.table[position] = new Node(element);
            // If we found a collision, we will use chaining to store the element
        } else {
            this.collisions += 1;
            Node temp = this.table[position];
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node(element);
        }
        this.elementCount += 1;
        return;
    }

    // Method to print the hash table
    void display() {
        for (int i = 0; i < this.size; i++) {
            System.out.print(i + "->");
            Node temp = this.table[i];
            while (temp != null) {
                System.out.print(temp.data + "->");
                temp = temp.next;
            }
            System.out.println("Number of collisions: " + this.collisions);
            System.out.println();
        }
        return;
    }

    // Method to search an element in the hash table
    int search(int element) {
        int position = this.hashFunction(element);
        Node temp = this.table[position];
        while (temp != null) {
            if (temp.data == element) {
                return position;
            }
            temp = temp.next;
        }
        return -1;
    }

    // Method to remove an element from the hash table
    void remove(int element) {
        int position = this.search(element);

        // If the element is found in the hash table
        if (position != -1) {
            Node temp = this.table[position];
            if (temp.data == element) {
                this.table[position] = this.table[position].next;
            } else {
                // traverse the linked list to find the element
                while (temp.next.data != element) {
                    temp = temp.next;
                }
                temp.next = temp.next.next;
            }
            System.out.println("Element " + element + " is deleted");
            this.elementCount -= 1;
        }
        // If the element is not found in the hash table
        else {
            System.out.println("Element not found");
        }
        return;
    }

    public static void main(String[] args) {
        System.out.println("Enter the size of the Hash Table");
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        Chaining table = new Chaining(input);
        Boolean flag = true;
        while (flag) {
            System.out.println("Select an option from the menu below");
            System.out.println("1. Insert a new element in the Hash Table");
            System.out.println("2. Delete an element from the Hash Table");
            System.out.println("3. Exit");
            int choice = sc.nextInt();
            if (choice < 1 || choice > 3) {
                System.out.println("Invalid choice");
            } else {
                switch (choice) {
                    case 1:
                        System.out.println("Enter the value to be inserted");
                        int value = sc.nextInt();
                        table.insert(value);
                        break;
                    case 2:
                        System.out.println("Enter the key to be deleted");
                        int d_key = sc.nextInt();
                        table.remove(d_key);
                        break;
                    case 3:
                        flag = false;
                        break;
                }
                table.display();
                if (flag == false) {
                    System.out.println("Exiting the program");
                }
            }
        }
    }
}
