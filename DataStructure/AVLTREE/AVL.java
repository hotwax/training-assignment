package AVLTREE;

import java.util.Scanner;

public class AVL {

       class Node {
              int data;
              int height;
              Node left;
              Node right;

              Node(int data) {
                     this.data = data;
                     this.left = null;
                     this.right = null;
                     this.height = 1;
              }
       }

       private Node root;

       AVL() {
              root = null;
       }

       private int height(Node node) {
              if (node == null) {
                     return 0;
              }

              return node.height;

       }

       private int max(int a, int b) {
              return a > b ? a : b;
       }

       private Node rightrotate(Node node) {
              Node newnode = node.left;
              Node rightnewnode = newnode.right;

              // right rotate
              newnode.right = node;
              node.left = rightnewnode;

              node.height = 1 + max(height(node.left), height(node.right));
              newnode.height = 1 + max(height(newnode.left), height(newnode.right));

              return newnode;
       }

       private Node leftrotate(Node node) {
              Node newnode = node.right;
              Node leftnewnode = newnode.left;

              // rotate
              newnode.left = node;
              node.right = leftnewnode;

              node.height = max(height(node.left), height(node.right)) + 1;
              newnode.height = max(height(newnode.left), height(newnode.right)) + 1;

              return newnode;
       }

       private int getbalance(Node node) {
              if (node == null) {
                     return 0;
              }

              return height(node.left) - height(node.right);

       }

       void insertion(int data) {
              root = insertionrec(root, data);

       }

       private Node insertionrec(Node node, int data) {
              if (node == null) {
                     Node newnode = new Node(data);
                     node = newnode;

                     return node;
              }

              if (node.data > data) {
                     node.left = insertionrec(node.left, data);
              } else if (node.data < data) {
                     node.right = insertionrec(node.right, data);
              } else {
                     return node;
              }

              node.height = 1 + max(height(node.left), height(node.right));
              int balance = getbalance(node);

              // Right Rotate
              if (balance > 1 && data < node.left.data) {
                     return rightrotate(node);
              }

              // Leftrotate
              if (balance < -1 && data > node.right.data) {
                     return leftrotate(node);
              }

              // Left Right Rotate
              if (balance > 1 && data > node.left.data) {
                     node.left = leftrotate(node.left);
                     return rightrotate(node);
              }

              // Right Left Rotate
              if (balance < -1 && data < node.right.data) {
                     node.right = rightrotate(node.right);
                     return leftrotate(node);
              }

              return node;
       }

       // Inorder Traversal in BST
       void inorder() {
              if (root == null) {
                     System.out.println("BST is empty");
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

       // preorder Traversal in BST

       void preorder() {
              if (root == null) {
                     System.out.println("BST is empty");
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
                     System.out.println("BST is empty");
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
              root = deleted(root, key);
              if (root == null)
                     return;
              // check the Tree is Balanced after deletion
              root.height = 1 + max(height(root.left), height(root.right));
              int balance = getbalance(root);

              // rightrotate
              if (balance > 1 && getbalance(root.left) >= 0) {
                     root = rightrotate(root);
              }
              // leftrotate
              else if (balance < -1 && getbalance(root.right) <= 0) {
                     root = leftrotate(root);
              }
              // left right rotate
              else if (balance > 1 && getbalance(root.left) < 0) {
                     root.left = leftrotate(root.left);
                     root = rightrotate(root);

              }
              // right left rotate
              else if (balance < -1 && getbalance(root.right) > 0) {
                     root.right = rightrotate(root.right);
                     root = leftrotate(root);
              }

       }

       private Node deleted(Node node, int key) {
              if (node == null) {
                     return node;
              }
              if (node.data == key) {
                     if (node.left == null && node.right == null) {
                            return null;
                     } else if (node.left != null && node.right == null) {
                            return node.left;
                     } else if (node.left == null && node.right != null) {
                            return node.right;
                     } else if (node.left != null && node.right != null) {
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
                     System.out.println("BST is empty");
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
              AVL avl = new AVL();
              try (Scanner input = new Scanner(System.in)) {
                     int choices;
                     do {
                            System.out.println();
                            System.out.println("Operations in Avl Tree");
                            System.out.println();
                            System.out.println("  1.Insertion in AVL tree");
                            System.out.println("  2.Searching in AVL tree");
                            System.out.println("  3.Inorder Traversal");
                            System.out.println("  4.Preorder Traversal");
                            System.out.println("  5.Postorder Traversal");
                            System.out.println("  6.Morris Traversal");
                            System.out.println("  7.Deletion in AVL tree");
                            System.out.println("  8.Exit");
                            System.out.println();
                            System.out.println("Enter the choices");
                            System.out.println();
                            choices = input.nextInt();
                            System.out.println();
                            switch (choices) {
                                   case 1:

                                          System.out.println("Enter the data");
                                          int d = input.nextInt();
                                          avl.insertion(d);
                                          break;

                                   case 2:
                                          System.out.println();
                                          System.out.println("Enter the key");
                                          int key = input.nextInt();
                                          if (avl.search(key)) {
                                                 System.out.println("Key is found in AVL tree");
                                          } else {
                                                 System.out.println("Key is not Found in AVL tree");
                                          }
                                          System.out.println();
                                          break;

                                   case 3:
                                          System.out.println();
                                          System.out.println("Inorder traversal -");
                                          avl.inorder();
                                          System.out.println();
                                          break;

                                   case 4:
                                          System.out.println();
                                          System.out.println("Preorder traversal -");
                                          avl.preorder();
                                          System.out.println();
                                          break;

                                   case 5:
                                          System.out.println();
                                          System.out.println("Postorder traversal -");
                                          avl.postorder();
                                          System.out.println();
                                          break;

                                   case 6:
                                          System.out.println();
                                          System.out.println("Morris Traversal -");
                                          avl.morristraversal();
                                          System.out.println();
                                          break;

                                   case 7:
                                          System.out.println();
                                          System.out.println("Enter the key ");
                                          int k = input.nextInt();
                                          if (avl.search(k)) {
                                                 avl.deletion(k);
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
              }

       }

}
