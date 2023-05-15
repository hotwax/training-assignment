import java.util.Scanner;
import java.util.InputMismatchException;

class CustomLinkedList {
  int data;
  CustomLinkedList next;
   CustomLinkedList start = null;

  int countCustomLinkedList() //count nodes
  {
    int c = 0;
    CustomLinkedList tempNode = start;
    while (tempNode != null) {
      c++;
      tempNode = tempNode.next;
    }
    return c;
  }

  CustomLinkedList msort(CustomLinkedList first,CustomLinkedList second) //merge sort to merge two sorted LinkedList
  {
    CustomLinkedList result;
    if (first == null)
      return second;
    if (second == null)
      return first;
    if (first.data <= second.data) {
      result = first;
      result.next = msort(first.next, second);
    } else {
      result = second;
      result.next = msort(first, second.next);
    }
    return result;
  }
  
  void sort() // main method to sort
  {
    start = split(start);
    showAll();
  }
  
  CustomLinkedList split(CustomLinkedList tempNode) // to break a CustomLinkedList into two parts
  {
    if (tempNode == null || tempNode.next == null) {
      return tempNode;
    }

    CustomLinkedList first = getMiddle(tempNode); // to find middle
    CustomLinkedList second = first.next;

    first.next = null;

    CustomLinkedList left = split(tempNode);
    CustomLinkedList right = split(second);

    CustomLinkedList sortedlist = msort(left, right);
    return sortedlist;
  }

  public static CustomLinkedList getMiddle(CustomLinkedList head) //method to find middle
  {
    if (head == null)
      return head;

    CustomLinkedList slow = head, fast = head;

    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  void update(int pre, int nw) // method to update a CustomLinkedList
  {
    CustomLinkedList tempNode = start;
    while (tempNode != null && tempNode.data != pre) {
      tempNode = tempNode.next;
    }
    if (tempNode == null)
      System.out.println("Data not found");
    else
      tempNode.data = nw;
  }

  void showAll() //method to print 
  {
    CustomLinkedList tempNode = start;
    while (tempNode != null) {
      System.out.print(tempNode.data + "  ");
      tempNode = tempNode.next;
    }
    System.out.println();
  }

  void deleteAtB() //deletion at beginning
  {
    if (start == null) {
      System.out.println("list is empty");
      return;
    }
    CustomLinkedList tempNode1 = start;
    start = start.next;
    tempNode1 = null;
  }

  void deleteAtE() // deletion at ending
  {
    if (start == null || start.next == null) {
      deleteAtB();
      return;
    }
    CustomLinkedList tempNode1 = start;
    CustomLinkedList tempNode2 = start;
    while (tempNode1.next != null) {
      tempNode2 = tempNode1;
      tempNode1 = tempNode1.next;
    }
    tempNode2.next = null;
    tempNode1 = null;
  }

  void deleteAtSP(int position) // deletion at specific point
  {
    if (position <= 1) {
      deleteAtB();
      return;
    }
    if (position > countCustomLinkedList()) {
      deleteAtE();
      return;
    }
    CustomLinkedList tempNode1 = start;
    CustomLinkedList t;
    int index;
    for (index = 1; index < position - 1; index++) {
      tempNode1 = tempNode1.next;
    }
    t = tempNode1.next;
    tempNode1.next = t.next;
    t = null;
  }

  void insertAtB(int data) // insertion at beginning
  {
    CustomLinkedList tempNode = new CustomLinkedList();
    tempNode.data = data;
    tempNode.next = start;
    start = tempNode;
  }

  void insertAtE(int data) // insertion at ending
  {
    if (start == null) {
      insertAtB(data);
      return;
    }
    CustomLinkedList tempNode = new CustomLinkedList();
    CustomLinkedList tempNode1 = start;
    tempNode.data = data;
    tempNode.next = null;
    while (tempNode1.next != null)
      tempNode1 = tempNode1.next;

    tempNode1.next = tempNode;
  }

  boolean search(int data) // searching an element 
  {
    CustomLinkedList tempNode = start;
    while (tempNode != null) {
      if (tempNode.data == data) return true;
      tempNode = tempNode.next;
    }
    return false;
  }

  void insertAtSP(int data, int position) // insertion at specific point
  {
    if (position <= 1) {
      insertAtB(data);
      return;
    }
    if (position >= countCustomLinkedList()) {
      insertAtE(data);
      return;
    }
    CustomLinkedList tempNode = new CustomLinkedList();
    CustomLinkedList tempNode1 = start;
    tempNode.data = data;
    for (int index = 1; index < position - 1; index++)
      tempNode1 = tempNode1.next;

    tempNode.next = tempNode1.next;
    tempNode1.next = tempNode;
  }

}
class Main {
  public static void main(String[] args) {
    CustomLinkedList start = new CustomLinkedList();
    int value1, value2;
    while (true) {
      System.out.println("===========================");
      System.out.println("*****Enter your choice*****");
      System.out.println("1.Insert at beginning");
      System.out.println("2.Insert at ending");
      System.out.println("3.Insert at specific position");
      System.out.println("4.Deletion at beginning");
      System.out.println("5.Deletion at ending");
      System.out.println("6.Deletion at specific position");
      System.out.println("7.Update");
      System.out.println("8.Search");
      System.out.println("9.ShowAll");
      System.out.println("10. Sort");
      System.out.println("11.Exit");
      System.out.println("===========================");
      try {
        Scanner sc = new Scanner(System.in);
        int condition = sc.nextInt();
        switch (condition) {
        case 1:
          System.out.println("Enter a number ");
          value1 = sc.nextInt();
          start.insertAtB(value1);
          System.out.println("Done \nList is :");
		  start.showAll();
          break;

        case 2:
          System.out.println("Enter a number ");
          value2 = sc.nextInt();
          start.insertAtE(value2);
          System.out.println("Done \nList is :");
		  start.showAll();
          break;

        case 3:
          System.out.println("Enter a number ");
          value1 = sc.nextInt();
          System.out.println("Enter a position ");
          value2 = sc.nextInt();
          start.insertAtSP(value1, value2);
          System.out.println("Done \nList is :");
		  start.showAll();
          break;

        case 4:
          start.deleteAtB();
          System.out.println("Done \nList is :");
		  start.showAll();
          break;

        case 5:
          start.deleteAtE();
          System.out.println("Done \nList is :");
		  start.showAll();
          break;

        case 6:
          System.out.println("Enter a position ");
          value2 = sc.nextInt();
          start.deleteAtSP(value2);
          System.out.println("Done \nList is :");
		  start.showAll();
          break;

        case 7:
          System.out.println("Enter no. to be replaced ");
          value1 = sc.nextInt();
          System.out.println("Enter new number ");
          value2 = sc.nextInt();
          start.update(value1, value2);
          System.out.println("List is :");
		  start.showAll();
          break;

        case 8:
          System.out.println("Enter a number ");
          value2 = sc.nextInt();
          System.out.println(start.search(value2));
          break;

        case 9:
          start.showAll();
          break;

        case 10:
          start.sort();
          break;

        case 11:
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