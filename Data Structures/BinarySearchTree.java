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
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        // Create a blank binary search tree
        Node root = null;
        // Variable to store the choice of the user
        int choice;
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
                    if(root == null){
                        System.out.println("The Binary Search Tree is empty");
                    }
                    System.out.println("Node Deleted");
                    break;
                case 3:
                    System.out.print("Please Enter the Node Value: ");
                    updateElement(root, sc.nextInt());
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
        } while (choice != 7);

    }

    private static void postorderTraversal(Node root) {
        if(root != null){
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            System.out.print(root.data + " ");
        }
    }

    private static void preorderTraversal(Node root) {
        if(root != null){
            System.out.print(root.data + " ");
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
    }

    private static void inorderTraversal(Node root) {
        if(root != null){
            inorderTraversal(root.left);
            System.out.print(root.data + " ");
            inorderTraversal(root.right);
        }
    }

    private static Node sortBinarySearchTree(Node root) {
        if(root == null){
            return null;
        }
        sortBinarySearchTree(root.left);
        System.out.print(root.data + " ");
        sortBinarySearchTree(root.right);
        return root;

    }

    private static void searchElement(Node root, int nextInt) {
        while(root != null){
            if(root.data == nextInt){
                System.out.println("Element Present");
                return;
            }
            else if(root.data > nextInt){
                root = root.left;
            }
            else{
                root = root.right;
            }
        }
        System.out.println("Element Not Present");
    }

    private static Node delete(Node root, int nextInt) {
        if(root == null){
            return null;
        }
        if(root.data ==  nextInt){
            return helper(root);
        }
        Node dummy = root;
        while(root != null){
            if(root.data > nextInt){
                if(root.left != null && root.left.data == nextInt){
                    root.left = helper(root.left);
                    break;
                }else{
                    root =  root.left;
                }
            }else{
                if(root.right != null && root.right.data == nextInt){
                    root.right = helper(root.right);
                    break;
                }
                root = root.right;
            }
        }
        return dummy;
    }

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

    private static Node findLastChild(Node left) {
        while(left.right != null){
            left = left.right;
        }
        return left;
    }


    private static void updateElement(Node root, int nextInt) {
        Node temp = root;
        while (temp != null) {
            if (temp.data == nextInt) {
                System.out.print("Please Enter the new Node Value: ");
                temp.data = sc.nextInt();
                break;
            } else if (temp.data > nextInt) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
    }
    private static Node createBinarySearchTree(int nextInt) {
        Node root = new Node(nextInt);
        return root;
    }

   private static Node insert(Node root,int nextInt) {
        Node curr = root;
        while(true){
            if(nextInt < curr.data){
                if(curr.left == null){
                    curr.left = new Node(nextInt);
                    break;
                }else{
                    curr = curr.left;
                }
            }else{
                if(curr.right == null){
                    curr.right = new Node(nextInt);
                    break;
                }else{
                    curr = curr.right;
                }
            }
        }
        return root;
    }

}
