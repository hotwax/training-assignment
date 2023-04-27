package Graph;
import org.junit.jupiter.api.Test;
import Queue.Queue;
import org.junit.jupiter.api.Assertions;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DirectedGraphTest {
  boolean isDirected = true;
  int numberOfNodes = 5;
  Graph graph = new Graph(numberOfNodes, isDirected);
  final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

  // passed
  @Test
  void addEdgeTest() {
    graph.addEdge(0, 1);
    graph.addEdge(0, 4);
    graph.addEdge(1, 3);
    graph.addEdge(2, 4);
    graph.addEdge(3, 0);
    graph.addEdge(4, 1);
    int graphMatrix[][] = { { 0, 1, 0, 0, 1 },
        { 0, 0, 0, 1, 0 },
        { 0, 0, 0, 0, 1 },
        { 1, 0, 0, 0, 0 },
        { 0, 1, 0, 0, 0 } };
    Assertions.assertArrayEquals(graphMatrix, graph.graphDisplay());
  }

  @Test
  void deletedEdgeTest() {
    graph.addEdge(0, 1);
    graph.addEdge(0, 4);
    graph.addEdge(1, 3);
    graph.addEdge(2, 3);
    graph.addEdge(3, 0);
    graph.addEdge(4, 2);
    int graphMatrix[][] = { { 0, 1, 0, 0, 1 },
        { 0, 0, 0, 1, 0 },
        { 0, 0, 0, 1, 0 },
        { 1, 0, 0, 0, 0 },
        { 0, 0, 1, 0, 0 } };

    Assertions.assertArrayEquals(graphMatrix, graph.graphDisplay());
    graph.deleteEdge(4, 1);
    graph.deleteEdge(1, 3);
    graphMatrix[4][1] = 0;
    graphMatrix[1][3] = 0;
    Assertions.assertArrayEquals(graphMatrix, graph.graphDisplay());
  }

  @Test
  void bfsTraversalTest() {
    System.setOut(new PrintStream(outputStream));
    Queue<Integer> queue = new Queue<Integer>();
    graph.addEdge(0, 1);
    graph.addEdge(0, 4);
    graph.addEdge(1, 3);
    graph.addEdge(2, 4);
    graph.addEdge(3, 0);
    graph.addEdge(4, 1);
    graph.bFSTraversal(queue);
    Assertions.assertEquals("0 1 4 3 2 ".toString().replaceAll(" ", ""), outputStream.toString().replaceAll(" ", ""));
  }

  @Test
  void dfsTraversalTest() {
    System.setOut(new PrintStream(outputStream));
    graph.addEdge(0, 1);
    graph.addEdge(0, 4);
    graph.addEdge(1, 3);
    graph.addEdge(2, 4);
    graph.addEdge(3, 0);
    graph.addEdge(4, 1);
    graph.dFSTraversal();
    Assertions.assertEquals("0 1 3 4 2 ".toString().replaceAll(" ", ""), outputStream.toString().replaceAll(" ", ""));
  }

  @Test
  void numberOfEdgeTest() {
    graph.addEdge(0, 1);
    graph.addEdge(0, 4);
    graph.addEdge(1, 3);
    graph.addEdge(2, 4);
    graph.addEdge(3, 0);
    graph.addEdge(0, 1);// if edges is already present
    graph.addEdge(0, 1);// so it's counted
    graph.addEdge(3, 0);
    graph.addEdge(4, 1);
    Assertions.assertEquals(6, graph.numberOfEdges);
    graph.deleteEdge(3, 0);
    graph.deleteEdge(3, 0);
    graph.deleteEdge(3, 0);
    graph.deleteEdge(2, 4);
    Assertions.assertEquals(4, graph.numberOfEdges);
  }
}
