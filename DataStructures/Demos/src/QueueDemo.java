import java.util.Scanner; 

class QueueDemo
{
 static Scanner scanner= new Scanner(System.in);
 static Queue qu= new Queue();
 public static void main(String args[])
 {
  try{
    boolean flag=true;
    while(flag)
    {
    System.out.println("Enter a choice:");
    System.out.println("[1] Enqueue");
    System.out.println("[2] Dequeue");
    System.out.println("[3] Front");
    System.out.println("[4] Back");
    System.out.println("[5] Display All");
    System.out.println("[0] Exit");
    System.out.print("Your Input(press enter when done):");
    int selected = scanner.nextInt();

    switch(selected)
    {
      case 1: enqueue(); break;
      case 2: dequeue(); break;
      case 3: front(); break;
      case 4: back(); break;
      case 5: displayAll(); break;
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
 public static void enqueue(){
   System.out.println("Enter the value to be enqueued:");
   int val=scanner.nextInt();
   qu.enqueue(val);
   System.out.println(val+" enqueued successfully.");
 }
 public static void front() throws Exception {
  int val=qu.front();
  System.out.println("Front: "+val);
 }
 public static void back() throws Exception {
  int val=qu.back();
  System.out.println("Back: "+val);
 }
 public static void dequeue(){
  qu.dequeue();
}
 public static void displayAll(){
  System.out.println("Total elements: "+qu.size());
  int val[]=qu.getAll();
  System.out.print("back(out)->[");
  for(int i=0;i<val.length;i++)
  {
   System.out.print(" "+val[i]);
   if(i!=val.length-1) System.out.print(",");
  }
  System.out.println(" ]<-front(in)");
 }
              
 public static void defaultMessage(){
  System.out.println("Invalid Input, try again");
 }

}