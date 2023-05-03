package AVL;
import java.util.InputMismatchException;
import java.util.Scanner;

//AVL class
public class AVL {
       
       //Node class
       class Node {
              int data;
              int height;
              Node left;
              Node right;

              // constructor
              Node(int val) {
                     this.data = val;
                     this.left = null;
                     this.right = null;
                     this.height = 1;
              }
       }

       // root Node
       private Node root;

       // Constructor
       AVL() {
              root = null;
       }

       // return height of Avl Tree
       private int height(Node node) {
              if (node == null) {
                     return 0;
              }
              return node.height;
       }

       private int maximum(int parameter1, int parameter2) {
              return parameter1 > parameter2 ? parameter1 : parameter2;
       }

       // Right Rotate
       private Node rightRotate(Node node) {
              Node newnode = node.left;
              Node rightnewnode = newnode.right;
              // right rotate
              newnode.right = node;
              node.left = rightnewnode;
              node.height = 1 + maximum(height(node.left), height(node.right));
              newnode.height = 1 + maximum(height(newnode.left), height(newnode.right));
              return newnode;
       }

       // Left Rotate
       private Node leftRotate(Node node) {
              Node newnode = node.right;
              Node leftnewnode = newnode.left;
              // rotate
              newnode.left = node;
              node.right = leftnewnode;
              node.height = maximum(height(node.left), height(node.right)) + 1;
              newnode.height = maximum(height(newnode.left), height(newnode.right)) + 1;
              return newnode;
       }

       // Get the Balance of tree
       private int getBalance(Node node) {
              if (node == null) {
                     return 0;
              }
              return height(node.left) - height(node.right);
       }

       // check Avl tree is empty or not
       Boolean isEmpty() {
              if (root == null) {
                     return true;
              }
              return false;
       }

       // Insertion in Avl Tree
       void insertion(int data) {
              root = insertionRec(root, data);
       }

       private Node insertionRec(Node rootNode, int data) {
              if (rootNode == null) {
                     Node newnode = new Node(data);
                     rootNode = newnode;
                     return rootNode;
              }
              if (rootNode.data > data) {
                     rootNode.left = insertionRec(rootNode.left, data);
              } else if (rootNode.data < data) {
                     rootNode.right = insertionRec(rootNode.right, data);
              } else {
                     return rootNode;
              }
              rootNode.height = 1 + maximum(height(rootNode.left), height(rootNode.right));
              int balance = getBalance(rootNode);

              // Right Rotate
              if (balance > 1 && data < rootNode.left.data) {
                     return rightRotate(rootNode);
              }

              // Leftrotate
              if (balance < -1 && data > rootNode.right.data) {
                     return leftRotate(rootNode);
              }

              // Left Right Rotate
              if (balance > 1 && data > rootNode.left.data) {
                     rootNode.left = leftRotate(rootNode.left);
                     return rightRotate(rootNode);
              }

              // Right Left Rotate
              if (balance < -1 && data < rootNode.right.data) {
                     rootNode.right = rightRotate(rootNode.right);
                     return leftRotate(rootNode);
              }
              return rootNode;
       }

       // Inorder Traversal in AVL
       void inorder() {
              if (root == null) {
                     System.out.println("-1");
                     return;
              }
              inorderRec(root);
       }

       private void inorderRec(Node rootNode) {
              if (rootNode == null) {
                     return;
              }
              inorderRec(rootNode.left);
              System.out.print(rootNode.data + "  ");
              inorderRec(rootNode.right);
       }

       // preorder Traversal in AVL
       void preorder() {
              if (root == null) {
                     System.out.println("-1");
                     return;
              }
              preorderRec(root);
       }

       private void preorderRec(Node rootNode) {
              if (rootNode == null) {
                     return;
              }
              System.out.print(rootNode.data + "  ");
              preorderRec(rootNode.left);
              preorderRec(rootNode.right);
       }

       // Postorder Traversal in AVL
       void postorder() {
              if (root == null) {
                     System.out.println("-1");
                     return;
              }
              postorderRec(root);
       }

       private void postorderRec(Node rootNode) {
              if (rootNode == null) {
                     return;
              }
              postorderRec(rootNode.left);
              postorderRec(rootNode.right);
              System.out.print(rootNode.data + "  ");
       }

       // Delete a key if it is present in Avl Tree
       void deletion(int key) {
              if (root == null) {
                     System.out.println("-1");
                     return;
              }
              root = deleted(root, key);
              if (root == null)
                     return;
              // check the Tree is Balanced after deletion
              root.height = 1 + maximum(height(root.left), height(root.right));
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

       private Node deleted(Node rootNode, int key) {
              if (rootNode == null) {
                     return rootNode;
              }

              //root node is a key
              if (rootNode.data == key) {
                     
                     //both child is null
                     if (rootNode.left == null && rootNode.right == null) {
                            return null;
                     }
                     //left child is not null
                     else if (rootNode.left != null && rootNode.right == null) {
                            return rootNode.left;
                     } 
                     //right child is not null
                     else if (rootNode.left == null && rootNode.right != null) {
                            return rootNode.right;
                     } 
                     //both child is not null
                     else if (rootNode.left != null && rootNode.right != null) {
                            Node newnode = rootNode.right;
                            while (newnode.left != null) {
                                   newnode = newnode.left;
                            }
                            rootNode.data = newnode.data;
                            rootNode.right = deleted(rootNode.right, newnode.data);
                            return rootNode;
                     }
              } else if (rootNode.data > key) {
                     rootNode.left = deleted(rootNode.left, key);
                     return rootNode;
              } else {
                     rootNode.right = deleted(rootNode.right, key);
                     return rootNode;
              }
              return rootNode;
       }

       // Iterative way of tree traversal
       void morrisTraversal() {
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

       // Search a key in Avl Tree
       boolean searchKey(int key) {
              return searchRec(root, key);
       }

       private boolean searchRec(Node rootNode, int key) {
              if (rootNode == null) {
                     return false;
              }
              if (rootNode.data == key) {
                     return true;
              } else if (rootNode.data < key) {
                     return searchRec(rootNode.right, key);
              } else {
                     return searchRec(rootNode.left, key);
              }
       }

       public static void main(String[] args) {
              AVL avlTree = new AVL();
              try (Scanner input = new Scanner(System.in)) {
                     String choices;
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
                            choices = input.next();
                            System.out.println();
                            switch (choices) {
                                   case "1":
                                          System.out.println("Enter the data");
                                          int d = input.nextInt();
                                          avlTree.insertion(d);
                                          break;

                                   case "2":
                                          System.out.println();
                                          System.out.println("Enter the key");
                                          int key = input.nextInt();

                                          //check avl tree is empty or not
                                          if (avlTree.isEmpty()) {
                                                 System.out.println("Avl tree is empty");
                                                 break;
                                          }
                                          //search the key in avl tree
                                          if (avlTree.searchKey(key)) {
                                                 System.out.println("Key is found in AVL tree");
                                          } else {
                                                 System.out.println("Key is not Found in AVL tree");
                                          }
                                          System.out.println();
                                          break;

                                   case "3":
                                          System.out.println();
                                          if (avlTree.isEmpty()) {
                                                 System.out.println("Avl tree is empty");
                                                 break;
                                          }
                                          System.out.println("Inorder traversal -");
                                          avlTree.inorder();
                                          System.out.println();
                                          break;

                                   case "4":
                                          System.out.println();
                                          if (avlTree.isEmpty()) {
                                                 System.out.println("Avl tree is empty");
                                                 break;
                                          }
                                          System.out.println("Preorder traversal -");
                                          avlTree.preorder();
                                          System.out.println();
                                          break;

                                   case "5":
                                          System.out.println();
                                          if (avlTree.isEmpty()) {
                                                 System.out.println("Avl tree is empty");
                                                 break;
                                          }
                                          System.out.println("Postorder traversal -");
                                          avlTree.postorder();
                                          System.out.println();
                                          break;

                                   case "6":
                                          System.out.println();
                                          if (avlTree.isEmpty()) {
                                                 System.out.println("Avl tree is empty");
                                                 break;
                                          }
                                          System.out.println("Morris Traversal -");
                                          avlTree.morrisTraversal();
                                          System.out.println();
                                          break;

                                   case "7":
                                          System.out.println();
                                          System.out.println("Enter the key ");
                                          int keyForDeletion = input.nextInt();
                                          if (avlTree.isEmpty()) {
                                                 System.out.println("Avl tree is empty");
                                                 break;
                                          }
                                          if (avlTree.searchKey(keyForDeletion)) {
                                                 avlTree.deletion(keyForDeletion);
                                                 System.out.println("deleted Key will be-" + keyForDeletion);
                                          } else {
                                                 System.out.println("Key not found");
                                          }
                                          System.out.println();
                                          break;

                                   case "8":
                                          System.out.println("Thank you");
                                          break;

                                   default:
                                          System.out.println("Invalid Choice");
                                          break;
                            }
                     } while (!choices.equals("8"));
              } catch (InputMismatchException exception) {
                     System.out.println(exception);
              } catch (Exception exception) {
                     System.out.println(exception);
              }
       }
}
