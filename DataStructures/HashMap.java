import java.util.Scanner;



    // Pair class to store key value pairs
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

    int size;

    // Array of pairs to store the key value pairs
    Pair[] arr;

    // Constructor to initialize the hash map
    HashMap(int size) {
        this.size = size;
        arr = new Pair[size];
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
    }

    // hash function to get the index of the key
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
            // if collision occurs linear probing is used to find the key
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

    // Function to display the hash map
    public void display() {
        for (int i = 0; i < size; i++) {
            Pair temp = arr[i];
            while (temp != null) {
                System.out.print(temp.key + "->" + temp.value + "  ");
     

        }

        
            

            
        
        if (temp.key == n
     

                return;
            }
            temp = temp.next;
        }
        System.out.println("Key not found");
    }

    public static void main(String[] args) {
        System.out.println("Enter the size of the hash map");
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        HashMap map = new HashMap(size);
        Boolean flag = true;
        while (flag) {
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
                        map.insert(key, value);
                        break;
                    case 2:
                        System.out.println("Enter the key to be deleted");
                        int d_key = input.nextInt();
                        map.delete(d_key);
                        break;
                    case 3:
                        System.out.println("Enter the key to be searched");
                        int s_key = input.nextInt();
                        map.search(s_key);
                        break;
                    case 4:
                        flag = false;
                        break;
                }
                map.display();
                if (flag == false) {
                    System.out.println("Exiting the program");
                }
            }
        }
    }
}
