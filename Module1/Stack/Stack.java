// Importing Scanner class for taking input from user.
import java.util.Scanner;


/* 
Creating Node class For creating new node everytime.
This will acts as a block in which all data will be stored.
*/
class Node{
    int data;
    Node next;
    Node(int data){
        this.data = data;
        this.next = null; 
    }
}

/*
 This class will actually help us in creating stack.
 */
class CreatingStack{
    Node head;

    // By using this function we can add( push ) data into stack.
    public void push(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            return;
        }
        Node temp = head;
        head = newNode;
        head.next = temp;
    }
// Removing top most value from the list.
    public void pop(){
        if(head == null){
            System.out.println("stack is empty. ");
            return ;
        }
        head = head.next;
    }

// Updating the old value with new value.
    public void update(int old, int newData){
        Node temp = head;
        while(temp != null && temp.data != old){
            temp  = temp.next;
        }
        if(temp != null && temp.data == old){
            temp.data  = newData;
            System.out.println("updated successfully");
            return;
        }
        System.out.println("Unable to find "+old+" in stack.");
    }
// This function is used for displaying the stack.
    public void showData(){
        Node curr = head;
        while(curr != null){
            System.out.print(curr.data+ " --> ");
            curr = curr.next;
        }
        System.out.println();
    }

}


public class Stack {
    public static void main(String[] args) {
        CreatingStack obj = new CreatingStack();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter 1 for adding new value.");
        System.out.println("Enter 2 for updating a value.");
        System.out.println("Enter 3 for deleting a value.");
        System.out.println("Enter 4 for displaying a value.");
        System.out.println("Enter 5 to exit.");
        int option;
        while(true){
            System.out.println("Enter your choice.");
            option = input.nextInt();
            if(option==1){
                System.out.print("Enter a value: ");
                int value = input.nextInt();
                obj.push(value);
            }
            else if(option==2){
                System.out.print("Enter old value: ");
                int old = input.nextInt();
                System.out.print("Enter new value: ");
                int newData = input.nextInt();
                obj.update(old, newData);
            }
            else if(option == 3){
                obj.pop();
            }
            else if(option == 4){
                obj.showData();
            }

            else if(option== 5){
                break;
            }
            else{
                System.out.println("Please enter a valid option.");
            }

        }

       
        
    }
}
