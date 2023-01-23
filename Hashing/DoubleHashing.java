import java.util.Scanner;

class HashMap {
    public int size;
    public int elements;
    public int[] table;
    public int collision;
    static Scanner sc = new Scanner(System.in);
    HashMap() {
        System.out.println("Enter the size of the hash table: ");
        size = sc.nextInt();
        table = new int[size];
        for (int i = 0; i < size; i++) {
            table[i] = -1;
        }
        elements = 0;
    }

    boolean isFull() {
        return elements == size;
    }

    int hashFunction(int key) {
        return key % size;
    }

    int hasFunction2(int key) {
        return 7 - (key % 7);
    }

    void doubleHashing(int element, int pos, boolean isStored) {
        if (isStored) {
            int i = pos;
            int j = 1;
            while (table[i] != -1) {
                i = (pos + j * hasFunction2(element)) % size;
                j++;
            }
            if (table[i] == -1) {
                table[i] = element;
                elements++;
            }
        } else {
            int i = pos;
            int j = 1;
            while (table[i] != element) {
                i = (pos + j * hasFunction2(element)) % size;
                j++;
            }
            if (table[i] == element) {
                table[i] = -1;
                elements--;
            }
        }
    }

    void insert(int element) {
        if (isFull()) {
            System.out.println("Hash table is full");
            return;
        }
        int pos = hashFunction(element);
        if (table[pos] == -1) {
            table[pos] = element;
            elements++;
        } else {
            doubleHashing(element, pos, true);
        }
    }

    void delete(int element) {
        int pos = hashFunction(element);
        if (table[pos] == element) {
            table[pos] = -1;
            elements--;
        } else {
            doubleHashing(element, pos, false);
        }
    }
}