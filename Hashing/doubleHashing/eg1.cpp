#include <iostream>
#include <vector>
using namespace std;
class HashTable 
{
private:
    vector<int> table;
    int capacity;
    int size;
    int collisionCount;
    // first hash function
    int hash1(int key) 
    {
        return key%capacity;
    }
    // second hash function
    int hash2(int key) 
    {
        return 7-(key%7);
    }
public:
    HashTable(int capacity) 
    {
        this->capacity=capacity;
        this->table.assign(capacity,-1);
        this->size=0;
        this->collisionCount=0;
    }
    bool isFull() 
    {
        return size==capacity;
    }
    bool isEmpty() 
    {
        return size==0;
    }
    bool search(int key) 
    {
        int index=hash1(key);
        int step=hash2(key);
        int i=0;
        while(table[index]!=-1 && i<capacity) 
        {
            if(table[index]==key)  return true;
            index=(index+step)%capacity;
            i++;
        }
        return false;
    }
    bool insert(int key) 
    {
        if(isFull()) return false;
        int index=hash1(key);
        int step=hash2(key);
        int i=0;
        while(table[index]!=-1) 
        {
            collisionCount++;
            index=(index+step)%capacity;
            i++;
        }
        table[index]=key;
        size++;
        return true;
    }
    bool remove(int key){
        if(isEmpty()) return false;
        int index=hash1(key);
        int step=hash2(key);
        int i=0;
        while(table[index]!=-1 && i<capacity) 
        {
            if(table[index]==key) 
            {
                table[index]=-1;
                size--;
                return true;
            }
            index=(index+step)%capacity;
            i++;
        }
        return false;
    }
    void display() 
    {
        for(int i=0;i<capacity;i++) 
        {
            if(table[i]!=-1) cout<<"row "<<i<<" --> "<<this->table[i]<<endl;
        }
    }
    int getCollisionCount() 
    {
        return this->collisionCount;
    }  
};
int main() {
    HashTable ht(10);
    int ch,key;
    while(1) 
    {
        cout<<"1.Insert"<<endl;
        cout<<"2.Remove"<<endl;
        cout<<"3.Contains"<<endl;
        cout<<"4.Display"<<endl;
        cout<<"5.Collision count"<<endl;
        cout<<"6.Exit"<<endl;
        cout<<"Enter your choice : ";
        cin>>ch;
        fflush(stdin);
        switch(ch) 
        {
            case 1:
                cout<<"Enter key to insert : ";
                cin>>key;
                if(ht.insert(key)) cout<<"Key inserted successfully."<<endl;
                else cout << "Hash table is full."<<endl;
                cout<<endl;
                break;
            case 2:
                cout<<"Enter key to remove : ";
                cin>>key;
                if (ht.remove(key)) cout<<"Key removed successfully."<<endl;
                else cout<<"Key not found."<<endl;
                cout<<endl;
                break;
            case 3:
                cout<<"Enter key to search for : ";
                cin>>key;
                if(ht.search(key)) cout<<"Key found in hash table."<<endl;
                else cout << "Key not found in hash table."<<endl;
                cout<<endl;
                break;
            case 4:
                cout<<"Hash table contents : "<<endl;
                ht.display();
                cout<<endl;
                break;
            case 5:
                cout<<"Collision count : "<<ht.getCollisionCount()<<endl;
                cout<<endl;
                break;
            case 6:
                return 0;
            default:
                cout<<"Invalid choice. Try again."<<endl;
                cout<<endl;
        }
    }
    return 0;
}