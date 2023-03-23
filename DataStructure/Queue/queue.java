package Queue;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Queue<V> {

	class Node {
		V data;
		Node next;

		Node(V val) {
			this.data = val;
			this.next = null;
		}
	}

	private Node front;
	private Node rear;
	private int size;

	// Constructor calling of class queue
	public Queue() {
		front = null;
		rear = null;
		size = 0;
	}

	// Enqueue operation in queue
	public void push(V data) {
		if (front == null) {
			Node newnode = new Node(data);
			front = rear = newnode;
			size++;
			return;
		} else {
			Node newnode = new Node(data);
			rear.next = newnode;
			rear = rear.next;
			size++;

		}

	}

	// dequeue operation in queue
	public void pop() {
		if (front == null) {
			return;
		}
		front = front.next;
		size--;

	}

	// get the front Value from queue
	public V Front() {
		if (front == null) {
			return null;
		}
		return front.data;
	}

	// get the size of queue
	public int Size() {
		if (front == null) {
			return 0;
		} else {
			return size;
		}

	}

	// check queue is empty or not
	public boolean isempty() {
		if (front == null) {
			return true;
		} else {
			return false;
		}

	}

	void display() {

		// check the queue is empty or not
		if (front == null) {
			System.out.println("-1");
			return;
		} else {

			Node temp = front;
			while (temp != null) {

				System.out.print(temp.data + "  ");
				temp = temp.next;

			}

		}

	}

	public static void main(String[] args) {
		try (Scanner input = new Scanner(System.in)) {
			Queue<String> queue = new Queue<String>();
			int choices;
			do {
				System.out.println();
				System.out.println("Operations in Queue");
				System.out.println();
				System.out.println("  1. Push in Queue ");
				System.out.println("  2. Pop in Queue ");
				System.out.println("  3. Front of Queue ");
				System.out.println("  4. Size of Queue");
				System.out.println("  5. Queue is Empty or not");
				System.out.println("  6. Display");
				System.out.println("  7. Exit ");
				System.out.println();
				System.out.println("Enter Your Choice");
				System.out.println();
				choices = input.nextInt();
				System.out.println();
				switch (choices) {
					case 1:
						System.out.println("Enter the data");
						String data = input.next();
						queue.push(data);
						break;
					case 2:

						if (queue.isempty()) {
							System.out.println("Queue is empty");
						} else {
							String front1 = queue.Front();
							queue.pop();

							System.out.println("Deleted data from queue is - " + front1);
						}
						break;

					case 3:

						if (queue.isempty()) {
							System.out.println("Queue is empty");
						} else {
							String front = queue.Front();
							System.out.println("Front of queue is - " + front);

						}
						break;

					case 4:
						int size = queue.Size();
						System.out.println("Size of queue - " + size);
						break;

					case 5:
						if (queue.isempty()) {
							System.out.println("Queue is empty");

						} else {
							System.out.println("Queue is not empty");
						}
						break;

					case 6:
						if (queue.isempty()) {
							System.out.println("Queue is empty");
							break;
						}
						System.out.println("Values in Queue is -");
						queue.display();
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
