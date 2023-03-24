package Graph;

import java.util.InputMismatchException;
import java.util.Scanner;

import Queue.Queue;

public class Graph {
    int numberofnodes;
    private boolean vis[];
    private boolean directed;
    private int arr[][];
    int numberofedges;

    Graph(int noofnodes, boolean dir) {
        this.numberofnodes = noofnodes;
        this.directed = dir;
        arr = new int[numberofnodes][numberofnodes];
        vis = new boolean[numberofnodes];
        numberofedges = 0;

    }

    void Addedge(int source, int destination) {
        if (destination >= numberofnodes || source >= numberofnodes) {
            System.out.println("invalid Edges");
            return;
        }
        if (source != destination && arr[source][destination] == 0) {
            if (directed) {
                arr[source][destination] = 1;
                numberofedges++;
            } else {

                arr[source][destination] = 1;
                arr[destination][source] = 1;
                numberofedges++;
            }
        }
    }

    int[][] graphDisplay() {
        return arr;
    }

    void DFS() {

        for (int i = 0; i < numberofnodes; i++) {
            if (!vis[i]) {
                dfsrec(i);
            }
        }

        for (int i = 0; i < numberofnodes; i++) {
            vis[i] = false;
        }

    }

    void dfsrec(int node) {

        System.out.print(node + " ");
        vis[node] = true;

        for (int j = 0; j < numberofnodes; j++) {

            if (arr[node][j] == 1 && !vis[j]) {
                dfsrec(j);
            }

        }

    }

    void BFS(Queue<Integer> q) {

        for (int i = 0; i < numberofnodes; i++) {
            if (!vis[i]) {
                bfs(i, q);
            }
        }

        for (int i = 0; i < numberofnodes; i++) {
            vis[i] = false;
        }

    }

    void bfs(int node, Queue<Integer> q) {

        vis[node] = true;
        q.push(node);

        while (!q.isempty()) {
            int top = q.Front();
            q.pop();
            System.out.print(top + " ");
            for (int j = 0; j < numberofnodes; j++) {
                if (arr[top][j] == 1 && !vis[j]) {
                    q.push(j);
                    vis[j] = true;
                }

            }

        }

    }

    void deletededge(int source, int destination) {
        if (source >= numberofnodes || destination >= numberofnodes) {
            System.out.println("invalid Edges");
            return;
        }

        if (arr[source][destination] == 1 && directed) {
            arr[source][destination] = 0;
            numberofedges--;
        } else if (arr[source][destination] == 1 && !directed) {
            arr[source][destination] = 0;
            arr[destination][source] = 0;
            numberofedges--;
        }

    }

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter the number of node");
            int numberofnode = input.nextInt();
            int type;
            boolean dir = false;
            do {
                System.out.println("Enter What type of Graph You want to design");
                System.out.println("1. Directed Graph");
                System.out.println("2. Undirected Graph");
                type = input.nextInt();
                switch (type) {
                    case 1:
                        dir = true;
                        break;
                    case 2:
                        dir = false;
                        break;
                    default:
                        System.out.println("invalid Choice");
                        break;
                }
            } while (type != 1 && type != 2);
            Graph graph = new Graph(numberofnode, dir);
            Queue<Integer> queue = new Queue<Integer>();

            int choices;

            do {
                System.out.println();
                System.out.println("Operations in Graph");
                System.out.println();
                System.out.println("1. Add edges");
                System.out.println("2. DFS Traversal");
                System.out.println("3. BFS Traversal");
                System.out.println("4. Display");
                System.out.println("5. Get number of Edges in Graph");
                System.out.println("6. Delete egdes");
                System.out.println("7. Exit ");
                System.out.println();
                System.out.println("Enter Your Choice");
                choices = input.nextInt();
                System.out.println();
                switch (choices) {
                    case 1:
                        System.out.println("Enter the node where you add edge Source to Destination");
                        System.out.println("Enter Source node");
                        int Source = input.nextInt();
                        System.out.println("Enter destination node");
                        int destination = input.nextInt();
                        graph.Addedge(Source, destination);
                        break;

                    case 2:
                        System.out.println("DFS Traversal - ");

                        graph.DFS();
                        System.out.println();
                        break;

                    case 3:
                        System.out.println("BFS Traversal - ");

                        graph.BFS(queue);
                        System.out.println();
                        break;

                    case 4:
                        System.out.println("Adjency List Representation");
                        int arr[][] = graph.graphDisplay();

                        for (int i = 0; i < graph.numberofnodes; i++) {
                            System.out.print(i + "->");
                            for (int j = 0; j < graph.numberofnodes; j++) {
                                if (arr[i][j] == 1) {
                                    System.out.print(j + ",");
                                }
                            }
                            System.out.print("\n");
                        }
                        break;

                    case 5:
                        System.out.println();
                        System.out.println("number of edges in Graph is - " + graph.numberofedges);
                        break;

                    case 6:
                        System.out.println("Enter the Source and destination node where you want to delete a edge");
                        System.out.println("Enter the source node");
                        int s = input.nextInt();
                        System.out.println("Enter the destination node");
                        int d = input.nextInt();
                        graph.deletededge(s, d);

                        break;

                    case 7:
                        System.out.println("Thank you");
                        break;

                    default:
                        System.out.println("Inavalid choice");
                        break;

                }
            } while (choices != 7);
        } catch (InputMismatchException exception) {
            System.out.println(exception);
        }

    }

}
