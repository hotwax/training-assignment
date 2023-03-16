package stackbyarray1;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
public class StackByArrayTest {

  @Test
  public void testPushAndPeek() {
    StackByArray stack = new StackByArray();
    stack.push(5);
    stack.push(10);
    stack.push(15);
    assertEquals(15, stack.peek());
  }
  @Test
  public void testPop() {
    StackByArray stack = new StackByArray();
    stack.push(5);
    stack.push(10);
    stack.push(15);
    assertEquals(15, stack.pop());
    assertEquals(10, stack.pop());
  }
  @Test
  public void testIsEmpty() {
    StackByArray stack = new StackByArray();
    assertTrue(stack.isEmpty());
    stack.push(5);
    assertFalse(stack.isEmpty());
    stack.pop();
    assertTrue(stack.isEmpty());
  }
   @Test
  public void testSearch() {
    StackByArray stack = new StackByArray();
    stack.push(5);
    stack.push(10);
    stack.push(15);
    assertTrue(stack.search(10));
    assertFalse(stack.search(20));
  }
  @Test
  public void testPopOnEmptyStack() {
    StackByArray stack = new StackByArray();
    assertEquals(-1, stack.pop());
  }
   @Test
  public void testPeekOnEmptyStack() {
    StackByArray stack = new StackByArray();
    assertEquals(-1, stack.peek());
  }
  @Test
  public void testPushOnFullStack() {
    StackByArray stack = new StackByArray();
    for (int i = 0; i < stack.CAPACITY; i++) {
      stack.push(i);
    }
    stack.push(100); // push on full stack
    assertEquals(stack.CAPACITY - 1, stack.peek());
  }
}
