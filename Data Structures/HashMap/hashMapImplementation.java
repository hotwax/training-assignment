import java.util.InputMismatchException;
import java.util.Scanner;
class hashMapImplementation {
    public static void main(String[] args) {

        // Variable to store the choice of the user
        map hashmap = new map(0);
        int choice = 0;
        do {
            //  Printing the menu for the user
            System.out.println(" --------- MENU --------- ");
            System.out.println("Press 0 : Create a new HashMap");
            System.out.println("Press 1 : Insert an element");
            System.out.println("Press 2 : Delete an element");
            System.out.println("Press 3 : Get an element");
            System.out.println("Press 4 : Display/Traversal the LinkedList");
            System.out.println("Press 5 : Search an element");
            System.out.println("Press 6 : Exit");
            System.out.println("Enter your choice : ");
            try {
                Scanner sc = new Scanner(System.in);
                choice = sc.nextInt();
                //  Taking the choice from the user and performing the corresponding operation
                switch (choice) {
                    case 0:
                        System.out.print("Please Enter the size of the HashMap: ");
                        hashmap = new map(sc.nextInt());
                        break;
                    case 1:
                        System.out.println("Please Enter the key and value: ");
                        hashmap.insert(sc.nextInt(), sc.nextInt());
                        break;
                    case 2:
                        System.out.println("Please Enter the Node Value: ");
                        hashmap.delete(sc.nextInt());
                        break;
                    case 3:
                        System.out.print("Please Enter the Node Value: ");
                        System.out.println(hashmap.get(sc.nextInt()));
                        break;
                    case 4:
                        hashmap.display();
                        break;
                    case 5:
                        System.out.println("Enter the key to search: ");
                        hashmap.search(sc.nextInt());
                        break;
                    case 6 :
                        System.out.println("Exiting the program");
                        break;
                    default:
                        System.out.println("Invalid Input");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter Integer Value Only!!");
            }
        } while (choice != 6);
    }

}

class Pair {
    int key;
    int value;
    Pair next;
    Pair(int key, int value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}
class map {
    // size of the hash map
    int size;
    // Array of LinkedList list
    Pair[] arr;

    map(int size) {
        this.size = size;
        arr = new Pair[size];
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
    }

    //  Function to insert a key value pair in the hash map
    public int hashFunction(int key) {
        return key % size;
    }

    //  Function to insert a key value pair in the hash map
    public void insert(int key, int value) {
        int index = hashFunction(key);
        Pair newNode = new Pair(key, value);
        if (arr[index] == null) {
            arr[index] = newNode;
        } else {
            Pair head = arr[index];
            while (head.next != null) {
                head = head.next;
            }
            head.next = newNode;
        }
    }

    //  Function to search a key in the hash map
    public void delete(int key) {
        int index = hashFunction(key);
        Pair head = arr[index];
        if (head.key == key) {
            arr[index] = head.next;
        } else {
            while (head.next != null) {
                if (head.next.key == key) {
                    head.next = head.next.next;
                    break;
                }
                head = head.next;
            }
        }
    }

    //  Function to search a key in the hash map
    public int get(int key) {
        int index = hashFunction(key);
        Pair head = arr[index];
        while (head != null) {
            if (head.key == key) {
                return head.value;
            }
            head = head.next;
        }
        return -1;
    }
    // method to display the hash map
    public void display() {
        for (int index = 0; index < size; index++) {
            // temp is a pointer to the head of the linked list at index
            Pair head = arr[index];
            System.out.print("Index " + index + " is connected to: ");
            while (head != null) {
                System.out.print(head.key + "->" + head.value + "  ");
                head = head.next;
            }
            System.out.println();
        }

    }
    // Method to search a key in the hash map
    public void search(int nextInt) {
        int index = hashFunction(nextInt);
        Pair head = arr[index];
        while (head != null) {
            if (head.key == nextInt) {
                System.out.println(head.key + "->" + head.value + " is present in the hash map");

                return;
            }
            head = head.next;
        }
        System.out.println("Key not found");
    }
}