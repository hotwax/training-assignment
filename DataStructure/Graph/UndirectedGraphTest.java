package Graph;
import org.junit.jupiter.api.Test;
import Queue.Queue;
import org.junit.jupiter.api.Assertions;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
public class UndirectedGraphTest {
  boolean isDirected = false;
  int numberOfNodes = 5;
  Graph graph = new Graph(numberOfNodes, isDirected);

  // passed
  @Test
  void addedgeTest() {
    graph.Addedge(0, 1);
    graph.Addedge(0, 4);
    graph.Addedge(1, 3);
    graph.Addedge(2, 4);
    graph.Addedge(3, 0);
    graph.Addedge(4, 1);
    int arr[][] = { { 0, 1, 0, 1, 1 }, 
                    { 1, 0, 0, 1, 1 },
                    { 0, 0, 0, 0, 1 },
                    { 1, 1, 0, 0, 0 },
                    { 1, 1, 1, 0, 0 } };
    Assertions.assertArrayEquals(arr, graph.graphDisplay());

  }

  @Test
  void deletedEdgeTest() {

    graph.Addedge(0, 1);
    graph.Addedge(0, 4);
    graph.Addedge(1, 3);
    graph.Addedge(2, 3);
    graph.Addedge(3, 0);
    graph.Addedge(4, 2);
    int arr[][] = { { 0, 1, 0, 1, 1 }, 
                    { 1, 0, 0, 1, 0 },
                    { 0, 0, 0, 1, 1 },
                    { 1, 1, 1, 0, 0 },
                    { 1, 0, 1, 0, 0 } };
   
    Assertions.assertArrayEquals(arr, graph.graphDisplay());               
    graph.deletededge(4, 1);
    graph.deletededge(1, 3);
    arr[4][1]=0;
    arr[1][4]=0;
    arr[1][3]=0;
    arr[3][1]=0;
    Assertions.assertArrayEquals(arr, graph.graphDisplay()); 
  }

@Test
void bfsTraversalTest(){
  final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); 
  System.setOut(new PrintStream(outputStream));
  Queue<Integer> queue =new Queue<Integer>();
  graph.Addedge(0, 1);
  graph.Addedge(0, 4);
  graph.Addedge(1, 3);
  graph.Addedge(2, 4);
  graph.Addedge(3, 0);
  graph.Addedge(4, 1);
  graph.BFS(queue);
  Assertions.assertEquals("0 1 3 4 2 ".toString().replaceAll(" ", ""), outputStream.toString().replaceAll(" ",""));


}


@Test
void dfsTraversalTest(){
  final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); 
  System.setOut(new PrintStream(outputStream));
  graph.Addedge(0, 1);
  graph.Addedge(1, 3);
  graph.Addedge(2, 4);
  graph.Addedge(3, 0);
  graph.Addedge(4, 2);
  graph.DFS();
  Assertions.assertEquals("0 1 3 2 4 ".toString().replaceAll(" ", ""), outputStream.toString().replaceAll(" ",""));

}

@Test

void numberOfEdgeTest(){
  graph.Addedge(0, 1);
  graph.Addedge(0, 4);
  graph.Addedge(1, 3);
  graph.Addedge(2, 4);
  graph.Addedge(3, 0);
  graph.Addedge(0, 1);//if edges is already present  
  graph.Addedge(0, 1);//so it's counted
  graph.Addedge(3, 0);
  graph.Addedge(4, 1);
  graph.Addedge(1, 4);
  Assertions.assertEquals(6, graph.numberofedges);
  graph.deletededge(3, 0);
  graph.deletededge(3, 0);
  graph.deletededge(3, 0);
  graph.deletededge(2, 4);
  Assertions.assertEquals(4, graph.numberofedges);
}






}
