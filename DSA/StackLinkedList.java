import java.util.Scanner;
import java.util.InputMismatchException;
class stack {
  int data;
  stack next;

  stack top = null;

  void showAll() //to display all elements of stack
  {
    stack temp = top;
    while (temp != null) {
      System.out.print(temp.data + "  ");
      temp = temp.next;
    }
    System.out.println();
  }

  boolean search(int x) // To search an element
  {
    stack temp = top;
    while (temp != null) {
      if (temp.data == x) return true;
      temp = temp.next;
    }
    return false;
  }

  stack push(int x) // To insert an element
  {
    stack temp = new stack();
    temp.data = x;
    temp.next = top;
    top = temp;
    return temp;
  }

  int pop() // To remove an element
  {
    if (isEmpty()) {
      System.out.println("stack is empty");
      return -1;
    }
    int x = top.data;
    stack temp = top;
    top = top.next;
    temp = null;
    return x;
  }

  int peek() // To fetch top most element
  {
    if (isEmpty()) {
      System.out.println("stack is empty");
      return -1;
    }
    return top.data;
  }

  boolean isEmpty() // To check if stack is empty or not
  {
    if (top == null) return true;
    return false;
  }
}
class Demo {
  public static void main(String[] args) {
    stack top = new stack();
    int a, b;

    
      while (true) {
        System.out.println("===========================");
        System.out.println("*****Enter your choice*****");
        System.out.println("1.Push");
        System.out.println("2.Pop");
        System.out.println("3.Peek");
        System.out.println("4.Search");
        System.out.println("5.isEmpty");
        System.out.println("6.ShowAll");
        System.out.println("7.Exit");
        System.out.println("===========================");
		try{
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        switch (x) {
        case 1:
          System.out.println("Enter a number ");
          a = sc.nextInt();
          top.push(a);
          System.out.println("Done ");
          break;

        case 2:
          System.out.println("Element " + top.pop());
          break;

        case 3:
          System.out.println("Element " + top.peek());
          break;

        case 4:
          System.out.println("Enter a number ");
          a = sc.nextInt();
          System.out.println("Element present : " + top.search(a));
          break;

        case 5:
          System.out.println("Stack is empty : " + top.isEmpty());
          break;

        case 6:
          top.showAll();
          break;

        case 7:
          System.out.println("Thank you");
          System.exit(0);
          break;

        default:
          System.out.println("Invalid");
          break;
        }
      }
	  catch (InputMismatchException e) {
      System.out.println("Enter valid option");
    }
    } 
  }
}