#include <bits/stdc++.h>
using namespace std;

// printing function

void print(string message)
{
    cout << endl
         << message;
}

// Graph Class

class Graph
{
public:
    // Using adjency list for making this graph

    map<int, vector<int>> adj;
    int noOfNodes;

    // Constructor

    Graph(int nodes)
    {
        this->noOfNodes = nodes;
    }

    // For adding a edge

    void addEdge()
    {
        // Taking input from the user
        int node1;
        print("Enter the node1: ");
        cin >> node1;

        int node2;
        print("Enter the node2: ");
        cin >> node2;
        // Checking the nodes exist or not
        if (node1 < 0 || node1 >= this->noOfNodes)
        {
            print("Invalid Value.");
            return;
        }
        if (node2 < 0 || node2 >= this->noOfNodes)
        {
            print("Invalid Value.");
            return;
        }
        // If Node1 and Node2 are same do nothing. Treating a mistake by user
        if (node1 == node2)
        {
            return;
        }

        // check if edge already exist in node1 if not add it
        bool existInNode1 = false;
        for (auto i : adj[node1])
        {
            if (i == node2)
            {
                existInNode1 = false;
            }
        }

        if (!existInNode1)
        {
            adj[node1].push_back(node2);
        }
    }

    // To remove a edge

    void removeEdge()
    {
        // Taking input from the user

        int node1;
        print("Enter the node1: ");
        cin >> node1;

        int node2;
        print("Enter the node2: ");
        cin >> node2;
        // Checking the nodes exist or not
        if (node1 < 0 || node1 >= this->noOfNodes)
        {
            print("Invalid Value.");
            return;
        }
        if (node2 < 0 || node2 >= this->noOfNodes)
        {
            print("Invalid Value.");
            return;
        }
        // If Node1 and Node2 are same do nothing. Treating a mistake by user
        if (node1 == node2)
        {
            return;
        }

        // Removing the edge
        for (int i = 0; i < adj[node1].size(); i++)
        {
            if (adj[node1][i] == node2)
            {
                adj[node1].erase(adj[node1].begin() + i);
            }
        }

        return;
    }

    // Function to display the graph in adjacency list

    void display()
    {
        cout << endl;

        for (auto i : adj)
        {
            cout << i.first << " -> ";
            for (auto j : i.second)
            {
                cout << j << " ";
            }
            cout << endl;
        }
    }

    // BFS

    void bfs()
    {

        if (adj.size() == 0)
        {
            return;
        }

        print("BFS is -");

        // declaring an unordered map for marking visited
        unordered_map<int, bool> visited;
        for (auto i : adj)
        {

            // if not present in visited

            if (!visited[i.first])
            {

                queue<int> q;
                int front = i.first;
                q.push(front);
                visited[front] = 1;

                while (!q.empty())
                {
                    front = q.front();

                    // printing value

                    cout << front << " ";

                    // removing it from queue

                    q.pop();

                    // adding all the neighbouring edge in queue

                    for (auto i : adj[front])
                    {
                        if (!visited[i])
                        {
                            q.push(i);
                            visited[i] = 1;
                        }
                    }
                }
            }
        }
    }

    // DFS

    void recursiveFunctionForDfs(unordered_map<int, bool> &visited, int node)
    {
        cout << node << " ";
        visited[node] = 1;
        for (auto listItem : adj[node])
        {
            if (!visited[listItem])
            {
                recursiveFunctionForDfs(visited, listItem);
            }
        }
    }

    // dfs main function

    void dfs()
    {

        cout << endl
             << "DFS is -- ";
        unordered_map<int, bool> visited;

        for (auto i : adj)
        {
            if (!visited[i.first])
            {
                recursiveFunctionForDfs(visited, i.first);
            }
        }
    }
};

int main()
{

    int nodes;

    print("Enter the no. of nodes.");

    cin >> nodes;

    // Creating Graph Object

    Graph g(nodes);

    // while for menu --

    while (true)
    {
        print("Your Directed Graph -- ");
        g.display();

        cout << endl;

        print("Enter 1 for add edge operation");
        print("Enter 2 for remove edge operation");
        print("Enter 3 for BFS Traversal operation");
        print("Enter 4 for DFS Traversal operation");

        print("Enter 5 to exit");
        cout << endl;
        int input;
        cin >> input;
        int data;

        // On different input cases

        switch (input)
        {
        case 1:
            g.addEdge();
            break;
        case 2:
            g.removeEdge();
            break;
        case 3:
            g.bfs();
            break;
        case 4:
            g.dfs();
            break;

        default:
            return 0;
        }
    }
}