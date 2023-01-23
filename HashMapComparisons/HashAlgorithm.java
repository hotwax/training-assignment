class HashAlgorithm
{
 static int calculateHash(int key)
 {
  return key;
  // int result = key;
  // result = 31*result+(key>>16);
  // result = 31*result+(key<<15);
  // result = 31*result^(key>>3);
  
  // if(result<0) result*=(-1);
  
  // return result;
 }
 static boolean isPrime(int n)
 {
  for(int i=n-1;i>1;i--)
   if(n%i==0)
    return false;
  return true;
 }
 static int calculateSecondHash(int key)
 {
  for(int i=key;i>1;i--)
   if(isPrime(i))
    return i;
  return 3;
 }
}