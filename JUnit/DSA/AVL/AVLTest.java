package avl;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
public class AVLTest {
AVL tree;
@Before
public void setUp() {
 tree = new AVL();
}

@After
public void tearDown() {
tree=null;
}

@Test
public void testInsertSingleValue() {
    AVL avl = new AVL();
    avl.root = avl.insert(avl.root, 10);
    assertEquals(10, avl.root.data);
    assertEquals(null, avl.root.left);
    assertEquals(null, avl.root.right);
    assertEquals(1, avl.root.height);
}

@Test
public void testInsertMultipleValues() {
    tree.root = tree.insert(tree.root, 10);
    tree.root = tree.insert(tree.root, 20);
    tree.root = tree.insert(tree.root, 30);
    tree.root = tree.insert(tree.root, 40);
    assertEquals(20, tree.root.data);
    assertEquals(10, tree.root.left.data);
    assertEquals(30, tree.root.right.data);
    assertEquals(40, tree.root.right.right.data);
    assertEquals(3, tree.root.height);
}

@Test
public void testDelete() {
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 40);
        tree.root = tree.insert(tree.root, 50);
        tree.root = tree.insert(tree.root, 60);
        
        // Deleting a node with no child
        tree.root = tree.delete(tree.root, 60);
        assertNull(tree.root.right.right);
        
        // Deleting a node with one child
        tree.root = tree.delete(tree.root, 50);
        assertEquals(40, tree.root.right.data);
        
        // Deleting a node with two children
        tree.root = tree.delete(tree.root, 20);
        assertEquals(30, tree.root.data);
        assertEquals(10, tree.root.left.data);
        assertEquals(40, tree.root.right.data);
}

@Test
public void testShowAll() {
tree.root = tree.insert(tree.root, 10);
tree.root = tree.insert(tree.root, 20);
tree.root = tree.insert(tree.root, 30);
tree.root = tree.insert(tree.root, 40);
ByteArrayOutputStream outContent = new ByteArrayOutputStream();
System.setOut(new PrintStream(outContent));
tree.showAll(tree.root);
String expectedOutput = "20 10 30 40";
assertEquals(expectedOutput, outContent.toString().trim());
}

@Test
public void testSort() {
tree.root = tree.insert(tree.root, 10);
tree.root = tree.insert(tree.root, 20);
tree.root = tree.insert(tree.root, 30);
tree.root = tree.insert(tree.root, 40);
ByteArrayOutputStream outContent = new ByteArrayOutputStream();
System.setOut(new PrintStream(outContent));
tree.sort(tree.root);
String expectedOutput = "10 20 30 40";
assertEquals(expectedOutput, outContent.toString().trim());
}

@Test
public void testUpdate() {
tree.root = tree.insert(tree.root, 10);
tree.root = tree.insert(tree.root, 20);
tree.root = tree.insert(tree.root, 30);
tree.update(tree.root, 20 , 222);
  assertEquals(30, tree.root.data);
    assertEquals(10, tree.root.left.data);
    assertEquals(222, tree.root.right.data);
    assertEquals(2, tree.root.height);
}

@Test
public void testSearchNode() {
tree.root = tree.insert(tree.root, 102);
tree.root = tree.insert(tree.root, 34);
tree.root = tree.insert(tree.root, 40);
assertEquals(true, tree.searchNode(tree.root,102));
assertEquals(false, tree.searchNode(tree.root,12));
}

}
