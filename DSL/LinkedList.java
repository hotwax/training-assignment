
class LinkNode
{
 int val;
 LinkNode next;

 public LinkNode(int val, LinkNode next)
 {
  this.val=val;
  this.next=next;
 }
}
public class LinkedList
{
 LinkNode HEAD,TAIL; 
 int size;

 public int size()
 {
  return this.size;
 }
 public void add(int val)
 {
  LinkNode newNode= new LinkNode(val,null); 
  
  if(this.HEAD==null)
   this.HEAD=newNode;
  else
   this.TAIL.next=newNode;
  
  this.TAIL=newNode;
  this.size++;
 }
 public void addAtIndex(int index, int val) throws Exception
 {
  if(index<0||index>=size)
   throw new IndexOutOfBoundsException("Invalid index");
   
  LinkNode newNode= new LinkNode(val,null); 

  if(index==0)
  {
   newNode.next=HEAD;
   HEAD=newNode;
  }
  else
  {
   LinkNode curr=HEAD;
   for(int i=0;i<index-1;i++)
    curr=curr.next;

   newNode.next=curr.next;
   curr.next=newNode;
  } 
  size++; 
 }
 public void deleteIndex(int index)
 {
  // if(index<0||index>=size)
  //  throw new IndexOutOfBoundsException("Invalid index");

  // if(index==0)
  // {
  //  newNode.next=HEAD;
  //  HEAD=newNode;
  // }
  // else
  // {
  //  LinkNode curr=HEAD;
  //  for(int i=0;i<index-1;i++)
  //   curr=curr.next;

  //  newNode.next=curr.next;
  //  curr.next=newNode;
  // } 
  // ListNode curr=HEAD;

  // for(int i=0;i<index;i++)
  //  cur=cur.next;
  
  // cur.next=cur.next.next;
  // size--;
 }
 public void updateIndex(int index, int val)
 {

 }
 public int get(int index)
 {
 return 1;
 }
 public int[] getAll()
 {
  return null;
 }
 public int search(int val)
 {
  return 1;
 }
 public void sort()
 {

 }

 public LinkedList()
 {
  this.HEAD=null;
  this.TAIL=null;
  this.size=0;
 }
}