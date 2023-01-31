import java.util.Scanner;

public class TreeBST {

    Node root;

    static class Node {
        int data;
        Node left, right;

        Node(int d) {
            data = d;
            left = right = null;
        }
    }

    // Method to insert a new element in the tree
    public static TreeBST insert(TreeBST tree, int value) {
        Node temp = new Node(value);
        if (tree.root == null) {
            tree.root = temp;
            return tree;
        }
        Node temp1 = tree.root;
        Node temp2 = null;

        // Traverse the tree to find the position to insert the new node
        while (temp1 != null) {
            temp2 = temp1;
            if (value > temp1.data) {
                temp1 = temp1.right;
            } else {
                temp1 = temp1.left;
            }
        }
        if (value > temp2.data) {
            temp2.right = temp;
        } else {
            temp2.left = temp;
        }
        return tree;
    }

    // Method to delete an element from the tree
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
        return root;
    }

    // Method to find the minimum value in the tree
    public static int minValue(Node root) {
        int minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    // Method to print the tree
    public static void printTree(Node temp) {
        if (temp == null)
            return;
        printTree(temp.left);
        System.out.println(temp.data);
        printTree(temp.right);
    }

    public static void main(String[] args) {
        TreeBST tree = new TreeBST();
        Boolean flag = true;
        Scanner sc = new Scanner(System.in);
        while (flag) {
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter the value to be inserted: ");
                    int value = sc.nextInt();
                    tree = insert(tree, value);
                    break;
                case 2:
                    System.out.print("Enter the value to be deleted: ");
                    value = sc.nextInt();
                    delete(tree.root, value);
                    break;
                case 3:
                    flag = false;
                    break;
            }
            printTree(tree.root);
            if (flag == false) {
                System.out.println("Exiting the program");
            }
        }
    }

}
