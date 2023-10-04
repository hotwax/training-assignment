
//importing necessary packages
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

//HashMap class 
public class HashMap {
    LinkedList<Pair> bucket[];
    int size, numElements;
    int collisions;

    class Pair {
        int key;
        int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public HashMap(Integer size) {
        this.size = size;
        numElements = 0;
        collisions = 0;

        // creating bucket of size, size
        bucket = new LinkedList[size];
    }

    public int getCollisions() {
        int numCollisions = 0;

        // total collision
        for (int index = 0; index < bucket.length; index++) {
            if (bucket[index] != null && bucket[index].size() > 1) {
                numCollisions++;
            }
        }
        return numCollisions;
    }

    // putting the key and value
    public void put(Integer key, Integer value) {
        int hashValue = hash(key);
        if (bucket[hashValue] == null) {
            bucket[hashValue] = new LinkedList<>();
        }

        for (Pair pair : bucket[hashValue]) {
            if (pair.key == key) {
                pair.value = value;
                return;
            }
        }

        // adding key
        bucket[hashValue].add(new Pair(key, value));
        numElements++;
    }

    // traversing the Map
    public void traverse() {
        for (int index = 0; index < bucket.length; index++) {
            if (bucket[index] == null)
                continue;
            for (Pair pair : bucket[index])
                System.out.print("[" + pair.key + "," + pair.value + "] ");
        }
        System.out.println();
    }

    // Deleting value for key
    public void delete(Integer key) {
        int hashValue = hash(key);
        if (bucket[hashValue] == null)
            return;

        // Searching for the key for deletion
        for (Pair pair : bucket[hashValue]) {
            if (pair.key == key) {
                bucket[hashValue].remove(pair);
                numElements--;
                return;
            }
        }
    }

    public int hash(Integer key) {
        return (key.hashCode() % size);
    }

    public int get(Integer key) {
        if (bucket[hash(key)] == null) {
            return -1;
        }
        for (Pair pair : bucket[hash(key)]) {
            if (pair.key == key)
                return pair.value;

        }
        return -1;
    }

    public static void main(String args[]) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter size of bucket");
            int size = input.nextInt();
            HashMap map = new HashMap(size);
            int choice;
            do {
                System.out.println("1: Put");
                System.out.println("2: Get");
                System.out.println("3: Delete");
                System.out.println("4: Traverse");
                System.out.println("5: Get Collisions");
                System.out.println("6: Exit");

                choice = input.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Enter key and value to put");
                        map.put(input.nextInt(), input.nextInt());
                        break;
                    case 2:
                        System.out.println("Enter key to get value");
                        int keyToGet = input.nextInt();
                        int valueToGet = map.get(keyToGet);
                        if (valueToGet == -1) {
                            System.out.println("Key not found");
                        } else {
                            System.out.println("Value of key " + keyToGet + " is " + valueToGet);
                        }
                        break;
                    case 3:
                        System.out.println("Enter key to delete");
                        int keyToDelete = input.nextInt();
                        int value = map.get(keyToDelete);
                        if (value == -1) {
                            System.out.println("Key not found");
                        } else {
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
            } while (choice != 6);
        } catch (InputMismatchException exception) {
            System.out.println(exception);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}