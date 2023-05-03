package BST;
import java.util.InputMismatchException;
import java.util.Scanner;

//BST class
public class BST {
    
    //Node class
    class Node {
        int data;
        Node left;
        Node right;

        //Constructor
        Node(int val) {
            this.data = val;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    // Constructor call
    BST() {
        root = null;
    }

    //check the BST is empty or not
    Boolean isEmpty() {
        if (root == null) {
            return true;
        }
        return false;
    }

    //Insert the data in BST
    void insertion(int data) {
        root = insertRec(data, root);
    }

    private Node insertRec(int data, Node rootNode) {
        if (rootNode == null) {
            rootNode = new Node(data);
            return rootNode;
        } else {
            if (data < rootNode.data) {
                rootNode.left = insertRec(data, rootNode.left);
            } else if (data > rootNode.data) {
                rootNode.right = insertRec(data, rootNode.right);
            } else {
                return rootNode;
            }
        }
        return rootNode;
    }

    // Inorder Traversal in BST
    void inorder() {
        if (root == null) {
            System.out.println("-1");
            return;
        }
        inorderRec(root);
    }

    private void inorderRec(Node rootNode) {
        if (rootNode == null) {
            return;
        }
        inorderRec(rootNode.left);
        System.out.print(rootNode.data + "  ");
        inorderRec(rootNode.right);
    }

    // Preorder Traversal in BST
    void preorder() {
        if (root == null) {
            System.out.println("-1");
            return;
        }
        preorderRec(root);
    }

    private void preorderRec(Node rootNode) {
        if (rootNode == null) {
            return;
        }
        System.out.print(rootNode.data + "  ");
        preorderRec(rootNode.left);
        preorderRec(rootNode.right);
    }

    // Postorder Traversal in BST
    void postorder() {
        if (root == null) {
            System.out.println("-1");
            return;
        }
        postorderRec(root);
    }

    private void postorderRec(Node rootNode) {
        if (rootNode == null) {
            return;
        }
        postorderRec(rootNode.left);
        postorderRec(rootNode.right);
        System.out.print(rootNode.data + "  ");
    }

    // Delete a key if it is present in Binary Search Tree
    void deletion(int key) {
        if (root == null) {
            System.out.println("-1");
            return;
        }
        root = deleted(root, key);
    }

    // recursive call for deletion
    private Node deleted(Node rootNode, int key) {
        // root is null, So return null
        if (rootNode == null) {
            return null;
        }
        // if key is equal to root so delete root
        if (rootNode.data == key) {
            // if left and right of root is null so return null
            if (rootNode.left == null && rootNode.right == null) {
                return null;
            }
            // if left of root is not null but right is null so return left
            else if (rootNode.left != null && rootNode.right == null) {
                return rootNode.left;
            }
            // if right of root is not null but left is null so return right
            else if (rootNode.left == null && rootNode.right != null) {
                return rootNode.right;
            }
            // if both right and left of root is not null
            // So return min value in right or max value in left
            else if (rootNode.left != null && rootNode.right != null) {
                Node newnode = rootNode.right;
                while (newnode.left != null) {
                    newnode = newnode.left;
                }
                rootNode.data = newnode.data;
                rootNode.right = deleted(rootNode.right, newnode.data);
                return rootNode;
            }
        } else if (rootNode.data > key) {
            rootNode.left = deleted(rootNode.left, key);
            return rootNode;

        } else {
            rootNode.right = deleted(rootNode.right, key);
            return rootNode;
        }
        return rootNode;
    }

    // Iterative way of tree traversal
    void morrisTraversal() {
        if (root == null) {
            System.out.println("-1");
            return;
        }
        Node curr = root;
        while (curr != null) {

            if (curr.left == null) {
                System.out.print(curr.data + "  ");
                curr = curr.right;
            } else {
                Node prec = curr.left;
                while (prec.right != null && prec.right != curr) {
                    prec = prec.right;
                }
                if (prec.right == null) {
                    prec.right = curr;
                    curr = curr.left;
                } else if (prec.right == curr) {
                    System.out.print(curr.data + "  ");
                    curr = curr.right;
                }
            }
        }
    }

    // Search a data in BST
    boolean searchDataInBst(int data) {
        return searchRec(root, data);
    }

    private boolean searchRec(Node rootNode, int data) {
        if (rootNode == null) {
            return false;
        }
        if (rootNode.data == data) {
            return true;
        } else if (rootNode.data < data) {
            return searchRec(rootNode.right, data);
        } else {
            return searchRec(rootNode.left, data);
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        try (Scanner input = new Scanner(System.in)) {
            String choices;
            do {
                System.out.println();
                System.out.println("Operations in BST ");
                System.out.println();
                System.out.println("  1.Insertion in BST");
                System.out.println("  2.Searching in BST");
                System.out.println("  3.Inorder Traversal");
                System.out.println("  4.Preorder Traversal");
                System.out.println("  5.Postorder Traversal");
                System.out.println("  6.Morris Traversal");
                System.out.println("  7.Deletion in BST");
                System.out.println("  8.Exit");
                System.out.println();
                System.out.println("Enter the choices ");
                System.out.println();
                choices = input.next();
                System.out.println();
                switch (choices) {
                    case "1":
                        System.out.println();
                        System.out.println("Enter the data");
                        int d = input.nextInt();
                        bst.insertion(d);
                        System.out.println();
                        break;

                    case "2":
                        System.out.println();
                        System.out.println("Enter the key");
                        int key = input.nextInt();

                        //check bst is empty
                        if (bst.isEmpty()) {
                            System.out.println("BST is empty");
                            break;
                        }
                        //search the key in bst
                        if (bst.searchDataInBst(key)) {
                            System.out.println("Key is found in BST");
                        } else {
                            System.out.println("Key is not Found in BST");
                        }
                        System.out.println();
                        break;

                    case "3":
                        System.out.println();
                        if (bst.isEmpty()) {
                            System.out.println("BST is empty");
                            break;
                        }
                        System.out.println("Inorder traversal -");
                        bst.inorder();
                        System.out.println();
                        break;

                    case "4":
                        System.out.println();
                        if (bst.isEmpty()) {
                            System.out.println("BST is empty");
                            break;
                        }
                        System.out.println("Preorder traversal -");
                        bst.preorder();
                        System.out.println();
                        break;

                    case "5":
                        System.out.println();
                        if (bst.isEmpty()) {
                            System.out.println("BST is empty");
                            break;
                        }
                        System.out.println("Postorder traversal -");
                        bst.postorder();
                        System.out.println();
                        break;

                    case "6":
                        System.out.println();
                        if (bst.isEmpty()) {
                            System.out.println("BST is empty");
                            break;
                        }
                        System.out.println("Morris Traversal -");
                        bst.morrisTraversal();
                        System.out.println();
                        break;

                    case "7":
                        System.out.println();
                        System.out.println("Enter the key ");
                        int keyForDelete = input.nextInt();
                        if (bst.isEmpty()) {
                            System.out.println("BST is empty");
                            break;
                        }
                        if (bst.searchDataInBst(keyForDelete)) {
                            bst.deletion(keyForDelete);
                            System.out.println("deleted Key will be-" + keyForDelete);
                        } else {
                            System.out.println("Key not found");
                        }
                        System.out.println();
                        break;

                    case "8":
                        System.out.println("Thank you");
                        break;

                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
            } while (!choices.equals("8"));
        } catch (InputMismatchException exception) {
            System.out.println(exception);
        }
        catch(Exception exception){
            System.out.println(exception);
        }
    }
}