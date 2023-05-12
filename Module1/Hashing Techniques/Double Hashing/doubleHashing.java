import java.util.InputMismatchException;
import java.util.Scanner;

public class doubleHashing {
    public static class HashMapArray {
         //Size of the hashmap.
        int size;
        // capacity of the hashmap
        int capacity; 
        int collision;
        // hashmap array
        Node[] table;

        // Node class
        static class Node {
            int key;
            int value;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        // Constructor
        public HashMapArray(int capacity) {
            int size = 0;
            this.capacity = capacity;
            table = new Node[capacity];
            for (int i = 0; i < capacity; i++) {
                table[i] = null;
            }
            collision = 0;
        }

        int justSmallerPrimeNum() {
            for (int num = capacity - 1; num >= 1; num--) {
                if (isPrime(num))
                    return num;
            }

            return 1;
        }

        public boolean isPrime(int n) { // ts - sqrt(n)
            for (int div = 2; div * div <= n; div++) { // div <= root(n)
                if (n % div == 0) {
                    return false;
                }
            }
            return true;
        }

        // Function to create hash function 1
        int hashFunction(int key) {
            return key % capacity;
        }

        // Function to create hash function 2
        int hashFunction1(int key) {
            if (key < 0)
                key += capacity;
            key = key % capacity;
            return justSmallerPrimeNum() - (key % justSmallerPrimeNum());
        }

        // Function to get value from hash map
        public void getValueOfKey(int key) {
            int index = hashFunction(key);
            int index1 = hashFunction1(key);
            while (table[index] != null && (!(table[index].key == key))) {
                index += index1;
                index %= capacity;
            }
            if(table[index]!=null){
                System.out.println("{"+table[index].key+":"+table[index].value+"}");
            }
            else{
                System.out.println("Key not found.");
            }
            return ;
        }

        // Function to insert a key value pair in the hash map
        public void insert(int key, int val) {
            if (size == capacity) {
                System.out.println("Hash map is full");
                return;
            }
            int index = hashFunction(key);
            int index1 = hashFunction1(key);

            while (table[index] != null) {
                if (table[index].key == key) {
                    table[index].value = val;
                    return;
                }

                collision++;
                index = (index + index1) % capacity;

            }
            Node newNode = new Node(key, val);
            table[index] = newNode;
            size++;
        }

        // Function to delete a key value pair in the hash map
        public void delete(int key) {
            int index = hashFunction(key);
            int index1 = hashFunction1(key);
            while (table[index] != null && (!(table[index].key == key))) {
                index += index1;
                index %= capacity;
            }
            table[index] = null;
            size--;
        }

        // Function to display the hash map
        public void display() {
            int count = 0;
            for (int i = 0; i < capacity; i++) {
                System.out.print(count+ " --> ");
                if (table[i] != null) {
                    System.out.print("{" + table[i].key+" : " + table[i].value+"}");
                }
                System.out.println();
                count +=1;
            }
        }

        // Function to get the number of collision
        public int numberOfCollision() {
            return collision;
        }

    }

    public static void main(String[] args) {
        HashMapArray obj = null;
        int option = 0;
        Scanner input = new Scanner(System.in);
        System.out.print("Please Enter the size of the HashMap: ");
        obj = new HashMapArray(input.nextInt());
        System.out.println("Select an option from the menu given below.");
        System.out.println("1 : Insert  ");
        System.out.println("2 : Delete ");
        System.out.println("3 : Display the Hashmap");
        System.out.println("4 : Number of Collisions");
        System.out.println("5 : Get Value of Key");
        System.out.println("6 : Exit");
        long t1,t2;
        int key,value;
        loop:while(true){
            try{
                option = input.nextInt();
                switch(option){
                    case 1:
                        t1 = System.currentTimeMillis();
                        System.out.print("Enter key: ");
                        key = input.nextInt();
                        System.out.print("Enter value: ");
                        value = input.nextInt();
                        obj.insert(key, value);
                        t2 = System.currentTimeMillis();
                        System.out.println("Time taken in insertion is: "+ (t2-t1));
                        break;

                    case 2:
                        t1 = System.currentTimeMillis();
                        System.out.print("Enter key: ");
                        key = input.nextInt();
                        obj.delete(key);
                        t2 = System.currentTimeMillis();
                        System.out.println("Time taken in deletion: "+ (t2-t1));
                        break;

                    case 3:
                        // t1 = System.currentTimeMillis();
                        obj.display();
                        break;
                        // t2 = System.currentTimeMillis();
                        // System.out.println("Time taken in insertion is: "+ (t2-t1));
                
                    case 4:
                        System.out.println("Number of collision occurs: "+obj.collision);
                        break;
                    case 5:

                        t1 = System.currentTimeMillis();
                        System.out.print("Enter key: ");
                        key = input.nextInt();
                        obj.getValueOfKey(key);
                        t2 = System.currentTimeMillis();
                        System.out.println("Time taken in searching: "+ (t2-t1));
                        break;

                    case 6:
                        break loop;
                    default:
                        System.out.println("please choose a valid option.");
                }
            }catch(InputMismatchException ex){
                System.out.println("Invalid input type. Please enter integer(number)");
                input = new Scanner(System.in);
            }
        }
    }
}