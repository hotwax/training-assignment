/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_Structures.linked_list;

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
public class My_LinkedListTest {
    
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
        My_LinkedList ll=new My_LinkedList();
        ll.add(10);
        ll.add(20);
        ll.add(80);
        ll.add(70);
        ll.add(60);
        ll.traverseAll();
        assertEquals("10 20 80 70 60", outContent.toString().trim());
        ll.sort();
        
        setUpStreams();
        
        ll.traverseAll();
        assertEquals("10 20 60 70 80", outContent.toString().trim());
        
        assertEquals(true,ll.search(10));
        assertEquals(true,ll.search(80));
        assertEquals(false,ll.search(100));
    }
    
}
