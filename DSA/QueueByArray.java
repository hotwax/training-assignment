import java.util.*;
 class QueuE
{
	int CAPACITY=100;
	int f=-1;
	int r=-1;
	int queue[]=new int[CAPACITY];
	
	void showAll() //to display queue elements
	{
 	if(isEmpty())
 	{
 		System.out.println("queue is empty\n");
 		return;
	}
 	for(int i=f;i<=r;i++)
	{
		System.out.print(queue[i]+"  ");
	}
 	System.out.println();
    }
	
	boolean search(int x)// to search elements
	{
	for(int i=f;i<=r;i++)
	{
	if(queue[i]==x)return true;
	}
	return false;
	}
 
	 int dequeue()// to remove elements
    {
 	if(isEmpty())
 	{
 		System.out.println("queue is empty\n");
 		return -1;
	}
	return queue[f++];
    }
	
	int front() // to see first entered element
	{
	if(isEmpty())
 	{
 		System.out.println("queue is empty\n");
 		return -1;
	}
     return queue[f];	
	}
	
	boolean isEmpty() //to check if queue is empty
	{
		if(f==-1 || f>r)return true;
		return false;
	}
	
	boolean isFull() // to check if queue is full
	{
		if(r==CAPACITY-1)return true;
		return false;
	}
	
	void enqueue(int x) // to insert element
	{
		if(isFull())
		{
						System.out.println("Queue is full");
						return;
		}
		if(f==-1)f++;
		queue[++r]=x;
 	}
	
	void update(int pre,int nw) // to update value of an element
	{
 	if(isEmpty())
 	{
 		System.out.println("queue is empty\n");
 		return;
	}
	for(int i=f;i<=r;i++)
	{
		if(queue[i]==pre)
		{
			queue[i]=nw;
			System.out.println("Done");	
			return;
		}
	}
			System.out.println("Data not fount");	
	}
}
class Demo
{  
    public static void main(String[] args)
    {
	QueuE q=new QueuE();
	int a,b;
	while(true)
	{
	System.out.println("===========================");
	System.out.println("*****Enter your choice*****");
	System.out.println("1.Enqueue");
	System.out.println("2.Dequeue");
	System.out.println("3.showAll");
	System.out.println("4.Search");
	System.out.println("5.Update");
	System.out.println("6.Exit");
	System.out.println("===========================");
	
	Scanner sc=new Scanner(System.in);
	int x=sc.nextInt();
	switch(x)
	{
		case 1:
		System.out.println("Enter a number ");
		a=sc.nextInt();
		q.enqueue(a);
		System.out.println("Done ");
		break;
		
		case 2:
		System.out.println("Element "+q.dequeue());
		break;
		
		case 3:
		q.showAll();
		break;
		
		case 4:
		System.out.println("Enter a number ");
		a=sc.nextInt();
		System.out.println("Element present : "+q.search(a));
		break;
		
		case 5:
		System.out.println("Enter no. to be replaced ");
		a=sc.nextInt();
		System.out.println("Enter a number");
		b=sc.nextInt();
		q.update(a,b);
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
		
	
