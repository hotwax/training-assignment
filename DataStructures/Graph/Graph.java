import java.util.Scanner;
import java.util.LinkedList;
import java.util.InputMismatchException;

public class Graph {
    private boolean adjMatrix[][];
    private int numVertices;

    // Initialize the matrix
    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjMatrix = new boolean[numVertices][numVertices];
    }

    // Add edges
    public void addEdge(int row, int col) {
        if (adjMatrix[row][col] == true) {
            System.out.println("Edge already exists between " + row + " and " + col);
            return;
        }
        adjMatrix[row][col] = true;
        adjMatrix[col][row] = true;
        System.out.println("Edge inserted succesfully");
    }

    // Remove edges
    public void removeEdge(int row, int col) {
        if (adjMatrix[row][col] == false) {
            System.out.println("No edge between " + row + " and " + col);
            return;
        }
        adjMatrix[row][col] = false;
        adjMatrix[col][row] = false;
        System.out.println("Edge deleted succesfully");
    }


    // dfs traversal
    public void dfs(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");
        for (int index = 0; index < numVertices; index++) {
            if (adjMatrix[vertex][index] && !visited[index]) {
                dfs(index, visited);
            }
        }
        System.out.println();
    }

    // bfs traversal
    public void bfs(int vertex, boolean[] visited) {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[vertex] = true;
        queue.add(vertex);
        while (!queue.isEmpty()) {
            vertex = queue.poll();
            System.out.print(vertex + " ");
            for (int index = 0; index < numVertices; index++) {
                if (adjMatrix[vertex][index] && !visited[index]) {
                    visited[index] = true;
                    queue.add(index);
                }
            }
        }
        System.out.println();
    }

    public void displayGraph() {
        for (int index = 0; index < numVertices; index++) {
            System.out.print(index + ": ");
            for (boolean val : adjMatrix[index]) {
                System.out.print((val ? 1 : 0) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("Enter the number of vertices");
        Scanner sc = new Scanner(System.in);
        Graph graph = new Graph(sc.nextInt());
        Boolean flag = true;
        boolean visited[] = new boolean[graph.numVertices];
        while (flag) {
            try {
                System.out.println("Select an option from the menu below");
                System.out.println("1. Insert an edge in the Graph");
                System.out.println("2. Delete an edge from the Graph");
                System.out.println("3. Run DFS Traversal");
                System.out.println("4. Run BFS Traversal");
                System.out.println("5. Exit");
                Scanner input = new Scanner(System.in);
                int choice = input.nextInt();
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
                        graph.removeEdge(v1, v2);
                        break;
                    case 3:
                        System.out.println("Enter the starting vertex");
                        int start = input.nextInt();
                        graph.dfs(start, visited);
                        break;
                    case 4:
                        System.out.println("Enter the starting vertex");
                        int start_v = input.nextInt();
                        graph.bfs(start_v, visited);
                        break;
                    case 5:
                        flag = false;
                        break;
                    default:
                        System.out.println("Please Choose a number from the menu");
                
                }
                graph.displayGraph();
                if (flag == false) {
                    System.out.println("Exiting the program");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid input");
            }
    }
    }
}
