class PairNode
{
 int key;    // a node that can hold key value pair
 int val; 
 PairNode next; //for chaining purposes
 public PairNode(int key, int val)
 {
  this.key=key;
  this.val=val;
  this.next=null;
 }
};

public class HashMap
{
 PairNode hashTable[];
 int capacity;
 public void set(int key, int val)
 {
  int hashKey=calculateHash(key);  //calculating hashkey
  if(hashTable[hashKey]==null)    //checking if there is no collision
   hashTable[hashKey]=new PairNode(key,val);    //simply filling when no collision
  else
  {
   PairNode curr=hashTable[hashKey];
   while(curr!=null)        //traversing chain 
   {
    if(curr.key==key)  //until we find same key
    {
     curr.val=val;     //incase updating it
     return; 
    }
    else if(curr.next==null)   //or, reach end of chain
    {
     curr.next=new PairNode(key,val); //in case adding it
     return;
    }
    curr=curr.next;
   }
  }
 }
 public int get(int key) throws Exception
 {
  int hashKey=calculateHash(key);
  PairNode curr=hashTable[hashKey];  //searching in hashtable
  
  while(curr!=null)
  {                     //traversing chain to find exact matching key
   if(curr.key==key)
    return curr.val;
  }
  throw new Exception("Key not found"); 
 }
 public boolean contains(int key)
 {
  int hashKey=calculateHash(key);       
  PairNode curr=hashTable[hashKey];  //searching in hashtable

  while(curr!=null)
  {
   if(curr.key==key)             //traversing chain to find exact matching key
    return true;
  }
  return false;
 }
 public void delete(int key) throws Exception
 {
  int hashKey=calculateHash(key);

  if(hashTable[hashKey].key==key)         //searching in hashtable
  {
   hashTable[hashKey]=hashTable[hashKey].next;
   return;
  }

  PairNode curr=hashTable[hashKey];
  while(curr!=null)          
  {
   if(curr.next.key==key)     //traversing chain to find exact matching key
   {
    curr.next=curr.next.next;  //forwarding previous node to nullify that node
    return;
   }
  }
  throw new Exception("Key not found");
 }
 public int calculateHash(int key)
 {
  int result = key;
  result = 31*result+(key>>16);  // a simple hashing algorithm that uses
  result = 31*result+(key<<15);  //bits shifting, XOR, and multipication
  result = 31*result^(key>>3);
  
  if(result<0) result*=(-1);
  
  return result%capacity;
 }
 public HashMap(int capacity)
 {
  this.capacity=capacity;
  this.hashTable=new PairNode[capacity];   //generating hashtable capacity according to user's input
 }
}