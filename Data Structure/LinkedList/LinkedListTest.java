import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class LinkedListTest
{
LinkedList list =  new LinkedList();

@Test
public void TestInsert(){
    final ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
    list.insert(10);
    list.insert(20);
    list.insert(30);
    list.insert(40);
    System.setOut(new PrintStream(outputstream));
    list.display();
    Assertions.assertEquals("10 20 30 40".replaceAll(" ",""), outputstream.toString().replaceAll(" ", ""));
}

@Test
public void TestDelete(){
    final ByteArrayOutputStream outputstream=new ByteArrayOutputStream();
    list.insert(10);
    list.insert(20);
    list.insert(30);
    list.insert(40);
    System.setOut(new PrintStream(outputstream));
    list.delete(40);
    list.display();
    Assertions.assertEquals("10 20 30".replaceAll(" ",""),outputstream.toString().replaceAll(" ","")); 
    outputstream.reset();
    System.setOut(new PrintStream(outputstream));
    list.delete(20);
    list.display();    
    Assertions.assertEquals("10 30".replaceAll(" ",""),outputstream.toString().replaceAll(" ","")); 
}

@Test
public void TestSort(){
    final ByteArrayOutputStream outputstream=new ByteArrayOutputStream();
    list.insert(10);
    list.insert(40);
    list.insert(30);
    list.insert(20);
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
public void Testupdate(){
    final ByteArrayOutputStream outputstream=new ByteArrayOutputStream();
    list.insert(10);
    list.insert(20);
    list.insert(30);
    list.insert(40);
    list.insert(50);
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
public void TestDisplay(){
    final ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
    list.insert(10);
    list.insert(20);
    list.insert(30);
    list.insert(40);
    System.setOut(new PrintStream(outputstream));
    list.display();
    Assertions.assertEquals("10 20 30 40".replaceAll(" ",""), outputstream.toString().replaceAll(" ", ""));
}

}





































