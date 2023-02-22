#include<iostream>
#include<time.h>
#include<stdlib.h>
using namespace std;
const int TABLE_SIZE=10;
class HashTable 
{
private:
    int *table;
    int size;
    int collisions;
public:
    HashTable() 
    {
        this->size=TABLE_SIZE;
        this->table=(int *)malloc(sizeof(int)*size);
        for(int i=0;i<size;i++) table[i]=-1;
        this->collisions=0;
    }
    //function to add record in the table
    void insert(int key) 
    {
        int index=key%size;
        int i=1;
        while(table[index]!=-1) 
        {
            this->collisions++;
            index=(key+i*i)%size;
            i++;
        }
        table[index]=key;
    }
    //function to search
    int search(int key) 
    {
        int index=key%size;
        int i=1;
        while(table[index]!=key && table[index]!=-1) 
        {
            index=(key+i*i)%size;
            i++;
        }
        if(table[index]==key) return index;
        else return -1;
    }
    //function to remove
    void remove(int key) 
    {
        int index=search(key);
        if(index!=-1) 
        {
            table[index]=-1;
            cout<<"Key : " <<key<< " removed from the table."<<endl;
        }
        else 
        {
            cout<<"Key : "<<key<<" not found in the hash table."<<endl;
        }
    }
    //function to display all the entries
    void display() 
    {
        for(int i=0;i<size;i++) 
        {
            if(table[i]!=-1) cout<<"row "<<i<<" --> "<<table[i]<<endl;
            else cout<<"row "<<i<<endl;
        }
    }
    //function to get the Collision count
    int getCollisionCount() 
    {
        return this->collisions;
    }
};

int main() 
{
    int ch,num;
    HashTable hashTable;
    cout<<"HashMap Table initialized with space for 10 entries!!!!"<<endl;
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
            cout<<"Enter the number to insert : ";
            cin>>num;
            fflush(stdin);
            hashTable.insert(num);
            cout<<endl;
        }
        else if(ch==2)
        {
            cout<<"Enter the key to delete from the Table : ";
            cin>>num;
            fflush(stdin);
            hashTable.remove(num);
            cout<<endl;
        }
        else if(ch==3)
        {
            cout<<"Collision count : "<<hashTable.getCollisionCount()<<endl;
            cout<<endl;
        }
        else if(ch==4)
        {
            hashTable.display();
            cout<<endl;
        }
        else if(ch==5)
        {
            cout<<"Enter a key to search in Table : ";
            cin>>num;
            fflush(stdin);
            cout<<"Value : "<<hashTable.search(num)<<endl;
            cout<<endl;
        }
        else break;
    }
    return 0;
}
