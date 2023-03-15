/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_Structures.queue;

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
public class My_QueueTest {
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
        My_Queue stack=new My_Queue();
        stack.add(10);
        stack.add(20);
        stack.add(80);
        stack.add(70);
        stack.add(60);
        setUpStreams();
        stack.traverseAll();
        assertEquals("10 20 80 70 60", outContent.toString().trim());
        
        assertEquals(true,stack.search(10));
        assertEquals(true,stack.search(80));
        assertEquals(false,stack.search(100));
        
        assertEquals(10,stack.peek());
        assertEquals(10,stack.poll());
        assertEquals(20,stack.poll());
        assertEquals(80,stack.poll());
        assertEquals(70,stack.poll());
        assertEquals(60,stack.poll());
        assertEquals(-1,stack.poll());
    }
    
}
