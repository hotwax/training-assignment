import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class StackTest{

    Stack stacktest =new Stack(10);

   @Test
    public void TestIsEmpty(){
        Assertions.assertTrue(stacktest.isEmpty());
     }
    
     @Test
     public void TestIsEmptyFalse(){
        stacktest.push(10); 
         Assertions.assertFalse(stacktest.isEmpty());
      }
   
    @Test
    public void TestPush() 
    {
    ByteArrayOutputStream out= new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
              stacktest.push(5);
              stacktest.push(4);
              stacktest.push(3);
              stacktest.push(2);
              stacktest.push(1);
              stacktest.display();
             Assertions.assertEquals("1 2 3 4 5".replaceAll(" ",""),out.toString().replaceAll(" ",""));
    }

    @Test
    public void TestPeek() 
    {
    ByteArrayOutputStream out= new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
              stacktest.push(5);
              stacktest.push(4);
              stacktest.push(3);
              stacktest.push(2);
              stacktest.push(1);
              Assertions.assertEquals(1,stacktest.peek());
              stacktest.pop();
              Assertions.assertEquals(2,stacktest.peek());
              stacktest.pop();
              Assertions.assertEquals(3,stacktest.peek());
              stacktest.pop();
              Assertions.assertEquals(4,stacktest.peek());
    }

    @Test
    public void TestPop() 
    {
    stacktest.push(5);
    stacktest.push(4);
    stacktest.push(3);
    stacktest.push(2);
    stacktest.push(1);
    Assertions.assertEquals(1,stacktest.peek());
    stacktest.pop();
    Assertions.assertEquals(2,stacktest.peek());
    stacktest.pop();
    Assertions.assertEquals(3,stacktest.peek());
    stacktest.pop();
    Assertions.assertEquals(4,stacktest.peek());
    
}

@Test
public void TestDisplay() 
{
ByteArrayOutputStream out= new ByteArrayOutputStream();
System.setOut(new PrintStream(out));

          stacktest.push(5);
          stacktest.push(4);
          stacktest.push(3);
          stacktest.push(2);
          stacktest.push(1);
          stacktest.display();
          Assertions.assertEquals("1 2 3 4 5".replaceAll(" ",""),out.toString().replaceAll(" ",""));
    }

}