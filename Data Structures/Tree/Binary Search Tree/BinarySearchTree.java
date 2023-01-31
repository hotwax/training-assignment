import java.util.InputMismatchException;
import java.util.Scanner;
public class BinarySearchTree {

    static class Node {
        //  Data of the node
        int data;
        // Pointer to the left node
        Node left;
        // Pointer to the right node
        Node right;

        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }

    }

    public static void main(String[] args) {
        // Create a blank binary search tree
        Node root = null;
        // Variable to store the choice of the user
        int choice  = 0;
        do {
            //  Printing the menu for the user
            System.out.println(" --------- MENU --------- ");
            System.out.println("Press 0 : Create a New Binary Search Tree");
            System.out.println("Press 1 : Insert an element");
            System.out.println("Press 2 : Delete an element");
            System.out.println("Press 3 : Update an element");
            System.out.println("Press 4 : Traverse the Binary Search Tree");
            System.out.println("Press 5 : Search an element");
            System.out.println("Press 6 : Sort the Binary Search Tree");
            System.out.println("Press 7 : Exit");
            System.out.println("Enter your choice : ");
            try {
                // Creating a scanner object to take input from the user
                Scanner sc = new Scanner(System.in);
                // Taking the choice from the user
                choice = sc.nextInt();
                //  Taking the choice from the user and performing the corresponding operation
                switch (choice) {
                    case 0:
                        System.out.print("Please Enter the first Node Value: ");
                        root = createBinarySearchTree(sc.nextInt());
                        break;
                    case 1:
                        if (root != null) {
                            System.out.print("Please Enter the Node Value: ");
                            root = insert(root, sc.nextInt());
                        } else {
                            System.out.println("Please create a Binary Search Tree first");
                        }
                        break;
                    case 2:
                        System.out.println("Please Enter the Node Value: ");
                        root = delete(root, sc.nextInt());
                        if (root == null) {
                            System.out.println("The Binary Search Tree is empty");
                        }
                        System.out.println("Node Deleted");
                        break;
                    case 3:
                        System.out.println("Please Enter the Node Value: ");
                        System.out.print("Please Enter the new Node Value: ");
                        updateElement(root, sc.nextInt(), sc.nextInt());
                        break;
                    case 4:
                        int choice2;
                        do {
                            System.out.println("Press 0 : Inorder Traversal");
                            System.out.println("Press 1 : Preorder Traversal");
                            System.out.println("Press 2 : Postorder Traversal");
                            System.out.println("Press 3 : Back to Main Menu");
                            System.out.println("Enter your choice : ");
                            choice2 = sc.nextInt();
                            switch (choice2) {
                                case 0:
                                    System.out.println("Inorder Traversal");
                                    inorderTraversal(root);
                                    System.out.println();
                                    break;
                                case 1:
                                    System.out.println("Preorder Traversal");
                                    preorderTraversal(root);
                                    System.out.println();
                                    break;
                                case 2:
                                    System.out.println("Postorder Traversal");
                                    postorderTraversal(root);
                                    System.out.println();
                                    break;
                                case 3:
                                    break;
                                default:
                                    System.out.println("Invalid Choice");
                            }
                        } while (choice2 != 3);
                        break;
                    case 5:
                        System.out.print("Please Enter the Node Value: ");
                        searchElement(root, sc.nextInt());
                        break;
                    case 6:
                        System.out.print("Binary Search Tree Sorted : ");
                        root = sortBinarySearchTree(root);
                        System.out.println();
                        break;
                    case 7:
                        System.out.println("Program Terminated Successfully");
                        break;
                    default:
                        System.out.println("Invalid Input");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter Integer Only");
            }
        } while (choice != 7);

    }
    // Method to print PostOrder Traversal of the Binary Search Tree
    private static void postorderTraversal(Node root) {
        if(root != null){
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            System.out.print(root.data + " ");
        }
    }
    // Method to print PreOrder Traversal of the Binary Search Tree
    private static void preorderTraversal(Node root) {
        if(root != null){
            System.out.print(root.data + " ");
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
    }
    // Method to print InOrder Traversal of the Binary Search Tree
    private static void inorderTraversal(Node root) {
        if(root != null){
            inorderTraversal(root.left);
            System.out.print(root.data + " ");
            inorderTraversal(root.right);
        }
    }
    // Method to Sort Binary Search Tree
    private static Node sortBinarySearchTree(Node root) {
        if(root == null){
            return null;
        }
        sortBinarySearchTree(root.left);
        System.out.print(root.data + " ");
        sortBinarySearchTree(root.right);
        return root;

    }
    // Method to Search an element in the Binary Search Tree
    private static void searchElement(Node root, int valueToSearch) {
        while(root != null){
            if(root.data == valueToSearch){
                System.out.println("Element Present");
                return;
            }
            else if(root.data > valueToSearch){
                root = root.left;
            }
            else{
                root = root.right;
            }
        }
        System.out.println("Element Not Present");
    }
    // Method to delete value from Binary Search Tree
    private static Node delete(Node root, int valueToDelete) {
        if(root == null){
            return null;
        }
        if(root.data == valueToDelete){
            return helper(root);
        }
        Node dummy = root;
        while(root != null){
            if(root.data > valueToDelete){
                if(root.left != null && root.left.data == valueToDelete){
                    root.left = helper(root.left);
                    break;
                }else{
                    root =  root.left;
                }
            }else{
                if(root.right != null && root.right.data == valueToDelete){
                    root.right = helper(root.right);
                    break;
                }
                root = root.right;
            }
        }
        return dummy;
    }
    // Helper Method for Delete
    private static Node helper(Node root) {
        if(root.left == null)
            return root.right;
        if(root.right == null){
            return root.left;
        }
        Node rightChild = root.right;
        Node lastChild = findLastChild(root.left);
        lastChild.right = rightChild;
        return root.left;
    }
    // Helper Method for Delete
    private static Node findLastChild(Node left) {
        while(left.right != null){
            left = left.right;
        }
        return left;
    }
    // Method to Update the Node Value
    private static void updateElement(Node root, int valueToUpdate, int newValue) {
        Node temp = root;
        while (temp != null) {
            if (temp.data == valueToUpdate) {
                temp.data = newValue;
                break;
            } else if (temp.data > valueToUpdate) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
    }
    // Method to Create a Binary Search Tree
    private static Node createBinarySearchTree(int initialValue) {
        Node root = new Node(initialValue);
        return root;
    }
    // Method to Insert a Node in Binary Search Tree
   private static Node insert(Node root,int valueToInsert) {
        Node curr = root;
        while(true){
            if(valueToInsert < curr.data){
                if(curr.left == null){
                    curr.left = new Node(valueToInsert);
                    break;
                }else{
                    curr = curr.left;
                }
            }else{
                if(curr.right == null){
                    curr.right = new Node(valueToInsert);
                    break;
                }else{
                    curr = curr.right;
                }
            }
        }
        return root;
    }

}
