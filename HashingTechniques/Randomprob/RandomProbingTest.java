package Randomprob;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class RandomProbingTest {
    RandomProbing hashMap=new RandomProbing(5);

    @Test
    void insertionTest() {
        hashMap.insertion(3, 30);
        hashMap.insertion(2, 20);
        hashMap.insertion(4, 40);
        hashMap.insertion(3, 90);
        hashMap.insertion(6, 60);
        hashMap.insertion(8, 80);
        hashMap.insertion(50, 8);
        Assertions.assertFalse(hashMap.contain(50));
        Assertions.assertEquals(90, hashMap.getValue(3));// value is updated
        Assertions.assertEquals(20, hashMap.getValue(2));
        Assertions.assertEquals(40, hashMap.getValue(4));
        Assertions.assertEquals(60, hashMap.getValue(6));
        Assertions.assertEquals(80, hashMap.getValue(8));
        hashMap.insertion(9, 10);// insertion is not possible because Map is full
        Assertions.assertFalse(hashMap.contain(10));

    }

    @Test
    void deletionTest() {
        Assertions.assertEquals(-1, hashMap.deletion(24));// Map is empty
        hashMap.insertion(3, 30);
        hashMap.insertion(2, 20);
        hashMap.insertion(4, 40);
        hashMap.insertion(3, 90);
        hashMap.insertion(6, 60);
        hashMap.insertion(8, 80);
        Assertions.assertEquals(2, hashMap.deletion(2));
        Assertions.assertEquals(4, hashMap.deletion(4));
        Assertions.assertEquals(8, hashMap.deletion(8));
        Assertions.assertEquals(3, hashMap.deletion(3));
        Assertions.assertEquals(-1, hashMap.deletion(10));// key is not present in Map
    }

    @Test
    void getValueTest() {
        Assertions.assertEquals(-1, hashMap.getValue(10));// Map is empty
        hashMap.insertion(3, 30);
        hashMap.insertion(2, 20);
        hashMap.insertion(4, 40);
        hashMap.insertion(3, 90);
        hashMap.insertion(6, 60);
        hashMap.insertion(8, 80);
        hashMap.insertion(40,67);//Key is not inserted in Map because map is full
        Assertions.assertFalse(hashMap.contain(40));
        Assertions.assertTrue(hashMap.contain(3));
        Assertions.assertEquals(90, hashMap.getValue(3));
        Assertions.assertEquals(40, hashMap.getValue(4));
        Assertions.assertEquals(60, hashMap.getValue(6));
        Assertions.assertEquals(80, hashMap.getValue(8));
        Assertions.assertEquals(-1, hashMap.getValue(100));// key is not present in Map
    }

    @Test
    void getSizeTest() {
        hashMap.insertion(3, 30);
        hashMap.insertion(2, 20);
        hashMap.insertion(4, 40);
        hashMap.insertion(3, 90);
        hashMap.insertion(6, 60);
        hashMap.insertion(8, 80);
        hashMap.insertion(40,67);
        Assertions.assertEquals(5, hashMap.getSize());
        Assertions.assertTrue(hashMap.isFull());
    }

    @Test
    void clearMapTest() {
        hashMap.insertion(3, 30);
        hashMap.insertion(2, 20);
        hashMap.insertion(4, 40);
        hashMap.insertion(3, 90);
        hashMap.insertion(6, 60);
        hashMap.insertion(8, 80);
        Assertions.assertFalse(hashMap.isEmpty());
        hashMap.clear();
        Assertions.assertTrue(hashMap.isEmpty());
    }

    @Test
    void mapIsEmptyTest() {
        Assertions.assertTrue(hashMap.isEmpty());
        hashMap.insertion(3, 30);
        hashMap.insertion(2, 20);
        hashMap.insertion(4, 40);
        Assertions.assertFalse(hashMap.isEmpty());
    }

    @Test
    void mapIsFullTest() {
        hashMap.insertion(3, 30);
        hashMap.insertion(2, 20);
        hashMap.insertion(4, 40);
        Assertions.assertFalse(hashMap.isFull());
        hashMap.insertion(3, 90);
        hashMap.insertion(6, 60);
        hashMap.insertion(8, 80);
        Assertions.assertTrue(hashMap.isFull());
    }   
}
