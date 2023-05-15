package chaining;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
public class ChainingTest {
 @Test
  public void testPut() {
    Chaining chaining = new Chaining(10);
    chaining.put(1, 10);
    chaining.put(2, 20);
    assertEquals(new Integer(10), chaining.get(1));
    assertEquals(new Integer(20), chaining.get(2));
  }
  @Test
  public void testGet(){
    Chaining chaining = new Chaining(10);
    chaining.put(77, 106);
    chaining.put(23, 21);
    assertEquals(new Integer(106), chaining.get(77));
    assertEquals(new Integer(21), chaining.get(23));
    assertEquals(null, chaining.get(555));
  }
  
  @Test
  public void testRemove() {
    Chaining chaining = new Chaining(10);
    chaining.put(1, 10);
    chaining.put(2, 20);
    chaining.remove(1);
    assertFalse(chaining.isEmpty());
    chaining.remove(2);
    assertTrue(chaining.isEmpty());
    chaining.showAll();
  }
  @Test
  public void testCollisions() {
    Chaining chaining = new Chaining(5);
    chaining.put(1, 10);
    chaining.put(6, 20);
    assertEquals(1, chaining.getCollisions());
    chaining.put(11, 20);
    assertEquals(2, chaining.getCollisions());
  }
  @Test
  public void testSize() {
    Chaining chaining = new Chaining(10);
    chaining.put(1, 10);
    chaining.put(2, 20);
    assertEquals(2, chaining.getSize());
  }
}
