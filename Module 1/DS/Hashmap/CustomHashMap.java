import java.util.Scanner;
import java.util.InputMismatchException;

public class CustomHashMap {
    static class Pair {
        int key;
        int value;
        Pair next;

        Pair(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    static class hashMap {
        int size;
        Pair[] arr;

        hashMap(int size) {
            this.size = size;
            arr = new Pair[size];
            for (int i = 0; i < size; i++) {
                arr[i] = null;
            }
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

        // Function to search a key in the hash map
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

        public void display() {
            for (int i = 0; i < size; i++) {
                Pair temp = arr[i];
                System.out.print("Index " + i + " is connected to: ");
                while (temp != null) {
                    System.out.print(temp.key + "->" + temp.value + "  ");
                    temp = temp.next;
                }
                System.out.println();
            }

        }

        public void search(int nextInt) {
            int index = hashFunction(nextInt);
            Pair temp = arr[index];
            while (temp != null) {
                if (temp.key == nextInt) {
                    System.out.println(temp.key + "->" + temp.value + " is present in the hash map");

                    return;
                }
                temp = temp.next;
            }
            System.out.println("Key not found");
        }

    }

    public static void main(String[] args) {
        System.out.println("Enter the size of the Hash map");

        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        // Variable to store the choice of the user
        hashMap hashmap = new hashMap(size);
        int choice;
        boolean flag = true;
        while(flag) {
            // Printing the menu for the user
            System.out.println();
            System.out.println("Select an option from the DashBoard given below: ");
            System.out.println("1 : Insert ");
            System.out.println("2 : Delete ");
            System.out.println("3 : Get value of the key.");
            System.out.println("4 : Display the Hashmap.");
            System.out.println("5 : Search an element");
            System.out.println("6 : Exit");
            System.out.println("Enter your choice : ");
            choice = sc.nextInt();
            // Taking the choice from the user and performing the corresponding operation
            try {
                switch (choice) {
                    case 1:
                        System.out.println("Please Enter the key and value: ");
                        hashmap.insert(sc.nextInt(), sc.nextInt());
                        System.out.println("The key and value is inserted");
                        break;
                    case 2:
                        System.out.println("Please Enter the Node Value: ");
                        hashmap.delete(sc.nextInt());
                        System.out.println("The node is deleted.");
                        break;
                    case 3:
                        System.out.print("Please Enter the key whose value you want: ");
                        System.out.println(hashmap.get(sc.nextInt()));
                        break;
                    case 4:
                        hashmap.display();
                        break;
                    case 5:
                        System.out.println("Enter the key to search: ");
                        hashmap.search(sc.nextInt());
                        break;
                    case 6:
                        System.out.println("Exiting the program.");
                        flag = false;
                        break;
                    default:
                        System.out.println("Invalid Input.");
                        break;

                }
            } catch (InputMismatchException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

}
