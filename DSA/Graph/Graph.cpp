#include <iostream>
#include <vector>
using namespace std;

class Graph {
private:
    int V;
    vector<vector<int>> adj;

public:
    Graph(int v) {
        V = v;
        adj.resize(V);
    }

    void addEdge(int u, int v) {
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    void removeEdge(int u, int v) {
        // find the position of v in the adjacency list of u
        int pos = -1;
        for (int i = 0; i < adj[u].size(); i++) {
            if (adj[u][i] == v) {
                pos = i;
                break;
            }
        }
        if (pos != -1) {
            adj[u].erase(adj[u].begin() + pos);
        }

        // find the position of u in the adjacency list of v
        pos = -1;
        for (int i = 0; i < adj[v].size(); i++) {
            if (adj[v][i] == u) {
                pos = i;
                break;
            }
        }
        if (pos != -1) {
            adj[v].erase(adj[v].begin() + pos);
        }
    }

    void display() {
        for (int u = 0; u < V; u++) {
            cout << u << " -> ";
            for (int i = 0; i < adj[u].size(); i++) {
                cout << adj[u][i] << " ";
            }
            cout << endl;
        }
    }
};

int main() {
    int V, choice, u, v;
    cout << "Enter the number of vertices: ";
    cin >> V;
    Graph g(V);

    do {
        cout << "\n1. Add edge\n2. Remove edge\n3. Display graph\n4. Quit\nEnter your choice: ";
        cin >> choice;
        switch (choice) {
            case 1:
                cout << "Enter the two vertices to connect: ";
                cin >> u >> v;
                g.addEdge(u, v);
                break;
            case 2:
                cout << "Enter the two vertices to disconnect: ";
                cin >> u >> v;
                g.removeEdge(u, v);
                break;
            case 3:
                g.display();
                break;
            case 4:
                break;
            default:
                cout << "Invalid choice\n";
        }
    } while (choice != 4);

    return 0;
} 
