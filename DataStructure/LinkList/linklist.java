package LinkList;

import java.util.InputMismatchException;
import java.util.Scanner;

public class linklist {
	// Create Node
	class Node {
		int data;
		Node next;

		Node(int val) {
			this.data = val;
			this.next = null;
		}

	}

	private Node head = null;
	private Node tail = null;

	// Insertion in List
	void insertiontolast(int data) {
		if (head == null) {
			Node temp = new Node(data);
			head = temp;
			tail = temp;
			return;
		} else {

			Node temp = new Node(data);
			tail.next = temp;
			tail = tail.next;

		}

	}

	// insertion from head

	void insertiontohead(int data) {
		if (head == null) {
			Node temp = new Node(data);
			head = temp;
			tail = temp;
		} else {
			Node temp = new Node(data);
			temp.next = head;
			head = temp;

		}

	}

	// insertion from Any Position In Linklist

	void insertionatanypos(int data, int pos) {

		if (pos < 1) {
			System.out.println("Invalid Postion");
			return;
		}
		if (pos == 1) {
			insertiontohead(data);
			return;
		} else {
			int count = 1;
			Node temp = head;
			while (count < pos - 1 && temp != null) {
				temp = temp.next;
				count++;
			}
			Node newnode = new Node(data);
			if (count == pos - 1 && temp.next == null) {
				insertiontolast(data);
				return;

			} else if (temp == null && count <= pos) {
				System.out.println("Invalid Position");
				return;

			} else if (temp != null) {
				newnode.next = temp.next;
				temp.next = newnode;

			}

		}

	}

	// deletion from head

	int deletefromhead() {
		if (head == null) {
			return -1;

		}
		if (head.next == null) {
			int temp = head.data;
			head = tail = null;
			return temp;
		} else {
			Node temp = head;
			head = head.next;
			return temp.data;
		}
	}

	int deletefromlast() {
		if (head == null) {

			return -1;

		}
		if (head.next == null) {
			int temp = head.data;
			head = tail = null;
			return temp;
		}

		Node temp = head;
		Node temp2 = head.next;
		while (temp2.next != null) {
			temp = temp2;
			temp2 = temp2.next;
		}
		tail = temp;
		tail.next = null;
		return temp2.data;
	}

	// deletion from Any Position

	int deletefromanypos(int pos) {
		if (isempty()) {
			return -1;
		}
		if (pos < 1) {
			return -1;
		}
		if (pos == 1) {
			return deletefromhead();
		} else {
			int count = 1;
			Node temp = head;
			Node temp1 = null;
			while (count < pos && temp != null) {
				temp1 = temp;
				temp = temp.next;
				count++;
			}
			if (temp == null && count <= pos) {
				return -1;
			}

			temp1.next = temp.next;
			return temp.data;

		}

	}

	// Sort List
	void sort() {
		if (head == null) {
			System.out.println("-1");
			return;
		}

		head = mergesort(head);

	}

	// Sort List using Mergesort
	private Node mergesort(Node head) {
		if (head.next == null) {
			return head;
		}

		Node mid = midelement(head);
		Node temp2 = mid.next;
		mid.next = null;

		Node first = mergesort(head);
		Node second = mergesort(temp2);

		Node temp = merge(first, second);

		return temp;
	}

	// Find the middle element in list
	private Node midelement(Node temp) {
		if (temp.next == null) {
			return temp;
		}
		Node slow = temp;
		Node fast = temp.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next;
			if (fast.next != null) {
				fast = fast.next;
			}
		}

		return slow;

	}

	// Merge the list
	private Node merge(Node firsthead, Node sechead) {

		Node ans = new Node(-1);
		Node temp = ans;
		while (firsthead != null && sechead != null) {
			if (firsthead.data < sechead.data) {
				temp.next = firsthead;
				firsthead = firsthead.next;
			} else {
				temp.next = sechead;
				sechead = sechead.next;
			}
			temp = temp.next;
		}

		while (firsthead != null) {
			temp.next = firsthead;
			firsthead = firsthead.next;
			temp = temp.next;

		}
		while (sechead != null) {
			temp.next = sechead;
			sechead = sechead.next;
			temp = temp.next;

		}
		return ans.next;

	}

	// Update the data at particular position

	void updateAtpos(int data, int pos) {
		if (isempty()) {
			System.out.println("List is empty");
			return;
		}
		if (pos < 1) {
			System.out.println("invalid position");
			return;
		}
		int count = 1;
		Node temp = head;
		while (count < pos && temp != null) {
			temp = temp.next;
			count++;
		}
		if (temp != null) {
			temp.data = data;
		} else {
			System.out.println("invalid position");
		}

	}

	// display the elements

	void print() {

		if (head == null) {
			System.out.println("-1");
			return;
		}

		System.out.print(head.data);
		Node temp = head.next;
		while (temp != null) {
			System.out.print("->" + temp.data);
			temp = temp.next;

		}

	}

	// Search a key in Link List

	boolean search(int key) {
		if (head == null) {
			return false;

		}
		Node temp = head;
		while (temp != null) {
			if (temp.data == key) {
				return true;
			}
			temp = temp.next;
		}

		return false;

	}

	boolean isempty() {
		if (head == null) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		try (Scanner input = new Scanner(System.in)) {
			int choice;

			linklist list = new linklist();
			do {
				System.out.println();
				System.out.println("Operations in Link List");
				System.out.println();
				System.out.println("  1.Insert From First");
				System.out.println("  2.Insert From Last");
				System.out.println("  3.Insert From Any Postion");
				System.out.println("  4.Display");
				System.out.println("  5.Delete From First");
				System.out.println("  6.Delete From Last ");
				System.out.println("  7.Delete From Any Position");
				System.out.println("  8.Sort the List");
				System.out.println("  9.Update List Any position");
				System.out.println("  10.Search the element in List");
				System.out.println("  11.Exit");
				System.out.println();
				System.out.println("Enter Your Choice");
				System.out.println();
				choice = input.nextInt();
				System.out.println();
				switch (choice) {

					case 1:
						System.out.println("Enter the data");
						int data = input.nextInt();
						list.insertiontohead(data);
						break;
					case 2:
						System.out.println("Enter the data");
						int dt = input.nextInt();
						list.insertiontolast(dt);
						break;
					case 3:
						System.out.println("Enter the data ");
						int dt1 = input.nextInt();
						System.out.println("Enter the Position");
						int pos = input.nextInt();
						list.insertionatanypos(dt1, pos);
						break;
					case 4:
						if (list.isempty()) {
							System.out.println("List is empty");
							break;
						}
						list.print();
						System.out.println();
						break;

					case 5:
						if (list.isempty()) {
							System.out.println("List is Empty");
							break;
						}
						int d = list.deletefromhead();
						System.out.println("deleted data is " + "- " + d);
						break;

					case 6:
						int d1 = list.deletefromlast();
						if (d1 == -1) {
							System.out.println("List is empty");
							break;
						}
						System.out.println("deleted data is " + "- " + d1);
						break;

					case 7:
						if (list.isempty()) {
							System.out.println("List is empty");
							break;
						}
						System.out.println("Enter the Postion");
						int posi = input.nextInt();
						int d2 = list.deletefromanypos(posi);
						if (d2 == -1) {
							System.out.println("invalid position");
						} else {
							System.out.println("deleted data is " + "- " + d2);
						}
						break;

					case 8:
						if (list.isempty()) {
							System.out.println("List is empty");
							break;
						}
						list.sort();
						System.out.println("Sorted List is -");
						list.print();
						break;

					case 9:
						if (list.isempty()) {
							System.out.println("List is empty");
							break;
						}
						System.out.println("Enter the upadted data");
						int updata = input.nextInt();
						System.out.println("Enter Position");
						int position = input.nextInt();
						list.updateAtpos(updata, position);

						break;

					case 10:
						if (list.isempty()) {
							System.out.println("List is empty");
							break;
						}
						System.out.println("Enter the key");
						int key = input.nextInt();
						if (list.search(key)) {
							System.out.println("Key is found");
						} else {
							System.out.println("Key is not found");
						}
						break;

					case 11:
						System.out.println("Thank you");
						break;

					default:
						System.out.println("Invalid Choice");
						break;

				}

			} while (choice != 11);
		} catch (InputMismatchException exception) {
			System.out.println(exception);
		}

	}

}
