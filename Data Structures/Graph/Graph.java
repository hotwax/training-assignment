import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Graph {
    ArrayList<ArrayList<Integer>> adj;
    int V;

    public Graph(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public void deleteEdge(int u, int v) {
        adj.get(u).remove((Integer) v);
        adj.get(v).remove((Integer) u);
    }

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

    public void dfs(int s) {
        boolean[] visited = new boolean[V];
        dfsUtil(s, visited);
        System.out.println();
    }

    void dfsUtil(int s, boolean[] visited) {
        visited[s] = true;
        System.out.print(s + " ");
        for (int i : adj.get(s)) {
            if (!visited[i]) {
                dfsUtil(i, visited);
            }
        }
    }

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //
        Graph graph = null;
        int choice;
        do {
            // Printing the menu for the user
            System.out.println("--------- MENU ---------");
            System.out.println("Press 0 : Create a New Graph");
            System.out.println("Press 1 : Add a new Edge");
            System.out.println("Press 2 : Delete a Node");
            System.out.println("Press 3 : Print the BFS Traversal");
            System.out.println("Press 4 : Print the DFS Traversal");
            System.out.println("Press 5 : Exit");
            System.out.println("Enter your choice : ");
            choice = sc.nextInt();
            // Taking the choice from the user and performing the corresponding operation
            switch (choice) {
                case 0:
                    System.out.print("Please Enter the size of the Graph: ");
                    graph = new Graph(sc.nextInt());
                    break;
                case 1:
                    if (graph != null) {
                        System.out.print("Please Enter the Edge Values: ");
                        graph.addEdge(sc.nextInt(), sc.nextInt());
                    } else {
                        System.out.println("Please create a Graph First");
                    }
                    break;
                case 2:
                    System.out.print("Please Enter the Edge Values: ");
                    if (graph != null) {
                        graph.deleteEdge(sc.nextInt(), sc.nextInt());
                    } else {
                        System.out.println("Please create a Graph First");
                    }
                    break;
                case 3:
                    if (graph != null) {
                        System.out.print("Please Enter the Source Node: ");
                        graph.bfs(sc.nextInt());
                    } else {
                        System.out.println("Please create a Graph First");
                    }
                    break;
                case 4:
                    if (graph != null) {
                        System.out.print("Please Enter the Source Node: ");
                        graph.dfs(sc.nextInt());
                    } else {
                        System.out.println("Please create a Graph First");
                    }
                    break;
                case 5:
                    System.out.println("Program Terminated Successfully");
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        } while (choice != 7);

    }

}
