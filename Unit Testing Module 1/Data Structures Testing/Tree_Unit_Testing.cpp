#include<iostream>
#include <cassert>
using namespace std ;

// Creating Class Node 

class Node
{
    public:
    int data ;
    Node * left ;
    Node * right ;
    Node (int value)
    {
        data = value ;
        left = right = NULL ;
    }
};

// Creating Class Tree
class Tree
{
    //inserting the node in the tree
    public:
    Node* insertNode(Node * &root, int value)
    {
        if(root==NULL)
        {
            Node * newNode = new Node(value) ;
            root = newNode ;
            return root ;
        }
        else if(root->data<value)
        {
            root-> right = insertNode(root->right , value );
        }
        else
        {
            root->left = insertNode(root->left , value );
        }
        return root ;
    }

    // Searching Node in the Tree
    bool Search(Node* root , int value)
    {

        if(root==NULL)
        {
            return false ;
        }
        else if (root->data == value) 
        {
            return true ;
        }
        else if(value < root->data )
        {
            return Search(root->left , value);
        }
        else
        {
            return Search(root->right , value );
        }
    }

    // Preorder Traversal

    void preorder(Node *root)
    {
        if (!root)
        {
            return;
        }
        cout << root->data << " ";
        preorder(root->left);
        preorder(root->right);
    }

    // PostOrder Traversal

    void postorder(Node *root)
    {
        if (!root)
        {
            return;
        }

        postorder(root->left);
        postorder(root->right);
        cout << root->data << " ";
    }

    // Inorder Traversal

    void inorder(Node *root)
    {
        if (!root)
        {
            return;
        }

        inorder(root->left);
        cout << root->data << " ";
        inorder(root->right);
    }

    // Find minimum element in the Tree
    Node * findMin(Node* root)
    {
        if(root==NULL)
        {
            cout<<"Tree is empty"<<endl;
            return root ;
        }
        if(root->left == NULL)
        {
            return root->left ;
        }
        return findMin(root->left);
    }

    // Delete a Node from the tree
    Node* deleteNode(Node * root , int value)
    {
        if(root==NULL)
        {
            return root ;
        }
        else if(value > root->data)
        {
            root->right = deleteNode(root->right , value ); // recursive call
        }
        else if(value < root->data)
        {
            root->left = deleteNode(root->left , value );
        }
        else
        {
            if(root->left == NULL && root->right == NULL)
            {
                delete root ;
                root = NULL ;
            }
            else if(root->left == NULL)
            {
                Node * to_delete_node = root->right ;
                delete root ;
                root = to_delete_node ; 
            }
            else if(root->right == NULL )
            {
                Node * to_delete_node = root->left ;
                delete root ;
                root = to_delete_node ;
            }
            else 
            {
                Node * to_delete_node = findMin(root->right);
                root -> data = to_delete_node -> data  ;
                root->right = deleteNode(root->right , to_delete_node ->data );
            }
        }
        return root ;
    }

};

int main() {
    // create a new Tree object
    Tree tree;

    // test insertNode() method
    Node* root = NULL;
    root = tree.insertNode(root, 50);
    root = tree.insertNode(root, 30);
    root = tree.insertNode(root, 20);
    root = tree.insertNode(root, 40);
    root = tree.insertNode(root, 70);
    root = tree.insertNode(root, 60);
    root = tree.insertNode(root, 80);
    assert(tree.Search(root, 20) == true);
    assert(tree.Search(root, 10) == false);

    // test preorder() method
    std::cout << "Preorder Traversal: ";
    tree.preorder(root);
    std::cout << std::endl;

    // test postorder() method
    std::cout << "Postorder Traversal: ";
    tree.postorder(root);
    std::cout << std::endl;

    // test inorder() method
    std::cout << "Inorder Traversal: ";
    tree.inorder(root);
    std::cout << std::endl;

    // test deleteNode() method
    root = tree.deleteNode(root, 20);
    assert(tree.Search(root, 20) == false);
    root = tree.deleteNode(root, 30);
    assert(tree.Search(root, 30) == false);
    cout<<"All Test Cases Passed";
    return 0;
}