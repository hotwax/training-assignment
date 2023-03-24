package Quadprob;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class QuadprobingTest {
    Quadprobing hashmap = new Quadprobing(5);

    @Test
    void insertionTest() {
        hashmap.insertion("Gourav", "Rana");
        hashmap.insertion("Gourav", "Singh");
        hashmap.insertion("Yash", "Singhal");
        hashmap.insertion("Yash", "Rathore");
        hashmap.insertion("Vishudhi", "Jain");
        hashmap.insertion("Niharika", "Gurjar");
        hashmap.insertion("Kunal", "Bhade");
        Assertions.assertEquals("Singh", hashmap.getval("Gourav"));// value is updated
        Assertions.assertEquals("Rathore", hashmap.getval("Yash"));// value is updated
        Assertions.assertEquals("Jain", hashmap.getval("Vishudhi"));
        Assertions.assertEquals("Gurjar", hashmap.getval("Niharika"));
        Assertions.assertEquals("Bhade", hashmap.getval("Kunal"));
        hashmap.insertion("Kratika", "Khichi");// insertion is not possible because Map is full
        Assertions.assertFalse(hashmap.contain("Kratika"));

    }

    @Test
    void deletionTest() {
        Assertions.assertEquals(null, hashmap.deleted("Gourav"));// Map is empty
        hashmap.insertion("Gourav", "Rana");
        hashmap.insertion("Gourav", "Singh");
        hashmap.insertion("Yash", "Singhal");
        hashmap.insertion("Yash", "Rathore");
        hashmap.insertion("Vishudhi", "Jain");
        hashmap.insertion("Niharika", "Gurjar");
        hashmap.insertion("Kunal", "Bhade");
        Assertions.assertEquals("Gourav", hashmap.deleted("Gourav"));
        Assertions.assertEquals("Yash", hashmap.deleted("Yash"));
        Assertions.assertEquals("Kunal", hashmap.deleted("Kunal"));
        Assertions.assertEquals(null, hashmap.deleted("Yash"));// key is not present in Map
    }

    @Test
    void getValueTest() {
        Assertions.assertEquals(null, hashmap.getval("Gourav"));// Map is empty
        hashmap.insertion("Sam", "s");
        hashmap.insertion("Sagar", "a");
        hashmap.insertion("Raghav", "y");
        hashmap.insertion("Rohan", "v");
        hashmap.insertion("Sagar", "c");
        hashmap.insertion("Kunal", "d");// key is not inserted in Map
        Assertions.assertTrue(hashmap.contain("Kunal"));
        Assertions.assertTrue(hashmap.contain("Sam"));
        Assertions.assertEquals("s", hashmap.getval("Sam"));
        Assertions.assertEquals("c", hashmap.getval("Sagar"));
        Assertions.assertEquals("y", hashmap.getval("Raghav"));
        Assertions.assertEquals("v", hashmap.getval("Rohan"));
        Assertions.assertEquals("d", hashmap.getval("Kunal"));
        Assertions.assertEquals(null, hashmap.getval("Gourav"));// key is not present in Map
    }

    @Test
    void getSizeTest() {
        hashmap.insertion("Sam", "s");
        hashmap.insertion("Sagar", "a");
        hashmap.insertion("Raghav", "y");
        hashmap.insertion("Rohan", "v");
        hashmap.insertion("Rudra", "b");
        hashmap.insertion("Sagar", "c");
        hashmap.insertion("Kunal", "d");
        Assertions.assertEquals(5, hashmap.getsize());
        Assertions.assertTrue(hashmap.isfull());
    }

    @Test
    void clearMapTest() {
        hashmap.insertion("Sam", "s");
        hashmap.insertion("Sagar", "a");
        hashmap.insertion("Raghav", "y");
        hashmap.insertion("Rohan", "v");
        hashmap.insertion("Rudra", "b");
        hashmap.insertion("Sagar", "c");
        Assertions.assertFalse(hashmap.isempty());
        hashmap.clear();
        Assertions.assertTrue(hashmap.isempty());
    }

    @Test
    void mapIsEmptyTest() {
        Assertions.assertTrue(hashmap.isempty());
        hashmap.insertion("Sam", "s");
        hashmap.insertion("Sagar", "a");
        hashmap.insertion("Raghav", "y");
        hashmap.insertion("Rohan", "v");
        hashmap.insertion("Rudra", "b");
        hashmap.insertion("Sagar", "c");
        hashmap.insertion("Kunal", "d");
        Assertions.assertFalse(hashmap.isempty());
    }

    @Test
    void mapIsFullTest() {
        hashmap.insertion("Sam", "s");
        hashmap.insertion("Sagar", "a");
        Assertions.assertFalse(hashmap.isfull());
        hashmap.insertion("Raghav", "y");
        hashmap.insertion("Rohan", "v");
        hashmap.insertion("Gourav", "b");
        hashmap.insertion("Sagar", "c");
        Assertions.assertTrue(hashmap.isfull());
    }

}
