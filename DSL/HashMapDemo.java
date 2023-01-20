import java.util.Scanner; 

class HashMapDemo
{
 static Scanner scanner= new Scanner(System.in);
 static HashMap hm;
 public static void main(String args[])
 {
  System.out.println("Enter size of hashtable:");
  int capacity=scanner.nextInt();
  hm= new HashMap(capacity);
  try{
    boolean flag=true;
    while(flag)
    {
    System.out.println("Enter a choice:");
    System.out.println("[1] Set a key value pair");
    System.out.println("[2] Search for a key");
    System.out.println("[3] Delete a key");
    System.out.println("[0] Exit");
    System.out.print("Your Input(press enter when done):");
    int selected = scanner.nextInt();

    switch(selected)
    {
      case 1: set(); break;
      case 2: get(); break;
      case 3: delete(); break;
      case 0: flag=false; break;
      default: defaultMessage(); break;
    }
   }
   }
   catch(Exception e)
   {
    System.out.print(e.getMessage());
   } 
 }
 public static void set()
 {
  System.out.println("Key:");
  int key=scanner.nextInt();
  System.out.println("Value:");
  int val=scanner.nextInt();
  hm.set(key,val);
 }
 public static void get() throws Exception
 {
  System.out.println("Key:");
  int key=scanner.nextInt();
  if(!hm.contains(key))
  {
   System.out.println("Not Found");
   return;
  }
  int val=hm.get(key);
  System.out.println("Value:"+val);
 }
 public static void delete() throws Exception
 {
  System.out.println("Key:");
  int key=scanner.nextInt();
  hm.delete(key);
 }
 public static void defaultMessage(){
  System.out.println("Invalid Input, try again");
 }

}