import java.util.Random;

public class Chaining {
    static class Pair {
        int key;
        int value;
        Pair next;

        Pair(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    static class hashMap {
        int size;
        Pair[] arr;
        int collision;
        hashMap(int size) {
            this.size = size;
            arr = new Pair[size];
            for (int i = 0; i < size; i++) {
                arr[i] = null;
            }
            collision = 0;
        }

        //  Function to insert a key value pair in the hash map
        public int hashFunction(int key) {
            return key % size;
        }

        //  Function to insert a key value pair in the hash map
        public void insert(int key, int value) {
            int index = hashFunction(key);
            Pair newNode = new Pair(key, value);
            if (arr[index] == null) {
                arr[index] = newNode;
            } else {
                Pair temp = arr[index];
                while (temp.next != null) {
                    temp = temp.next;
                }
                collision++;
                temp.next = newNode;
            }
        }

        //  Function to search a key in the hash map
        public void delete(int key) {
            int index = hashFunction(key);
            Pair temp = arr[index];
            if (temp.key == key) {
                arr[index] = temp.next;
            } else {
                while (temp.next != null) {
                    if (temp.next.key == key) {
                        temp.next = temp.next.next;
                        break;
                    }
                    temp = temp.next;
                }
            }
        }

        //  Function to search a key in the hash map
        public int search(int key) {
            int index = hashFunction(key);
            Pair temp = arr[index];
            while (temp != null) {
                if (temp.key == key) {
                    return temp.value;
                }
                temp = temp.next;
            }
            return -1;
        }
    }

    public static void main(String[] args) {
      hashMap map = new hashMap(10001);
        int z = 234579;


        Random rand = new Random();
        for (int i = 1; i < 10000; i++) {
            int r = rand.nextInt((1000000 - 1) + 1) + 1;
            map.insert(r, i * 10);
        }
        System.out.println("Number of collisions: " + map.collision);
    }
}
