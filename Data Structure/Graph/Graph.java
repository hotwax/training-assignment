import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.LinkedList;
public class Graph {
    private int Vertices;  //Vertices in graph
    private int array[][]; // Adjacency Matrix represeting graph
    //Constructor for intializing graph object
    public Graph(int vertices) {
        Vertices = vertices;
        array = new int[Vertices][Vertices];
    }
    //method to get adjacency marix of a graph
    public int[][] graphdis(){
        return array;
    }
    //method to get adjacency marix of a graph
    public void addEdge(int src, int dest) {
        array[src][dest]=1; // adding an edge from source to destination 
        array[dest][src]=1; // adding an edge from destination to source  
    }
    //method to remove an edge 
    public void removeEdge(int src, int dest) {
        array[src][dest]=0; // removing an edge from source to destination
        array[dest][src]=0; // removing an edge from destination to source
    }
    //method to print the graph
    public void printGraph() {
        for (int index = 0; index < Vertices; index++) {
            System.out.print(index+"->");
            for(int j=0;j<Vertices;j++){
                if( array[index][j]==1){
                    System.out.print(j+" ");
                }
            }
            System.out.println();
        }
    }
    //method to print DFS traversal of Graph
    public void DFS(boolean[] visited){
        
        for(int index=0;index<Vertices;index++){
            if(!visited[index]){
                dfs(index,visited);
            }
        }
        
    }

    public void dfs(int vertices, boolean[] visited) {
        visited[vertices] = true; // marking current vertice as visited
        System.out.print(vertices + " ");
            for(int j=0;j<Vertices;j++){
                if(array[vertices][j]==1 && !visited[j]){
                       visited[j]=true;   
                       dfs(j,visited);     
                }   
        }   
    }
    //method to print BFS traversal of Graph
    public void BFS(boolean[] vis){
    for(int index=0;index <Vertices;index++){
        if(!vis[index]){ //visiting unvisited vertices in Graph
            bfs(index,vis);
        }
    }
    
}
     
    public void bfs(int vertices,boolean[] vis) {
       
        LinkedList<Integer> queue = new LinkedList<Integer>();
        vis[vertices] = true; // marking current vertex as visited 
        queue.add(vertices); // adding current vertex in queue

        while (!queue.isEmpty()) { 
            vertices = queue.poll();
            System.out.print(vertices + " ");

            for(int index=0;index<Vertices;index++){
                for(int j=0;j<Vertices;j++){
                    if(array[index][j]==1 && !vis[j]){
                           vis[j]=true;
                           queue.add(j);        
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
            do{
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
                        boolean[] visited = new boolean[NumberVertices];
                        System.out.print("DFS traversal: ");
                        graph.DFS( visited);
                        break;
                    case 5:
                         boolean[] vis= new boolean[NumberVertices];
                        System.out.print("BFS traversal: ");
                        graph.BFS(vis);
                        break;
                    case 6:
                       System.exit(0);
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
            }while(choice!=6);
        }
        catch(InputMismatchException ex)
        {
        System.out.println(ex);
        }
    }
}