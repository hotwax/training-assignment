import java.util.*;


class My_LinkedList{
  
  Node head,curr;
  
  class Node{
    int val;
    Node next;
    
    public Node(int x){
    this.val=x;
  }
  }
  
  public void add(int x){ //O(1)
    if(head==null){
      head=new Node(x);
      curr=head;
    }
    else{
      Node n=new Node(x);
      curr.next=n;
      curr=curr.next;
    }
  }
  public void delete(int x){ //0(n)
    Node temp=head,prev=null;
    if(head.val==x){
      head=head.next;
      return;
    }
    while(temp!=null){
      if(temp.val==x){
        prev.next=temp.next;
        if(prev.next==null) curr=prev;
        return;
      }
      prev=temp;
      temp=temp.next;
    }
  }
  public void update(int a, int b){//O(n)
    Node temp=head;
    while(temp!=null){
      if(temp.val==a){
        temp.val=b;
        return;
      }
      temp=temp.next;
    }
  }
  public boolean search(int x){//O(n)
    Node temp=head;
    while(temp!=null){
      if(temp.val==x) return true;
      temp=temp.next;
    }
    return false;
  }
  
  //Merge Sort
  public void sort(){
    head=sort(head);
  }
  public Node sort(Node root){//O{nlogn}
    if(root==null || root.next==null) return root;
    //partitioning list into 2 half
    Node mid=getMiddle(root);
    Node secRoot=mid.next;
    mid.next=null;
    //sorting both halves
    Node left=sort(root);
    Node right=sort(secRoot);
    //merging them
    Node res=merge(left,right);
    return res;
  }
  public Node merge(Node a,Node b){
    if(a==null) return b;
    if(b==null) return a;
    Node res=null;
    if(a.val<b.val){
      res=a;
      res.next=merge(a.next,b);
    }
    else{
      res=b;
      res.next=merge(a,b.next);
    }
    return res;
  }
  public Node getMiddle(Node root){
    if (root==null) return root;
    Node slow = root, fast = root;
    while (fast.next != null && fast.next.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    return slow;
  }
  
  public void traverseAll(){//O(n)
    Node temp=head;
    while(temp!=null){
      System.out.print(temp.val+" ");
      temp=temp.next;
    }
    System.out.println();
  }
  public void menu(){
    Scanner sc=new Scanner(System.in);
    
    System.out.println("1: add");
    System.out.println("2: delete");
    System.out.println("3: update");
    System.out.println("4: sort");
    System.out.println("5: traverse");
    System.out.println("6: terminate the program");
    boolean flag=true;
    while(flag){
      int n=sc.nextInt();
      switch(n){
        case 1:// add
          System.out.println("enter no. to add");
          add(sc.nextInt());
          break;
         case 2:// delete
             System.out.println("enter no. to delete");
             delete(sc.nextInt());
             break;
         case 3:// upadte
             System.out.println("enter no. to update and the value");
             update(sc.nextInt(),sc.nextInt());
             break;
         case 4:// sort
             sort();
         case 5:// traverse
             traverseAll();
             break;
         case 6:// end
             flag=false;
             break;
         default:
             System.out.println("invalid input");
             break;
      }
    }
    
  }
  public static void main(String args[]){
    My_LinkedList ll=new My_LinkedList();
    ll.menu();
  }
}

