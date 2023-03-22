public class Tree {

    
class Node{
    int data;
    Node left,right;
    Node(int data){
        this.data = data;
        this.left = null;
        this.right= null;
    }
}


class CreateTree{
    Node root;
    public void add(int data){
        Node newNode = new Node(data);
        if(root == null){
            root = newNode;
            return;
        }
        Node curr = root;
        while(true){
            if(curr.data > data)curr = curr.left;
            else if(curr.data < data)curr = curr.right;
            if(curr == null)break;
        }
        curr = newNode;
    }

    public void inOrder(Node curr){
        
    }


}
    public static void main(String[] args) {
        
    }
}
