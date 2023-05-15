package hashmap;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
public class CustomHashMapTest {
@Test
    public void testPutAndGet() {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        assertEquals(1, (int) map.get("one"));
        assertEquals(2, (int) map.get("two"));
        assertEquals(3, (int) map.get("three"));
    }
        @Test
    public void testPutOverride() {
        CustomHashMap<String, String> map = new CustomHashMap<>();
        map.put("one", "1");
        map.put("two", "2");
        map.put("one", "one");
        assertEquals("one", map.get("one"));
        assertEquals("2", map.get("two"));
    }
    @Test
    public void testRemove() {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.remove("two");
        assertNull(map.get("two"));
        assertEquals(1, (int) map.get("one"));
        assertEquals(3, (int) map.get("three"));
    }
    @Test
    public void testShowAll() {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);
        map.put("ten", 10);
        map.showAll();
    }
}
