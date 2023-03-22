import java.util.Scanner;
import java.util.InputMismatchException;

public class AVL {

  public static class Node {
    int data;
    Node left; // left child
    Node right; // right child

    int height = 1; // height
    int bal = 0; // balance

    Node(int data) {
      this.data = data;
    }
  }

  public static void main(String[] args) {

    try {
      Scanner sc = new Scanner(System.in);
      Node root = null;

      while (true) {
        System.out.println("Your tree: ");
        display(root);

        System.out.println("Enter 1 to add a node");
        System.out.println("Enter 2 to remove a node");
        System.out.println("Enter 3 to check whether data exists");
        System.out.println("Enter 4 to exit");

        int choice = sc.nextInt();
        int val;

        switch (choice) {
          case 1:
            System.out.println("Enter the value of the node: ");
            val = sc.nextInt();
            root = add(root, val);
            System.out.println("-------------------------------------");
            break;

          case 2:
            System.out.println("Enter the value of the node: ");
            val = sc.nextInt();
            root = remove(root, val);
            System.out.println("-------------------------------------");
            break;

          case 3:
            System.out.println("Enter the value of the node: ");
            val = sc.nextInt();
            String result = whetherExists(root, val) ? (val + " exists") : (val + " doesn't exists");
            System.out.println(result);
            System.out.println("-----------------------------");
            break;

          case 4:
            System.out.println("Program terminated successfully.");
            System.out.println("-------------------------------------");
            return;

          default:
            System.out.println("Please enter a valid choice (1,2,3).");
            System.out.println("-------------------------------------");
            break;
        }

      }

    } catch (InputMismatchException e) {
      System.out.println("Please give a valid number. " + e);
    } catch (Exception e) {
      System.out.println(e);
    }

  }

  static int height(Node node) {
    int leftChildHeight = (node.left == null ? 0 : node.left.height); // left child height
    int rightChildHeight = (node.right == null ? 0 : node.right.height); // right child height
    return Math.max(leftChildHeight, rightChildHeight) + 1;
  }

  static int balance(Node node) {
    int leftChildHeight = (node.left == null ? 0 : node.left.height);
    int rightChildHeight = (node.right == null ? 0 : node.right.height);
    return leftChildHeight - rightChildHeight;
  }

  static void display(Node node) {

    if (node == null)
      return;

    String lstr = (node.left == null ? "" : node.left.data + "");
    String rstr = (node.right == null ? "" : node.right.data + "");
    String str = lstr + "  <---- " + node.data + "[ height = " + node.height + ", balance = " + node.bal + "]"
        + " ----> "
        + rstr;

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
    } else{
      System.out.println("node with data "+val+" already exists.");
      return node;
    } 

    node.height = height(node);
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

    z.height = height(z);
    z.bal = balance(z);

    y.height = height(y);
    y.bal = balance(y);

    return y;
  }

  static Node leftRotate(Node z) {

    Node y = z.right;

    Node t2 = y.left;

    // rotate
    z.right = t2;
    y.left = z;

    z.height = height(z);
    z.bal = balance(z);

    y.height = height(y);
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

    node.height = height(node);
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

  static boolean whetherExists(Node node, int val) {
    if (node == null) {
      return false;
    }

    if (val < node.data)
      return whetherExists(node.left, val);
    else if (val > node.data)
      return whetherExists(node.right, val);
    else
      return true;
  }

  

}