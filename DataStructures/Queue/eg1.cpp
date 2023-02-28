#include<iostream>
#include<stdlib.h>
using namespace std;
typedef struct __queue_node
{
    int num;
    struct __queue_node *next;
}QueueNode;
typedef struct _queue
{
    int size;
    QueueNode *start;
    QueueNode *end;
}Queue;
//function to initialize the Queue
void initQueue(Queue *queue)
{
    queue->size=0;
    queue->start=NULL;
    queue->end=NULL;
}
//function to check whether the Queue is empty or not
int isQueueEmpty(Queue *queue)
{
    return queue->size==0;
}
//function add number to Queue
void addToQueue(Queue *queue,int num)
{
    QueueNode *t=(QueueNode *)malloc(sizeof(QueueNode));
    t->num=num;
    t->next=NULL;
    //if initially the Queue is empty
    if(queue->start==NULL)
    {
        queue->start=t;
        queue->end=t;
    }
    //if the queue is not empty
    else
    {
        queue->end->next=t;
        queue->end=t;
    }
    //for every item added, the size of queue will increase by one
    queue->size++;
}
//function to remove from queue
int removeFromQueue(Queue *queue)
{
    //if queue is empty, then return -1
    if(queue==NULL) return -1;
    if(queue->start==NULL || queue->end==NULL || queue->size==0) return -1;
    QueueNode *t=queue->start;
    int num=queue->start->num;
    //moving the pointer to the next position
    queue->start=queue->start->next;
    if(queue->start==NULL) queue->end=NULL;
    //deallocating the memory for the Node
    free(t);
    //size will decrease by one
    queue->size--;
    return num;
}
//function to peek the first number in Queue
int peekIntoQueue(Queue *queue)
{
    return queue->start->num;
}
//function to traverse on Queue
void traverseOnQueue(Queue *queue)
{
    for(QueueNode *t=queue->start;t;t=t->next) cout<<t->num<<endl;
}
//function to Search the element in Queue
int searchInQueue(Queue *queue,int num)
{
    if(queue==NULL) return 0;
    if(queue->start==NULL || queue->end==NULL || queue->size==0) return 0;
    QueueNode *t=queue->start;
    while(t)
    {
        if(t->num==num) break;
        t=t->next;
    }
    if(t==NULL) return 0;
    else return 1;
}
int main()
{
    Queue queue;
    initQueue(&queue);
    int ch,num;
    while(1)
    {
        cout<<"1. Add into Queue"<<endl;
        cout<<"2. Remove from Queue"<<endl;
        cout<<"3. Is Queue Empty"<<endl;
        cout<<"4. Search in Queue"<<endl;
        cout<<"5. Iterate on Queue"<<endl;
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
            cout<<"Enter a number to push into Queue : ";
            cin>>num;
            addToQueue(&queue,num);
            cout<<endl;
        }
        else if(ch==2)
        {
            int k=removeFromQueue(&queue);
            if(k==-1)
            {
                cout<<"Some Error...,try again"<<endl;
                continue;
            }  
            cout<<k<<" removed from QUEUE"<<endl;
            cout<<endl;
        }
        else if(ch==3)
        {
            if(isQueueEmpty(&queue)) cout<<"Queue is Empty"<<endl;
            else cout<<"Queue is not Empty"<<endl;
            cout<<endl;
        }else if(ch==4)
        {
            cout<<"Enter a Number to Search in QUEUE : ";
            cin>>num;
            if(searchInQueue(&queue,num)) cout<<"Number FOUND!"<<endl;
            else cout<<"Not Found..."<<endl;
            cout<<endl;
        }else if(ch==5)
        {
            traverseOnQueue(&queue);
            cout<<endl;
        }
        else break;
    }
    free(&queue);
    return 0;
}