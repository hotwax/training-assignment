class DoubleHashing implements CollisionHandler
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
  int secondHash=HashAlgorithm.calculateSecondHash(val);
  int i=1;
  while(true)
  {
   int newKey=Math.abs((hashKey+i*secondHash)%capacity)%capacity;
   if(hashTable[newKey]==null)
   {
    hashTable[newKey]=new ProbingNode(val);
    break;
   }
   i++;
  }
  return 1;
 }


 public DoubleHashing(int capacity)
 {
  this.hashTable=new ProbingNode[capacity];
  this.capacity=capacity;
 }
}