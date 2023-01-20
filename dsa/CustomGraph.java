import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class CustomGraph{

  static ArrayList<Integer>[] graph;

  public static void makeArray(int n){
    graph = new ArrayList<>[n];

    for (int i = 0; i < n; i++) {
      graph[i]=new ArrayList<>();
    }
  }
  

  public static void display(){
     for (int i = 0; i < n; i++) {
       System.out.print(i+": ");
       for (Integer v : graph[i]) {
         System.out.println(v+" ");
       }
       System.out.println();
     }
  }

  public static void addEdge(int v1, int v2){
    graph[v1].add(v2);
    graph[v2].add(v1);
  }

  public static void removeEdge(int v1, int v2){
    graph[v1].remove(v2);
    graph[v2].remove(v1);
  }

  public static void dfs(ArrayList<Integer>[] graph, int v, boolean[] visited){
    System.out.print(v+" ");  
    for (Integer nbr : graph[v]) {
      if(!visited[nbr]) dfs(graph, nbr, visited);
    }
  }

  public static void bfs(int src, int noofnodes){
    Queue<Integer> q = new ArrayDeque<>();
    boolean[] visited = new boolean[noofnodes];
    q.add(src);

    while (q.size()>0) {
      Integer rem = q.remove();
      System.out.print(rem);

      for (Integer nbr : graph[rem]) {
        if(!visited[nbr]) q.add(nbr);
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the number of nodes: ");
    int noofnodes=sc.nextInt();
    makeArray(noofnodes);

    while (true) {
      System.out.println("Your graph: ");
      display();
      
      System.out.println("Enter 1 to add edge");
      System.out.println("Enter 2 to remove edge");
      System.out.println("Enter 3 to apply dfs");
      System.out.println("Enter 4 to apply bfs");
      System.out.println("Enter 5 to end the program");

      int n=sc.nextInt();

      if (n==1) {
        System.out.println("Nodes: ");
        int v1=sc.nextInt(), v2=sc.nextInt();

        addEdge(v1, v2);
      } else if(n==2){
        System.out.println("Nodes: ");
        int v1=sc.nextInt(), v2=sc.nextInt();

        remooveEdge(v1, v2);
      } else if(n==3){
        System.out.println("Starting point: ");
        int src=sc.nextInt();
        dfs(graph, src, new boolean[noofnodes]);
      } else if(n==4){
        System.out.println("Starting point: ");
        int src=sc.nextInt();
        bfs(src, noofnodes);
      } else{
        return;
      }
    }

    

    


  }


}