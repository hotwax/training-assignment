import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class BSTtest {
    BST BSTtest = new BST();
    
    @Test
    public void Testinsert()
    {    
    ByteArrayOutputStream out= new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
        BSTtest.insert(12);
        BSTtest.insert(32);
        BSTtest.insert(45);
        BSTtest.insert(76);
        BSTtest.insert(34);
        BSTtest.inorder();
        System.setOut(new PrintStream(out));
        Assertions.assertEquals("12 32 34 45 76".replaceAll(" ",""),out.toString().replaceAll(" ",""));
        out.reset();
        BSTtest.preorder();
        System.setOut(new PrintStream(out));
        Assertions.assertEquals("12 32 45 34 76".replaceAll(" ",""),out.toString().replaceAll(" ",""));
        out.reset();
        BSTtest.postorder();
        System.setOut(new PrintStream(out));
        Assertions.assertEquals("34 76 45 32 12".replaceAll(" ",""),out.toString().replaceAll(" ",""));
    }

    @Test
    public void TestInorder()
    {    
    ByteArrayOutputStream out= new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
        BSTtest.insert(12);
        BSTtest.insert(32);
        BSTtest.insert(45);
        BSTtest.insert(76);
        BSTtest.inorder();
        System.setOut(new PrintStream(out));   
        Assertions.assertEquals("12 32 45 76".replaceAll(" ",""),out.toString().replaceAll(" ",""));
    }

    @Test
    public void TestPreorder()
    {    
    ByteArrayOutputStream out= new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
        BSTtest.insert(12);
        BSTtest.insert(32);
        BSTtest.insert(45);
        BSTtest.insert(76);
        BSTtest.preorder();  
        System.setOut(new PrintStream(out));
        Assertions.assertEquals("12 32 45 76".replaceAll(" ",""),out.toString().replaceAll(" ",""));
    }

    @Test
    public void TestPostorder()
    {    
    ByteArrayOutputStream out= new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
        BSTtest.insert(12);
        BSTtest.insert(32);
        BSTtest.insert(45);
        BSTtest.insert(76);
        BSTtest.postorder();    
        System.setOut(new PrintStream(out));
        Assertions.assertEquals("76 45 32 12".replaceAll(" ",""),out.toString().replaceAll(" ",""));
    }


    @Test
    public void TestDeletion()
    {    
    ByteArrayOutputStream out= new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
        BSTtest.insert(12);
        BSTtest.insert(32);
        BSTtest.insert(45);
        BSTtest.insert(76);
        BSTtest.delete(45);
        BSTtest.inorder();
        System.setOut(new PrintStream(out));
        Assertions.assertEquals("12 32 76".replaceAll(" ",""),out.toString().replaceAll(" ",""));
        out.reset();
        BSTtest.delete(12);
        BSTtest.inorder();
        System.setOut(new PrintStream(out));
        Assertions.assertEquals("32 76".replaceAll(" ",""),out.toString().replaceAll(" ",""));
    }

    @Test
    public void testSearch() 
{
        ByteArrayOutputStream out= new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));    
        BSTtest.insert(12);
        BSTtest.insert(32);
        BSTtest.insert(45);
        BSTtest.insert(76);
        Assertions.assertTrue(BSTtest.search(32));
        Assertions.assertTrue(BSTtest.search(76));
        Assertions.assertFalse(BSTtest.search(90));
    }
}
