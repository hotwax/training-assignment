import org.junit.Test;

import static org.junit.Assert.*;


public class StackTest {


    @Test
    public void pushTest(){

        Stack stack = new Stack();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);


        assertEquals(5,stack.peek() );
        stack.pop();
        assertEquals(4, stack.peek());
        stack.pop();
        assertEquals(3, stack.peek());
        stack.pop();
        assertEquals(2, stack.peek());
        stack.pop();
        assertEquals(1, stack.peek());
        
    }


    @Test
    public void pop () {
        Stack stack = new Stack();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        stack.pop();
        assertEquals(4, stack.peek());
        stack.pop();
        assertEquals(3, stack.peek());
        stack.pop();
        assertEquals(2, stack.peek());
        stack.pop();
        assertEquals(1, stack.peek());
        stack.pop();
        assertEquals(-1, stack.peek());
    }


    @Test
    public void search () {
        Stack stack = new Stack();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        assertEquals(true, stack.search(1));
        assertEquals(true, stack.search(2));
        assertEquals(true, stack.search(3));    
        assertEquals(false, stack.search(6));
    }



    @Test
    public void peek() {
        Stack stack = new Stack();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        assertEquals(5, stack.peek());
        stack.pop();
        assertEquals(4, stack.peek());
        stack.pop();
        assertEquals(3, stack.peek());
        stack.pop();
        assertEquals(2, stack.peek());
        stack.pop();
        assertEquals(1, stack.peek());
        stack.pop();
        assertEquals(-1, stack.peek());
    }



    
}
