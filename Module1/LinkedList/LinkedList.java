package Module1.LinkedList;

import java.util.Scanner;

import Module1.Tree.Node;


class Node{
    int data;
    Node next;

    Node(int value){
        this.data = value;
        this.next = null;
    }
}

class Operation{
    private Node head;

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

    public void showData(){
        Node temp = head;
        while(temp !=null){
            System.out.print(temp.data+ " --> ");
            temp = temp.next;
        }
        System.out.println();
    }


    public void update(int old, int newValue){
        if(head==null){
            System.out.println("Your linked list is empty.");
            return;
        }
        Node temp = head;
        while( temp != null && temp.data != old){
            System.out.println("ADDRESS : "+temp);
            temp = temp.next;
        }
        if(temp !=null && temp.data == old){
            temp.data = newValue;
            return;
        }
        System.out.println("Unable To find "+old);

    }

    public void sort(){
        Node temp = head;
        while(temp !=null){
            
        }
    }
}

class LinkedList{
    public static void main(String[] args) {
        Operation obj = new Operation();

        Scanner input = new Scanner(System.in);
        int option;
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
            }
            else if(option==2){
                System.out.print("Enter the value to delete. ");
                int value = input.nextInt();
                obj.DeleteData(value);
            }
            else if(option==3){
                System.out.println("Enter the old value: ");
                int oldValue = input.nextInt();
                int newValue = input.nextInt();
                obj.update(oldValue, newValue);
            }

            System.out.println("Enter Your next Choice.");
           
        }
        

    }
}