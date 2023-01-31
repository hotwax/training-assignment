import java.util.Scanner;

// Linked List class
public class LinkedList {

	Node head; // Head of list

	// Node class for creating nodes of linked list.
	static class Node {

		int data;
		Node next;

		// Constructor
		Node(int d) {
			data = d;
			next = null;
		}
	}

	// Method to insert a new node
	public static LinkedList insert(LinkedList list, int data) {

		// Create a new node with given data
		Node new_node = new Node(data);
		new_node.next = null;

		// If the Linked List is empty, then make the new node as head
		if (list.head == null) {
			list.head = new_node;
		} else {
			// Else traverse till the last node and insert the new_node there
			Node last = list.head;
			while (last.next != null) {
				last = last.next;
			}
			// Insert the new_node at last node
			last.next = new_node;
		}
		return list;
	}

	// Method to delete a node in the linkedlist.
	public static LinkedList delete(LinkedList list, int data) {
		// Store head node
		Node currNode = list.head, prev = null;

		// If head node itself holds the key to be deleted
		if (currNode != null && currNode.data == data) {
			list.head = currNode.next;
			System.out.println(data + " found and deleted");
			return list;
		}

		// search for the data to be deleted by traversing the list
		while (currNode != null && currNode.data != data) {
			// If currNode does not hold key
			// continue to next node
			prev = currNode;
			currNode = currNode.next;
		}

		// if we found the data in the list then delete it
		if (currNode != null) {
			prev.next = currNode.next;
			System.out.println(data + " found and deleted");
		}

		// if the data is not present in the list
		if (currNode == null) {
			System.out.println(data + " not found");
		}

		return list;
	}

	// update a value in the linkedlist.
	public static LinkedList update(LinkedList list, int data, int newData) {

		Node currNode = list.head, prev = null;

		// If head node itself holds the value to be updated
		if (currNode != null && currNode.data == data) {
			currNode.data = newData;
			System.out.println(data + " found and updated");
			return list;
		}

		// search for the data to be updated by traversing the list
		while (currNode != null && currNode.data != data) {
			prev = currNode;
			currNode = currNode.next;
		}

		// if we found the data in the list then update it
		if (currNode != null) {
			currNode.data = newData;
			System.out.println(data + " found and updated");
		}

		// if the data is not present in the list
		if (currNode == null) {
			System.out.println(data + " not found");
		}

		return list;
	}

	public static LinkedList sort(LinkedList list) {
		Node currNode = list.head;
		Node nextNode = list.head.next;
		int temp;
		while (currNode != null) {
			while (nextNode != null) {
				if (currNode.data > nextNode.data) {
					temp = currNode.data;
					currNode.data = nextNode.data;
					nextNode.data = temp;
				}
				nextNode = nextNode.next;
			}
			currNode = currNode.next;
			nextNode = currNode.next;
		}
		return list;
	}

	public static LinkedList insertAtIndex(LinkedList list, int index, int data) {
		Node currNode = list.head;
		Node prev = null;
		Node newNode = new Node(data);
		int count = 0;
		while (currNode != null) {
			if (count == index) {
				prev.next = newNode;
				newNode.next = currNode;
				break;
			}
			prev = currNode;
			currNode = currNode.next;
			count++;
		}
		return list;
	}

	// Method to print the LinkedList.
	public static void printList(LinkedList list) {
		Node currNode = list.head;
		System.out.print("LinkedList: ");

		// Traverse through the LinkedList
		while (currNode != null) {
			// Print the data at current node
			System.out.print(currNode.data + "->");
			// Go to next node
			currNode = currNode.next;
		}
		System.out.println("null");
	}

	// **************MAIN METHOD**************

	public static void main(String[] args) {
		// Create a LinkedList
		LinkedList list = new LinkedList();
		Boolean flag = true;
		while (flag) {
			System.out.println("Select an option from the menu below");
			System.out.println("1. Insert a new node in the list");
			System.out.println("2. Delete a node from the list");
			System.out.println("3. Update a node in the list");
			System.out.println("4. Sort the list");
			System.out.println("5. Insert new element on index");
			System.out.println("6. Exit");
			Scanner input = new Scanner(System.in);
			int choice = input.nextInt();
			if (choice < 1 || choice > 5) {
				System.out.println("Invalid choice");
			} else {
				switch (choice) {
					case 1:
						System.out.println("Enter the value to be inserted");
						int i_value = input.nextInt();
						list = insert(list, i_value);
						break;
					case 2:
						System.out.println("Enter the value to be deleted");
						int d_value = input.nextInt();
						list = delete(list, d_value);
						break;
					case 3:
						System.out.println("Enter the the node to be updated and the new value");
						int s_value = input.nextInt();
						int n_value = input.nextInt();
						list = update(list, s_value, n_value);
						break;
					case 4:
						System.out.println("Sorting the list");
						list = sort(list);
						break;
					case 5:
						System.out.println("Enter the index and the value to be inserted");
						int index = input.nextInt();
						int value = input.nextInt();
						list = insertAtIndex(list, index, value);
						break;
					case 6:
						flag = false;
						break;
				}
				printList(list);
				if (flag == false) {
					System.out.println("Exiting the program");
				}
			}
		}

	}
}
