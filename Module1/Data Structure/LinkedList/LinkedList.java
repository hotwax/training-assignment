import java.util.Scanner;

// Class used to create new node of linked list. 
class Node{
    int data;
    Node next;
    Node(int value){
        this.data = value;
        this.next = null;
    }
}

class Operation{
    Node head;
    /*
     * This function is used to insert data into linked list.
     * fuction accepts an argument data of integer type.
     */
    public void insertData(int data){
        Node newNode = new Node(data);

        if(head==null){
            head = newNode;
        }
        else {
            Node temp = head;
            while (temp.next != null){
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    /*
     * Method is used to delete an element from the linked list.
     * fuction accepts an argument data of integer type.
     */
    public void DeleteData(int data){

        if (head == null) {
            System.out.println("You have an empty linked list.");
            return;
        }
        if (head.data == data) {
            head = head.next;
            System.out.println(data+" deleted successfully: ");
            return;
        }
        Node prev = null;
        Node curr = head;
        while (curr != null && curr.data != data) {
            prev = curr;
            curr = curr.next;
        }
        if (curr != null) {
            prev.next = curr.next;
        }
        System.out.println(data+" deleted successfully: ");

    }

    // This function is used to display linked list data.
    public void showData(){
        Node temp = head;
        while(temp !=null){
            System.out.print(temp.data+ " --> ");
            temp = temp.next;
        }
        System.out.println();
    }

    /*
     * This function is used to update data in the linked list.
     * function accepts two arguments old value and new argument.
     */
    public boolean update(int old, int newValue){
        if(head==null){
            System.out.println("Your linked list is empty.");
            return false;
        }
        Node temp = head;
        while( temp != null && temp.data != old){
            temp = temp.next;
        }
        if(temp !=null && temp.data == old){
            temp.data = newValue;
            return true;
        }
        System.out.println("Unable to find "+old);
        return false;

    }

    /*
     * This function is used to sort the data of linked list
     * Function accepts head node as an argument.
     */
    static Node sort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node mid = getMid(head);
        Node nextToMid = mid.next;
        mid.next = null;
        Node left = sort(head);
        Node right = sort(nextToMid);
        return merge(left, right);
    }

    /*This function is used for finding middle element of linked list.
     */
    static Node getMid(Node head) {
        if (head == null) {
            return head;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return slow;
    }

    // function is used for merging two sorted linked list.
    static Node merge(Node a, Node b) {
        Node result = null;
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        if (a.data <= b.data) {
            result = a;
            result.next = merge(a.next, b);
        } else {
            result = b;
            result.next = merge(a, b.next);
        }
        return result;
    }
}

class LinkedList{
    public static void main(String[] args) {
        Operation obj = new Operation();

        Scanner input = new Scanner(System.in);
        int option;
        // Menu options for uses. 
        System.out.println("Enter 1 for insertion. ");
        System.out.println("Enter 2 for Deletion. ");
        System.out.println("Enter 3 for Updation. ");
        System.out.println("Enter 4 for Sorting. ");
        System.out.println("Enter 5 to see the data. ");
        System.out.println("Enter 6 to exit.");

        while(true){
            option = input.nextInt();
            if (option==6){
                break;
            }
            else if(option==5){
                obj.showData();
            }
            else if (option == 1){
                System.out.print("Enter a value for Insertion. ");
                int value = input.nextInt();
                obj.insertData(value);
                System.out.println("Element Added successfully");
            }
            else if(option==2){
                System.out.print("Enter the value to delete. ");
                int value = input.nextInt();
                obj.DeleteData(value);

                System.out.println();
            }
            else if(option==3){
                System.out.println("Enter the old value: ");
                int oldValue = input.nextInt();
                int newValue = input.nextInt();
                boolean resp = obj.update(oldValue, newValue);
                if(resp){
                    System.out.println("Data Updated successfully.");
                }
            }
            else if(option == 4){
                obj.head = obj.sort(obj.head);
                System.out.println("Data sorted");
            }
            else if(option == 5){
                obj.showData();
            }
            else if(option == 6){
                break;
            }
            else{
                System.out.println("Choose a correct option.");
            }
            System.out.println("Enter Your next Choice.");  
        }
    }
}