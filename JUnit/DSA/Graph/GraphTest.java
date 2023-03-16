package graph;
import org.junit.Test;
import static org.junit.Assert.*;
public class GraphTest {
  @Test
  public void testPrintMatrix() {
    Graph graph = new Graph(4, 4);
    graph.insert(0, 1, 1);
    graph.insert(0, 2, 1);
    graph.insert(0, 3, 1);
    graph.insert(0, 4, 1);
    graph.printMatrix();
    System.out.println();
        int[][] expectedMatrix = new int[][]{
                {0, 1, 1, 1, 1},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0}
        };
    assertArrayEquals(expectedMatrix, graph.graph);
  }

  @Test
  public void testAdjMatrixWithValidInput() {
    Graph graph = new Graph(4, 4);
    graph.insert(0, 1, 1);
    graph.insert(0, 2, 1);
    graph.insert(0, 3, 1);
    graph.insert(0, 4, 1);

         int[][] expectedMatrix = new int[][]{
                {0, 1, 1, 1, 1},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0}
        };
    assertArrayEquals(expectedMatrix, graph.graph);
  }
  @Test
  public void testDfs() {
    int[][] graph = {
            {0, 1, 1, 0},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {0, 1, 1, 0}
    };
    Graph gra = new Graph(3, 3);
    boolean[] visited = new boolean[graph.length];
    gra.dfs(0, visited);

    assertTrue(visited[0]);
    assertFalse(visited[1]);
    assertFalse(visited[2]);
    assertFalse(visited[3]);
  }

}