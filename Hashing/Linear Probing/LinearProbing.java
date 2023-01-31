import java.util.Scanner;

public class LinearProbing {
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
            } else if (arr[index].key == key) {
                arr[index].value = value;
            } else {
                int i = index; // i is the index of the next empty slot

                while (arr[i] != null) {
                    i = (i + 1) % size;
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
                while (arr[i] != null) {
                    if (arr[i].key == key) {
                        arr[i] = null;
                        break;
                    }
                    i = (i + 1) % size;
                }
            }
        }

        // Function to get a value from the hash map
        public int get(int key) {
            int index = hashFunction(key);
            if (arr[index].key == key) {
                return arr[index].value;
            } else {
                int i = index;
                while (arr[i] != null) {
                    if (arr[i].key == key) {
                        return arr[i].value;
                    }
                    i = (i + 1) % size;
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

        public int getCollision() {
            return collision;
        }
    }

    // Driver code
    public static void main(String[] args) {
        hashMap hashmap = null;
        int choice;
        Scanner sc = new Scanner(System.in);
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
            choice = sc.nextInt();
            switch (choice) {
                case 0:
                    System.out.print("Please Enter the size of the HashMap: ");
                    hashmap = new hashMap(sc.nextInt());
                    break;
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
                        System.out.println("Deleted");
                    }
                    break;
                case 3:
                    if (hashmap != null)
                        hashmap.display();
                    break;
                case 4:
                    if (hashmap != null) {
                        System.out.println("Number of collisions: " + hashmap.getCollision());
                    }
                    break;
                case 5:
                    if (hashmap != null) {
                        System.out.print("Please Enter the key: ");
                        int key1 = sc.nextInt();
                        System.out.println("Value: " + hashmap.get(key1));
                    }
                    break;
                case 6:
                    System.out.println("Program Terminated");
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        } while (choice != 6);
    }
}
