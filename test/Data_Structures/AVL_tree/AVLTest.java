/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_Structures.AVL_tree;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Dhruv
 */
public class AVLTest {
    private  ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        outContent=new ByteArrayOutputStream();
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
    public void testing() {
        AVL bst=new AVL();
        
        bst.add(10);
        bst.add(60);
        bst.add(20);
        bst.add(70);
        bst.add(30);
        bst.add(80);
        bst.add(40);
        bst.add(90);
        bst.add(50);
        bst.add(100);
                
        setUpStreams();
        bst.inorder();
        assertEquals("10 20 30 40 50 60 70 80 90 100", outContent.toString().trim());
        setUpStreams();
        bst.preorder();
        assertEquals("60 20 10 40 30 50 80 70 90 100", outContent.toString().trim());
        setUpStreams();
        bst.postorder();
        assertEquals("10 30 50 40 20 70 100 90 80 60", outContent.toString().trim());
        
        bst.delete(20);
        bst.delete(30);
        bst.delete(40);
        
        setUpStreams();
        bst.inorder();
        assertEquals("10 50 60 70 80 90 100", outContent.toString().trim());
        setUpStreams();
        bst.preorder();
        assertEquals("60 50 10 80 70 90 100", outContent.toString().trim());
        setUpStreams();
        bst.postorder();
        assertEquals("10 50 70 100 90 80 60", outContent.toString().trim());
        
    }
    
}
