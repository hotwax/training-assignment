package hashing.chaining;
import java.util.LinkedList;
import java.util.Scanner;

public class Chaining_HashTable {
    LinkedList<Pair> arr[];
    int m,n;
    int collisions;
    class Pair{
        int key;
        int val;
        public Pair(int k,int v){
            key=k;
            val=v;
        }
    }
    public Chaining_HashTable(Integer m){
        this.m=m;
        this.n=n;
        collisions=0;
        //creating bucket of size m
        arr=new LinkedList[m];
    }
    public int getCollisions(){
        int bucketElements=0;
        // total no. of collision is equal to the 
        //summation of size of all linkedList(chains) excluding first element
        for(int i=0;i<arr.length;i++){
              if(arr[i]!=null && arr[i].size()>1){
                  bucketElements+=(arr[i].size()-1);
              }
        }
        return bucketElements;
    }
    public void put(Integer k, Integer v){
        int hashValue=hash(k);
        if(arr[hashValue]==null){
            arr[hashValue]=new LinkedList<>();
        }
        for(Pair p:arr[hashValue]){
            if(p.key==k){
                p.val=v;
                return;
            }
        }
        //adding key to chain
        arr[hashValue].add(new Pair(k,v));
        
    }
    public void traverse(){
        for(int i=0;i<arr.length;i++){
            if(arr[i]==null)continue;
            for(Pair p: arr[i])
                System.out.print("["+p.key+","+p.val+"] ");
        }
        System.out.println();
    }
    public void delete(Integer k){
        int hashValue=hash(k);
        if(arr[hashValue]==null) return ;
        //iterating to find matching key
        for(Pair p:arr[hashValue]){
            //if found pair with given key, then removing it
            if(p.key==k){
                arr[hashValue].remove(p);
                return;
            }
        }
    }
    public int hash(Integer k){
        return (k.hashCode()%m);
    }
    public int get(Integer k){ //O(size of chain)
        //iterate to chain stored at hash location to find value of provided key
        
        for(Pair p:arr[hash(k)]){
            if(p.key==k) return p.val;
        }
        return -1;
    }
    public static void main(String args[]){        
        Scanner sc1=new Scanner(System.in);
        boolean flag=true;
        System.out.println("Enter size of bucket");
        int m=sc1.nextInt();
        Chaining_HashTable map=new Chaining_HashTable(m);
        
        while(flag){
            System.out.println("\n1: put");
            System.out.println("2: get");
            System.out.println("3: delete");
            System.out.println("4: traverse");
            System.out.println("5: get collisions");
            System.out.println("6: terminate the program");
            try{
                Scanner sc=new Scanner(System.in);
                int x=sc.nextInt();
                switch(x){
                    case 1:// put
                        System.out.println("enter key and value to put");
                        map.put(sc.nextInt(),sc.nextInt());
                        break;
                   case 2:// get
                        System.out.println("enter key");
                        System.out.println("value= "+map.get(sc.nextInt()));
                        break;
                   case 3: //delete
                       System.out.println("enter key to delete");
                       map.delete(map.get(sc.nextInt()));
                        System.out.println("value deleted successfully");
                        break;
                   case 4:// traverse
                        System.out.println("traversal= ");
                        map.traverse();
                        break;
                   case 5:// get collision
                        System.out.println("total collisions= "+map.getCollisions());
                        break;
                   case 6:// terminate program
                        flag=false;
                        break;
                   default:
                       System.out.println("invalid input");
                }
            }
            catch(Exception e){
                System.out.println("Invalid input: please enter Integer value");
            }
            
        }
    }
}

