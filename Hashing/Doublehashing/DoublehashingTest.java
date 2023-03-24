package Doublehashing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class DoublehashingTest {
    Doublehashing hashmap = new Doublehashing(5);

    @Test
    void insertionTest() {
        hashmap.insertion(60, 30);
        hashmap.insertion(40, 20);
        hashmap.insertion(30, 10);
        hashmap.insertion(70, 67);
        hashmap.insertion(60, 56);
        hashmap.insertion(80, 9);
        Assertions.assertTrue(hashmap.isfull());
        hashmap.insertion(70, 34);// if the map is full but key is already present in map, So the value is upadted
        Assertions.assertEquals(56, hashmap.getval(60));// value is updated
        Assertions.assertEquals(34, hashmap.getval(70));// value is updated
        Assertions.assertEquals(9, hashmap.getval(80));
        hashmap.insertion(50, 4);// new key is not inserted because the map is full
        Assertions.assertFalse(hashmap.contain(50));

    }

    @Test
    void deletionTest() {
        Assertions.assertEquals(-1, hashmap.deletion(60));// Map is empty
        hashmap.insertion(60, 30);
        hashmap.insertion(40, 20);
        hashmap.insertion(30, 10);
        hashmap.insertion(70, 67);
        hashmap.insertion(60, 56);
        hashmap.insertion(80, 9);
        Assertions.assertEquals(60, hashmap.deletion(60));
        Assertions.assertEquals(30, hashmap.deletion(30));
        Assertions.assertEquals(-1, hashmap.deletion(50));// key is not present in Map
        Assertions.assertEquals(-1, hashmap.getval(60));// key is not present
    }

    @Test
    void getValueTest() {
        Assertions.assertEquals(-1, hashmap.getval(34));// Map is empty
        hashmap.insertion(60, 30);
        hashmap.insertion(40, 20);
        hashmap.insertion(30, 10);
        hashmap.insertion(70, 67);
        hashmap.insertion(60, 56);
        hashmap.insertion(80, 9);
        Assertions.assertEquals(56, hashmap.getval(60));
        Assertions.assertEquals(67, hashmap.getval(70));
        Assertions.assertEquals(10, hashmap.getval(30));
        Assertions.assertEquals(9, hashmap.getval(80));
        Assertions.assertEquals(20, hashmap.getval(40));
    }

    @Test
    void getSizeTest() {
        hashmap.insertion(60, 30);
        hashmap.insertion(40, 20);
        hashmap.insertion(30, 10);
        hashmap.insertion(70, 67);
        hashmap.insertion(60, 56);
        hashmap.insertion(80, 9);
        Assertions.assertEquals(5, hashmap.getsize());
    }

    @Test
    void clearMapTest() {
        hashmap.insertion(60, 30);
        hashmap.insertion(40, 20);
        hashmap.insertion(30, 10);
        hashmap.insertion(70, 67);
        hashmap.insertion(60, 56);
        hashmap.insertion(80, 9);
        Assertions.assertFalse(hashmap.isempty());
        hashmap.clear();
        Assertions.assertTrue(hashmap.isempty());
    }

    @Test
    void mapIsEmptyTest() {
        Assertions.assertTrue(hashmap.isempty());
        hashmap.insertion(60, 30);
        hashmap.insertion(40, 20);
        hashmap.insertion(30, 10);
        hashmap.insertion(70, 67);
        hashmap.insertion(60, 56);
        hashmap.insertion(80, 9);
        Assertions.assertFalse(hashmap.isempty());

    }

    @Test
    void mapIsFullTest() {
        Assertions.assertFalse(hashmap.isfull());
        hashmap.insertion(60, 30);
        hashmap.insertion(40, 20);
        hashmap.insertion(30, 10);
        hashmap.insertion(70, 67);
        hashmap.insertion(60, 56);
        hashmap.insertion(80, 9);
        Assertions.assertTrue(hashmap.isfull());
    }

}
