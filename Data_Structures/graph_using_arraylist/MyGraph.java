package Data_Structures.graph_using_arraylist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class MyGraph {

    //adjacency list store the nodes adjacent to each node
    private List<List<Integer>> adj;
    int no_Of_Vertices;

    public MyGraph(int V) {
        adj = new ArrayList<>();
        this.no_Of_Vertices = V;
        //create adj list for every node
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    //adding edge 
    public void addEdge(int from, int to) {
        //make both Node a and b adjacent to each other (undirected graph)
        if(from >= no_Of_Vertices || to >= no_Of_Vertices || from < 0 || to < 0) return;
        adj.get(from).add(to);
        adj.get(to).add(from);
        System.out.println("Edge Added Successfully");
    }

    public void deleteEdge(int from, int to) {
        //remove both Nodes from each others adjacency list (undirected graph)
        for (int i=0; i<adj.get(from).size(); i++){
            if (adj.get(from).get(i) == to){
                adj.get(from).remove(i);
                break;
            }
        }
 
        // Traversing through the second vector list
        // and removing the first element from it
        for (int i=0; i<adj.get(to).size(); i++){
            if (adj.get(to).get(i) == from){
                adj.get(to).remove(i);
                break;
            }
        }
        System.out.println("Edge Deleted Successfully");
    }

    //Traversals
    public void dfs() {
        //boolean array to keep track of visited Nodes
        boolean visited[] = new boolean[no_Of_Vertices];
        for (int node = 0; node < no_Of_Vertices; node++) {
            if (!visited[node]) {
                dfs_main(node, visited);
            }
        }
        System.out.println();
    }

    public void dfs_main(int start, boolean visited[]) {
        visited[start] = true;
        System.out.print(start + " ");
        //call every node of graph wheather it is connected or disconnnected.
        for (int neighbor : adj.get(start)) {
            if (!visited[neighbor]) {
                dfs_main(neighbor, visited);
            }
        }
    }

    public void bfs() {
        boolean visited[] = new boolean[no_Of_Vertices];
        //call every node of graph wheather it is connected or disconnnected.
        for (int node = 0; node < no_Of_Vertices; node++) {
            if (!visited[node])//if that node is not visited
            {
                bfs_main(node, visited);
            }
        }
        System.out.println();
    }

    public void bfs_main(int start, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            System.out.print(u + " ");
            //store all adjacent node of u into the queue if they are not visited
            for (int neighbor : adj.get(u)) {
                if (!visited[neighbor]) {
                    q.add(neighbor);
                    visited[neighbor] = true;
                }
            }
        }
    }

    public static void main(String args[]) {
        boolean terminationFlag = true;
        System.out.println("Enter number of vertices");
        Scanner sc1 = new Scanner(System.in);
        MyGraph graph = new MyGraph(sc1.nextInt());

        while (terminationFlag) {
            System.out.println("===========================");
            System.out.println("1: add Edge");
            System.out.println("2: remove Edge");
            System.out.println("3: bfs Traversal");
            System.out.println("4: dfs Traversal");
            System.out.println("5: terminate the program");
            System.out.println();

            try {
                Scanner sc = new Scanner(System.in);
                int n = sc.nextInt();
                switch (n) {
                    case 1:// addEdge
                        System.out.println("enter node 1 and node 2 of the edge you want to add");
                        graph.addEdge(sc.nextInt(), sc.nextInt());
                        break;
                    case 2:// removeEdge
                        System.out.println("enter node1 and node 2 of the edge you want to delete");
                        graph.deleteEdge(sc.nextInt(), sc.nextInt());
                        break;
                    case 3:// bfs
                        System.out.println("bfs traversal");
                        graph.bfs();
                        break;
                    case 4:// dfs
                        System.out.println("dfs traversal");
                        graph.dfs();
                        break;
                    case 5:// dfs
                        System.out.println("program terminated successfully");
                        terminationFlag = false;
                        break;
                    default:
                        System.out.println("invalid input");
                        break;
                }
            } catch (Exception e) {
                //if user enter any other value than integer
                System.out.println("Invalid input: please enter Integer");
            }

        }
    }
}