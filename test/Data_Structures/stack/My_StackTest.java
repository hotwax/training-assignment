/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_Structures.stack;

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
public class My_StackTest {
    
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
        My_Stack stack=new My_Stack();
        stack.push(10);
        stack.push(20);
        stack.push(80);
        stack.push(70);
        stack.push(60);
        setUpStreams();
        stack.traverseAll();
        assertEquals("60 70 80 20 10", outContent.toString().trim());
        
        assertEquals(true,stack.search(10));
        assertEquals(true,stack.search(80));
        assertEquals(false,stack.search(100));
        
        assertEquals(60,stack.peek());
        assertEquals(60,stack.pop());
        assertEquals(70,stack.pop());
        assertEquals(80,stack.pop());
        assertEquals(20,stack.pop());
        assertEquals(10,stack.pop());
        assertEquals(-1,stack.pop());
    }
}
