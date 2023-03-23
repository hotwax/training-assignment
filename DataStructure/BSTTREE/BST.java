package BSTTREE;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BST {

    class Node {
        int data;
        Node left;
        Node right;

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

    Boolean isEmpty() {
        if (root == null) {
            return true;
        }
        return false;
    }

    void insertion(int data) {
        root = insertrec(data, root);
    }

    private Node insertrec(int data, Node node) {
        if (node == null) {
            node = new Node(data);

            return node;
        } else {

            if (data < node.data) {
                node.left = insertrec(data, node.left);
            } else if (data > node.data) {
                node.right = insertrec(data, node.right);
            } else {
                return node;
            }

        }
        return node;
    }

    // Preorder Traversal in BST
    void inorder() {
        if (root == null) {
            System.out.println("-1");
            return;
        }

        inorderrec(root);
    }

    private void inorderrec(Node node) {
        if (node == null) {
            return;
        }

        inorderrec(node.left);
        System.out.print(node.data + "  ");
        inorderrec(node.right);
    }

    // Inorder Traversal in BST

    void preorder() {
        if (root == null) {
            System.out.println("-1");
            return;
        }
        preorderrec(root);
    }

    private void preorderrec(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.data + "  ");
        preorderrec(node.left);
        preorderrec(node.right);
    }

    // Postorder Traversal in BST

    void postorder() {
        if (root == null) {
            System.out.println("-1");
            return;
        }
        postorderrec(root);

    }

    private void postorderrec(Node node) {
        if (node == null) {
            return;
        }

        postorderrec(node.left);
        postorderrec(node.right);
        System.out.print(node.data + "  ");
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
    private Node deleted(Node node, int key) {
        // root is null, So return null
        if (node == null) {
            return null;
        }
        // if key is equal to root so delete root
        if (node.data == key) {
            // if left and right of root is null so return null
            if (node.left == null && node.right == null) {
                return null;
            }
            // if left of root is not null but right is null so return left
            else if (node.left != null && node.right == null) {
                return node.left;
            }
            // if right of root is not null but left is null so return right
            else if (node.left == null && node.right != null) {
                return node.right;
            }
            // if both right and left of root is not null
            // So return min value in right or max value in left
            else if (node.left != null && node.right != null) {
                Node newnode = node.right;
                while (newnode.left != null) {
                    newnode = newnode.left;
                }
                node.data = newnode.data;
                node.right = deleted(node.right, newnode.data);
                return node;
            }

        } else if (node.data > key) {
            node.left = deleted(node.left, key);
            return node;

        } else {
            node.right = deleted(node.right, key);
            return node;

        }
        return node;
    }

    // Iterative way of tree traversal

    void morristraversal() {
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

    // Search a key
    boolean search(int key) {
        return searchrec(root, key);
    }

    private boolean searchrec(Node node, int key) {
        if (node == null) {
            return false;
        }
        if (node.data == key) {
            return true;
        } else if (node.data < key) {
            return searchrec(node.right, key);
        } else {
            return searchrec(node.left, key);
        }

    }

    public static void main(String[] args) {
        BST bst = new BST();
        try (Scanner input = new Scanner(System.in)) {
            int choices;
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
                choices = input.nextInt();
                System.out.println();
                switch (choices) {
                    case 1:
                        System.out.println();
                        System.out.println("Enter the data");
                        int d = input.nextInt();
                        bst.insertion(d);
                        System.out.println();
                        break;

                    case 2:
                        System.out.println();
                        System.out.println("Enter the key");
                        int key = input.nextInt();
                        if (bst.isEmpty()) {
                            System.out.println("BST is empty");
                            break;
                        }
                        if (bst.search(key)) {
                            System.out.println("Key is found in BST");
                        } else {
                            System.out.println("Key is not Found in BST");
                        }
                        System.out.println();
                        break;

                    case 3:
                        System.out.println();
                        if (bst.isEmpty()) {
                            System.out.println("BST is empty");
                            break;
                        }
                        System.out.println("Inorder traversal -");
                        bst.inorder();
                        System.out.println();
                        break;

                    case 4:
                        System.out.println();
                        if (bst.isEmpty()) {
                            System.out.println("BST is empty");
                            break;
                        }
                        System.out.println("Preorder traversal -");
                        bst.preorder();
                        System.out.println();
                        break;

                    case 5:
                        System.out.println();
                        if (bst.isEmpty()) {
                            System.out.println("BST is empty");
                            break;
                        }
                        System.out.println("Postorder traversal -");
                        bst.postorder();
                        System.out.println();
                        break;

                    case 6:
                        System.out.println();
                        if (bst.isEmpty()) {
                            System.out.println("BST is empty");
                            break;
                        }
                        System.out.println("Morris Traversal -");
                        bst.morristraversal();
                        System.out.println();
                        break;

                    case 7:
                        System.out.println();
                        System.out.println("Enter the key ");
                        int k = input.nextInt();
                        if (bst.isEmpty()) {
                            System.out.println("BST is empty");
                            break;
                        }
                        if (bst.search(k)) {
                            bst.deletion(k);
                            System.out.println("deleted Key will be-" + k);
                        } else {
                            System.out.println("Key not found");
                        }
                        System.out.println();
                        break;

                    case 8:
                        System.out.println("Thank you");
                        break;

                    default:
                        System.out.println("Invalid Choice");
                        break;
                }

            } while (choices != 8);
        } catch (InputMismatchException exception) {
            System.out.println(exception);
        }

    }

}
