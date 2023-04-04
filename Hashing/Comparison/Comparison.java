package Hashing.Comparison;

import Hashing.Chaining.Chaining;
import Hashing.DoubleHashing.DoubleHashing;
import Hashing.LinearProbing.LinearProbing;
import Hashing.QuadraticProbing.QuadraticProbing;
import Hashing.RandomProbing.RandomProbing;

import java.util.Random;

public class Comparison {
    public static void main(String[] args) {
        System.out.println("Comparison of HashMaps");

        Chaining.hashMap h = new Chaining.hashMap(50000);
        LinearProbing.hashMap h1 = new LinearProbing.hashMap(50000);
        QuadraticProbing.hashMap h2 = new QuadraticProbing.hashMap(50000);
        RandomProbing h3 = new RandomProbing(50000);
        DoubleHashing.HashMapArray h4 = new DoubleHashing.HashMapArray(50000);
        for (int i = 0; i < 25000; i++) {
            Random r = new Random();
            int val = r.nextInt(1000000);
            h.insert(val, val);
            h1.insert(val, val);
            h2.insert(val, val);
            h3.insert(val, val);
            h4.insert(val, val);
        }
        System.out.println("Chaining: " + h.getCollision());
        System.out.println("Linear Probing: " + h1.getCollision());
        System.out.println("Quadratic Probing: " + h2.getCollision());
        System.out.println("Random Probing: " + h3.getCollision());
        System.out.println("Double Hashing: " + h4.getCollision());

    }
}

