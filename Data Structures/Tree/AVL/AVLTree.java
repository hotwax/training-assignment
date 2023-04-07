import java.util.InputMismatchException;
import java.util.Scanner;

class AVLNode {
    int key;
    int height;
    AVLNode left;
    AVLNode right;

    AVLNode(int key) {
        this.key = key;
        this.height = 1;
        this.left = null;
        this.right = null;
    }
}

class AVLTree {
    private AVLNode root;

    AVLTree() {
        root = null;
    }

    private int height(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int balanceFactor(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private AVLNode rightRotate(AVLNode y) {
        AVLNode newroot = y.left;
        AVLNode rightOfnewroot = newroot.right;

        // Perform rotation
        newroot.right = y;
        y.left = rightOfnewroot;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        newroot.height = Math.max(height(newroot.left), height(newroot.right)) + 1;

        // Return new root
        return newroot;
    }

    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    private AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public AVLNode insert(AVLNode node, int key) {
        // Perform normal BST insertion
        if (node == null) {
            return new AVLNode(key);
        }
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            return node; // Duplicate keys not allowed
        }

        // Update height of the current node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Get the balance factor of this node
        int balance = balanceFactor(node);

        // Left Left Case
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }

        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Return the unchanged node
        return node;
    }

    public AVLNode delete(AVLNode node, int key) {
        // Perform normal BST deletion
        if (node == null) {
            return node;
        }
        if (key < node.key) {
            node.left = delete(node.left, key);

        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else {
            // Node with only one child or no child
            if (node.left == null || node.right == null) {
                AVLNode temp = null;
                if (temp == node.left) {
                    temp = node.right;
                } else {
                    temp = node.left;
                }
                // No child case
                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    // One child case
                    node = temp; // Copy the contents of the non-empty child
                }
            } else {
                // Node with two children: Get the inorder successor (smallest
                // in the right subtree)
                AVLNode temp = minValueNode(node.right);
    
                // Copy the inorder successor's data to this node
                node.key = temp.key;
    
                // Delete the inorder successor
                node.right = delete(node.right, temp.key);
            }
        }
    
        // If the tree had only one node, then return
        if (node == null) {
            return node;
        }
    
        // Update the height of the current node
        node.height = 1 + Math.max(height(node.left), height(node.right));
    
        // Get the balance factor of this node
        int balance = balanceFactor(node);
    
        // Left Left Case
        if (balance > 1 && balanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }
    
        // Left Right Case
        if (balance > 1 && balanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
    
        // Right Right Case
        if (balance < -1 && balanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }
    
        // Right Left Case
        if (balance < -1 && balanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
    
        return node;
    }

    public   int search(AVLNode node, int key) {
        if (node == null) {
            return -1;
        }
        
        if (node.key == key) {
            return node.key;
        }
        
        if (node.key > key) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }


    public void updateElement(AVLNode root, int prevValue, int newValue) {
        if(prevValue == newValue) {
            System.out.println("New value is same as old value");
            return;
        }
        if (root == null) {
            System.out.println("The  is empty");
            return;
        }
        if (search(root, prevValue)!= -1) {
            delete(root, prevValue);
            insert(root, newValue);
        } else {
            System.out.println("The Node does not exist");
        }
    }
    
    private void inorder(AVLNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }


    public void postorderTraversal(AVLNode node) {
        if (node != null) {
            postorderTraversal(node.left);
            postorderTraversal(node.right);
            System.out.print(node.key + " ");
        }
    }

    public void preorderTraversal(AVLNode node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preorderTraversal(node.left);
            preorderTraversal(node.right);
        }
    }
    
    
    public void printInorder() {
        inorder(root);
        System.out.println();
    }

    public void printPostorder() {
        postorderTraversal(root);
        System.out.println();
    }


    public void printPreorder() {
        preorderTraversal(root);
        System.out.println();
    }
    
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        try{
        Scanner scanner = new Scanner(System.in);
    
        while (true) {
            System.out.println("\nAVL Tree Operations:");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. Print Inorder (Sorted)");
            System.out.println("5. Print Preorder");
            System.out.println("6. Print Postorder");
            System.out.println("7. Update Element");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
    
            switch (choice) {
                case 1:
                    System.out.print("Enter the key to insert: ");
                    int key = scanner.nextInt();
                    tree.root = tree.insert(tree.root, key);
                    break;
                case 2:
                    System.out.print("Enter the key to delete: ");
                    int keyToDelete = scanner.nextInt();
                    tree.root = tree.delete(tree.root, keyToDelete);

                    if (tree.root == null) {
                        System.out.println("Tree is empty.");
                    }
                    
                    break;
                case 3:
                    System.out.print("Enter thekey to search: ");


                    int keyToSearch = scanner.nextInt();
                    int result = tree.search(tree.root, keyToSearch);
                    if (result == -1) {
                    System.out.println("Key not found in the tree.");
                    } else {
                    System.out.println("Key found in the tree.");
                    }
                    break;

                case 4:
                    System.out.print("Inorder traversal of the tree: ");
                    tree.printInorder();
                    break;

                case 5:
                    System.out.print("Preorder traversal of the tree: ");
                    tree.printPreorder();
                    break;

                case 6:
                    System.out.print("Postorder traversal of the tree: ");
                    tree.printPostorder();
                    break;

                case 7:

                    System.out.print("Enter the key to update: ");
                    int prevValue = scanner.nextInt();
                    System.out.print("Enter the new key: ");
                    int newValue = scanner.nextInt();
                    tree.updateElement(tree.root, prevValue, newValue);
                    break;

                case 8:
                    System.out.println("Exiting the program...");
                    scanner.close();
                    System.exit(0);
                    default:
                    System.out.println("Invalid choice.");
                    }
                    }}
                    catch(InputMismatchException e){
                    System.out.println("Invalid input. Please enter a number.");}
                    catch(Exception e){
                    System.out.println("An error occured. Please try again.");
                    System.out.println(e);}
                    }
                    }
    
