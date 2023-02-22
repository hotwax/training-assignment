#include<iostream>
#include<stdlib.h>
using namespace std;
//the 'node' which contains the data and the next pointer
struct Node
{
int num;
struct Node *next;
};
struct Node *start=NULL;
//function to add number in the list
void addAtEnd(int num)
{
struct Node *t,*j;
t=(struct Node *)malloc(sizeof(struct Node));
t->num=num;
t->next=NULL;
//if the list is empty, add the first node
if(start==NULL)
{
start=t;
return;
}
//if the list is not empty, then iterate till the last node added
//finally, attach the refrence of the new node with the last node
j=start;
while(j->next) j=j->next;
j->next=t;
}
//function to add data in the top of the list
void insertAtTop(int num)
{
struct Node *t=(struct Node *)malloc(sizeof(struct Node *));
t->num=num;
t->next=NULL;
if(start==NULL) 
{
start=t;
return;
}
t->next=start;
start=t;
}
//function to add number at any position in the list
void insertAtPosition(int pos,int num)
{
struct Node *t,*p1,*p2;
int x;
t=(struct Node *)malloc(sizeof(struct Node));
t->num=num;
t->next=NULL;
//if the list is empty
if(start==NULL)
{
start=t;
return;
}
if(pos<0) pos=0;
//iterate till that position where you need to insert number
for(p1=start,x=1;p1!=NULL && x<=pos;x++)
{
p2=p1;
p1=p1->next;
}
//add at end
if(p1==NULL) 
{
p2->next=t;
return;
}
//add at start
if(p1==start)
{
t->next=start;
start=t;
return;
}
//add in between
t->next=p1;
p2->next=t;
}
//function to remove number from position
void removeFromPosition(int pos)
{
if(pos<0 || start==NULL) return;
struct Node *p1,*p2;
int x;
//iterate till the position on the list
for(p1=start,x=1;x<=pos && p1!=NULL;x++)
{
p2=p1;
p1=p1->next;
}
//if the list is empty
if(p1==NULL) return;
//remove from the start
if(p1==start) start=start->next;
//remove from the between
else p2->next=p1->next;
//release the data node
free(p1);
}
//function to travel from top to bottom
void traverseTopToBottom()
{
for(struct Node *t=start;t;t=t->next) cout<<t->num<<" ";
}
void _traverseBottomToTop(struct Node *t)
{
if(t==NULL) return;
_traverseBottomToTop(t->next);
cout<<t->num<<" ";
}
//function to travel from bottom to top in the list
void traverseBottomToTop()
{
_traverseBottomToTop(start);
}
//function to sort the list
void sortTheList(struct Node *start)
{
int size=0;
//gets the size of list
for(struct Node *t=start;t;t=t->next) size++;
//creates a dynamic array for the size
int *x=(int *)malloc(sizeof(int)*size);
int g,i=0;
//push every element from list in the array
for(struct Node *t=start;t;t=t->next,i++) x[i]=t->num;
int e,f;
//perform the sorting code(it can be any)
for(e=0;e<=size-2;e++)
{
for(f=e+1;f<=size-1;f++)
{
if(x[f]<x[e])
{
g=x[e];
x[e]=x[f];
x[f]=g;
}
}
}
struct Node *t=start;
//iterate again on the list, to push the sorted data in the list from the array
for(int i=0;i<size;i++)
{
t->num=x[i];
t=t->next;
}
//release the array
free(x); 
}
//function to search the number in list
int searchTheNumber(int num)
{
for(struct Node *t=start;t;t=t->next) if(t->num==num) return 1;
return 0;
}
int main()
{
int ch,pos,num;
while(1)
{
cout<<"1. Add at End : "<<endl;
cout<<"2. Insert at Top : "<<endl;
cout<<"3. Insert at Position : "<<endl;
cout<<"4. Remove from Position : "<<endl;
cout<<"5. Traverse(top to bottom) : "<<endl;
cout<<"6. Traverse(bottom to top) : "<<endl;
cout<<"7. Sort the List : "<<endl;
cout<<"8. Search in the List : "<<endl;
cout<<"9. Exit "<<endl;
cout<<"Enter your choice : ";
cin>>ch;
fflush(stdin);
if(ch<1 || ch>9)
{
cout<<"Enter a CORRECT chooice\n"<<endl;
continue;
}
if(ch==1)
{
cout<<"Enter a number to 'add at end' : ";
cin>>num;
fflush(stdin);
addAtEnd(num);
cout<<endl;
}
else if(ch==2)
{
cout<<"Enter a number to 'insert at top' : ";
cin>>num;
fflush(stdin);
insertAtTop(num);
cout<<endl;
}
else if(ch==3)
{
cout<<"Enter a number to 'insert at position' : ";
cin>>num;
fflush(stdin);
cout<<"Enter the position to insert : ";
cin>>pos;
fflush(stdin);
insertAtPosition(pos,num);
cout<<endl;
}
else if(ch==4)
{
cout<<"Enter the position to remove number : ";
cin>>pos;
fflush(stdin);
removeFromPosition(pos);
cout<<endl;
}
else if(ch==5)
{
cout<<"Traversal(Top-Bottom)"<<endl;
traverseTopToBottom();
cout<<endl;
}
else if(ch==6)
{
cout<<"Traversal(Bottom-Top)"<<endl;
traverseBottomToTop();
cout<<endl;
}
else if(ch==7)
{
cout<<"Before Sorting : "<<endl;
traverseTopToBottom();
cout<<endl<<"After Sorting : "<<endl;
sortTheList(start);
traverseTopToBottom();
cout<<endl;
}
else if(ch==8)
{
cout<<"Enter a number to Search : ";
cin>>num;
if(searchTheNumber(num)) cout<<"FOUND!"<<endl;
else cout<<"NOT FOUND"<<endl;
cout<<endl;
}
else break;
}
return 0;
}