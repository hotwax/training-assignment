package linearprobing;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
public class LinearProbingTest {
@Test
  public void testPut() {
    LinearProbing linear = new LinearProbing(10);
    linear.insert(15, 100);
    linear.insert(24, 260);
    assertEquals(new Integer(100), linear.get(15));
    assertEquals(new Integer(260), linear.get(24));
  }
  @Test
  public void testGet(){
    LinearProbing linear = new LinearProbing(10);
    linear.insert(7, 10);
    linear.insert(3, 24);
    assertEquals(new Integer(10), linear.get(7));
    assertEquals(new Integer(24), linear.get(3));
    assertEquals(null, linear.get(555));
  }
  
  @Test
  public void testRemove() {
    LinearProbing linear = new LinearProbing(10);
    linear.insert(1, 10);
    linear.insert(2, 20);
    linear.remove(1);
    assertFalse(linear.isEmpty());
    linear.remove(2);
    assertTrue(linear.isEmpty());
    linear.showAll();
  }
    
  @Test
  public void testCollisions() {
    LinearProbing linear = new LinearProbing(5);
    linear.insert(1, 10);
    linear.insert(6, 20);
    assertEquals(1, linear.getCollision());
    linear.insert(11, 20);
    assertEquals(3, linear.getCollision());
  }
  
  @Test
  public void testSize() {
    LinearProbing linear = new LinearProbing(10);
    linear.insert(16, 10);
    linear.insert(27, 20);
    assertEquals(2, linear.getSize());
  }
}
