// Java program to implement AVL Tree
import java.util.InputMismatchException;
import java.util.Scanner;

class AVLTree {
    public static void main(String args[]) {
        TreeNode root = null;
        // Variable to store the choice of the user
        int choice = 0;
        do {
            //  Printing the menu for the user
            System.out.println(" --------- MENU --------- ");
            System.out.println("Press 0 : Create a New AVL Tree");
            System.out.println("Press 1 : Insert an element");
            System.out.println("Press 2 : Delete an element");
            System.out.println("Press 3 : Update an element");
            System.out.println("Press 4 : Traverse the AVL Tree");
            System.out.println("Press 5 : Search an element");
            System.out.println("Press 6 : Sort the AVL Tree");
            System.out.println("Press 7 : Exit");
            System.out.println("Enter your choice : ");
            try {
                // Creating a scanner object to take input from the user
                Scanner sc = new Scanner(System.in);
                // Taking the choice from the user
                choice = sc.nextInt();
                //  Taking the choice from the user and performing the corresponding operation
                switch (choice) {
                    case 0:
                        System.out.print("Please Enter the first Node Value: ");
                        root = new TreeNode(sc.nextInt());
                        break;
                    case 1:
                        if (root != null) {
                            System.out.print("Please Enter the Node Value: ");
                            root.insert(root, sc.nextInt());
                        } else {
                            System.out.println("Please create a AVL first");
                        }
                        break;
                    case 2:
                        System.out.println("Please Enter the Node Value: ");
                        root.delete(root, sc.nextInt());
                        System.out.println("Node Deleted");
                        break;
                    case 3:
                        System.out.println("Please Enter the Node Value: ");
                        System.out.print("Please Enter the new Node Value: ");
                        root.updateElement(root, sc.nextInt(), sc.nextInt());
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
                                    root.inOrder(root);
                                    System.out.println();
                                    break;
                                case 1:
                                    System.out.println("Preorder Traversal");
                                    root.preOrder(root);
                                    System.out.println();
                                    break;
                                case 2:
                                    System.out.println("Postorder Traversal");
                                    root.postOrder(root);
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
                        boolean isPresent = root.searchElement(root, sc.nextInt());
                        if (isPresent) {
                            System.out.println("The Node is present in the Tree");
                        } else {
                            System.out.println("The Node is not present in the Tree");
                        }
                        break;
                    case 6:
                        System.out.print(" Sorted : ");
                        if (root != null) {
                            root.sortAVLTree(root);
                            System.out.println();
                        }
                        break;
                    case 7:
                        System.out.println("Program Terminated Successfully");
                        break;
                    default:
                        System.out.println("Invalid Input");
                }
            } catch (NullPointerException e){
                System.out.println("null pointer exception");
            }
            catch (InputMismatchException e) {
                System.out.println("Enter Integer Only");
            }
        } while (choice != 7);

    }
    // TreeNode Clas for the AVL Tree
    public static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
        int height;

        TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = 0;
        }
    // Method to update the element in the AVL Tree
        public void updateElement(TreeNode root, int oldValue, int newValue) {
            if(oldValue == newValue) {
                System.out.println("The new value is same as the old value");
                return;
            }
            if (root == null) {
                System.out.println("The  is empty");
                return;
            }
            if (searchElement(root, oldValue)) {
                delete(root, oldValue);
                insert(root, newValue);
            } else {
                System.out.println("The Node does not exist");
            }
        }

        // method to search in a tree
        public boolean searchElement(TreeNode root, int value) {
            if (root == null) return false;
            if (root.data == value) return true;
            if (root.data > value)
                return searchElement(root.left, value);
            return searchElement(root.right, value);

        }

        // method to delete a node from Tree
        public TreeNode delete(TreeNode root, int nodeToDelete) {
            if (root == null) return null;
            if (nodeToDelete < root.data) {
                root.left = delete(root.left, nodeToDelete);
            } else if (nodeToDelete > root.data) {
                root.right = delete(root.right, nodeToDelete);
            } else {
                if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                }
                root.data = minValue(root.right);
                root.right = delete(root.right, root.data);
            }
            return root;
        }
    // Function to find the minimum value in the AVL Tree : Used in the delete function
        private int minValue(TreeNode root) {
            int minValue = root.data;
            while (root.left != null) {
                minValue = root.left.data;
                root = root.left;
            }
            return minValue;
        }

        // function to get height of a node in AVL Tree
        private int getHeight(TreeNode node) {
            if (node != null) return node.height;
            return -1;
        }

        // function to get balance factor of a node
        private int getBalanceFactor(TreeNode node) {
            return getHeight(node.left) - getHeight(node.right);
        }

        // method to left rotate a node
        private TreeNode leftRotate(TreeNode node) {
            TreeNode rightChild = node.right;
            TreeNode leftSubtreeOfRightChild = rightChild.left;

            // Perform rotation
            rightChild.left = node;
            node.right = leftSubtreeOfRightChild;

            // Update heights
            node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
            rightChild.height = Math.max(getHeight(rightChild.left), getHeight(rightChild.right)) + 1;

            // Return new root
            return rightChild;
        }

        // method to right rotate a node
        private TreeNode rightRotate(TreeNode node) {
            TreeNode leftChild = node.left;
            TreeNode rightSubtreeOfLeftChild = leftChild.right;

            // Perform rotation
            leftChild.right = node;
            node.left = rightSubtreeOfLeftChild;

            // Update heights
            node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
            leftChild.height = Math.max(getHeight(leftChild.left), getHeight(leftChild.right)) + 1;

            // Return new root
            return leftChild;
        }

        // method to insert
        public TreeNode insert(TreeNode node, int data) {
            if (node == null) return new TreeNode(data);

            if (data < node.data)
                node.left = insert(node.left, data);
            else if (data > node.data)
                node.right = insert(node.right, data);
            else
                return node;


            node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
            int balanceFactor = getBalanceFactor(node);
            // Left Left Case
            if (balanceFactor > 1 && data < node.left.data)
                return rightRotate(node);
            // Right Right Case
            if (balanceFactor < -1 && data > node.right.data)
                return leftRotate(node);
            // Left Right Case
            if (balanceFactor > 1 && data > node.left.data) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            // Right Left Case
            if (balanceFactor < -1 && data < node.right.data) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
            return node;
        }

        // method to print the tree
        public void preOrder(TreeNode node) {
            if (node == null) return;
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
        // print the tree in order
        public void inOrder(TreeNode node) {
            if (node == null) return;
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
        // print the tree in post order
        public void postOrder(TreeNode node) {
            if (node == null) return;
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }
        //sorting the tree
        public void sortAVLTree(TreeNode root) {
            if (root == null) return;
            sortAVLTree(root.left);
            System.out.print(root.data + " ");
            sortAVLTree(root.right);
        }
    }
}
