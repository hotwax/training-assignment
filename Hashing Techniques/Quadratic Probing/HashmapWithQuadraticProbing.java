import java.util.*;

public class HashmapWithQuadraticProbing {

    int capacity;
    Entry dummyNode;        
    Entry[] table;          
    int collision; 
    long timeTakentoInsert; // the total time taken to insert all elements
    int size; 

    // Entry class to store key-value pairs
    static class Entry {
        int key;
        int value;

        Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // Constructor to create a hash table with given capacity
    HashmapWithQuadraticProbing(int cap) {

        this.capacity = cap;
        table = new Entry[cap];
        dummyNode = new Entry(-1, -1);   // Initialize dummy node with key and value as -1

    }

    // Method to insert a key-value pair into the hash table
    void insert(int key, int value) {

        if (size >= capacity) {
            System.out.println("Capacity is full");
            return;
        }

        long s = System.currentTimeMillis();
        Entry newEntry = new Entry(key, value);
        int hash = getHash(key);

        // If the hash table slot is empty or marked deleted, add the new entry
        if (table[hash] == null || table[hash].key == -1) {
            table[hash] = newEntry;
            long e = System.currentTimeMillis();
            timeTakentoInsert += (e - s);
            size += 1;
            
        } else {

            // If the slot is already occupied, use quadratic probing to find an empty slot
            for (int i = 0; i < capacity; i++) {
                int t = (hash + i * i) % capacity;
                if (table[t] == null || table[t].key == -1) {
                    table[t] = newEntry;

                    long e = System.currentTimeMillis();
                    timeTakentoInsert += (e - s);
                    size += 1;
                    collision+=1;
                    return;
                }

            }


            //no empty slot is found after probing
            System.out.println("No slot found");
        }
    }


    // Method to remove a key-value pair from the hash table given the key
    int remove(int key) {
        int hash = getHash(key);
        while (table[hash] != null) {

            if (table[hash].key == key) {
                Entry temp = table[hash];
                table[hash] = dummyNode;    // Mark the slot as deleted using the dummy node
                return temp.value;
            }
            
            hash += 1;
            hash = hash % capacity;
        }
        // If the key is not found, return -1
        return -1;
    }



// Method to get the value associated with a key in the hash table
int getValue(int key) {
    int hash = getHash(key);
    int i = 0;
    while (table[hash] != null && i < capacity) {
        if (table[hash].key == key) {
            return table[hash].value;
        }
        i++;
        hash = (hash + i * i) % capacity;
    }
    // If the key is not found, return -1
    return -1;
}




// Method to search for a key in the hash table
boolean search(int key) {
    int hash = getHash(key);
    int i = 0;
    while (table[hash] != null && i < capacity) {
        if (table[hash].key == key) {
            return true;
        }
        i++;
        hash = (hash + i * i) % capacity;
    }
    return false;
}


    // Method to display the contents of the hash table
    void display() {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                System.out.println(i + ": (" + table[i].key + "," + table[i].value + ")");
            } else {
                System.out.println(i + ":");
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




    public static void main(String[] args) {



        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter capacity");
            int cap= scanner.nextInt();

            HashmapWithQuadraticProbing hashmap = new HashmapWithQuadraticProbing(cap);


            int choice;
            int key, value;

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
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter key: ");
                        key = scanner.nextInt();
                        System.out.print("Enter value: ");
                        value = scanner.nextInt();
                        hashmap.insert(key, value);
                        break;

                    case 2:
                        System.out.print("Enter key: ");
                        key = scanner.nextInt();
                        int removedValue = hashmap.remove(key);
                        if (removedValue == -1) {
                            System.out.println("Key not found");
                        } else {
                            System.out.println("Value removed: " + removedValue);
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
                    key = scanner.nextInt();
                    if (hashmap.search(key)) {
                        System.out.println("Key found");
                    } else {
                        System.out.println("Key not found");
                    }
                    break;
        
                    case 7:
                        System.out.print("Enter key: ");
                        key = scanner.nextInt();
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
            } while (choice != 8);
        }
        
    }
    
}

