package AVL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AvlTest {
    AVL avl = new AVL();
    final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    // passed
    @Test
    void checkAvlIsEmpty() {
        Assertions.assertTrue(avl.isEmpty());
    }

    @Test
    void preorderTraversalTest1() {
        System.setOut(new PrintStream(outputStream));
        avl.preorder();
        Assertions.assertEquals("-1".replaceAll(" ", ""), outputStream.toString().trim().replaceAll(" ", ""));
    }

    // passed
    @Test
    void insertionTest() {
        System.setOut(new PrintStream(outputStream));
        avl.insertion(30);
        avl.insertion(20);
        avl.insertion(10);
        avl.insertion(5);
        avl.insertion(40);
        avl.insertion(25);
        avl.preorder();
        Assertions.assertEquals("20 10 5 30 25 40 ".replaceAll(" ", ""),
                outputStream.toString().trim().replaceAll(" ", ""));
    }

    @Test
    void inorderTraversalTest() {
        System.setOut(new PrintStream(outputStream));
        avl.insertion(30);
        avl.insertion(20);
        avl.insertion(10);
        avl.insertion(5);
        avl.insertion(40);
        avl.insertion(25);
        avl.inorder();
        Assertions.assertEquals("5 10 20 25 30 40 ".replaceAll(" ", ""),
                outputStream.toString().trim().replaceAll(" ", ""));
    }

    // passed
    @Test
    void preorderTraversalTest2() {
        System.setOut(new PrintStream(outputStream));
        avl.insertion(30);
        avl.insertion(20);
        avl.insertion(10);
        avl.insertion(5);
        avl.insertion(40);
        avl.insertion(25);
        avl.preorder();
        Assertions.assertEquals("20 10 5 30 25 40".replaceAll(" ", ""),
                outputStream.toString().trim().replaceAll(" ", ""));
    }

    // passed
    @Test
    void postTraversalTest() {
        System.setOut(new PrintStream(outputStream));
        avl.insertion(30);
        avl.insertion(20);
        avl.insertion(10);
        avl.insertion(5);
        avl.insertion(40);
        avl.insertion(25);
        avl.postorder();
        Assertions.assertEquals("5 10 25 40 30 20".replaceAll(" ", ""),
                outputStream.toString().trim().replaceAll(" ", ""));
    }

    // passed
    @Test
    void morrisTraversalTest() {
        System.setOut(new PrintStream(outputStream));
        avl.insertion(30);
        avl.insertion(20);
        avl.insertion(10);
        avl.insertion(5);
        avl.insertion(40);
        avl.insertion(25);
        avl.insertion(60);
        avl.insertion(55);
        avl.morrisTraversal();
        Assertions.assertEquals("5 10 20 25 30 40 55 60".replaceAll(" ", ""),
                outputStream.toString().trim().replaceAll(" ", ""));
    }

    // passed
    @Test
    void deletionTest() {
        System.setOut(new PrintStream(outputStream));
        avl.insertion(10);
        avl.insertion(20);
        avl.insertion(30);
        avl.insertion(40);
        avl.insertion(50);
        avl.insertion(70);
        avl.deletion(20);
        avl.deletion(40);
        avl.preorder();
        Assertions.assertEquals(" 50 30 10 70".replaceAll(" ", ""), outputStream.toString().trim().replaceAll(" ", ""));
    }

    // passed
    @Test
    void SearchInAvlTest1() {
        avl.insertion(20);
        avl.insertion(60);
        avl.insertion(30);
        avl.insertion(40);
        avl.insertion(10);
        Assertions.assertTrue(avl.searchKey(40));
    }

    // passed
    @Test
    void SearchInAvlTest2() {
        avl.insertion(20);
        avl.insertion(60);
        avl.insertion(30);
        avl.insertion(40);
        avl.insertion(10);
        Assertions.assertFalse(avl.searchKey(4));
    }
}
