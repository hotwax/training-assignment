import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Graph {
    // adjacency list
    ArrayList<ArrayList<Integer>> adj;
    int V;

    // constructor
    public Graph(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // add edge by addding the vertex to the adjacency list
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    // remove edge by removing the vertex from the adjacency list
    public void deleteEdge(int u, int v) {
        adj.get(u).remove((Integer) v);
        adj.get(v).remove((Integer) u);
    }

    // breadth first search
    public void bfs(int s) {
        boolean[] visited = new boolean[V];
        List<Integer> queue = new ArrayList<>();
        visited[s] = true;
        queue.add(s);
        while (!queue.isEmpty()) {
            s = queue.remove(0);
            System.out.print(s + " ");
            for (int i : adj.get(s)) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        System.out.println();
    }

    // depth first search
    public void dfs(int s) {
        boolean[] visited = new boolean[V];
        dfsSolve(s, visited);
        System.out.println();
    }

    // dfs helper function
    void dfsSolve(int s, boolean[] visited) {
        visited[s] = true;
        System.out.print(s + " ");
        for (int i : adj.get(s)) {
            if (!visited[i]) {
                dfsSolve(i, visited);
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Graph graph = new Graph(input.nextInt());
        Boolean flag = true;
        while (flag) {
            System.out.println("Select an option from the menu below");
            System.out.println("1. Insert an edge in the Graph");
            System.out.println("2. Delete an edge from the Graph");
            System.out.println("3. Exit");

            int choice = input.nextInt();
            if (choice < 1 || choice > 4) {
                System.out.println("Invalid choice");
            } else {
                switch (choice) {
                    case 1:
                        System.out.println("Enter the vertex1 and vertex2 to be inserted");
                        int u = input.nextInt();
                        int v = input.nextInt();
                        graph.addEdge(u, v);
                        break;
                    case 2:
                        System.out.println("Enter the vertexes which edge to be deleted");
                        int v1 = input.nextInt();
                        int v2 = input.nextInt();
                        graph.deleteEdge(v1, v2);
                        break;
                    case 3:
                        flag = false;
                        break;
                }
                if (flag == false) {
                    System.out.println("Exiting the program");
                }
            }
        }
    }

}
