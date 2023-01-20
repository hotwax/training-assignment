import java.util.Random;

public class QuadraticProbing {
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
                int i = index;
                int j = 1;
                while (arr[i] != null) {
                    i = (i + j * j) % size;
                    j++;
                }
                if ((i != hashFunction(key)) && (arr[i] == null || arr[i].key != key)) {
                    arr[i] = newNode;
                }
            }
        }

        //  Function to search a key in the hash map
        public void delete(int key) {
            int index = hashFunction(key);
            if (arr[index].key == key) {
                arr[index] = null;
            } else {
                int i = index;
                int j = 1;
                while (arr[i] != null) {
                    if (arr[i].key == key) {
                        arr[i] = null;
                        break;
                    }
                    i = (i + j * j) % size;
                    j++;
                }
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
}
