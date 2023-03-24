import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;




public class HashmapWithRandomProbingTest {
    
    private  ByteArrayOutputStream outContent;
    private  ByteArrayOutputStream errContent; 
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    
    
    @Before
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    
    }


    @Test
    public void insertTest()
    {
        
            HashmapWithRandomProbing map= new HashmapWithRandomProbing(10);
        
            // normal insertion

            map.insert(1, 10);
            map.insert(2, 20);
            map.insert(3, 30);
            

            // insertion when collision occurs

            map.insert(11, 40);
            assertEquals(1, map.getCollision());

            // updating the val when key is already present while insertion

            map.insert(1, 50);
            assertEquals(50, map.get(1));

            map.insert(4, 60);
            map.insert(5, 70);
            map.insert(6, 600);
            map.insert(7, 700);
            map.insert(8, 800);
            map.insert(9, 900);

            // insertion when table is full
            setUpStreams();
            map.insert(10, 1000);

            assertEquals("Capacity is full", outContent.toString().trim());
    }


    @Test
public void deleteTest()
{
    HashmapWithRandomProbing map= new HashmapWithRandomProbing(10);
    
    setUpStreams();
    map.delete(5);

    assertEquals("Key not found", outContent.toString().trim());

    map.insert(1, 10);
    map.insert(2, 20);
    map.insert(3, 30);

    setUpStreams();
    map.delete(3);
    assertEquals("Key value pair deleted-3 30", outContent.toString().trim());


    map.insert(11, 40);
    setUpStreams();
    map.delete(11);
    assertEquals("Key value pair deleted-11 40", outContent.toString().trim());

}



@Test
public void getTest()
{
    HashmapWithRandomProbing map= new HashmapWithRandomProbing(10);
    
    setUpStreams();
    map.get(5);

    assertEquals(-1, map.get(5));

    map.insert(1, 10);
    map.insert(2, 20);
    map.insert(3, 30);

    assertEquals(10, map.get(1));
    assertEquals(20, map.get(2));
    assertEquals(30, map.get(3));

    map.insert(11, 40);
    assertEquals(40, map.get(11));

}


@Test
public void searchTest()
{
    HashmapWithRandomProbing map= new HashmapWithRandomProbing(10);
    
    setUpStreams();

    assertEquals(false, map.searchKey(5));

    map.insert(1, 10);
    map.insert(2, 20);
    map.insert(3, 30);

    assertEquals(true, map.searchKey(1));
    assertEquals(true, map.searchKey(2));
    assertEquals(true, map.searchKey(3));

    map.insert(11, 40);
    assertEquals(true, map.searchKey(11));




}
}




