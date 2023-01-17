import java.util.Scanner;
public class My_Queue {
    Node head,tail;
    class Node{
        int val;
        Node next;
        public Node(int val){
            this.val=val;
        }
    }
    public void add(int x){
        if(head==null){
            head=new Node(x);
            tail=head;
            return;
        }
        Node newNode=new Node(x);
        tail.next=newNode;
        tail=tail.next;
    }
    public int poll(){
        if(head==null) return -1;
        int removed=head.val;
        head=head.next;
        return removed;
    }
    public int peek(){
        if(head==null) return -1;
        else return head.val;
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
    public void menu(){
    Scanner sc=new Scanner(System.in);
    
    System.out.println("1: add");
    System.out.println("2: poll");
    System.out.println("3: peek");
    System.out.println("4: traverse");
    System.out.println("5: update");
    System.out.println("6: search");
    System.out.println("7: terminate the program");
    boolean flag=true;
    while(flag){
      int n=sc.nextInt();
      switch(n){
        case 1:// add
          System.out.println("enter no. to add");
          add(sc.nextInt());
          break;
         case 2:// poll
             System.out.println("polled value= "+poll());
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
        My_Queue q=new My_Queue();
        q.menu();
    }
}
