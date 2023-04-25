import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Graph {

    // to store number of node or vertices in a graph
    int vertices;
    // to store edge in a 2d matrix
    int matrix[][];

    // constructor
    Graph(int vertices) {
        this.vertices = vertices;
        matrix = new int[vertices + 1][vertices + 1];
    }

    // method to add edge in a graph
    void addEdge(int source, int destination) {
        matrix[source][destination] = 1;
        matrix[destination][source] = 1;
    }

    // method to print graph matrix
    void printGraphMaxtrix() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // method to traverse a graph in breadth first search manner
    void bfs(int start) {
        try {
            boolean visit[] = new boolean[vertices];
            Arrays.fill(visit, false);
            // List<Integer> q = new ArrayList<Integer>();
            Queue q = new Queue(vertices + 1);
            q.enQueue(start);
            visit[start] = true;
            int queue_val;
            while (!q.empty()) {
                queue_val = q.peak();
                q.deQueue();
                System.out.print(queue_val + " ");
                for (int i = 0; i < vertices; i++) {
                    if (matrix[queue_val][i] == 1 && visit[i] == false) {
                        q.enQueue(i);
                        visit[i] = true;
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("Enter correct node value   " + e);
        }

    }

    // method to traverse a graph in depth first search manner
    void dfs(int start) {
        boolean visit[] = new boolean[vertices];
        Arrays.fill(visit, false);
        dfsHelper(start, visit);

    }

    // method to recursively visit to each node and mark it as visit
    void dfsHelper(int start, boolean visit[]) {
        try {
            System.out.print(start + " ");
            visit[start] = true;
            for (int i = 0; i < matrix[start].length; i++) {
                if (matrix[start][i] == 1 && visit[i] == false) {
                    dfsHelper(i, visit);
                }
            }
        } catch (Exception e) {
            System.out.println("Enter correct node value   " + e);
        }
    }

    // to delete the edge in a graph
    void deleteEdge(int position_1, int position_2) {
        try {
            matrix[position_1][position_2] = 0;
            matrix[position_2][position_1] = 0;
        } catch (Exception e) {
            System.out.println("Kindly entered the correct value of edge");
        }
    }

    public static void main(String[] args) {
        // taking input from user
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of vertices");
        int v = sc.nextInt();

        int u;

        // Creating Graph class object "gp"
        Graph gp = new Graph(v);

        boolean flag = true;
        // running the while loop till flag is false
        while (flag) {
            System.out.println("Choose an option from the Dashboard given below:\n");
            System.out.println("1. Insert ");
            System.out.println("2. BFS Traversal");
            System.out.println("3. DFS Traversal");
            System.out.println("4. Delete ");
            System.out.println("5. EXIT");
            System.out.println();

            int ch = sc.nextInt();
            if (ch < 1 || ch > 5) {
                System.out.println("Kindly enter the correct option.");
            } else {
                // Taking the choice from the user and performing the corresponding operation
                try {
                    switch (ch) {
                        case 1:

                            System.out.println("Enter the number of edges");
                            u = sc.nextInt();

                            for (int i = 0; i < u; i++) {
                                System.out.println("Enter the source vertex");
                                int a = sc.nextInt();
                                System.out.println("Enter the destination vertex");
                                int b = sc.nextInt();

                                gp.addEdge(a, b);

                            }

                            break;

                        case 2:
                            System.out.println("Enter the starting node value");
                            int start_position = sc.nextInt();
                            System.out.print("BFS Traversal  ");
                            gp.bfs(start_position);
                            System.out.println();
                            break;

                        case 3:
                            System.out.println("Enter the starting node value");
                            int start = sc.nextInt();
                            System.out.print("DFS Traversal  ");
                            gp.dfs(start);
                            System.out.println();
                            break;

                        case 4:
                            System.out.println("Enter the two nodes which you want to delete the edge between");
                            int node_1 = sc.nextInt();
                            int node_2 = sc.nextInt();
                            gp.deleteEdge(node_1, node_2);
                            System.out.println("Node edge deleted");
                            break;

                        case 5:
                            flag = false;
                            break;

                    }

                    if (flag == false) {
                        System.out.println("Exiting program");
                    }
                } catch (InputMismatchException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        }

    }

}
