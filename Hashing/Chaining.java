import java.util.*;
class Node // a node for insertion in chaining hash map
{
        int key;
		int value;
        Node(int k,int v)
		{
            key=k;
            value=v;
        }
		public String toString() // overriding method for displaying data
		{
		return "key="+key+"  value="+value;
		}
}
class Chaining
{
    ArrayList<Node> arr[];
    int CAPACITY,n;
    
    Chaining(Integer CAPACITY) //constructor to initialize
	{
        this.CAPACITY=CAPACITY;
        this.n=n;
        arr=new ArrayList[CAPACITY];
    }
    public int getCollisions() //get no. of collision
	{
        int count=1;
        for(int i=0;i<arr.length;i++)
		{
              if(arr[i]!=null && arr[i].size()>1)
			  {
                  count++;
              }
        }
        return count;
    }
    public void put(Integer k, Integer v) //insert key value pair
	{
        int index=hashcode(k);
        if(arr[index]==null)
		{
            arr[index]=new ArrayList<>();
        }
        for(Node n:arr[index])
		{
            if(n.key==k)
			{
                n.value=v;
                return;
            }
        }
		Node n1=new Node(k,v);
        arr[index].add(n1);
    }
    public int hashcode(Integer k) 
	{
        return k%CAPACITY;
    }
    public int get(Integer k)  //search key
	{  
        for(Node n:arr[hashcode(k)])
		{
            if(n.key==k) return n.value;
        }
        return -1;
    }
	public void remove(Integer k)// delete an key value pair
	{  
        for(Node n:arr[hashcode(k)])
		{
			if(n!=null && n.key==k)
				arr[hashcode(k)].remove(n);
        }
    } 
	void showAll() // print all
	{  
        for(ArrayList a: arr)
		{
		  if(a!=null)
		  {
			  for(int i=0;i<a.size();i++)
				System.out.println(a.get(i));
		  }
		}
	System.out.println();
    }
}
class Demo
{
	public static void main(String[] args)
	{
	int a,b;
	Chaining c=new Chaining(50);
	
	while(true)
	{
	System.out.println("===========================");
	System.out.println("*****Enter your choice*****");
	System.out.println("1.Put / Update");
	System.out.println("2.Remove");
	System.out.println("3.ShowAll");
	System.out.println("4.Search");
	System.out.println("5.Get collision");
	System.out.println("6.Get Time ");
	System.out.println("7.Exit");
	System.out.println("===========================");
	
	Scanner sc=new Scanner(System.in);
	int x=sc.nextInt();
	switch(x)
	{
		case 1:
		System.out.println("Enter key ");
		a=sc.nextInt();
		System.out.println("Enter value ");
		b=sc.nextInt();
		c.put(a,b);
		System.out.println("Done ");
		break;
		
		case 2:
		System.out.println("Enter key ");
		a=sc.nextInt();
		c.remove(a);
		System.out.println("Done ");
		break;
		
		case 3:
		c.showAll();
		break;
		
		case 4:
		System.out.println("Enter key ");
		a=sc.nextInt();
		System.out.println("key="+a+"     Value="+c.get(a));
		break;
		
		case 5:
		System.out.println("No of collision : "+c.getCollisions());
		break;
        
		case 6:		
		System.out.println("Time in mili seconds"+System.currentTimeMillis());
		break;
		
		case 7:		
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
