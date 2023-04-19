import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;




public class HashmapWithLinearProbingTest {

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

    HashmapWithLinearProbing map= new HashmapWithLinearProbing(10);

    // normal insertion
    map.insert(1, 10);
    map.insert(2, 20);
    map.insert(3, 30);

    // insertion when collision occurs
    map.insert(11, 40);
    assertEquals(3, map.getCollision());


    // updating the val when key is already present while insertion

    map.insert(1, 50);
    assertEquals(50, map.getValue(1));

    map.insert(4, 60);
    map.insert(5, 70);
    map.insert(6, 600);
    map.insert(7, 700);


    setUpStreams();
    map.display();
    assertEquals("0:\r\n1:(1,50)\r\n2:(2,20)\r\n3:(3,30)\r\n4:(11,40)\r\n5:(4,60)\r\n6:(5,70)\r\n7:(6,600)\r\n8:(7,700)\r\n9:", outContent.toString().trim());


    // inserting when size is full

    setUpStreams();
    map.insert(8, 80);
    map.insert(9, 90);
    map.insert(10, 100);
    assertEquals("Capacity is full", outContent.toString().trim());


}

@Test
public void deleteTest(){

    HashmapWithLinearProbing map= new HashmapWithLinearProbing(10);


    // deleting when key is not present
    assertEquals(-1, map.remove(5));

    map.insert(1, 10);
    map.insert(2, 20);
    map.insert(3, 30);

    // deleting when key is present
    assertEquals(20, map.remove(2));

    // deleting when key is not present
    assertEquals(-1, map.remove(6));

    //final display
    setUpStreams();
    map.display();
    assertEquals("0:\r\n1:(1,10)\r\n2:(-1,-1)\r\n3:(3,30)\r\n4:\r\n5:\r\n6:\r\n7:\r\n8:\r\n9:", outContent.toString().trim());

}


@Test
public void searchTest(){

    HashmapWithLinearProbing map= new HashmapWithLinearProbing(10);

    // searching when key is not present

    map.insert(1, 10);
    map.insert(2, 20);
    map.insert(3, 30);

    // searching when key is present
    assertEquals(true, map.search(2));

    // searching when key is not present
    assertEquals(false, map.search(6));
}
    



@Test
public void getValueTest(){

    HashmapWithLinearProbing map= new HashmapWithLinearProbing(10);


    map.insert(1, 10);
    map.insert(2, 20);
    map.insert(3, 30);

    // searching when key is present
    assertEquals(20, map.getValue(2));

    // searching when key is not present
    assertEquals(-1, map.getValue(6));
}




@Test
public void displayTest(){

    HashmapWithLinearProbing map= new HashmapWithLinearProbing(10);

    map.insert(1, 10);
    map.insert(2, 20);
    map.insert(3, 30);
    map.insert(11, 40);

    setUpStreams();
    map.display();
    assertEquals("0:\r\n1:(1,10)\r\n2:(2,20)\r\n3:(3,30)\r\n4:(11,40)\r\n5:\r\n6:\r\n7:\r\n8:\r\n9:", outContent.toString().trim());
}

}




