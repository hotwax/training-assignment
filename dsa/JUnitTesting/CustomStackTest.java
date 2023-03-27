/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CustomStackTest {

    public CustomStackTest() {
    }

    @AfterClass
    public static void printSuccessfulMsg(){
        System.out.println("All test cases are passed.");
    }  
    
    @Test
    public void testMain() {
        Stack stack = new Stack();

        //check if stack is empty
        assertEquals(true, stack.isEmpty());
        //--------------------------------------------------------------

        stack.push(11); //stack- 11
        stack.push(22); //stack- 22 11
        stack.push(33); //stack- 33 22 11

        //check for push 
        System.out.print("After push: ");
        stack.display(stack.head);
        System.out.println();
        //--------------------------------------------------------------

        //check for peek 
        assertEquals(33, stack.peek());
        //--------------------------------------------------------------
        
        //check for pop 
        assertEquals(33, stack.pop());
        System.out.print("After pop: ");
        stack.display(stack.head);
        System.out.println();
        //--------------------------------------------------------------

        //check for search 
        assertEquals(1, stack.search(11));
        //--------------------------------------------------------------

        //check for update a value - invalid index
        assertEquals(Integer.MAX_VALUE, stack.update(11, 100));
        //--------------------------------------------------------------

        //check for update a value - valid index
        assertEquals(11, stack.update(1, 100)); //stack- 22 100
        System.out.print("After update: ");
        stack.display(stack.head);
        System.out.println();
        //--------------------------------------------------------------
    }

}
