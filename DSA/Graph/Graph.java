package com.java.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Queue;
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
    void addedge(int source, int destination) {
        matrix[source][destination] = 1;
        matrix[destination][source] = 1;
    }

    // method to print graph matrix
    void printgraphmaxtrix() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // method to traverse a graph in breadth first search manner
    void bfs(int start) {
        boolean visit[] = new boolean[vertices];
        Arrays.fill(visit, false);
        List<Integer> q = new ArrayList<Integer>();
        q.add(start);
        visit[start] = true;
        int queue_val;
        while (!q.isEmpty()) {
            queue_val = q.get(0);
            q.remove(0);
            System.out.print(queue_val + " ");
            for (int i = 0; i < vertices; i++) {
                if (matrix[queue_val][i] == 1 && visit[i] == false) {
                    q.add(i);
                    visit[i] = true;
                }
            }

        }

    }

    // method to traverse a graph in depth first search manner
    void dfs(int start) {
        boolean visit[] = new boolean[vertices];
        Arrays.fill(visit, false);
        dfs_helper(start, visit);

    }

    // method to recursively visit to each node and mark it as visit
    void dfs_helper(int start, boolean visit[]) {
        System.out.print(start + " ");
        visit[start] = true;
        for (int i = 0; i < vertices; i++) {
            if (visit[i] == false) {
                dfs_helper(i, visit);
            }
        }
    }

    // to delete the edge in a graph
    void delete_edge(int position_1, int position_2) {
        matrix[position_1][position_2] = 0;
        matrix[position_2][position_1] = 0;
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

                                gp.addedge(a, b);

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
                            gp.delete_edge(node_1, node_2);
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
