
import java.util.Queue;
import java.util.LinkedList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Graph {
    private int[][] adj;
    int no_Of_Vertices;

    public Graph(int no_Of_Vertices) {
        // The size of the matrix is nxn.
        // 1 means there is an edge ie they are adjacent
        // 0 means no edge is present and the nodes are not adjacent
        adj = new int[no_Of_Vertices][no_Of_Vertices];
        this.no_Of_Vertices = no_Of_Vertices;
    }

    public void addEdge(int from, int to) {
        // make the node a and b adjacent. (undirected)
        adj[from][to] = 1;
        adj[to][from] = 1;
    }

    public void deleteEdge(int from, int to) {
        // remove the edge between Node a nad b. (undirected)
        adj[from][to] = 0;
        adj[to][from] = 0;
    }

    // Traversals
    public void dfsTraverse() {
        // boolean array to keep track of visited Nodes
        boolean visited[] = new boolean[no_Of_Vertices];
        for (int node = 0; node < no_Of_Vertices; node++) {
            if (!visited[node])
                dfs_main(node, visited);
        }
        System.out.println();
    }

    public void dfs_main(int start, boolean visited[]) {
        visited[start] = true;
        System.out.print(start + " ");
        // call every node of graph whether it is connected or disconnnected.
        for (int neighbor : adj[start]) {
            if (neighbor == 1 && !visited[neighbor])
                dfs_main(neighbor, visited);
        }
    }

    public void bfsTraverse() {
        boolean visited[] = new boolean[no_Of_Vertices];
        // call every node of graph wheather it is connected or disconnnected.
        for (int node = 0; node < no_Of_Vertices; node++) {
            if (!visited[node])
                bfs_main(node);
        }
    }

    public void bfs_main(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        boolean visited[] = new boolean[no_Of_Vertices];
        visited[start] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            System.out.print(u + " ");
            // store all adjacent node of u into the queue if they are not visited
            for (int neighbor : adj[u])
                if (neighbor == 1 && !visited[neighbor]) {
                    q.add(neighbor);
                    visited[neighbor] = true;
                }
        }
        System.out.println();
    }

    public static void main(String args[]) {
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Enter number of vertices");
        int V = sc1.nextInt();
        Graph g = new Graph(V);
        boolean flag = true;
        while (flag) {
            try {
                System.out.println("Select an option from the Menu given below:");
                System.out.println("1: Insert Edge");
                System.out.println("2: Delete Edge");
                System.out.println("3: BFS Traversal");
                System.out.println("4: DFS Traversal");
                System.out.println("5: EXIT.");
                System.out.println();
                Scanner sc = new Scanner(System.in);
                int n = sc.nextInt();
                switch (n) {
                    case 1:// addEdge
                        System.out.println("enter node 1 and node 2 of the edge you want to add");
                        g.addEdge(sc.nextInt(), sc.nextInt());
                        break;
                    case 2:// removeEdge
                        System.out.println("enter node1 and node 2 of the edge you want to delete");
                        g.deleteEdge(sc.nextInt(), sc.nextInt());
                        break;
                    case 3:// bfs
                        System.out.println("BFS Traversal");
                        g.bfsTraverse();
                        break;
                    case 4:// dfs
                        System.out.println("DFS Traversal");
                        g.dfsTraverse();
                        break;
                    case 5:// dfs
                        System.out.println("Exiting the program.");
                        flag = false;
                        break;
                    default:
                        System.out.println("Invalid Input");
                        break;
                }
            } catch (Exception e) {
                // if user enter any other value than integer
                System.out.println("e.getMessage()");
            }
        }

    }
}