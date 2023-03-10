import java.util.Scanner;
import java.util.InputMismatchException;

public class CustomGraph {

  public static void main(String[] args) {

    try {
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter the number of nodes: ");
      int noofnodes = sc.nextInt();

      if (noofnodes == 0) {
        System.out.println("Please enter a valid (>0) number of nodes");
        return;
      }

      Graph graph = new Graph();
      graph.makeArray(noofnodes);

      while (true) {
        System.out.println("Your graph: ");
        graph.display();

        System.out.println("Enter 1 to add edge");
        System.out.println("Enter 2 to remove edge");
        System.out.println("Enter 3 to apply dfs");
        System.out.println("Enter 4 to apply bfs");
        System.out.println("Enter 5 to exit");

        System.out.println("\nNodes values should be between 1 and " + noofnodes + " both inclusive");

        int choice = sc.nextInt();
        int v1, v2;

        switch (choice) {
          case 1:
            System.out.print("Nodes value: ");
            v1 = sc.nextInt();
            v2 = sc.nextInt();

            graph.addEdge(v1, v2);
            System.out.println("-------------------------------------");
            break;

          case 2:
            System.out.print("Nodes value: ");
            v1 = sc.nextInt();
            v2 = sc.nextInt();

            graph.removeEdge(v1, v2);
            System.out.println("-------------------------------------");
            break;

          case 3:
            System.out.print("Starting point: ");
            int src = sc.nextInt();
            if (src <= 0 || src > noofnodes)
              System.out.println("Invalid arguments");
            else
              graph.dfs(src - 1, new boolean[noofnodes], -1);
            System.out.println("-------------------------------------");
            break;

          case 4:
            System.out.print("Starting point: ");
            src = sc.nextInt();
            if (src <= 0 || src > noofnodes)
              System.out.println("Invalid arguments");
            else
              graph.bfs(src, noofnodes);
            System.out.println("-------------------------------------");
            break;

          case 5:
            System.out.println("Program terminated successfully.");
            System.out.println("-------------------------------------");
            return;

          default:
            System.out.println("Please enter a valid choice (1,2,3,4,5).");
            System.out.println("-------------------------------------");
            break;
        }

      }

    } catch (InputMismatchException e) {
      System.out.println("Please give a valid number. " + e);
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
    if (v1 < 0 || v1 > graph.length || v2 < 0 || v2 > graph.length) {
      System.out.println("Invalid arguments");
    } else {
      graph[v1 - 1][v2 - 1] = 1;
      graph[v2 - 1][v1 - 1] = 1;

    }
  }

  void removeEdge(int v1, int v2) {
    if (v1 < 0 || v1 > graph.length || v2 < 0 || v2 > graph.length) {
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

    if (parent != -1)
      System.out.println((parent + 1) + " -> " + (v + 1));

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

class Queue {

  Queue() {

  }

  Node head, tail;
  int size;

  boolean isEmpty() {
    if (size == 0)
      System.out.println("Queue is empty. Please enter 1 to add a number to queue.");
    return size == 0;
  }

  void add(int val) {
    Node temp = new Node(); // a temporary node
    temp.data = val;

    if (size == 0) {
      head = tail = temp;
    } else {
      tail.next = temp;
      tail = temp;
    }

    size++;
  }

  int remove() {
    if (size == 0) {
      System.out.println("List is empty");
      return -1;
    } else if (size == 1) {
      int headData = head.data;
      head = tail = null;
      size = 0;
      return headData;
    } else {
      int headData = head.data;
      head = head.next;
      size--;
      return headData;
    }
  }

  int peek() {
    if (size == 0) {
      System.out.println("List is empty");
      return -1;
    } else {
      return head.data;
    }
  }

  int search(int val) {
    Node temp = head;

    int idx = 0; // index
    while (temp != null) {
      if (temp.data == val)
        return idx;
      temp = temp.next;
      idx++;
    }

    return -1;
  }

  void display(Node node) {
    if (node == null)
      return;

    System.out.print(node.data + " ");
    display(node.next);
  }

  int size() {
    return size;
  }

}

class Node {
  int data;
  Node next;
}
