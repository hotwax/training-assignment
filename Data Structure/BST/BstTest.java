import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class BstTest {
    BST bstTest = new BST();
    
    @Test
    public void testInsert()
    {    
    ByteArrayOutputStream output= new ByteArrayOutputStream();
    System.setOut(new PrintStream(output));
        bstTest.insert(12);
        bstTest.insert(32);
        bstTest.insert(45);
        bstTest.insert(76);
        bstTest.insert(34);
        bstTest.inorder();
        System.setOut(new PrintStream(output));
        Assertions.assertEquals("12 32 34 45 76".replaceAll(" ",""),output.toString().replaceAll(" ",""));
        output.reset();
        bstTest.preorder();
        System.setOut(new PrintStream(output));
        Assertions.assertEquals("12 32 45 34 76".replaceAll(" ",""),output.toString().replaceAll(" ",""));
        output.reset();
        bstTest.postorder();
        System.setOut(new PrintStream(output));
        Assertions.assertEquals("34 76 45 32 12".replaceAll(" ",""),output.toString().replaceAll(" ",""));
    }

    @Test
    public void testInorder()
    {    
    ByteArrayOutputStream out= new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
        bstTest.insert(12);
        bstTest.insert(32);
        bstTest.insert(45);
        bstTest.insert(76);
        bstTest.inorder();
        System.setOut(new PrintStream(out));   
        Assertions.assertEquals("12 32 45 76".replaceAll(" ",""),out.toString().replaceAll(" ",""));
    }

    @Test
    public void testPreorder()
    {    
    ByteArrayOutputStream out= new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
        bstTest.insert(12);
        bstTest.insert(32);
        bstTest.insert(45);
        bstTest.insert(76);
        bstTest.preorder();  
        System.setOut(new PrintStream(out));
        Assertions.assertEquals("12 32 45 76".replaceAll(" ",""),out.toString().replaceAll(" ",""));
    }

    @Test
    public void testPostorder()
    {    
    ByteArrayOutputStream out= new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
        bstTest.insert(12);
        bstTest.insert(32);
        bstTest.insert(45);
        bstTest.insert(76);
        bstTest.postorder();    
        System.setOut(new PrintStream(out));
        Assertions.assertEquals("76 45 32 12".replaceAll(" ",""),out.toString().replaceAll(" ",""));
    }


    @Test
    public void testDeletion()
    {    
    ByteArrayOutputStream out= new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
        bstTest.insert(12);
        bstTest.insert(32);
        bstTest.insert(45);
        bstTest.insert(76);
        bstTest.delete(45);
        bstTest.inorder();
        System.setOut(new PrintStream(out));
        Assertions.assertEquals("12 32 76".replaceAll(" ",""),out.toString().replaceAll(" ",""));
        out.reset();
        bstTest.delete(12);
        bstTest.inorder();
        System.setOut(new PrintStream(out));
        Assertions.assertEquals("32 76".replaceAll(" ",""),out.toString().replaceAll(" ",""));
    }

    @Test
    public void testSearch() 
{
        ByteArrayOutputStream out= new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));    
        bstTest.insert(12);
        bstTest.insert(32);
        bstTest.insert(45);
        bstTest.insert(76);
        Assertions.assertTrue(bstTest.search(32));
        Assertions.assertTrue(bstTest.search(76));
        Assertions.assertFalse(bstTest.search(90));
    }
}
