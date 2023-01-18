#include <bits/stdc++.h>
using namespace std;

class Node
{
public:
    int data;
    Node *left;
    Node *right;

    Node(int data)
    {
        this->data = data;
        left = NULL;
        right = NULL;
    }
};

// for printing

void print(string message)
{
    cout << endl
         << message;
}

// for adding element

Node *addElement(Node *&root, int data)
{

    if (root == NULL)
    {
        Node *newNode = new Node(data);
        root = newNode;
        return root;
    }

    if (data > root->data)
    {

        root->right = addElement(root->right, data);
    }
    else
    {
        root->left = addElement(root->left, data);
    }

    return root;
}

// for adding element the function should not return something

void addVoidEle(Node *&root)
{
    // Taking input

    int data;
    print("Enter the Data: ");
    cin >> data;

    root = addElement(root, data);
}

// for deleting element

Node *getMin(Node *root)
{
    Node *temp = root;

    while (temp->left != NULL)
    {
        temp = temp->left;
    }

    return temp;
}

Node *deleteNode(Node *&root, int key)
{
    if (!root)
    {
        print("Key does not exist.");
        return NULL;
    }

    if (root->data == key)
    {
        if (!root->left && !root->right)
        {
            return NULL;
        }
        else if (root->left && !root->right)
        {
            Node *temp = root->left;
            delete root;
            return temp;
        }
        else if (!root->left && root->right)
        {
            Node *temp = root->right;
            delete root;
            return temp;
        }
        else
        {
            Node *min = getMin(root->right);
            root->data = min->data;
            root->right = deleteNode(root, min->data);

            return root;
        }
    }
    else
    {
        if (root->data > key)
        {
            root->left = deleteNode(root->left, key);
        }
        else
        {
            root->right = deleteNode(root->right, key);
        }
    }

    return root;
}

// a simple funtio to delete which is void

void deleteVoidNode(Node *&root)
{
    // Taking input

    int data;
    print("Enter the data to delete: ");
    cin >> data;

    root = deleteNode(root, data);
}

// funtion to search a element in bst

void isExist(Node *root)
{

    int data;
    print("Enter the data to search : ");
    cin >> data;

    Node *temp = root;

    while (temp)
    {

        if (temp->data == data)
        {
            print("Data is Present.");
            return;
        }
        else if (data < temp->data)
        {
            temp = temp->left;
        }
        else
        {
            temp = temp->right;
        }
    }

    print("Data is Not Present.");
    return;
}

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

int main()
{

    Node *root = NULL;

    while (true)
    {
        print("Your BST with Preorder -- ");
        preorder(root);

        cout << endl;

        print("Enter 1 for addElement operation");
        print("Enter 2 for DeleteElement operation");
        print("Enter 3 for search operation");
        print("Enter 4 for inorder traversal");
        print("Enter 5 for preorder traversal");
        print("Enter 6 for postorder traversal");

        print("Enter 7 to exit");
        cout << endl;
        int input;
        cin >> input;
        int data;

        switch (input)
        {
        case 1:
            addVoidEle(root);
            break;
        case 2:
            deleteVoidNode(root);

            break;
        case 3:
            isExist(root);
            break;
        case 4:
            print("Inorder is - ");
            inorder(root);
            break;
        case 5:
            print("Preorder is - ");
            preorder(root);
            break;
        case 6:
            print("Postorder is - ");
            postorder(root);
            break;
        default:
            return 0;
        }
    }
}