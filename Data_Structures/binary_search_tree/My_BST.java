package Data_Structures.binary_search_tree;

import java.util.Scanner;

public class My_BST {
    Node head;
    class Node{
        int val;
        Node left,right;
        public Node(int val){
            this.val=val;
        }
    }
    
    //-------addition-------
    public void add(int value_To_add){
        head=add(head,value_To_add);
    }
    public Node add(Node root,int value_To_add){
        //if root is null then create newNode and return to head
        if(root==null)return new Node(value_To_add);
        //if value is smaller than root, then search in left subtree.
        if(root.val>value_To_add){
            root.left=add(root.left,value_To_add);
        }
        //if value is greater than root, then search in right subtree.
        else if(root.val<value_To_add){
            root.right=add(root.right,value_To_add);
        }
        return root;
    }
    
    //-------deletion-------
    public void delete(int value_To_add){
        head=delete(head,value_To_add);
    }
    public Node delete(Node root,int value_To_add){
        if(root==null) return null;
        //if value is smaller than root, then search in left subtree.
        if(root.val>value_To_add){
            root.left=delete(root.left,value_To_add);
        }
        //if value is greater than root, then search in right subtree.
        else if(root.val<value_To_add){
            root.right=delete(root.right,value_To_add);
        }
        //else we found the node to delete
        else{
            //if left subtree is null just delete that node and make next right node head of subtree
            if(root.left==null) return root.right;
            //if right subtree is null just delete that node and make next left node head of subtree
            else if(root.right==null) return root.left;
            //else find the smallest element which is greater than deleted node and then call delete for that node recursively.
            else{
                Node nearestLargest=getNearestLargest(root);
                root.val=nearestLargest.val;
                root.right=delete(root.right, nearestLargest.val);
            }
        }
        return root;
    }
    //function to find the smallest element which is greater than root
    public Node getNearestLargest(Node root){
        Node curr=root;
        while(curr==null && root.left==null){
            curr=curr.left;
        }
        return curr;
    }
    
    //-------inorder-------
    public void inorder(){
        inorder(head);
        System.out.println();
    }
    public void inorder(Node root){
        if(root==null) return;
        inorder(root.left);
        System.out.print(root.val+" ");
        inorder(root.right);
    }
    
    //-------preorder-------
    public void preorder(){
        preorder(head);
        System.out.println();
    }
    public void preorder(Node root){
        if(root==null) return;
        System.out.print(root.val+" ");
        preorder(root.left);
        preorder(root.right);
    }
    
    //-------postorder-------
    public void postorder(){
        postorder(head);
        System.out.println();
    }
    public void postorder(Node root){
        if(root==null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.val+" ");
    }
    //-------Search-------
    public boolean search(int value_To_search){
        Node curr=head;
        while(curr!=null){
            if(curr.val==value_To_search) return true;
            else if(curr.val>value_To_search) curr=curr.left;
            else curr=curr.right;
        }
        return false;
    }
    public void menu(){
        boolean flag=true;
        while(flag){
            System.out.println("=========================");
            System.out.println("1: add Node");
            System.out.println("2: remove Node");
            System.out.println("3: inorder traversal");
            System.out.println("4: preorder traversal");
            System.out.println("5: postorder traversal");
            System.out.println("6: search");
            System.out.println("7: terminate the program");
            System.out.println();
            Scanner sc=new Scanner(System.in);
            int n=sc.nextInt();
            try{
                switch(n){
                  case 1:// add
                    System.out.println("enter Node value to add");
                    add(sc.nextInt());
                    break;
                   case 2:// delete
                       System.out.println("enter Node value to delete");
                       delete(sc.nextInt());
                       break;
                   case 3:// inorder
                       System.out.print("inorder-> ");
                       inorder();
                       break;
                   case 4:// preorder
                       System.out.print("preorder-> ");
                       preorder();
                       break;
                   case 5:// postorder
                       System.out.print("postorder-> ");
                       postorder();
                       break;
                   case 6:// search
                       System.out.println("enter value to search");
                       System.out.println(search(sc.nextInt()));
                       break;
                   case 7:// end
                       flag=false;
                       break;
                   default:
                       System.out.println("invalid input");
                       break;
                }
            }
            catch(Exception e){
                //if user enter any other value than integer
                  System.out.println("Invalid input: please enter Integer");
            }
        }
    }

    public static void main(String args[]){
        My_BST t=new My_BST();
        t.menu();
    }
}
