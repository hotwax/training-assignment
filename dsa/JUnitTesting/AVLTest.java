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
public class AVLTest {

    public AVLTest() {
    }

    @AfterClass
    public static void printSuccessfulMsg() {
        System.out.println("All test cases are passed.");
    }

    @Test
    public void testMain() {
        AVL.Node root = null;

        //add a node- non-existing node (valid) 
        root = AVL.add(root, 50);
        root = AVL.add(root, 30);
        root = AVL.add(root, 70);
        root = AVL.add(root, 20);
  
        //balanced tree
        System.out.print("After add: ");
        AVL.display(root);
        System.out.println();      
        
        //unbalanced LL tree(left-left)
        root = AVL.add(root, 10);

        System.out.print("After add: ");
        AVL.display(root);
        System.out.println();
        //----------------------------------------------------------
        
        root = AVL.add(root, 5);
        
        //unbalanced LR tree(left-right)
        root = AVL.add(root, 8);

        System.out.print("After add: ");
        AVL.display(root);
        System.out.println();
        //----------------------------------------------------------
        
        root = AVL.add(root, 15);
        
        //unbalanced RL tree(right-left)
        root = AVL.add(root, 12);

        System.out.print("After add: ");
        AVL.display(root);
        System.out.println();
        //----------------------------------------------------------
        
        root = AVL.add(root, 17);
        
        //unbalanced RR tree(right-right)
        root = AVL.add(root, 19);

        System.out.print("After add: ");
        AVL.display(root);
        System.out.println();
        //----------------------------------------------------------


        //check if a node exists- doesn't exists
        assertEquals(false, AVL.whetherExists(root, 200));
        //----------------------------------------------------------

        //check if a node exists- exists
        assertEquals(true, AVL.whetherExists(root, 10));
        //----------------------------------------------------------

        //remove a node- non-existing node
        root = AVL.remove(root, 100);
        //----------------------------------------------------------

        //remove a node- existing node
        root = AVL.remove(root, 12);
        System.out.print("After remove: ");
        AVL.display(root);
        System.out.println();
        //----------------------------------------------------------

        

    }

}
