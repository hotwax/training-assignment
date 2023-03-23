package Stack;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class StackTest {

    Stack<Integer> stacktest = new Stack<Integer>();


    @Test
    void stackPushTest() {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        stacktest.push(50);
        stacktest.push(40);
        stacktest.push(30);
        stacktest.push(20);   
        stacktest.push(60);
        stacktest.display();
        // test top to end elements in stack
        Assertions.assertEquals("60 20 30 40 50".replaceAll(" ", ""),outputStream.toString().replaceAll(" ", ""));
    }


    @Test
    void stackPopTest(){
        stacktest.push(50);
        stacktest.push(40);
        stacktest.push(30);
        stacktest.push(20);   
        stacktest.push(60);
        Assertions.assertEquals(60, stacktest.Top());
        stacktest.pop();
        Assertions.assertEquals(20, stacktest.Top());
        stacktest.pop();
        Assertions.assertEquals(30, stacktest.Top()); 
        stacktest.pop();
        Assertions.assertEquals(40, stacktest.Top());
        stacktest.pop();
        Assertions.assertEquals(50, stacktest.Top());
        stacktest.pop();
        stacktest.pop();
        Assertions.assertEquals(null, stacktest.Top());
    }
    
    @Test
    void stackTopTest(){
        stacktest.push(50);
        stacktest.push(40);
        stacktest.push(30);
        stacktest.push(20);   
        stacktest.push(60);
        stacktest.pop();
        stacktest.pop();
        Assertions.assertEquals(30, stacktest.Top());
        stacktest.push(10);
        stacktest.push(15);
        Assertions.assertEquals(15, stacktest.Top());

    }
   
    @Test
    void stackEmptyTest1(){
        Assertions.assertTrue(stacktest.isempty());
    }

    @Test
    void stackEmptyTest2(){
        stacktest.push(28);
        stacktest.push(19);
        Assertions.assertFalse(stacktest.isempty());
    }

    @Test
    void stackSizeTest(){
        stacktest.push(30);
        stacktest.push(10);
        stacktest.push(50);
        stacktest.push(60);
        Assertions.assertEquals(4, stacktest.Size());
        stacktest.pop();
        stacktest.pop();
        stacktest.push(70);
        stacktest.pop();
        Assertions.assertEquals(2, stacktest.Size());

    }
    
    @Test 
    void stackDisplayTest(){
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        stacktest.push(30);
        stacktest.push(10);
        stacktest.push(50);
        stacktest.push(60);
        stacktest.pop();
        stacktest.push(34);
        stacktest.push(23);
        stacktest.push(56);
        stacktest.push(67);
        stacktest.pop();
        stacktest.display();
        Assertions.assertEquals("56 23 34 50 10 30".replaceAll(" ", ""),outputStream.toString().replaceAll(" ", "") );



    }


    
}
