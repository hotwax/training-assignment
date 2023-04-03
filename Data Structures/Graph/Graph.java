import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;


public class Graph {


    int adj[][];
    int n;


    Graph(int v)
    {
        adj= new int[v][v];
        this.n= v;
    }

    // Add edge between two vertices
    void addEdge(int to, int from)
    {  
        if (to>=n || from>=n || to<0 || from<0)
            System.out.println("Invalid vertices");
        else{
        adj[to][from]= 1;
        adj[from][to]= 1;}
    }


    // Remove edge between two vertices
    void removeEdge(int to, int from) {

        if (to>=n || from>=n|| to<0 || from<0)
            System.out.println("Invalid vertices");

        else if (adj[to][from]==0 || adj[from][to]==0)
            System.out.println("Edge does not exist");
        else{
        

        adj[to][from] = 0;
        adj[from][to] = 0;}
    }

    
    // Display adjacency matrix
    void display(){

        for(int i=0;i<n;i++) {

            for (int j=0;j<n;j++)
            {
                System.out.print(adj[i][j]+ " ");
                

            }
            System.out.println();

        }
    }

    // Perform DFS
    void dfs(int start, boolean visited[])
    {

        System.out.print(start + " ");
        visited[start]=true;

        for (int i=0;i<adj[start].length;i++)
        {

            if (adj[start][i]==1 && !visited[i]){
                dfs(i,visited);
            }


        }

    }

    // Perform BFS
    void bfs(int start)
    {

        List<Integer> q= new ArrayList<>();

        boolean visited[]= new boolean [n];
        Arrays.fill(visited,false );

        q.add(start);

        visited[start]=true;

        int vis;

        while( !q.isEmpty())
        {
            vis= q.get(0);

            System.out.print(vis +" ");

            q.remove(q.get(0));

            for(int i=0;i<n;i++){

                if (adj[vis][i]==1 && !visited[i]){

                    q.add(i);
                    visited[i]= true;

                }

            }



        }



    }


    public static void main(String[] args) {


        try{

        Scanner sc= new Scanner(System.in);

        System.out.println("Enter the number of vertices: ");
        int v= sc.nextInt();
        if (v<0)
            throw new Exception("Number of vertices cannot be negative");

        Graph g= new Graph(v);

        while(true){

            System.out.println("\n\n1. Add Edge \n2. Remove Edge \n3. Display Adjacency Matrix \n4. Perform DFS \n5. Perform BFS \n6. Exit ");

            System.out.println("\nEnter your choice: ");
            int ch= sc.nextInt();

            switch (ch){

                case 1:
                    System.out.println("\nEnter the vertices to add edge between: ");
                    int v1= sc.nextInt();
                    int v2= sc.nextInt();

                    g.addEdge(v1,v2);
                    break;

                case 2:
                System.out.println("\nEnter the vertices to remove edge between: ");
                int vt1= sc.nextInt();
                int vt2= sc.nextInt();

                g.removeEdge(vt1,vt2);
                break;

                case 3:
                    System.out.println("\nAdjacency Matrix: ");
                    g.display();
                    break;

                case 4:
                    System.out.println("\nEnter the starting vertex for DFS: ");
                    int start= sc.nextInt();
                    boolean visited[]= new boolean[v];
                    g.dfs(start,visited);
                    break;

                case 5:
                    System.out.println("\nEnter the starting vertex for BFS: ");
                    int startb= sc.nextInt();
                    g.bfs(startb);
                    break;

                case 6:
                    System.exit(0);
            }
        }
    }
    catch(InputMismatchException e){
        System.out.println("Invalid input, Please Enter a integer input. " );
        System.out.println("Exception: " + e + "");
    }

    catch(Exception e){
        System.out.println("Exception: " + e + "");
    }
}}
