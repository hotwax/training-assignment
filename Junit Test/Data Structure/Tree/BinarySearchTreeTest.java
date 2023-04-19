import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class BinarySearchTreeTest {


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
    public void insertTest() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);
        assertEquals(5, tree.root.data);
        assertEquals(3, tree.root.left.data);
        assertEquals(7, tree.root.right.data);
        assertEquals(2, tree.root.left.left.data);
        assertEquals(4, tree.root.left.right.data);
        assertEquals(6, tree.root.right.left.data);
        assertEquals(8, tree.root.right.right.data);
    }


    @Test
    public void deleteTest(){ // delete root
        BinarySearchTree tree = new BinarySearchTree();


        // Deletition when tree is empty
        setUpStreams();
        tree.delete(5);
        assertEquals("Tree is empty, Cannot delete!", outContent.toString().trim());

        // Deletition when tree has only one node
        setUpStreams();
        tree.insert(5); 
        tree.delete(5);
        assertEquals(null, tree.root);


    // Deletition when tree has more than one node



        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);
        tree.insert(1);


        // Deleting the node having single child node

        tree.delete(2);
        setUpStreams();
        tree.inOrder();
        assertEquals("1 3 4 5 6 7 8", outContent.toString().trim());


        // Deleting the node having two child nodes
        setUpStreams();
        tree.delete(5);
        tree.inOrder();
        assertEquals("1 3 4 6 7 8", outContent.toString().trim());


        // Deleting the node having no child nodes
        setUpStreams();
        tree.delete(4);
        tree.inOrder();
        assertEquals("1 3 6 7 8", outContent.toString().trim());


        // Deleting the node which is not present in the tree
        setUpStreams();
        tree.delete(9);
        assertEquals("Key not found in tree!", outContent.toString().trim());
        
    }


    @Test
    public void inorderTest(){
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);
        tree.inOrder();
        assertEquals("2 3 4 5 6 7 8 ", outContent.toString());
    }


    @Test
    public void searchTest(){
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
      
        tree.search(5);
        assertEquals("Found", outContent.toString().trim());
        setUpStreams();
        tree.search(9);
        assertEquals("Not found", outContent.toString().trim());
    }


    @Test
    public void updateTest(){
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(8);
        tree.update(5, 9);

        setUpStreams();
        tree.inOrder();
        assertEquals("2 3 7 8 9", outContent.toString().trim());

    }
    
}
