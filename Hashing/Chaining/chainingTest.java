package Chaining;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class chainingTest {
Hashmap hashmap=new Hashmap(10);
    
   @Test
   void insertionTest(){
    hashmap.put("Gourav",30);
    hashmap.put("Gourav",50);
    hashmap.put("Yash",32);
    hashmap.put("Yash",40);
    hashmap.put("Vishudhi",35);
    Assertions.assertEquals(50, hashmap.get("Gourav"));//value is updated
    Assertions.assertEquals(40, hashmap.get("Yash"));//value is updated
    Assertions.assertEquals(35, hashmap.get("Vishudhi"));
}

    @Test
    void deletionTest(){
        Assertions.assertEquals(null, hashmap.delete("Gourav"));//Map is empty
        hashmap.put("Gourav",30);
        hashmap.put("Gourav",50);
        hashmap.put("Yash",32);
        hashmap.put("Yash",40);
        hashmap.put("Vishudhi",35);
        hashmap.put("Niharika",60);
        Assertions.assertEquals("Gourav", hashmap.delete("Gourav"));
        Assertions.assertEquals("Yash", hashmap.delete("Yash"));
        Assertions.assertEquals(null, hashmap.delete("Kunal"));//key is not present in Map 

    }

    @Test
    void getValueTest(){
      Assertions.assertEquals(-1, hashmap.get("Gourav"));//Map is empty
      hashmap.put("Sam", 20);
      hashmap.put("Sagar",40);
      hashmap.put("Raghav",60);
      hashmap.put("Rohan",80);
      hashmap.put("Rudra",35);
      hashmap.put("Sagar",70);
       Assertions.assertEquals(20, hashmap.get("Sam"));
       Assertions.assertEquals(70, hashmap.get("Sagar"));
       Assertions.assertEquals(60, hashmap.get("Raghav"));
       Assertions.assertEquals(80, hashmap.get("Rohan"));
       Assertions.assertEquals(35, hashmap.get("Rudra"));
       Assertions.assertEquals(-1, hashmap.get("Roshni"));//key is not present in Map
    }
   
    @Test
    void getSizeTest(){
        hashmap.put("Sam", 20);
        hashmap.put("Sagar",40);
        hashmap.put("Raghav",60);
        hashmap.put("Rohan",80);
        hashmap.put("Rudra",35);
        hashmap.put("Sagar",70);
        hashmap.put("Gourav",67);
        hashmap.put("Rudra",45);
        Assertions.assertEquals(6,hashmap.Size());
    }

    @Test
    void clearMapTest(){
        hashmap.put("Sam", 20);
        hashmap.put("Sagar",40);
        hashmap.put("Raghav",60);
        hashmap.put("Rohan",80);
        hashmap.put("Rudra",35);
        Assertions.assertFalse(hashmap.isempty());
        hashmap.put("Sagar",70);
        hashmap.put("Gourav",67);
        hashmap.put("Rudra",45);
        hashmap.clear();
        Assertions.assertTrue(hashmap.isempty());
    }

    @Test
    void mapIsEmptyTest(){
       Assertions.assertTrue( hashmap.isempty());
       hashmap.put("Sam", 20);
        hashmap.put("Sagar",40);
        hashmap.put("Raghav",60);
        hashmap.put("Rohan",80);
       Assertions.assertFalse(hashmap.isempty()); 


    }

}
