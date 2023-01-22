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
public class Chaining
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
 public HashingStat test(int numOfOperations)
 {
  long startTime=System.currentTimeMillis();
  int collisions=0;

  for(int i=0;i<numOfOperations;i++)
   collisions+=add((int)(Math.random()*1000000));

  long endTime=System.currentTimeMillis();
  return new HashingStat(collisions,endTime-startTime);
 }
 public Chaining(int capacity)
 {
  this.hashTable=new ChainNode[capacity];
  this.capacity=capacity;
 }
}