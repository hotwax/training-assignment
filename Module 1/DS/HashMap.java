import java.util.Set;
import java.util.HashSet;

class HashMap {
    Set<Integer> set = new HashSet<>();
    int[] list = new int[100000000]; // Array initialization.

    // Put Method
    public void put(int key, int value) {
        if (!set.contains(key)) {
            set.add(key);
            list[key] = value;
        } else {
            list[key] = value;
        }
    }

    // Get Method
    public int get(int key) {
        if (!set.contains(key)) {
            return -1;
        }
        return list[key];
    }

    // Delete Method
    public void remove(int key) {
        list[key] = -1;
        set.remove(key);

    }

    public static void main(String args[]) {
        HashMap map = new HashMap();
        map.put(1, 10);
        map.put(12, 20);
        map.put(16, 30);
        map.put(81, 40);
        System.out.println(map.get(1));
        System.out.println(map.get(12));
        System.out.println(map.get(16));
        System.out.println(map.get(81));
    }
}