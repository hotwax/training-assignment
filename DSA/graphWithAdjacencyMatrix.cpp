#include <bits/stdc++.h>
using namespace std;

// Queue

// Creating Node Class for queue

class Node
{
public:
    int data;
    Node *next;

    Node(int data)
    {
        this->data = data;
        next = NULL;
    }
};

// Creating queue class with its functions

class Queue
{
public:
    Node *front;
    Node *rear;

    Queue()
    {
        front = NULL;
        rear = NULL;
    }

    // for checking if empty

    bool isEmpty(){
        if(!front){
            return true;
        }
        return false;
    }

    // for enqueue operation

    void enqueue(int data)
    {

        Node *newNode = new Node(data);

        if (rear == NULL)
        {
            rear = front = newNode;
            return;
        }

        rear->next = newNode;
        rear = newNode;
    }

    // Function for dequeue Operation

    int dequeue()
    {
        if (front == NULL)
        {
            return;
        }

        Node *temp = front;
        front = front->next;

        if (front == NULL)
        {
            rear = NULL;
        }
        int val = temp->data;
        delete temp;
        return val;
    }

    // Search Function

    void search(int data)
    {
        Node *temp = front;

        while (temp != NULL)
        {
            if (temp->data == data)
            {
                cout << endl
                     << "Present";
                return;
            }
            temp = temp->next;
        }

        print("Not Present");
    }
};


// A printing function

void print(string message){
    cout<<endl<<message;
}

// Creating Directed Graph with adjacency Matrix

class Graph
{
    int **matrix;  // Matrix of graph
    int noOfNodes; // size of matrix

    Graph(int size)
    {
        this->noOfNodes = size;

        // Initializing the list

        this->matrix = new int *[this->noOfNodes];

        for (int index = 0; index < this->noOfNodes; index++)
        {
            matrix[index] = new int[this->noOfNodes];
        }

        for(int row = 0; row < this->noOfNodes; row++){
            for(int col = 0; col < this->noOfNodes; col++){
                matrix[row][col] = 0;
            }
        }
    }

    // function to add edge

    void addEdge(){
        // Taking input from user
        print("Enter the Node 1 : ");
        int node1;
        cin>>node1;

        print("Enter the node2 : ");
        int node2;
        cin>>node2;

        // creating an edge

        matrix[node1][node2] = 1;
    }

    // function to delete edge

    void deleteEdge(){
        // Taking input from user
        print("Enter the Node 1 : ");
        int node1;
        cin>>node1;

        print("Enter the node2 : ");
        int node2;
        cin>>node2;

        // creating an edge

        matrix[node1][node2] = 0;
    }

    // BFS traversal


    void bfs(){

        print("Bfs Traversal - ");

        // Declaring and initializing visitedArray for marking node visited

        bool *visitedArray = new bool[this->noOfNodes];

        for(int index=0; index<this->noOfNodes; index++){
            visitedArray[index] = 0;
        }

        // BFS traversal for directed graph

        for(int node = 0; node < this->noOfNodes; node++){

            if(!visitedArray[node]){
                // Queue with my own declared class
                Queue q;
                q.enqueue(node);
                visitedArray[node] = true;

                while(!Queue.isEmpty()){
                    int front = Queue.dequeue();
                    cout<<" "<<front;

                    for(int col = 0; col<this->noOfNodes; col++){
                        int linkedNode = visitedArray[matrix[front][col]];
                        if(!visitedArray[linkedNode]){
                            Queue.enqueue(linkedNode);

                            visitedArray[linkedNode] = true;
                        }
                    }
                }
            }    
        }



    }


    // Dfs Traversal

    void dfs(){

    }

    // Display Graph in matrix

    void display(){
        
        for(int row = 0; row<noOfNodes; row++){
            for(int col = 0; col < noOfNodes; col++){
                cout<<matrix[row][col]<<" ";
            }
            cout<<endl;
        }
    }

}

int main()
{

    // Taking input the no of nodes in graph

    int nodes;
    print("Enter the no. of nodes : ");
    cin>>nodes;

    // declaring graph object

    Graph g(nodes);

    // while for menu --

    while (true)
    {
        print("Your Directed Graph -- ");
        cout<<endl;
        map.display();

        cout << endl;

        print("Enter 1 for Add Edge operation");
        print("Enter 2 for Delete Edge operation");
        print("Enter 3 for BFS Traversal operation");
        print("Enter 4 for DFS Traversal operation");

        print("Enter 5 to exit");
        cout << endl;
        int input;
        cin >> input;
        int data;

        switch (input)
        {
        case 1:
            g.addEdge();
            break;
        case 2:
            g.deleteEdge();
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