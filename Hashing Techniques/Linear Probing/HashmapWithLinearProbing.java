import java.util.*;


public class HashmapWithLinearProbing {
    
    int capacity; 
    Entry dummyNode; 
    Entry [] table; 
    int collision; 
    long timeTakentoInsert; // the total time taken to insert all elements
    int size; 

    // Define the Entry class that holds the key-value pairs
    static class Entry {
        int key;
        int value;

        Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // Constructor that creates a hash table with the specified capacity
    HashmapWithLinearProbing(int cap) {
        this.capacity = cap;
        table = new Entry[cap];
        dummyNode = new Entry(-1, -1);
    }



    // Insert a new key-value pair into the hash table
    void insert(int key, int value) {
        // If the hash table is full, print an error message and return
        if (size >= capacity) {
            System.out.println("Capacity is full");
            return;
        }

        long s = System.currentTimeMillis();
        Entry newEntry = new Entry(key, value);

        int hash = getHash(key);

        // If the table slot at the hash index is not null and the key is not -1
        while (table[hash] != null && table[hash].key != -1) {
            // Move to the next slot using linear probing
            hash += 1;
            hash = hash % capacity;
        }

        table[hash] = newEntry;
        long e = System.currentTimeMillis();
        timeTakentoInsert += (e - s);
        size += 1;
        collision += 1;

    }



    // Remove the key-value pair with the specified key from the hash table
    int remove(int key) {
        int hash = getHash(key);
        int currentCheckCount=0;


        while (table[hash] != null &&  currentCheckCount<= capacity) {

            if (table[hash].key == key) {

                Entry temp = table[hash];
                table[hash] = dummyNode;
                return temp.value;
            }

            // Move to the next slot using linear probing
            hash += 1;
            currentCheckCount+=1;
            hash = hash % capacity;
        }

        // If the key was not found, return -1
        return -1;
    }



    // Display the contents of the hash table
    void display()
    {

        for(int i=0; i<capacity;i++)
        {   


            if(table[i]!= null)
            System.out.println(i + ":" +   "(" + table[i].key +"," + table[i].value + ")" );
            else{
                System.out.println(i+ ":");
            }


        }


    }


    // Returns the total no of collision
    int getCollision()
    {
        return collision;
    }


    // Returns the hash of the key
    int getHash(int key){

        int hash= key%capacity;
        return hash;

    }

    // Returns the time taken to insert
    long getTimetaken(){

        return timeTakentoInsert;
    
    }




    // Returns the value of the key passed
    int getValue(int key) {

        int currentCheckCount=0;
        int hash = getHash(key);

        while (table[hash] != null  && currentCheckCount<= capacity) {
            if (table[hash].key == key) {
                return table[hash].value;
            }

            hash += 1;
            currentCheckCount+=1;

            hash = hash % capacity;
        }

        return -1;
    }


    // Searches the key
    boolean search(int key) {
        int hash = getHash(key);
        int currentCheckCount=0;

        while (table[hash] != null && currentCheckCount<= capacity) {
            if (table[hash].key == key) {
                return true;
            }

            hash += 1;
            currentCheckCount+=1;
            hash = hash % capacity;
        }

        return false;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice, key, value;
        int k;

        System.out.println("Enter capacity");
        int cap= sc.nextInt();
        HashmapWithLinearProbing hashmap = new HashmapWithLinearProbing(cap);

        do {
            System.out.println("1. Insert");
            System.out.println("2. Remove");
            System.out.println("3. Display");
            System.out.println("4. No of collisions");
            System.out.println("5. Time taken");
            System.out.println("6. Search a key");
            System.out.println("7. Get a key");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter key: ");
                    key = sc.nextInt();
                    System.out.print("Enter value: ");
                    value = sc.nextInt();
                    hashmap.insert(key, value);
                    break;
                case 2:
                    System.out.print("Enter key: ");
                    key = sc.nextInt();
                    int removedValue = hashmap.remove(key);
                    if (removedValue != -1) {
                        System.out.println("Value removed: " + removedValue);
                    } else {
                        System.out.println("Key not found");
                    }
                    break;
                case 3:
                    hashmap.display();
                    break;

                

                case 4:
                System.out.println(hashmap.getCollision());
                break;

                case 5:
                System.out.println(hashmap.getTimetaken());
                break;
                
                case 6:
                System.out.print("Enter key: ");
                key = sc.nextInt();
                if (hashmap.search(key)) {
                    System.out.println("Key found");
                } else {
                    System.out.println("Key not found");
                }
                break;

            case 7:
                System.out.print("Enter key: ");
                key = sc.nextInt();
                int val = hashmap.getValue(key);
                if (val != -1) {
                    System.out.println("Value for key " + key + " is " + val);
                } else {
                    System.out.println("Key not found");
                }
                break;
                case 8:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }

            System.out.println();

        } while (choice != 8);

        sc.close();
        
        
    }



    
}

