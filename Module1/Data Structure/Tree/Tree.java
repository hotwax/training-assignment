import java.util.Scanner;

public class Tree {
    //This is a static class used for creating new node for the tree.
    static class Node{
        private int data;
        Node left,right;
        /*
         * A parameterised constructor which will invoked automatically when an object of this calss will
         * intialised.
         */
        Node(int data){
            this.data = data;
            this.left = null;
            this.right= null;
        }
    }

    static class CreateTree{
        private Node root;
        /*
         * This method will be used for creating tree or we can add data into tree using this method.
         * Method will accept an integer value and then insert into tree at right place as per 
         * B.S.T rule.
         */
        public void add(int data){
            Node newNode = new Node(data);
            if(root == null){
                root = newNode;
                return;
            }
            Node curr = root;
            while(true){
                if(curr.data > data){
                    if(curr.left == null){
                        curr.left = newNode;
                        break;
                    }
                    curr = curr.left;
                }
                else{
                    if(curr.right == null){
                        curr.right = newNode;
                        break;
                    }
                    curr = curr.right;
                } 
            }
        }
        /*
         * This method can be used for sorting the tree.
         * Using this method we can find the inorder of the tree. 
         */
        public void inOrder(Node curr){
            if(curr==null)return ;
            inOrder(curr.left);
            System.out.print(curr.data+ " -->");
            inOrder(curr.right);
        }

        //This method will use to traverse(post order) the tree.
        public void postOrder(Node curr){
            if(curr==null)return ;
            postOrder(curr.left);
            postOrder(curr.right);
            System.out.print(curr.data+ " --> ");
        }

        //This method will use to traverse(pre order) the tree.
        public void preOrder(Node curr){
            if(curr == null)return;
            System.out.print(curr.data+"-->");
            preOrder(curr.left);
            preOrder(curr.right);
        }
        //This is a private function used for updating the value recusively.
        private void changeValue(int oldValue, int newValue, Node curr){
            if(curr == null)return;
            changeValue(oldValue, newValue, curr.left);
            if(curr.data == oldValue){
                curr.data = newValue;
                return ;
            }
            changeValue(oldValue, newValue, curr.right);
        }
        /*
         * This function is used to update a perticular value in tree.
         * Function accepts two arguments
         * 1. old value -> which needs to be change
         * 2. new value -> which will replace old value.
         */
        public void updateData(int oldValue, int newValue){
            if(root==null){
                System.out.println("Tree is empty. please add some data");
                return;
            }
            if(root.data == oldValue){
                root.data = newValue;
                return;
            }
            changeValue(oldValue, newValue, root);
            
        }

        /*
         * This method is used for searching an element in the tree.
         * The mehod excepts an data of integer type and return true if that data exists in the tree
         * else it will retur false.
         */
        public boolean search(int data){
            if(root==null){
                System.out.println("please enter some value in your tree.");
                return false;
            }
            Node temp = root;
            while(temp !=null){
                if(temp.data == data){
                    return true;
                }
                else if(temp.data > data){
                    temp = temp.left;
                }
                else{
                    temp = temp.right;
                }
            }
            return false;
        }

        //This function is used for searching largest element in the tree. just after the node passed
        //as an parameter 
        private Node findLargestNode(Node curr){
            curr = curr.right;
            while(curr != null && curr.left != null){
                curr = curr.left;
            }
            return curr;
        }
        /*
         * This method is used for deleting an element from the tree.
         * The method accepts an data of integer type and a head pointer.
         * And then returns the Node after deleting the element.
         */
        public Node delete(int data, Node node){
                if(node == null){
                    return null;
                }
                if(node.data > data){
                    node.left = delete(data, node.left);
                }
                else if(node.data <data){
                    node.right = delete(data, node.right);
                }
                else{
                    if(node.left == null){
                        Node temp = node.right;
                        return temp;
                    }
                    else if(node.right == null){
                        Node temp = node.left;
                        return temp;
                    }
                    else{
                        Node largest = findLargestNode(node);
                        node.data = largest.data;
                        node.right = delete( largest.data, root.right);
                    }
                }
                root = node;
                return node;
        }
    }
    public static void main(String[] args) {
        CreateTree obj = new CreateTree();
        Scanner input = new Scanner(System.in);

        // Dashboard options
        System.out.println("Enter 1 for adding new value.");
        System.out.println("Enter 2 for updating a value.");
        System.out.println("Enter 3 for deleting a value.");
        System.out.println("Enter 4 for displaying a value.");
        System.out.println("Enter 5 for searching.");
        System.out.println("Enter 6 to exit.");
        System.out.println("Enter your choice. ");
        int option;
        while(true){
            option = input.nextInt();
            if(option == 1){
                System.out.println("Enter a value");
                int value = input.nextInt();
                obj.add(value);
                System.out.println("value added successfully.");
            }
            else if(option == 2){
                System.out.println("Though you are trying to update the value but it will break the rule of B.S.T");
                System.out.println("Enter old value: ");
                int oldValue = input.nextInt();
                System.out.println("Enter new value: ");
                int newValue = input.nextInt();
                obj.updateData(oldValue, newValue);
                System.out.println("Data updated successfully.");
            }
            else if(option == 3){
                System.out.println("Enter a n element. ");
                int value = input.nextInt();
                obj.root = obj.delete(value, obj.root);
                System.out.println("Element deleted successfully");
            }
            else if(option == 4){
                System.out.print("Inorder: ");
                obj.inOrder(obj.root);
                System.out.println();
                System.out.print("Pre Order: ");
                obj.preOrder(obj.root);
                System.out.println();
                System.out.print("Post Order: ");
                obj.postOrder(obj.root);
                System.out.println();
            }
            else if(option == 5){
                System.out.println("Enter a number.");
                int num = input.nextInt();
                boolean bool = obj.search(num);
                if(bool){
                    System.out.println(num+" is there in the tree.");
                }
                else{
                    System.out.println("Unable to find the "+num+" in tree.");
                }
            }
            else if(option == 6){
                break;
            }
            else{
                System.out.println("Enter a valid option.");
            }
            System.out.println("Enter your choice. ");
        }
        
    }
}
