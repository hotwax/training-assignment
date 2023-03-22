import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomQueue {

  public static void main(String[] args) {

    try {
      Scanner sc = new Scanner(System.in);

      Queue queue = new Queue();

      while (true) {
        System.out.print("Your Queue: ");
        queue.display(queue.head);
        System.out.println();

        System.out.println("Enter 1 to add");
        System.out.println("Enter 2 to remove");
        System.out.println("Enter 3 to peek");
        System.out.println("Enter 4 to search");
        System.out.println("Enter 5 to update");
        System.out.println("Enter 6 to exit the program");

        int choice = sc.nextInt();
        int val, idx;

        switch (choice) {
          case 1:
            System.out.print("Enter the value: ");
            val = sc.nextInt();
            queue.add(val);
            System.out.println("-------------------------------------");
            break;

          case 2:
            if (!queue.isEmpty())
              System.out.println("Removed value: " + queue.remove());
            System.out.println("-------------------------------------");
            break;

          case 3:
            if (!queue.isEmpty())
              System.out.println("Peeked value: " + queue.peek());
            System.out.println("-------------------------------------");
            break;

          case 4:
            if (!queue.isEmpty()) {
              System.out.print("Enter the value to search: ");
              val = sc.nextInt();
              if (queue.search(val) == -1)
                System.out.println(val + " Doesn't exists.");
              else
                System.out.println("Searched value is present at index: " + queue.search(val));
            }
            System.out.println("-------------------------------------");
            break;

          case 5:
          if (!queue.isEmpty()) {
            System.out.print("Enter the index at which value needs to be updated: ");
            idx = sc.nextInt();
            System.out.print("Enter the new value: ");
            val = sc.nextInt();
            int oldValue = queue.update(idx, val);
            if (oldValue!=Integer.MAX_VALUE) {
              System.out.println("Old value: " +oldValue);
            }
          }
          System.out.println("-------------------------------------");
          break;

          case 6:
            System.out.println("Program terminated successfully.");
            System.out.println("-------------------------------------");
            return;

          default:
            System.out.println("Please enter a valid choice (1,2,3,4,5).");
            System.out.println("-------------------------------------");
            break;
        }

      }

    } catch (InputMismatchException e) {
      System.out.println("Please give a valid number. " + e);
    }

  }

}

class Node {
  int data;
  Node next;
}
 
class Queue {

  Queue(){


  }

  Node head, tail;
  int size;

  boolean isEmpty() {
    if (size == 0)
      System.out.println("Queue is empty. Please enter 1 to add a number to queue.");
    return size == 0;
  }

  void add(int val) {
    Node temp = new Node(); // a temporary node
    temp.data = val;

    if (size == 0) {
      head = tail = temp;
    } else {
      tail.next = temp;
      tail = temp;
    }

    size++;
  }

  int remove() {
    if (size == 0) {
      System.out.println("List is empty");
      return -1;
    } else if (size == 1) {
      int headData = head.data;
      head = tail = null;
      size = 0;
      return headData;
    } else {
      int headData = head.data;
      head = head.next;
      size--;
      return headData;
    }
  }

  int peek() {
    if (size == 0) {
      System.out.println("List is empty");
      return -1;
    } else {
      return head.data;
    }
  }

  int search(int val) {
    Node temp = head;

    int idx = 0; // index
    while (temp != null) {
      if (temp.data == val)
        return idx;
      temp = temp.next;
      idx++;
    }

    return -1;
  }

  void display(Node node) {
    if (node == null)
      return;

    System.out.print(node.data + " ");
    display(node.next);
  }

  int size() {
    return size;
  }

  int update(int idx, int val){
    if(idx<0 || idx>=size){
      System.out.println("Please enter a valid index");
      return Integer.MAX_VALUE;
    }
    Node temp=head;
    for (int i = 0; i < idx; i++) {
      temp=temp.next;
    }
    int oldValue = temp.data;
    temp.data=val;
    return oldValue;
  }


}

