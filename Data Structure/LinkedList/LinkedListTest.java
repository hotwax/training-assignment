import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class LinkedListTest
{
LinkedList list =  new LinkedList();

@Test
public void testInsertBeginning(){
    final ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
    list.insertEnd(10);
    list.insertBegin(20);
    list.insertBegin(30);
    list.insertBegin(40);
    System.setOut(new PrintStream(outputstream));
    list.display();
    Assertions.assertEquals("40 30 20 10".replaceAll(" ",""), outputstream.toString().replaceAll(" ", ""));
}

@Test
public void testInsertEnd(){
    final ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
    list.insertEnd(10);
    list.insertEnd(20);
    list.insertEnd(30);
    list.insertEnd(40);
    System.setOut(new PrintStream(outputstream));
    list.display();
    Assertions.assertEquals("10 20 30 40".replaceAll(" ",""), outputstream.toString().replaceAll(" ", ""));
}

@Test
public void testInsertPosition(){
    final ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
    list.insertEnd(10);
    list.insertEnd(20);
    list.insertEnd(30);
    list.insertPosition(2,40);
    System.setOut(new PrintStream(outputstream));
    list.display();
    Assertions.assertEquals("10 40 20 30".replaceAll(" ",""), outputstream.toString().replaceAll(" ", ""));
}

@Test
public void testDeleteAtBegin(){
    final ByteArrayOutputStream outputstream=new ByteArrayOutputStream();
    list.insertEnd(10);
    list.insertEnd(20);
    list.insertEnd(30);
    list.insertEnd(40);
    System.setOut(new PrintStream(outputstream));
    list.deleteBegin();
    list.display();
    Assertions.assertEquals("20 30 40".replaceAll(" ",""),outputstream.toString().replaceAll(" ","")); 
    outputstream.reset();
    System.setOut(new PrintStream(outputstream));
    list.deleteBegin();
    list.display();    
    Assertions.assertEquals("30 40".replaceAll(" ",""),outputstream.toString().replaceAll(" ","")); 
}

@Test
public void testDeleteAtEnd(){
    final ByteArrayOutputStream outputstream=new ByteArrayOutputStream();
    list.insertEnd(10);
    list.insertEnd(20);
    list.insertEnd(30);
    list.insertEnd(40);
    System.setOut(new PrintStream(outputstream));
    list.deleteEnd();
    list.display();
    Assertions.assertEquals("10 20 30".replaceAll(" ",""),outputstream.toString().replaceAll(" ","")); 
    outputstream.reset();
    System.setOut(new PrintStream(outputstream));
    list.deleteEnd();
    list.display();    
    Assertions.assertEquals("10 20".replaceAll(" ",""),outputstream.toString().replaceAll(" ","")); 
}

@Test
public void testDeleteAtPosition(){
    final ByteArrayOutputStream outputstream=new ByteArrayOutputStream();
    list.insertEnd(10);
    list.insertEnd(20);
    list.insertEnd(30);
    list.insertEnd(40);
    System.setOut(new PrintStream(outputstream));
    list.deletePosition(2);
    list.display();
    Assertions.assertEquals("10 30 40".replaceAll(" ",""),outputstream.toString().replaceAll(" ","")); 
}

@Test
public void testSort(){
    final ByteArrayOutputStream outputstream=new ByteArrayOutputStream();
    list.insertEnd(10);
    list.insertEnd(40);
    list.insertEnd(30);
    list.insertEnd(20);
    System.setOut(new PrintStream(outputstream));
    list.display();
    Assertions.assertEquals("10 40 30 20".replaceAll(" ",""),outputstream.toString().replaceAll(" ","")); 
    outputstream.reset();
    System.setOut(new PrintStream(outputstream));
    list.sortList();
    list.display();
    Assertions.assertEquals("10 20 30 40".replaceAll(" ", ""),outputstream.toString().trim().replaceAll(" ", ""));  
}

@Test
public void testUpdate(){
    final ByteArrayOutputStream outputstream=new ByteArrayOutputStream();
    list.insertEnd(10);
    list.insertEnd(20);
    list.insertEnd(30);
    list.insertEnd(40);
    list.insertEnd(50);
    System.setOut(new PrintStream(outputstream));
    list.display(); 
    Assertions.assertEquals("10 20 30 40 50".replaceAll(" ", ""), outputstream.toString().trim().replaceAll(" ", ""));
    outputstream.reset();
    list.update(40, 70);
    System.setOut(new PrintStream(outputstream));
    list.display(); 
    Assertions.assertEquals("10 20 30 70 50".replaceAll(" ", ""), outputstream.toString().trim().replaceAll(" ", ""));
}

@Test
public void testDisplay(){
    final ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
    list.insertEnd(10);
    list.insertEnd(20);
    list.insertEnd(30);
    list.insertEnd(40);
    System.setOut(new PrintStream(outputstream));
    list.display();
    Assertions.assertEquals("10 20 30 40".replaceAll(" ",""), outputstream.toString().replaceAll(" ", ""));
}
}





































