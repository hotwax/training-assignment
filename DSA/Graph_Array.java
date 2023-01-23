import java.util.*;
public class Graph_Array {
    private int[][] adj;
    int V;
    public Graph_Array(int V){
        adj=new int[V][V];
        this.V=V;
    }
    public void addEdge(int a, int b){
        adj[a][b]=1;
        adj[b][a]=1;
    }
    public void deleteEdge(int a, int b){
        adj[a][b]=0;
        adj[b][a]=0;
    }
    //Traversals
    public void dfs(){
        boolean visited[]=new boolean[V];
        for(int i=0;i<V;i++){
            if(!visited[i]) dfs_main(i,visited);
        }
        System.out.println();
    }
    public void dfs_main(int s, boolean visited[]){
        visited[s]=true;
        System.out.print(s+" ");
        for(int v:adj[s]){
            if(v==1 && !visited[v]) dfs_main(v,visited);
        }
    }
    public void bfs(){
        boolean visited[]=new boolean[V];
        for(int i=0;i<V;i++){
            if(!visited[i])
            bfs_main(i);
        }
    }
    public void bfs_main(int s){
        Queue<Integer> q=new LinkedList<>();
        q.add(s);
        boolean visited[]=new boolean[V];
        visited[0]=true;
        while(!q.isEmpty()){
            int u=q.poll();
            System.out.print(u+" ");
            for(int v:adj[u])
                if(v==1 && !visited[v]){
                    q.add(v);
                    visited[v]=true;
                }
        }
        System.out.println();
    }
    public void menu(){
    Scanner sc=new Scanner(System.in);
    
    System.out.println("1: add Edge");
    System.out.println("2: remove Edge");
    System.out.println("3: bfs Traversal");
    System.out.println("4: dfs Traversal");
    System.out.println("5: terminate the program");
    boolean flag=true;
    while(flag){
      int n=sc.nextInt();
      switch(n){
        case 1:// addEdge
          System.out.println("enter node1 and node 2 of the edge you want to add");
          addEdge(sc.nextInt(),sc.nextInt());
          break;
         case 2:// removeEdge
             System.out.println("enter node1 and node 2 of the edge you want to delete");
             deleteEdge(sc.nextInt(),sc.nextInt());
             break;
         case 3:// bfs
             System.out.println("bfs traversal");
             bfs();
             break;
         case 4:// dfs
             System.out.println("dfs traversal");
             dfs();
             break;
         default:
             System.out.println("invalid input");
             break;
      }
    }
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of vertices");
        int V=sc.nextInt();
        MyGraph g=new MyGraph(V);
        g.menu();
        
    }
}
