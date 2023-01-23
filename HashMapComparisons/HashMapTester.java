class HashMapTester
{
 public static HashingStat test(CollisionHandler collisionHandler,int numOfOperations)
 {
  long startTime=System.nanoTime();
  int collisions=0;

  for(int i=0;i<numOfOperations;i++)
   collisions+=collisionHandler.add((int)(Math.random()*1000000));

  long endTime=System.nanoTime();
  return new HashingStat(collisions,endTime-startTime);
 }
}