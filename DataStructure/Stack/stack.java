package Stack;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Stack<V> {
	class Node {
		V data;
		Node next;

		Node(V val) {
			this.data = val;
			this.next = null;
		}
	}

	private Node top;
	private int size;

	Stack() {
		top = null;
		size = 0;
	}

	// insertion in Stack From Top

	void push(V data) {
		if (top == null) {
			Node newnode = new Node(data);
			top = newnode;
			size++;
			return;
		} else {
			Node newnode = new Node(data);
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

	V Top() {
		if (top == null) {
			return null;
		}
		return top.data;
	}

	// Size of Stack
	int Size() {
		if (top == null) {
			return 0;
		} else {
			return size;
		}

	}

	// Check Stack is empty or not
	boolean isempty() {
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
			Node temp = top;
			while (temp != null) {
				System.out.print(temp.data + "  ");
				temp = temp.next;
			}
		}
	}

	public static void main(String[] args) {
		try (Scanner input = new Scanner(System.in)) {
			Stack<Integer> stack = new Stack<Integer>();
			int choices;
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
				choices = input.nextInt();
				System.out.println();
				switch (choices) {
					case 1:
						System.out.println("Enter the data");
						int d = input.nextInt();
						stack.push(d);
						break;

					case 2:
						if (stack.isempty()) {
							System.out.println("Stack is empty");
						} else {
							int top1 = stack.Top();
							stack.pop();
							System.out.println("Deleted element of Stack is - " + top1);
						}
						break;

					case 3:
						if (stack.isempty()) {
							System.out.println("Stack is empty");
						} else {
							int top = stack.Top();
							System.out.println("Top element of Stack is - " + top);
						}
						break;

					case 4:
						int size = stack.Size();
						System.out.println("Size of Stack is - " + size);
						break;

					case 5:

						if (stack.isempty()) {
							System.out.println("Stack is Empty");
						} else {
							System.out.println("Stack is not Empty");
						}
						break;

					case 6:
						if (stack.isempty()) {
							System.out.println("Stack is empty");
							break;
						}
						System.out.println("Values in Stack is - ");
						stack.display();
						System.out.println();
						break;

					case 7:
						System.out.println("Thank You");
						break;

					default:
						System.out.println("Invalid Choice");
						break;
				}
			} while (choices != 7);
		} catch (InputMismatchException exception) {
			System.out.println(exception);
		}

	}
}
