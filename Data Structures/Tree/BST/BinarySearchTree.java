import java.util.InputMismatchException;
import java.util.Scanner;
public class BinarySearchTree 
{
    Node root;

    // Creating a inner class Node with fields - data, left and right nodes
    class Node {

        int data;
        Node left;
        Node right;

        // Constructor of the Node class
        public Node( int data)
        {
            this.data= data;
            left= null;
            right= null;
        }
    }

    // Method to search for a given value in the BST
        void search(int data)
        {
            searchNode(root,data);
        }


        // Recursive Function to Search for a given value in the BST
        void searchNode(Node root,int key)
        {
            if (root==null)
            {
                System.out.println("Not found");
                return;
            }
            if (root.data==key)
            {
                System.out.println("Found");
                return;
            }

            else if (key<root.data)
            {
                searchNode(root.left,key);
            }

            else if (key>root.data)
            {
                searchNode(root.right,key);
            }
        }


    // Method to update a given value in the BST
        void update(int data,int newdata)
        {
            updateNode(root,data,newdata);
        }

    //Helper function to update a given value in the BST
        void updateNode(Node root, int data, int newdata)
        {
            if (root==null)
            {
                System.out.println("Tree is empty");
            }

            delete(data);
            insert(newdata);
        }


    // Method to insert a new value in the BST
        void insert(int data)
        {
            root= insertNode(root,data);
        }


    // Method to delete a given value from the BST
        void delete(int data)
        {   
            boolean[] found = new boolean[1]; // using an array to pass found as reference

            found[0]= false;
            root= deleteNode(root,data, found);

            

            if (root==null)
            {
                System.out.println("Tree is empty, Cannot delete!");
                return;
            }

            if(found[0]==false){
                System.out.println("Key not found in tree!");
            }

            

        }


        Node deleteNode( Node root,int key,boolean [] found)
        {
            if (root==null)
            {
                return null;
            }


            if (key< root.data)
            {
                root.left= deleteNode(root.left,key,found);
            }

            else if (root.data <key)
            {
                root.right= deleteNode(root.right,key,found);
            }

            else
            {           
                found[0] = true; 

                if (root.left==null)
                {   
                    return root.right;
                }
                else if (root.right==null)
                {
                    return root.left;
                }

                else{

                    root.data = getInorderSuccessor( root.right);
                    root.right= deleteNode(root.right,root.data,found);

                }


            }

        
            return root;

        }


    // Method to get the inorder successor of a given node in the BST
        int getInorderSuccessor(Node root)
        {

            int minval= root.data;

            while  (root.left!= null)
            {
                minval= root.left.data;

                root= root.left;

            }

            return minval;

        }


    // Method to perform inorder traversal of the BST
        void inOrder()
        {
            inOrderTraversal(root);
        }

    // Recursive function to perform inorder traversal of the BST
        void inOrderTraversal(Node root)
    
        {
            if (root!= null)
            {
                inOrderTraversal(root.left);
                System.out.print(root.data + " ");
                inOrderTraversal(root.right);

            }

        }

        Node insertNode(Node root,int key)
        {
            if (root==null)
            {
                root= new Node(key);
                return root;
            }

            else if (key<root.data)
            {
                root.left= insertNode(root.left,key);
                
            }

            else if (key>root.data)
            {

                root.right= insertNode(root.right,key);
            }

            return root;

        }


        public static void main(String[] args) {


            try{

            Scanner sc = new Scanner(System.in);
            BinarySearchTree tree= new BinarySearchTree();
        
            int choice = 0;
        
            while (choice != 6) {
                System.out.println("\nBinary Search Tree Operations");
                System.out.println("1. Insert");
                System.out.println("2. Delete");
                System.out.println("3. Search");
                System.out.println("4. Update");
                System.out.println("5. Inorder Traversal");
                System.out.println("6. Exit");
        
                System.out.println("Enter your choice: ");
                choice = sc.nextInt();
        
                switch (choice) {
                    case 1:
                        System.out.println("Enter the element to be inserted: ");
                        int element = sc.nextInt();
                        tree.insert(element);
                        break;
        
                    case 2:
                        System.out.println("Enter the element to be deleted: ");
                        int elementToDelete = sc.nextInt();
                        tree.delete(elementToDelete);
                        break;
        
                    case 3:
                        System.out.println("Enter the element to be searched: ");
                        int elementToSearch = sc.nextInt();
                        tree.search(elementToSearch);
                        break;
        
                    case 4:
                        System.out.println("Enter the element to be updated: ");
                        int elementToUpdate = sc.nextInt();
                        System.out.println("Enter the new value: ");
                        int newElementValue = sc.nextInt();
                        tree.update(elementToUpdate, newElementValue);
                        break;
        
                    case 5:
                        System.out.println("Inorder Traversal: ");
                        tree.inOrder();
                        break;
        
                    case 6:
                        System.out.println("Exiting...");
                        break;
        
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            }
        sc.close();
        }
            catch(InputMismatchException e){
                System.out.println("Invalid input, Please enter a integer input. " );
                System.out.println("Exception: " + e + "");
            }
    
            catch(Exception e){
                System.out.println("Exception: " + e + "");
            }

        }
        

}



