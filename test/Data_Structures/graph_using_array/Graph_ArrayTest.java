/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_Structures.graph_using_array;

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
public class Graph_ArrayTest {
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
        Graph_Array graph=new Graph_Array(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(0, 2);
        graph.addEdge(4, 2);
        graph.addEdge(4, 0);
        setUpStreams();
        graph.dfs();
        assertEquals("0 1 2 3 4", outContent.toString().trim());
        setUpStreams();
        graph.bfs();
        assertEquals("0 1 2 3 4", outContent.toString().trim());
        
        graph.deleteEdge(3, 4);
        graph.deleteEdge(1, 2);
        graph.deleteEdge(0, 1);
        
        setUpStreams();
        graph.dfs();
        assertEquals("0 2 3 4 1", outContent.toString().trim());
        setUpStreams();
        graph.bfs();
        assertEquals("0 2 3 4 1", outContent.toString().trim());
        
        //assertEquals("10 20 60 70 80", outContent.toString().trim());
        
    }
    
}
