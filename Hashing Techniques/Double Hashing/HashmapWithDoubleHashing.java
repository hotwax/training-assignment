import java.util.Scanner;

public class HashmapWithDoubleHashing {

    int capacity;
    Entry dummyNode;
    Entry[] table;
    int prime;
    int collision;
    long timeTakentoInsert;
    int size;

    // The Entry class represents a key-value pair in the hash map
    static class Entry{
        int key;
        int value;
        Entry(int key,int value){
            this.key= key;
            this.value= value;
        }
    }

    // Constructor for the hash map
    HashmapWithDoubleHashing(int cap) {
        this.capacity= cap;
        table= new Entry[cap];
        dummyNode= new Entry(-1, -1);
        this.prime= getPrime(cap);
        this.timeTakentoInsert=0;
        this.collision = 0;
        this.size = 0;
    }

    // A helper function to check if a number is prime
    boolean isPrime(int n) {
        if(n==1 || n==0)
            return false;
        for(int i=2;i<n;i++){
            if(n%i==0)
                return false;
        }
        return true;
    }

    // A helper function to get the largest prime number less than a given number
    int getPrime(int cap) {
        cap-=1;
        while(cap>0){
            if (isPrime(cap)){
                return cap;
            }
            cap-=1;
        }
        return -1;
    }

    // The first hash function
    int hash1(int key) {
        return key%capacity;
    }

    // The second hash function
    int hash2(int key) {
        return (prime-(key% prime));
    }

    // Insert a key-value pair into the hash map
    void insert(int key,int value) {

        if (size >= capacity) {
            System.out.println("Capacity is full");
            return;
        }

        // Record the start time for inserting the entry
        long s = System.currentTimeMillis();

        Entry newEntry= new Entry(key,value);

        // Compute the hash values
        int h1= hash1(key);
        int h2= hash2(key);

        // Use double hashing to find an empty slot in the hash table
        while(table[h1]!= null){

            // If the key already exists, update the value
            if(table[h1].key==key){
                table[h1].value= value;
                return;
            }
            
            h1+=h2;
            h1%= capacity;
            collision+=1;

        }

        table[h1]= newEntry;

        // Record the end time for inserting the entry
        long e = System.currentTimeMillis();

        // Update the collision count, size, and time taken to insert
        size += 1;
        timeTakentoInsert += (e - s);
    }

    // Remove a key-value pair from the hash map
    int remove(int key) {
        int hash= hash1(key);
        int i=0;
        while(i<capacity) {
            if(table[hash]!= null && table[hash].key==key){
                Entry temp= table[hash];
                table[hash]= dummyNode;
                size-=1;
                return temp.value;
            }
            hash+=1;
            i+=1;
            hash= hash%capacity;
        }
        return -1;
    }


    // Method to retrieve the value of key passed
    int getValue(int key) {
        int hash = hash1(key);
        int h2 = hash2(key);
        int count = 0;
        while (table[hash] != null) {
            if (table[hash].key == key) {
                return table[hash].value;
            }
            hash += h2;
            hash %= capacity;
            count++;
            if (count == capacity) {
                break;
            }
        }
        return -1;
    }
    
    // Method to search if a key is present or not
    boolean search(int key) {
        int h1 = hash1(key);
        int h2 = hash2(key);
        int count = 0;
        while (table[h1] != null) {
            if (table[h1].key == key) {
                return true;
            }
            h1 += h2;
            h1 %= capacity;
            count++;
            if (count == capacity) {
                break;
            }
        }
        return false;
    }

    // Method to display the hash table
    void display() {
        for(int i=0; i<capacity;i++) {
            if(table[i]!= null) {
                System.out.println(i + ":" +   "(" + table[i].key +"," + table[i].value + ")" );
            } else {
                System.out.println(i+ ":");
            }
        }
    }

    // Returns the total no of collision
   int getCollision()
   {
       return collision;
   }


   

   // Returns the time taken to insert
   long getTimetaken(){

       return timeTakentoInsert;

   }



    public static void main(String[] args) {
        try (// Sample usage
        Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter capacity of HashMap:");
            int capacity = scanner.nextInt();
            HashmapWithDoubleHashing map = new HashmapWithDoubleHashing(capacity);

            while (true) {
                System.out.println("\nChoose an option:");
                System.out.println("1. Insert a key-value pair");
                System.out.println("2. Remove a key-value pair");
                System.out.println("3. Get the value associated with a key");
                System.out.println("4. Search for a key");
                System.out.println("5. No of collisions");
                System.out.println("6. Time taken");
                System.out.println("7. Display the contents of the HashMap");
                System.out.println("8. Exit");

                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("Enter key:");
                        int key = scanner.nextInt();
                        System.out.println("Enter value:");
                        int value = scanner.nextInt();
                        map.insert(key, value);
                        System.out.println("Key-value pair inserted successfully.");
                        break;
                    case 2:
                        System.out.println("Enter key:");
                        key = scanner.nextInt();
                        value = map.remove(key);
                        if (value == -1) {
                            System.out.println("Key not found.");
                        } else {
                            System.out.println("Value removed: " + value);
                        }
                        break;
                    case 3:
                        System.out.println("Enter key:");
                        key = scanner.nextInt();
                        value = map.getValue(key);
                        if (value == -1) {
                            System.out.println("Key not found.");
                        } else {
                            System.out.println("Value: " + value);
                        }
                        break;
                    case 4:
                        System.out.println("Enter key:");
                        key = scanner.nextInt();
                        boolean found = map.search(key);
                        if (found) {
                            System.out.println("Key found.");
                        } else {
                            System.out.println("Key not found.");
                        }
                        break;

                    case 5:
                    System.out.println(map.getCollision());
                    break;

    
                    case 6:
                    System.out.println(map.getTimetaken());
                    break;

                    case 7:
                        map.display();
                        break;

                    case 8:
                        System.exit(0);
                    default:
                        System.out.println("Invalid option.");
                }
            }
        }
    }
}


