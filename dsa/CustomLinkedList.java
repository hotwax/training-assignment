import java.util.Scanner;
 
class Node {
  int data;
  Node next;
}

public class CustomLinkedList {

  static Node head, tail;
  static int size;

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    
    while (true) {
      System.out.print("Your linked list: ");
      display();

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
      System.out.println("Enter 12 to end the program");

      int n = sc.nextInt();

      if (n == 1) {
        System.out.print("Enter the value: ");
        int val = sc.nextInt();
        addLast(val);
        System.out.println("-------------------------------------");
      } else if (n == 2) {
        System.out.print("Enter the value: ");
        int val = sc.nextInt();
        addFirst(val);
        System.out.println("-------------------------------------");
      } else if (n == 3) {
        System.out.print("Enter the index: ");
        int idx = sc.nextInt();
        System.out.print("Enter the value: ");
        int val = sc.nextInt();
        addAt(idx, val);
        System.out.println("-------------------------------------");
      } else if (n == 4) {
        removeLast();
        System.out.println("-------------------------------------");
      } else if (n == 5) {
        removeFirst();
        System.out.println("-------------------------------------");
      } else if (n == 6) {
        System.out.print("Enter the index: ");
        int idx = sc.nextInt();
        removeAt(idx);
        System.out.println("-------------------------------------");
      } else if (n == 7) {
        System.out.println(getLast());
        System.out.println("-------------------------------------");
      } else if (n == 8) {
        System.out.println(getFirst());
        System.out.println("-------------------------------------");
      } else if (n == 9) {
        System.out.print("Enter the index: ");
        int idx = sc.nextInt();
        System.out.println(getAt(idx));
        System.out.println("-------------------------------------");
      } else if (n == 10) {
        reverseLL();
        display();
        System.out.println("-------------------------------------");
      } else if (n == 11) {
        System.out.println(midNode());
        System.out.println("-------------------------------------");
      }  else {
        System.out.println("-------------------------------------");
        return;
      }

    }
  }

  static void display() {
    Node temp = head;

    while (temp != null) {
      System.out.print(temp.data + " ");
      temp = temp.next;
    }
    System.out.println();
  }

  static void addLast(int val) {
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

  static void addFirst(int val) {
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

  static void addAt(int idx, int val) {
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

  static void removeLast() {
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

  static void removeFirst() {
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

  static void removeAt(int idx) {
    if (idx < 0 || idx >= size) {
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
      Node nnode = temp.next.next;
      temp.next = nnode;
      size--;
    }
  }

  static int getFirst() {
    if (size == 0) {
      System.out.println("List is empty");
      return -1;
    } else {
      return head.data;
    }
  }

  static int getLast() {
    if (size == 0) {
      System.out.println("List is empty");
      return -1;
    } else {
      return tail.data;
    }
  }

  static int getAt(int idx) {
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

  static void reverseLL() {
    Node prev = null;
    Node curr = head;

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

  static int midNode() {
    Node slow = head;
    Node fast = head;

    while (fast != tail && fast.next != tail) {
      slow = slow.next;
      fast = fast.next.next;
    }

    return slow.data;
  }

}