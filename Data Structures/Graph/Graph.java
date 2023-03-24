import java.util.*;

public class Graph {


    int adj[][];
    int n;


    Graph(int v)
    {
        adj= new int[v][v];
        this.n= v;
    }

    void addEdge(int to, int from)
    {  
        adj[to][from]= 1;
        adj[from][to]= 1;
    }

    void removeEdge(int to, int from) {

        if (adj[to][from]==0 || adj[from][to]==0)
            System.out.println("Edge does not exist");
        

        adj[to][from] = 0;
        adj[from][to] = 0;
    }

    

    void display(){

        for(int i=0;i<n;i++) {

            for (int j=0;j<n;j++)
            {
                System.out.print(adj[i][j]+ " ");
                

            }
            System.out.println();

        }
    }


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

        Scanner sc= new Scanner(System.in);

        System.out.println("Enter the number of vertices: ");
        int v= sc.nextInt();

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
}
