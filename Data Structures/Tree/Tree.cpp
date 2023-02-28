#include<iostream>
#include<bits/stdc++.h>
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

int main()
{
    int choice, value;
    Tree t ;
    Node * root = NULL ;
    do{
        // Display menu
        cout << "\nTree Menu\n";
        cout << "====================\n";
        cout << "1. Insert Node\n";
        cout << "2. Delete Node\n";
        cout << "3. Search Element\n";
        cout << "4. Traverse the Tree\n";
        cout << "5. Exit\n";
        cout << "Enter your choice: ";
        cin >> choice;

        switch(choice)
        {
            case 1:
            {
                cout<<"Enter the value of Node"<<endl;
                int value ;
                cin>>value ;
                root = t.insertNode(root , value);
                break ;
            }
            case 2 :
            {
                cout<<"Enter the value of Node to delete"<<endl;
                int value ;
                cin>>value  ;
                if(t.deleteNode(root, value)) 
                {
                    cout<<"Operation Successful"<<endl;
                }
                else
                {
                    cout<<"Element Not present"<<endl;
                }
                break ;
            }
            case 3 :
            {
                cout<<"Enter the value of Node to Search"<<endl;
                int value ;
                cin>>value ;
                int isExist = t.Search(root , value);
                if(isExist) cout<<"Element Found"<<endl;
                else cout<<"Element Not Found"<<endl;
                break ;

            }
            case 4 :
            {
                cout<<"Traversal Techniques :- "<<endl;
                cout<<"1. PreOrder"<<endl;
                cout<<"2. PostOrder"<<endl;
                cout<<"3. Inorder"<<endl;
                cout<<"Enter Your Choice"<<endl;
                int choice1 = 0 ;
                cin>>choice1 ;
                if(choice1==1) t.preorder(root);
                else if(choice1==2) t.postorder(root);
                else if(choice1==3) t.inorder(root);
                else cout<<"Invalid Choice"<<endl;
                cout<<endl;
                break ;
            }
            case 5:
            {
                cout<<"EXIT"<<endl;
                break ;
            }
            default:
            {
                cout << "Invalid choice. Please try again.\n";
            }
            
        }

    }
    while(choice != 5);
    return 0 ;
} 