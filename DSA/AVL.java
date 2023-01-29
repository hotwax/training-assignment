import java.util.Scanner;
import java.util.InputMismatchException;
class Node // a normal node class
{
  Node left, right, parent;
  int height = 1;
  int data;

  Node(int val) {
    this.data = val;
  }

}
class AVL {
  Node root;

  int height(Node N) // to find height of tree
  {
    if (N == null)
      return 0;
    return N.height;
  }

  private Node rightRotate(Node temp) // Right rotation for balancing tree 
  {
    Node first = temp.left;
    Node second = first.right;
    // Perform rotation
    first.right = temp;
    temp.left = second;
    //  Update heights
    temp.height = Math.max(height(temp.left), height(temp.right)) + 1;
    first.height = Math.max(height(first.left), height(first.right)) + 1;
    // Return new root
    return first;
  }

  private Node leftRotate(Node temp) // Left rotation for balancing tree
  {
    Node first = temp.right;
    Node second = first.left;
    // Perform rotation
    first.left = temp;
    temp.right = second;
    //  Update heights
    temp.height = Math.max(height(temp.left), height(temp.right)) + 1;
    first.height = Math.max(height(first.left), height(first.right)) + 1;
    // Return new root
    return first;
  }

  int heightDiff(Node N) // to check if tree is unbalance or not
  {
    if (N == null)
      return 0;
    return height(N.left) - height(N.right);
  }

  Node min(Node node) //min node
  {
    Node temp;
    for (temp = node; temp.left != null; temp = temp.left);
    return temp;
  }

  void inOrder(Node node) //sorted form of traversing
  {

    if (node != null) {
      inOrder(node.left);
      System.out.print(node.data + " ");
      inOrder(node.right);
    }
  }

  Node insert(Node node, int data) {
    if (node == null) {
      return (new Node(data));
    }

    if (data < node.data)
      node.left = insert(node.left, data);
    else
      node.right = insert(node.right, data);

    /* Update height of this ancestor node */
    node.height = Math.max(height(node.left), height(node.right)) + 1;

    /*  Get the balance factor of this ancestor node to check whether
       this node became unbalanced */
    int balance = heightDiff(node);

    // If this node becomes unbalanced, then there are 4 cases

    // Left Left Case
    if (balance > 1 && data < node.left.data)
      return rightRotate(node);

    // Right Right Case
    if (balance < -1 && data > node.right.data)
      return leftRotate(node);

    // Left Right Case
    if (balance > 1 && data > node.left.data) {
      node.left = leftRotate(node.left);
      return rightRotate(node);
    }

    // Right Left Case
    if (balance < -1 && data < node.right.data) {
      node.right = rightRotate(node.right);
      return leftRotate(node);
    }

    return node;
  }

  Node delete(Node root, int data) {

    if (root == null)
      return root;

    // If the data to be deleted is smaller than the root's data,
    // then it lies in left subtree
    if (data < root.data)
      root.left = delete(root.left, data);

    // If the data to be deleted is greater than the root's data,
    // then it lies in right subtree
    else if (data > root.data)
      root.right = delete(root.right, data);

    // if data is same as root's data, then This is the node
    // to be deleted
    else {
      // node with only one child or no child
      if ((root.left == null) || (root.right == null)) {

        Node temp;
        if (root.left != null)
          temp = root.left;
        else
          temp = root.right;

        // No child case
        if (temp == null) {
          temp = root;
          root = null;
        } else // One child case
          root = temp; // Copy the contents of the non-empty child

        temp = null;
      } else {
        //smallest in the right subtree
        Node temp = min(root.right);

        // Copy the inorder successor's data to this node
        root.data = temp.data;

        // Delete the inorder successor
        root.right = delete(root.right, temp.data);
      }
    }

    // If the tree had only one node then return
    if (root == null)
      return root;

    root.height = Math.max(height(root.left), height(root.right)) + 1;

    // to check whether this node became unbalanced)
    int balance = heightDiff(root);

    // If this node becomes unbalanced, then there are 4 cases

    // Left Left Case
    if (balance > 1 && heightDiff(root.left) >= 0)
      return rightRotate(root);

    // Left Right Case
    if (balance > 1 && heightDiff(root.left) < 0) {
      root.left = leftRotate(root.left);
      return rightRotate(root);
    }

    // Right Right Case
    if (balance < -1 && heightDiff(root.right) <= 0)
      return leftRotate(root);

    // Right Left Case
    if (balance < -1 && heightDiff(root.right) > 0) {
      root.right = rightRotate(root.right);
      return leftRotate(root);
    }
    return root;
  }

  void preOrder(Node node) //traversing
  {
    if (node != null) {
      System.out.print(node.data + " ");
      preOrder(node.left);
      preOrder(node.right);
    }
  }

  void showAll(Node root) {
    preOrder(root);
    System.out.println("\n");
  }

  void sort(Node root) {
    inOrder(root);
    System.out.println("");
  }

  Node update(Node root, int pre, int nw) // to replaced or update a node in AVL
  {
    root = delete(root, pre);
    root = insert(root, nw);
    return root;
  }

  Node search(Node temp, int x) //helping function to check if an element is present or not
  {

    if (temp != null) {
      if (temp.data == x)
        return temp;
      else if (temp.data > x)
        return search(temp.left, x);
      else
        return search(temp.right, x);
    }

    return null;
  }

  boolean searchNode(Node root, int x) //main function to search
  {
    Node temp = search(root, x);
    if (temp != null) return true;
    else return false;
  }

}

class Demo {
  public static void main(String[] args) {
    AVL tree = new AVL();
    int input1, input2;
    
      while (true) {
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

		try {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        switch (x) {
        case 1:
          System.out.println("Enter a number ");
          input1 = sc.nextInt();
          tree.root = tree.insert(tree.root, input1);
          System.out.println("Done ");
          break;

        case 2:
          System.out.println("Enter a number ");
          input1 = sc.nextInt();
          tree.root = tree.delete(tree.root, input1);
          System.out.println("Done");
          break;

        case 3:
          tree.showAll(tree.root);
          break;

        case 4:
          System.out.println("Enter a number ");
          input1 = sc.nextInt();
          System.out.println("Element present : " + tree.searchNode(tree.root, input1));
          break;

        case 5:
          tree.sort(tree.root);
          break;

        case 6:
          System.out.println("Enter no. to be replaced ");
          input1 = sc.nextInt();
          System.out.println("Enter a number");
          input2 = sc.nextInt();
          tree.root = tree.update(tree.root, input1, input2);
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
	  catch (InputMismatchException e) {
      System.out.println("Enter valid option");
    }

    } 

  }
}