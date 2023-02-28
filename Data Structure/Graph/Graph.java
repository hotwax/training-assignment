import java.util.*;

public class Graph {
    private int V;
    private LinkedList<Integer>[] adjList;

    public Graph(int v) {
        V = v;
        adjList = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adjList[i] = new LinkedList<Integer>();
        }
    }

    public void addEdge(int src, int dest) {
        adjList[src].add(dest);
        adjList[dest].add(src);
    }

    public void removeEdge(int src, int dest) {
        adjList[src].remove(Integer.valueOf(dest));
        adjList[dest].remove(Integer.valueOf(src));
    }

    public void printGraph() {
        for (int i = 0; i < V; i++) {
            System.out.print("Vertex " + i + ": ");
            for (Integer j : adjList[i]) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
    
    void DFS(boolean[] visited){
        
        for(int i=0;i<V;i++){
            if(!visited[i]){
                dfs(i,visited);
            }
        }
        
    }

    public void dfs(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");

        for (int i : adjList[v]) {
            if (!visited[i]) {
                dfs(i, visited);
            }
        }
    }

void BFS(boolean[] vis){
    
    for(int i=0;i<V;i++){
        if(!vis[i]){
            bfs(i,vis);
        }
    }
    
}
     
    public void bfs(int v,boolean[] vis) {
       
        LinkedList<Integer> queue = new LinkedList<Integer>();
        vis[v] = true;
        queue.add(v);

        while (!queue.isEmpty()) {
            v = queue.poll();
            System.out.print(v + " ");

            for (int i : adjList[v]) {
                if (!vis[i]) {
                    vis[i] = true;
                    queue.add(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int n = scanner.nextInt();
        Graph graph = new Graph(n);
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add edge");
            System.out.println("2. Remove edge");
            System.out.println("3. Print graph");
            System.out.println("4. DFS traversal");
            System.out.println("5. BFS traversal");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter source vertex: ");
                    int src = scanner.nextInt();
                    System.out.print("Enter destination vertex: ");
                    int dest = scanner.nextInt();
                    graph.addEdge(src, dest);
                    System.out.println("Edge added.");
                    break;
                case 2:
                    System.out.print("Enter source vertex: ");
                    src = scanner.nextInt();
                    System.out.print("Enter destination vertex: ");
                    dest = scanner.nextInt();
                    graph.removeEdge(src, dest);
                    System.out.println("Edge removed.");
                    break;
                case 3:
                    graph.printGraph();
                    break;
                case 4:
                    boolean[] visited = new boolean[n];
                    System.out.print("DFS traversal: ");
                    graph.DFS( visited);
                    break;
                case 5:
                     boolean[] vis= new boolean[n];
                    System.out.print("BFS traversal: ");
                    graph.BFS(vis);
                    break;
                case 6:
                   System.exit(0);
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }
}