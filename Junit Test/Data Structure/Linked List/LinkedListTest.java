import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListTest {

    @Test
    public void insertStartTest() {
        LinkedList list = new LinkedList();
        list.insertStart(1);
        list.insertStart(2);
        list.insertStart(3);
        list.insertStart(4);
        list.insertStart(5);
        assertEquals(5, list.head.data);
        assertEquals(4, list.head.next.data);
        assertEquals(3, list.head.next.next.data);
        assertEquals(2, list.head.next.next.next.data);
        assertEquals(1, list.head.next.next.next.next.data);
        assertEquals(5, list.len);
    }


    @Test
    public void insertEndTest() {
        LinkedList list = new LinkedList();
        list.insertEnd(1);
        list.insertEnd(2);
        list.insertEnd(3);
        list.insertEnd(4);
        list.insertEnd(5);
        assertEquals(1, list.head.data);
        assertEquals(2, list.head.next.data);
        assertEquals(3, list.head.next.next.data);
        assertEquals(4, list.head.next.next.next.data);
        assertEquals(5, list.head.next.next.next.next.data);
        assertEquals(5, list.len);
    }



    @Test
    public void deleteStartTest() {
        LinkedList list = new LinkedList();
        list.insertEnd(1);
        list.insertEnd(2);
        list.insertEnd(3);
        list.insertEnd(4);
        list.insertEnd(5);
        list.deleteStart();
        assertEquals(2, list.head.data);
        assertEquals(3, list.head.next.data);
        assertEquals(4, list.head.next.next.data);
        assertEquals(5, list.head.next.next.next.data);
        assertEquals(null, list.head.next.next.next.next);
        assertEquals(4, list.len);
    }

    @Test
    public void deleteEndTest() {
        LinkedList list = new LinkedList();
        list.insertEnd(1);
        list.insertEnd(2);
        list.insertEnd(3);
        list.insertEnd(4);
        list.insertEnd(5);
        list.deleteEnd();
        assertEquals(1, list.head.data);
        assertEquals(2, list.head.next.data);
        assertEquals(3, list.head.next.next.data);
        assertEquals(4, list.head.next.next.next.data);
        assertEquals(null, list.head.next.next.next.next);
        assertEquals(4, list.len);
    }


    @Test
    public void updateByPositionTest() {
        LinkedList list = new LinkedList();
        list.insertEnd(1);
        list.insertEnd(2);
        list.insertEnd(3);
        list.insertEnd(4);
        list.insertEnd(5);
        list.updateByPos(2, 10);
        assertEquals(1, list.head.data);
        assertEquals(10, list.head.next.data);
        assertEquals(3, list.head.next.next.data);
        assertEquals(4, list.head.next.next.next.data);
        assertEquals(5, list.head.next.next.next.next.data);
        assertEquals(5, list.len);
    }


    @Test
    public void searchTest() {
        LinkedList list = new LinkedList();
        list.insertEnd(1);
        list.insertEnd(2);
        list.insertEnd(3);
        list.insertEnd(4);
        list.insertEnd(5);
        assertEquals(0, list.search(1));
        assertEquals(1, list.search(2));
        assertEquals(2, list.search(3));
        assertEquals(3, list.search(4));
        assertEquals(4, list.search(5));
        assertEquals(-1, list.search(6));
    }


    @Test
    public void sortingTest() {
        LinkedList list = new LinkedList();
        list.insertEnd(5);
        list.insertEnd(4);
        list.insertEnd(3);
        list.insertEnd(2);
        list.insertEnd(1);
        list.sort();
        assertEquals(1, list.head.data);
        assertEquals(2, list.head.next.data);
        assertEquals(3, list.head.next.next.data);
        assertEquals(4, list.head.next.next.next.data);
        assertEquals(5, list.head.next.next.next.next.data);
        assertEquals(5, list.len);
    }

    
}
