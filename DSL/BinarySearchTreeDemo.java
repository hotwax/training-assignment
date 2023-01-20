import java.util.Scanner; 

class BinarySearchTreeDemo
{
 static Scanner scanner= new Scanner(System.in);
 static BinarySearchTree bst = new BinarySearchTree();
 public static void main(String args[])
 {
  try{
    boolean flag=true;
    while(flag)
    {
     System.out.println("Enter a choice:");
     System.out.println("[1] Insert");
     System.out.println("[2] Remove");
     System.out.println("[3] Search");
     System.out.println("[4] Display");
     System.out.println("[0] Exit");
     System.out.print("Your Input(press enter when done):");
     int selected = scanner.nextInt();

     switch(selected)
     {
      case 1: insert(); break;
      case 2: remove(); break;
      case 3: search(); break;
      case 4: displayAll(); break;
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
 public static void insert()
 {
  System.out.println("Enter value to be inserted:");
  int val=scanner.nextInt();
  bst.insert(val);
 }
 public static void search()
 {
  System.out.println("Enter value to be searched:");
  int val=scanner.nextInt();
  if(bst.contains(val))
   System.out.println("Found");
  else
   System.out.println("Not Found");
 }
 public static void remove() throws Exception
 {
  System.out.println("Enter value to be deleted:");
  int key=scanner.nextInt();
  bst.delete(key);
 }
 public static void displayAll()
 {
  System.out.println("Total elements: "+bst.size());
  int val[]=bst.getAll();
  System.out.print("[");
  for(int i=0;i<val.length;i++)
  {
   System.out.print(" "+val[i]);
   if(i!=val.length-1) System.out.print(",");
  }
  System.out.println(" ]");
 }
 public static void defaultMessage(){
  System.out.println("Invalid Input, try again");
 }

}