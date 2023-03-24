
import java.util.*;
import java.util.Random;

public class HashmapWithRandomProbing {

    // class fields

    private int size;
    private int capacity;

    private Entry[] table;
    private int numItems;
    private Random random;
    private int collision;
    private long timeTakentoInsert;

    // constructor
    public HashmapWithRandomProbing(int capacity) {
        this.size = 0;
        this.table = new Entry[capacity];
        this.numItems = 0;
        this.random = new Random();
        this.timeTakentoInsert=0;
        this.collision = 0;
        this.capacity=capacity;

    }


    // method to add an entry to the hash map
    public void insert(int key, int value) {

        if (numItems >= capacity) {
            System.out.println("Capacity is full");
            return;
        }

        long startTime = System.currentTimeMillis();

        int index = hash(key);
        int numProbes = 0;

        // loop until an empty slot is found or the entire table has been probed
        while (numProbes < capacity) {
            if (table[index] == null) {
                table[index] = new Entry(key, value);
                numItems++;
                long endTime = System.currentTimeMillis();
                timeTakentoInsert += (endTime - startTime);
                size+=1;
                return;
            } else if (table[index].getKey()==(key)) {
                table[index].setValue(value);
                long endTime = System.currentTimeMillis();
                timeTakentoInsert += (endTime - startTime);
                size+=1;
                return;
            } else {
                index = (index + random.nextInt(capacity - 1) + 1) % capacity;
                numProbes++;
                collision+=1;
            }
        }

        System.out.println("Error: Hash table is full");
    }


    // method to get the value associated with a given key
    public int get(int key) {
        int index = 0;
        int numProbes = 0;

        // loop until the key is found or the entire table has been probed
        while (numProbes < capacity) {
             if (table[index]!= null && table[index].getKey()==(key)) {
                return table[index].getValue();
            } else {
                index = index+1;
                numProbes++;
            }
        }

        return -1;
    }



    // method to check if a key exists in the hash map
public boolean searchKey(int key) {
    int index = 0;
    int numProbes = 0;

    // loop until the key is found or the entire table has been probed
    while (numProbes < capacity) {
         if (table[index]!= null && table[index].getKey()==(key)) {
            return true;
        } else {
            index = index+1;
            numProbes++;
        }
    }

    return false;
}


    // method to get the number of entries in the hash map
    public int size() {
        return numItems;
    }


    // private method to cominserte the hash of a given key
    private int hash(int key) {
        int hash = key % capacity;
        return hash >= 0 ? hash : hash + capacity;
    }


    // method to delete an entry from the hash map
    public void delete(int key) {
        int index = 0;
        int numProbes = 0;
        int val=-1;
    
        while (numProbes < capacity) {
             if ( table[index]!= null && table[index].getKey()==(key)) {
                val=table[index].getValue();
                table[index] = null;
                numItems--;
                System.out.println("Key value pair deleted-" + key + " " + val); 

                return;
            } else {
                index+=1;
                numProbes++;
            }
        }
        System.out.println("Key not found"); 

    }
    

    private static void displayMenu() {
        System.out.println("1. Insert");
        System.out.println("2. Delete");
        System.out.println("3. Get");
        System.out.println("4. Current Size");
        System.out.println("5. Search");
        System.out.println("6. No of collisions");
        System.out.println("7. Time taken");
        System.out.println("8. Display");
        System.out.println("9. Exit");

        System.out.print("Enter your choice: ");
    }


    // method to display the entries in the hash map
    public void display() {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null)
                System.out.println(i + " :(" + table[i].key + " " + table[i].value + ")");
            else
                System.out.println(i + ":");
            
        }
    }


    // Returns the no of collisions
    int getCollision()
    {
        return collision;
    }
 
 
    // Returns the time taken to insert
    long getTimetaken(){
 
        return timeTakentoInsert;
 
    }


    private class Entry {
        private int key;
        private int value;

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

 
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the hash table: ");
        int size = scanner.nextInt();
        HashmapWithRandomProbing map = new HashmapWithRandomProbing(size);
        int choice;

        do {
            displayMenu();
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the key: ");
                    int key = scanner.nextInt();
                    System.out.print("Enter the value: ");
                    int value = scanner.nextInt();
                    map.insert(key, value);
                    break;

                case 2:
                System.out.print("Enter the key: ");
                key = scanner.nextInt();
                map.delete(key);
                break;

                case 3:
                    System.out.print("Enter the key: ");
                    key = scanner.nextInt();
                    value = map.get(key);
                    if (value == -1) {
                        System.out.println("Key not found");
                    } else {
                        System.out.println("Value = " + value);
                    }
                    break;

                case 4:
                    System.out.println("Size = " + map.size());
                    break;

                case 5:

                System.out.println("Enter key:");
                        key = scanner.nextInt();
                        boolean found = map.searchKey(key);
                        if (found) {
                            System.out.println("Key found.");
                        } else {
                            System.out.println("Key not found.");
                        }
                        break;
                    

                case 6:
                System.out.println(map.getCollision());
                break;


                case 7:
                System.out.println(map.getTimetaken());
                break;

                case 8:

                    map.display();
                    break;
            
                    

                case 9:

                    System.out.println("Exiting...");
                        break;


                default:
                    System.out.println("Invalid choice");
                    break;
            }

            System.out.println();
        } while (choice != 9);

        scanner.close();
    }
}

