import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.InputMismatchException;

class GraphMatrix {
    // Variable to store the number of vertices
    int[][] matrix;
    // Variable to store the number of vertices
    int vertex;
    // Constructor to initialize the Graph
    GraphMatrix(int vertex) {
        this.vertex = vertex;
        matrix = new int[vertex][vertex];
    }
    // Method to create new Graph
    public void createGraph(int vertex){
        this.vertex = vertex;
        matrix = new int[vertex][vertex];
    }

    // Method to add an edge
    public void addEdge(int vertex, int vertex1) {
        matrix[vertex][vertex1] = 1;
        matrix[vertex1][vertex] = 1;
    }

    // Method to delete an edge
    public void deleteEdge(int vertex, int vertex1) {
        matrix[vertex][vertex1] = 0;
        matrix[vertex1][vertex] = 0;
    }

    // Method to perform the BFS
    public void breadthFirstSearch() {
        boolean[] visited = new boolean[vertex];
        for (int node = 0; node < vertex; node++) {
            if (!visited[node]) {
                bfsUtil(node);
            }
        }
    }

    // Recursive function to perform the BFS
    private void bfsUtil(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        boolean[] visited = new boolean[vertex];
        visited[start] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
            for (int neighbour : matrix[node]) {
                if ( neighbour == 1 && !visited[neighbour]) {
                    queue.add(neighbour);
                    visited[neighbour] = true;
                }
            }
        }
        System.out.println();
    }

    // Method to perform the DFS
    public void depthFirstSearch() {
        boolean[] visited = new boolean[vertex];
        for (int node = 0; node < vertex; node++) {
            if (!visited[node]) {
                dfsUtil(node, visited);
            }
        }
        System.out.println();
    }

    // Recursive function to perform the DFS
    void dfsUtil(int start, boolean[] visited) {
        visited[start] = true;
        System.out.print(start + " ");
        for (int neighbour : matrix[start]) {
            if (!visited[neighbour]) {
                dfsUtil(neighbour, visited);
            }
        }
    }
}
public class Graph{
        public static void main (String[]args){
            //creating choice variable to store the user choice
        int choice=0;
        // creating default graph with 1 vertex
        GraphMatrix graphTest= new GraphMatrix(1);
        do{
        // Printing the menu for the user
        System.out.println("--------- MENU ---------");
        System.out.println("Press 0 : Create a New Graph");
        System.out.println("Press 1 : Add a new Edge");
        System.out.println("Press 2 : Delete a Node");
        System.out.println("Press 3 : Print the BFS Traversal");
        System.out.println("Press 4 : Print the DFS Traversal");
        System.out.println("Press 5 : Exit");
        System.out.println("Enter your choice : ");
        try{
        Scanner sc=new Scanner(System.in);

        choice=sc.nextInt();
        // Taking the choice from the user and performing the corresponding operation
        switch(choice){
        case 0:
            System.out.print("Please Enter the size of the Graph: ");
            graphTest.createGraph(sc.nextInt());
            break;
        case 1:
            System.out.print("Please Enter the Edge Values: ");
            graphTest.addEdge(sc.nextInt(),sc.nextInt());
            break;
        case 2:
            System.out.print("Please Enter the Edge Values: ");
            graphTest.deleteEdge(sc.nextInt(),sc.nextInt());
            break;
        case 3:
            System.out.println("BFS Traversal: ");
            graphTest.breadthFirstSearch();
            break;
        case 4:
            System.out.println("DFS Traversal: ");
            graphTest.depthFirstSearch();
            break;
        case 5:
            System.out.println("Program Terminated Successfully");
            break;
        default:
            System.out.println("Invalid Input");
            }
        } catch(InputMismatchException e){
                System.out.println("Please Enter Integer Only!!");
                }
            } while(choice!=5);
        }
}

