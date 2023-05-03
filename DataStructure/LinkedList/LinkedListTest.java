package LinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LinkedListTest {
    LinkedList list = new LinkedList();
    final ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
    @Test
    void insertionTest() {
        list.insertionToHead(20);
        list.insertionToHead(30);
        list.insertionToHead(50);
        list.insertionToLast(60);
        list.insertionToLast(70);
        list.insertionToLast(10);
        list.insertionAtAnyPos(320, 10);// invalid postion
        list.insertionAtAnyPos(80, 7);// insertion in last position
        list.insertionAtAnyPos(26, 4);// insertion in 4th position
        list.insertionToLast(90);
        System.setOut(new PrintStream(outputstream));
        list.print();
        Assertions.assertEquals("50 30 20 26 60 70  10 80  90".replaceAll(" ", ""),
                outputstream.toString().trim().replaceAll("->", ""));
    }

    @Test
    void deletionTest() {
        list.insertionToHead(20);
        list.insertionToHead(30);
        list.insertionToHead(50);
        list.insertionToLast(60);
        list.insertionToLast(70);
        list.insertionToLast(10);
        list.insertionToHead(27);
        list.insertionAtAnyPos(40, 4);
        Assertions.assertEquals(27, list.deleteFromHead());
        Assertions.assertEquals(10, list.deleteFromLast());
        Assertions.assertEquals(40, list.deleteFromAnyPos(3));
        Assertions.assertEquals(-1, list.deleteFromAnyPos(10));// invalid position
        System.setOut(new PrintStream(outputstream));
        list.print();
        Assertions.assertEquals("50 30 20 60 70".replaceAll(" ", ""),
                outputstream.toString().trim().replaceAll("->", ""));
    }

    @Test
    void updationTest() {
        list.insertionToHead(20);
        list.insertionToHead(30);
        list.insertionToHead(50);
        list.insertionToLast(60);
        list.insertionToLast(70);
        list.insertionToLast(10);
        list.insertionToHead(27);
        System.setOut(new PrintStream(outputstream));
        list.print();
        Assertions.assertEquals("27 50 30 20 60 70 10 ".replaceAll(" ", ""),
                outputstream.toString().trim().replaceAll("->", ""));
        outputstream.reset();
        list.updateAtPos(90, 3);
        System.setOut(new PrintStream(outputstream));
        list.print();
        Assertions.assertEquals("27 50 90 20 60 70 10 ".replaceAll(" ", ""),
                outputstream.toString().trim().replaceAll("->", ""));
    }

    @Test
    void searchInListTest() {
        list.insertionToHead(20);
        list.insertionToHead(30);
        list.insertionToHead(50);
        list.insertionToLast(60);
        list.insertionToLast(70);
        list.insertionToLast(10);
        list.insertionToHead(27);
        Assertions.assertTrue(list.searchDataInList(70));
        Assertions.assertFalse(list.searchDataInList(100));
    }

    @Test
    void sortListTest() {
        list.insertionToHead(20);
        list.insertionToHead(30);
        list.insertionToHead(50);
        list.insertionToLast(60);
        list.insertionToLast(70);
        list.insertionToLast(10);
        list.insertionToHead(27);
        System.setOut(new PrintStream(outputstream));
        list.print();
        Assertions.assertEquals("27 50 30 20 60 70 10 ".replaceAll(" ", ""),
                outputstream.toString().trim().replaceAll("->", ""));
        outputstream.reset();
        System.setOut(new PrintStream(outputstream));
        list.sort();
        list.print();
        Assertions.assertEquals("10 20 27 30 50 60 70 ".replaceAll(" ", ""),
                outputstream.toString().trim().replaceAll("->", ""));
    }

    @Test
    void listIsEmptyTest() {
        Assertions.assertTrue(list.listIsEmpty());
        list.insertionToHead(20);
        list.insertionToLast(40);
        Assertions.assertFalse(list.listIsEmpty());
    }
}
