
class LinkNode
{
 int val; //Data to be stored
 LinkNode next; //Reference to next node

 public LinkNode(int val, LinkNode next)
 {
  this.val=val;
  this.next=next;
 }
}
public class LinkedList
{
 LinkNode HEAD,TAIL; //References to first and last nodes
 int size;

 public int size()
 {
  return this.size;
 }
 public void add(int val)
 {
  LinkNode newNode= new LinkNode(val,null);  //create new node
  
  if(this.HEAD==null)   
   this.HEAD=newNode;   //if no nodes are there, attach it to head, else
  else
   this.TAIL.next=newNode; // attach it to next after tail
  
  this.TAIL=newNode;  //set the tail to new node
  this.size++;
 }
 public void addAtIndex(int index, int val) throws Exception
 {
  if(index<0||index>=size)
   throw new IndexOutOfBoundsException("Invalid index");
   
  LinkNode newNode= new LinkNode(val,null); //create new node

  if(index==0)
  {
   newNode.next=HEAD;   //if adding it to front, attach it to head, else
   HEAD=newNode;
  }
  else
  {
   LinkNode curr=HEAD;
   for(int i=0;i<index-1;i++)
    curr=curr.next;     //find the predecessor

   newNode.next=curr.next;   //attach it to next of it
   curr.next=newNode;        //set new node next to successor
  } 
  size++; 
 }
 public void deleteIndex(int index)
 {
  if(index<0||index>=size)
   throw new IndexOutOfBoundsException("Invalid index");
  

  LinkNode curr=HEAD;       
  if(index==0)         //if first element is to be deleted, simple move forward HEAD
   HEAD=HEAD.next;
  else
  {
   curr=HEAD;                     //else find predecessor
   for(int i=0;i<index-1;i++)
    curr=curr.next;

   curr.next=curr.next.next;    //move it's successor forward
  }

  curr=HEAD;       //setting TAIL to correct position
  do
  {
   TAIL=curr;
   curr=curr.next;
  }
  while(curr!=null);
  size--;
 }
 public void updateIndex(int index, int val) throws Exception
 {
  if(index<0||index>=size)
   throw new IndexOutOfBoundsException("Invalid index");
  
  LinkNode curr=HEAD;
  for(int i=0;i<index;i++)   //traverse index times
   curr=curr.next;

  curr.val=val;  //update value
  
 }
 public int get(int index) throws Exception
 {
  if(index<0||index>=size)
   throw new IndexOutOfBoundsException("Invalid index");
  
  LinkNode curr=HEAD;
  for(int i=0;i<index;i++)   //traverse index times
   curr=curr.next;

  return curr.val;  //return value
  
 }
 public int[] getAll()
 {
  int arr[]=new int[size];  //create a new array

  LinkNode curr=HEAD;
  for(int i=0;i<size;i++)   //traverse and dump all elements of linked lists on it
  {
   arr[i]=curr.val;
   curr=curr.next;
  }
  return arr; 
 }
 public int search(int val)
 {
  LinkNode curr=HEAD;
  for(int i=0;i<size;i++)  //perform linear search 
  {
   if(curr.val==val)
    return i;       //if found return the 
   curr=curr.next; 
  }
  return -1;     //if not found return -1
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