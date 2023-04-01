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
public class CustomQueueTest {
    
    public CustomQueueTest() {
    }

    @AfterClass
    public static void printSuccessfulMsg() {
        System.out.println("All test cases are passed.");
    }

    @Test
    public void testMain() {
        Queue queue = new Queue();

        //check if queue is empty
        assertEquals(true, queue.isEmpty());
        //--------------------------------------------------------------

        queue.add(11); //queue- 11
        queue.add(22); //queue- 11 22
        queue.add(33); //queue- 11 22 33

        //check for add 
        System.out.print("After add: ");
        queue.display(queue.head);
        System.out.println();
        //--------------------------------------------------------------

        //check for peek
        assertEquals(11, queue.peek());
        //--------------------------------------------------------------

        //check for remove
        assertEquals(11, queue.remove()); //queue- 22 33
        System.out.print("After remove: ");
        queue.display(queue.head);
        System.out.println();

        //check for search 
        assertEquals(1, queue.search(33));
        //--------------------------------------------------------------


    }
    
}
