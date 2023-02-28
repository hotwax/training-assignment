#include<iostream>
#include<string>
#include<stdlib.h>
using namespace std;
const int TABLE_SIZE=10;
class HashNode 
{
public:
    int key;
    string value;
    HashNode()
    {
        this->key=0;
        this->value=""; 
    }
    HashNode(int key, string value) 
    {
        this->key = key;
        this->value = value;
    }
};

class HashMap 
{
private:
    HashNode **table;
    int size;
    int collisionCount;  
public:
    //empty constructor will be called, whenever the object of this class is created
    HashMap() 
    {
        this->table=(HashNode **)malloc(sizeof(HashNode)*TABLE_SIZE);
        this->size=0;
        this->collisionCount=0;  
        for(int i=0;i<TABLE_SIZE;i++) table[i]=NULL;
    }
    int hashCode(int key) 
    {
        return key%TABLE_SIZE;
    }
    //function to add the record in the table
    void insert(int key,string value) 
    {
        int hash=hashCode(key);
        while(table[hash]!=NULL && table[hash]->key!=key) 
        {
            hash=(hash+1)%TABLE_SIZE;
            collisionCount++;  
        }
        if(table[hash]) free(table[hash]);
        table[hash]=new HashNode(key,value);
        size++;
    }
    //function to display all the records of table
    void display() 
    {  
        for(int i=0;i<TABLE_SIZE;i++) 
        {
            if(table[i]) cout<<"K : "<<table[i]->key<<" V : "<<table[i]->value<<endl;
        }
    }
    //function to search for the value via the key provided
    string search(int key) 
    {
        int hash=hashCode(key);
        while(table[hash]!=NULL && table[hash]->key!=key) hash=(hash+1)%TABLE_SIZE;
        if (table[hash]==NULL) return "No entries to be found...";
        else return table[hash]->value;
    }
    //function to remove from the table
    void remove(int key) 
    {
        int hash=hashCode(key);
        while(table[hash]!=NULL) 
        {
            if(table[hash]->key==key) break;
            hash=(hash+1)%TABLE_SIZE;
        }
        if (table[hash]==NULL)
        {
            cout<<"Key not found..."<<endl;
            return;
        }
        else 
        {
            free(table[hash]);
            table[hash]=NULL;
            size--;
        }
    }
    //destructor will be implicitly called whenever the object of that class dies
    ~HashMap()
    {
        for(int i=0;i<TABLE_SIZE;i++) if(table[i]!=NULL) delete table[i];
        delete []table;
    }
    //function to get the collision count
    int getCollisionCount() 
    {  
        return collisionCount;
    }
};
int main() {
    int ch,key;
    string value;
    HashMap hashMap;
    cout<<"HashMap Table initialized with space for 10 entries!!!!"<<endl;
    while(1)
    {
        cout<<"1.Insert into HashMap"<<endl;
        cout<<"2.Remove from the HashMap"<<endl;
        cout<<"3.Get Collison Count"<<endl;
        cout<<"4.Display the Contents"<<endl;
        cout<<"5.Search for an entry in the Table"<<endl;
        cout<<"6.Exit"<<endl;
        cout<<"Enter your choice : ";
        cin>>ch;
        fflush(stdin);
        if(ch<1 || ch>6)
        {
            cout<<"Enter a CORRECT Choice"<<endl<<endl;
            continue;
        }
        if(ch==1)
        {
            cout<<"Enter the key : ";
            cin>>key;
            fflush(stdin);
            cout<<"Enter value for that key : ";
            cin>>value;
            fflush(stdin);
            hashMap.insert(key,value);
            cout<<endl;
        }
        else if(ch==2)
        {
            cout<<"Enter the key to delete from the Table : ";
            cin>>key;
            fflush(stdin);
            hashMap.remove(key);
            cout<<endl;
        }
        else if(ch==3)
        {
            cout<<"Collision count : "<<hashMap.getCollisionCount()<<endl;
            cout<<endl;
        }
        else if(ch==4)
        {
            hashMap.display();
            cout<<endl;
        }
        else if(ch==5)
        {
            cout<<"Enter a key to search in Table : ";
            cin>>key;
            fflush(stdin);
            cout<<"Value : "<<hashMap.search(key)<<endl;
            cout<<endl;
        }
        else break;
    }
    return 0;
}
