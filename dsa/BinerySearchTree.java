import java.util.*;

public class BinerySearchTree {

  public static class Node{
    int data;
    Node left;
    Node right;

    Node(int data){
      this.data=data;
    }
  }

  static void display(Node node){
      if (node==null) {
        return;
      }

      String lstr = (node.left==null ? "" : node.left.data+"");
      String rstr = (node.right==null ? "" : node.right.data+"");
      String str = (lstr+ " <---- " + node.data + " ----> " + rstr);
      
      System.out.println(str);

      display(node.left);
      display(node.right);
  }

  static Node add(Node node, int val){
    if (node==null) {
      return new Node(val);
    }

    if(val<=node.data) node.left = add(node.left, val);
    else if(val>node.data) node.right = add(node.right, val);

    return node;
  }

  static Node remove(Node node, int val){

    if (val<node.data) {
      node.left = remove(node.left, val);
    } else if (val>node.data){
      node.right = remove(node.right, val);
    } else{

      if(node.left!=null && node.right!=null){
        int leftMax = leftMax(node.left).data; 
        node.data=leftMax;
        return remove(node.left, leftMax);
      } else if(node.left!=null){
        return node.left; 
      } else if(node.right!=null){
        return node.right;
      } else{
        return null;
      }
    }

    return node;
  }

  static boolean whetherExists(Node node, int val){
    if (node==null) {
      return false;
    }

    if(val<node.data) return whetherExists(node.left, val);
    else if(val>node.data) return whetherExists(node.right, val);
    else return true;
  }

  public static Node leftMax(Node node){
    
    while (node.right!=null) {
      node=node.right;
    }

    return node;
  }

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    Node root=null;

    while (true) {
      System.out.println("Your binery search tree: ");
      display(root);

      System.out.println("Enter 1 to add a node");
      System.out.println("Enter 2 to remove a node");
      System.out.println("Enter 3 to check whether data exsits");
      System.out.println("Enter 4 to end the program");

      int n = sc.nextInt();

      if (n == 1) {
        System.out.println("Enter the value of the node: ");
        int val = sc.nextInt();
        root = add(root, val);
        System.out.println("-----------------------------");
      } else if (n == 2) {
        System.out.println("Enter the value of the node: ");
        int val = sc.nextInt();
        root = remove(root, val);
        System.out.println("-----------------------------");
      } else if (n==3){
        System.out.println("Enter the value of the node: ");
        int val = sc.nextInt();
        System.out.println(whetherExists(root, val));
        System.out.println("-----------------------------");
      } else {
        return;
      }
    }

  }
}