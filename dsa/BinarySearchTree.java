import java.util.Scanner;
import java.util.InputMismatchException;

public class BinarySearchTree {

  public static class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
      this.data = data;
    }
  }

  static void display(Node node) {
    if (node == null) {
      return;
    }

    String lstr = (node.left == null ? "null" : node.left.data + "");
    String rstr = (node.right == null ? "null" : node.right.data + "");
    String str = (lstr + " <---- " + node.data + " ----> " + rstr);

    System.out.println(str);

    display(node.left);
    display(node.right);
  }

  static Node add(Node node, int val) {
    if (node == null) {
      return new Node(val);
    }

    if (val < node.data)
      node.left = add(node.left, val);
    else if (val > node.data)
      node.right = add(node.right, val);
    else{
      System.out.println("node with data "+val+" already exists.");
    } 
    return node;
  }

  static Node remove(Node node, int val) {
    if (node == null)
      return null;

    if (val < node.data) {
      node.left = remove(node.left, val);
    } else if (val > node.data) {
      node.right = remove(node.right, val);
    } else {

      if (node.left != null && node.right != null) {
        int leftMax = leftMax(node.left).data;
        node.data = leftMax;
        node.left = remove(node.left, leftMax);
        return node;
      } else if (node.left != null) {
        return node.left;
      } else if (node.right != null) {
        return node.right;
      } else {
        return null;
      }
    }

    return node;
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

  public static Node leftMax(Node node) { // max value of left child tree

    while (node.right != null) {
      node = node.right;
    }

    return node;
  }

  static void inOrderTraversal(Node node){
    if(node==null) return;
    inOrderTraversal(node.left);
    System.out.print(node.data+" ");
    inOrderTraversal(node.right);
  }

  static void postOrderTraversal(Node node){
    if(node==null) return;
    postOrderTraversal(node.left);
    postOrderTraversal(node.right);
    System.out.print(node.data+" ");
  }

  public static void main(String[] args) {

    try {
      Scanner sc = new Scanner(System.in);

      Node root = null;

      while (true) {
        System.out.println("Your binary search tree: ");
        display(root);
        System.out.println();

        System.out.println("Enter 1 to add a node");
        System.out.println("Enter 2 to remove a node");
        System.out.println("Enter 3 to check whether data exists");
        System.out.println("Enter 4 for inorder traversal");
        System.out.println("Enter 5 for post order traversal");
        System.out.println("Enter 6 to exit");

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
            inOrderTraversal(root);
            System.out.println("\n-------------------------------------");
            break;
            
          case 5:
            postOrderTraversal(root);  
            System.out.println("\n-------------------------------------");
            break;

          case 6:
            System.out.println("Program terminated successfully.");
            System.out.println("-------------------------------------");
            return;

          default:
            System.out.println("Please enter a valid choice (1,2,3,4).");
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
}