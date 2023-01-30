import java.util.Scanner;
import java.util.InputMismatchException;
class QueueByArray {
	
  int CAPACITY = 100; // maxium capacity
  int front = -1; // variable which points the front 
  int rear = -1; //variable to point the last inserted element
  int queue[] = new int[CAPACITY];

  void showAll() //to display queue elements
  {
    if (isEmpty()) {
      System.out.println("queue is empty\n");
      return;
    }
    for (int i = front; i <= rear; i++) {
      System.out.print(queue[i] + "  ");
    }
    System.out.println();
  }

  boolean search(int data) // to check if elements is present or not
  {
    for (int index = front; index <= rear; index++) {
      if (queue[index] == data) return true;
    }
    return false;
  }

  int dequeue() // to remove elements
  {
    if (isEmpty()) {
      System.out.println("queue is empty\n");
      return -1;
    }
    return queue[front++];
  }

  int front() // to see first entered element
  {
    if (isEmpty()) {
      System.out.println("queue is empty\n");
      return -1;
    }
    return queue[front];
  }

  boolean isEmpty() //to check if queue is empty
  {
    if (front == -1 || front > rear) return true;
    return false;
  }

  boolean isFull() // to check if queue is full
  {
    if (rear == CAPACITY - 1) return true;
    return false;
  }

  void enqueue(int data) // to insert element
  {
    if (isFull()) {
      System.out.println("Queue is full");
      return;
    }
    if (front == -1) front++;
    queue[++rear] = data;
  }

  void update(int pre, int nw) // to update value of an element
  {
    if (isEmpty()) {
      System.out.println("queue is empty\n");
      return;
    }
    for (int index = front; index <= rear; index++) {
      if (queue[index] == pre) {
        queue[index] = nw;
        System.out.println("Done");
        return;
      }
    }
    System.out.println("Data not fount");
  }
}

class Demo {
  public static void main(String[] args) {
    QueueByArray queue = new QueueByArray();
    int input1, input2;
    while (true) {
      System.out.println("===========================");
      System.out.println("*****Enter your choice*****");
      System.out.println("1.Enqueue");
      System.out.println("2.Dequeue");
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
          queue.enqueue(input1);
          System.out.println("Done ");
          break;

        case 2:
          System.out.println("Element " + queue.dequeue());
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