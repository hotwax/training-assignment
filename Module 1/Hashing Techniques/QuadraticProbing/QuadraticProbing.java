import java.util.InputMismatchException;
import java.util.Scanner;

public class QuadraticProbing {
    static class Pair {
        // key and value of the pair
        int key;
        int value;

        Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    static class hashMap {
        // size of the hash map
        int size;
        // array of pairs
        Pair[] arr;
        // number of collisions
        int collision;

        // constructor
        hashMap(int size) {
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
                int i = index;
                int j = 1;
                while (arr[i] != null) {
                    i = (i + j * j) % size;
                    j++;
                }
                if ((i != hashFunction(key)) && (arr[i] == null || arr[i].key != key)) {
                    collision++;
                    arr[i] = newNode;
                }
            }
        }

        // Function to search a key in the hash map
        public void delete(int key) {
            int index = hashFunction(key);
            if (arr[index].key == key) {
                arr[index] = null;
            } else {
                int i = index;
                int j = 1;
                while (arr[i] != null) {
                    if (arr[i].key == key) {
                        arr[i] = null;
                        break;
                    }
                    i = (i + j * j) % size;
                    j++;
                }
            }
        }

        // Function to get the value of a key in the hash map
        public int getValueOfKey(int key) {
            int index = hashFunction(key);
            if (arr[index].key == key) {
                return arr[index].value;
            } else {
                int i = index;
                int j = 1;
                while (arr[i] != null) {
                    if (arr[i].key == key) {
                        return arr[i].value;
                    }
                    i = (i + j * j++) % size;
                }
            }
            return -1;
        }

        // Function to print the hash map
        public void display() {
            for (int i = 0; i < size; i++) {
                if (arr[i] != null) {
                    System.out.println(arr[i].key + "->" + arr[i].value);
                }
            }
        }

        public int noOfCollisions() {
            return collision;
        }
    }

    // Driver code
    public static void main(String[] args) {
        hashMap hashmap = null;
        int ch = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("Please Enter the size of the HashMap: ");
        hashmap = new hashMap(sc.nextInt());
        long t1 = System.currentTimeMillis();
        do {
            System.out.println("\nSelect an option from the menu given below.");
            System.out.println("1 : Insert  ");
            System.out.println("2 : Delete ");
            System.out.println("3 : Display the Hashmap");
            System.out.println("4 : Number of Collisions");
            System.out.println("5 : Get Value of Key");
            System.out.println("6 : Calculate Time ");
            System.out.println("7 : Exit");

            try {
                System.out.println("Enter your choice : ");
                ch = sc.nextInt();
                switch (ch) {
                    case 1:
                        if (hashmap != null) {
                            System.out.print("Please Enter the key: ");
                            int key = sc.nextInt();
                            System.out.print("Please Enter the value: ");
                            int value = sc.nextInt();
                            hashmap.insert(key, value);
                        }
                        break;
                    case 2:
                        if (hashmap != null) {
                            System.out.print("Please Enter the key: ");
                            hashmap.delete(sc.nextInt());
                            System.out.println("The key is deleted.");
                        }
                        break;
                    case 3:
                        if (hashmap != null)
                            hashmap.display();
                        break;
                    case 4:
                        if (hashmap != null) {
                            System.out.println("Number of collisions: " + hashmap.noOfCollisions());
                        }
                        break;
                    case 5:
                        if (hashmap != null) {
                            System.out.print("Please Enter the key: ");
                            int key1 = sc.nextInt();
                            System.out.println("Value: " + hashmap.getValueOfKey(key1));
                        }
                        break;
                    case 6:
                        long t2 = System.currentTimeMillis();
                        System.out.println("Time in milli seconds is: " + (t2 - t1));
                        break;
                    case 7:
                        System.out.println("Exiting the program.");
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        } while (ch != 7);
    }
}