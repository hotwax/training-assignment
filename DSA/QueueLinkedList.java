import java.util.*;
 class queue
{
	int data;
	queue next;
	queue f;
	queue r;
	void showAll() // to see inserted elements 
	{
 	queue temp=f;
 	if(isEmpty())
 	{
 		System.out.println("queue is empty\n");
 		return;
	}
 	while(temp!=null)
	{
		System.out.print(temp.data+"  ");
 		temp=temp.next;
	}
 	System.out.println();
    }
	
	boolean search(int x) // to search elements
	{
		queue temp=f;
		while(temp!=null)
		{
		if(temp.data==x)return true;
 		temp=temp.next;
		}
		return false;
	}
 
	 int dequeue() // to remove element form queue
    {
 	if(isEmpty())
 	{
 		System.out.println("queue is empty\n");
 		return -1;
	}
	queue temp=f;
	int x=temp.data;
	f=f.next;
	temp=null;
	return x;
    }
	
	int front() // to see first inserted element
	{
	if(isEmpty())
 	{
 		System.out.println("queue is empty\n");
 		return -1;
	}
     return f.data;	
	}
	
	boolean isEmpty() // to check if queue is empty or not
	{
		if(f==null)return true;
		return false;
	}
	
	void enqueue(int x) // to insert an element
	{
	queue temp=new queue();
 	if(temp==null)
 	{
 		System.out.println("space not available");
 		return;
	}
	temp.data=x;
	temp.next=null;
	if(r==null)
	f=temp;
	else 
	r.next=temp;
	
	r=temp;
	}
	
	void update(int pre,int nw) // to update value of an element
	{
	queue temp=f;
 	if(isEmpty())
 	{
 		System.out.println("queue is empty\n");
 		return;
	}
 	while(temp!=null && temp.data!=pre)
	{
 		temp=temp.next;
	}
	if(temp!=null)
	{
		temp.data=nw;
		System.out.println("Done");
	}
	else 
	System.out.println("data not found");
	}
}
class Demo
{  
    public static void main(String[] args)
    {
	queue q=new queue();
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
		
	
