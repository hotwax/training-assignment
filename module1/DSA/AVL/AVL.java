package com.java.demo;

import java.util.InputMismatchException;
import java.util.Scanner;

// The AVL class contains an inner class node, which represents a node in the AVL tree. Each node has a data value, left and right child nodes, and a height field used to balance the tree.
public class AVL {

    class Node {
        int data;
        Node left;
        Node right;
        int height;

        Node(int val) {
            this.data = val;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    @SuppressWarnings("unused")
    private Node root;

    AVL() {
        root = null;
    }

    // to find out max value
    @SuppressWarnings("unused")
    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // to calculate height of a node
    @SuppressWarnings("unused")
    private int height(Node n) {
        if (n == null) {
            return 0;
        }
        return n.height;
    }

    // The rightRotate methods are used to balance the tree by performing rotations
    // on nodes.
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node z = x.right;

        x.right = y;
        y.left = z;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // The leftRotate methods are used to balance the tree by performing rotations
    // on nodes.
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node z = y.left;

        y.left = x;
        x.right = z;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.left)) + 1;

        return y;
    }

    // The getBalance method is used to calculate the balance factor of a node.
    int getBalance(Node n) {
        if (n == null)
            return 0;
        return height(n.left) - height(n.right);
    }

    // isEmpty method is used to find if the tree is empty or not
    Boolean isEmpty() {
        if (root == null) {
            return true;
        } else {
            return false;
        }
    }

    // insertion method to call insertionRecursively method
    void insertion(int data) {
        root = insertionRecursively(root, data);

    }

    // insertionRecursively method to insert the data recursively
    private Node insertionRecursively(Node n, int data) {
        if (n == null) {
            Node newnode = new Node(data);
            n = newnode;
            return n;

        }

        if (n.data > data) {
            n.left = insertionRecursively(n.left, data);
        } else if (n.data < data) {
            n.right = insertionRecursively(n.right, data);
        } else {
            return n;
        }

        n.height = 1 + max(height(n.left), height(n.right));
        int balance = getBalance(n);

        if (balance > 1 && data < n.left.data) {
            return rightRotate(n);
        }

        if (balance < -1 && data > n.right.data) {
            return leftRotate(n);
        }

        if (balance > 1 && data > n.left.data) {
            n.left = leftRotate(n.left);
            return rightRotate(n);
        }
        if (balance < -1 && data < n.right.data) {
            n.right = rightRotate(n.right);
            return leftRotate(n);
        }

        return n;
    }

    // inorder method to traverse a tree.
    void inorder() {
        if (root == null) {
            return;
        }
        inorderRecursively(root);
    }

    // inorderRecursively method to display or traverse a data recursively
    private void inorderRecursively(Node n) {
        if (n == null)
            return;

        inorderRecursively(n.left);
        System.out.print(n.data + " ");
        inorderRecursively(n.right);
    }

    // This function deletes a node with the given key from the AVL tree
    void deletion(int key) {
        if (root == null) {
            System.out.println("-1");
            return;
        }
        root = deleteValue(root, key);
        if (root == null)
            return;
        // check the Tree is Balanced after deletion
        root.height = 1 + max(height(root.left), height(root.right));
        int balance = getBalance(root);

        // rightrotate
        if (balance > 1 && getBalance(root.left) >= 0) {
            root = rightRotate(root);
        }
        // leftrotate
        else if (balance < -1 && getBalance(root.right) <= 0) {
            root = leftRotate(root);
        }
        // left right rotate
        else if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            root = rightRotate(root);

        }
        // right left rotate
        else if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            root = leftRotate(root);
        }

    }

    // This function deletes the node with the given key from the AVL tree rooted at
    // n
    private Node deleteValue(Node n, int key) {
        if (n == null) {
            return n;
        }
        if (n.data == key) {
            if (n.left == null && n.right == null) {
                return null;
            } else if (n.left != null && n.right == null) {
                return n.left;
            } else if (n.left == null && n.right != null) {
                return n.right;
            } else if (n.left != null && n.right != null) {
                Node newnode = n.right;
                while (newnode.left != null) {
                    newnode = newnode.left;
                }
                n.data = newnode.data;
                n.right = deleteValue(n.right, newnode.data);
                return n;
            }

        } else if (n.data > key) {
            n.left = deleteValue(n.left, key);
            return n;
        } else {
            n.right = deleteValue(n.right, key);
            return n;
        }
        return n;
    }

    // update method to update a value
    void update(int newValue, int oldValue) {
        deletion(oldValue);
        insertion(newValue);

    }

    // search method to call Search_Recursively
    Boolean search(int key) {
        return Search_Recursively(root, key);
    }

    // Search_Recursively to search the key recursively
    Boolean Search_Recursively(Node n, int key) {
        if (n == null)
            return false;
        if (n.data == key) {
            return true;
        } else if (n.data < key) {
            return Search_Recursively(n.right, key);
        } else {
            return Search_Recursively(n.left, key);
        }
    }

    public static void main(String[] args) {
        AVL avl = new AVL();
        boolean flag = true;
        // running the while loop till flag is false
        while (flag) {
            System.out.println("Choose an option from the Dashboard given below:\n");
            System.out.println("1. Insert a data in AVL Tree");
            System.out.println("2. Display AVL Tree");
            System.out.println("3. Delete a data in AVL Tree");
            System.out.println("4. Update a data in AVL Tree ");
            System.out.println("5. Search a data in AVL Tree ");
            System.out.println("6. EXIT");
            System.out.println();
            Scanner sc = new Scanner(System.in);

            { // Menu driven
                try {
                    int ch = sc.nextInt();
                    if (ch < 1 || ch > 6) {
                        System.out.println("Kindly enter the correct option.");
                    } else {
                        switch (ch) {
                            case 1:
                                System.out.println("Enter the value");
                                int insertData = sc.nextInt();
                                avl.insertion(insertData);
                                break;
                            case 2:
                                avl.inorder();
                                System.out.println();
                                break;
                            case 3:
                                System.out.println("Enter the value to be deleted");
                                int toDeleteValue = sc.nextInt();
                                avl.deletion(toDeleteValue);
                                break;
                            case 4:
                                System.out.println("Enter the new value");
                                int newValue = sc.nextInt();
                                System.out.println("Enter the old value");
                                int oldValue = sc.nextInt();
                                avl.update(newValue, oldValue);
                                break;
                            case 5:
                                System.out.println("Enter a value to search");
                                int search_value = sc.nextInt();
                                if (avl.search(search_value)) {
                                    System.out.println("Value present in a AVL Tree");
                                    System.out.println();
                                } else {
                                    System.out.println("Value does not present in a AVL Tree");
                                    System.out.println();
                                }
                                break;
                            case 6:
                                flag = false;
                                System.out.println("Programe Terminated");
                                break;
                        }
                    }

                } catch (InputMismatchException ex) {
                    System.out.println("Enter a correct choice ");
                }
            }

        }

    }

}
