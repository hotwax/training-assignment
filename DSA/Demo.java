import java.util.*;
class linkedlist
{
	int data;
	linkedlist next;
	
	static linkedlist start=null;
	
    int countlinkedlist()
    {
	int c=0;
	linkedlist temp=start;
	while(temp!=null)
	{
		c++;
		temp=temp.next;
	}
	return c;
    }
	
	linkedlist msort(linkedlist a, linkedlist b)
    {
        linkedlist result;
        if (a == null)
            return b;
        if (b == null)
            return a;
        if (a.data <= b.data) 
		{
            result = a;
            result.next = msort(a.next, b);
        }
        else 
		{
            result = b;
            result.next = msort(a, b.next);
        }
        return result;
    }
    void sort()
	{
		start=split(start);
	}
    linkedlist split(linkedlist h)
    {
        if (h == null || h.next == null) 
		{
            return h;
        }
  
        linkedlist a = getMiddle(h);
        linkedlist b = a.next;
  
        a.next = null;
  
        linkedlist left = split(h);
        linkedlist right = split(b);
  
        linkedlist sortedlist = msort(left, right);
        return sortedlist;
    }
  
    public static linkedlist getMiddle(linkedlist head)
    {
        if (head == null)
            return head;
  
        linkedlist slow = head, fast = head;
  
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

	
	void update(int pre,int nw)
	{
		linkedlist temp=start;
		while(temp!=null && temp.data!=pre)
		{
		temp=temp.next;
		}
		if(temp==null)
			System.out.println("Data not found");
		else
			temp.data=nw;
	}
	
    void showAll()
   {
	linkedlist temp=start;
	while(temp!=null)
	{
		System.out.print(temp.data+"  ");
		temp=temp.next;
	}
	    System.out.println();
	}
	
	void deleteAtB()
    {
	if(start==null)
	{
		System.out.println("list is empty");
		return;
	}
	linkedlist temp1=start;
	start=start.next;
	temp1=null;
    }
	
	void deleteAtE()
	{
	if(start==null || start.next==null)
	{
		deleteAtB();
		return;
	}
	linkedlist temp1=start;
	linkedlist temp2=start;
	while(temp1.next!=null)
	{
		temp2=temp1;
		temp1=temp1.next;
	}
	temp2.next=null;
	temp1=null;
	}
	 
	 void deleteAtSP(int p)
	{
	if(p<=1)
	{
		deleteAtB();
		return;
	}
	if(p>countlinkedlist())
	{
		deleteAtE();
		return;
	}
	linkedlist temp1=start;
	linkedlist t;	
	int i;
	for(i=1;i<p-1;i++)
	{
		temp1=temp1.next;
	}
	t=temp1.next;
	temp1.next=t.next;
	t=null;
	}
	
	void insertAtB(int x)
	{
	linkedlist temp=new linkedlist();
	temp.data=x;
	temp.next=start;
	start=temp;
	}
	
	void insertAtE(int x)
	{
	if(start==null)
	{
		insertAtB(x);
		return;
	}
	linkedlist temp=new linkedlist();
	linkedlist temp1=start;
	temp.data=x;
	temp.next=null;
	while(temp1.next!=null)
	temp1=temp1.next;
	
	temp1.next=temp;
	}
	
	boolean search(int x)
	{
		linkedlist temp=start;
		while(temp!=null)
	    {
			if(temp.data==x)return true;
			temp=temp.next;
		}
		return false;
	}
	
	
	void insertAtSP(int x,int p)
	{
	if(p<=1)
	{
		insertAtB(x);
		return;
	}
	if(p>=countlinkedlist())
	{
		insertAtE(x);
		return;
	}
	linkedlist temp=new linkedlist();
	linkedlist temp1=start;
	temp.data=x;
	for(int i=1;i<p-1;i++)
	temp1=temp1.next;
	
	temp.next=temp1.next;
	temp1.next=temp;
	}
	
}
class Demo
{  
    public static void main(String[] args)
    {
    linkedlist start=new linkedlist();
	int a,b;
	while(true)
	{
	System.out.println("===========================");
	System.out.println("*****Enter your choice*****");
	System.out.println("1.Insert at beginning");
	System.out.println("2.Insert at ending");
	System.out.println("3.Insert at specific position");
	System.out.println("4.Deletion at beginning");
	System.out.println("5.Deletion at ending");
	System.out.println("6.Deletion at specific position");
	System.out.println("7.Update");
	System.out.println("8.Search");
	System.out.println("9.ShowAll");
	System.out.println("10.Exit");
	System.out.println("===========================");
	
	Scanner sc=new Scanner(System.in);
	int x=sc.nextInt();
	switch(x)
	{
		case 1:
		System.out.println("Enter a number ");
		a=sc.nextInt();
		start.insertAtB(a);
		System.out.println("Done ");
		break;
		
		case 2:
		System.out.println("Enter a number ");
		b=sc.nextInt();
		start.insertAtE(b);
		System.out.println("Done ");
		break;
		
		case 3:
		System.out.println("Enter a number ");
		a=sc.nextInt();
		System.out.println("Enter a position ");
		b=sc.nextInt();
		start.insertAtSP(a,b);
		System.out.println("Done ");
		break;
		
		case 4:
		start.deleteAtB();
		System.out.println("Done ");
		break;
		
		case 5:
		start.deleteAtE();
		System.out.println("Done ");
		break;
		
		case 6:
		System.out.println("Enter a position ");
		b=sc.nextInt();
		start.deleteAtSP(b);
		System.out.println("Done ");
		break;
		
		case 7:
		System.out.println("Enter no. to be replaced ");
		a=sc.nextInt();
		System.out.println("Enter new number ");
		b=sc.nextInt();
		start.update(a,b);
		System.out.println("Done ");
		break;
		
		case 8:
		System.out.println("Enter a number ");
		b=sc.nextInt();
		System.out.println(start.search(b));
		break;
		
		case 9:
		start.showAll();
		break;
		
		case 10:
		System.exit(0);
		break;
		
		default:
		System.out.println("Invalid");
		break;
	}
	
	}
	
	}
}
		


// class Node
// {
	// int data;
	// Node left;
	// Node right;
	// Node insert(Node temp,int x)
	// {
		
		// if(temp==null)
		// {
			// temp=new Node();
			// temp.data=x;
			// temp.left=null;
			// temp.right=null;
		// }
		// else if(temp.data>x)
		// temp.left=insert(temp.left,x);
	    // else
		// temp.right=insert(temp.right,x);
		// return temp;
	// }
	// void preOrder(Node temp)
	// {
		// if(temp==null)return;
		// preOrder(temp.left);
		// System.out.print(temp.data+"  ");
		// preOrder(temp.right);
	// }
	// void update(Node temp,int pre,int nw)
	// {
		// if(temp==null)return;
		// preOrder(temp.left);
		// if(temp.data==pre) temp.data=nw;
		// preOrder(temp.right);
	// }
// }
// class Demo
// {
	    // public static void main(String[] args)
		// {
			// Node root=null;
			// Node  n=new Node();
			// root=n.insert(root,10);
			// root=n.insert(root,20);
			// root=n.insert(root,40);
			// n.preOrder(root);
			// System.out.println();
			
			// n.update(root,10,110);
			// n.preOrder(root);
			// System.out.println();
			
			
			// root=n.insert(root,50);
			// root=n.insert(root,30);
			// root=n.insert(root,90);
			// n.preOrder(root);
			// System.out.println();
			
			// n.update(root,90,990);
			// n.update(root,50,555);
			// n.preOrder(root);
			// System.out.println();
			
		// }
// }
