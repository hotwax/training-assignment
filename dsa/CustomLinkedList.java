import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomLinkedList {

  public static void main(String[] args) {

    try {
      Scanner sc = new Scanner(System.in);
      LinkedList ll = new LinkedList(); // ll- linkedlist

      while (true) {
        System.out.print("Your linked list: ");
        ll.display();

        System.out.println("Enter 1 to add at last");
        System.out.println("Enter 2 to add at First");
        System.out.println("Enter 3 to add at a position");
        System.out.println("Enter 4 to remove from last");
        System.out.println("Enter 5 to remove from First");
        System.out.println("Enter 6 to remove at a position");
        System.out.println("Enter 7 to get from last");
        System.out.println("Enter 8 to get from First");
        System.out.println("Enter 9 to get at a position");
        System.out.println("Enter 10 to reverse the list");
        System.out.println("Enter 11 to get the mid node");
        System.out.println("Enter 12 to exit the program");

        int choice = sc.nextInt();
        int val, idx;

        switch (choice) {
          case 1:
            System.out.print("Enter the value: ");
            val = sc.nextInt(); // value
            ll.addLast(val);
            System.out.println("-------------------------------------");
            break;

          case 2:
            System.out.print("Enter the value: ");
            val = sc.nextInt();
            ll.addFirst(val);
            System.out.println("-------------------------------------");
            break;

          case 3:
            System.out.print("Enter the index: ");
            idx = sc.nextInt(); // index
            System.out.print("Enter the value: ");
            val = sc.nextInt();
            ll.addAt(idx, val);
            System.out.println("-------------------------------------");
            break;

          case 4:
            ll.removeLast();
            System.out.println("-------------------------------------");
            break;

          case 5:
            ll.removeFirst();
            System.out.println("-------------------------------------");
            break;

          case 6:
            System.out.print("Enter the index: ");
            idx = sc.nextInt();
            ll.removeAt(idx);
            System.out.println("-------------------------------------");
            break;

          case 7:
            System.out.println(ll.getLast());
            System.out.println("-------------------------------------");
            break;

          case 8:
            System.out.println(ll.getFirst());
            System.out.println("-------------------------------------");
            break;

          case 9:
            System.out.print("Enter the index: ");
            idx = sc.nextInt();
            System.out.println(ll.getAt(idx));
            System.out.println("-------------------------------------");
            break;

          case 10:
            ll.reverseLL();
            ll.display();
            System.out.println("-------------------------------------");
            break;

          case 11:
            System.out.println(ll.midNode());
            System.out.println("-------------------------------------");
            break;

          case 12:
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
    } catch (Exception e) {
      System.out.println(e);
    }

  }

}

class Node {
  int data;
  Node next; // pointer to the next node
}

class LinkedList {

  Node head, tail;
  int size;

  void display() {
    Node temp = head; // temporary node

    while (temp != null) {
      System.out.print(temp.data + " ");
      temp = temp.next;
    }
    System.out.println();
  }

  void addLast(int val) {
    Node node = new Node();
    node.data = val;
    node.next = null;

    if (size == 0) {
      head = tail = node;
    } else {
      tail.next = node;
      tail = node;
    }
    size++;
  }

  void addFirst(int val) {
    Node temp = new Node();
    temp.data = val;

    if (size == 0) {
      head = tail = temp;
    } else {
      temp.next = head;
      head = temp;
    }

    size++;
  }

  void addAt(int idx, int val) {
    if (idx < 0 || idx > size) {
      System.out.println("Invalid arguments");
      return;
    }
    Node node = new Node();
    node.data = val;

    if (idx == 0) {
      node.next = head;
      head = node;
    } else if (idx == size) {
      tail.next = node;
      tail = node;
    } else {
      Node temp = head;
      for (int i = 1; i < idx; i++) {
        temp = temp.next;
      }
      Node nnode = temp.next; // next node or idx node
      temp.next = node;
      node.next = nnode;
    }
    size++;
  }

  void removeLast() {
    if (size == 0) {
      System.out.println("List is empty");
    } else if (size == 1) {
      head = tail = null;
      size = 0;
    } else {
      Node temp = head;
      while (temp.next != tail) {
        temp = temp.next;
      }
      temp.next = null;
      tail = temp;
      size--;
    }
  }

  void removeFirst() {
    if (size == 0) {
      System.out.println("List is empty");
    } else if (size == 1) {
      head = tail = null;
      size = 0;
    } else {
      head = head.next;
      size--;
    }
  }

  void removeAt(int idx) {
    if (size == 0) {
      System.out.println("List is empty");
    }
    else if (idx < 0 || idx >= size) {
      System.out.println("Invalid arguments");
    } else if (idx == 0) {
      removeFirst();
    } else if (idx == size - 1) {
      removeLast();
    } else {

      Node temp = head;
      for (int i = 1; i < idx; i++) {
        temp = temp.next;
      }
      Node nextnextnode = temp.next.next;
      temp.next = nextnextnode;
      size--;
    }
  }

  int getFirst() {
    if (size == 0) {
      System.out.println("List is empty");
      return -1;
    } else {
      return head.data;
    }
  }

  int getLast() {
    if (size == 0) {
      System.out.println("List is empty");
      return -1;
    } else {
      return tail.data;
    }
  }

  int getAt(int idx) {
    if (size == 0) {
      System.out.println("List is empty");
      return -1;
    } else if (idx < 0 || idx >= size) {
      System.out.println("Invalid arguments");
      return -1;
    }

    Node temp = head;
    for (int i = 1; i <= idx; i++) {
      temp = temp.next;
    }
    return temp.data;
  }

  void reverseLL() {
    Node prev = null; // previous
    Node curr = head; // current

    while (curr != null) {
      Node cnext = curr.next;
      curr.next = prev;

      prev = curr;
      curr = cnext;
    }

    Node temp = head;
    head = tail;
    tail = temp;
  }

  int midNode() {
    if(size==0){
      System.out.println("List is empty");
      return -1;
    }

    Node slow = head; // slow with reference to speed
    Node fast = head; // fast with reference to speed

    while (fast != tail && fast.next != tail) {
      slow = slow.next;
      fast = fast.next.next;
    }

    return slow.data;
  }

}