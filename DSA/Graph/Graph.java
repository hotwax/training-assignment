import java.util.Scanner;
import java.util.InputMismatchException;
class CustomGraph {
  int vertex;
  int edge;
  int[][] graph;
  Scanner sc = new Scanner(System.in);

  CustomGraph(int vertex, int edge) //constructor to initialize vertex and edges
  {
    this.vertex = vertex;
    this.edge = edge;
    this.graph = new int[vertex + 1][vertex + 1];
  }

  void insert(int vertex1, int vertex2, int weight) // to create adjacency matrix 
  {
    graph[vertex1][vertex2] = weight;
    graph[vertex2][vertex1] = weight;
  }

  void adjMaxtrix() // to initialize adjacency matrix
  {
    for (int index = 0; index < edge; index++) {
      System.out.println("enter starting vertex ");
      int value1 = sc.nextInt();
      System.out.println("enter ending vertex ");
      int value2 = sc.nextInt();
      insert(value1, value2, 1);
      System.out.println("==================");
    }
  }

  void printMatrix() //to print adjacency matrix
  {
    for (int row = 0; row < vertex; row++) {
      for (int column = 0; column < vertex; column++)
        System.out.print(graph[row][column] + "  ");

      System.out.println();
    }
  }

  void dfs(int start, boolean[] visited) {
    // Print the current node
    System.out.print(start + " ");
    // Set current node as visited
    visited[start] = true;
    // For every node of the graph
    for (int index = 0; index < graph[start].length; index++) {
      // If some node is adjacent to the current node
      // and it has not already been visited
      if (graph[start][index] == 1 && (!visited[index])) {
        dfs(index, visited);
      }
    }
  }

}
class Main {
  public static void main(String ar[]) {
    Scanner sc = new Scanner(System.in);
    try {
      System.out.println("Enter number of vertices");
      int vertex = sc.nextInt();
      System.out.println("Enter number of edges");
      int edge = sc.nextInt();
      CustomGraph g = new CustomGraph(vertex, edge);

      while (true) {
        System.out.println("===========================");
        System.out.println("*****Enter your choice*****");
        System.out.println("1. Create Adjancy matrix ");
        System.out.println("2. Print Adjancy matrix");
        System.out.println("3. Traversing DFS");
        System.out.println("4. Exit");
        System.out.println("===========================");
        try {
          int condition = sc.nextInt();
          switch (condition) {
          case 1:
            g.adjMaxtrix();
            System.out.println("Done ");
            break;

          case 2:
            g.printMatrix();
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