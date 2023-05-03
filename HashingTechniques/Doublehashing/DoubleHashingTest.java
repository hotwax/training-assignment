package Doublehashing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class DoubleHashingTest {
    DoubleHashing hashMap = new DoubleHashing(5);

    @Test
    void insertionTest() {
        hashMap.insertion(60, 30);
        hashMap.insertion(40, 20);
        hashMap.insertion(30, 10);
        hashMap.insertion(70, 67);
        hashMap.insertion(60, 56);
        hashMap.insertion(80, 9);
        Assertions.assertTrue(hashMap.isFull());
        hashMap.insertion(70, 34);// if the map is full but key is already present in map, So the value is upadted
        Assertions.assertEquals(56, hashMap.getValue(60));// value is updated
        Assertions.assertEquals(34, hashMap.getValue(70));// value is updated
        Assertions.assertEquals(9, hashMap.getValue(80));
        hashMap.insertion(50, 4);// new key is not inserted because the map is full
        Assertions.assertFalse(hashMap.contain(50));
    }

    @Test
    void deletionTest() {
        Assertions.assertEquals(-1, hashMap.deletion(60));// Map is empty
        hashMap.insertion(60, 30);
        hashMap.insertion(40, 20);
        hashMap.insertion(30, 10);
        hashMap.insertion(70, 67);
        hashMap.insertion(60, 56);
        hashMap.insertion(80, 9);
        Assertions.assertEquals(60, hashMap.deletion(60));
        Assertions.assertEquals(30, hashMap.deletion(30));
        Assertions.assertEquals(-1, hashMap.deletion(50));// key is not present in Map
        Assertions.assertEquals(-1, hashMap.getValue(60));// key is not present
    }

    @Test
    void getValueTest() {
        Assertions.assertEquals(-1, hashMap.getValue(34));// Map is empty
        hashMap.insertion(60, 30);
        hashMap.insertion(40, 20);
        hashMap.insertion(30, 10);
        hashMap.insertion(70, 67);
        hashMap.insertion(60, 56);
        hashMap.insertion(80, 9);
        Assertions.assertEquals(56, hashMap.getValue(60));
        Assertions.assertEquals(67, hashMap.getValue(70));
        Assertions.assertEquals(10, hashMap.getValue(30));
        Assertions.assertEquals(9, hashMap.getValue(80));
        Assertions.assertEquals(20, hashMap.getValue(40));
    }

    @Test
    void getSizeTest() {
        hashMap.insertion(60, 30);
        hashMap.insertion(40, 20);
        hashMap.insertion(30, 10);
        hashMap.insertion(70, 67);
        hashMap.insertion(60, 56);
        hashMap.insertion(80, 9);
        Assertions.assertEquals(5, hashMap.getSize());
    }

    @Test
    void clearMapTest() {
        hashMap.insertion(60, 30);
        hashMap.insertion(40, 20);
        hashMap.insertion(30, 10);
        hashMap.insertion(70, 67);
        hashMap.insertion(60, 56);
        hashMap.insertion(80, 9);
        Assertions.assertFalse(hashMap.isEmpty());
        hashMap.clear();
        Assertions.assertTrue(hashMap.isEmpty());
    }

    @Test
    void mapIsEmptyTest() {
        Assertions.assertTrue(hashMap.isEmpty());
        hashMap.insertion(60, 30);
        hashMap.insertion(40, 20);
        hashMap.insertion(30, 10);
        hashMap.insertion(70, 67);
        hashMap.insertion(60, 56);
        hashMap.insertion(80, 9);
        Assertions.assertFalse(hashMap.isEmpty());
    }

    @Test
    void mapIsFullTest() {
        Assertions.assertFalse(hashMap.isFull());
        hashMap.insertion(60, 30);
        hashMap.insertion(40, 20);
        hashMap.insertion(30, 10);
        hashMap.insertion(70, 67);
        hashMap.insertion(60, 56);
        hashMap.insertion(80, 9);
        Assertions.assertTrue(hashMap.isFull());
    }
}
