
// Import necessary Packages
import java.util.Scanner;
import java.util.InputMismatchException;

// Node class for AVL Tree
class AVLNode {
    int data;
    AVLNode left;
    AVLNode right;
    int height;

    public AVLNode(int data) {
        this.data = data;
        this.height = 1;
    }
}

public class AVL {
    public AVLNode root;

    // Method to insert a node in the AVL tree
    public AVLNode insert(AVLNode node, int data) {
        if (node == null) {
            return new AVLNode(data);
        }

        if (data < node.data) {
            node.left = insert(node.left, data);
        } else if (data > node.data) {
            node.right = insert(node.right, data);
        } else {
            return node; 
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        if (balance > 1 && data < node.left.data) {
            return rotateRight(node);
        }

        if (balance < -1 && data > node.right.data) {
            return rotateLeft(node);
        }

        if (balance > 1 && data > node.left.data) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && data < node.right.data) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // Method to delete a node from the AVL tree
    public AVLNode delete(AVLNode node, int data) {
        if (node == null) {
            return null;
        }

        if (data < node.data) {
            node.left = delete(node.left, data);
        } else if (data > node.data) {
            node.right = delete(node.right, data);
        } else {
            if (node.left == null || node.right == null) {
                AVLNode temp = null;
                if (temp == node.left) {
                    temp = node.right;
                } else {
                    temp = node.left;
                }

                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                AVLNode temp = minValueNode(node.right);
                node.data = temp.data;
                node.right = delete(node.right, temp.data);
            }
        }

        if (node == null) {
            return null;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0) {
            return rotateRight(node);
        }

        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && getBalance(node.right) <= 0) {
            return rotateLeft(node);
        }

        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // Method to search a node from the AVL tree
    public boolean search(int data) {
        return search(root, data);
    }

    public boolean search(AVLNode node, int data) {
        if (node == null) {
            return false;
        }

        if (data == node.data) {
            return true;
        } else if (data < node.data) {
            return search(node.left, data);
        } else {
            return search(node.right, data);
        }
    }
    
    // Preorder traversal of the AVL tree
    public void preorder() {
        preorder(root);
    }

    public void preorder(AVLNode node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }

     // Inorder traversal of the AVL tree
    public void inorder() {
        inorder(root);
    }

    public void inorder(AVLNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data + " ");
            inorder(node.right);
        }
    }

    // Postorder traversal of the AVL tree
    public void postorder() {
        postorder(root);
    }

    public void postorder(AVLNode node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.data + " ");
        }
    }

    // Get height of a node
    public int height(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

     // Get the balance factor of a node
    public int getBalance(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    // Rotate a node to the right
    public AVLNode rotateRight(AVLNode node) {
        AVLNode leftChild = node.left;
        AVLNode rightChildOfLeftChild = leftChild.right;

        leftChild.right = node;
        node.left = rightChildOfLeftChild;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        leftChild.height = 1 + Math.max(height(leftChild.left), height(leftChild.right));

        return leftChild;
    }

    // Rotate a node to the left
    public AVLNode rotateLeft(AVLNode node) {
        AVLNode rightChild = node.right;
        AVLNode leftChildOfRightChild = rightChild.left;

        rightChild.left = node;
        node.right = leftChildOfRightChild;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        rightChild.height = 1 + Math.max(height(rightChild.left), height(rightChild.right));

        return rightChild;
    }

    // to find node with the minimum value in a subtree
    public AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            AVL avlTree = new AVL();
            int choice;
            do {
                System.out.println("1. Insert element");
                System.out.println("2. Delete element");
                System.out.println("3. Search element");
                System.out.println("4. Preorder traversal");
                System.out.println("5. Inorder traversal");
                System.out.println("6. Postorder traversal");
                System.out.println("7. Exit");

                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter the element to insert: ");
                        int insertElement = scanner.nextInt();
                        avlTree.root = avlTree.insert(avlTree.root, insertElement);
                        break;
                    case 2:
                        System.out.print("Enter the element to delete: ");
                        int deleteElement = scanner.nextInt();
                        if (avlTree.search(deleteElement)) {
                            avlTree.root = avlTree.delete(avlTree.root, deleteElement);
                            System.out.println("Element deleted successfully.");
                        } else {
                            System.out.println("Element not found in the tree.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter the element to search: ");
                        int searchElement = scanner.nextInt();
                        if (avlTree.search(searchElement)) {
                            System.out.println("Element found in the tree.");
                        } else {
                            System.out.println("Element not found in the tree.");
                        }
                        break;
                    case 4:
                        System.out.print("Preorder traversal: ");
                        avlTree.preorder();
                        System.out.println();
                        break;
                    case 5:
                        System.out.print("Inorder traversal: ");
                        avlTree.inorder();
                        System.out.println();
                        break;
                    case 6:
                        System.out.print("Postorder traversal: ");
                        avlTree.postorder();
                        System.out.println();
                        break;
                    case 7:
                        System.out.println("Exit done");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice");
                }
            } while (choice != 7);
        } catch (InputMismatchException exception) {
            System.out.println(exception);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}