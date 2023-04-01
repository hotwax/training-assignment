import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomStack {

  public static void main(String[] args) {

    Stack stack = new Stack();
    
    while (true) {
      
      System.out.print("Your Stack: ");
      stack.display(stack.head);
      System.out.println();
      
      System.out.println("Enter 1 to push");
      System.out.println("Enter 2 to pop");
      System.out.println("Enter 3 to peek");
      System.out.println("Enter 4 to search");
      System.out.println("Enter 5 to update");
      System.out.println("Enter 6 to exit the program");
      
      try {
        int val, idx;
        Scanner sc = new Scanner(System.in);
        
        int choice = sc.nextInt();

        switch (choice) {
          case 1:
            System.out.print("Enter the value: ");
            val = sc.nextInt();
            stack.push(val);
            System.out.println("-------------------------------------");
            break;

          case 2:
            if (!stack.isEmpty())
              System.out.println("Popped out value: " + stack.pop());
            System.out.println("-------------------------------------");
            break;

          case 3:
            if (!stack.isEmpty())
              System.out.println("Peeked value: " + stack.peek());
            System.out.println("-------------------------------------");
            break;

          case 4:
            if (!stack.isEmpty()) {
              System.out.print("Enter the value to search: ");
              val = sc.nextInt();
              int serachedVal = stack.search(val);
              if (serachedVal != Integer.MAX_VALUE) {
                if (serachedVal == -1)
                  System.out.println(val + " Doesn't exists.");
                else
                  System.out.println("Searched value is present at index: " + serachedVal);
              }
            }

            System.out.println("-------------------------------------");
            break;

          case 5:
            if (!stack.isEmpty()) {
              System.out.print("Enter the index at which value needs to be updated: ");
              idx = sc.nextInt();
              System.out.print("Enter the new value: ");
              val = sc.nextInt();
              int oldValue = stack.update(idx, val);
              if (oldValue != Integer.MAX_VALUE) {
                System.out.println("Old value: " + oldValue);
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

      } catch (InputMismatchException e) {
        System.out.println("Please give a valid number. " + e);
      }
    }

  }

}

class Stack {

  Node head, tail;
  int size;

  boolean isEmpty() {
    if (size == 0)
      System.out.println("Stack is empty. Please enter 1 to add a number to stack.");
    return size == 0;
  }

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
    if (size == 1) {
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
    return head.data;

  }

  int search(int val) {

    Node temp = head; // a temporary node

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

  int update(int idx, int val) {
    if (idx < 0 || idx >= size) {
      System.out.println("Please enter a valid index");
      return Integer.MAX_VALUE;
    }
    Node temp = head;
    for (int i = 0; i < idx; i++) {
      temp = temp.next;
    }
    int oldValue = temp.data;
    temp.data = val;
    return oldValue;
  }

}

class Node {
  int data;
  Node next;
}