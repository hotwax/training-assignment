import java.util.*;
class Node  //Node to store data
{
    Integer key;
    int value;    
    Node(Integer key, int value) 
    {
        this.key = key;
        this.value = value;        
    }
}
class RandomPro //random probeing
{
	Node a[];
	int CAP;
	 int c=0;
	boolean remove[];
     RandomPro(int CAP)  //constructor
    {
		this.CAP=CAP;
		a=new Node[CAP];
		remove=new boolean[CAP];
    } 
	void showAll() //method to print all the table
	{
		for(int i=0;i<a.length;i++)
		{
			if(a[i]!=null)
				System.out.println(a[i].key+"  "+a[i].value);
		}
	}
	 int hash(Integer k) // to find out hashcode
	{
        return (k%CAP);
	}
	 void add(int key, int value) // program to insert new key value pair 
	{
		if(key%CAP > CAP)
		{
			System.out.print("Not accepted");
		}
        int hashcode=hash(key);
        int random=1; // this variable will randomly findout a index
        
		while((a[hashcode]!=null && a[hashcode].key!=key) )
		{
            random=(int)(Math.random()*(CAP))+1;
            hashcode=(hashcode+random)%random;
        }
		
		// if already that index was already filled then hashcode will be different than hash() returned value
        if(hashcode!=hash(key) || (a[hashcode]!=null && a[hashcode].key!=key)) c++;
        a[hashcode]=new Node(key,value);
      }

	void remove(int key) // to remove the key we will have to linear search 
	{
        for(int i=0;i<a.length;i++)
		{
			if(a[i]!=null && a[i].key==key)
			{
			a[i]=null;
			}
		}
    }
	
	Integer get(int key) // to seach we will have to go linearly
	{
        for(int i=0;i<a.length;i++)
		{
			if(a[i]!=null && a[i].key==key)
			{
				return a[i].value;
			}
		}
		return null;
    }
	
	int getCollisions() // to count number of collision
	{
		return c;
	}
}
 
class Demo
{
	public static void main(String[] args)
	{
	int a,b;
	RandomPro r=new RandomPro(50);
	while(true)
	{
	System.out.println("===========================");
	System.out.println("*****Enter your choice*****");
	System.out.println("1.Put / Update");
	System.out.println("2.Remove");
	System.out.println("3.ShowAll");
	System.out.println("4.Search");
	System.out.println("5.Get collision");
	System.out.println("6.Time");
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
		r.add(a,b);
		System.out.println("Done ");
		break;
		
		case 2:
		System.out.println("Enter key ");
		a=sc.nextInt();
		r.remove(a);
		System.out.println("Done ");
		break;
		
		case 3:
		r.showAll();
		break;
		
		case 4:
		System.out.println("Enter key ");
		a=sc.nextInt();
		System.out.println("key="+a+"     Value="+r.get(a));
		break;
		
		case 5:
		System.out.println("No of collision : "+r.getCollisions());
		break;
		
		case 6:		
		System.out.println("Time in mili seconds "+System.currentTimeMillis());
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
