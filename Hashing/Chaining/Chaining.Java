import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Chaining{
    LinkedList<Pair> bucket[];
    int size, numElements;
    int collisions;

    class Pair {
        int key;
        int value;

        public Pair(int k, int v) {
            key = k;
            value = v;
        }
    }

    public Chaining(Integer size) {
        this.size = size;
        numElements = 0;
        collisions = 0;
        //creating bucket of size, size
        bucket = new LinkedList[size];
    }

    public int getCollisions() {
        int numCollisions = 0;
        // total collision
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] != null && bucket[i].size() > 1) {
                numCollisions++;
            }
        }
        return numCollisions;
    }

    public void put(Integer key, Integer value) {
        int hashValue = hash(key);
        if (bucket[hashValue] == null) {
            bucket[hashValue] = new LinkedList<>();
        }

        for (Pair p : bucket[hashValue]) {
            if (p.key == key) {
                p.value = value;
                return;
            }
        }
        //adding key 
        bucket[hashValue].add(new Pair(key, value));
        numElements++;
    }
    //traversing
    public void traverse() {
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == null)
                continue;
            for (Pair p : bucket[i])
                System.out.print("[" + p.key + "," + p.value + "] ");
        }
        System.out.println();
    }
    // deleting value for key
    public void delete(Integer key) {
        int hashValue = hash(key);
        if (bucket[hashValue] == null)
            return;
        //searching key
        for (Pair p : bucket[hashValue]) {
            if (p.key == key) {
                bucket[hashValue].remove(p);
                numElements--;
                return;
            }
        }
    }

    public int hash(Integer key) {
        return (key.hashCode() % size);
    }

    public int get(Integer key) {
        if(bucket[hash(key)]==null){
            return -1;
        } 
        for (Pair p : bucket[hash(key)]) {
            if (p.key == key)
                return p.value;

        }
        return -1;
    }

    public static void main(String args[]) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter size of bucket");
            int size = sc.nextInt();
            Chaining map = new Chaining(size);
            int input;
            do{
                System.out.println("1: Put");
                System.out.println("2: Get");
                System.out.println("3: Delete");
                System.out.println("4: Traverse");
                System.out.println("5: Get Collisions");
                System.out.println("6: Exit");
                 
                        input = sc.nextInt();
                        switch (input) {
                            case 1:
                                System.out.println("enter key and value to put");
                                map.put(sc.nextInt(), sc.nextInt());
                                break;
                                case 2: 
                                System.out.println("Enter key to get value");
                                int keyToGet = sc.nextInt();
                                int value = map.get(keyToGet);
                                if (value == -1) {
                                    System.out.println("Key not found");
                                } else {
                                System.out.println("Value of key " + keyToGet + " is " + value);
                                }
                                break;
                                case 3: 
                                System.out.println("Enter key to delete");
                                int keyToDelete = sc.nextInt();
                                int val = map.get(keyToDelete);
                                if (val == -1) {
                                    System.out.println("Key not found");
                                }else{
                                map.delete(keyToDelete);
                                System.out.println("Key " + keyToDelete + " deleted");
                                }
                                break;
                                case 4: 
                                System.out.println("Traverse:");
                                map.traverse();
                                break;
                                case 5: 
                                int numCollisions = map.getCollisions();
                                System.out.println("Total number of collisions: " + numCollisions);
                                break;
                                case 6: 
                                System.out.println("Exit done");
                                break;
                                default:
                                System.out.println("Invalid input");
                                break; 
                            }
                   
            }while (input!=6);
        }catch(InputMismatchException ex)
        {
            System.out.println(ex);
        }
    }
}