package queuebyarray;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
public class QueueByArrayTest {

    @Test
    public void testEnqueueDequeue() {
        QueueByArray q = new QueueByArray();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        assertEquals(1, q.dequeue());
        assertEquals(2, q.dequeue());
        assertEquals(3, q.dequeue());
    }
    @Test
    public void testIsEmpty() {
        QueueByArray q = new QueueByArray();
        assertTrue(q.isEmpty());
        q.enqueue(1);
        assertFalse(q.isEmpty());
        q.dequeue();
        assertTrue(q.isEmpty());
    }
    @Test
    public void testIsFull() {
        QueueByArray q = new QueueByArray();
        for (int i = 0; i < 100; i++) {
            q.enqueue(i);
        }
        assertTrue(q.isFull());
    }
     @Test
    public void testFront() {
        QueueByArray q = new QueueByArray();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        assertEquals(1, q.front());
        q.dequeue();
        assertEquals(2, q.front());
        q.dequeue();
        assertEquals(3, q.front());
    }
    @Test
    public void testUpdate() {
        QueueByArray queue = new QueueByArray();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.update(2, 4);
        assertTrue(queue.search(4));
    }

    @Test
    public void testSearch() {
        QueueByArray queue = new QueueByArray();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertTrue(queue.search(2));
        assertFalse(queue.search(4));
    }
}
