import java.util.*;
class GrapH
{
	int vertex;
	int edge;
	int[][] graph;
	ArrayList<ArrayList<Integer>> al;
	Scanner sc=new Scanner(System.in);
	GrapH(int x,int y) //constructor to initialize vertex and edges
	{
		vertex=x;
		edge=y;
		graph=new int[vertex+1][vertex+1];
		al=new ArrayList<ArrayList<Integer>>(vertex);
		adjList();
	}
	
	void insert(int v1,int v2,int w) // to create adjacency matrix 
	{
		graph[v1][v2]=w;
		graph[v2][v1]=w;
	}
	void adjMaxtrix()  // to initialize adjacency matrix
	{
		for(int i=0;i<edge;i++)
		{
			System.out.println("enter starting vertex ");
			int a=sc.nextInt();
			System.out.println("enter ending vertex ");
			int b=sc.nextInt();
			insert(a,b,1);
			System.out.println("==================");
		}
	}
	void adjList() //to create adjacency list
	{
		for(int i=0;i<vertex;i++)
			al.add(new ArrayList<>());
	}
	void addList(int v,int u)
	{
		al.get(v).add(u);
		al.get(u).add(v);
	}
	void PList() // to print list
	{
		for(int i=0;i<al.size();i++)
		{
			System.out.print("Vertex "+i+"=>");
			for(int j=0;j<al.get(i).size();j++)
			{
				System.out.print(al.get(i).get(j)+"  ");
			}
			System.out.println();
		}
	}
	void Pmatrix() //to print adjacency matrix
	{
		for(int i=0;i<vertex;i++)
		{
			for(int j=0;j<vertex;j++)
			System.out.print(graph[i][j]+"  ");
		\
		System.out.println();
		}
	}
	public void dfsearch() //depth first search
	{
        boolean v[]=new boolean[vertex];
        df_search(0,v);
        System.out.println();
    }
    public void df_search(int s, boolean v[]) //helping method
	{
        v[s]=true;
        System.out.print(s+" ");
        for(int a:al.get(s))
		{
            if(!v[a]) df_search(a,v);
        }
    }
}
class Demo
{
	public static void main(String ar[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter number of vertices");
		int vertex=sc.nextInt(); 
		System.out.println("Enter number of edges");
		int edge=sc.nextInt(); 
		GrapH g=new GrapH(vertex,edge);
		int a,b;
	while(true)
	{
	System.out.println("===========================");
	System.out.println("*****Enter your choice*****");
	System.out.println("1. Create Adjancy matrix ");
	System.out.println("2. Create Adjancy List");
	System.out.println("3. Print Adjancy matrix");
	System.out.println("4. Print Adjancy List");
	System.out.println("5. Traversing DFS");
	System.out.println("6. Exit");
	System.out.println("===========================");
	
	int x=sc.nextInt();
	switch(x)
	{
		case 1:
		g.adjMaxtrix();
		System.out.println("Done ");
		break;
		
		case 2:
		System.out.println("Enter two connecting edges name ");
		System.out.println("First vertex");
		a=sc.nextInt();
		System.out.println("second vertex");
		b=sc.nextInt();
		g.addList(a,b);
		System.out.println("Done \n");
		break;
		
		case 3:
		g.Pmatrix();
		break;
		
		case 4:
		g.PList();
		break;
		
		case 5:
		g.dfsearch();
		break;
		
		case 6:		
		System.out.println("Thank you");
		System.exit(0);
		break;
		
		default:
		System.out.println("Invalid");
		break;
	}
	
	}
	}
}