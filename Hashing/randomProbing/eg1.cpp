#include<iostream>
#include<chrono>
#include<ctime>
using namespace std;
const int TABLE_SIZE = 10;
class HashNode 
{
public:
    int key;
    int value;
    HashNode()
    {
        this->key=key;
        this->value=value;
    }
    HashNode(int key,int value)
    {
        this->key=key;
        this->value=value;
    }
};
class HashTable 
{
private:
    HashNode **table;
    int collisionCount;
public:
    HashTable() 
    {
        this->table=(HashNode **)malloc(sizeof(HashNode *)*TABLE_SIZE);
        for(int i=0;i<TABLE_SIZE;i++) table[i]=NULL;
        this->collisionCount=0;
    }
    ~HashTable() 
    {
        for(int i=0;i<TABLE_SIZE;i++)  if(table[i]) delete table[i];
        delete []table;
    }
    int hash(int key) 
    {
        return key%TABLE_SIZE;
    }
    void insert(int key,int value) 
    {
        auto start=chrono::high_resolution_clock::now();
        int h=hash(key);
        int count=0;
        while(table[h]!=NULL && table[h]->key!=key && count<TABLE_SIZE) 
        {
            h=(h+rand())%TABLE_SIZE;
            count++;
            this->collisionCount++;
        }
        if(table[h]==nullptr) table[h]=new HashNode(key,value);
        else 
        {
            if(table[h]->key==key) table[h]->value=value;
            else cout<<"Error: hash table is full."<<endl;
        }
        auto end=chrono::high_resolution_clock::now();
        auto timeTaken=chrono::duration_cast<chrono::nanoseconds>(end-start).count();
    }
    int search(int key) 
    {
        int h=hash(key);
        int count=0;
        while(table[h]!=nullptr && table[h]->key!=key && count<TABLE_SIZE) 
        {
            h=(h+rand())%TABLE_SIZE;
            count++;
        }
        if(table[h]==nullptr || count==TABLE_SIZE) return -1;
        else return this->table[h]->value;
    }
    int getCollisionCount() 
    {
        return this->collisionCount;
    }
    void display() 
    {
        for(int i=0;i<TABLE_SIZE;i++) 
        {
            if(table[i]) cout<<"K : " <<this->table[i]->key<<", V : "<<this->table[i]->value<<endl;
        }
    }
    /*int search(int value) 
    {
        for(int i=0;i<TABLE_SIZE;i++) 
        {
            if(table[i]!=nullptr && table[i]->value==value) return table[i]->key;
        }
        return -1;
    }*/
    void remove(int key) 
    {
        int h=hash(key);
        int count=0;
        while(table[h]!=nullptr && table[h]->key!=key && count<TABLE_SIZE) 
        {
            h=(h+rand())%TABLE_SIZE;
            count++;
        }
        if(table[h]!=nullptr && table[h]->key==key) 
        {
            delete table[h];
            table[h]=NULL;
        }
        else cout << "Error: key not found." << endl;
    }
};

int main() {
    srand(time(nullptr));
    int ch,key,value;
    HashTable ht;
    cout<<"HashTable initialized with space for 10 entries!!!!"<<endl;
    while(1)
    {
        cout<<"1.Insert into HashTable"<<endl;
        cout<<"2.Remove from the HashTable"<<endl;
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
            ht.insert(key,value);
            cout<<endl;
        }
        else if(ch==2)
        {
            cout<<"Enter the key to delete from the Table : ";
            cin>>key;
            fflush(stdin);
            ht.remove(key);
            cout<<endl;
        }
        else if(ch==3)
        {
            cout<<"Collision count : "<<ht.getCollisionCount()<<endl;
            cout<<endl;
        }
        else if(ch==4)
        {
            ht.display();
            cout<<endl;
        }
        else if(ch==5)
        {
            cout<<"Enter a key to search in Table : ";
            cin>>key;
            fflush(stdin);
            cout<<"Value : "<<ht.search(key)<<endl;
            cout<<endl;
        }
        else break;
    }
    return 0;
}
