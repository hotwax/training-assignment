package com.java.demo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BinaryTree {
	
	// Initializing the root of the tree
	node root;
	
	// creating the node class to store all the information of tree node
	class node
	{
		int data;
		node left;
		node right;
		
		// constructor to Initialize new node of a tree
		public node(int value)
		{
			data = value;
			left = null;
			right = null;
		}
		
		
	}
	
	// creating the insert method to insert the element in a BST
   void insert(int value)
   {
	   // passing value insertrec method and storing the data in root, we also pass root along with value 
	   root = insertrec(root,value);
   }
	
	
	// creating the insertrec method to recursively insert data to it's correct position
	 node insertrec(node root,int value)
	{
		 // if root is node return new node
		if(root==null) 
		{
			root = new node(value);
			return root;
		}
		// if value is small as comapre to root.data then call insertrec method with root.left and value as a arguments
		else if(value<root.data)
		{
			root.left = insertrec(root.left,value);
		}
		// if value is large as comapre to root.data then call insertrec method with root.right and value as a arguments
		else if(value>root.data)
		{
			root.right = insertrec(root.right,value);
		}
			
		return root;
		
	}
	 
	 // creating deletion method to pass root and value to deletionrec method
	 void deletion(int value)
	 {
		 root = deletionrec(root,value);
	 }
	 
	 // creating deletionrec method to recursively delete the data from the tree and rearrange the tree with correct manner to find the correct element from the tree
	 node deletionrec(node root, int key)
	 {
		 // checking if the root is null
		if(root==null) return root;
		// // if key is large as compare to root.data then call deletionrec method with root.right and value as a arguments to find the correct element from the tree
		if(root.data<key)
		{
			root.right = deletionrec(root.right,key);
		}
		// if key is smaller as compare to root.data then call deletionrec method with root.left and value as a arguments to find the correct element from the tree
		else if(root.data> key)
		{
			root.left = deletionrec(root.left,key);
		}
		// else we are at the correct position where our key and root.data is matched
		else
		{
			// if root left and right is null then simply delete it from the BST 
			if(root.left==null && root.right ==null)
			{
				root = null;
			}
			// if right child is present then call successor method and deletionrec to rearrange the BST and delete the node from the BST
			else if(root.right!=null)
			{
				root.data = successor(root);
				root.right = deletionrec(root.right, root.data);
			}else
			{
				root.data = predecessor(root);
				root.left = deletionrec(root.left, root.data);
			}
		}
		return root;
	 }
	 
	 // successor method return the lowest value from the right tree of root 
	 int successor(node root)
	 {
		 root = root.right;
		 while(root.left!=null)
		 {
			 root = root.left;
		 }
		 return root.data;
	 }
	 
	 // predecessor method return the highest value from left tree of root
	 int predecessor(node root)
	 {
		 root = root.right;
		 while(root.left!=null)
		 {
			 root  = root.left;
		 }
		 return root.data;
	 }
	 
	 
	 // creating preorder method to print the data of in preorder manner
	 void preorder()
	 {
		 preorderrec(root);
	 }
	// creating preorderrec method to print the data of in preorder manner with recursively
	 void preorderrec(node root)
	 {
		 
		 if(root==null)
		 {
			 return;
		 }
		 
		 System.out.print(root.data + " ");
		 preorderrec(root.left);
		 preorderrec(root.right);
	 }
	 
	 // search method to search the element in a BST  and return true or false
	 boolean search(int value)
	 {
		 boolean isfound;
		isfound =  searchrec(root,value);
		 return isfound;
		 
	 }
	 // searchrec method to search the element in a BST and return true or false recursively
	 boolean searchrec(node root, int value)
	 {
		 if(root==null)
		 {
			 return false;
		 }
		 if(root.data==value)
		 {
			 return true;
		 }
		 
		 
		 return ((root.data>value)? (searchrec(root.left,value)) : (searchrec(root.right,value)) );
		 
		 
	 }
	 
	 // update method to update the data in a BST
	 void update(int data, int olddata)
	 {
		 // it will delete the current value in a BST
		deletion(olddata);
		// it will insert the data in a BST
		insert(data);
		
	 }

	public static void main(String[] args) {
		
		// creating object of BinaryTree class
		
		BinaryTree tree = new BinaryTree();
		boolean flag = true;
		// running the while loop while flag is false
		while(flag)
		{
			System.out.println("Choose an option from the Dashboard given below:\n");
			System.out.println("1. Insert an element");
            System.out.println("2. Delete an element ");
            System.out.println("3. Update an element");
            System.out.println("4. Display data in preorder ");
            System.out.println("5. Search");
            System.out.println("6. EXIT");
            System.out.println();
            Scanner sc = new Scanner(System.in);
            int ch = sc.nextInt();
            if (ch < 1 || ch > 6) {
                System.out.println("Kindly enter the correct option.");
            } else {
            	//Taking the choice from the user and performing the corresponding operation
            	try {
            		switch(ch)
            		{
            		case 1: 
            			System.out.println("Enter the value to be inserted");
                        int input_val = sc.nextInt();
                        tree.insert(input_val);
                        System.out.print("Binary Tree : ");
                        tree.preorder();
                        System.out.println("");
                        break;
                        
            		case 2:
            			System.out.println("Enter the value to be deleted");
                        int input_value1 = sc.nextInt();
                        tree.deletion(input_value1);
                        System.out.print("Binary Tree : ");
                        tree.preorder();
                        System.out.println("");
            			break;
            		
            		case 3:
            			System.out.println("Enter the new value to be updated");
            			int update_new = sc.nextInt();
            			System.out.println("Enter the current value present in a tree");
            			int update_current = sc.nextInt();
            			tree.update(update_new, update_current);
            			System.out.print("Binary Tree : ");
            			tree.preorder();
            			System.out.println("");
            			break;
            			
            		case 4:
            			System.out.print("Binary Tree : ");
            			tree.preorder();
            			System.out.println("");
            			break;
            			
            		case 5:
            			System.out.println("Enter the value to be searched in a tree");
            			int search_value = sc.nextInt();
            			System.out.println( tree.search(search_value));
            			
            			break;
            			
            		case 6:
            			flag = false;
            			break;
            			
            		}
            		
            		if(flag==false)
            		{
            			System.out.println("Exiting program");
            		}
            	}
            	catch(InputMismatchException ex)
            	{
            		System.out.println(ex.getMessage());
            	}
            	
            	
            }
		}  
             	
            	
	}
    

}
