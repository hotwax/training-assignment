#include <iostream>
using namespace std;
// Create a Queue Data Structure which is Used in BFS Traversal
int vertices=0;
class Queue
{
public:
    int front, rear, capacity;
    int queue[1000];

    // Constructor to initialize all the class variables
    Queue(int capacity)
    {
        front = rear = 0;
        this->capacity = capacity ;
    }
    // Method to push the element in the Queue
    void Push(int data)
    {
        // if rear pointer equals to capacity of queue , then queue is full
        if (capacity == rear)
        {
            cout << "Queue is full" << endl;
            return;
        }

        else
        {
            queue[rear] = data;
            rear++;
        }
        return;
    }
    // Method to pop the element from the Queue
    void Pop()
    {
        // Queue is empty
        if (front == rear)
        {
            return;
        }
        else
        {
            // i = index
            for (int i = 0; i < rear - 1; i++)
            {
                queue[i] = queue[i + 1];
            }
            rear--;
        }
    }
    // Print Front of the queue
    int Front()
    {
        // Queue is empty
        if (front == rear)
        {
            return -1;
        }
        return queue[front];
    }
    bool Empty()
    {
        return front == rear;
    }
};
// Create Custom Graph DataStructure
class Graph
{
public:
    int vertices; // number of nodes/vertices in the Graph
    // create a Adjacencey matrix
    int **adjacencyMatrix;

    // Create a firstNode or source Node for DFS and BFS traversal
    int firstNode;
    Graph(int value)
    {
        vertices = value;
        adjacencyMatrix = new int *[value + 1];
        // i = index
        for (int i = 0; i <= value ; i++)
        {
            adjacencyMatrix[i] = new int[value];
        }
        firstNode = -1; // Store the first / Source Node
    }

    // function to add a edge in the graph
    void addEdge()
    {
        int node1, node2;
        cout << "Enter the Value of Vertex1" << endl;
        cin >> node1;
        cout << "Enter the Value of Vertex2" << endl;
        cin >> node2;

        if(node1>vertices)
        {
            cout<<"Vertex1 Value is Inavlid , Vertex value should be Between 0 to "<<vertices<<endl;
            return ;
        }
        if(node2>vertices)
        {
            cout<<"Value2 Value is Inavlid , Vertex value should be Between 0 to "<<vertices<<endl;
            return ;
        }

        adjacencyMatrix[node1][node2] = 1;
        adjacencyMatrix[node2][node1] = 1;

        if (firstNode == -1) // store source node
        {
            firstNode = node1;
        }
        cout<<"Edge Added"<<endl;
    }

    // Graph Traversal using BFS
    void bfs()
    {
        // made a visited array to mark nodes visited
        int visited[vertices + 1] = {0};

        // Create a Object of Queue Class
        Queue q(100);
        q.Push(firstNode);
        // mark source node visited
        visited[firstNode] = 1;

        while (!q.Empty())
        {
            int vertex = q.Front();
            cout << vertex << " ";
            q.Pop();
            // i = index
            for (int i = 0; i <= vertices; i++)
            {
                if (adjacencyMatrix[vertex][i] == 1 && !visited[i])
                {

                    // Push the adjacent node to the queue
                    q.Push(i);

                    // Set
                    visited[i] = 1;
                }
            }
        }
        cout << endl;
    }

    void DfsTraversal(int source, int visited[])
    {
        visited[source] = 1;
        cout << source << " ";

        for (int child = 0; child <= vertices; child++)
        {
            if (adjacencyMatrix[source][child] == 1 && !visited[child])
            {
                DfsTraversal(child, visited);
            }
        }
        return;
    }

    // Graph Traversal using DFS
    void dfs()
    {
        // made a visited array to mark nodes visited
        int visited[vertices + 1] = {0};
        DfsTraversal(firstNode, visited);
        cout << endl;
        return;
    }

    // function to delete a Edge from Graph
    void deleteEdge()
    {
        int node1, node2;
        cout << "Enter Node1 and Node2 whose edge between them is to be deleted" << endl;
        cin >> node1 >> node2;

        if (node1 > vertices || node2 > vertices)
        {
            cout << "Invalid Node Values" << endl;
            return;
        }
        // delete node by mark them 0 in matrix
        adjacencyMatrix[node1][node2] = 0;
        adjacencyMatrix[node2][node1] = 0;

        cout<<"Operation Successful"<<endl;
        return;
    }

    // Function to Display all the nodes in the Graph
    void display()
    {
        cout << "Your List is " << endl;
        for (int row = 0; row <= vertices; row++)
        {
            cout << row << "->";
            for (int column = 0; column <= vertices; column++)
            {
                if (adjacencyMatrix[row][column] == 1)
                {
                    cout << column << " ";
                }
            }
            cout << endl;
        }
    }
};

int main()
{
    
    cout << "Enter Number of vertices" << endl;
    cin >> vertices;

    // Create Instance of the Graph Class
    Graph g(vertices);
    int choice = 0;

    while (choice != 5)
    {
        cout << "1. Add a Edge" << endl;
        cout << "2. Traverse the Graph" << endl;
        cout << "3. Delete the Edge" << endl;
        cout << "4. Display" << endl;
        cout << "5. Exit" << endl;
        cout << "Enter Your Choice" << endl;
        cin >> choice;

        // Menu
        switch (choice)
        {
        case 1:
        {
            g.addEdge();
            break;
        }
        case 2:
        {
            cout << "Traversal Techniques" << endl;
            cout << "1. DFS" << endl;
            cout << "2. BFS" << endl;
            int choice1;
            cin >> choice1;
            if (choice1 == 1)
            {
                g.dfs();
            }
            else if (choice1 == 2)
            {
                g.bfs();
            }
            else
            {
                cout << "Invalid Choice" << endl;
            }
            break;
        }
        case 3:
        {
            g.deleteEdge();
            break;
        }
        case 4:
        {
            g.display();
            break;
        }
        case 5:
        {
            cout << "Exit" << endl;
            break;
        }
        default:
            cout<<"Invalid Choice"<<endl;
            break ;
        }
    }
    return 0;
}
