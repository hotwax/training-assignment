
import java.util.Scanner;
// LINKED LIST IMPLEMENTATION
public class My_Stack {
    Node head;
    public class Node{
        int val;
        Node next;
        
        public Node(int val){
            this.val=val;
        }
    }
    public void push(int x){
        if(head==null){
            head=new Node(x);
            return;
        }
        Node newNode=new Node(x);
        newNode.next=head;
        head=newNode;
    }
    public int pop(){
        if(head==null) return -1;
        int removed=head.val;
        head=head.next;
        return removed;
    }
    public int peek(){
        if(head==null) return -1;
        else return head.val;
    }
    public void update(int a, int b){
        Node curr=head;
        while(curr!=null){
            if(curr.val==a){
                curr.val=b;
                return;
            }
            curr=curr.next;
        }
    }
    public boolean search(int x){
        Node curr=head;
        while(curr!=null){
            if(curr.val==x) return true;
            curr=curr.next;
        }
        return false;
    }
    public void traverseAll(){
        Node curr=head;
        while(curr!=null){
            System.out.print(curr.val+" ");
            curr=curr.next;
        }
        System.out.println();
    }
    public void menu(){
    Scanner sc=new Scanner(System.in);
    
    System.out.println("1: push");
    System.out.println("2: pop");
    System.out.println("3: peek");
    System.out.println("4: traverse");
    System.out.println("5: update");
    System.out.println("6: search");
    System.out.println("7: terminate the program");
    boolean flag=true;
    while(flag){
      int n=sc.nextInt();
      switch(n){
        case 1:// push
          System.out.println("enter no. to push");
          push(sc.nextInt());
          break;
         case 2:// pop
             System.out.println("popped value= "+pop());
             break;
         case 3:// peek
             System.out.println("peeked value= "+peek());
             break;
         case 4:// traverse
             traverseAll();
             break;
         case 5:// update
             System.out.println("enter no. to update and the value");
             update(sc.nextInt(),sc.nextInt());
             break;
         case 6:// search
             System.out.println("enter value to search");
             System.out.println(search(sc.nextInt()));
             break;
         case 7:// end
             flag=false;
             break;
         default:
             System.out.println("invalid input");
             break;
      }
    }
    }
    public static void main(String args[]){
        My_Stack st=new My_Stack();
        st.menu();
    }
}
