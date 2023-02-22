#include<iostream>
#include<stdlib.h>
using namespace std;
struct Node 
{
    int num;
    struct Node *left;
    struct Node *right;
};
struct Node *root=NULL;
//function to add number to Tree
void addToBST(int num)
{
    struct Node *t,*j;
	t=(struct Node *)malloc(sizeof(struct Node));
    t->num=num;
    t->left=NULL;
    t->right=NULL;
    //if tree is empty initally
    if(root==NULL)
    {
        root=t;
        return;
    }
    //if tree is not empty, then traverse to add that number
    j=root;
    while(1)
    {
        if(j->num<t->num)
        {
            if(j->left==NULL)
            {
                j->left=t;
                break;
            }
            else
            {
                j=j->left;
            }
        }
        else
        {
            if(j->right==NULL)
            {
                j->right=t;
                break;
            }
            else
            {
                j=j->right;
            }
        }
    }
}
//function for inOrderTraversal
void inOrderTraversal(struct Node *t)
{
    if(t==NULL) return;
    inOrderTraversal(t->left);
    cout<<t->num<<" ";
    inOrderTraversal(t->right);
}
//function for preOrderTraversal
void preOrderTraversal(struct Node *t)
{
    if(t==NULL) return;
    cout<<t->num<<" ";
    preOrderTraversal(t->left);
    preOrderTraversal(t->right);
}
//function for postOrderTraversal
void postOrderTraversal(struct Node *t)
{
    if(t==NULL) return;
    preOrderTraversal(t->left);
    preOrderTraversal(t->right);
    cout<<t->num<<" ";
}
//function for searching the number in Tree
int searchTheNumber(int num)
{
    if(root==NULL) return 0;
    struct Node *t=root;
    while(t)
    {
        if(t->num==num) break;
        else if(t->num<num) t=t->left;
        else t=t->right;
    }
    if(t) return 1;
    return 0;
}
//function to remove the number
void removeTheNumber(int num)
{
    struct Node *t,*j;
    struct Node **p2p;
    t=root;
    //loop to search, whether the number is there in the tree or not
    while(t!=NULL)  
    {
        if(num==t->num) break;
        j=t;
        if(t->num<num) t=t->left;
        else t=t->right;
    }
    //after this loop,
    //'t' will have the node to be deleted and 'j' will be its ancestor node
    if(t==NULL)
    {
        cout<<"Invalid Number : "<<num<<endl;
        return;
    }
    //node to be deleted is root nodes
    //'p2p' pointer will contain the address of the node to be deleted
    if(t==root) p2p=&root;
    else
    {
        //if 't' is the left ancestor of 'j'
        if(t==j->left) p2p=&(j->left);
        //if 't' is the right ancestor of 'j'
        else p2p=&(j->right);
        //'p2p' pointer will contain the address of the node to be deleted
    }
    //if 't' is the leaf node, which is to be deleted
    if(t->left==NULL && t->right==NULL)
    {
    *p2p=NULL;
    //de-allocate the memory of that node to be deleted
    free(t);
    return;
    }
    struct Node *k,*rr;
    //if control over this line, then the node to be deleted is somewhere,
    //between the tree only
    //if 't' has some nodes in the right part, then we will attach the left lowest ancestor of the right node
    if(t->right!=NULL)
    {
        for(k=t->right;k->left!=NULL;k=k->left) rr=k;
        k->left=t->left;
        if(t->right!=k)
        {
            rr->left=k->right;
            k->right=t->right;
        }
    }
    //if 't' has some nodes in the left part, then we will attach the right lowest ancestor of the left node
    else
    {
        for(k=t->left;k->right!=NULL;k=k->right) rr=k;
        k->right=t->right;
        if(t->left!=k)
        {
            rr->right=k->left;
            k->left=t->left;
        }
    }
    *p2p=k;
    free(t);
}
int _getHeightOfTree(struct Node *t,int c)
{
    if(t==NULL) return c;
    return (_getHeightOfTree(t->left,c+1)>_getHeightOfTree(t->right,c+1))?_getHeightOfTree(t->left,c+1):_getHeightOfTree(t->right,c+1);
}
int getHeightOfTree(struct Node *t)
{
    if(t==NULL) return 0;
    if(t->left==NULL && t->right==NULL) return 1;
    else return _getHeightOfTree(t,0);
}
int main()
{
    int ch,num;
    while(1)
    {
        cout<<"1.Insert Into BST"<<endl;
        cout<<"2.InOrder Traversal."<<endl;
        cout<<"3.PostOrder Traversal"<<endl;
        cout<<"4.PreOrder Traversal"<<endl;
        cout<<"5.Search a Number"<<endl;
        cout<<"6.Remove a Number"<<endl;
        cout<<"7.Height of Tree"<<endl;
        cout<<"8.Exit"<<endl;
        cout<<"Enter your choice : ";
        cin>>ch;
        if(ch<1 || ch>8)
        {
            cout<<"Enter a CORRET choice"<<endl;
            continue;
        }
        if(ch==1)
        {
            cout<<"Enter a number to 'insert into tree' : ";
            cin>>num;
            fflush(stdin);
            addToBST(num);
            cout<<endl;
        }
        else if(ch==2)
        {
            inOrderTraversal(root);
            cout<<endl;
        }
        else if(ch==3)
        {
            postOrderTraversal(root);
            cout<<endl;            
        }else if(ch==4)
        {
            preOrderTraversal(root);
            cout<<endl;            
        }else if(ch==5)
        {
            cout<<"Enter a number to search : ";
            cin>>num;
            fflush(stdin);
            if(searchTheNumber(num)) cout<<"FOUND!"<<endl;
            else cout<<"NOT FOUND"<<endl;
            cout<<endl;
            
        }else if(ch==6)
        {
            cout<<"Enter a number 'to remove' : ";
            cin>>num;
            fflush(stdin);
            removeTheNumber(num);
            cout<<"Number REMOVED Successfully"<<endl<<endl; 
        }
        else if(ch==7)
        {
            cout<<"Height of Tree : "<<getHeightOfTree(root)<<endl;
            cout<<endl;
        }
        else break;
    }
    return 0;
}