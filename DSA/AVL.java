import java.util.*;
class  Node  // a normal node class
{
  int data, height;
  Node left, right;

  Node(int d) 
  {
    data = d;
    height = 1;
  }
}
class AVL 
{
	Node root;
	int height(Node N) // to find height of tree
	{
		if (N == null)
			return 0;
		return N.height;
	}

	Node rotateRight(Node b) // Right rotation for balancing tree 
	{
		Node a = b.left;
		Node c = a.right;

		a.right = b;
		b.left = c;

		b.height = Math.max(height(b.left), height(b.right)) + 1;
		a.height = Math.max(height(a.left), height(a.right)) + 1;

		return a;
	}

	Node rotateLeft(Node a)// Left rotation for balancing tree
	{
		Node b = a.right;
		Node c = b.left;

		b.left = a;
		a.right = c;

		a.height = Math.max(height(a.left), height(a.right)) + 1;
		b.height = Math.max(height(b.left), height(b.right)) + 1;

		return b;
	}

	int heightDiff(Node N) // to check if tree is unbalance or not
	{
		if (N == null)
			return 0;
		return height(N.left) - height(N.right);
	}

	Node insert(Node node, int data) // insertion of new node in AVL
	{
		if (node == null) // if its a 1st insertion
			return (new Node(data));

		if (data < node.data) // if data is smaller than root node
			node.left = insert(node.left, data);
		else if (data > node.data)  // if data is larger than root node
			node.right = insert(node.right, data);
		else 
			return node;

		node.height = 1 + Math.max(height(node.left),height(node.right));

		int balance = heightDiff(node); // balance factor
		if (balance > 1 && data < node.left.data)
			return rotateRight(node);

		if (balance < -1 && data > node.right.data)
			return rotateLeft(node);

		if (balance > 1 && data > node.left.data)
		{
			node.left = rotateLeft(node.left);
			return rotateRight(node);
		}

		if (balance < -1 && data < node.right.data)
		{
			node.right = rotateRight(node.right);
			return rotateLeft(node);
		}
		
		return node;
	}

	Node min(Node node) //min node
	{
	    Node temp;
        for(temp=node;temp.left!=null;temp=temp.left);
		return temp;
	}

	Node delete(Node root, int data) // deletion of a node form AVL
	{
		if (root == null)// if its a 1st insertion
			return root;

		if (data < root.data)// if data is smaller than root node
			root.left = delete(root.left, data);

		else if (data > root.data) // if data is larger than root node
			root.right = delete(root.right, data);

		else // the node which user want to delete
		{

			if ((root.left == null) || (root.right == null))
			{
				Node temp = null;
				if (temp == root.left)
					temp = root.right;
				else
					temp = root.left;

				if (temp == null)
				{
					temp = root;
					root = null;
				}
				else 
					root = temp;
			}
			else
			{

				Node temp = min(root.right);

				root.data = temp.data;

				root.right = delete(root.right, temp.data);
			}
		}

		if (root == null)
			return root;

		root.height = Math.max(height(root.left), height(root.right)) + 1;
    	        int balance = heightDiff(root);

		if (balance > 1 && heightDiff(root.left) >= 0)
			return rotateRight(root);

		if (balance > 1 && heightDiff(root.left) < 0)
		{
			root.left = rotateLeft(root.left);
			return rotateRight(root);
		}

		if (balance < -1 && heightDiff(root.right) <= 0)
			return rotateLeft(root);

		if (balance < -1 && heightDiff(root.right) > 0)
		{
			root.right = rotateRight(root.right);
			return rotateLeft(root);
		}

		return root;
	}

	void inOrder(Node node) //sorted form of traversing
	{
		if (node != null)
		{
			inOrder(node.left);
			System.out.print(node.data + " ");
			inOrder(node.right);
		}
	}
	void preOrder(Node node) //traversing
	{
		if (node != null)
		{
			System.out.print(node.data + " ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	void showAll(Node root)
	{
		preOrder(root);
		System.out.println("\n");
	}
	
	void sort(Node root)
	{
		inOrder(root);
		System.out.println("\n");
	}
	
	Node update(Node root,int pre,int nw) // to replaced or update a node in AVL
	{
      root=delete(root,pre);
	  root=insert(root,nw);
	  return root;
	}
	
	Node search(Node temp,int x) //helping function to check if an element is present or not
	{
		if(temp!=null)
		{
		if(temp.data==x)
			return temp;
		else if(temp.data>x)
			return search(temp.left,x);
		else 
			return search(temp.right,x);
		}
		return null;
	}
	 
	 boolean searchNode(Node root,int x) //main function to search
	 {
		 Node temp=search(root,x);
		 if(temp!=null)return true;
		 else return false;
	 }
}

class Demo
{
	 public static void main(String[] args)
	{
	AVL tree = new AVL();
    
	int a,b;
	while(true)
	{
	System.out.println("===========================");
	System.out.println("*****Enter your choice*****");
	System.out.println("1.Insert ");
	System.out.println("2.Deletion");
	System.out.println("3.showAll");
	System.out.println("4.Search");
	System.out.println("5.Sort");
	System.out.println("6.Update");
	System.out.println("7.Exit");
	System.out.println("===========================");
	
	Scanner sc=new Scanner(System.in);
	int x=sc.nextInt();
	switch(x)
	{
		case 1:
		System.out.println("Enter a number ");
		a=sc.nextInt();
		tree.root = tree.insert(tree.root, a);
		System.out.println("Done ");
		break;
		
		case 2:
		System.out.println("Enter a number ");
		a=sc.nextInt();
		tree.root = tree.delete(tree.root, 33);
		break;
		
		case 3:
		tree.showAll(tree.root);
		break;
		
		case 4:
		System.out.println("Enter a number ");
		a=sc.nextInt();
		System.out.println("Element present : "+tree.searchNode(tree.root,a));
		break;
		
		case 5:
		tree.sort(tree.root);
		break;
		
		case 6:
		System.out.println("Enter no. to be replaced ");
		a=sc.nextInt();
		System.out.println("Enter a number");
		b=sc.nextInt();
		tree.root=tree.update(tree.root,a,b);	
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









