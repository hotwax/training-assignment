class ChainNode
{
 public int val;
 public ChainNode next;
 public ChainNode(int val)
 {
  this.val=val;
  this.next=null;
 }
};
public class Chaining implements CollisionHandler
{
 ChainNode[] hashTable;
 int capacity;

 public int add(int val)
 {
  int hashKey=HashAlgorithm.calculateHash(val)%capacity;
  if(hashTable[hashKey]==null)
  {
   hashTable[hashKey]=new ChainNode(val);
   return 0;
  }

  ChainNode curr=hashTable[hashKey];
   while(curr!=null)        //traversing chain 
   {
    if(curr.val==val)  //until we find same key
    {
     curr.val=val;     //incase updating it
     return 1; 
    }
    else if(curr.next==null)   //or, reach end of chain
    {
     curr.next=new ChainNode(val); //in case adding it
     return 1;
    }
    curr=curr.next;
   }
   return 1;
 }
 
 public Chaining(int capacity)
 {
  this.hashTable=new ChainNode[capacity];
  this.capacity=capacity;
 }
}