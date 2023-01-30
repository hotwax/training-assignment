import java.util.Scanner;

public class Graph {
    private boolean adjMatrix[][];
    private int numVertices;

    // Initialize the matrix
    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjMatrix = new boolean[numVertices][numVertices];
    }

    // Add edges
    public void addEdge(int i, int j) {
        adjMatrix[i][j] = true;
        adjMatrix[j][i] = true;
    }

    // Remove edges
    public void removeEdge(int i, int j) {
        adjMatrix[i][j] = false;
        adjMatrix[j][i] = false;
    }

    public void displayGraph() {
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + ": ");
            for (boolean j : adjMatrix[i]) {
                System.out.print((j ? 1 : 0) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Graph graph = new Graph(input.nextInt());
        Boolean flag = true;
        while (flag) {
            System.out.println("Select an option from the menu below");
            System.out.println("1. Insert an edge in the Graph");
            System.out.println("2. Delete an edge from the Graph");
            System.out.println("3. Exit");

            int choice = input.nextInt();
            if (choice < 1 || choice > 4) {
                System.out.println("Invalid choice");
            } else {
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
                        flag = false;
                        break;
                }
                graph.displayGraph();
                if (flag == false) {
                    System.out.println("Exiting the program");
                }
            }
        }
    }

}
