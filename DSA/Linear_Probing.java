import java.util.*;

public class Linear_Probing {
    int m,n;
    Pair arr[];
    int collisions;
    boolean[] deleted;
    class Pair{
        int key;
        int val;
        public Pair(int k,int v){
            key=k;
            val=v;
        }
    }
    public Linear_Probing(int n, int m){
        this.m=m;
        this.n=n;
        collisions=0;
        arr=new Pair[m];
        deleted=new boolean[m];
    }
    public int hash(Integer k){
        return (k.hashCode()%m);
    }
    public void put(int k, int v){
        int probe=hash(k);
        
        int offset=1;
        //hash finctoion(prob)=(hash(key)+i)%m  {i=1,2,....,m}
        //if probe location is already filled, then check for next location untill find an empty slot or same key to replace if existed
        while((arr[probe]!=null && arr[probe].key!=k) && deleted[probe]){
            probe=(probe+offset)%m;
        }
        //if probe is not equal to original hash of key then its a collision
        //if the same key is inseted again with different value then this is not a collision, its updation
        if(probe!=hash(k)&&(arr[probe]==null || arr[probe].key!=k)) collisions++;
        arr[probe]=new Pair(k,v);
        deleted[probe]=true;
        
    }
    public void delete(int k){
        int hashValue=hash(k);
        int probe=hashValue;
        int offset=1;
        int start=probe;
        while(arr[probe]!=null && arr[probe].key!=k){
            probe=(probe+offset)%m;
            if(probe==start){
                return;
            }
        }
        //if encounter a null value then value to delete is not present
        if(arr[probe]==null){
            return;
        }
        //we can't make arr value null(it will create problrm in searching) thats why maintained a deleted boolean array
        deleted[probe]=false;
        for(int i=0;i<m;i++){
            System.out.print(deleted[i]+" ");
        }
        System.out.println();
    }
    public int get(Integer k){
        int hashValue=hash(k);
        int probe=hashValue;
        int offset=1;
        int start=probe;
        //itearte to find array in cluster
        while(arr[probe]!=null && arr[probe].key!=k){
            probe=(probe+offset)%m;
            //if we again reach to where we started, then element is not present 
            if(probe==start){
                System.out.println("not found;");
                return -1;
            }
        }
        //if encountered a null value then key is not present
        if(arr[probe]==null || !deleted[probe]){
            System.out.println("not found;--outside");
            return -1;
        }
        return arr[probe].val;
    }
    public void traverse(){
        for(int i=0;i<arr.length;i++){
            if(arr[i]==null || !deleted[i]) continue;
            System.out.print("["+arr[i].key+","+arr[i].val+","+i+"] ");
        }
                
        System.out.println();
    }
    public int getTotalCollisions(){
        return collisions;
    }
    public static void main(String args[]){
        
        Scanner sc=new Scanner(System.in);
        
        boolean flag=true;
        System.out.println("Enter size of bucket");
        int m=sc.nextInt();
        System.out.println("Enter size of HashMap");
        int n=sc.nextInt();
        Linear_Probing map=new Linear_Probing(n,m);
        int val=10,key=1;
        Set<Integer> s=new HashSet<>();
        Random rand =new Random();
        for(int i=0;i<100000;i++){
            int r=rand.nextInt((1000000-1)+1)+1;
            if(!s.contains(r))
            map.put(r, val++);
            s.add(r);
        }
        System.out.println("\n1: put");
        System.out.println("2: get");
        System.out.println("3: delete");
        System.out.println("4: traverse");
        System.out.println("5: get collisions");
        System.out.println("6: terminate the program");
        while(flag){
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
                    System.out.println("total collisions= "+map.getTotalCollisions());
                    break;
               case 6:// terminate program
                    flag=false;
                    break;
               default:
                   System.out.println("invalid input");
            }
        }
          
          
    }
}
