import java.util.Scanner;
import java.util.InputMismatchException;
class BST {
  int data;
  BST left;
  BST right;
  BST root;

  void insert(int x) //insert in BST
  {
    root = insert(root, x);
  }
  BST insert(BST temp, int x) //insert  node in BST
  {
    if (temp == null) {
      temp = new BST();
      temp.data = x;
      temp.left = null;
      temp.right = null;
      return temp;
    } else if (temp.data > x)
      temp.left = insert(temp.left, x);
    else
      temp.right = insert(temp.right, x);

    return temp;
  }
  void inOrder(BST temp) // inorder to print it in sorted way
  {
    if (temp == null) return;
    inOrder(temp.left);
    System.out.print(temp.data + "  ");
    inOrder(temp.right);
  }

  void delete(int x) //deletion main function
  {
    root = delete(root, x);
  }

  BST delete(BST root, int x) //deletion in BST
  {
    if (root == null)
      return root;
    if (x < root.data) // if x is less than root
      root.left = delete(root.left, x);
    else if (x > root.data)
      root.right = delete(root.right, x);
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

  int subTree(BST temp) //to fetch a subTree
  {
    int a = temp.data;
    while (temp.left != null) {
      a = temp.left.data;
      temp = temp.left;
    }
    return a;
  }

  BST search(BST temp, int x) //helping function to check if an element is present or not
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

  boolean searchBST(int x) //main function to search
  {
    BST temp = search(root, x);
    if (temp != null) return true;
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
    BST n = new BST();
    int a, b;
    
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
		try {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        switch (x) {
        case 1:
          System.out.println("Enter a number ");
          a = sc.nextInt();
          n.insert(a);
          System.out.println("Done ");
          break;

        case 2:
          System.out.println("Enter a number ");
          a = sc.nextInt();
          n.delete(a);
          break;

        case 3:
          n.showAll();
          break;

        case 4:
          System.out.println("Enter a number ");
          a = sc.nextInt();
          System.out.println("Element present : " + n.searchBST(a));
          break;

        case 5:
          System.out.println("Enter no. to be replaced ");
          a = sc.nextInt();
          System.out.println("Enter a number");
          b = sc.nextInt();
          n.update(a, b);
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