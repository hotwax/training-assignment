import java.util.Random;

public class LinearProbing {
    static class Pair {
        int key;
        int value;

        Pair(int key, int value) {
            this.key = key;
            this.value = value;
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
                int i = index;

                while (arr[i] != null) {
                    i = (i + 1) % size;
                }
                if ((i != hashFunction(key)) && (arr[i] == null || arr[i].key != key)) {
                    collision++;
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
                while (arr[i] != null) {
                    if (arr[i].key == key) {
                        arr[i] = null;
                        break;
                    }
                    i = (i + 1) % size;
                }
            }
        }

        //  Function to search a key in the hash map
        public int search(int key) {
            int index = hashFunction(key);
            if (arr[index].key == key) {
                return arr[index].value;
            } else {
                int i = index;
                while (arr[i] != null) {
                    if (arr[i].key == key) {
                        return arr[i].value;
                    }
                    i = (i + 1) % size;
                }
            }
            return -1;
        }

        //  Function to print the hash map
        public void print() {
            for (int i = 0; i < size; i++) {
                if (arr[i] != null) {
                    System.out.println(arr[i].key + " " + arr[i].value);
                }
            }
        }
    }

    public static void main(String[] args) {
        hashMap map = new hashMap(10001);
        int z = 234579;
        Random rand =  new Random();
        for(int i=231;i<10000;i++){
            int r = rand.nextInt((1000000-1)+1)+1;
            map.insert(r,i*10);
        }
        System.out.println("Number of collisions: " + map.collision);

    }
}
