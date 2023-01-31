import java.util.Scanner;
import java.util.InputMismatchException;
class StackByArray {
	
  int CAPACITY = 1000;
  int stack[] = new int[CAPACITY];
  int top = -1;

  void push(int data) //to insert elements
  {
    if (top == CAPACITY - 1) {
      System.out.println("Stack is full");
      return;
    }
    stack[++top] = data;
  }

  void showAll() //to display all elements of stack
  {
    for (int index = top; index >= 0; index--) {
      System.out.print(stack[index] + "  ");
    }
    System.out.println();
  }

  boolean search(int data) // to check an element is present or not
  {
    for (int index = top; index >= 0; index--) {
      if (stack[index] == data) return true;
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
    StackByArray top = new StackByArray();
    int input1, input2;
    
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
        int condition = sc.nextInt();
        switch (condition) {
        case 1:
          System.out.println("Enter a number ");
          input1 = sc.nextInt();
          top.push(input1);
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
          input1 = sc.nextInt();
          System.out.println("Element present : " + top.search(input1));
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