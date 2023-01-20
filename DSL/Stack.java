class Node
{
 int val; //Data to be stored
 Node next; //Reference to next node

 public Node(int val, Node next)
 {
  this.val=val;
  this.next=next;
 }
}
public class Stack
{
 Node HEAD; //References to first and last nodes
 int size;

 public int size()
 {
  return this.size;
 }

 public void pop()
 {
  if(HEAD!=null)       //moving HEAD forward
   HEAD=HEAD.next;
  size--;
 }
 public void push(int val)
 {
  Node newNode= new Node(val,HEAD);  //creating new node as predeccesor of HEAD
  HEAD=newNode;            //setting new node as HEAD
  size++; 
 }

 public int top() throws Exception
 {
  if(size==0)
   throw new Exception("Stack is Empty");
  return HEAD.val;         //returning top node's val
 }
 public int[] getAll()
 {
  int arr[]=new int[size];  //create a new array

  Node curr=HEAD;
  for(int i=0;i<size;i++)   //traverse and dump all elements of stack on it
  {
   arr[i]=curr.val;
   curr=curr.next;
  }
  return arr; 
 }
 public Stack()
 {
  this.HEAD=null;
  this.size=0;
 }
}