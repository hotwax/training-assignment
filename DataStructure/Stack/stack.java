package Stack;
import java.util.InputMismatchException;
import java.util.Scanner;

//Stack class
public class Stack<V> {
	//Node class
	class Node {
		V data;
		Node next;

		// Node class constructor
		Node(V val) {
			this.data = val;
			this.next = null;
		}
	}

	private Node top;
	private int size;

	// Stack class constructor
	Stack() {
		top = null;
		size = 0;
	}

	// insertion in Stack From Top
	void push(V data) {
		Node newnode = new Node(data);
		if (top == null) {
			top = newnode;
			size++;
			return;
		} else {
			newnode.next = top;
			top = newnode;
			size++;
		}
	}

	// deletion in Stack From Top
	void pop() {
		if (top == null) {
			return;
		}
		top = top.next;
		size--;
	}

	// Top Element in Stack
	V topElement() {
		if (top == null) {
			return null;
		}
		return top.data;
	}

	// Size of Stack
	int stackSize() {
		if (top == null) {
			return 0;
		} else {
			return size;
		}
	}

	// Check Stack is empty or not
	boolean isEmpty() {
		if (top == null) {
			return true;
		} else {
			return false;
		}
	}

	// Display the elements in Stack
	void display() {
		if (top == null) {
			System.out.println("-1");
			return;
		} else {
			Node tempTopRef = top;
			while (tempTopRef != null) {
				System.out.print(tempTopRef.data + "  ");
				tempTopRef = tempTopRef.next;
			}
		}
	}

	public static void main(String[] args) {
		try (Scanner input = new Scanner(System.in)) {
			Stack<Integer> stack = new Stack<Integer>();
			String choices;
			do {
				System.out.println();
				System.out.println("Operations in Stack");
				System.out.println();
				System.out.println("  1. Push in Stack");
				System.out.println("  2. Pop in Stack");
				System.out.println("  3. Top of the Stack ");
				System.out.println("  4. Size of the Stack");
				System.out.println("  5. Check Stack is Empty or not");
				System.out.println("  6. Display");
				System.out.println("  7. Exit");
				System.out.println();
				System.out.println("Enter Your Choices");
				System.out.println();
				choices = input.next();
				System.out.println();
				switch (choices) {
					case "1":
						System.out.println("Enter the data");
						int data = input.nextInt();
						stack.push(data);
						break;

					case "2":
						if (stack.isEmpty()) {
							System.out.println("Stack is empty");
						} else {
							int prevTopElement = stack.topElement();
							stack.pop();
							System.out.println("Deleted element of Stack is - " + prevTopElement);
						}
						break;

					case "3":
						if (stack.isEmpty()) {
							System.out.println("Stack is empty");
						} else {
							int topElementOfStack = stack.topElement();
							System.out.println("Top element of Stack is - " + topElementOfStack);
						}
						break;

					case "4":
						int size = stack.stackSize();
						System.out.println("Size of Stack is - " + size);
						break;

					case "5":
						if (stack.isEmpty()) {
							System.out.println("Stack is Empty");
						} else {
							System.out.println("Stack is not Empty");
						}
						break;

					case "6":
						if (stack.isEmpty()) {
							System.out.println("Stack is empty");
							break;
						}
						System.out.println("Values in Stack is - ");
						stack.display();
						System.out.println();
						break;

					case "7":
						System.out.println("Thank You");
						break;

					default:
						System.out.println("Invalid Choice");
						break;
				}
			} while (!choices.equals("7"));
		} catch (InputMismatchException exception) {
			System.out.println(exception);
		} catch (Exception exception) {
			System.out.println(exception);
		}
	}
}