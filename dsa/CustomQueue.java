import java.util.Scanner;
import CustomLinkedList;

class Node {
  int data;
  Node next;
}

public class CustomQueue{

  static Node head, tail;
  static int size;

  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);

    while (true) {
      System.out.print("Your Queue: ");
      display(head);
      System.out.println();

      System.out.println("Enter 1 to add");
      System.out.println("Enter 2 to remove");
      System.out.println("Enter 3 to peek");
      System.out.println("Enter 4 to search");
      System.out.println("Enter 5 to end the program");

      int n = sc.nextInt();

      if (n == 1) {
        System.out.print("Enter the value: ");
        int val = sc.nextInt();
        addFirst(val);
        System.out.println("-------------------------------------");
      } else if (n == 2) {
        removeFirst();
        System.out.println("-------------------------------------");
      } else if (n == 3) {
        System.out.println(getFirst());
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



  static int getFirst() {
    if (size == 0) {
      System.out.println("List is empty");
      return -1;
    } else {
      return head.data;
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
    System.out.print(node.data+" ");
  }




}