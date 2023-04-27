package Queue;
import java.util.InputMismatchException;
import java.util.Scanner;

//Queue class
public class Queue<V> {

	//Node class
	class Node {
		V data;
		Node next;

		// constructor calling of Node class
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
		Node newnode = new Node(data);
		if (front == null) {
			front = rear = newnode;
			size++;
			return;
		} else {
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
	public V frontElement() {
		if (front == null) {
			return null;
		}
		return front.data;
	}

	// get the size of queue
	public int queueSize() {
		if (front == null) {
			return 0;
		} else {
			return size;
		}
	}

	// check queue is empty or not
	public boolean isEmpty() {
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
			Node tempHeadRef = front;
			while (tempHeadRef != null) {
				System.out.print(tempHeadRef.data + "  ");
				tempHeadRef = tempHeadRef.next;
			}
		}
	}

	public static void main(String[] args) {
		try (Scanner input = new Scanner(System.in)) {
			Queue<String> queue = new Queue<String>();
			String choices;
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
				choices = input.next();
				System.out.println();
				switch (choices) {
					case "1":
						System.out.println("Enter the data");
						String data = input.next();
						queue.push(data);
						break;

					case "2":
						if (queue.isEmpty()) {
							System.out.println("Queue is empty");
						} else {
							String front1 = queue.frontElement();
							queue.pop();
							System.out.println("Deleted data from queue is - " + front1);
						}
						break;

					case "3":
						if (queue.isEmpty()) {
							System.out.println("Queue is empty");
						} else {
							String front = queue.frontElement();
							System.out.println("Front of queue is - " + front);
						}
						break;

					case "4":
						int size = queue.queueSize();
						System.out.println("Size of queue - " + size);
						break;

					case "5":
						if (queue.isEmpty()) {
							System.out.println("Queue is empty");
						} else {
							System.out.println("Queue is not empty");
						}
						break;

					case "6":
						if (queue.isEmpty()) {
							System.out.println("Queue is empty");
							break;
						}
						System.out.println("Values in Queue is -");
						queue.display();
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
