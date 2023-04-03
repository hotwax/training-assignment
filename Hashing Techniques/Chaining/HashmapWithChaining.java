import java.util.InputMismatchException;
import java.util.Scanner;

public class HashmapWithChaining {


int capacity;
Entry[] table; // array of linked lists
int collision;
int size;
long timeTakentoInsert;
double loadfactor;
int threshold;

static class Entry {
    int key;
    int value;
    Entry next;

    Entry(int key, int value, Entry next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}

HashmapWithChaining(int cap) {
    this.capacity = cap;
    table = new Entry[cap];
    this.collision = 0;
    this.size = 0;
    this.timeTakentoInsert= 0;
    this.loadfactor= 0.75;
    this.threshold= (int)(cap*this.loadfactor);

}

// Method to insert a new key-value pair into the hash map
void insert(int key, int value) {

    

    long s,e;

    s= System.currentTimeMillis(); 



    Entry newEntry = new Entry(key, value, null);
    int hash = getHash(key);

    // If the hash table slot is empty, add the new key-value pair
    if (table[hash] == null) {
        table[hash] = newEntry;
        size += 1;
        System.out.println("Key-value pair inserted.");
    }
    // If the hash table slot is not empty, add the new key-value pair to the end of the linked list in that slot
    else {
        Entry current = table[hash];

        if (current.key == key){
            current.value = value;
            System.out.println("Key-value pair updated.");
            return;
        }


        while (current.next != null) {


            if (current.key == key){
                current.value = value;
                System.out.println("Key-value pair updated.");

                e= System.currentTimeMillis(); 
                timeTakentoInsert += (e- s);

                return;
            }

            current = current.next;
        }
        current.next = newEntry;
        System.out.println("Key-value pair inserted.");
        size += 1;
        collision += 1;
    }


    if (size >= threshold){
        System.out.println("Load factor reached. Rehashing...");
        rehash();
    }

    e= System.currentTimeMillis(); 
    timeTakentoInsert += (e- s);

}


void rehash(){
    int newCap = capacity*2;
    Entry[] newTable = new Entry[newCap];

    int tempc= capacity;
    capacity= newCap;
    
    for (int i=0; i<tempc; i++){
        Entry current = table[i];
        while (current != null){
            int hash = getHash(current.key);
            Entry newEntry = new Entry(current.key, current.value, null);
            if (newTable[hash] == null){
                newTable[hash] = newEntry;
            }
            else{
                Entry temp = newTable[hash];
                while (temp.next != null){
                    temp = temp.next;
                }
                temp.next = newEntry;
            }
            current = current.next;
        }
    }
    table = newTable;
    capacity = newCap;
    threshold = (int)(capacity*loadfactor);

}

// Method to remove a key-value pair from the hash map
int remove(int key) {
    int hash = getHash(key);
    if (table[hash] == null) {
        return -1; // Key not found
    } else {
        Entry current = table[hash];
        Entry previous = null;
        while (current != null) {
            if (current.key == key) {
                if (previous == null) {
                    // if the first element in the linked list matches the key
                    int rem = current.value;
                    table[hash] = table[hash].next;
                    size -= 1;
                    return rem;
                } else {
                    
                    int rem = current.value;
                    previous.next = current.next;
                    size -= 1;
                    return rem;
                }
            }
            previous = current;
            current = current.next;
        }
        return -1; // Key not found
    }
}

// Method to get the value associated with a key
int getValue(int key) {
    int hash = getHash(key);
    Entry current = table[hash];
    if (current == null) {
        return -1; // Key not found
    } else {
        while (current != null) {
            if (current.key == key) {
                return current.value;
            }
            current = current.next;
        }
    }
    return -1; // Key not found
}


long getTimetaken(){

    return timeTakentoInsert;

}

// Method to search for a key in the hash map
    boolean search(int key)
    {
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

    

// Method to display the hashmap
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


    // Method to get hash value of the key
    int getHash(int key){

        int hash= key%capacity;
        return hash;

    }

    // Method to return overall no of collision
    int getCollision()
    {

        return collision;
    }


    public static void main(String[] args) {

        try{
        Scanner sc = new Scanner(System.in);
        int choice, key, value;
        boolean result;

        System.out.println("Enter capacity");
        int cap= sc.nextInt();
        HashmapWithChaining hashmap = new HashmapWithChaining(cap);



        do {
            
            
            System.out.println("1. Insert");
            System.out.println("2. Remove");
            System.out.println("3. Search");
            System.out.println("4. Get Value");
            System.out.println("5. Collision");
            System.out.println("6. Time taken");
            System.out.println("7. Display");
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
                System.out.print("Enter key: ");
                key = sc.nextInt();
                result = hashmap.search(key);
                if (result) {
                    System.out.println("Key found.");
                } else {
                    System.out.println("Key not found.");
                }
                break;
            case 4:
                System.out.print("Enter key: ");
                key = sc.nextInt();
                int val = hashmap.getValue(key);
                if (val == -1) {
                    System.out.println("Key not found.");
                } else {
                    System.out.println("Value for key " + key + ": " + val);
                }
                break;
            case 5:

                System.out.println(hashmap.getCollision());
                break;
                
            case 6:

                System.out.println(hashmap.getTimetaken());
                break;

            case 7:
                hashmap.display();
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

        catch(InputMismatchException e){
            System.out.println("Invalid input, Please Enter a valid input. " );
            System.out.println("Exception: " + e + "");
        }

        catch(Exception e){
            System.out.println("Exception: " + e + "");
        }

    
}}
