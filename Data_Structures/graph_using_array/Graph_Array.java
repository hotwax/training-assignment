package Data_Structures.graph_using_array;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//Primitive Array based implementation of graph
public class Graph_Array {

    private int[][] adj;
    int no_Of_Vertices;

    public Graph_Array(int no_Of_Vertices) {
        //created a 2d matrix of size VxV for storing edges
        //1 means there is an edge ie they are adjacent
        //0 means no edge is present and the nodes are not adjacent
        adj = new int[no_Of_Vertices][no_Of_Vertices];
        this.no_Of_Vertices = no_Of_Vertices;
    }

    public void addEdge(int from, int to) {
        if(from >= no_Of_Vertices || to >= no_Of_Vertices || from < 0 || to < 0 ) return;
        //make the node a and b adjacent. (undirected)
        adj[from][to] = 1;
        adj[to][from] = 1;
    }

    public void deleteEdge(int from, int to) {
        //remove the edge between Node a nad b. (undirected)
        adj[from][to] = 0;
        adj[to][from] = 0;
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
        for (int i = 0; i < no_Of_Vertices; i++) {
            if (adj[start][i] == 1 && !visited[i]) {
                dfs_main(i, visited);
            }
        }
    }

    public void bfs() {
        boolean visited[] = new boolean[no_Of_Vertices];
        //call every node of graph wheather it is connected or disconnnected.
        for (int node = 0; node < no_Of_Vertices; node++) {
            if (!visited[node]) {
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
            for (int i = 0; i < no_Of_Vertices; i++) {
                if (adj[start][i] == 1 && !visited[i]) {
                    bfs_main(i, visited);
                }
            }
        }
    }

    public static void main(String args[]) {
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Enter number of vertices");
        int V = sc1.nextInt();
        Graph_Array graph = new Graph_Array(V);
        boolean terminationFlag = true;
        while (terminationFlag) {
            try {
                System.out.println("=========================");
                System.out.println("1: add Edge");
                System.out.println("2: remove Edge");
                System.out.println("3: bfs Traversal");
                System.out.println("4: dfs Traversal");
                System.out.println("5: terminate the program");
                System.out.println();
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
                        System.out.println("program terminated");
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
