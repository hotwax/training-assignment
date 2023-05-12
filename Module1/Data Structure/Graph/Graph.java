import java.util.Queue;
import java.util.LinkedList;
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
        // remove the edge between Node a and b. (undirected)
        adj[from][to] = 0;
        adj[to][from] = 0;
    }

    // Traversals
    public void dfsTraverse() {
        // Bolean array is created to keep track of the nodes visited. 
        boolean visited[] = new boolean[no_Of_Vertices];
        for (int node = 0; node < no_Of_Vertices; node++) {
            if (!visited[node])
                dfs_main(node, visited);
        }
        System.out.println();
    }

    public void dfs_main(int start, boolean visited[]) {
        visited[start] = true;
        System.out.print(start + " --> ");
        for (int neighbor : adj[start]) {
            if (neighbor == 1 && !visited[neighbor])
                dfs_main(neighbor, visited);
        }
    }

    public void bfsTraverse() {
        boolean visited[] = new boolean[no_Of_Vertices];
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
            System.out.print(u + "--> ");
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
        System.out.println(" 1 Insert Edge");
        System.out.println(" 2 Delete Edge");
        System.out.println(" 3 BFS Traversal");
        System.out.println(" 4 DFS Traversal");
        System.out.println(" 5 EXIT.");
        System.out.println();
        Scanner input = new Scanner(System.in);
        while(true){
        int option = input.nextInt();
            
        if(option == 1){// addEdge
            System.out.println("Enter the nodes you want to insert.");
            g.addEdge(input.nextInt(), input.nextInt());
            System.out.println("The Nodes are added.");
        }
        else if(option ==2){
            // removeEdge
            System.out.println("Enter the nodes which you want to delete");
            g.deleteEdge(input.nextInt(), input.nextInt());
            System.out.println("The Nodes are deleted.");
        }
        else if(option ==3){
            // bfs
            System.out.println("BFS Traversal");
            g.bfsTraverse();
        }
        else if(option == 4){// dfs
            System.out.println("DFS Traversal");
            g.dfsTraverse();
        }
        else if(option == 5){
            // dfs
            System.out.println("Exiting the program.");
            break;
        }
        else{
            System.out.println("Invalid Input");
            
        }
        
        }
    }

}
