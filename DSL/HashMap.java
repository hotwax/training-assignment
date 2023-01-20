class PairNode
{
 int key; 
 int val; 
 PairNode next;
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
  int hashKey=calculateHash(key);
  if(hashTable[hashKey]==null)
   hashTable[hashKey]=new PairNode(key,val);
  else
  {
   PairNode curr=hashTable[hashKey];
   while(curr!=null)
   {
    if(curr.key==key)
    {
     curr.val=val;
     return;
    }
    else if(curr.next==null)
    {
     curr.next=new PairNode(key,val);
     return;
    }
    curr=curr.next;
   }
  }
 }
 public int get(int key) throws Exception
 {
  int hashKey=calculateHash(key);
  PairNode curr=hashTable[hashKey];
  
  while(curr!=null)
  {
   if(curr.key==key)
    return curr.val;
  }
  throw new Exception("Key not found"); 
 }
 public boolean contains(int key)
 {
  int hashKey=calculateHash(key);
  PairNode curr=hashTable[hashKey];

  while(curr!=null)
  {
   if(curr.key==key)
    return true;
  }
  return false;
 }
 public void delete(int key) throws Exception
 {
  int hashKey=calculateHash(key);

  if(hashTable[hashKey].key==key)
  {
   hashTable[hashKey]=hashTable[hashKey].next;
   return;
  }

  PairNode curr=hashTable[hashKey];
  while(curr!=null)
  {
   if(curr.next.key==key)
   {
    curr.next=curr.next.next;
    return;
   }
  }
  throw new Exception("Key not found");
 }
 public int calculateHash(int key)
 {
  int result = key;
  result = 31*result+(key>>16);
  result = 31*result+(key<<15);
  result = 31*result^(key>>3);
  
  if(result<0) result*=(-1);
  
  return result%capacity;
 }
 public HashMap(int capacity)
 {
  this.capacity=capacity;
  this.hashTable=new PairNode[capacity];
 }
}