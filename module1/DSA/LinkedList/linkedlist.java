import java.util.InputMismatchException;
import java.util.Scanner;

public class LinkedList {
	// Creating head for linkedlist
	LinkedListNode head;
	// Initialize size of linkedlist 
	int size ;
	
	
	// creating the class for Linkedlist
	class LinkedListNode
	{
		int data,size;
		LinkedListNode next;
		
		
		// creating constructor
		public LinkedListNode(int value)
		{
			// assigning value to a Linkedlist node data
			data = value;
			next = null;
		}
	}
	
	
	// Creating Method to insert the data at tail in linkedlist
	void insertAtTail(int data)
	{
		// Initialize and assign new node of linkedlist data type
		LinkedListNode newnode = new LinkedListNode(data);
		
		// if head is null 
		if(head == null)
		{
			// making new node as a head of linkedlist
			newnode.next = head;
			head = newnode;
			size = size +1;
			return;
		}
		// Declaring "temp" data member with head
		LinkedListNode temp = head;
		
		// while temp data member next is not null
		while(temp.next!=null)
		{
			temp = temp.next;
		}
		
		// assigning a new node to temp.next
		temp.next = newnode;
		size = size+1;
		
	}
	
	
	// Creating Method to insert at head
	void insertAtHead(int data)
	{
		// Initialize and declare a new node
		LinkedListNode newnode = new LinkedListNode(data);
		// increasing the size of linkedlist
		size = size+1;
		// pointing the new node.next to head
		newnode.next = head;
		// newnode is now a head
		head = newnode;
		
	}
	
	// Creating method to delete the element from the linkedlist from the head
	void deleteAtHead()
	{
		// pointing head to head.next
		head = head.next;
		
		// decreasing the size of linkedlist
		size -=1;
		
	}
	
	// Creating the method to delete the element from the tail
	void deleteAtTail()
	{
		// if head is null
		if(head==null)
		{
			// simply return
			return;
		}
		// if head.next is null
		if(head.next==null)
		{
			// heading head to head.next
			head = head.next;
			// decreasing the size of linkedlist
			size -=1;
			return;
		}
		// creating a data member "temp" and assigning head
		LinkedListNode temp = head;
		
		// while temo.next.next is not null
		while(temp.next.next!=null)
		{
			temp = temp.next;
		}
		// deleting last element 
		temp.next = temp.next.next;
		// decreasing the size of linkedlist
		size -=1;
		
		
	}
	

	
	// Creating method to display linkedlist data
	void display()
	{
		// declaring data member "temp" with head 
		LinkedListNode temp = head;
		System.out.print("Linkedlist : ");
		// while temp is not null
		while(temp!=null)
		{
			// printing the data of linkedlist
			System.out.print(temp.data + " ");
			temp = temp.next;
		}
		System.out.println(" ");
	}
	
	
	// Creating the method to update the data in a linkedlist
	void update(int index,int value)
	{
		// checking if the entered index is exist or not
		if(index>size-1)
		{
			System.out.println("Enter a valid index " );
			return;
		}
		// if only one data in linkedlist then simply update it's value
		if(index==0)
		{
			head.data = value;
		}
		// Initializing counter to count the no. of node traverse
		int counter =0;
		LinkedListNode temp = head;
		// while temp is not null and counter is not equal to user entered indexed
		while(temp!=null && (counter!=index))
		{
			temp = temp.next;
			counter+=1;
		}
		// updating the value at a correct indexed
		temp.data = value;
	}
	
	
	// creating a sort method to sort the data by applying the selection sorrt
	void sort()
	{
		LinkedListNode temp1 = head;
		// looping through the 0 to size-1 
		for(int i=0;i<size-1;i++)
		{
			// if i is not equal to zero
			if(i!=0)
			{
				// traversing in linkedlist or moving forward
				temp1 = temp1.next;
			}
			// Initializing 2nd temp2 to compare value with temp1
			LinkedListNode temp2 = temp1;
			// looping from i to size
			for(int j=i;j<size;j++)
			{
				// checking if temp2 next is not null
				if(temp2.next!=null)
				{
					// moving forward
					temp2 = temp2.next;
				}
				
				// if temp1 data is bigger than temp2 data then swap the data
				if(temp1.data>temp2.data)
				{
					int temp = temp2.data;
					temp2.data = temp1.data;
					temp1.data = temp;
				}
			}
		}
	}
	
	// Creating search method to search the element and show the index at which the data is present 
	void search(int val )
	{
		LinkedListNode temp = head;
		int counter = 0;
		while(temp!=null)
		{
			// when we found user input value in a linkedlist then simply print the index at which data is present
			if(temp.data==val)
			{
				System.out.println("Element present at " + counter + " Index");
			}
			temp = temp.next;
			// increasing the counter value by one
			counter = counter+1;
		}
	}
	
	
	public static void main(String[] args) {
		
		
		// creating object of linkedlist class
		LinkedList link= new LinkedList();
		
		// flag to stop while loop
		boolean flag = true;
		// while flag is true
		while(flag)
		{
			System.out.println("Choose an option from the Dashboard given below:\n");
			System.out.println("1. Insert an element at head ");
            System.out.println("2. Insert an element at tail ");
            System.out.println("3. Delete an element from the head ");
            System.out.println("4. Delete an element from the tail ");
            System.out.println("5. Display");
            System.out.println("6. Sort");
            System.out.println("7. Update");
            System.out.println("8. Search");
            System.out.println("9. EXIT");
            System.out.println();
            
            // creating object of Scanner class
            Scanner sc = new Scanner(System.in);
            // taking the input from user and store in a ch data member
            int ch = sc.nextInt();
            // checking for invalid options
            if (ch < 1 || ch > 9) {
                System.out.println("Kindly enter the correct option.");
            } else {
            	// Taking the choice from the user and performing the corresponding operation
            	try {
            		switch(ch)
            		{
            		case 1: 
            			System.out.println("Enter the value to be inserted");
                        int input_value = sc.nextInt();
                        link.insertAtHead(input_value);
                        link.display();
                        break;
                        
            		case 2:
            			System.out.println("Enter the value to be inserted");
                        int input_value1 = sc.nextInt();
                        link.insertAtHead(input_value1);
                        link.display();
            			break;
            		
            		case 3:
            			link.deleteAtHead();
            			link.display();
            			break;
            			
            		case 4:
            			link.deleteAtHead();
            			link.display();
            			break;
            			
            		case 5:
            			link.display();
            			break;
            		case 6:
            			link.sort();
            			link.display();
            			break;
            		case 7:
            			System.out.println("Enter the index of current element to be updated");
                        int update_index = sc.nextInt();
                        System.out.println("Enter the value of new element to be updated");
                        int update_value = sc.nextInt();
                        
            			link.update(update_index, update_value);
            			link.display();
            			break;
            			
            		case 8:
            			System.out.println("Enter the value to be search in linkedlist");
            			int search_value = sc.nextInt();
            			link.search(search_value);
            			break;
            			
            		case 9:
            			flag = false;
            			break;
            			
            		}
            		
            		if(flag==false)
            		{
            			System.out.println("Exiting program");
            		}
            	}
            	catch(InputMismatchException ex)
            	{
            		System.out.println(ex.getMessage());
            	}
            	
            }
		}
				
		
		
	}

}
