class DoubleHashing
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
   int newKey=(hashKey+HashAlgorithm.calculateHash(i))%capacity;
   if(hashTable[newKey]==null)
   {
    hashTable[newKey]=new ProbingNode(val);
    break;
   }
   i++;
  }
  return 1;
 }

 public HashingStat test(int numOfOperations)
 {
  int allValues[]=new int[numOfOperations];
  for(int i=0;i<numOfOperations;i++)
   allValues[i]=(int)(Math.random()*1000000);
  
  long startTime=System.currentTimeMillis();
  int collisions=0;

  for(int i=0;i<numOfOperations;i++)
   collisions+=add(allValues[i]);

  long endTime=System.currentTimeMillis();
  return new HashingStat(collisions,endTime-startTime);
 }
 public DoubleHashing(int capacity)
 {
  this.hashTable=new ProbingNode[capacity];
  this.capacity=capacity;
 }
}