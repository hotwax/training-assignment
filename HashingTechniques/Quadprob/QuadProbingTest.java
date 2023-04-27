package Quadprob;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class QuadProbingTest {
    QuadraticProbing hashMap = new QuadraticProbing(5);

    @Test
    void insertionTest() {
        hashMap.insertion("Gourav", "Rana");
        hashMap.insertion("Gourav", "Singh");
        hashMap.insertion("Yash", "Singhal");
        hashMap.insertion("Yash", "Rathore");
        hashMap.insertion("Vishudhi", "Jain");
        hashMap.insertion("Niharika", "Gurjar");
        hashMap.insertion("Kunal", "Bhade");
        Assertions.assertEquals("Singh", hashMap.getValue("Gourav"));// value is updated
        Assertions.assertEquals("Rathore", hashMap.getValue("Yash"));// value is updated
        Assertions.assertEquals("Jain", hashMap.getValue("Vishudhi"));
        Assertions.assertEquals("Gurjar", hashMap.getValue("Niharika"));
        Assertions.assertEquals("Bhade", hashMap.getValue("Kunal"));
        hashMap.insertion("Kratika", "Khichi");// insertion is not possible because Map is full
        Assertions.assertFalse(hashMap.contain("Kratika"));

    }

    @Test
    void deletionTest() {
        Assertions.assertEquals(null, hashMap.deleted("Gourav"));// Map is empty
        hashMap.insertion("Gourav", "Rana");
        hashMap.insertion("Gourav", "Singh");
        hashMap.insertion("Yash", "Singhal");
        hashMap.insertion("Yash", "Rathore");
        hashMap.insertion("Vishudhi", "Jain");
        hashMap.insertion("Niharika", "Gurjar");
        hashMap.insertion("Kunal", "Bhade");
        Assertions.assertEquals("Gourav", hashMap.deleted("Gourav"));
        Assertions.assertEquals("Yash", hashMap.deleted("Yash"));
        Assertions.assertEquals("Kunal", hashMap.deleted("Kunal"));
        Assertions.assertEquals(null, hashMap.deleted("Yash"));// key is not present in Map
    }

    @Test
    void getValueTest() {
        Assertions.assertEquals(null, hashMap.getValue("Gourav"));// Map is empty
        hashMap.insertion("Sam", "s");
        hashMap.insertion("Sagar", "a");
        hashMap.insertion("Raghav", "y");
        hashMap.insertion("Rohan", "v");
        hashMap.insertion("Sagar", "c");
        hashMap.insertion("Kunal", "d");// key is not inserted in Map
        Assertions.assertTrue(hashMap.contain("Kunal"));
        Assertions.assertTrue(hashMap.contain("Sam"));
        Assertions.assertEquals("s", hashMap.getValue("Sam"));
        Assertions.assertEquals("c", hashMap.getValue("Sagar"));
        Assertions.assertEquals("y", hashMap.getValue("Raghav"));
        Assertions.assertEquals("v", hashMap.getValue("Rohan"));
        Assertions.assertEquals("d", hashMap.getValue("Kunal"));
        Assertions.assertEquals(null, hashMap.getValue("Gourav"));// key is not present in Map
    }

    @Test
    void getSizeTest() {
        hashMap.insertion("Sam", "s");
        hashMap.insertion("Sagar", "a");
        hashMap.insertion("Raghav", "y");
        hashMap.insertion("Rohan", "v");
        hashMap.insertion("Rudra", "b");
        hashMap.insertion("Sagar", "c");
        hashMap.insertion("Kunal", "d");
        Assertions.assertEquals(5, hashMap.getSize());
        Assertions.assertTrue(hashMap.isFull());
    }

    @Test
    void clearMapTest() {
        hashMap.insertion("Sam", "s");
        hashMap.insertion("Sagar", "a");
        hashMap.insertion("Raghav", "y");
        hashMap.insertion("Rohan", "v");
        hashMap.insertion("Rudra", "b");
        hashMap.insertion("Sagar", "c");
        Assertions.assertFalse(hashMap.isEmpty());
        hashMap.clear();
        Assertions.assertTrue(hashMap.isEmpty());
    }

    @Test
    void mapIsEmptyTest() {
        Assertions.assertTrue(hashMap.isEmpty());
        hashMap.insertion("Sam", "s");
        hashMap.insertion("Sagar", "a");
        hashMap.insertion("Raghav", "y");
        hashMap.insertion("Rohan", "v");
        hashMap.insertion("Rudra", "b");
        hashMap.insertion("Sagar", "c");
        hashMap.insertion("Kunal", "d");
        Assertions.assertFalse(hashMap.isEmpty());
    }

    @Test
    void mapIsFullTest() {
        hashMap.insertion("Sam", "s");
        hashMap.insertion("Sagar", "a");
        Assertions.assertFalse(hashMap.isFull());
        hashMap.insertion("Raghav", "y");
        hashMap.insertion("Rohan", "v");
        hashMap.insertion("Gourav", "b");
        hashMap.insertion("Sagar", "c");
        Assertions.assertTrue(hashMap.isFull());
    }

}
