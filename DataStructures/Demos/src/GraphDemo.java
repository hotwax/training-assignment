import java.util.Scanner; 

class GraphDemo
{
 static Scanner scanner= new Scanner(System.in);
 static Graph gh;
 public static void main(String args[])
 {
  System.out.println("Enter number of Nodes:");
  int numOfNodes=scanner.nextInt();
  gh= new Graph(numOfNodes);
  try{
    boolean flag=true;
    while(flag)
    {
    System.out.println("Enter a choice:");
    System.out.println("[1] Insert edge");
    System.out.println("[2] Delete edge");
    System.out.println("[3] DFS traversal");
    System.out.println("[4] BFS traversal");
    System.out.println("[0] Exit");
    System.out.print("Your Input(press enter when done):");
    int selected = scanner.nextInt();

    switch(selected)
    {
      case 1: addEdge(); break;
      case 2: deleteEdge(); break;
      case 3: dfs(); break;
      case 4: bfs(); break;
      case 0: flag=false; break;
      default: defaultMessage(); break;
    }
   }
   }
   catch(Exception e)
   {
    System.out.print(e.getMessage());
   }
   
  }
 public static void addEdge()throws Exception{
   System.out.println("Enter the edge pair:");
   int node1=scanner.nextInt();
   int node2=scanner.nextInt();
   gh.addEdge(node1,node2);
   System.out.println("edge added successfully.");
 }
 public static void deleteEdge() throws Exception {
   System.out.println("Enter the edge pair:");
   int node1=scanner.nextInt();
   int node2=scanner.nextInt();
   gh.deleteEdge(node1,node2);
   System.out.println("edge deleted successfully.");
 }
 
 public static void dfs()throws Exception{
  int val[]=gh.dfs();
  System.out.print("[");
  for(int i=0;i<val.length;i++)
  {
   System.out.print(" "+val[i]);
   if(i!=val.length-1) System.out.print(",");
  }
  System.out.println(" ]");
 }
 public static void bfs()throws Exception{
  int val[]=gh.bfs();
  System.out.print("[");
  for(int i=0;i<val.length;i++)
  {
   System.out.print(" "+val[i]);
   if(i!=val.length-1) System.out.print(",");
  }
  System.out.println(" ]");
 }            
 public static void defaultMessage(){
  System.out.println("Invalid Input, try again");
 }

}