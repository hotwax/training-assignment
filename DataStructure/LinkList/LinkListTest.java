package LinkList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LinkListTest {
    linklist list = new linklist();

    @Test
    void insertionTest() {
        final ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
        list.insertiontohead(20);
        list.insertiontohead(30);
        list.insertiontohead(50);
        list.insertiontolast(60);
        list.insertiontolast(70);
        list.insertiontolast(10);
        list.insertionatanypos(320, 10);// invalid postion
        list.insertionatanypos(80, 7);// insertion in last position
        list.insertionatanypos(26, 4);// insertion in 4th position
        list.insertiontolast(90);
        System.setOut(new PrintStream(outputstream));
        list.print();
        Assertions.assertEquals("50 30 20 26 60 70  10 80  90".replaceAll(" ", ""),
                outputstream.toString().trim().replaceAll("->", ""));
    }

    @Test
    void deletionTest() {
        final ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
        list.insertiontohead(20);
        list.insertiontohead(30);
        list.insertiontohead(50);
        list.insertiontolast(60);
        list.insertiontolast(70);
        list.insertiontolast(10);
        list.insertiontohead(27);
        list.insertionatanypos(40, 4);
        Assertions.assertEquals(27, list.deletefromhead());
        Assertions.assertEquals(10, list.deletefromlast());
        Assertions.assertEquals(40, list.deletefromanypos(3));
        Assertions.assertEquals(-1, list.deletefromanypos(10));// invalid position
        System.setOut(new PrintStream(outputstream));
        list.print();
        Assertions.assertEquals("50 30 20 60 70".replaceAll(" ", ""),
                outputstream.toString().trim().replaceAll("->", ""));
    }

    @Test
    void updationTest() {
        final ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
        list.insertiontohead(20);
        list.insertiontohead(30);
        list.insertiontohead(50);
        list.insertiontolast(60);
        list.insertiontolast(70);
        list.insertiontolast(10);
        list.insertiontohead(27);
        System.setOut(new PrintStream(outputstream));
        list.print();
        Assertions.assertEquals("27 50 30 20 60 70 10 ".replaceAll(" ", ""),
                outputstream.toString().trim().replaceAll("->", ""));
        outputstream.reset();
        list.updateAtpos(90, 3);
        System.setOut(new PrintStream(outputstream));
        list.print();
        Assertions.assertEquals("27 50 90 20 60 70 10 ".replaceAll(" ", ""),
                outputstream.toString().trim().replaceAll("->", ""));
    }

    @Test
    void searchInListTest() {
        list.insertiontohead(20);
        list.insertiontohead(30);
        list.insertiontohead(50);
        list.insertiontolast(60);
        list.insertiontolast(70);
        list.insertiontolast(10);
        list.insertiontohead(27);
        Assertions.assertTrue(list.search(70));
        Assertions.assertFalse(list.search(100));

    }

    @Test
    void sortListTest() {
        final ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
        list.insertiontohead(20);
        list.insertiontohead(30);
        list.insertiontohead(50);
        list.insertiontolast(60);
        list.insertiontolast(70);
        list.insertiontolast(10);
        list.insertiontohead(27);
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
        Assertions.assertTrue(list.isempty());
        list.insertiontohead(20);
        list.insertiontolast(40);
        Assertions.assertFalse(list.isempty());
    }

}
