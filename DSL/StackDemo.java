import java.util.Scanner; 

class StackDemo
{
 static Scanner scanner= new Scanner(System.in);
 static Stack st= new Stack();
 public static void main(String args[])
 {
  try{
    boolean flag=true;
    while(flag)
    {
    System.out.println("Enter a choice:");
    System.out.println("[1] Push");
    System.out.println("[2] Top");
    System.out.println("[3] Pop");
    System.out.println("[4] Display all");
    System.out.println("[0] Exit");
    System.out.print("Your Input(press enter when done):");
    int selected = scanner.nextInt();

    switch(selected)
    {
      case 1: push(); break;
      case 2: top(); break;
      case 3: pop(); break;
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
 public static void push(){
   System.out.println("Enter the value to be pushed:");
   int val=scanner.nextInt();
   st.push(val);
   System.out.println(val+" pushed successfully.");
 }
 public static void top() throws Exception {
  int val=st.top();
  System.out.println("Top: "+val);
 }
 public static void pop(){
  st.pop();
}
 public static void displayAll(){
  System.out.println("Total elements: "+st.size());
  int val[]=st.getAll();
  System.out.print("top->[");
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