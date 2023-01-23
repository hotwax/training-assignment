public class Graph
{
 private int adj[][];
 private int numberOfNodes;
 public int[] tmp;
 private int curr;
 public void addEdge(int node1,int node2)throws Exception
 {
  if(node1>=numberOfNodes||node2>=numberOfNodes)
   throw new Exception("Invalid node");

  adj[node1][node2]=1;
  adj[node1][node2]=1;
 }
 public void deleteEdge(int node1,int node2)throws Exception
 {
  if(node1>=numberOfNodes||node2>=numberOfNodes)
   throw new Exception("Invalid node");

  adj[node1][node2]=0;
  adj[node1][node2]=0;
 }
 public void dfsRecursive(int start, boolean visited[])
 {
  visited[start]=true;
  tmp[curr]=start;
  curr++;
  for(int v:adj[start])
   if(!visited[v])
    dfsRecursive(v,visited);
 }
 public int[] dfs() throws Exception
 {
  boolean[] visited = new boolean[numberOfNodes];
  curr=0;
  tmp=new int[numberOfNodes];
  for(int i=0;i<numberOfNodes;i++)
   if(!visited[i])
    dfsRecursive(i,visited);
  return tmp;
 }
 public void bfsRecursive(int start) throws Exception
 {
  Queue q= new Queue();
  q.enqueue(start);
  boolean visited[]=new boolean[numberOfNodes];
  visited[0]=true;
  while(q.size()>0)
  {
   int u=q.back();
   q.dequeue();
   tmp[curr]=u;
   curr++;
   for(int v:adj[u])
   {
    if(v==1&&!visited[v])
    {
     q.enqueue(v);
     visited[v]=true;
    } 
   }
  }
 }
 public int[] bfs() throws Exception
 {
  boolean visited[]=new boolean[numberOfNodes];
  tmp=new int[numberOfNodes];
  curr=0;
  for(int i=0;i<numberOfNodes;i++)
   if(!visited[i])
    bfsRecursive(i);
  return tmp;
 }


 public Graph(int numberOfNodes)
 {
  this.numberOfNodes=numberOfNodes;
  this.adj=new int[numberOfNodes][numberOfNodes];
 }
}