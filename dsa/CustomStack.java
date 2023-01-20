import java.util.Scanner;
import CustomLinkedList;

class Node {
  int data;
  Node next;
}

public class CustomStack{

  static Node head, tail;
  static int size;

  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);

    while (true) {
      System.out.print("Your Stack: ");
      display(head);
      System.out.println();

      System.out.println("Enter 1 to push");
      System.out.println("Enter 2 to pop");
      System.out.println("Enter 3 to peek");
      System.out.println("Enter 4 to search");
      System.out.println("Enter 5 to end the program");

      int n = sc.nextInt();

      if (n == 1) {
        System.out.print("Enter the value: ");
        int val = sc.nextInt();
        addLast(val);
        System.out.println("-------------------------------------");
      } else if (n == 2) {
        removeLast();
        System.out.println("-------------------------------------");
      } else if (n == 3) {
        System.out.println(getLast());
        System.out.println("-------------------------------------");
      } else if (n == 4) {
        int val = sc.nextInt();
        System.out.println(search(val));
        System.out.println("-------------------------------------");
      } else {
        System.out.println("-------------------------------------");
        return;
      }

    }
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


  static int getLast() {
    if (size == 0) {
      System.out.println("List is empty");
      return -1;
    } else {
      return tail.data;
    }
  }


  static int search(int val){
     Node temp=head;

     int idx=0;
     while (temp!=null) {
       if(temp.data==val) return idx; 
       temp=temp.next;
       idx++;
     }

     return -1;
  }
 
  static void display(Node node) {
    if(node==null) return;

    display(node.next);
    System.out.print(node.data+ " ");
  }




}