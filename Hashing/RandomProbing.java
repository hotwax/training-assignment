import java.util.*;

class Node {
    int key;
    int value;

    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class RandomProbing {
    Node[] table;
    int capacity = 0;
    int collision;
    boolean remove[];

    RandomProbing(int capacity) {
        this.capacity = capacity;
        table = new Node[capacity];
        remove = new boolean[capacity];
    }

    void display() {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                System.out.println("Key: " + table[i].key + " Value: " + table[i].value);
            }
        }
    }

    int hash(int key) {
        return key % capacity;
    }

    void insert(int key, int value) {
        if (key % capacity > capacity) {
            System.out.println("Key is greater than capacity");
        }
        int index = hash(key);
        int random = 1;
        while ((table[index] != null && table[index].key != key)) {
            random = (int) (Math.random() * (capacity)) + 1;
            index = (index + random) % random;
        }
        if (index != hash(key) || (table[index] != null && table[index].key == key)) {
            collision++;
        }
        table[index] = new Node(key, value);
        remove[index] = true;
    }

    void delete(int key) {
        int index = hash(key);
        int offset = 0;
        int start = index;
        while (table[index]!=null|| table[index].key != key){
            offset = (int) (Math.random() * (capacity)) + 1;
            index = (index + offset) % capacity;
            if (start == index) {
                System.out.println("Key not found");
                return;
            }
        }
        if(table[index] == null){
            System.out.println("Key not found");
            return;
        }
        remove[index] = false;
        for(int i=0;i<capacity;i++){
            System.out.print(remove[i]+" ");
        }
        System.out.println();
    }

    int get(int key) {
        int index = hash(key);
        int offset = 1;
        int start = index;
        while (true) {
            if (table[index] != null && table[index].key == key) {
                break;
            }
            index = (index + offset) % capacity;
            if (start == index) {
                System.out.println("Key not found");
                return -1;
            }
        }
        return table[index].value;
    }
    public void traverse(){
        for(int i=0;i<capacity;i++){
            if(table[i]==null || !remove[i])continue;
            System.out.println("Key: "+table[i].key+" Value: "+table[i].value);
        }
        System.out.println();
    }

    int getCollision() {
        return collision;
    }
}

class hashMap {
    public static void main(String[] args) {
        RandomProbing map = null;
        int choice;
        Scanner sc = new Scanner(System.in);
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
                    System.out.println("Program Terminated");
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        } while (choice != 6);
    }
}