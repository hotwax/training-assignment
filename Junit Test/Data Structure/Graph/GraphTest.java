import org.junit.Test;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GraphTest {


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
    public void insertTest(){

        Graph graph= new Graph(4);

        // normal insertion
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,2);
        graph.addEdge(2,0);
        graph.addEdge(2,3);
        
        setUpStreams();

        graph.display();
        assertEquals("0 1 1 0 \r\n1 0 1 0 \r\n1 1 0 1 \r\n0 0 1 0", outContent.toString().trim());


    }


@Test
public void removeTest(){


    Graph graph= new Graph(4);

    // normal insertion
    graph.addEdge(0,1);
    graph.addEdge(0,2);
    graph.addEdge(1,2);
    graph.addEdge(2,0);
    graph.addEdge(2,3);

    graph.removeEdge(2,3);

    setUpStreams();

    graph.display();
    assertEquals("0 1 1 0 \r\n1 0 1 0 \r\n1 1 0 0 \r\n0 0 0 0", outContent.toString().trim());

}



@Test
public void bfsTest(){
    
        Graph graph= new Graph(4);
    
        // normal insertion
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,2);
        graph.addEdge(2,0);
        graph.addEdge(2,3);
    
        setUpStreams();
    
        graph.bfs(2);
        assertEquals("2 0 1 3", outContent.toString().trim());
    
}

@Test
public void dfsTest(){
    
        Graph graph= new Graph(4);
    
        // normal insertion
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,2);
        graph.addEdge(2,0);
        graph.addEdge(2,3);
    
        setUpStreams();
        boolean visited[]= new boolean[4];

        graph.dfs(2,visited);
        assertEquals("2 0 1 3", outContent.toString().trim());  }
    




    
        
}
