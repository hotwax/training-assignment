#include <bits/stdc++.h>
using namespace std;

// a print function

void print(string message)
{
    cout << endl
         << message;
}

// Class for Tree Node

class Node
{
public:
    int data;
    Node *left;
    Node *right;
    int bal;
    int height;

    Node(int data)
    {
        this->data = data;
        left = NULL;
        right = NULL;
        bal = 0;
        height = 1;
    }
};

// Class For AVL

class AVL
{
public:
    Node *root;

    AVL()
    {
        root = NULL;
    }

    // TO find Height

    int height(Node *root)
    {
        if (!root)
        {
            return 0;
        }

        int lh = height(root->left);
        int rh = height(root->right);

        return 1 + max(lh, rh);
    }

    // To find Balance

    int balance(Node *root)
    {
        if (!root)
        {
            return 0;
        }

        int lh = height(root->left);
        int rh = height(root->right);

        return lh - rh;
    }

    // To Rotate Right

    Node *rightRotate(Node *&z)
    {
        Node *y = z->left;

        Node *t = y->left;

        z->left = t;
        y->right = z;

        z->height = height(z);
        z->bal = balance(z);

        y->height = height(y);
        y->bal = balance(y);

        return y;
    }

    // To Rotate left

    Node *leftRotate(Node *&z)
    {
        Node *y = z->right;

        Node *t = z->left;

        z->right = t;

        y->left = z;

        z->height = height(z);
        z->bal = balance(z);

        y->height = height(y);
        y->bal = balance(y);

        return y;
    }

    // To add Node to tree

    Node *add(Node *root, int data)
    {
        if (root == NULL)
        {
            Node *temp = new Node(data);
            return temp;
        }

        // Checking where node can be inserted

        if (data < root->data)
        {
            root->left = this->add(root->left, data);
        }
        else if (data > root->data)
        {
            root->right = add(root->right, data);
        }
        else
        {
            return root;
        }

        root->height = height(root);
        root->bal = balance(root);

        if (root->bal > 1)
        { // it is either LL or LR

            if (root->left->bal >= 0)
            { // it is LL
                root = rightRotate(root);
            }
            else
            { // Lr
                root->left = leftRotate(root->left);
                root = rightRotate(root);
            }
        }
        else if (root->bal < -1)
        { // either RR or Rl
            if (root->right->bal >= 0)
            { // RL
                root->right = rightRotate(root->right);
                root = leftRotate(root);
            }
            else
            {
                // RR
                root = leftRotate(root);
            }
        }

        return root;
    }

    // to find minimum value from the tree

    int getMin(Node *root)
    {
        if (root->left == NULL)
        {
            return root->data;
        }
        return getMin(root->left);
    }

    // For Node Removal

    Node *removeNode(Node *root, int data)
    {

        if (!root)
        {
            return NULL;
        }

        // Cases for deletion

        if (root->data == data)
        {
            if (!root->left && root->right)
            {
                Node *temp = root->right;
                delete root;
                return temp;
            }
            else if (root->left && !root->right)
            {
                Node *temp = root->right;
                delete root;
                return temp;
            }
            else if (!root->left && !root->right)
            {
                return NULL;
            }
            else
            {
                int min = getMin(root->right);

                root->data = min;

                root = removeNode(root->right, min);

                return root;
            }
        }

        else if (data < root->data)
        {
            root->left = removeNode(root->left, data);
        }
        else
        {
            root->right = removeNode(root->right, data);
        }

        // for balancing the tree

        root->height = height(root);
        root->bal = balance(root);

        if (root->bal > 1)
        { // it is either LL or LR

            if (root->left->bal >= 0)
            { // it is LL
                root = rightRotate(root);
            }
            else
            { // Lr
                root->left = leftRotate(root->left);
                root = rightRotate(root);
            }
        }
        else if (root->bal < -1)
        { // either RR or Rl
            if (root->right->bal >= 0)
            { // RL
                root->right = rightRotate(root->right);
                root = leftRotate(root);
            }
            else
            {
                // RR
                root = leftRotate(root);
            }
        }

        return root;
    }

    // funtion for level order traversal

    void levelOrderTraversal()
    {

        cout << "Level Order Traversal - ";

        // if root is null
        if (!this->root)
        {
            return;
        }

        queue<Node *> q;
        q.push(this->root);

        while (!q.empty())
        {
            Node *front = q.front();
            q.pop();

            cout << front->data << " ";

            if (front->left)
            {
                q.push(front->left);
            }
            if (front->right)
            {
                q.push(front->right);
            }
        }
    }

    // Add function for use in menu

    void addforMenu()
    {
        // Taking input

        print("Enter the data : ");
        int data;
        cin >> data;

        this->root = add(this->root, data);
    }

    // Remove function for use in menu

    void removeForMenu()
    {
        // Taking input
        print("Enter the data : ");
        int data;
        cin >> data;

        this->root = this->removeNode(this->root, data);
    }
};

int main()
{

    // Creating AVL Object;

    AVL tree;

    // while for menu --

    while (true)
    {
        print("Your AVL Tree -- ");
        cout << endl;
        tree.levelOrderTraversal();

        cout << endl;

        print("Enter 1 for add operation");
        print("Enter 2 for delete operation");

        print("Enter 3 to exit");
        cout << endl;
        int input;
        cin >> input;
        int data;

        switch (input)
        {
        case 1:
            tree.addforMenu();
            break;
        case 2:
            tree.removeForMenu();
            break;

        default:
            return 0;
        }
    }
}