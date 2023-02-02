package hashing.comparing_all_hashing;

import hashing.random_probing.Random_Probing;
import hashing.quadratic_probing.Quadratic_Probing;
import hashing.linear_probing.Linear_Probing;
import hashing.double_hashing.Double_Hashing;
import hashing.chaining.Chaining_HashTable;
import java.util.Random;

public class Compare_Hashing {

    public static void main(String args[]) {
        int n = 50005, m = 100001;

        Chaining_HashTable chaining = new Chaining_HashTable(m);
        Linear_Probing linearProbing = new Linear_Probing(n, m);
        Quadratic_Probing quadraticProbing = new Quadratic_Probing(n, m);
        Random_Probing randomProbing = new Random_Probing(n, m);
        Double_Hashing doubleHashing = new Double_Hashing(n, m);

        int val = 10;
        Random rand = new Random();

        //calculating time and collisions for chaining
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            int r = rand.nextInt(100000000);
            chaining.put(r, val++);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("chaining= " + chaining.getCollisions());
        System.out.println("time required= " + (endTime - startTime) + " ms");
        System.out.println();

        //calculating time and collisions for Linear probing
        val = 10;
        startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            int r = rand.nextInt((1000000 - 1) + 1) + 1;
            linearProbing.put(r, val++);
        }
        endTime = System.currentTimeMillis();
        System.out.println("linear probing= " + linearProbing.getTotalCollisions());
        System.out.println("time required= " + (endTime - startTime) + " ms");
        System.out.println();

        //calculating time and collisions for quadratic probing
        val = 10;
        startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            int r = rand.nextInt((1000000 - 1) + 1) + 1;
            quadraticProbing.put(r, val++);
        }
        endTime = System.currentTimeMillis();
        System.out.println("quadratic probing= " + quadraticProbing.getTotalCollisions());
        System.out.println("time required= " + (endTime - startTime) + " ms");
        System.out.println();

        //calculating time and collisions for random probing
        val = 10;
        startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            int r = rand.nextInt((1000000 - 1) + 1) + 1;
            randomProbing.put(r, val++);
        }
        endTime = System.currentTimeMillis();
        System.out.println("Random probing= " + randomProbing.getTotalCollisions());
        System.out.println("time required= " + (endTime - startTime) + " ms");
        System.out.println();

        //calculating time and collisions for double hashing
        val = 10;
        startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            int r = rand.nextInt((1000000 - 1) + 1) + 1;
            doubleHashing.put(r, val++);
        }
        endTime = System.currentTimeMillis();
        System.out.println("Double hashing= " + doubleHashing.getTotalCollisions());
        System.out.println("time required= " + (endTime - startTime) + " ms");
    }
}
