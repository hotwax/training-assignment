import java.util.*;

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
    public void add(int x){
        head=add(head,x);
    }
    public Node add(Node root,int x){
        if(root==null)return new Node(x);
        if(root.val>x){
            root.left=add(root.left,x);
        }
        else if(root.val<x){
            root.right=add(root.right,x);
        }
        return root;
    }
    
    //-------deletion-------
    public void delete(int x){
        head=delete(head,x);
    }
    public Node delete(Node root,int x){
        if(root==null) return null;
        if(root.val>x){
            root.left=delete(root.left,x);
        }
        else if(root.val<x){
            root.right=delete(root.right,x);
        }
        else{
            if(root.left==null) return root.right;
            else if(root.right==null) return root.left;
            else{
                Node nearestLargest=getNearestLargest(root);
                root.val=nearestLargest.val;
                root.right=delete(root.right, nearestLargest.val);
            }
        }
        return root;
    }
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
    public boolean search(int x){
        Node curr=head;
        while(curr!=null){
            if(curr.val==x) return true;
            else if(curr.val>x) curr=curr.left;
            else curr=curr.right;
        }
        return false;
    }
    public void menu(){
    Scanner sc=new Scanner(System.in);
    
    System.out.println("1: add Node");
    System.out.println("2: remove Node");
    System.out.println("3: inorder traversal");
    System.out.println("4: preorder traversal");
    System.out.println("5: postorder traversal");
    System.out.println("6: search");
    System.out.println("7: terminate the program");
    boolean flag=true;
    while(flag){
      int n=sc.nextInt();
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
    }

    public static void main(String args[]){
        My_BST t=new My_BST();
        t.menu();
    }
}
