public class Graph{
    static class Node{
        int data;
        Node next;
        Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    static class Edge{
        int src;
        int dest;
        Edge(int src, int dest){
            this.src = src;
            this.dest = dest;
        }
    }

        int V;
        Node[] adjList;
        Graph(int V){
            this.V = V;
            adjList = new Node[V];
            for(int i = 0; i < V; i++){
                adjList[i] = null;
            }
        }
        public void addEdge(int src, int dest){
            Node newNode = new Node(dest);
            newNode.next = adjList[src];
            adjList[src] = newNode;
            newNode = new Node(src);
            newNode.next = adjList[dest];
            adjList[dest] = newNode;
        }
        public void printGraph(){
            for(int i = 0; i < V; i++){
                Node temp = adjList[i];
                System.out.print("Vertex " + i + " is connected to: ");
                while(temp != null){
                    System.out.print(temp.data + " ");
                    temp = temp.next;
                }
                System.out.println();
            }
        }

    public static void main(String[] args){
        int V = 5;
        Graph graph = new Graph(V);
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.printGraph();
    }
}
