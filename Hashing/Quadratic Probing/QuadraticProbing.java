import java.util.InputMismatchException;
import java.util.Scanner;

public class QuadraticProbing {
    static class Pair {
        // key and value of the pair

        int key;
        int value;
        // next pair in the linked list
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
        int collision;

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
        public int get(int key) {
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
                    i = (i + j * j) % size;
                    j++;
                }
            }
            return -1;
        }

        // Function to print the hash map
        public void display() {
            for (int i = 0; i < size; i++) {
                if (arr[i] != null) {
                    System.out.println("Key: " + arr[i].key + " Value: " + arr[i].value);
                }
            }
        }

        // Function to get collisions
        public int getCollision() {
            return collision;
        }
    }

    static class Quadratic {
        public static void main(String[] args) {
            hashMap hashmap = null;
            int choice = 0 ;

            do {
                System.out.println("--------- MENU ---------");
                System.out.println("Press 0 : Create a new Hashmap");
                System.out.println("Press 1 : Insert  Value");
                System.out.println("Press 2 : Delete Value");
                System.out.println("Press 3 : Display the Hashmap");
                System.out.println("Press 4 : Get All Collisions");
                System.out.println("Press 5 : To get Value from key");
                System.out.println("Press 6 : Exit");
                System.out.println("Enter your choice : ");
                try {
                    Scanner sc = new Scanner(System.in);
                    choice = sc.nextInt();
                    switch (choice) {
                        case 0:
                            System.out.print("Please Enter the size of the HashMap: ");
                            hashmap = new hashMap(sc.nextInt());
                            break;
                        case 1:
                            if (hashmap == null) {
                                System.out.println("Please create a new HashMap");
                                break;
                            } else {
                                System.out.print("Please Enter the key: ");
                                int key = sc.nextInt();
                                System.out.print("Please Enter the value: ");
                                int value = sc.nextInt();
                                hashmap.insert(key, value);
                            }
                            break;
                        case 2:
                            if (hashmap == null) {
                                System.out.println("Please create a new HashMap");
                                break;
                            } else {
                                System.out.print("Please Enter the key: ");
                                hashmap.delete(sc.nextInt());
                                System.out.println("Deleted");
                            }
                            break;

                        case 3:
                            if (hashmap == null) {
                                System.out.println("Please create a new HashMap");
                                break;
                            } else {
                                hashmap.display();
                            }
                            break;
                        case 4:
                            if (hashmap != null) {
                                System.out.println("Number of collisions: " + hashmap.getCollision());
                            }
                            break;
                        case 5:
                            if (hashmap != null) {
                                System.out.print("Please Enter the key: ");
                                int key = sc.nextInt();
                                System.out.println("Value: " + hashmap.get(key));
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
                    System.out.println("Enter Integer Only");
                }
            } while (choice != 6);
        }

    }
}
