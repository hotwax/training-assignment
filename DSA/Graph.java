import java.util.Scanner;
import java.util.InputMismatchException;
class GrapH {
  int vertex;
  int edge;
  int[][] graph;
  Scanner sc = new Scanner(System.in);

  GrapH(int x, int y) //constructor to initialize vertex and edges
  {
    vertex = x;
    edge = y;
    graph = new int[vertex + 1][vertex + 1];
  }

  void insert(int v1, int v2, int w) // to create adjacency matrix 
  {
    graph[v1][v2] = w;
    graph[v2][v1] = w;
  }

  void adjMaxtrix() // to initialize adjacency matrix
  {
    for (int i = 0; i < edge; i++) {
      System.out.println("enter starting vertex ");
      int a = sc.nextInt();
      System.out.println("enter ending vertex ");
      int b = sc.nextInt();
      insert(a, b, 1);
      System.out.println("==================");
    }
  }

  void Pmatrix() //to print adjacency matrix
  {
    for (int i = 0; i < vertex; i++) {
      for (int j = 0; j < vertex; j++)
        System.out.print(graph[i][j] + "  ");

      System.out.println();
    }
  }

  void dfs(int start, boolean[] visited) {
    // Print the current node
    System.out.print(start + " ");
    // Set current node as visited
    visited[start] = true;
    // For every node of the graph
    for (int i = 0; i < graph[start].length; i++) {
      // If some node is adjacent to the current node
      // and it has not already been visited
      if (graph[start][i] == 1 && (!visited[i])) {
        dfs(i, visited);
      }
    }
  }

}
class Demo {
  public static void main(String ar[]) {
    Scanner sc = new Scanner(System.in);
    try {
      System.out.println("Enter number of vertices");
      int vertex = sc.nextInt();
      System.out.println("Enter number of edges");
      int edge = sc.nextInt();
      GrapH g = new GrapH(vertex, edge);
      int a, b;

      while (true) {
        System.out.println("===========================");
        System.out.println("*****Enter your choice*****");
        System.out.println("1. Create Adjancy matrix ");
        System.out.println("2. Print Adjancy matrix");
        System.out.println("3. Traversing DFS");
        System.out.println("4. Exit");
        System.out.println("===========================");
        try {
          int x = sc.nextInt();
          switch (x) {
          case 1:
            g.adjMaxtrix();
            System.out.println("Done ");
            break;

          case 2:
            g.Pmatrix();
            break;

          case 3:
            boolean[] visited = new boolean[vertex];
            g.dfs(0, visited);
            System.out.println();
            break;

          case 4:
            System.out.println("Thank you");
            System.exit(0);
            break;

          default:
            System.out.println("Invalid");
            break;
          }
        } catch (Exception e) {
          System.out.println("Enter valid option");
        }
      }
    } catch (InputMismatchException e) {
      System.out.println("Enter valid option");
    }

  }
}