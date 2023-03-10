import java.util.Scanner;
import java.util.InputMismatchException;

public class TreeAVL {
    // Node class for the AVL tree
    static class Node {
        int data;
        Node left, right;
        int height;

        Node(int d) {
            data = d;
            left = right = null;
            height = 1;
        }
    }

    // Method to right rotate the tree
    public static Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // Method to left rotate the tree
    public static Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Method to get the balance factor of a node
    public static int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    public static int height(Node N) {
        if (N == null)
            return 0;

        return N.height;
    }

    public static int minValue(Node root) {
        int minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    // Method to insert a new element in the AVL tree
    public static Node insert(Node root, int value) {
        if (root == null) {
            return (new Node(value));
        }
        if (value < root.data) {
            root.left = insert(root.left, value);
        } else if (value > root.data) {
            root.right = insert(root.right, value);
        } else {
            return root;
        }
        root.height = 1 + Math.max(height(root.left), height(root.right));
        int balance = getBalance(root);
        if (balance > 1 && value < root.left.data) {
            return rightRotate(root);
        }
        if (balance < -1 && value > root.right.data) {
            return leftRotate(root);
        }
        if (balance > 1 && value > root.left.data) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if (balance < -1 && value < root.right.data) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }

    public static Node delete(Node root, int value) {
        if (root == null)
            return root;
        if (value < root.data)
            root.left = delete(root.left, value);
        else if (value > root.data)
            root.right = delete(root.right, value);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.data = minValue(root.right);
            root.right = delete(root.right, root.data);
        }
        root.height = 1 + Math.max(height(root.left), height(root.right));
        int balance = getBalance(root);
        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }

    public static void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    public static void preorder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    public static void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }

    public static boolean search(Node root, int value) {
        if (root == null)
            return false;
        if (root.data == value)
            return true;
        if (root.data > value)
            return search(root.left, value);
        return search(root.right, value);
    }

    public static void main(String[] args) {
        Node root = null;
        boolean flag = true;
        while (flag) {
            try {
                System.out.println("Select an option from the menu below");
                System.out.println("1. Insert a new node");
                System.out.println("2. Delete a node");
                System.out.println("3. Inorder traversal");
                System.out.println("4. Preorder traversal");
                System.out.println("5. Postorder traversal");
                System.out.println("6. Search a node");
                System.out.println("7. Exit");
                Scanner sc = new Scanner(System.in);
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Enter the value to be inserted");
                        int value = sc.nextInt();
                        root = insert(root, value);
                        break;
                    case 2:
                        System.out.println("Enter the value to be deleted");
                        int value1 = sc.nextInt();
                        root = delete(root, value1);

                        break;
                    case 3:
                        System.out.println("Inorder traversal of the tree is");
                        inorder(root);
                        System.out.println();
                        break;
                    case 4:
                        System.out.println("Preorder traversal of the tree is");
                        preorder(root);
                        System.out.println();
                        break;
                    case 5:
                        System.out.println("Postorder traversal of the tree is");
                        postorder(root);
                        System.out.println();
                        break;
                    case 6:
                        System.out.println("Enter the value to be searched");
                        int value2 = sc.nextInt();
                        search(root, value2);
                        break;
                    case 7:
                        flag = false;
                        break;
                    default:
                        System.out.println("Please choose a valid option from the menu");
                }
                inorder(root);
                System.out.println();
                if (flag == false) {
                    System.out.println("Exiting the program");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid input");
            }
        }
    }
}
