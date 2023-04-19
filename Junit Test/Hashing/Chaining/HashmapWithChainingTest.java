import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;



public class HashmapWithChainingTest {


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
public void insertTest(){

    HashmapWithChaining map= new HashmapWithChaining(10);

    // normal insertion
    map.insert(1, 10);
    map.insert(2, 20);
    map.insert(3, 30);


    // insertion when collision occurs
    map.insert(11, 40);

    // updating the val when key is already present while insertion
    map.insert(1, 50);

    map.insert(4, 60);
    map.insert(5, 70);

    setUpStreams();
    map.display();

    assertEquals("0\r\n1:(1,50) (11,40) \r\n2:(2,20) \r\n3:(3,30) \r\n4:(4,60) \r\n5:(5,70) \r\n6\r\n7\r\n8\r\n9",outContent.toString().trim() );

    // rehashing

    map.insert(6, 80);

    setUpStreams();
    map.display();
    assertEquals("0\r\n1:(1,50) \r\n2:(2,20) \r\n3:(3,30) \r\n4:(4,60) \r\n5:(5,70) \r\n6:(6,80) \r\n7\r\n8\r\n9\r\n10\r\n11:(11,40) \r\n12\r\n13\r\n14\r\n15\r\n16\r\n17\r\n18\r\n19",outContent.toString().trim());

    //new capacity
    assertEquals(20, map.capacity);



}



@Test
public void deleteTest(){

    HashmapWithChaining map= new HashmapWithChaining(5);

    // Deletion when hashmap is empty
    setUpStreams();
    assertEquals(-1,map.remove(5) );

    // Deletion when key is not present

    map.insert(1, 10);
    map.insert(2, 20);
    map.insert(3, 30);
    map.insert(11, 40);

    assertEquals(-1,map.remove(4) );


    // Deletion when key is present
    assertEquals(40,map.remove(11) );


    // Final output of hashmap
    setUpStreams();
    map.display();
    assertEquals("0\r\n1:(1,10) \r\n2:(2,20) \r\n3:(3,30) \r\n4\r\n5\r\n6\r\n7\r\n8\r\n9", outContent.toString().trim());
   

}




@Test
public void getValueTest(){

    HashmapWithChaining map= new HashmapWithChaining(5);

    // getValue when hashmap is empty
    setUpStreams();
    assertEquals(-1,map.getValue(5) );


    // getValue when key is not present

    map.insert(1, 10);
    map.insert(2, 20);
    map.insert(3, 30);
    map.insert(11, 40);

    assertEquals(-1,map.getValue(4) );

    // getValue when key is present
    assertEquals(40,map.getValue(11) );


}


@Test
public void searchTest(){

    HashmapWithChaining map= new HashmapWithChaining(5);

    // search when hashmap is empty
    setUpStreams();
    assertEquals(false,map.search(5) );



    // search when key is not present

    map.insert(1, 10);
    map.insert(2, 20);
    map.insert(3, 30);
    map.insert(11, 40);

    setUpStreams();
    assertEquals(false,map.search(4) );

    // search when key is present
    assertEquals(true,map.search(11) );


}


@Test
public void displayTest()
{

    HashmapWithChaining map= new HashmapWithChaining(5);

    map.insert(1, 10);
    map.insert(2, 20);
    map.insert(3, 30);
    map.insert(11, 40);

    setUpStreams();
    map.display();
    assertEquals("0\r\n1:(1,10) (11,40) \r\n2:(2,20) \r\n3:(3,30) \r\n4\r\n5\r\n6\r\n7\r\n8\r\n9", outContent.toString().trim());
}

    
}



    

    
    



