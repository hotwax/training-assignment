#include<iostream>
#include<vector>
#include<queue>
#include<stack>
#include<algorithm>
using namespace std;
class Graph 
{
private:
    int V;
    vector<vector<int>> adjList; // adjacency list to represent graph
public:
    Graph(int V) 
    {
        this->V=V;
        adjList.resize(V);
    }
    //add edge between vertices u and v
    void addEdge(int u,int v) 
    {
        adjList[u].push_back(v);
        adjList[v].push_back(u);
    }
    //function to remove edge between u and v
    void removeEdge(int u,int v) 
    {
        for(auto it=adjList[u].begin();it!=adjList[u].end();it++) 
        {
            if(*it==v) 
            {
                adjList[u].erase(it);
                break;
            }
        }
        for(auto it=adjList[v].begin();it!=adjList[v].end();it++) 
        {
            if(*it==u) 
            {
                adjList[v].erase(it);
                break;
            }
        }
    }
    bool isEdge(int u,int v) 
    {
        for(auto it=adjList[u].begin();it!=adjList[u].end();it++) 
        {
            if(*it==v) return true;
        }
        return false;
    }
    //print the full graph
    void printGraph() 
    {
        for(int i=0;i<V;i++) 
        {
            cout<<i<<" : ";
            for(auto j:adjList[i]) cout<<j<<" ";
            cout<<endl;
        }
    }
    //BFS travel
    void BFS(int s)
    {
        vector<bool> visited(V,false);
        queue<int> q;
        visited[s]=true;
        q.push(s);
        while(!q.empty()) 
        {
            int u=q.front();
            q.pop();
            cout<<u<<" ";
            for(auto v:adjList[u]) 
            {
                if(!visited[v]) 
                {
                    visited[v]=true;
                    q.push(v);
                }
            }
        }
        cout<<endl;
    }
    //DFS Travel
    void DFS(int s) 
    {
        vector<bool> visited(V,false);
        stack<int> stk;
        stk.push(s);
        while(!stk.empty()) 
        {
            int u=stk.top();
            stk.pop();
            if(!visited[u]) 
            {
                cout<<u<<" ";
                visited[u]=true;
            }
            for (auto v:adjList[u]) if(!visited[v]) stk.push(v);
        }
        cout << endl;
    }
};
int main() 
{
    int V,ch,u,v;
    cout<<"Enter the number of vertices : ";
    cin>>V;
    fflush(stdin);
    Graph g(V);
    while(1)
    {
        cout<<"1.Add edge in the Graph"<<endl;
        cout<<"2.Remove edge from the Graph"<<endl;
        cout<<"3.Check if edge exists in Graph"<<endl;
        cout<<"4.Print graph"<<endl;
        cout<<"5.BFS of the Graph"<<endl;
        cout<<"6.DFS of the Graph"<<endl;
        cout<<"7.Exit"<<endl;
        cout<<"Enter your choice: ";
        cin>>ch;
        fflush(stdin);
        if(ch<1 || ch>7)
        {
            cout<<"Enter a CORRECT Choice..."<<endl<<endl;
            continue;
        }
        if(ch==1)
        {
            cout<<"Enter the vertices to add edge : ";
            cin>>u>>v;
            g.addEdge(u,v);
            cout<<endl;
        }
        else if(ch==2)
        {
            cout<<"Enter the vertices to remove edge : ";
            cin>>u>>v;
            g.removeEdge(u, v);
        }
        else if(ch==3)
        {
            cout<<"Enter the vertices to check edge : ";
            cin>>u>>v;
            if(g.isEdge(u,v)) cout<<"Edge exists!"<<endl;
            else cout<<"Edge does not exist..."<<endl;
        }
        else if(ch==4)
        {
            g.printGraph();
            cout<<endl;
        }
        else if(ch==5)
        {
            cout<<"Enter the starting vertex for BFS : ";
            cin>>u;
            cout<<"BFS : ";
            g.BFS(u);
            cout<<endl<<endl;
        }
        else if(ch==6)
        {
            cout<<"Enter the starting vertex for DFS : ";
            cin>>u;
            cout<<"DFS : ";
            g.DFS(u);
            cout<<endl<<endl;
        }
        else break;
    }
    return 0;
}