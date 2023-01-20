import java.util.Arrays;

public class DoubleHashing {

  static int[] keys;
  static int[] vals;
  static int currentSize=0, maxSize=0; 
  static int noOfCollisions=0;
  
  DoubleHashing(int size){
      maxSize=size;
      keys=new int[size];
      vals=new int[size];

      Arrays.fill(keys, -1);
      Arrays.fill(vals, -1);
  }

  static void display(){
    for (int i = 0; i < keys.length; i++) {
      if(keys[i]!=-1) System.out.print(keys[i]+" "+vals[i]+", ");
    }
  }

  static void add(int key, int value){
    int i = hash(key);
    int temp=i;

    do{
       if (keys[i]==-1) {
         keys[i]= key;
         vals[i]= value;
         currentSize++;
         return;
       } else if(keys[i]==key){
         vals[i]= value;
         return;
       } else{
         i = (i*i)%maxSize;
         noOfCollisions++;
       }
       
       
    } while(i!=temp);
  }

  static int hash(int key){
     int hash1 = (key+"").hashCode() % maxSize;
     int hash2 = (prime-(key%prime));

     return (hash1+hash2)%maxSize;
  }
 
  static int prime = justSmallerPrimeNum();

  static int justSmallerPrimeNum(){
     int x=0;
     for (int i = 1; i < maxSize; i++) {
        if(isPrime(i)) x=i;
     }

     return x;
  }

  public static boolean isPrime(int n) {  //ts - sqrt(n)
    for(int div=2; div*div <= n; div++){   // div <= root(n)
        if(n%div == 0){
           return false;
        }
    }
    return true;
}

  static void remove(int key){
    if(get(key)==-1) return;

    int i = hash(key);
    while (keys[i]!=key) {
      i=(i*i)%maxSize;
    }
    keys[i]=vals[i]=null;
    
  }

  static int get(int key){
    int i=hash(key);
    while (keys[i]!=-1) {
      if (keys[i]==key) {
        return vals[i];
      }
      i=(i*i)%maxSize;
    }
    return -1;
  }


  public static void main(String[] args) {

    int startingTime = System.currentTimeMillis();
    
    Scanner sc=new Scanner(System.in);
    int capacity = sc.nextInt();
    LinearProbing(capacity);

     while (true) {
       System.out.println("Your hash table: ");
       display();

       System.out.println("Enter 1 to add an element");
       System.out.println("Enter 2 to remove an element");
       System.out.println("Enter 3 to search an element");
       System.out.println("Enter 4 to check whether an element is present");
       System.out.println("Enter 5 to find number of collisions");
       System.out.println("Enter 6 to end the program");

       int n=sc.nextInt();

       if (n==1) {
        System.out.println("Enter the key: ");
        int key=sc.nextInt(); 
        System.out.println("Enter the value: ");
        int value=sc.nextInt();
        add(key, value);
        int endingTime = System.currentTimeMillis();
        System.out.println("Time taken: "+ (endingTime-startingTime));
       } else if (n==2) {
        System.out.println("Enter the key: ");
        int key=sc.nextInt();
        root=remove(key);
       } else if (n==3) {
        System.out.println("Enter the key: ");
        int key=sc.nextInt();
        System.out.println(get(key));
       } else if (n==4) {
        System.out.println("Enter the key: ");
        int key=sc.nextInt();
        if(get(key)==-1) System.out.println("Doesn't exists");
        else System.out.println("Exists");
       } else if(n==5){
        System.out.println(noOfCollisions);
      } else{
         return;
       }
     }
  }
}