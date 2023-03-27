/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author nayan
 */
public class BinarySearchTreeTest {

    public BinarySearchTreeTest() {
    }

    @AfterClass
    public static void printSuccessfulMsg() {
        System.out.println("All test cases are passed.");
    }

    @Test
    public void testMain() {
        BinarySearchTree.Node root = null;

        //add a node- non-existing node (valid)
        root = BinarySearchTree.add(root, 10);
        root = BinarySearchTree.add(root, 5);
        root = BinarySearchTree.add(root, 15);
        root = BinarySearchTree.add(root, 2);
        root = BinarySearchTree.add(root, 20);

        System.out.print("After add: ");
        BinarySearchTree.display(root);
        System.out.println();
        //----------------------------------------------------------

        //add a node- existing node
        root = BinarySearchTree.add(root, 10);
        //----------------------------------------------------------

        //check if a node exists- doesn't exists
        assertEquals(false, BinarySearchTree.whetherExists(root, 200));
        //----------------------------------------------------------

        //check if a node exists- exists
        assertEquals(true, BinarySearchTree.whetherExists(root, 10));
        //----------------------------------------------------------

        //remove a node- non-existing node
        root = BinarySearchTree.remove(root, 100);
        //----------------------------------------------------------

        //remove a node with no child- existing node
        root = BinarySearchTree.remove(root, 20);
        System.out.print("After remove: ");
        BinarySearchTree.display(root);
        System.out.println();
        //----------------------------------------------------------

        //remove a node with only left child- existing node
        root = BinarySearchTree.remove(root, 5);
        System.out.print("After remove: ");
        BinarySearchTree.display(root);
        System.out.println();
        //----------------------------------------------------------

        //add a node to create a right child of 15
        root = BinarySearchTree.add(root, 30);

        System.out.print("After add: ");
        BinarySearchTree.display(root);
        System.out.println();
        //----------------------------------------------------------

        //remove a node with only right child- existing node
        root = BinarySearchTree.remove(root, 15);
        System.out.print("After remove: ");
        BinarySearchTree.display(root);
        System.out.println();
        //----------------------------------------------------------

        //remove a node with both children- existing node
        root = BinarySearchTree.remove(root, 10);
        System.out.print("After remove: ");
        BinarySearchTree.display(root);
        System.out.println();
        //----------------------------------------------------------

    }
}
