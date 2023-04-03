#include <iostream>

using namespace std;

struct Node {
    int key;
    Node* left;
    Node* right;
    int height;
};


void displayMenu() {
    cout << "AVL Tree Menu:" << endl;
    cout << "1. Add a key" << endl;
    cout << "2. Delete a key" << endl;
    cout << "3. Update a key" << endl;
    cout << "4. Print inorder traversal" << endl;
    cout << "5. Exit" << endl;
}



int getHeight(Node* node) {
    if (node == nullptr) {
        return 0;
    }
    return node->height;
}

int getBalance(Node* node) {
    if (node == nullptr) {
        return 0;
    }
    return getHeight(node->left) - getHeight(node->right);
}

Node* leftRotate(Node* x) {
    Node* y = x->right;
    Node* T2 = y->left;
    
    // Perform rotation
    y->left = x;
    x->right = T2;
    
    // Update heights
    x->height = 1 + max(getHeight(x->left), getHeight(x->right));
    y->height = 1 + max(getHeight(y->left), getHeight(y->right));
    
    return y;
}

Node* rightRotate(Node* y) {
    Node* x = y->left;
    Node* T2 = x->right;
    
    // Perform rotation
    x->right = y;
    y->left = T2;
    
    // Update heights
    y->height = 1 + max(getHeight(y->left), getHeight(y->right));
    x->height = 1 + max(getHeight(x->left), getHeight(x->right));
    
    return x;
}

Node* add(Node* root, int key) {
    // Perform a normal binary search tree insertion
    if (root == nullptr) {
        return new Node{key, nullptr, nullptr, 1};
    } else if (key < root->key) {
        root->left = add(root->left, key);
    } else if (key > root->key) {
        root->right = add(root->right, key);
    } else { // key already exists in the tree
        return root;
    }
    
    // Update the height of the current node
    root->height = 1 + max(getHeight(root->left), getHeight(root->right));
    
    // Check if the tree is still balanced
    int balance = getBalance(root);
    
    // Left Left Case
    if (balance > 1 && key < root->left->key) {
        return rightRotate(root);
    }
    
    // Right Right Case
    if (balance < -1 && key > root->right->key) {
        return leftRotate(root);
    }
    
    // Left Right Case
    if (balance > 1 && key > root->left->key) {
        root->left = leftRotate(root->left);
        return rightRotate(root);
    }
    
    // Right Left Case
    if (balance < -1 && key < root->right->key) {
        root->right = rightRotate(root->right);
        return leftRotate(root);
    }
    
    return root;
}

Node* minValueNode(Node* node) {
    Node* current = node;
    while (current->left != nullptr) {
        current = current->left;
    }
    return current;
}

Node* deleteNode(Node* root, int key) {
    // Perform a normal binary search tree deletion
    if (root == nullptr) {
        return root;
    } else if (key < root->key) {
        root->left = deleteNode(root->left, key);
    } else if (key > root->key) {
        root->right = deleteNode(root->right, key);
    } else {
        // This is the node to be deleted
        if (root->left == nullptr || root->right == nullptr) {
            // Node has at most one child
            Node* temp = root->left ? root->left : root->right;
            
            if (temp == nullptr) {
                // Node has no child
                temp = root;
                root = nullptr;
            } else {
                // Node has one child
                *root = *temp;
            }
            
            delete temp;
        } else {
            // Node has two children
            Node* temp = minValueNode(root->right);
            root->key = temp->key;
            root->right = deleteNode(root->right, temp->key);
        }
    }
    
    // If the tree had only one node, return
    if (root == nullptr) {
        return root;
    }
    
    // Update the height of the current node
    root->height = 1 + max(getHeight(root->left), getHeight(root->right));
    
    // Check if the tree is still balanced
    int balance = getBalance(root);
    
    // Left Left Case
    if (balance > 1 && getBalance(root->left) >= 0) {
        return rightRotate(root);
    }
    
    // Left Right Case
    if (balance > 1 && getBalance(root->left) < 0) {
        root->left = leftRotate(root->left);
        return rightRotate(root);
    }
    
    // Right Right Case
    if (balance < -1 && getBalance(root->right) <= 0) {
        return leftRotate(root);
    }
    
    // Right Left Case
    if (balance < -1 && getBalance(root->right) > 0) {
        root->right = rightRotate(root->right);
        return leftRotate(root);
    }
    
    return root;
}

Node* update(Node* root, int oldKey, int newKey) {
    root = deleteNode(root, oldKey);
    root = add(root, newKey);
    return root;
}

void inorderTraversal(Node* root) {
    if (root != nullptr) {
        inorderTraversal(root->left);
        cout << root->key << " ";
        inorderTraversal(root->right);
    }
    
    }



int main() {
    Node* root = nullptr;
    int choice, key, oldKey, newKey;
    while (true) {
        displayMenu();
        cout << "Enter your choice: ";
        cin >> choice;
        switch (choice) {
            case 1:
                cout << "Enter the key to add: ";
                cin >> key;
                root = add(root, key);
                break;
            case 2:
                cout << "Enter the key to delete: ";
                cin >> key;
                root = deleteNode(root, key);
                break;
            case 3:
                cout << "Enter the old key to update: ";
                cin >> oldKey;
                cout << "Enter the new key: ";
                cin >> newKey;
                root = update(root, oldKey, newKey);
                break;
            case 4:
                cout << "Inorder traversal: ";
                inorderTraversal(root);
                cout << endl;
                break;
            case 5:
                cout << "Exiting..." << endl;
                return 0;
            default:
                cout << "Invalid choice." << endl;
                break;
        }
    }
}