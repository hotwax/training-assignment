public class Queue
{
 Node HEAD,TAIL; //References to first and last nodes
 int size;

 public int size()
 {
  return this.size;
 }
 public void enqueue(int val)
 {
  Node newNode= new Node(val,null);  //create new node
  
  if(this.HEAD==null)   
   this.HEAD=newNode;   //if no nodes are there, attach it to head, else
  else
   this.TAIL.next=newNode; // attach it to next after tail
  
  this.TAIL=newNode;  //set the tail to new node
  this.size++;
 }
 public void dequeue()
 {
  if(HEAD!=null)      //remove first node(back)
   HEAD=HEAD.next;
  
  size--;
  if(size==0)
   TAIL=null;
 }
 public int front() throws Exception
 {
  if(size==0)
   throw new Exception("Queue is Empty");
  return TAIL.val;         //returning last node's val
 }
 public int back() throws Exception
 {
  if(size==0)
   throw new Exception("Queue is Empty");
  return HEAD.val;         //returning first node's val
 }

 public int[] getAll()
 {
  int arr[]=new int[size];  //create a new array

  Node curr=HEAD;
  for(int i=0;i<size;i++)   //traverse and dump all elements of linked lists on it
  {
   arr[i]=curr.val;
   curr=curr.next;
  }
  return arr; 
 }
 public Queue()
 {
  this.HEAD=null;
  this.TAIL=null;
  this.size=0;
 }
}