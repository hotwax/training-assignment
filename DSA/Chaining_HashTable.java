import java.util.*;
public class Chaining_HashTable {
    LinkedList<Pair> arr[];
    int total=0;
    int capacity;
    int collisions;
    class Pair{
        int key;
        int val;
        public Pair(int k,int v){
            key=k;
            val=v;
        }
    }
    public Chaining_HashTable(Integer x){
        capacity=x;
        collisions=0;
        arr=new LinkedList[x];
    }
    public int getCollisions(){
        int bucketElements=0;
        
        for(int i=0;i<arr.length;i++){
              if(arr[i]!=null && arr[i].size()>1){
                  bucketElements++;
              }
        }
        System.out.println(bucketElements+" - "+total);
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
            total++;
            arr[hashValue].add(new Pair(k,v));
        
        //if(arr[hashValue]!=null && arr[hashValue].key!=k) collisions++;
    }
    public int hash(Integer k){
        //System.out.println(k+"---"+k.hashCode()%capacity);
        return (k.hashCode()%capacity);
    }
    public int get(Integer k){
        //System.out.println(" "+arr[hash(k)]key);
        for(Pair p:arr[hash(k)]){
            if(p.key==k) return p.val;
        }
        return -1;
    }
    public static void main(String args[]){
        Chaining_HashTable map=new Chaining_HashTable(10);
        int val=10,key=1;
//        Set<Integer> s=new HashSet<>();
//        Random rand =new Random();
//        for(int i=0;i<10000;i++){
//            int r=rand.nextInt((1000000-1)+1)+1;
//            if(!s.contains(r))
//            map.put(r, val++);
//            s.add(r);
//        }
        map.put(1,10);
        map.put(2,20);
        map.put(3,30);
        map.put(11,40);
        map.put(8686, 789);
        System.out.println(map.get(1));
        System.out.println(map.get(2));
        System.out.println(map.get(3));
        System.out.println(map.get(11));
        System.out.println(map.getCollisions());
    }
}

