#include<iostream>
#include<stdlib.h>
using namespace std;
class HashNode 
{
    public:
	    int value;
	    int key;
	public:
		HashNode()
		{
			this->key=0;
			this->value=0;
		}
    	HashNode(int key,int value)
	    {
		    this->value=value;
		    this->key=key;
	    }
};
class HashMap 
{
    private:
	    HashNode **arr;
	    HashNode *dummy;
	    int capacity;
	    int size;
    public:
	HashMap()
	{
		this->capacity=20;
		this->size=0;
        this->arr=(HashNode **)malloc(sizeof(HashNode *)*capacity);
		for(int i=0;i<capacity;i++) arr[i]=NULL;
		this->dummy=new HashNode(-1,-1);
	}
	int hashCode(int key)
	{
		return key%capacity;
	}
	void insertNodeInHashMap(int key,int value)
	{
        HashNode *tmp=new HashNode(key,value);
		// Apply hash function to find index for given key
		int hashIndex=hashCode(key);
		while(arr[hashIndex]!=NULL && arr[hashIndex]->key!=key && arr[hashIndex]->key!=-1) 
        {
			hashIndex++;
			hashIndex%=capacity;
		}
		//size needs to be increased after inserting node
		if(arr[hashIndex]==NULL || arr[hashIndex]->key==-1) size++;
		arr[hashIndex]=tmp;
	}
	// Function to delete a key value pair
	int deleteNodeFromHashMap(int key)
	{
		int hashIndex=hashCode(key);
		// finding the node with given key
		while(arr[hashIndex]!=NULL) 
        {
			//if node found
			if(arr[hashIndex]->key==key) 
            {
                HashNode *tmp=arr[hashIndex];
				arr[hashIndex]=dummy;
				this->size--;
				return tmp->value;
			}
			hashIndex++;
			hashIndex%=capacity;
		}
		// If not found return -1
		return -1;
	}
	// Function to search the value for a given key
	int get(int key)
	{
		int hashIndex=hashCode(key);
		int counter=0;
		// finding the node with given key
		while(arr[hashIndex]!=NULL) 
        { 
			if((counter++)>capacity) return -1;
			// if node found return its value
			if(arr[hashIndex]->key==key) return arr[hashIndex]->value;
			hashIndex++;
			hashIndex%=capacity;
		}
		//if not found, then return -1
        return -1;
	}
	//function to return the size of map
	int sizeofHashMap()
	{
		return this->size;
	}
	//function to return, if HashMap is empty or not
	bool isHashMapEmpty()
	{
		return this->size==0;
	}
	//function to display the stored key value pairs
	void displayTheHashMap()
	{
		for(int i=0;i<capacity;i++) 
        {
			if(arr[i]!=NULL && arr[i]->key!= -1) cout<<"K-"<<arr[i]->key<<" V-"<<arr[i]->value<<endl;
		}
	}
};
int main()
{
	int ch;
	int key,value;
	HashMap *hashMap=new HashMap();
	cout<<"HashMap initialized successfully!!!!"<<endl;
	while(1)
	{
		cout<<"1.Insert Key-Value pair into HashMap"<<endl;
		cout<<"2.Display the HashMap Created"<<endl;
		cout<<"3.Get the Size of the HashMap"<<endl;
		cout<<"4.Remove a value from the HashMap"<<endl;
		cout<<"5.Is HashMap Empty"<<endl;
		cout<<"6.Get the Value from the HashMap"<<endl;
		cout<<"7.Exit"<<endl;
		cout<<"Enter your Choice : ";
		cin>>ch;
		fflush(stdin);
		if(ch==1)
		{
			cout<<"Enter Key : ";
			cin>>key;
			fflush(stdin);
			cout<<"Enter Value for the key("<<key<<") : ";
			cin>>value;
			fflush(stdin);
			hashMap->insertNodeInHashMap(key,value);
			cout<<endl;
		}else if(ch==2)
		{
			hashMap->displayTheHashMap();
			cout<<endl;
		}
		else if(ch==3)
		{
			cout<<"Size of HashMap : "<<hashMap->sizeofHashMap()<<endl;
			cout<<endl;
			
		}else if(ch==4)
		{
			cout<<"Enter the Key to delete from the HashMap : ";
			cin>>key;
			int k=hashMap->deleteNodeFromHashMap(key);
			if(k==-1)
			{
				cout<<"Entry Not Found, try Again..."<<endl;
				continue;
			}
			cout<<"DELETION Successfully..."<<endl;
			cout<<endl;
			
		}else if(ch==5)
		{
			if(!hashMap->isHashMapEmpty()) cout<<"HashMap is not empty"<<endl;
			else cout<<"HashMap is empty"<<endl;
			cout<<endl;
		}else if(ch==6)
		{
			cout<<"Enter the key, to get value from the HashMap : ";
			cin>>key;
			fflush(stdin);
			int k=hashMap->get(key);
			if(k==-1)
			{
				cout<<"Entries not found, try again..."<<endl<<endl;
				continue;
			}
			cout<<"Value Recovered : "<<k<<endl<<endl;
		}
		else break;
	}
	return 0;
}
