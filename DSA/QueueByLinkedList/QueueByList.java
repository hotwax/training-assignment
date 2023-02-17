import java.util.Scanner;
import java.util.InputMismatchException;
class QueueByList {
	
  int data;
  QueueByList next;
  QueueByList front;
  QueueByList rear;
  
  void showAll() // to see inserted elements 
  {
    QueueByList temp = front;
    if (isEmpty()) {
      System.out.println("Queue is empty\n");
      return;
    }
    while (temp != null) {
      System.out.print(temp.data + "  ");
      temp = temp.next;
    }
    System.out.println();
  }

  boolean search(int data) // to search elements
  {
    QueueByList temp = front;
    while (temp != null) {
      if (temp.data == data) return true;
      temp = temp.next;
    }
    return false;
  }

  int deQueue() // to remove element form QueueByList
  {
    if (isEmpty()) {
      System.out.println("Queue is empty\n");
      return -1;
    }
    QueueByList temp = front;
    int data = temp.data;
    front = front.next;
    temp = null;
    return data;
  }

  int front() // to see first inserted element
  {
    if (isEmpty()) {
      System.out.println("Queue is empty\n");
      return -1;
    }
    return front.data;
  }

  boolean isEmpty() // to check if QueueByList is empty or not
  {
    if (front == null) return true;
    return false;
  }

  void enQueue(int data) // to insert an element
  {
    QueueByList temp = new QueueByList();
    if (temp == null) {
      System.out.println("space not available");
      return;
    }
    temp.data = data;
    temp.next = null;
    if (rear == null)
      front = temp;
    else
      rear.next = temp;

    rear = temp;
  }

  void update(int pre, int nw) // to update value of an element
  {
    QueueByList temp = front;
    if (isEmpty()) {
      System.out.println("QueueByList is empty\n");
      return;
    }
    while (temp != null && temp.data != pre) {
      temp = temp.next;
    }
    if (temp != null) {
      temp.data = nw;
      System.out.println("Done");
    } else
      System.out.println("data not found");
  }
}
class Demo {
  public static void main(String[] args) {
	  
    QueueByList queue = new QueueByList();
    int input1, input2;
	
    while (true) {
      System.out.println("===========================");
      System.out.println("*****Enter your choice*****");
      System.out.println("1.EnQueueByList");
      System.out.println("2.DeQueueByList");
      System.out.println("3.showAll");
      System.out.println("4.Search");
      System.out.println("5.Update");
      System.out.println("6.Exit");
      System.out.println("===========================");
      Scanner sc = new Scanner(System.in);

      try {
        int condition = sc.nextInt();
        switch (condition) {
        case 1:
          System.out.println("Enter a number ");
          input1 = sc.nextInt();
          queue.enQueue(input1);
          System.out.println("Done ");
          break;

        case 2:
          System.out.println("Element " + queue.deQueue());
          break;

        case 3:
          queue.showAll();
          break;

        case 4:
          System.out.println("Enter a number ");
          input1 = sc.nextInt();
          System.out.println("Element present : " + queue.search(input1));
          break;

        case 5:
          System.out.println("Enter no. to be replaced ");
          input1 = sc.nextInt();
          System.out.println("Enter a number");
          input2 = sc.nextInt();
          queue.update(input1, input2);
          break;

        case 6:
          System.out.println("Thank you");
          System.exit(0);
          break;

        default:
          System.out.println("Invalid");
          break;
        }
      } catch (InputMismatchException e) {
        System.out.println("Enter valid option");
      }
    }

  }
}