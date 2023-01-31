import java.util.Scanner;

public class CustomGraph {

  public static void main(String[] args) {

    try {
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter the number of nodes: ");
      int noofnodes = sc.nextInt();

      Graph graph = new Graph();
      graph.makeArray(noofnodes);


      while (true) {
        System.out.println("Your graph: ");
        graph.display();

        System.out.println("Enter 1 to add edge");
        System.out.println("Enter 2 to remove edge");
        System.out.println("Enter 3 to apply dfs");
        System.out.println("Enter 4 to apply bfs");

        System.out.println("\nNodes values should be between 1 and "+noofnodes+" both inclusive");

        int choice = sc.nextInt();

        if (choice == 1) {
          System.out.print("Nodes value: ");
          int v1 = sc.nextInt(), v2 = sc.nextInt();

          graph.addEdge(v1, v2);
          System.out.println("-------------------------------------");
        } else if (choice == 2) {
          System.out.print("Nodes value: ");
          int v1 = sc.nextInt(), v2 = sc.nextInt();

          graph.removeEdge(v1, v2);
          System.out.println("-------------------------------------");
        } else if (choice == 3) {
          System.out.print("Starting point: ");
          int src = sc.nextInt();
          if(src<=0 || src>noofnodes) System.out.println("Invalid arguments");
          else graph.dfs(src - 1, new boolean[noofnodes], -1);
          System.out.println("-------------------------------------");
        } else if (choice == 4) {
          System.out.print("Starting point: ");
          int src = sc.nextInt();
          if(src<=0 || src>noofnodes) System.out.println("Invalid arguments");
          else graph.bfs(src, noofnodes);
          System.out.println("-------------------------------------");
        } else {
          System.out.println("Program terminated successfully.");
          System.out.println("-------------------------------------");
          return;
        }
      }

    } catch (Exception e) {
      System.out.println(e);
    }

  }

}

class Graph {
  int[][] graph;

  void makeArray(int noofnodes) {
    graph = new int[noofnodes][noofnodes];
  }

  void display() {
    for (int row = 0; row < graph.length; row++) {

      System.out.print((row + 1) + " -> ");

      for (int col = 0; col < graph.length; col++) {

        if (graph[row][col] == 1) {
          System.out.print((col + 1) + " ");
        }
      }

      System.out.println();
    }
  }

  void addEdge(int v1, int v2) {
    if (v1<0 || v1>graph.length || v2<0 || v2>graph.length) {
      System.out.println("Invalid arguments");
    } else {
      graph[v1 - 1][v2 - 1] = 1;
      graph[v2 - 1][v1 - 1] = 1;
      
    }
  }

  void removeEdge(int v1, int v2) {
    if (v1<0 || v1>graph.length || v2<0 || v2>graph.length) {
      System.out.println("Invalid arguments");
    } else {
      graph[v1 - 1][v2 - 1] = 0;
      graph[v2 - 1][v1 - 1] = 0;
      
    }
  }

  void dfs(int v, boolean[] visited, int parent) {

    if (visited[v])
      return;

    visited[v] = true; // if true- node is already visited, false- node is not visited yet 

    if(parent!=-1) System.out.println((parent + 1) + " -> " + (v + 1));

    for (int nbr = 0; nbr < visited.length; nbr++) {
      if (graph[v][nbr] == 1 && visited[nbr] == false)
        dfs(nbr, visited, v);
    }
  }

  void bfs(int src, int noofnodes) {
    Queue queue = new Queue();
    boolean[] visited = new boolean[noofnodes]; // if true- node is already visited, false- node is not visited yet 

    queue.add(src);
    while (queue.size() > 0) {

      int removedVal = queue.remove();

      if (visited[removedVal - 1])
        continue;

        
      visited[removedVal - 1] = true;

      System.out.println(removedVal);

      for (int nbr = 0; nbr < graph.length; nbr++) {
        if (graph[removedVal - 1][nbr] == 1 && visited[nbr] == false)
          queue.add(nbr + 1);
      }
    }

  }

}
