package Chaining;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class chainingTest {
ChainingHashing hashMap=new ChainingHashing(10);
    
   @Test
   void insertionTest(){
    hashMap.insertion("Gourav",30);
    hashMap.insertion("Gourav",50);
    hashMap.insertion("Yash",32);
    hashMap.insertion("Yash",40);
    hashMap.insertion("Vishudhi",35);
    Assertions.assertEquals(50, hashMap.getValue("Gourav"));//value is updated
    Assertions.assertEquals(40, hashMap.getValue("Yash"));//value is updated
    Assertions.assertEquals(35, hashMap.getValue("Vishudhi"));
   }

    @Test
    void deletionTest(){
        Assertions.assertEquals(null, hashMap.delete("Gourav"));//Map is empty
        hashMap.insertion("Gourav",30);
        hashMap.insertion("Gourav",50);
        hashMap.insertion("Yash",32);
        hashMap.insertion("Yash",40);
        hashMap.insertion("Vishudhi",35);
        hashMap.insertion("Niharika",60);
        Assertions.assertEquals("Gourav", hashMap.delete("Gourav"));
        Assertions.assertEquals("Yash", hashMap.delete("Yash"));
        Assertions.assertEquals(null, hashMap.delete("Kunal"));//key is not present in Map 
    }

    @Test
    void getValueTest(){
      Assertions.assertEquals(-1, hashMap.getValue("Gourav"));//Map is empty
      hashMap.insertion("Sam", 20);
      hashMap.insertion("Sagar",40);
      hashMap.insertion("Raghav",60);
      hashMap.insertion("Rohan",80);
      hashMap.insertion("Rudra",35);
      hashMap.insertion("Sagar",70);
       Assertions.assertEquals(20, hashMap.getValue("Sam"));
       Assertions.assertEquals(70, hashMap.getValue("Sagar"));
       Assertions.assertEquals(60, hashMap.getValue("Raghav"));
       Assertions.assertEquals(80, hashMap.getValue("Rohan"));
       Assertions.assertEquals(35, hashMap.getValue("Rudra"));
       Assertions.assertEquals(-1, hashMap.getValue("Roshni"));//key is not present in Map
    }
   
    @Test
    void getSizeTest(){
        hashMap.insertion("Sam", 20);
        hashMap.insertion("Sagar",40);
        hashMap.insertion("Raghav",60);
        hashMap.insertion("Rohan",80);
        hashMap.insertion("Rudra",35);
        hashMap.insertion("Sagar",70);
        hashMap.insertion("Gourav",67);
        hashMap.insertion("Rudra",45);
        Assertions.assertEquals(6,hashMap.getSize());
    }

    @Test
    void clearMapTest(){
        hashMap.insertion("Sam", 20);
        hashMap.insertion("Sagar",40);
        hashMap.insertion("Raghav",60);
        hashMap.insertion("Rohan",80);
        hashMap.insertion("Rudra",35);
        Assertions.assertFalse(hashMap.isEmpty());
        hashMap.insertion("Sagar",70);
        hashMap.insertion("Gourav",67);
        hashMap.insertion("Rudra",45);
        hashMap.clear();
        Assertions.assertTrue(hashMap.isEmpty());
    }

    @Test
    void mapIsEmptyTest(){
       Assertions.assertTrue( hashMap.isEmpty());
       hashMap.insertion("Sam", 20);
        hashMap.insertion("Sagar",40);
        hashMap.insertion("Raghav",60);
        hashMap.insertion("Rohan",80);
       Assertions.assertFalse(hashMap.isEmpty()); 
    }
}
