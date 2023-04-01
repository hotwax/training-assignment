/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import org.junit.Test;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;

public class CustomLinkedListTest {

    public CustomLinkedListTest() {
    }

    @AfterClass
    public static void printSuccessfulMsg(){
        System.out.println("All test cases are passed.");
    } 
    
    
    @Test
    public void testMain() {
        LinkedList linkedList = new LinkedList();
        
        //empty list
        assertEquals(-1, linkedList.getLast());
        assertEquals(-1, linkedList.getFirst());
        assertEquals(-1, linkedList.getAt(1));
        assertEquals(0, linkedList.midNode(null, null).data);
        

        linkedList.addLast(11);  //list- 11
        linkedList.addLast(22);  //list- 11 22
        linkedList.addLast(33);  //list- 11 22 33

        //check for add at last
        assertEquals(33, linkedList.getLast());
        //--------------------------------------------------------------

        linkedList.addFirst(44);  //list- 44 11 22 33

        //check for add at first
        assertEquals(44, linkedList.getFirst());
        //--------------------------------------------------------------

        linkedList.addAt(1, 55);  //list- 44 55 11 22 33

        //check for add at a position
        assertEquals(55, linkedList.getAt(1));
        //--------------------------------------------------------------

        linkedList.removeLast();  //list- 44 55 11 22 

        //check for remove from last
        assertEquals(22, linkedList.getLast());
        //--------------------------------------------------------------

        linkedList.removeFirst();  //list- 55 11 22

        //check for remove from first
        assertEquals(55, linkedList.getFirst());
        //--------------------------------------------------------------

        linkedList.removeAt(1);  //list- 55 22 

        //check for remove at a position
        assertEquals(22, linkedList.getAt(1));
        //--------------------------------------------------------------

        //check for invalid index
        assertEquals(-1, linkedList.getAt(10));
        linkedList.removeAt(5);
        //--------------------------------------------------------------

        linkedList.addLast(66);  //list- 55 22 66 

        //check for middle node
        assertEquals(22, linkedList.midNode(linkedList.head, linkedList.tail).data);
        //--------------------------------------------------------------
        
        //check for reversing the list
        linkedList.reverseLL();
        linkedList.display();
        //--------------------------------------------------------------
        
        //check for updating a value
        assertEquals(66, linkedList.updateValue(0, 100));
        linkedList.display();
        //--------------------------------------------------------------
        
        //check for merge sorting the list
        LinkedList sortedList = linkedList.mergeSort(linkedList.head, linkedList.tail);
        linkedList.head = sortedList.head;
        linkedList.tail = sortedList.tail;
        linkedList.display();
        //--------------------------------------------------------------
        
        
        
    }

}
