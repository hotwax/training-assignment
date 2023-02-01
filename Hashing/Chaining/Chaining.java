package  Hashing.Chaining;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Chaining {
    static class Pair {
        // key
        int key;
        int value;
        Pair next;

        Pair(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public static class hashMap {
        int size;
        Pair[] arr;
        int collision;

        public hashMap(int size) {
            this.size = size;
            arr = new Pair[size];
            for (int i = 0; i < size; i++) {
                arr[i] = null;
            }
            collision = 0;
        }

        // Function to insert a key value pair in the hash map
        public int hashFunction(int key) {
            return key % size;
        }

        // Function to insert a key value pair in the hash map
        public void insert(int key, int value) {
            int index = hashFunction(key);
            Pair newNode = new Pair(key, value);
            if (arr[index] == null) {
                arr[index] = newNode;
            } else {
                Pair temp = arr[index];
                while (temp.next != null) {
                    temp = temp.next;
                }
                collision++;
                temp.next = newNode;
            }
        }

        // Function to search a key in the hash map
        public void delete(int key) {
            int index = hashFunction(key);
            Pair temp = arr[index];
            if (temp.key == key) {
                arr[index] = temp.next;
            } else {
                while (temp.next != null) {
                    if (temp.next.key == key) {
                        temp.next = temp.next.next;
                        break;
                    }
                    temp = temp.next;
                }
            }
        }

        // Function to get a value from the hash map
        public int get(int key) {
            int index = hashFunction(key);
            Pair temp = arr[index];
            while (temp != null) {
                if (temp.key == key) {
                    return temp.value;
                }
                temp = temp.next;
            }
            return -1;
        }

        // Function to display the hash map
        public void display() {
            for (int i = 0; i < size; i++) {
                Pair head = arr[i];
                while (head != null) {
                    System.out.print(head.key + " -> " + head.value);
                    head = head.next;
                }
                System.out.println();
            }
        }

        // Function to get all the collisions
        public int getCollision() {
            return collision;
        }
    }

    public static void main(String[] args) {
        hashMap map = null;

        int choice = 0;
        do {
            System.out.println("--------- MENU ---------");
            System.out.println("Press 0 : Create a new Hashmap");
            System.out.println("Press 1 : Insert  Value");
            System.out.println("Press 2 : Delete Value");
            System.out.println("Press 3 : Display the Hashmap");
            System.out.println("Press 4 : Get All Collisions");
            System.out.println("Press 5 : Get Value from Key");
            System.out.println("Press 6 : Exit");
            System.out.println("Enter your choice : ");
            try {
                Scanner sc = new Scanner(System.in);
                choice = sc.nextInt();
                switch (choice) {
                    case 0:
                        System.out.print("Please Enter the size of the HashMap: ");
                        map = new hashMap(sc.nextInt());
                        break;
                    case 1:
                        if (map != null) {
                            System.out.print("Please Enter the key: ");
                            int key = sc.nextInt();
                            System.out.print("Please Enter the value: ");
                            int value = sc.nextInt();
                            map.insert(key, value);
                        }
                        break;
                    case 2:
                        if (map != null) {
                            System.out.print("Please Enter the key: ");
                            map.delete(sc.nextInt());
                            System.out.println("Deleted");
                        }
                        break;
                    case 3:
                        if (map != null)
                            map.display();
                        break;
                    case 4:
                        if (map != null) {
                            System.out.println("Number of collisions: " + map.getCollision());
                        }
                        break;
                    case 5:
                        if (map != null) {
                            System.out.print("Please Enter the key: ");
                            int key1 = sc.nextInt();
                            System.out.println("Value: " + map.get(key1));
                        }
                        break;
                    case 6:
                        System.out.println("Program Terminated");
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter Integer Value Only");
            }
        } while (choice != 6);
    }
}
