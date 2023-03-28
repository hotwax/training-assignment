import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class QueueTest
{
    Queue Queuetest = new Queue(10);
    
    @Test
    public void TestIsEmptyTrue()
    {
        Assertions.assertTrue(Queuetest.isEmpty());
    }

    @Test
    public void TestIsEmptyFalse()
    {
    Queuetest.enqueue(10);
    Assertions.assertFalse(Queuetest.isEmpty());
    }

    @Test
    public void TestEnqueue()
    {
    ByteArrayOutputStream out= new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
            Queuetest.enqueue(1);
            Queuetest.enqueue(2);
            Queuetest.enqueue(3);
            Queuetest.enqueue(4);
            Queuetest.enqueue(5);
            Queuetest.display();
            Assertions.assertEquals("1 2 3 4 5".replaceAll(" ",""),out.toString().replaceAll(" ",""));
    }

    @Test
    public void dequeueTest() {
        Queuetest.enqueue(1);
        Queuetest.enqueue(2);
        Queuetest.enqueue(3);
        Queuetest.dequeue();
        Assertions.assertEquals(2,Queuetest.Front());
        Queuetest.enqueue(4);
        Queuetest.enqueue(5);
        Queuetest.dequeue();
        Assertions.assertEquals(3,Queuetest.Front());
    }

    @Test
    public void TestFront() 
    {
        Queuetest.enqueue(1);
        Assertions.assertEquals(1,Queuetest.Front());
        Queuetest.enqueue(2);
        Assertions.assertEquals(1,Queuetest.Front());
        Queuetest.enqueue(3);
        Assertions.assertEquals(1,Queuetest.Front());
        Queuetest.enqueue(4);
        Assertions.assertEquals(1,Queuetest.Front());
        Queuetest.enqueue(5);
        Assertions.assertEquals(1,Queuetest.Front());
    }


    @Test
    public void TestSize()
    {
    ByteArrayOutputStream out= new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
            Queuetest.enqueue(1);
            Queuetest.size();
            Assertions.assertEquals(1,1);          
            Queuetest.enqueue(2);
            Queuetest.size();
            Assertions.assertEquals(2,2);
            Queuetest.enqueue(3);
            Queuetest.size();
            Assertions.assertEquals(3,3);
            Queuetest.dequeue();
            Queuetest.size();
            Assertions.assertEquals(2,2);          
            Queuetest.dequeue();
            Queuetest.size();
            Assertions.assertEquals(1,1);           
            Queuetest.dequeue();
            Queuetest.size();
            Assertions.assertEquals(0,0);
    }
    
}

   
   