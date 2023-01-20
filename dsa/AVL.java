import java.util.Scanner;

public class AVL {

  public static class Node{
    int data;
    Node left; //left child
    Node right; //right child

    int ht=1; // height
    int bal=0; // balance

    Node(int data){
      this.data=data;
    }
  }

  public static void main(String[] args) {
     Node root=null;

     Scanner sc=new Scanner(System.in);

     while (true) {
       System.out.println("Your tree: ");
       display(root);

       System.out.println("Enter 1 to add a node");
       System.out.println("Enter 2 to remove a node");
       System.out.println("Enter 3 to end the program");

       int n=sc.nextInt();

       if (n==1) {
        System.out.println("Enter the value of the node: ");
        int val=sc.nextInt();
        root=add(root, val);
       } else if (n==2) {
        System.out.println("Enter the value of the node: ");
        int val=sc.nextInt();
        root=remove(root, val);
       } else{
         return;
       }
     }
      
  }

  static int height(Node node) {
    int lh = (node.left == null ? 0 : node.left.ht);
    int rh = (node.right == null ? 0 : node.right.ht);
    return Math.max(lh, rh) + 1;
  }

  static int balance(Node node) {
    int lh = (node.left == null ? 0 : node.left.ht);
    int rh = (node.right == null ? 0 : node.right.ht);
    return lh - rh;
  }

  static void display(Node node) {

    if (node == null)
      return;

    String lstr = (node.left == null ? "" : node.left.data + "");
    String rstr = (node.right == null ? "" : node.right.data + "");
    String str = lstr + "  <---- " + node.data + "[ height = " + node.ht + ", balance = " + node.bal + "]" + " ----> " + rstr;

    System.out.println(str);

    display(node.left);
    display(node.right);
  }

  static Node add(Node node, int val) {
    if (node == null)
      return new Node(val);

    if (val < node.data) {
      node.left = add(node.left, val);
    } else if (val > node.data) {
      node.right = add(node.right, val);
    }

    node.ht = height(node);
    node.bal = balance(node);

    if (node.bal > 1) { // ll or lr

      if (node.left.bal >= 0) { // ll
        node = rightRotate(node);
      } else { // lr
        node.left = leftRotate(node.left);
        node = rightRotate(node);
      }
    } else if (node.bal < -1) { // rr or rl

      if (node.right.bal >= 0) { // rl
        node.right = rightRotate(node.right);
        node = leftRotate(node);
      } else { // rr
        node = leftRotate(node);
      }
    }

    return node;
  }

  static Node rightRotate(Node z) {

    Node y = z.left;

    Node t3 = y.right;

    // rotate
    z.left = t3;
    y.right = z;

    z.ht = height(z);
    z.bal = balance(z);

    y.ht = height(y);
    y.bal = balance(y);

    return y;
  }

  static Node leftRotate(Node z) {

    Node y = z.right;

    Node t2 = y.left;

    // rotate
    z.right = t2;
    y.left = z;

    z.ht = height(z);
    z.bal = balance(z);

    y.ht = height(y);
    y.bal = balance(y);

    return y;
  }

  static Node remove(Node node, int val) {
    if (node == null) {
      return null;
    }

    if (val == node.data) {
      if (node.left != null && node.right != null) {
        int lmax = max(node.left);
        node.data = lmax;
        node.left = remove(node.left, lmax);
      } else if (node.left != null) {
        return node.left;
      } else if (node.right != null) {
        return node.right;
      } else {
        return null;
      }
    } else if (val < node.data) {
      node.left = remove(node.left, val);
    } else if (val > node.data) {
      node.right = remove(node.right, val);
    }

    node.ht = height(node);
    node.bal = balance(node);

    // bal = lh - rh
    if (node.bal > 1) { // ll, lr
      if (node.left.bal >= 0) { // ll
        node = rightRotate(node);
      } else { // lr
        node.left = leftRotate(node.left);
        node = rightRotate(node);
      }
    } else if (node.bal < -1) { // rr, rl
      if (node.right.bal < 0) { // rr
        node = leftRotate(node);
      } else { // rl
        node.right = rightRotate(node.right);
        node = leftRotate(node);
      }
    }

    return node;
  }

  static int max(Node node) {
    if (node.right == null) {
      return node.data;
    } else {
      return max(node.right);
    }
  }

}