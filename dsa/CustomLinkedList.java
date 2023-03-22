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
        System.out.println("Enter 11 to get the middle node");
        System.out.println("Enter 12 to update a value");
        System.out.println("Enter 13 to merge sort the list");
        System.out.println("Enter 14 to exit the program");

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
            System.out.println(ll.midNode(ll.head, ll.tail).data);
            System.out.println("-------------------------------------");
            break;

          case 12:
            System.out.println("Enter the index at which value needs to be updated:");
            idx = sc.nextInt();
            System.out.println("Enter the new value: ");
            val = sc.nextInt();
            int oldValue = ll.updateValue(idx, val);
            if (oldValue != Integer.MAX_VALUE) {
              System.out.println("Old value: " + oldValue);
            }
            System.out.println("-------------------------------------");
            break;

          case 13:
            if (ll.size != 0) {
              LinkedList sortedList = ll.mergeSort(ll.head, ll.tail);
              ll.head = sortedList.head;
              ll.tail = sortedList.tail;
            }
            else System.out.println("List is empty");
            System.out.println("-------------------------------------");
            break;

          case 14:
            System.out.println("Program terminated successfully.");
            System.out.println("-------------------------------------");
            return;

          default:
            System.out.println("Please enter a valid choice.");
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
    } else if (idx < 0 || idx >= size) {
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

  Node midNode(Node head, Node tail) {
    if (size == 0) {
      System.out.println("List is empty");
      return new Node();
    }

    Node slow = head; // slow with reference to speed
    Node fast = head; // fast with reference to speed

    while (fast != tail && fast.next != tail) {
      slow = slow.next;
      fast = fast.next.next;
    }

    return slow;
  }

  int updateValue(int idx, int val) {
    if (size == 0) {
      System.out.println("List is empty");
      return Integer.MAX_VALUE;
    } else if (idx < 0 || idx >= size) {
      System.out.println("Please enter a valid index");
      return Integer.MAX_VALUE;
    }

    else {
      Node temp = head;
      for (int i = 0; i < idx; i++) {
        temp = temp.next;
      }
      int oldVal = temp.data;
      temp.data = val;
      return oldVal;
    }
  }

  LinkedList mergeSort(Node head, Node tail) {
    if (head == tail) {
      LinkedList l = new LinkedList();
      l.addLast(head.data);
      return l;
    }

    Node midNode = midNode(head, tail);

    LinkedList l1 = mergeSort(head, midNode);
    LinkedList l2 = mergeSort(midNode.next, tail);

    return mergeTwoSortedLists(l1, l2);
  }

  LinkedList mergeTwoSortedLists(LinkedList l1, LinkedList l2) {

    LinkedList ans = new LinkedList();
    Node n1 = l1.head;
    Node n2 = l2.head;

    while (n1 != null && n2 != null) {
      if (n1.data <= n2.data) {
        ans.addLast(n1.data);
        n1 = n1.next;
      } else {
        ans.addLast(n2.data);
        n2 = n2.next;
      }
    }

    while (n1 != null) {
      ans.addLast(n1.data);
      n1 = n1.next;
    }

    while (n2 != null) {
      ans.addLast(n2.data);
      n2 = n2.next;
    }

    return ans;
  }
}