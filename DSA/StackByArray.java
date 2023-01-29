import java.util.Scanner;
import java.util.InputMismatchException;
class StacK {
  int CAPACITY = 1000;
  int stack[] = new int[CAPACITY];
  static int top = -1;

  void push(int x) //to insert elements
  {
    if (top == CAPACITY - 1) {
      System.out.println("Stack is full");
      return;
    }
    stack[++top] = x;
  }

  void showAll() //to display all elements of stack
  {
    for (int i = top; i >= 0; i--) {
      System.out.print(stack[i] + "  ");
    }
    System.out.println();
  }

  boolean search(int x) // to check an element is present or not
  {
    for (int i = top; i >= 0; i--) {
      if (stack[i] == x) return true;
    }
    return false;
  }

  int pop() // to remove element
  {
    if (isEmpty()) {
      System.out.println("Stack is empty");
      return -1;
    }
    return stack[top--];
  }

  int peek() // to see last inserted element
  {
    if (isEmpty()) {
      System.out.println("Stack is empty");
      return -1;
    }
    return stack[top];
  }

  boolean isEmpty() // stack is empty or not
  {
    if (top == -1) {
      return true;
    }
    return false;
  }
  
}
class Demo {
  public static void main(String[] args) {
    StacK top = new StacK();
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
		try {
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