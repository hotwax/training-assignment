package Queue;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QueueTest {
    Queue<String> queue = new Queue<String>();
    final ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
    @Test
    void queueEnqueueTest() {
        System.setOut(new PrintStream(outputstream));
        queue.push("Gourav");
        queue.push("Yash");
        queue.push("Vishudhi");
        queue.push("Niharika");
        queue.push("Kunal");
        queue.display();
        Assertions.assertEquals("Gourav Yash Vishudhi Niharika Kunal".replaceAll(" ", ""),
                outputstream.toString().replaceAll(" ", ""));
    }

    @Test
    void queueDequeueTest() {
        System.setOut(new PrintStream(outputstream));
        queue.push("Gourav");
        queue.push("Yash");
        queue.push("Vishudhi");
        Assertions.assertEquals("Gourav", queue.frontElement());
        queue.pop();
        Assertions.assertEquals("Yash", queue.frontElement());
        queue.push("Niharika");
        queue.push("Kunal");
        queue.pop();
        queue.display();
        Assertions.assertEquals(" Vishudhi Niharika Kunal".replaceAll(" ", ""),
                outputstream.toString().replaceAll(" ", ""));
    }

    @Test
    void queueFrontTest() {
        queue.push("Gourav");
        queue.push("Palak");
        queue.push("Kritika");
        queue.push("Harshita");
        Assertions.assertEquals("Gourav", queue.frontElement());
        queue.pop();
        Assertions.assertEquals("Palak", queue.frontElement());
        queue.pop();
        Assertions.assertEquals("Kritika", queue.frontElement());
        queue.pop();
        Assertions.assertEquals("Harshita", queue.frontElement());
        queue.pop();
        Assertions.assertEquals(null, queue.frontElement());
    }

    @Test
    void queueSizeTest() {
        Assertions.assertEquals(0, queue.queueSize());
        queue.push("Gourav");
        queue.push("Mamta");
        queue.push("Sam");
        Assertions.assertEquals(3, queue.queueSize());
        queue.pop();
        Assertions.assertEquals(2, queue.queueSize());
    }

    @Test
    void queueIsEmptyTest1() {
        Assertions.assertTrue(queue.isEmpty());
    }

    @Test
    void queueIsEmptyTest2() {
        queue.push("Gourav");
        Assertions.assertFalse(queue.isEmpty());
    }
}
