#include<iostream>
#include<stdlib.h>
#include<time.h>
using namespace std;
typedef struct __chain_node
{
    int num;
    struct __chain_node *next;
}ChainNode;
typedef struct __chain
{
    int size;
    ChainNode *start;
}Chain;
//function to initialize the initial 10 columns of chain
void initChain(Chain *chain)
{
    chain->size=0;
    chain->start=NULL;
}
//clock variables to get the runtime of program
clock_t sp,ep;
//function to perform addition in chaining
void addToChain(Chain *chain,int num)
{
    sp=clock();
    ChainNode *t=(ChainNode *)malloc(sizeof(ChainNode));
    t->next=NULL;
    t->num=num;
    //if the n'th chain block is empty
    if(chain->start==NULL) chain->start=t;
    //if the n'th chain block is not empty
    else
    {
        ChainNode *j=chain->start;
        while(j->next) j=j->next;
        j->next=t;
    }
    chain->size++;
    ep=clock();
}
//function to display all the values for the given keys
void displayTheChain(Chain &chain,int i)
{
    cout<<"row-"<<i<<" --> ";
    if(chain.start==NULL) return;
    ChainNode *t=chain.start;
    while(t)
    {
        cout<<t->num<<" ";
        t=t->next;
    }
}
//function to delete from the chain
void deleteFromChain(int num,Chain *chain)
{
    for(int i=0;i<10;i++)
    {
        if(chain[i].start==NULL) continue;
        ChainNode *j,*t=chain[i].start;
        while(t)
        {
            if(t->num==num) break;
            j=t;
            t=t->next;
        }
        //delete from the begining
        if(t==chain[i].start)
        {
            chain[i].start=(chain[i].start)->next;
            free(t);
            return;
        }
        //delete from the last
        if(t->next==NULL)
        {
            j->next=NULL;
            free(t);
            return;
        }    
        //delete in between
        j->next=t->next;
        free(t);        
    }
}
//function to get all the values for a given key
void getAllValues(int key,Chain *chain)
{
    if(chain->size==0)
    {
        cout<<"No Entries Found..."<<endl;
        return;
    }
    ChainNode *t=chain->start;
    cout<<"Value : ";
    while(t)
    {
        cout<<t->num<<" ";
        t=t->next;
    }
}
//function to get the collision count
int getCollisionCount(Chain *chain)
{
    int collision=INT_MIN;
    for(int i=0;i<=9;i++) if(chain[i].size>collision) collision=chain[i].size;
    return collision;
}
int main()
{
    int i,num,req,ch;
    cout<<"Enter Requirement : ";
    cin>>req;
    fflush(stdin);
    Chain chain[10];
    for(int i=0;i<10;i++) initChain(&chain[i]);
    while(1)
    {
        cout<<"1.Insert the Data"<<endl;
        cout<<"2.Display the Data"<<endl;
        cout<<"3.Time Required to do chaining"<<endl;
        cout<<"4.Delete the Entry"<<endl;
        cout<<"5.Get the Values via key"<<endl;
        cout<<"6.Get the Collision count"<<endl;
        cout<<"7.Exit"<<endl;
        cout<<"Enter your choice : ";
        cin>>ch;
        if(ch<1 || ch>7)
        {
            cout<<"Enter a CORRECT Value"<<endl<<endl;
            continue;
        }
        else if(ch==1)
        {
            int *arr=(int *)malloc(sizeof(int)*req);
            for(i=0;i<req;i++)
            {
                cout<<"Enter a number : ";
                cin>>arr[i];
                fflush(stdin);
            }
            for(int y=0;y<req;y++)
            {
                num=arr[y];
                i=num%10;
                addToChain(&chain[i],num);
            }
            cout<<endl;
        }
        else if(ch==2)
        {
            for(i=0;i<=9;i++)
            {
                displayTheChain(chain[i],i);
                cout<<endl;
            }
            cout<<endl;
        }else if(ch==3)
        {
            cout<<"Time Required : "<<double(ep-sp)/double(CLOCKS_PER_SEC)<<endl<<endl;
        }else if(ch==4)
        {
            cout<<"Enter the Value to Delete : ";
            cin>>num;
            fflush(stdin);
            deleteFromChain(num,chain);
            cout<<endl;
        }else if(ch==5)
        {
            cout<<"Enter the key to get all its values : ";
            cin>>num;
            getAllValues(num,&chain[num]);
            cout<<endl<<endl;
        }else if(ch==6)
        {
            cout<<"Collison count : "<<getCollisionCount(chain)<<endl<<endl;
        }
        else break;
    }
       return 0;
}