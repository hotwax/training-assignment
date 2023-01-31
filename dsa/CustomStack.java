import java.util.Scanner;

public class CustomStack {

  public static void main(String[] args) {

    try {
      Scanner sc = new Scanner(System.in);
      Stack stack = new Stack();

      while (true) {
        System.out.print("Your Stack: ");
        stack.display(stack.head);
        System.out.println();

        System.out.println("Enter 1 to push");
        System.out.println("Enter 2 to pop");
        System.out.println("Enter 3 to peek");
        System.out.println("Enter 4 to search");

        int choice = sc.nextInt();

        if (choice == 1) {
          System.out.print("Enter the value: ");
          int val = sc.nextInt();
          stack.push(val);
          System.out.println("-------------------------------------");
        } else if (choice == 2) {
          System.out.println("Popped out value: " + stack.pop());
          System.out.println("-------------------------------------");
        } else if (choice == 3) {
          System.out.println("Peeked value: " + stack.peek());
          System.out.println("-------------------------------------");
        } else if (choice == 4) {
          System.out.print("Enter the value to search: ");
          int val = sc.nextInt();
          if(stack.search(val)==-1) System.out.println(val+" Doesn't exists.");
          else System.out.println("Searched value is present at index: " + stack.search(val));
          System.out.println("-------------------------------------");
        } else {
          System.out.println("Program terminated successfully.");
          System.out.println("-------------------------------------");
          return;
        }

      }

    } catch (Exception e) {
      System.out.println(e);
    }

  }

}

class Stack {

  Node head, tail;
  int size;

  void push(int val) {
    Node node = new Node();
    node.data = val;
    node.next = null;

    if (size == 0) {
      head = tail = node;
    } else {
      node.next = head;
      head = node;
    }
    size++;
  }

  int pop() {
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
    Node temp = head; //a temporary node

    int idx = 0;
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

}

class Node {
  int data;
  Node next;
}