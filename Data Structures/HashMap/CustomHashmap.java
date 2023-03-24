import java.util.*;

public class CustomHashmap {

    // Member variables for the hash map
    int capacity;
    Entry [] table;
    int size;

    // Inner class to represent a single key-value pair
    static class Entry{
        int key;
        int value;
        Entry next;

        // Constructor for a new Entry object
        Entry(int key, int value, Entry next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    // Constructor for the hash map
    CustomHashmap(int cap){
        this.capacity = cap;
        table = new Entry[cap];
        this.size = 0;
    }

    // Method to insert a new key-value pair into the hash map
    void insert(int key, int value){
        // Check if the hash map is already at full capacity
        if (size >= capacity){

            System.out.println("Capacity is full");
            return;
        }

        Entry newEntry = new Entry(key, value, null);
        int hash = getHash(key);

        
        // If the hash table slot is empty, add the new key-value pair
        if (table[hash] == null){
            table[hash] = newEntry;
            size += 1;
            System.out.println("Key-value pair inserted.");
        }
        // If the hash table slot is not empty, add the new key-value pair to the end of the linked list in that slot
        else{ 


            Entry current = table[hash];

            if (current.key == key){
                current.value = value;
                System.out.println("Key-value pair updated.");
                return;
            }

            while (current.next != null){

                if (current.key == key){
                    current.value = value;
                    System.out.println("Key-value pair updated.");
                    return;
                }
                current = current.next;
            }
            current.next = newEntry;
            System.out.println("Key-value pair inserted.");
            size += 1;
        }
    }

    // Method to remove a key-value pair from the hash map
    boolean remove(int key){
        // Calculate the hash code for the key
        int hash = getHash(key);
        // If the hash table slot is empty, the key is not in the hash map
        if (table[hash] == null){
            return false;
        }
        // If the hash table slot is not empty, search for the key in the linked list
        else{
            Entry current = table[hash];
            Entry previous = null;
            while (current != null){
                // If the key is found, remove the corresponding key-value pair from the linked list
                if (current.key == key){
                    if (previous == null){
                        table[hash] = table[hash].next;
                        size -= 1;
                        return true;
                    }
                    else{
                        previous.next = current.next;
                        size -= 1;
                        return true;
                    }
                }
                previous = current;
                current = current.next;
            }
            return false;
        }
    }

    // Method to get the value associated with a given key in the hash map
    int getValue(int key){
        // Calculate the hash code for the key
        int hash = getHash(key);
        Entry current = table[hash];
        // If the hash table slot is empty, the key is not in the hash map
        if (current == null){
            return -1;
        }
        // If the hash table slot is not empty, search for the key in the linked list
        else{
            while (current != null){
                if (current.key == key){
                    return current.value;
                }
                current = current.next;
            }
        }
        return -1;
    }

    // Method to check if a given key is in the hash map
    boolean search(int key){
        

    
        int hash= getHash(key);

        if (table[hash]==null)
        {  
            return false;
        }

        else{


            Entry current= table[hash];

            while(current!= null){

                if(current.key==key){
                    return true;
                }

                current= current.next;

            }

        }

        return false;




    }

    // Method to display the adjacency matrix

    void display()
    {

        for(int i=0; i<capacity;i++)
        {



            if (table[i]!=null)
            {

                System.out.print(i + ":");

                Entry ptr= table[i];

                while(ptr!= null)
                {
                    System.out.print("("+ ptr.key + ","+ ptr.value + ")" + " ");

                    ptr= ptr.next;

                }

                System.out.println();

            }
            else{

                System.out.println(i);
            }



        }


    }


    int getHash(int key){

        int hash= key%capacity;
        return hash;

    }


    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter Size");

            int cap = input.nextInt();

            CustomHashmap map = new CustomHashmap(cap);
            
            while (true) {

                System.out.println("Choose an option:");
                System.out.println("1. Insert key-value pair");
                System.out.println("2. Remove key-value pair");
                System.out.println("3. Search for key");
                System.out.println("4. Get value for key");
                System.out.println("5. Display map");
                System.out.println("6. Quit");

                int choice = input.nextInt();
                int key, value;
                boolean result;

                switch (choice) {
                    case 1:
                        System.out.print("Enter key: ");
                        key = input.nextInt();
                        System.out.print("Enter value: ");
                        value = input.nextInt();
                        map.insert(key, value);
                        break;
                    case 2:
                        System.out.print("Enter key: ");
                        key = input.nextInt();
                        result = map.remove(key);
                        if (result) {
                            System.out.println("Key-value pair removed.");
                        } else {
                            System.out.println("Key not found.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter key: ");
                        key = input.nextInt();
                        result = map.search(key);
                        if (result) {
                            System.out.println("Key found.");
                        } else {
                            System.out.println("Key not found.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter key: ");
                        key = input.nextInt();
                        int val = map.getValue(key);
                        if (val == -1) {
                            System.out.println("Key not found.");
                        } else {
                            System.out.println("Value for key " + key + ": " + val);
                        }
                        break;
                    case 5:
                        map.display();
                        break;
                    case 6:
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                        break;
                }
            }
        }
    }
}




