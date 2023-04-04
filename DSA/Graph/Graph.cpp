#include <iostream>
#include <vector>
using namespace std;

class Graph {
private:
    int num_vertices;
    vector<vector<int>> adj_list;

public:
    // Constructor to initialize the graph with given number of vertices
    Graph(int vertices) {
        num_vertices = vertices;
        adj_list.resize(num_vertices);
    }

    // Function to add an undirected edge between two vertices
    void addEdge(int vertex1, int vertex2) {
        // Adding vertex2 to the adjacency list of vertex1
        adj_list[vertex1].push_back(vertex2);
        // Adding vertex1 to the adjacency list of vertex2
        adj_list[vertex2].push_back(vertex1);
    }

    // Function to remove an undirected edge between two vertices
    void removeEdge(int vertex1, int vertex2) {
        // Finding the position of vertex2 in the adjacency list of vertex1
        int position = -1;
        for (int i = 0; i < adj_list[vertex1].size(); i++) {
            if (adj_list[vertex1][i] == vertex2) {
                position = i;
                break;
            }
        }
        if (position != -1) {
            // Removing vertex2 from the adjacency list of vertex1
            adj_list[vertex1].erase(adj_list[vertex1].begin() + position);
        }

        // Finding the position of vertex1 in the adjacency list of vertex2
        position = -1;
        for (int i = 0; i < adj_list[vertex2].size(); i++) {
            if (adj_list[vertex2][i] == vertex1) {
                position = i;
                break;
            }
        }
        if (position != -1) {
            // Removing vertex1 from the adjacency list of vertex2
            adj_list[vertex2].erase(adj_list[vertex2].begin() + position);
        }
    }

    // Function to display the adjacency list of each vertex in the graph
    void display() {
        for (int vertex = 0; vertex < num_vertices; vertex++) {
            cout << vertex << " -> ";
            for (int i = 0; i < adj_list[vertex].size(); i++) {
                cout << adj_list[vertex][i] << " ";
            }
            cout << endl;
        }
    }
};

int main() {
    int num_vertices, choice, vertex1, vertex2;
    cout << "Enter the number of vertices: ";
    cin >> num_vertices;
    Graph graph(num_vertices);

    do {
        cout << "\n1. Add edge\n2. Remove edge\n3. Display graph\n4. Quit\nEnter your choice: ";
        cin >> choice;
        switch (choice) {
            case 1:
                cout << "Enter the two vertices to connect: ";
                cin >> vertex1 >> vertex2;
                graph.addEdge(vertex1, vertex2);
                break;
            case 2:
                cout << "Enter the two vertices to disconnect: ";
                cin >> vertex1 >> vertex2;
                graph.removeEdge(vertex1, vertex2);
                break;
            case 3:
                graph.display();
                break;
            case 4:
                break;
            default:
                cout << "Invalid choice\n";
        }
    } while (choice != 4);

    return 0;
}
