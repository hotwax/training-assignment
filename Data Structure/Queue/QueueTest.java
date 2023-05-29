import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class QueueTest
{
    Queue queueTest = new Queue(10);
    
    @Test
    public void testIsEmptyTrue()
    {
        Assertions.assertTrue(queueTest.isEmpty());
    }

    @Test
    public void testIsEmptyFalse()
    {
    queueTest.enqueue(10);
    Assertions.assertFalse(queueTest.isEmpty());
    }

    @Test
    public void testEnqueue()
    {
    ByteArrayOutputStream out= new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
            queueTest.enqueue(1);
            queueTest.enqueue(2);
            queueTest.enqueue(3);
            queueTest.enqueue(4);
            queueTest.enqueue(5);
            queueTest.display();
            Assertions.assertEquals("1 2 3 4 5".replaceAll(" ",""),out.toString().replaceAll(" ",""));
    }

    @Test
    public void dequeueTest() {
        queueTest.enqueue(1);
        queueTest.enqueue(2);
        queueTest.enqueue(3);
        queueTest.dequeue();
        Assertions.assertEquals(2,queueTest.front());
        queueTest.enqueue(4);
        queueTest.enqueue(5);
        queueTest.dequeue();
        Assertions.assertEquals(3,queueTest.front());
    }

    @Test
    public void testFront() 
    {
        queueTest.enqueue(1);
        Assertions.assertEquals(1,queueTest.front());
        queueTest.enqueue(2);
        Assertions.assertEquals(1,queueTest.front());
        queueTest.enqueue(3);
        Assertions.assertEquals(1,queueTest.front());
        queueTest.enqueue(4);
        Assertions.assertEquals(1,queueTest.front());
        queueTest.enqueue(5);
        Assertions.assertEquals(1,queueTest.front());
    }


    @Test
    public void testSize()
    {
    ByteArrayOutputStream out= new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
            queueTest.enqueue(1);
            queueTest.size();
            Assertions.assertEquals(1,1);          
            queueTest.enqueue(2);
            queueTest.size();
            Assertions.assertEquals(2,2);
            queueTest.enqueue(3);
            queueTest.size();
            Assertions.assertEquals(3,3);
            queueTest.dequeue();
            queueTest.size();
            Assertions.assertEquals(2,2);          
            queueTest.dequeue();
            queueTest.size();
            Assertions.assertEquals(1,1);           
            queueTest.dequeue();
            queueTest.size();
            Assertions.assertEquals(0,0);
    }
    
}

   
   