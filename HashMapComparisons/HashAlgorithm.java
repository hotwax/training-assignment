class HashAlgorithm
{
 static int calculateHash(int key)
 {
  int result = key;
  result = 31*result+(key>>16);
  result = 31*result+(key<<15);
  result = 31*result^(key>>3);
  
  if(result<0) result*=(-1);
  
  return result;
 }
}