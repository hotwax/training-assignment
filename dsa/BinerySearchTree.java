import java.util.Scanner;

public class BinerySearchTree {

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

    if (val <= node.data)
      node.left = add(node.left, val);
    else if (val > node.data)
      node.right = add(node.right, val);

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

  public static Node leftMax(Node node) {  //max value of left child tree

    while (node.right != null) {
      node = node.right;
    }

    return node;
  }

  public static void main(String[] args) {

    try {
      Scanner sc = new Scanner(System.in);

      Node root = null;

      while (true) {
        System.out.println("Your binery search tree: ");
        display(root);
        System.out.println();

        System.out.println("Enter 1 to add a node");
        System.out.println("Enter 2 to remove a node");
        System.out.println("Enter 3 to check whether data exsits");

        int choice = sc.nextInt();

        if (choice == 1) {
          System.out.println("Enter the value of the node: ");
          int val = sc.nextInt();
          root = add(root, val);
          System.out.println("-----------------------------");
        } else if (choice == 2) {
          System.out.println("Enter the value of the node: ");
          int val = sc.nextInt();
          root = remove(root, val);
          System.out.println("-----------------------------");
        } else if (choice == 3) {
          System.out.println("Enter the value of the node: ");
          int val = sc.nextInt();
          String result = whetherExists(root, val) ? (val + " exists") : (val + " doesn't exists");
          System.out.println(result);
          System.out.println("-----------------------------");
        } else {
          System.out.println("Program terminated successfully.");
          System.out.println("-----------------------------");
          return;
        }
      }

    } catch (Exception e) {
      System.out.println(e);
    }
  }
}