package Data_Structures.AVL_tree;

import java.util.Scanner;

public class AVL {

    Node head;

    class Node {

        int val, height;
        Node left, right;

        public Node(int val) {
            this.val = val;
            height = 1;
        }
    }

    //-------addition-------
    public void add(int value_To_add) {
        head = add(head, value_To_add);
    }

    public Node add(Node root, int value_To_add) {
        //if root is null then create newNode and return to head
        if (root == null) {
            return new Node(value_To_add);
        }
        //if value is smaller than root, then search in left subtree.
        if (root.val > value_To_add) {
            root.left = add(root.left, value_To_add);
        } //if value is greater than root, then search in right subtree.
        else if (root.val < value_To_add) {
            root.right = add(root.right, value_To_add);
        } else {
            return root;
        }

        // Update height of this ancestor node
        root.height = 1 + Math.max(height(root.left), height(root.right));

        //Get the balance factor of this ancestor node to check whether this node became unbalanced
        int balanceFactor = getBalanceFactor(root);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balanceFactor > 1 && value_To_add < root.left.val) {
            return rightRotate(root);
        }

        // Right Right Case
        if (balanceFactor < -1 && value_To_add > root.right.val) {
            return leftRotate(root);
        }

        // Left Right Case
        if (balanceFactor > 1 && value_To_add > root.left.val) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Left Case
        if (balanceFactor < -1 && value_To_add < root.right.val) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        //return the (unchanged) node 
        return root;
    }

    Node rightRotate(Node node) {
        Node nodeLeft = node.left;
        Node nodeLeftRight = nodeLeft.right;

        // Perform rotation
        nodeLeft.right = node;
        node.left = nodeLeftRight;

        // Update heights
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        nodeLeft.height = Math.max(height(nodeLeft.left), height(nodeLeft.right)) + 1;

        // Return new root
        return nodeLeft;
    }

    //-------deletion-------
    public void delete(int x) {
        head = delete(head, x);
    }

    Node delete(Node root, int x) {
        // perform simple bst delete
        if (root == null) {
            return root;
        }
        if (x < root.val) {
            root.left = delete(root.left, x);
        } else if (x > root.val) {
            root.right = delete(root.right, x);
        } else {

            // node with only one child or no child
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }
                // No child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } else // One child case
                //Copy the contents of the non-empty child
                {
                    root = temp;
                }
            } else {
                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                Node temp = minValueNode(root.right);
                // Copy the inorder successor's data to this node
                root.val = temp.val;
                // Delete the inorder successor
                root.right = delete(root.right, temp.val);
            }
        }
        // If the tree had only one node then return
        if (root == null) {
            return root;
        }

        //UPDATE HEIGHT OF THE CURRENT NODE
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        //GET THE BALANCE FACTOR OF THIS NODE (to check whether
        // this node became unbalanced)
        int balance = getBalanceFactor(root);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalanceFactor(root.left) >= 0) {
            System.out.println("right rotation");
            return rightRotate(root);
        }

        // Left Right Case
        if (balance > 1 && getBalanceFactor(root.left) < 0) {
            System.out.println("left rotation");
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalanceFactor(root.right) <= 0) {
            return leftRotate(root);
        }

        // Right Left Case
        if (balance < -1 && getBalanceFactor(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    // A utility function to print preorder traversal of
    // the tree. The function also prints height of every
    // node
    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.val + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    Node leftRotate(Node node) {
        Node nodeRight = node.right;
        Node nodeRightLeft = nodeRight.left;

        // Perform rotation
        nodeRight.left = node;
        node.right = nodeRightLeft;

        //  Update heights
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        nodeRight.height = Math.max(height(nodeRight.left), height(nodeRight.right)) + 1;

        // Return new root
        return nodeRight;
    }

    // Get Balance factor of node N
    int getBalanceFactor(Node N) {
        if (N == null) {
            return 0;
        }

        return height(N.left) - height(N.right);
    }

    int height(Node N) {
        if (N == null) {
            return 0;
        }

        return N.height;
    }

    //-------inorder-------
    public void inorder() {
        inorder(head);
        System.out.println();
    }

    public void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    //-------preorder-------
    public void preorder() {
        preorder(head);
        System.out.println();
    }

    public void preorder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        preorder(root.left);
        preorder(root.right);
    }

    //-------postorder-------
    public void postorder() {
        postorder(head);
        System.out.println();
    }

    public void postorder(Node root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.val + " ");
    }

    //-------Search-------
    public boolean search(int x) {
        Node curr = head;
        while (curr != null) {
            if (curr.val == x) {
                return true;
            } else if (curr.val > x) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return false;
    }

    public void menu() {

        boolean terminationFlag = true;
        while (terminationFlag) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("==========================");
                System.out.println("1: add Node");
                System.out.println("2: remove Node");
                System.out.println("3: inorder traversal");
                System.out.println("4: preorder traversal");
                System.out.println("5: postorder traversal");
                System.out.println("6: search");
                System.out.println("7: terminate the program");
                System.out.println();
                int n = sc.nextInt();
                switch (n) {
                    case 1:// add
                        System.out.println("enter Node value to add");
                        add(sc.nextInt());
                        break;
                    case 2:// delete
                        System.out.println("enter Node value to delete");
                        delete(sc.nextInt());
                        break;
                    case 3:// inorder
                        System.out.print("inorder-> ");
                        inorder();
                        break;
                    case 4:// preorder
                        System.out.print("preorder-> ");
                        preorder();
                        break;
                    case 5:// postorder
                        System.out.print("postorder-> ");
                        postorder();
                        break;
                    case 6:// search
                        System.out.println("enter value to search");
                        System.out.println(search(sc.nextInt()));
                        break;
                    case 7:// end
                        terminationFlag = false;
                        break;
                    default:
                        System.out.println("invalid input");
                        break;
                }
            } catch (Exception e) {
                //if user enter any other value than integer
                System.out.println("Invalid input: please enter Integer");
            }
        }
    }

    public static void main(String args[]) {
        AVL avl = new AVL();
        avl.menu();
    }
}
