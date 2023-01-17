import java.util.*;

public class MyGraph {
    private List<List<Integer>> adj;
    int V;
    public MyGraph(int V){
        adj=new ArrayList<>();
        this.V=V;
        for(int i=0;i<V;i++){
            adj.add(new ArrayList<>());
        }
    }
    public void addEdge(int a, int b){
        adj.get(a).add(b);
        adj.get(b).add(a);
    }
    public void deleteEdge(int a, int b){
        adj.get(a).remove(b);
        adj.get(b).remove(a);
    }
    //Traversals
    public void dfs(){
        boolean visited[]=new boolean[V];
        dfs_main(0,visited);
        System.out.println();
    }
    public void dfs_main(int s, boolean visited[]){
        visited[s]=true;
        System.out.print(s+" ");
        for(int v:adj.get(s)){
            if(!visited[v]) dfs_main(v,visited);
        }
    }
    
    public void bfs(){
        Queue<Integer> q=new LinkedList<>();
        q.add(0);
        boolean visited[]=new boolean[V];
        visited[0]=true;
        while(!q.isEmpty()){
            int u=q.poll();
            System.out.print(u+" ");
            for(int v:adj.get(u))
                if(!visited[v]){
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
        MyGraph g=new MyGraph(5);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(1, 3);
        g.addEdge(3, 4);
        g.addEdge(2, 4);
        g.menu();
        
    }
}
