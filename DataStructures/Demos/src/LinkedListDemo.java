import java.util.Scanner; 

class LinkedListDemo
{
 static Scanner scanner= new Scanner(System.in);
 static LinkedList ll= new LinkedList();
 public static void main(String args[])
 {
  try{
    boolean flag=true;
    while(flag)
    {
    System.out.println("Enter a choice:");
    System.out.println("[1] Add");
    System.out.println("[2] Add at index");
    System.out.println("[3] Delete index");
    System.out.println("[4] Update index");
    System.out.println("[5] Get at index");
    System.out.println("[6] Display all");
    System.out.println("[7] Search for");
    System.out.println("[8] Sort");
    System.out.println("[0] Exit");
    System.out.print("Your Input(press enter when done):");
    int selected = scanner.nextInt();

    switch(selected)
    {
      case 1: add(); break;
      case 2: addAtIndex(); break;
      case 3: deleteIndex(); break;
      case 4: updateIndex(); break;
      case 5: getAtIndex(); break;
      case 6: displayAll(); break;
      case 7: search(); break;
      case 8: sort(); break;
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
  public static void add(){
    System.out.println("Enter the value to added:");
    int val=scanner.nextInt();
    ll.add(val);
    System.out.println(val+" added successfully.");
  }
  
 public static void addAtIndex() throws Exception{
  System.out.println("Enter the index on which to be added:");
  int index=scanner.nextInt();
  System.out.println("Enter the value to added:");
  int val=scanner.nextInt();
  ll.addAtIndex(index,val);
  System.out.println(val+" added successfully at index "+index);
 }
 public static void deleteIndex() throws Exception{
  System.out.println("Enter the index on which to be deleted:");
  int index=scanner.nextInt();
  ll.deleteIndex(index);
  System.out.println(index+" index deleted successfully.");
 }
 public static void updateIndex() throws Exception {
  System.out.println("Enter the index which to be updated:");
  int index=scanner.nextInt();
  System.out.println("Enter the new value to added:");
  int val=scanner.nextInt();
  ll.updateIndex(index,val);
  System.out.println(index+" index updated successfully to "+val);
 }
 public static void getAtIndex() throws Exception {
  System.out.println("Enter the index which to be fetched:");
  int index=scanner.nextInt();
  int val=ll.get(index);
  System.out.println("Value at index: "+index+" ="+val);
 }
 
 public static void displayAll(){
  System.out.println("Total elements: "+ll.size());
  int val[]=ll.getAll();
  System.out.print("[");
  for(int i=0;i<val.length;i++)
  {
   System.out.print(" "+val[i]);
   if(i!=val.length-1) System.out.print(",");
  }
  System.out.println(" ]");
 }
 public static void search(){
  System.out.println("Enter the value which to be searched:");
  int val=scanner.nextInt();
  int index=ll.search(val);
  if(index!=-1)
   System.out.println(val+" found at index "+index);
  else
   System.out.println(val+" not found.");
 }
 public static void sort(){
  ll.sort();
  System.out.println("LinkedList Sorted.");
 }

               
 public static void defaultMessage(){
  System.out.println("Invalid Input, try again");
 }

}