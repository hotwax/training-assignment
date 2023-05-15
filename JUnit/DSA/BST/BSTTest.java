package bst;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import static org.junit.Assert.*;

public class BSTTest {
  @Test
  public void testInsert() {
    BST bst = new BST();
    bst.insert(5);
    bst.insert(3);
    bst.insert(7);
    bst.insert(1);
    bst.insert(9);
    assertEquals(true, bst.searchBST(5));
    assertEquals(true, bst.searchBST(3));
    assertEquals(true, bst.searchBST(7));
    assertEquals(true, bst.searchBST(1));
    assertEquals(true, bst.searchBST(9));
    assertEquals(false, bst.searchBST(2));
  }
  @Test
  public void testDelete() {
    BST tree = new BST();
    tree.insert(5);
    tree.insert(3);
    tree.insert(7);
    tree.insert(2);
    tree.insert(4);
    tree.insert(6);
    tree.insert(8);
    tree.delete(5);
    assertEquals(false, tree.searchBST(5));
    tree.delete(2);
    assertEquals(false, tree.searchBST(2));
  }

  @Test
  public void testSearch() {
    BST tree = new BST();
    tree.insert(5);
    tree.insert(3);
    tree.insert(7);
    tree.insert(2);
    tree.insert(4);
    tree.insert(6);
    tree.insert(8);
    assertTrue(tree.searchBST(5));
    assertFalse(tree.searchBST(10));
  }

  @Test
  public void showAllTest() {
    BST tree = new BST();
    tree.insert(5);
    tree.insert(3);
    tree.insert(7);
    tree.insert(2);
    tree.insert(4);
    tree.insert(6);
    tree.insert(8);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    tree.showAll();
    String expectedOutput = "2 3 4 5 6 7 8"; // add a space separator
    assertEquals(expectedOutput, outContent.toString().trim());

  }
}