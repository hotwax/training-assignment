package LinkedList;
import java.util.InputMismatchException;
import java.util.Scanner;

//LinkedList class 
public class LinkedList {
	// Create Node
	class Node {
		int data;
		Node next;
		//constructor
		Node(int val) {
			this.data = val;
			this.next = null;
		}
	}

	private Node head = null;//head of Linked List
	private Node tail = null;//tail of Linked List

	// Insertion in List
	void insertionToLast(int data) {
		Node newnode = new Node(data);
		if (head == null) {
			head = newnode;
			tail = newnode;
			return;
		} else {
			tail.next = newnode;
			tail = tail.next;
		}
	}

	// insertion from head
	void insertionToHead(int data) {
		Node newnode = new Node(data);
		if (head == null) {
			head = newnode;
			tail = newnode;
		} else {
			newnode.next = head;
			head = newnode;
		}
	}

	// insertion from Any Position In Linklist
	void insertionAtAnyPos(int data, int position) {
		if (position < 1) {
			System.out.println("Invalid Postion");
			return;
		}
		if (position == 1) {
			insertionToHead(data);
			return;
		} else {
			int count = 1;
			Node tempListRef = head;
			while (count < position - 1 && tempListRef != null) {
				tempListRef = tempListRef.next;
				count++;
			}
			Node newnode = new Node(data);
			if (count == position - 1 && tempListRef.next == null) {
				insertionToLast(data);
				return;
			} else if (tempListRef == null && count <= position) {
				System.out.println("Invalid Position");
				return;
			} else if (tempListRef != null) {
				newnode.next = tempListRef.next;
				tempListRef.next = newnode;
			}
		}
	}

	// deletion from head
	int deleteFromHead() {
		if (head == null) {
			return -1;
		}
		if (head.next == null) {
			int data = head.data;
			head = tail = null;
			return data;
		} else {
			Node tempHeadRef = head;
			head = head.next;
			return tempHeadRef.data;
		}
	}

	//deletion from last
	int deleteFromLast() {
		if (head == null) {
			return -1;
		}
		if (head.next == null) {
			int data = head.data;
			head = tail = null;
			return data;
		}
		Node tempHeadRef = head;
		Node tempHeadNextRef = head.next;
		while (tempHeadNextRef.next != null) {
			tempHeadRef = tempHeadNextRef;
			tempHeadNextRef = tempHeadNextRef.next;
		}
		tail = tempHeadRef;
		tail.next = null;
		return tempHeadNextRef.data;
	}

	// deletion from Any Position
	int deleteFromAnyPos(int position) {
		if (listIsEmpty()) {
			return -1;
		}
		if (position < 1) {
			return -1;
		}
		if (position == 1) {
			return deleteFromHead();
		} else {
			int count = 1;
			Node tempHeadRef = head;
			Node tempNodeRef = null;
			while (count < position && tempHeadRef != null) {
				tempNodeRef = tempHeadRef;
				tempHeadRef = tempHeadRef.next;
				count++;
			}
			if (tempHeadRef == null && count <= position) {
				return -1;
			}
			tempNodeRef.next = tempHeadRef.next;
			return tempHeadRef.data;
		}
	}

	// Sort List
	void sort() {
		if (head == null) {
			System.out.println("-1");
			return;
		}
		head = mergeSort(head);
	}

	// Sort List using Mergesort
	private Node mergeSort(Node head) {
		if (head.next == null) {
			return head;
		}
		Node mid = middleElement(head);
		Node tempMidRef = mid.next;
		mid.next = null;
		Node firstList = mergeSort(head);
		Node secondList = mergeSort(tempMidRef);
		Node sortedListHead = mergeTwoList(firstList, secondList);
		return sortedListHead;
	}

	// Find the middle element in list
	private Node middleElement(Node head) {
		if (head.next == null) {
			return head;
		}
		Node slow = head;
		Node fast = head.next;
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
	private Node mergeTwoList(Node firsthead, Node sechead) {
		Node newList = new Node(-1);
		Node tempList = newList;
		while (firsthead != null && sechead != null) {
			if (firsthead.data < sechead.data) {
				tempList.next = firsthead;
				firsthead = firsthead.next;
			} else {
				tempList.next = sechead;
				sechead = sechead.next;
			}
			tempList = tempList.next;
		}
		while (firsthead != null) {
			tempList.next = firsthead;
			firsthead = firsthead.next;
			tempList = tempList.next;
		}
		while (sechead != null) {
			tempList.next = sechead;
			sechead = sechead.next;
			tempList = tempList.next;
		}
		return newList.next;
	}

	// Update the data at particular position
	void updateAtPos(int data, int position) {
		if (listIsEmpty()) {
			System.out.println("List is empty");
			return;
		}
		if (position < 1) {
			System.out.println("invalid position");
			return;
		}
		int count = 1;
		Node tempHeadRef = head;
		while (count < position && tempHeadRef != null) {
			tempHeadRef = tempHeadRef.next;
			count++;
		}
		if (tempHeadRef != null) {
			tempHeadRef.data = data;
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
		Node tempHeadRef = head.next;
		while (tempHeadRef != null) {
			System.out.print("->" + tempHeadRef.data);
			tempHeadRef = tempHeadRef.next;
		}
	}

	// Search a key in Link List
	boolean searchDataInList(int data) {
		if (head == null) {
			return false;
		}
		Node tempHeadRef = head;
		while (tempHeadRef != null) {
			if (tempHeadRef.data == data) {
				return true;
			}
			tempHeadRef = tempHeadRef.next;
		}
		return false;
	}

	//check list is empty or not
	boolean listIsEmpty() {
		if (head == null) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		try (Scanner input = new Scanner(System.in)) {
			String choice;
			LinkedList list = new LinkedList();
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
				choice = input.next();
				System.out.println();
				switch (choice) {

					case "1":
						System.out.println("Enter the data");
						int data = input.nextInt();
						list.insertionToHead(data);
						break;

					case "2":
						System.out.println("Enter the data");
						int data1 = input.nextInt();
						list.insertionToLast(data1);
						break;

					case "3":
						System.out.println("Enter the data ");
						int dt1 = input.nextInt();
						System.out.println("Enter the Position");
						int pos = input.nextInt();
						list.insertionAtAnyPos(dt1, pos);
						break;

					case "4":
						if (list.listIsEmpty()) {
							System.out.println("List is empty");
							break;
						}
						list.print();
						System.out.println();
						break;

					case "5":
						if (list.listIsEmpty()) {
							System.out.println("List is Empty");
							break;
						}
						int deletedData = list.deleteFromHead();
						System.out.println("deleted data is " + "- " + deletedData);
						break;

					case "6":
						int deletedData1 = list.deleteFromLast();
						if (deletedData1 == -1) {
							System.out.println("List is empty");
							break;
						}
						System.out.println("deleted data is " + "- " + deletedData1);
						break;

					case "7":
						if (list.listIsEmpty()) {
							System.out.println("List is empty");
							break;
						}
						System.out.println("Enter the Postion");
						int position = input.nextInt();
						int deletedDataAtPos = list.deleteFromAnyPos(position);
						if (deletedDataAtPos == -1) {
							System.out.println("invalid position");
						} else {
							System.out.println("deleted data is " + "- " + deletedDataAtPos);
						}
						break;

					case "8":
						if (list.listIsEmpty()) {
							System.out.println("List is empty");
							break;
						}
						list.sort();
						System.out.println("Sorted List is -");
						list.print();
						break;

					case "9":
						if (list.listIsEmpty()) {
							System.out.println("List is empty");
							break;
						}
						System.out.println("Enter the upadted data");
						int updatedData = input.nextInt();
						System.out.println("Enter Position");
						int positionForUpdate = input.nextInt();
						list.updateAtPos(updatedData, positionForUpdate);
						break;

					case "10":
						if (list.listIsEmpty()) {
							System.out.println("List is empty");
							break;
						}
						System.out.println("Enter the key");
						int key = input.nextInt();
						if (list.searchDataInList(key)) {
							System.out.println("Key is found");
						} else {
							System.out.println("Key is not found");
						}
						break;

					case "11":
						System.out.println("Thank you");
						break;

					default:
						System.out.println("Invalid Choice");
						break;
				}
			} while (!choice.equals("11"));
		} catch (InputMismatchException exception) {
			System.out.println(exception);
		}
		catch(Exception exception){
			System.out.println(exception);
		}
	}
}