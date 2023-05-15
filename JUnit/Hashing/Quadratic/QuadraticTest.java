package quardatic;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
public class QuadraticTest {
  @Test
  public void testPut() {
    Quadratic quadratic = new Quadratic(10);
    quadratic.insert(15, 100);
    quadratic.insert(24, 260);
    assertEquals(new Integer(100), quadratic.get(15));
    assertEquals(new Integer(260), quadratic.get(24));
  }
  @Test
  public void testGet(){
    Quadratic quadratic = new Quadratic(10);
    quadratic.insert(7, 10);
    quadratic.insert(3, 24);
    assertEquals(new Integer(10), quadratic.get(7));
    assertEquals(new Integer(24), quadratic.get(3));
    assertEquals(null, quadratic.get(555));
  }
  
  @Test
  public void testRemove() {
    Quadratic quadratic = new Quadratic(10);
    quadratic.insert(1, 10);
    quadratic.insert(2, 20);
    quadratic.remove(1);
    assertFalse(quadratic.isEmpty());
    quadratic.remove(2);
    assertTrue(quadratic.isEmpty());
    quadratic.showAll();
  }
    
  @Test
  public void testCollisions() {
    Quadratic quadratic = new Quadratic(5);
    quadratic.insert(1, 10);
    quadratic.insert(6, 20);
    assertEquals(1, quadratic.getCollision());
    quadratic.insert(11, 20);
    assertEquals(3, quadratic.getCollision());
  }
  
  @Test
  public void testSize() {
    Quadratic quadratic = new Quadratic(10);
    quadratic.insert(16, 10);
    quadratic.insert(27, 20);
    assertEquals(2, quadratic.getSize());
  }
}
