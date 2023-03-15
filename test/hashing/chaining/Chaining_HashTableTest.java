/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashing.chaining;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dhruv
 */
public class Chaining_HashTableTest {

    @Test
    public void testingMyCode() {
        System.out.println("testing..........");
        Chaining_HashTable map=new Chaining_HashTable(17);
        map.put(10, 100);
        map.put(21, 200);
        map.put(32, 300);
        map.put(43, 400);
        map.put(54, 500);
        map.put(65, 600);
        map.put(76, 700);
        map.put(87, 800);
        map.put(10, 106);
        map.put(21, 205);
        map.put(32, 304);
        map.put(112, 103);
        map.put(243, 202);
        map.put(39, 301);
        
        assertEquals(400, map.get(43));
        assertEquals(500, map.get(54));
        
        map.delete(65);
        
        assertEquals(-1, map.get(65));
        assertEquals(700, map.get(76));
        assertEquals(800, map.get(87));
        assertEquals(2, map.getCollisions());
        
        assertEquals(106, map.get(10));
        assertEquals(205, map.get(21));
        assertEquals(304, map.get(32));
        assertEquals(103, map.get(112));
        assertEquals(202, map.get(243));
        assertEquals(-1, map.get(1000));
        assertEquals(-1, map.get(20003));
        assertEquals(-1, map.get(99999));
        assertEquals(2, map.getCollisions());
        
        map.put(38, 2800);
        assertEquals(3, map.getCollisions());
        System.out.println("testing completed.........");
    }
    
}
