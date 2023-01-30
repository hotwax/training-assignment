import java.util.Scanner;


class HashEntry {
    String key;
    int value;

    /* Constructor */
    HashEntry(String key, int value) {
        this.key = key;
        this.value = value;
    }
}

class HashTable {
    private int TABLE_SIZE;
    private int size;
    private HashEntry[] table;
    private int primeSize;

    /* Constructor */
    public HashTable(int ts) {
        size = 0;
        TABLE_SIZE = ts;
        table = new HashEntry[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
        primeSize = getPrime();
    }

    // Function to get prime number less than table size for myhash2 function 
    public int getPrime() {
        for (int i = TABLE_SIZE - 1; i >= 1; i--) {
            int fact = 0;
            for (int j = 2; j <= (int) Math.sqrt(i); j++)
                if (i % j == 0)
                    fact++;
            if (fact == 0)
                return i;
        }
        return 3;
    }

    // Function to get the size
    public int getSize() {
        return size;
    }

    // Function to check whether the table is empty or not.
    public boolean isEmpty() {
        return size == 0;
    }

    /* Function to clear hash table */
    public void makeEmpty() {
        size = 0;
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
    }

    /* Function to get value of a key */
    public int get(String key) {
        int hash1 = myhash1(key);
        int hash2 = myhash2(key);
        while (table[hash1] != null && !table[hash1].key.equals(key)) {
            hash1 += hash2;
            hash1 %= TABLE_SIZE;
        }
        return table[hash1].value;
    }

    /* Function to insert a key value pair */
    public void insert(String key, int value) {
        if (size == TABLE_SIZE) {
            System.out.println("Table full");
            return;
        }
        int hash1 = myhash1(key);
        int hash2 = myhash2(key);
        while (table[hash1] != null) {
            hash1 += hash2;
            hash1 %= TABLE_SIZE;
        }
        table[hash1] = new HashEntry(key, value);
        size++;
    }

    /* Function to remove a key */
    public void remove(String key) {
        int hash1 = myhash1(key);
        int hash2 = myhash2(key);
        while (table[hash1] != null && !table[hash1].key.equals(key)) {
            hash1 += hash2;
            hash1 %= TABLE_SIZE;
        }
        table[hash1] = null;
        size--;
    }

    /* Function myhash which gives a hash value for a given string */
    private int myhash1(String x) {
        int hashVal = x.hashCode();
        hashVal %= TABLE_SIZE;
        if (hashVal < 0)
            hashVal += TABLE_SIZE;
        return hashVal;
    }

    /* Function myhash function for double hashing */
    private int myhash2(String x) {
        int hashVal = x.hashCode();
        hashVal %= TABLE_SIZE;
        if (hashVal < 0)
            hashVal += TABLE_SIZE;
        return primeSize - hashVal % primeSize;
    }

    /* Function to print hash table */
    public void printHashTable() {
        System.out.println("\nHash Table");
        for (int i = 0; i < TABLE_SIZE; i++)
            if (table[i] != null)
                System.out.println(table[i].key + " " + table[i].value);
    }
}

/* Class DoubleHashingHashTableTest */
public class DoubleHashing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter size");
        /* Make object of HashTable */
        HashTable ht = new HashTable(sc.nextInt());
        char ch;
        /* Perform HashTable operations */
        do {
            System.out.println("\nKindly select one of the following Hash Table Operations\n");
            System.out.println("1. Insert ");
            System.out.println("2. Remove");
            System.out.println("3. Get");
            System.out.println("4. Check Empty");
            System.out.println("5. Clear");
            System.out.println("6. Size");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter key and value");
                    ht.insert(sc.next(), sc.nextInt());
                    break;
                case 2:
                    System.out.println("Enter key");
                    ht.remove(sc.next());
                    break;
                case 3:
                    System.out.println("Enter key");
                    System.out.println("Value = " + ht.get(sc.next()));
                    break;
                case 4:
                    System.out.println("Empty Status " + ht.isEmpty());
                    break;
                case 5:
                    ht.makeEmpty();
                    System.out.println("Hash Table Cleared\n");
                    break;
                case 6:
                    System.out.println("Size = " + ht.getSize());
                    break;
                default:
                    System.out.println("Kindly Enter Correct operation\n ");
                    break;
            }
            /* Display hash table */
            ht.printHashTable();
            System.out.println("\nDo you want to continue (Type y or n)\n");
            ch = sc.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }
}