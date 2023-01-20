import java.util.*;

public class Compare_Hashing {
    public static void main(String args[]){
        int n=5005,m=10000;
        
        Chaining_HashTable ch=new Chaining_HashTable(m);
        Linear_Probing lp=new Linear_Probing(n,m);
        Quadratic_Probing qp=new Quadratic_Probing(n,m);
        Double_Hashing db=new Double_Hashing(n,m);
        
        int val=10;
        Set<Integer> s=new HashSet<>();
        Random rand =new Random();
        
        
        //calculating time and collisions for chaining
        long startTime = System.currentTimeMillis();
        for(int i=0;i<n;i++){
            int r=rand.nextInt((1000000-1)+1)+1;
                ch.put(r, val++);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("chaining= "+ch.getCollisions());
        System.out.println("time required= "+(endTime-startTime));
        System.out.println();
        
        
        //calculating time and collisions for Linear probing
        val=10;
        startTime = System.currentTimeMillis();
        for(int i=0;i<n;i++){
            int r=rand.nextInt((1000000-1)+1)+1;
                lp.put(r, val++);
        }
        endTime = System.currentTimeMillis();
        System.out.println("linear probing= "+lp.getTotalCollisions());
        System.out.println("time required= "+(endTime-startTime));
        System.out.println();
        
        
        //calculating time and collisions for quadratic probing
        val=10;
        startTime = System.currentTimeMillis();
        for(int i=0;i<n;i++){
            int r=rand.nextInt((1000000-1)+1)+1;
                qp.put(r, val++);
        }
        endTime = System.currentTimeMillis();
        System.out.println("quadratic probing= "+qp.getTotalCollisions());
        System.out.println("time required= "+(endTime-startTime));
        System.out.println();
        
        
        //calculating time and collisions for double hashing
        val=10;
        startTime = System.currentTimeMillis();
        for(int i=0;i<n;i++){
            int r=rand.nextInt((1000000-1)+1)+1;
                db.put(r, val++);
        }
        endTime = System.currentTimeMillis();
        System.out.println("Double chaining= "+db.getTotalCollisions());
        System.out.println("time required= "+(endTime-startTime));
    }
}
