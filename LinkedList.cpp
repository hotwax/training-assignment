#include<iostream>
#include<bits/stdc++.h>
using namespace std ;


class Node()
{
    int data ;
    Node * next ;
    Node(int val)
    {
        data = val;
        next = NULL ;
    }
};
void insert(Node * &head , int val)
{
    Node * newNode = new Node(val);
    if(!head)
    {
        head = newNode ;
        return ;
    }
    Node * temp = head ;
    while(temp->next != NULL)
    {
        temp=temp->next ;
    }
    temp->next = newNode ;
}
void deleteNodeByValue(Node * &head , int val)
{
    Node * temp = head ;
    while(temp->next->data != val && temp!=NULL)
    {
        if(temp==head)
        {
            head=temp->next ;
            delete(temp);
            return ;
        }
        temp=temp->next ;
    }
    Node * n = temp->next ;
    temp->next = temp->next->next ;
    delete(n);
}
void deleteNodeByPosition(Node *&head, int n)
{
    Node *temp = head;
    if (n == 1)
    {
        head = temp->next;
        delete (temp);
        return;
    }
    for (int i = 1; i < n - 1; i++)
    {
        temp = temp->next;
    }
    Node *temp2 = temp->next;
    temp->next = temp2->next;
    delete (temp2);
}
void update(Node * &head , int pval , int nval )
{
    Node * temp = head ;
    while(temp->data != pval && temp!=NULL)
    {
        temp=temp->next ;
    }
    temp->data = nval ;
}
Node *merge(Node *a, Node *b)
{
    // base case
    if(a == NULL)
        return b;
    if(b == NULL)
        return a;
 
    // recursive case
    // take a head pointer
    Node *c;
 
    if(a->data < b->data)
    {
        c = a;
        c->next = merge(a->next, b);
    }
    else
    {
        c = b;
        c->next = merge(a, b->next);
    }
 
    return c;
}
 
Node *mid_point(Node *head)
{
    if(head == NULL || head->next == NULL)
        return head;

    Node *fast = head;
    Node *slow = head;
 
    while(fast != NULL && fast->next != NULL)
    {
        fast = fast->next;
 
        if(fast->next == NULL)
            break;
 
        fast = fast->next;
        slow = slow->next;
    }
 
    return slow;
}
 
Node* merge_sort(Node *head)
{
    // base case
    if(head == NULL || head->next == NULL)
        return head;
 
    // recursive case
    // Step 1: divide the linked list into
    // two equal linked lists
    Node *mid = mid_point(head);
    Node *a = head;
    Node *b = mid->next;
 
    mid->next = NULL;
 
    // Step 2: recursively sort the smaller
    // linked lists
    a = merge_sort(a);
    b = merge_sort(b);
 
    // Step 3: merge the sorted linked lists
    Node *c = merge(a, b);
 
    return c;
}
void print(Node * head)
{
    while(head!=NULL)
    {
        cout<<head->data<<endl;
        head=head->next ;
    }
}
int main()
{
    while(1){
    cout<<"Linked List"<<endl<<"Select Option"<<endl;
    cout<<"1. Add Element"<<endl<<"2. Delete Element"<<endl<<"3. Update List "<<endl<<"4. Sort the List"<<endl<<"5. Print List"<<endl<<"6. Exit";
    int choice = 0 ;
    cin>>choice;
    switch(choice)
    {
        case 1:
        cout<<"Enter the value to be inserted"<<endl;
        int val ; cin>>val ;
        insert(head,val);

        case 2:
        cout<<"Delete Element by"<<endl<<"1. position"<<endl<<"2. Value"<<endl;
        int ch = 0 ;
        cin>>ch;
        if(ch==2){
            cout<<"Enter the Value of the Element to be deleted"<<endl;
            int val ; cin>>val ;
            deleteNodeByValue(head , val);
        }
        else if(ch==1){
            cout<<"Enter the Positon of the Element to be deleted"<<endl;
            int pos ; cin>>pos ;
            deleteNodeByPosition(head , pos);
        }
        else{
            cout<<"invalid Choice"<<endl;
        }

        case 3:
        cout<<"Enter previous Value and New value to update the list ( Note : values should be separated by space ) "<<endl;
        int p_val , n_val ;
        cin>>p_val>>n_val;
        update(head , p_val , n_val);

        case 4:
        cout<<"Sort the List"<<endl;
        Node * n = merge_sort(head);
        head=n ;

        case 5:
        print(head);

        case 6:
        break ;
    }



}
