#include <iostream>
using namespace std;

// Create a Node of a Tree having four fields data , left , right and height
class Node
{
public:
    int data;
    Node *left;  // store the address of the left subtree
    Node *right; // store the address of the right subtree
    int height;  // store height of each node
};

// Create Custom AVL tree DataStructures
class AVLTree
{
public:
    // Create a NewNode and initialise all the fields of the Node
    Node *createNode(int data)
    {
        Node *newNode = new Node;
        newNode->left = NULL;
        newNode->right = NULL;
        newNode->data = data;
        newNode->height = 1;
        return newNode;
    }
    // Making a function which returns height of the given Node
    int getHeight(Node *node)
    {
        if (node == NULL)
        {
            return 0;
        }
        return node->height;
    }

    // Making a function which return the balance factor of the given Node
    // Balance factor = the balance factor of a node is just the value we receive after subtracting the height of the right child with that of the left child of the node.
    int getBalanceFactor(Node *node)
    {
        if (node == NULL)
        {
            return 0;
        }
        return getHeight(node->left) - getHeight(node->right);
    }

    // we move our unbalanced node to the left
    Node *leftRotate(Node *node)
    {
        Node *temporary_Node_Pointer1 = node->right;
        Node *temporary_Node_Pointer2 = temporary_Node_Pointer1->left;

        // Now change the links
        temporary_Node_Pointer1->left = node;
        node->right = temporary_Node_Pointer2;

        // Update the Heights of the Nodes
        node->height = max(getHeight(node->right), getHeight(node->left)) + 1;
        temporary_Node_Pointer1->height = max(getHeight(temporary_Node_Pointer1->right), getHeight(temporary_Node_Pointer1->left)) + 1;

        return temporary_Node_Pointer1;
    }

    // we move our unbalanced node to the right
    Node *rightRotate(Node *node)
    {
        Node *temporary_Node_Pointer1 = node->left;
        Node *temporary_Node_Pointer2 = temporary_Node_Pointer1->right;

        // Now change the links
        temporary_Node_Pointer1->right = node;
        node->left = temporary_Node_Pointer2;

        // Update the Heights of the Nodes
        node->height = max(getHeight(node->right), getHeight(node->left)) + 1;
        temporary_Node_Pointer1->height = max(getHeight(temporary_Node_Pointer1->right), getHeight(temporary_Node_Pointer1->left)) + 1;

        return temporary_Node_Pointer1;
    }

    Node *insertNode(Node *root, int data)
    {
        // If Root is Null Create a new Node and insert it
        if (root == NULL)
        {
            return createNode(data);
        }
        if (root->data > data) // if data is smaller than the data of the root , then go to the left subtree
        {
            root->left = insertNode(root->left, data);
        }
        else if (root->data < data) // if data is greater than the data of the root , then go to the right subtree
        {
            root->right = insertNode(root->right, data);
        }

        // update the height of the newNode ;
        root->height = 1 + max(getHeight(root->left), getHeight(root->right));

        // find the balance factor of the node
        int balance_factor = getBalanceFactor(root);

        // Left Left Case
        if (balance_factor > 1 && data < root->left->data)
        {
            return rightRotate(root);
        }
        // Right Right Case
        if (balance_factor < -1 && data > root->right->data)
        {
            return leftRotate(root);
        }
        // Left Right Case
        if (balance_factor > 1 && data > root->left->data)
        {
            root->left = leftRotate(root->left);
            return rightRotate(root);
        }
        // Right Left Case
        if (balance_factor < -1 && data < root->right->data)
        {
            root->right = rightRotate(root->right);
            return leftRotate(root);
        }
        return root;
    }

    // Search Node in the Tree
    bool Search(Node *root, int value)
    {

        if (root == NULL)
        {
            return false;
        }
        else if (root->data == value)
        {
            return true;
        }
        else if (value < root->data)
        {
            return Search(root->left, value);
        }
        else
        {
            return Search(root->right, value);
        }
    }

    // return the node with minimum key value
    // found in that tree.
    Node *minValueNode(Node *node)
    {
        Node *current = node;

        //  loop down to find the leftmost leaf
        while (current->left != NULL)
            current = current->left;

        return current;
    }

    // Recursive function to delete a node
    // with given key from subtree with
    // given root. It returns root of the
    // modified subtree.
    Node *deleteNode(Node *root, int data)
    {

        // STEP 1: PERFORM STANDARD BST DELETE

        // base Case
        if (root == NULL)
            return root;

        // If the key to be deleted is smaller
        // than the root's key, then it lies
        // in left subtree

        // left Part
        if (data < root->data)
            root->left = deleteNode(root->left, data);

        // If the key to be deleted is greater
        // than the root's key, then it lies
        // in right subtree

        // right part
        else if (data > root->data)
            root->right = deleteNode(root->right, data);

        // if key is same as root's key, then
        // This is the node to be deleted

        // data == root->data
        else
        {
            // node with only one child or no child

            // 0 child
            if (root->left == NULL && root->right == NULL)
            {
                delete root;
                root = NULL;
            }

            // 1 child = right child
            else if (root->left == NULL)
            {
                Node *node_pointer = root->right;
                delete root;
                root = node_pointer;
            }

            // 1 child = left child
            else if (root->right == NULL)
            {
                Node *node_pointer = root->left;
                delete root;
                root = node_pointer;
            }
            // 2 child
            else
            {
                Node *node_pointer = minValueNode(root->right);
                root->data = node_pointer->data;
                root->right = deleteNode(root->right, node_pointer->data);
            }
        }

        // If the tree had only one node
        // then return
        if (root == NULL)
            return root;

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root->height = 1 + max(getHeight(root->left), getHeight(root->right));

        // STEP 3: GET THE BALANCE FACTOR OF
        // THIS NODE (to check whether this
        // node became unbalanced)
        int balance = getBalanceFactor(root);

        // If this node becomes unbalanced,
        // then there are 4 cases

        // Left Left Case
        if (balance > 1 && getBalanceFactor(root->left) >= 0)
        {
            return rightRotate(root);
        }

        // Left Right Case
        if (balance > 1 && getBalanceFactor(root->left) < 0)
        {
            root->left = leftRotate(root->left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalanceFactor(root->right) <= 0)
        {
            return leftRotate(root);
        }

        // Right Left Case
        if (balance < -1 && getBalanceFactor(root->right) > 0)
        {
            root->right = rightRotate(root->right);
            return leftRotate(root);
        }

        return root;
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
};

int main()
{
    // create instance of the tree class
    AVLTree t;
    Node *root = NULL; // root node , initially when there is no node in the tree root is NULL
    int choice = 0;
    while (choice != 5)
    {
        cout << "1. Insert Node" << endl;
        cout << "2. Traversal" << endl;
        cout << "3. Search" << endl;
        cout << "4. Delete" << endl;
        cout << "5. Exit" << endl;
        cout << "Enter Your Choice" << endl;
        cin >> choice;

        switch (choice)
        {
        case 1:
        {
            cout << "Enter the value of Node to insert" << endl;
            int value;
            cin >> value;
            root = t.insertNode(root, value);
            break;
        }
        case 2:
        {
            cout << "Traversal Techniques :- " << endl;
            cout << "1. PreOrder" << endl;
            cout << "2. PostOrder" << endl;
            cout << "3. Inorder" << endl;
            cout << "Enter Your Choice" << endl;
            int choice1 = 0;
            cin >> choice1;
            if (choice1 == 1)
                t.preorder(root);
            else if (choice1 == 2)
                t.postorder(root);
            else if (choice1 == 3)
                t.inorder(root);
            else
                cout << "Invalid Choice" << endl;
            cout << endl;
            break;
        }
        case 3:
        {
            cout << "Enter the value of Node to Search" << endl;
            int value;
            cin >> value;
            int temp = t.Search(root, value);
            if (temp)
                cout << "Element Found" << endl;
            else
                cout << "Element Not Found" << endl;
            break;
        }
        case 4:
        {
            cout << "Enter the value of Node to delete" << endl;
            int value;
            cin >> value;
            if (t.deleteNode(root, value))
            {
                cout << "Operation Successful" << endl;
            }
            else
            {
                cout << "Element Not present" << endl;
            }
            break;
        }
        case 5:
            cout << "EXIT" << endl;
            break;
        default:
            cout << "Invalid Choice" << endl;
            return 0;
        }
    }
}
