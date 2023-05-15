import java.util.Scanner;
import java.util.InputMismatchException;
class StackByList {
	
  int data;
  StackByList next;

  StackByList top = null;

  void showAll() //to display all elements of stack
  {
    StackByList temp = top;
    while (temp != null) {
      System.out.print(temp.data + "  ");
      temp = temp.next;
    }
    System.out.println();
  }

  boolean search(int data) // To search an element
  {
    StackByList temp = top;
    while (temp != null) {
      if (temp.data == data) return true;
      temp = temp.next;
    }
    return false;
  }

  StackByList push(int data) // To insert an element
  {
    StackByList temp = new StackByList();
    temp.data = data;
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
    int data = top.data;
    StackByList temp = top;
    top = top.next;
    temp = null;
    return data;
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
class Main {
  public static void main(String[] args) {
    StackByList top = new StackByList();
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
		try{
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