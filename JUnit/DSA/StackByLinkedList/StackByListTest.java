package stackbylist;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
public class StackByListTest {
@Test
  public void testPushAndPeek() {
    StackByList stack = new StackByList();
    stack.push(5);
    stack.push(10);
    stack.push(15);
    assertEquals(15, stack.peek());
  }
  @Test
  public void testPop() {
    StackByList stack = new StackByList();
    stack.push(5);
    stack.push(10);
    stack.push(15);
    assertEquals(15, stack.pop());
    assertEquals(10, stack.pop());
  }
  @Test
  public void testIsEmpty() {
    StackByList stack = new StackByList();
    assertTrue(stack.isEmpty());
    stack.push(5);
    assertFalse(stack.isEmpty());
    stack.pop();
    assertTrue(stack.isEmpty());
  }
   @Test
  public void testSearch() {
    StackByList stack = new StackByList();
    stack.push(5);
    stack.push(10);
    stack.push(15);
    assertTrue(stack.search(10));
    assertFalse(stack.search(20));
  }
  @Test
  public void testPopOnEmptyStack() {
    StackByList stack = new StackByList();
    assertEquals(-1, stack.pop());
  }
   @Test
  public void testPeekOnEmptyStack() {
    StackByList stack = new StackByList();
    assertEquals(-1, stack.peek());
  }
 
}
