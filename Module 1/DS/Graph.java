import java.util.*;
import java.util.Queue;

public class Graph {
    private List<List<Integer>> adj;
    int V;

    public Graph(int V) {
        adj = new ArrayList<>();
        this.V = V;
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // Insert Method: 
    public void insert(int a, int b) {
        adj.get(a).add(b);
        adj.get(b).add(a);
    }

    // Delete Method:
    public void delete(int a, int b) {
        adj.get(a).remove(b);
        adj.get(b).remove(a);
    }

    // Traversal Method:
    public void helperOne() {
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++)
            if (!visited[i])
                dfsTraversal(i, visited);
        System.out.println();
    }

    public void dfsTraversal(int s, boolean visited[]) {
        visited[s] = true;
        System.out.print(s + " ");
        for (int v : adj.get(s)) {
            if (!visited[v])
                dfsTraversal(v, visited);
        }
    }

    public void helperTwo() {
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i])
                bfsTraversal(i);
        }
    }

    public void bfsTraversal(int s) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        boolean visited[] = new boolean[V];
        visited[0] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            System.out.print(u + " ");
            for (int v : adj.get(u))
                if (!visited[v]) {
                    q.add(v);
                    visited[v] = true;
                }
        }
        System.out.println();
    }

    public void dashBoard() {
        Scanner sc = new Scanner(System.in);

        System.out.println("1: Insert ");
        System.out.println("2: Delete");
        System.out.println("3: BFS Traversal");
        System.out.println("4: DFS Traversal");
        System.out.println("5: Exit");
        boolean flag = true;
        while (flag) {
            int n = sc.nextInt();
            switch (n) {
                case 1:// Insert 
                    System.out.println("Enter the nodes you want to insert. ");
                    insert(sc.nextInt(), sc.nextInt());
                    break;
                case 2://  Delete
                    System.out.println("Enter the nodes you want to delete. ");
                    delete(sc.nextInt(), sc.nextInt());
                    break;
                case 3:// bfs
                    System.out.println("BFS Traversal: ");
                    helperTwo();
                    break;
                case 4:// dfs
                    System.out.println("DFS Traversal: ");
                    helperOne();
                    break;
                case 5: //Exit from the program.
                    flag = false;
                    break;
                default:
                    System.out.println("Kindly enter the correct choice.");
                    break;
            }
        }
        // sc.close();
    }

    public static void main(String args[]) {
        Graph g = new Graph(5);
        g.insert(0, 4);
        g.insert(0, 2);
        g.insert(0, 1);
        g.insert(1, 3);
        g.insert(3, 4);
        g.insert(3, 2);
        g.dashBoard();

    }
}