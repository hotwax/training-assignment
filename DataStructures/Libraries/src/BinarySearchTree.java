
class TreeNode
{
 int val; //Data to be stored
 TreeNode left,right; //Reference to next node

 public TreeNode(int val)
 {
  this.val=val;
  this.left=null;  
  this.right=null;
 }
}
public class BinarySearchTree
{
 TreeNode ROOT; //Reference to root node

 int tmpSize;  //for getAll function
 int tmp[];


 public int getSizeRecursive(TreeNode node)
 {
  if(node==null)  
   return 0;
  
  int size=getSizeRecursive(node.left);  //get size of left subtree
  size+=getSizeRecursive(node.right); //get size of right subtree

  return size+1;
 }
 public int size()
 {
  return getSizeRecursive(ROOT);  //recursively find size
 }
 public TreeNode insertRecursive(TreeNode node, int val)
 {
  if(node==null)
   return new TreeNode(val);    //if blank place reached, insert there
  
   if(val<node.val)
    node.left=insertRecursive(node.left,val);   //traverse left subtree because val<current node's val
   else if(val>node.val)
    node.right=insertRecursive(node.right,val);  //traverse right subtree because val>current node's val

   return node;
 }
 
 public void insert(int val)
 {
  ROOT=insertRecursive(ROOT,val); //recursively insert and return root
 }
 int inorderPredecessor(TreeNode node)
 {
  int predecessor=node.val;
  while(node.left!=null)         
  {
   predecessor=node.left.val;   //traversing left and down untill last node
   node=node.left;
  }
  return predecessor;   //because left most adjacent tree's node is inorder predesscor
 }
 public TreeNode deleteRecursive(TreeNode node, int val)
 {
  if(node==null)
   return node;
  if(val<node.val)
   node.left=deleteRecursive(node.left,val);  //traverse left subtree because val<current node's val
  else if(val>node.val)
   node.right=deleteRecursive(node.right,val); //traverse right subtree because val>current node's val
  else
  {
   if(node.left==null)    // if node to be deleted is branch node
    return node.right;
   if(node.right==null) 
    return node.left;
 
   node.val=inorderPredecessor(node.right);     //if node to be deleted is vertex node
   node.right=deleteRecursive(node.right,node.val);
  }
  return node;  //if node to be deleted is a leaf node
 }
 public void delete(int val)
 {
  ROOT=deleteRecursive(ROOT,val); // recursively delete and return node
 }
 private void inorder(TreeNode node)
 {
  if(node==null)
   return;

  inorder(node.left);     //traversing inorder [left->root->right]
  tmp[tmpSize]=node.val;
  tmpSize++;
  inorder(node.right);
 }
 public int[] getAll()
 {
  tmp=new int[size()];  //create a new array to store elems
  tmpSize=0;
  inorder(ROOT);   //traversing inorder and filling tmp array
  return tmp;   //returning tmp array
 }
 public boolean contains(int val)
 {
  TreeNode curr=ROOT;
  while(curr!=null)
  {
   if(curr.val<val)      //checking right subtree because val>current node's val
    curr=curr.right;
   else if(curr.val>val) //checking left subtree because val<current node's val
    curr=curr.left;
   else
    return true;   //returning true if found
  }
  return false;  //returning false if not found after traversing whole tree
 }


 public BinarySearchTree()
 {
  this.ROOT=null;
 }
}