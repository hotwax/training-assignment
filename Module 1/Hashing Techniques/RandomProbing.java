import java.util.Scanner;

public class RandomProbing {
    int n1, n2;
    Pair arr[];
    int collisions;
    boolean[] deleted;

    class Pair {
        int key;
        int val;

        public Pair(int t1, int t2) {
            key = t1;
            val = t2;
        }
    }

    public RandomProbing(int n, int m) {
        this.n1 = m;
        this.n2 = n;
        collisions = 0;
        arr = new Pair[m];
        deleted = new boolean[m];
    }

    public int hashFunction(Integer k) {
        return (k.hashCode() % n1);
    }

    public void put(int k, int v) {
        int probe = hashFunction(k);

        int n1 = 0;
        while ((arr[probe] != null && arr[probe].key != k) && deleted[probe]) {
            n1 = (int) (Math.random() * (n1 - 1));
            probe = (probe + n1) % n1;
        }
        // if probe is not equal to original hash of key then its a collision
        // if the same key is inseted again with different value then this is not a
        // collision, its updation
        if (probe != hashFunction(k) && (arr[probe] == null || arr[probe].key != k))
            collisions++;
        arr[probe] = new Pair(k, v);
        deleted[probe] = true;

    }
    // Delete Method
    public void delete(int k) {
        int hashValue = hashFunction(k);
        int probe = hashValue;
        int offset = 0;
        int start = probe;
        while (arr[probe] != null && arr[probe].key != k) {
            offset = (int) (Math.random() * (n1 - 1));
            probe = (probe + offset) % n1;
            if (probe == start) {
                return;
            }
        }
        // if encounter a null value then value to delete is not present
        if (arr[probe] == null) {
            return;
        }
        // we can't make arr value null(it will create problrm in searching) thats why
        // maintained a deleted boolean array
        deleted[probe] = false;
        for (int i = 0; i < n1; i++) {
            System.out.print(deleted[i] + " ");
        }
        System.out.println();
    }

    // Get Method:
    public int get(Integer k) {
        int hashValue = hashFunction(k);
        int probe = hashValue;
        int offset = 1;
        int start = probe;
        // Iterate to find array in cluster
        while (true) {
            // if we found value then break;
            if (arr[probe] != null && arr[probe].key == k)
                break;
            // if we again reach to where we started, then element is not present
            probe = (probe + offset) % n1;
            if (probe == start) {
                System.out.println("not found;");
                return -1;
            }
        }
        return arr[probe].val;
    }

    // Traversal Method
    public void travel() {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null || !deleted[i])
                continue;
            System.out.print("[" + arr[i].key + "," + arr[i].val + "," + i + "] ");
        }

        System.out.println();
    }

    public int getCol() {
        return collisions;
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        boolean flag = true;
        System.out.println("Enter size of bucket");
        int m = sc.nextInt();
        System.out.println("Enter size of HashMap");
        int n = sc.nextInt();
        RandomProbing map = new RandomProbing(n, m);
        int val = 10, key = 1;
        Set<Integer> s = new HashSet<>();
        System.out.println("\n1: Insert");
        System.out.println("2: Get");
        System.out.println("3: Delete");
        System.out.println("4: Traverse");
        System.out.println("5: Get Collisions");
        System.out.println("6: Exit");
        while (flag) {
            int x = sc.nextInt();
            switch (x) {
                case 1:// put
                    System.out.println("Enter key and value to be inserted.");
                    map.put(sc.nextInt(), sc.nextInt());
                    break;
                case 2:// get
                    System.out.println("Enter Key.");
                    System.out.println("value= " + map.get(sc.nextInt()));
                    break;
                case 3: // delete
                    System.out.println("Enter key to be deleted.");
                    map.delete(map.get(sc.nextInt()));
                    System.out.println("value deleted successfully");
                    break;
                case 4:// traverse
                    System.out.println("Traversal = ");
                    
                    map.travel();
                    break;
                case 5:// get collision
                    System.out.println("total collisions= " + map.getCol());
                    break;
                case 6:// terminate program
                    flag = false;
                    break;
                default:
                    System.out.println("Kindly Enter the correct choice.");
            }
        }

    }
}