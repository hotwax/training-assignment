package Data_Structures.linked_list;
import java.util.Scanner;

class My_LinkedList{
  
  Node head,tail;
  
  class Node{
    int val;
    Node next;
    
    public Node(int x){
    this.val=x;
    }
  }
  
  public void add(int value_to_add){ //O(1)
    //if head node is null then create one 
    if(head==null){
      head=new Node(value_to_add);
      tail=head;
    } //else insert at the end of LL
    else{
      Node newNode=new Node(value_to_add);
      tail.next=newNode;
      tail=newNode;
    }
  }
  public void delete(int value_to_delete){ //0(n)
    Node curr=head,prev=null;
    if(head==null){
        System.out.println("value not present");
    }
    //if head node contains value
    if(head.val==value_to_delete){
      head=head.next;
      if(head==null) tail=null;
      return;
    }
    //iterate to find value and then delete it
    while(curr!=null){
      if(curr.val==value_to_delete){
        prev.next=curr.next;
        if(prev.next==null) tail=prev;
        return;
      }
      prev=curr;
      curr=curr.next;
    }
  }
  public void update(int oldVal, int newVal){//O(n)
    Node curr=head;
    //iterate all over LL and udate if value is found
    while(curr!=null){
      if(curr.val==oldVal){
        curr.val=newVal;
        return;
      }
      curr=curr.next;
    }
  }
  public boolean search(int value_to_search){//O(n)
    Node curr=head;
    //iterate all over LL and if value is present return true else return false
    while(curr!=null){
      if(curr.val==value_to_search) return true;
      curr=curr.next;
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
  //merge function to merge two sorted LL
  public Node merge(Node node1,Node node2){
    if(node1==null) return node2;
    if(node2==null) return node1;
    Node res=null;
    if(node1.val<node2.val){
      res=node1;
      res.next=merge(node1.next,node2);
    }
    else{
      res=node2;
      res.next=merge(node1,node2.next);
    }
    return res;
  }
  //function to get the middle element used in merge sort.
  //works in O(logn).
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
    Node curr=head;
    while(curr!=null){
      System.out.print(curr.val+" ");
      curr=curr.next;
    }
    System.out.println();
  }
  public void menu(){
    Scanner sc=new Scanner(System.in);
    
    boolean flag=true;
    try{
        while(flag){
            System.out.println("===========================");
            System.out.println("1: add");
            System.out.println("2: delete");
            System.out.println("3: update");
            System.out.println("4: sort");
            System.out.println("5: traverse");
            System.out.println("6: terminate the program");
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
    catch(Exception e){
        System.out.println("Invalid input: please enter Integer");
    }
    
  }
  public static void main(String args[]){
    My_LinkedList ll=new My_LinkedList();
    ll.menu();
  }
}

