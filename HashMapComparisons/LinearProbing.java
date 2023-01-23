class LinearProbing implements CollisionHandler
{
 ProbingNode[] hashTable;
 int capacity;

 public int add(int val)
 {
  int hashKey=HashAlgorithm.calculateHash(val)%capacity;
  if(hashTable[hashKey]==null)
  {
   hashTable[hashKey]=new ProbingNode(val);
   return 0;
  }

  int i=1;
  while(true)
  {
   if(hashTable[(hashKey+i)%capacity]==null)
   {
    hashTable[(hashKey+i)%capacity]=new ProbingNode(val);
    break;
   }
   i++;
  }
  return 1;
 }


 public LinearProbing(int capacity)
 {
  this.hashTable=new ProbingNode[capacity];
  this.capacity=capacity;
 }
}