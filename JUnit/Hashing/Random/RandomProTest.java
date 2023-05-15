package randompro;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
public class RandomProTest {
 @Test
  public void testPut() {
    RandomPro random = new RandomPro(10);
    random.insert(1, 10);
    random.insert(2, 20);
    assertEquals(new Integer(10), random.get(1));
    assertEquals(new Integer(20), random.get(2));
  }
  @Test
  public void testGet(){
    RandomPro random = new RandomPro(10);
    random.insert(77, 106);
    random.insert(23, 21);
    assertEquals(new Integer(106), random.get(77));
    assertEquals(new Integer(21), random.get(23));
    assertEquals(null, random.get(555));
  }
  
  @Test
  public void testRemove() {
    RandomPro random = new RandomPro(10);
    random.insert(1, 10);
    random.insert(2, 20);
    random.remove(1);
    assertFalse(random.isEmpty());
    random.remove(2);
    assertTrue(random.isEmpty());
    random.showAll();
  }
  @Test
  public void testCollisions() {
    RandomPro random = new RandomPro(5);
    random.insert(1, 10);
    random.insert(6, 20);
    assertEquals(1, random.getCollisions());
    random.insert(11, 20);
    assertEquals(2, random.getCollisions());
  }
  @Test
  public void testSize() {
    RandomPro random = new RandomPro(10);
    random.insert(1, 10);
    random.insert(2, 20);
    assertEquals(2, random.getSize());
  }

}
