import java.util.Scanner;

class HashMap {
    public static Pair[] map;
    public static int size;

    // Constructor to initialize the size of the hash map
    HashMap(int size) {
        map = new Pair[size];
        this.size = size;
        for (int i = 0; i < size; i++) {
            map[i] = null;
        }
    }

    // Class to store the key and value
    static class Pair {
        int key;
        int value;
        Pair next;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public static int hash(int key, int size) {
        return key % size;
    }

    public static void insert(int key, int value) {
        int index = hash(key, size);
        Pair pair = new Pair(key, value);
        if (map[index] == null) {
            map[index] = pair;
        } else {
            Pair temp = map[index];
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = pair;
        }
    }

    // Method to delete an element from the hash map
    public static void delete(int key) {
        int index = hash(key, size);
        if (map[index] == null) {
            System.out.println("Key not found");
        } else {
            Pair temp = map[index];
            Pair prev = null;
            while (temp.key != key && temp.next != null) {
                prev = temp;
                temp = temp.next;
            }
            if (temp.key == key) {
                if (prev == null) {
                    map[index] = null;
                } else {
                    prev.next = temp.next;
                }
            } else {
                System.out.println("Key not found");
            }
        }
    }

    // Method to search an element in the hash map
    public static void search(int key) {
        int index = hash(key, size);
        if (map[index] == null) {
            System.out.println("Key not found");
        } else {
            Pair temp = map[index];
            while (temp.key != key && temp.next != null) {
                temp = temp.next;
            }
            if (temp.key == key) {
                System.out.println("Key found");
                System.out.println("Value: " + temp.value);
            } else {
                System.out.println("Key not found");
            }
        }
    }

    // Method to print the hash map
    public static void display() {
        for (int i = 0; i < size; i++) {
            Pair temp = map[i];
            System.out.print(i + ": ");
            while (temp != null) {
                System.out.print("[" + temp.key + "," + temp.value + "]");
                temp = temp.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the hash map");
        int size = sc.nextInt();
        HashMap hmap = new HashMap(size);
        Boolean flag = true;
        while (flag) {
            Scanner input = new Scanner(System.in);
            System.out.println("Select an option from the menu below");
            System.out.println("1. Insert a new element in the Hash Table");
            System.out.println("2. Delete an element from the Hash Table");
            System.out.println("3. Search an element in the Hash Table by key");
            System.out.println("4. Exit");
            int choice = input.nextInt();
            if (choice < 1 || choice > 4) {
                System.out.println("Invalid choice");
            } else {
                switch (choice) {
                    case 1:
                        System.out.println("Enter the key and value to be inserted");
                        int key = input.nextInt();
                        int value = input.nextInt();
                        insert(key, value);
                        break;
                    case 2:
                        System.out.println("Enter the key to be deleted");
                        int d_key = input.nextInt();
                        delete(d_key);
                        break;
                    case 3:
                        System.out.println("Enter the key to be searched");
                        int s_key = input.nextInt();
                        search(s_key);
                        break;
                    case 4:
                        flag = false;
                        break;
                }
                display();
                if (flag == false) {
                    System.out.println("Exiting the program");
                }
            }
        }
    }
}
