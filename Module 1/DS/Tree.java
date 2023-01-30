import java.util.Scanner;

public class Tree {
    Node head;

    class Node {
        int val;
        Node left, right;

        public Node(int val) {
            this.val = val;
        }
    }

    // Insert Method
    public void insert(int x) {
        head = insert(head, x);
    }

    public Node insert(Node root, int x) {
        if (root == null)
            return new Node(x);
        if (root.val > x) {
            root.left = insert(root.left, x);
        } else if (root.val < x) {
            root.right = insert(root.right, x);
        }
        return root;
    }

    // Delete Method
    public void delete(int x) {
        head = delete(head, x);
    }

    public Node delete(Node root, int x) {
        if (root == null)
            return null;
        if (root.val > x) {
            root.left = delete(root.left, x);
        } else if (root.val < x) {
            root.right = delete(root.right, x);
        } else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            else {
                Node nearestLargest = getNearestLargest(root);
                root.val = nearestLargest.val;
                root.right = delete(root.right, nearestLargest.val);
            }
        }
        return root;
    }

    public Node getNearestLargest(Node root) {
        Node curr = root;
        while (curr == null && root.left == null) {
            curr = curr.left;
        }
        return curr;
    }

    // Sorting Method
    public void inorder() {
        inorder(head);
        System.out.println();
    }

    public void inorder(Node root) {
        if (root == null)
            return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    // Search Method
    public boolean search(int x) {
        Node curr = head;
        while (curr != null) {
            if (curr.val == x)
                return true;
            else if (curr.val > x)
                curr = curr.left;
            else
                curr = curr.right;
        }
        return false;
    }

    public void DashBoard() {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Insert");
        System.out.println("2. Delete");
        System.out.println("3. Sorting");
        System.out.println("4. Search");
        System.out.println("5. EXIT");
        boolean flag = true;
        while (flag) {
            int n = sc.nextInt();
            switch (n) {
                case 1:// Insert
                    System.out.println("Enter value to be inserted.");
                    insert(sc.nextInt());
                    System.out.println("The value is inserted. Continue.....");
                    break;
                case 2:// delete
                    System.out.println("Enter the value to be deleted.");
                    delete(sc.nextInt());
                    break;
                case 3:// inorder
                    System.out.print("Sorted:  ");
                    inorder();
                    break;
                case 4:// search
                    System.out.println("enter value to search");
                    System.out.println(search(sc.nextInt()));
                    break;
                case 5:// end
                    flag = false;
                    break;
                default:
                    System.out.println("Kindly choose the correct option.");
                    break;
            }
        }
    }

    public static void main(String args[]) {
        Tree t = new Tree();
        t.DashBoard();
    }
}