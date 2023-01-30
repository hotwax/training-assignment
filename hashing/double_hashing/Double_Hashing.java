package hashing.double_hashing;

import java.util.*;

public class Double_Hashing {
    int m,n,PM;
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
    public Double_Hashing(int n, int m){
        this.m=m;
        this.n=n;
        collisions=0;
        arr=new Pair[m];
        deleted=new boolean[m];
        PM=getPrime();
    }
    public int hash1(Integer k){
        return (k.hashCode()%m);
    }
    public int hash2(Integer k){
        //here hash2 should never return 0, thats why
        return PM;
    }
    public int getPrime(){
        for (int i = m - 1; i >= 1; i--) { 
            int cnt = 0;
            for (int j = 2; j * j <= i; j++)
                if (i % j == 0)
                    cnt++;
            if (cnt == 0)
                return i;
        }
        return 3;
    }
    public void put(int k, int v){
        int probe=hash1(k);
        int offset=hash2(k);
        //hash finctoion(prob)=(hash1(key)+i*hash2(key)%m  {i=1,2,....,m}
        //here hash2 will never return 0
        //if probe location is already filled, then check for next location until find an empty slot or same key to replace if exist
        while((arr[probe]!=null && arr[probe].key!=k) && deleted[probe]){
            //System.out.println(k+" "+probe+" "+offset);
            probe=(probe+offset)%m;
        }
        //if probe is not equal to original hash of key then its a collision
        //if the same key is inseted again with different value then this is not a collision, its updation
        if(probe!=hash1(k)&&(arr[probe]==null || arr[probe].key!=k)) collisions++;
        arr[probe]=new Pair(k,v);
        deleted[probe]=true;
        
    }
    public void delete(int k){
        int hashValue=hash1(k);
        int probe=hashValue;
        int offset=hash2(k);
        int start=probe;
        while(arr[probe]!=null && arr[probe].key!=k){
            probe=(probe+offset)%m;
            if(probe==start){
                System.out.println("not found;--inside");
                return;
            }
        }
        //if encounter a null value then value to delete is not present
        if(arr[probe]==null){
            System.out.println("not found;--outside");
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
        int hashValue=hash1(k);
        int probe=hashValue;
        int offset=hash2(k);
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
        
        Scanner sc1=new Scanner(System.in);
        
        boolean flag=true;
        System.out.println("Enter size of bucket");
        int m=sc1.nextInt();
        System.out.println("Enter size of HashMap");
        int n=sc1.nextInt();
        Double_Hashing map=new Double_Hashing(n,m);
        int val=10,key=1;
        Set<Integer> s=new HashSet<>();

        
        while(flag){
            System.out.println("==========================");
            System.out.println("\n1: put");
            System.out.println("2: get");
            System.out.println("3: delete");
            System.out.println("4: traverse");
            System.out.println("5: get collisions");
            System.out.println("6: terminate the program");
            System.out.println();
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
               case 3:
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
            catch(Exception e){
                System.out.println("Invalid input: please enter Integer");
            }
        }
          
    }
}
