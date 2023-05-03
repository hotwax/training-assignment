package Graph;
import java.util.InputMismatchException;
import java.util.Scanner;
import Queue.Queue;

//Graph class
public class Graph {
    int numberOfNodes;
    private boolean visited[];
    private boolean directed;
    private int graphMatrix[][];
    int numberOfEdges;

    // constructor
    Graph(int nodes, boolean dir) {
        this.numberOfNodes = nodes;
        this.directed = dir;
        graphMatrix = new int[numberOfNodes][numberOfNodes];
        visited = new boolean[numberOfNodes];
        numberOfEdges = 0; // count of the edges
    }

    // Added the edge between two nodes
    void addEdge(int source, int destination) {
        if (destination >= numberOfNodes || source >= numberOfNodes) {
            System.out.println("invalid Edges");
            return;
        }
        if (source != destination && graphMatrix[source][destination] == 0) {
            graphMatrix[source][destination] = 1;
            numberOfEdges++;
            if (!directed) {
                graphMatrix[destination][source] = 1;
            }
        }
    }

    int[][] graphDisplay() {
        return graphMatrix;
    }

    //dfs traversal of graph
    void dFSTraversal() {
        for (int index = 0; index < numberOfNodes; index++) {
            if (!visited[index]) {
                dfsRec(index);
            }
        }
        for (int index = 0; index < numberOfNodes; index++) {
            visited[index] = false;
        }
    }

    void dfsRec(int node) {
        System.out.print(node + " ");
        visited[node] = true;
        for (int index = 0; index < numberOfNodes; index++) {
            if (graphMatrix[node][index] == 1 && !visited[index]) {
                dfsRec(index);
            }
        }
    }

    //bfs traversal of graph
    void bFSTraversal(Queue<Integer> queue) {
        for (int index = 0; index < numberOfNodes; index++) {
            if (!visited[index]) {
                bfs(index, queue);
            }
        }
        for (int index = 0; index < numberOfNodes; index++) {
            visited[index] = false;
        }
    }

    void bfs(int node, Queue<Integer> queue) {
        visited[node] = true;
        queue.push(node);
        while (!queue.isEmpty()) {
            int top = queue.frontElement();
            queue.pop();
            System.out.print(top + " ");
            for (int index = 0; index < numberOfNodes; index++) {
                if (graphMatrix[top][index] == 1 && !visited[index]) {
                    queue.push(index);
                    visited[index] = true;
                }
            }
        }
    }

    //delete the edge 
    void deleteEdge(int source, int destination) {
        if (source >= numberOfNodes || destination >= numberOfNodes) {
            System.out.println("invalid Edges");
            return;
        }
        if (graphMatrix[source][destination] == 1) {
            graphMatrix[source][destination] = 0;
            numberOfEdges--;
        }
        if (graphMatrix[destination][source] == 1 && !directed) {
            graphMatrix[destination][source] = 0;
        }
    }

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter the number of node");
            int numberOfNode = input.nextInt();
            String type;
            boolean directed = false;
            do {
                System.out.println("Enter What type of Graph You want to design");
                System.out.println("1. Directed Graph");
                System.out.println("2. Undirected Graph");
                type = input.next();
                switch (type) {
                    case "1":
                        directed = true;
                        break;
                    case "2":
                        directed = false;
                        break;
                    default:
                        System.out.println("invalid Choice");
                        break;
                }
            } while (!type.equals("1") && !type.equals("2"));
            
            // object of Graph class
            Graph graph = new Graph(numberOfNode, directed);
            Queue<Integer> queue = new Queue<Integer>();
            String choices;
            do {
                System.out.println();
                System.out.println("Operations in Graph");
                System.out.println();
                System.out.println("1. Add edges");
                System.out.println("2. DFS Traversal");
                System.out.println("3. BFS Traversal");
                System.out.println("4. Display");
                System.out.println("5. Get number of Edges in Graph");
                System.out.println("6. Delete egdes");
                System.out.println("7. Exit ");
                System.out.println();
                System.out.println("Enter Your Choice");
                choices = input.next();
                System.out.println();
                switch (choices) {
                    case "1":
                        System.out.println("Enter the node where you add edge Source to Destination");
                        System.out.println("Enter Source node");
                        int Source = input.nextInt();
                        System.out.println("Enter destination node");
                        int destination = input.nextInt();
                        graph.addEdge(Source, destination);
                        break;

                    case "2":
                        System.out.println("DFS Traversal - ");
                        graph.dFSTraversal();
                        System.out.println();
                        break;

                    case "3":
                        System.out.println("BFS Traversal - ");
                        graph.bFSTraversal(queue);
                        System.out.println();
                        break;

                    case "4":
                        System.out.println("Adjency List Representation");
                        int graphMatrix[][] = graph.graphDisplay();
                        for (int index1 = 0; index1 < graph.numberOfNodes; index1++) {
                            System.out.print(index1 + "->");
                            for (int index2 = 0; index2 < graph.numberOfNodes; index2++) {
                                if (graphMatrix[index1][index2] == 1) {
                                    System.out.print(index2 + ",");
                                }
                            }
                            System.out.print("\n");
                        }
                        break;

                    case "5":
                        System.out.println();
                        System.out.println("number of edges in Graph is - " + graph.numberOfEdges);
                        break;

                    case "6":
                        System.out.println("Enter the Source and destination node where you want to delete a edge");
                        System.out.println("Enter the source node");
                        int source = input.nextInt();
                        System.out.println("Enter the destination node");
                        int dest = input.nextInt();
                        graph.deleteEdge(source, dest);
                        break;

                    case "7":
                        System.out.println("Thank you");
                        break;

                    default:
                        System.out.println("Inavalid choice");
                        break;
                }
            } while (!choices.equals("7"));
        } catch (InputMismatchException exception) {
            System.out.println(exception);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
