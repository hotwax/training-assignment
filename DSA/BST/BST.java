import java.util.Scanner;
import java.util.InputMismatchException;
class BST {
	
  int data;
  BST left;
  BST right;
  BST root;

  void insert(int data) //insert in BST
  {
    root = insert(root, data);
  }
  
  BST insert(BST tempNode, int data) //insert  node in BST
  {
    if (tempNode == null) {
      tempNode = new BST();
      tempNode.data = data;
      tempNode.left = null;
      tempNode.right = null;
      return tempNode;
    } else if (tempNode.data > data)
      tempNode.left = insert(tempNode.left, data);
    else
      tempNode.right = insert(tempNode.right, data);

    return tempNode;
  }
  
  void inOrder(BST tempNode) // inorder to print it in sorted way
  {
    if (tempNode == null) return;
    inOrder(tempNode.left);
    System.out.print(tempNode.data + "  ");
    inOrder(tempNode.right);
  }

  void delete(int data) //deletion main function
  {
    root = delete(root, data);
  }

  BST delete(BST root, int data) //deletion in BST
  {
    if (root == null)
      return root;
    if (data < root.data) // if data is less than root
      root.left = delete(root.left, data);
    else if (data > root.data)
      root.right = delete(root.right, data);
    else //node with only one or none child
    {
      if (root.left == null)
        return root.right;
      else if (root.right == null)
        return root.left;

      root.data = subTree(root.right); //small in right subTree
      root.right = delete(root.right, root.data);
    }

    return root;
  }

  int subTree(BST tempNode) //to fetch a subTree
  {
    int a = tempNode.data;
    while (tempNode.left != null) {
      a = tempNode.left.data;
      tempNode = tempNode.left;
    }
    return a;
  }

  BST search(BST tempNode, int data) //helping function to check if an element is present or not
  {
    if (tempNode != null) {
      if (tempNode.data == data)
        return tempNode;
      else if (tempNode.data > data)
        return search(tempNode.left, data);
      else
        return search(tempNode.right, data);
    }
    return null;
  }

  boolean searchBST(int data) //main function to search
  {
    BST tempNode = search(root, data);
    if (tempNode != null) return true;
    else return false;
  }

  void update(int pre, int nw) // update value of a node
  {
    delete(pre);
    insert(nw);
  }

  void showAll() //to display value
  {
    inOrder(root);
    System.out.println();
  }
}
class Demo {
  public static void main(String[] args) {
    BST tree = new BST();
    int input1, input2;
    
      while (true) {
        System.out.println("===========================");
        System.out.println("*****Enter your choice*****");
        System.out.println("1.Insert ");
        System.out.println("2.Deletion");
        System.out.println("3.showAll");
        System.out.println("4.Search");
        System.out.println("5.Update");
        System.out.println("6.Exit");
        System.out.println("===========================");
        Scanner sc = new Scanner(System.in);
		try {
        int condition = sc.nextInt();
        switch (condition) {
        case 1:
          System.out.println("Enter a number ");
          input1 = sc.nextInt();
          tree.insert(input1);
          System.out.println("Done ");
          break;

        case 2:
          System.out.println("Enter a number ");
          input1 = sc.nextInt();
          tree.delete(input1);
          break;

        case 3:
          tree.showAll();
          break;

        case 4:
          System.out.println("Enter a number ");
          input1 = sc.nextInt();
          System.out.println("Element present : " + tree.searchBST(input1));
          break;

        case 5:
          System.out.println("Enter no. to be replaced ");
          input1 = sc.nextInt();
          System.out.println("Enter a number");
          input2 = sc.nextInt();
          tree.update(input1, input2);
          break;

        case 6:
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