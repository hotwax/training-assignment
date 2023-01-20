public class RandomProbing {
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
                Pair temp = arr[index];
                while (temp.next != null) {
                    temp = temp.next;
                }
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

        //       Function to get collision in Linear Probing
        public int getCollision() {
            int count = 0;
            for (int i = 0; i < size; i++) {
                if (arr[i] != null) {
                    Pair temp = arr[i];
                    while (temp.next != null) {
                        count++;
                        temp = temp.next;
                    }
                }
            }
            return count;
        }
    }

    public static void main(String[] args) {
        hashMap map = new hashMap(10);
        map.insert(1, 10);
        map.insert(2, 20);
        map.insert(3, 30);
        map.insert(4, 40);
        map.insert(5, 50);
        map.insert(6, 60);
        map.insert(7, 70);
    }
}
