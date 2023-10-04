import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Graphtest 
{
   Graph graph = new Graph(5);

@Test
public void testAddEdge() {
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
       int array[][]={{0,1,1,0,0},{1,0,0,1,0},{1,0,0,0,0},{0,1,0,0,0},{0,0,0,0,0}};       
        Assertions.assertArrayEquals(array, graph.graphdis()); 
    }

    @Test
    public void testRemoveEdge() {
            graph.addEdge(0, 1);
            graph.addEdge(0, 2);
            graph.addEdge(1, 3);
           int array[][]={{0,1,1,0,0},{1,0,0,1,0},{1,0,0,0,0},{0,1,0,0,0},{0,0,0,0,0}};       
           Assertions.assertArrayEquals(array, graph.graphdis()); 
           graph.removeEdge(0, 2);
           int array2[][]={{0,1,0,0,0},{1,0,0,1,0},{0,0,0,0,0},{0,1,0,0,0},{0,0,0,0,0}};       
           Assertions.assertArrayEquals(array2, graph.graphdis()); 
        }

        @Test
        public void testDFS() {
        ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
        
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);

        System.setOut(new PrintStream(outputstream));
        graph.DFS();
        Assertions.assertEquals("0 1 3 2 4".replaceAll(" ",""),outputstream.toString().replaceAll(" ",""));
        }  

        @Test
        public void testBFS() {
        boolean[] vis=new boolean[5];
        ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
        
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        
        System.setOut(new PrintStream(outputstream));
        graph.BFS(vis);
        Assertions.assertEquals("0 1 2 3 4".replaceAll(" ",""),outputstream.toString().replaceAll(" ",""));
        }     
    }