/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author nayan
 */
public class CustomHashMapTest {
    
    public CustomHashMapTest() {
    }
    
    @AfterClass
    public static void printSuccessfulMsg(){
        System.out.println("All test cases passed.");
    } 

    @Test
    public void testMain() {
        //check for 0 size of map
        HashMap map = new HashMap(0);
        
        //check for map creation
        HashMap validMap = new HashMap(4);
        
        //check for add
        validMap.put(11, "nidhi");
        validMap.put(22, "kiana");
        validMap.put(33, "hitanshi");
        validMap.put(44, "anjali");
        
        System.out.print("After put: ");
        validMap.display();
        System.out.println();
        
        //check for add- entered an existing key
        validMap.put(11, "palak");
        System.out.print("After put: ");
        validMap.display();
        System.out.println();
        
        //remove a non existing pair
        validMap.remove(100);
        
        //remove an existing pair
        validMap.remove(44);
        System.out.print("After remove: ");
        validMap.display();
        System.out.println();
        
        //get a non existing pair
        validMap.get(100);
        
        //get an existing pair
        validMap.get(22);
        
        //check whether a pair exists- doesn't exists
        assertEquals(false, validMap.containsKey(100));
        
        //check whether a pair- exists
        assertEquals(true, validMap.containsKey(22));
        
        
    }
    
}
