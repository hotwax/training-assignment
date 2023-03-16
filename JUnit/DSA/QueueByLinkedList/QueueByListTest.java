package queuebylinkedlist;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
public class QueueByListTest {
@Test
    public void testEnqueueDequeue() {
        QueueByList q = new QueueByList();
        q.enQueue(1);
        q.enQueue(2);
        q.enQueue(3);
        assertEquals(1, q.deQueue());
        assertEquals(2, q.deQueue());
        assertEquals(3, q.deQueue());
    }
    @Test
    public void testIsEmpty() {
        QueueByList q = new QueueByList();
        assertTrue(q.isEmpty());
        q.enQueue(1);
        assertFalse(q.isEmpty());
        q.deQueue();
        assertTrue(q.isEmpty());
    }
     @Test
    public void testFront() {
        QueueByList q = new QueueByList();
        q.enQueue(1);
        q.enQueue(2);
        q.enQueue(3);
        assertEquals(1, q.front());
        q.deQueue();
        assertEquals(2, q.front());
        q.deQueue();
        assertEquals(3, q.front());
    }
    @Test
    public void testUpdate() {
        QueueByList queue = new QueueByList();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.update(2, 4);
        assertTrue(queue.search(4));
    }

    @Test
    public void testSearch() {
        QueueByList queue = new QueueByList();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        assertTrue(queue.search(2));
        assertFalse(queue.search(4));
    }
}
