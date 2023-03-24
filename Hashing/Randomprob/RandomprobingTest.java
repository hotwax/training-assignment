package Randomprob;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class RandomprobingTest {
    Randomprobing hashmap=new Randomprobing(5);


    @Test
    void insertionTest() {
        hashmap.insertion(3, 30);
        hashmap.insertion(2, 20);
        hashmap.insertion(4, 40);
        hashmap.insertion(3, 90);
        hashmap.insertion(6, 60);
        hashmap.insertion(8, 80);
        hashmap.insertion(50, 8);
        Assertions.assertFalse(hashmap.contain(50));
        Assertions.assertEquals(90, hashmap.getval(3));// value is updated
        Assertions.assertEquals(20, hashmap.getval(2));
        Assertions.assertEquals(40, hashmap.getval(4));
        Assertions.assertEquals(60, hashmap.getval(6));
        Assertions.assertEquals(80, hashmap.getval(8));
        hashmap.insertion(9, 10);// insertion is not possible because Map is full
        Assertions.assertFalse(hashmap.contain(10));

    }

    @Test
    void deletionTest() {
        Assertions.assertEquals(-1, hashmap.deletion(24));// Map is empty
        hashmap.insertion(3, 30);
        hashmap.insertion(2, 20);
        hashmap.insertion(4, 40);
        hashmap.insertion(3, 90);
        hashmap.insertion(6, 60);
        hashmap.insertion(8, 80);
        Assertions.assertEquals(2, hashmap.deletion(2));
        Assertions.assertEquals(4, hashmap.deletion(4));
        Assertions.assertEquals(8, hashmap.deletion(8));
        Assertions.assertEquals(3, hashmap.deletion(3));
        Assertions.assertEquals(-1, hashmap.deletion(10));// key is not present in Map
    }

    @Test
    void getValueTest() {
        Assertions.assertEquals(-1, hashmap.getval(10));// Map is empty
        hashmap.insertion(3, 30);
        hashmap.insertion(2, 20);
        hashmap.insertion(4, 40);
        hashmap.insertion(3, 90);
        hashmap.insertion(6, 60);
        hashmap.insertion(8, 80);
        hashmap.insertion(40,67);//Key is not inserted in Map because map is full
        Assertions.assertFalse(hashmap.contain(40));
        Assertions.assertTrue(hashmap.contain(3));
        Assertions.assertEquals(90, hashmap.getval(3));
        Assertions.assertEquals(40, hashmap.getval(4));
        Assertions.assertEquals(60, hashmap.getval(6));
        Assertions.assertEquals(80, hashmap.getval(8));
        Assertions.assertEquals(-1, hashmap.getval(100));// key is not present in Map
    }

    @Test
    void getSizeTest() {
        hashmap.insertion(3, 30);
        hashmap.insertion(2, 20);
        hashmap.insertion(4, 40);
        hashmap.insertion(3, 90);
        hashmap.insertion(6, 60);
        hashmap.insertion(8, 80);
        hashmap.insertion(40,67);
        Assertions.assertEquals(5, hashmap.getsize());
        Assertions.assertTrue(hashmap.isfull());
    }

    @Test
    void clearMapTest() {
        hashmap.insertion(3, 30);
        hashmap.insertion(2, 20);
        hashmap.insertion(4, 40);
        hashmap.insertion(3, 90);
        hashmap.insertion(6, 60);
        hashmap.insertion(8, 80);
        Assertions.assertFalse(hashmap.isempty());
        hashmap.clear();
        Assertions.assertTrue(hashmap.isempty());
    }

    @Test
    void mapIsEmptyTest() {
        Assertions.assertTrue(hashmap.isempty());
        hashmap.insertion(3, 30);
        hashmap.insertion(2, 20);
        hashmap.insertion(4, 40);
        Assertions.assertFalse(hashmap.isempty());
    }

    @Test
    void mapIsFullTest() {
        hashmap.insertion(3, 30);
        hashmap.insertion(2, 20);
        hashmap.insertion(4, 40);
        Assertions.assertFalse(hashmap.isfull());
        hashmap.insertion(3, 90);
        hashmap.insertion(6, 60);
        hashmap.insertion(8, 80);
        Assertions.assertTrue(hashmap.isfull());
    }   


}
