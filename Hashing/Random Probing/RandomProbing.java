package Hashing.RandomProbing;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class RandomProbing {
    int[] keys;
    int[] values;
    int capacity;
    int collision;
    int randomNum;
    int size;
    public RandomProbing(int capacity) {
        this.capacity = capacity;
        this.keys = new int[capacity];
        this.values = new int[capacity];
        this.collision = 0;
        this.size = 0;
        for(int i = 0; i < capacity; i++) {
            keys[i] = Integer.MAX_VALUE;
            values[i] = Integer.MAX_VALUE;
        }
        Random random = new Random();
        randomNum = random.nextInt(capacity);
    }
    boolean isEmpty() {
        return size != capacity;
    }
    int getCapacity() {
        return capacity;
    }
    public int getCollision() {
        return collision;
    }

    void display() {
        for(int index = 0; index <capacity; index++){
            if(keys[index]!=Integer.MAX_VALUE){
                System.out.println("Key: "+keys[index]+" Value: "+values[index]);
            }
        }
    }

    int hash(int key) {
        return key % capacity;
    }

    public void insert(int key, int value) {
        if(size == capacity){
            System.out.println("Hashmap is full");
            return;
        }
        int index = hash(key);
        int start = index;
        while(true){
            if(Integer.MAX_VALUE == keys[index]){
                keys[index] = key;
                values[index] = value;
                size++;
                break;
            }
            index = (index + randomNum * collision) % capacity;
            collision++;
            if(start == index){
//                System.out.println("Hashmap is full");
                return;
            }
        }
    }

    void delete(int key) {
        int index = hash(key);
        int start = index;
        while(true){
            if(keys[index] == key){
                keys[index] = Integer.MAX_VALUE;
                values[index] = Integer.MAX_VALUE;
                size--;
                break;
            }
            index = (index + randomNum * collision) % capacity;
            if(start == index){
                System.out.println("Key not found");
                return;
            }
        }
    }

    int get(int key) {
        int index = hash(key);
        int start =  index;
        while(true){
            if(keys[index] == key){
                return values[index];
            }
            index = (index + randomNum * collision) % capacity;
            if(start == index){
                System.out.println("Key not found");
                return Integer.MAX_VALUE;
            }
        }
    }

    public void search(int key) {
        int index = hash(key);
        int start = index;
        while(true){
            if(keys[index] == key){
                System.out.println("Key: "+keys[index]+" Value: "+values[index]+" is present in the hash map");
                return;
            }
            index = (index + randomNum * collision) % capacity;
            if(start == index){
                System.out.println("Key not found");
                return;
            }
        }
    }
}

class hashMap {
    public static void main(String[] args) {
        RandomProbing map = null;
        int choice = 0;
        do {
            System.out.println("--------- MENU ---------");
            System.out.println("Press 0 : Create a new Hashmap");
            System.out.println("Press 1 : Insert  Value");
            System.out.println("Press 2 : Delete Value");
            System.out.println("Press 3 : Display the Hashmap");
            System.out.println("Press 4 : Get All Collisions");
            System.out.println("Press 5 : To get Value from key");
            System.out.println("Press 6 : To search a key");
            System.out.println("Press 7 : Exit");
            System.out.println("Enter your choice : ");
            try {
                Scanner sc = new Scanner(System.in);
                choice = sc.nextInt();
                switch (choice) {
                    case 0:
                        System.out.print("Please Enter the size of the HashMap: ");
                        map = new RandomProbing(sc.nextInt());
                        break;
                    case 1:
                        if (map == null) {
                            System.out.println("Please create a new HashMap");
                            break;
                        } else {
                            System.out.print("Please Enter the key: ");
                            int key = sc.nextInt();
                            System.out.print("Please Enter the value: ");
                            int value = sc.nextInt();
                            map.insert(key, value);
                        }
                        break;
                    case 2:
                        if (map == null) {
                            System.out.println("Please create a new HashMap");
                            break;
                        } else {
                            System.out.print("Please Enter the key: ");
                            map.delete(sc.nextInt());
                            System.out.println("Deleted");
                        }
                        break;

                    case 3:
                        if (map == null) {
                            System.out.println("Please create a new HashMap");
                            break;
                        } else {
                            map.display();
                        }
                        break;
                    case 4:
                        if (map != null) {
                            System.out.println("Number of collisions: " + map.getCollision());
                        }
                        break;
                    case 5:
                        if (map != null) {
                            System.out.print("Please Enter the key: ");
                            int key = sc.nextInt();
                            System.out.println("Value: " + map.get(key));
                        }
                        break;
                    case 6:
                        if (map != null) {
                            System.out.println("Please Enter the key: ");
                            map.search(sc.nextInt());
                        }
                        break;
                    case 7:
                        System.out.println("Program Terminated");
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
            }catch (InputMismatchException e) {
                System.out.println("Enter Integer Only!!");
            }
        } while (choice != 7);
    }
}