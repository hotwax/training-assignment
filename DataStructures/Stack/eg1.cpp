#include<iostream>
#include<stdlib.h>
using namespace std;
struct Node
{
    int num;
    struct Node *next;
};
struct Node *top=NULL;
//function to push number into STACK
void pushIntoStack(int num)
{
    struct Node *t=(struct Node *)malloc(sizeof(struct Node));
    t->num=num;
    t->next=NULL;
    //if stack is initially empty
    if(top==NULL)
    {
        top=t;
        return;
    }
    //if stack is not empty initially
    t->next=top;
    top=t;
}
//function to pop number from the Stack
int popFromStack()
{
    //look out for the top element
    int data=top->num;
    struct Node *t=top;
    //move the top to the next position
    top=top->next;
    //delete the top element
    free(t);
    return data;
}
//function to check whether the stack is empty or not
int isStackEmpty()
{
    return top==NULL;
}
//function to search number in stack
int searchInStack(int num)
{
    //if stack is empty, return 0;
    if(top==NULL) return 0;
    struct Node *t;
    //if number found, while iterating on this loop, then return 1
    for(t=top;t;t=t->next) if(t->num==num) break;
    //if pointer 't' moves till the last and its value become NULL
    if(t==NULL) return 0;
    else return 1;
}
//function to iterate on stack, but the elements won't be removed
void stackTraversing()
{
    if(top==NULL) return;
    for(struct Node *t=top;t;t=t->next) cout<<t->num<<endl;
}
int main()
{
    int ch,num;
    while(1)
    {
        cout<<"1. Push into Stack"<<endl;
        cout<<"2. Pop from Stack"<<endl;
        cout<<"3. Is Stack Empty"<<endl;
        cout<<"4. Search in Stack"<<endl;
        cout<<"5. Iterate on Stack"<<endl;
        cout<<"6. Exit"<<endl;
        cout<<"Enter your choice : ";
        cin>>ch;
        fflush(stdin);
        if(ch<1 || ch>6)
        {
            cout<<"Invalid choice Entered"<<endl<<endl;
            continue;
        }
        if(ch==1)
        {
            cout<<"Enter a number to push into STACK : ";
            cin>>num;
            pushIntoStack(num);
            cout<<endl;
        }
        else if(ch==2)
        {
            cout<<popFromStack()<<" popped from STACK"<<endl;
            cout<<endl;
        }
        else if(ch==3)
        {
            if(isStackEmpty()) cout<<"Stack is Empty"<<endl;
            else cout<<"Stack is not Empty"<<endl;
            cout<<endl;
        }else if(ch==4)
        {
            cout<<"Enter a Number to Search in STACK : ";
            cin>>num;
            if(searchInStack(num)) cout<<"Number FOUND!"<<endl;
            else cout<<"Not Found..."<<endl;
        }else if(ch==5)
        {
            stackTraversing();
            cout<<endl;
        }
        else break;
    }
    return 0;
}