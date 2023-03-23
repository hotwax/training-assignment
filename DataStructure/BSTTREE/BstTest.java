package BSTTREE;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
public class BstTest {
     BST bst=new BST();
    
     //passed
     @Test
    void checkBstIsEmpty(){
     Assertions.assertTrue(bst.isEmpty());
    }

    //passed
     @Test
     void InsertionTest(){
      final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream(); 
      System.setOut(new PrintStream(outputStreamCaptor));
      bst.insertion(10);
      bst.insertion(20);
      bst.insertion(30);
      bst.insertion(40);
      bst.insertion(5);
      bst.insertion(2);
      bst.inorder();
      Assertions.assertEquals("2  5  10  20  30    40".replaceAll(" ", ""),outputStreamCaptor.toString().trim().replaceAll(" ",""));

    }

    //passed
    @Test
    void PreorderTraversalTest(){
    final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream(); 
    System.setOut(new PrintStream(outputStreamCaptor));
     bst.insertion(10);
     bst.insertion(20);
     bst.insertion(30);
     bst.insertion(40);
     bst.insertion(18);
     bst.insertion(5);
     bst.insertion(2);
     bst.deletion(10);
     bst.preorder();
     Assertions.assertEquals("18 5 2 20 30 40".replaceAll(" ", ""),outputStreamCaptor.toString().trim().replaceAll(" ",""));

   }
   
   //passed
   @Test
   void PostTraversalTest(){
    final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream(); 
    System.setOut(new PrintStream(outputStreamCaptor));
     bst.insertion(10);
     bst.insertion(20);
     bst.insertion(30);
     bst.insertion(6);
     bst.insertion(40);
     bst.insertion(5);
     bst.insertion(2);
     bst.insertion(4);
     bst.postorder();
     Assertions.assertEquals("4 2 5 6 40 30 20 10".replaceAll(" ", ""),outputStreamCaptor.toString().trim().replaceAll(" ",""));

   }
   
   //passed
   @Test
   void MorrisTraversalTest(){
    final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream(); 
    System.setOut(new PrintStream(outputStreamCaptor));
     bst.insertion(10);
     bst.insertion(20);
     bst.insertion(30);
     bst.insertion(6);
     bst.insertion(40);
     bst.insertion(5);
     bst.insertion(35);
     bst.insertion(2);
     bst.insertion(4);
     bst.morristraversal();
     Assertions.assertEquals("2 4 5 6 10 20 30 35 40 ".replaceAll(" ", ""),outputStreamCaptor.toString().trim().replaceAll(" ",""));

   }

   //passed
   @Test
   void deletionTest(){
    final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream(); 
    System.setOut(new PrintStream(outputStreamCaptor));
     bst.insertion(10);
     bst.insertion(20);
     bst.insertion(30);
     bst.insertion(6);
     bst.insertion(40);
     bst.deletion(10);
     bst.insertion(5);
     bst.insertion(35);
     bst.deletion(6);
     bst.insertion(2);
     bst.insertion(4);
     bst.morristraversal();
     Assertions.assertEquals("2 4 5 20 30 35 40 ".replaceAll(" ", ""),outputStreamCaptor.toString().trim().replaceAll(" ",""));

   }
//passed   
@Test
void SearchInBstTest1(){

    bst.insertion(10);
     bst.insertion(20);
     bst.insertion(30);
     bst.insertion(6);
     bst.insertion(40);
  Assertions.assertTrue(bst.search(40));

}   

//passed
@Test
void SearchInBstTest2(){

    bst.insertion(10);
     bst.insertion(20);
     bst.insertion(30);
     bst.insertion(6);
     bst.insertion(40);
  Assertions.assertFalse(bst.search(4));

}   



}
