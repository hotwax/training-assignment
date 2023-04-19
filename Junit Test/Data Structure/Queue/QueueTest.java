import org.junit.Test;
import static org.junit.Assert.*;

public class QueueTest {
    

    @Test
    public void enqueueTest() {
        Queue queue = new Queue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        assertEquals(1, queue.head.data);
        assertEquals(2, queue.head.next.data);
        assertEquals(3, queue.head.next.next.data);
        assertEquals(4, queue.head.next.next.next.data);
        assertEquals(5, queue.head.next.next.next.next.data);
        assertEquals(1,queue.head.data);
    
        assertEquals(5, queue.len);

    }


    @Test
    public void dequeueTest() {
        Queue queue = new Queue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.dequeue();
        assertEquals(2, queue.head.data);
        assertEquals(3, queue.head.next.data);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();

        assertEquals(5, queue.head.data);

        queue.dequeue();
        assertEquals(null, queue.head);
    }



    @Test
    public void peekTest() {
        Queue queue = new Queue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
     
        assertEquals(1, queue.peek());
        queue.dequeue();
        assertEquals(2, queue.peek());
        queue.dequeue();
        assertEquals(3, queue.peek());
        queue.dequeue();
        assertEquals(-1, queue.peek());
    }


    @Test
    public void searchTest(){



        Queue queue = new Queue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
    
        assertEquals(true, queue.search(1));
        assertEquals(true, queue.search(2));
        assertEquals(false, queue.search(11));
    }
}
