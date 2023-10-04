
//Importing necessary Packages
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.LinkedList;

//Graph Class
public class Graph {
    private int vertices; // Vertices in graph
    private int adjacencyMatrix[][]; // Adjacency Matrix represeting graph
    private boolean visited[];

    // Constructor for intializing graph object
    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencyMatrix = new int[vertices][vertices];
        visited = new boolean[vertices];
    }

    // method to get adjacency marix of a graph
    public int[][] graphdis() {
        return adjacencyMatrix;
    }

    // method to get adjacency marix of a graph
    public void addEdge(int source, int destination) {
        adjacencyMatrix[source][destination] = 1; // adding an edge from source to destination
        adjacencyMatrix[destination][source] = 1; // adding an edge from destination to source
    }

    // method to remove an edge
    public void removeEdge(int source, int destination) {
        adjacencyMatrix[source][destination] = 0; // removing an edge from source to destination
        adjacencyMatrix[destination][source] = 0; // removing an edge from destination to source
    }

    // method to print the graph
    public void printGraph() {
        for (int index1 = 0; index1 < vertices; index1++) {
            System.out.print(index1 + "->");
            for (int index2 = 0; index2 < vertices; index2++) {
                if (adjacencyMatrix[index1][index2] == 1) {
                    System.out.print(index2 + " ");
                }
            }
            System.out.println();
        }
    }

    // method to print DFS traversal of Graph
    public void DFS() {
        for (int index1 = 0; index1 < vertices; index1++) {
            if (!visited[index1]) {
                dfs(index1);
            }
        }
        for (int index = 0; index < vertices; index++) {
            visited[index] = false;
        }
    }

    // recursive call for DFS
    public void dfs(int vertice) {
        visited[vertice] = true; // marking current vertice as visited
        System.out.print(vertice + " ");
        for (int index2 = 0; index2 < vertices; index2++) {
            if (adjacencyMatrix[vertice][index2] == 1 && !visited[index2]) {
                dfs(index2);
            }
        }
    }

    // method to print BFS traversal of Graph
    public void BFS(boolean[] vis) {
        for (int index1 = 0; index1 < vertices; index1++) {
            if (!vis[index1]) { // visiting unvisited vertices in Graph
                bfs(index1, vis);
            }
        }

    }

    // recursive call for BFS
    public void bfs(int vertices, boolean[] vis) {

        LinkedList<Integer> queue = new LinkedList<Integer>();
        vis[vertices] = true; // marking current vertex as visited
        queue.add(vertices); // adding current vertex in queue

        while (!queue.isEmpty()) {
            vertices = queue.poll();
            System.out.print(vertices + " ");

            for (int index1 = 0; index1 < vertices; index1++) {
                for (int index2 = 0; index2 < vertices; index2++) {
                    if (adjacencyMatrix[index2][index2] == 1 && !vis[index2]) {
                        vis[index2] = true;
                        queue.add(index2);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;
            System.out.print("Enter number of vertices: ");
            int NumberVertices = scanner.nextInt();
            Graph graph = new Graph(NumberVertices);
            do {
                System.out.println("\nMenu:");
                System.out.println("1. Add edge");
                System.out.println("2. Remove edge");
                System.out.println("3. Print graph");
                System.out.println("4. DFS traversal");
                System.out.println("5. BFS traversal");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
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
                        System.out.print("DFS traversal: ");
                        graph.DFS();
                        break;
                    case 5:
                        boolean[] vis = new boolean[NumberVertices];
                        System.out.print("BFS traversal: ");
                        graph.BFS(vis);
                        break;
                    case 6:
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice.");
                }
            } while (choice != 6);
        } catch (InputMismatchException exception) {
            System.out.println(exception);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}