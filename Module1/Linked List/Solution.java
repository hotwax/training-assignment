import java.util.InputMismatchException;
import java.util.Scanner;

public class Solution {

    static Scanner scan = new Scanner(System.in);

    static void help(){
        System.out.println(
            "Choose One of the given below options" + '\n' + '\n' +
            "0. Help" + '\n' +
            "1. Add at the end of the list" + '\n' +
            "2. Add at the front of the list" + '\n' +
            "3. Get first element" + '\n' +
            "4. Get Last element" + '\n' +
            "5. Print Linked List" + '\n' +
            "6. Pop last element" + '\n' +
            "7. Pop first element" + '\n' +
            "8. Remove element by index" + '\n' +
            "9. Sort the Linked List" + '\n' +
            "10. Exit Program" + '\n'
        );
    }

    public static void main(String args[])   {
        LinkedList list = new LinkedList();
        int choice = 0;
        help();
        do {
            choice = scan.nextInt();
            int elem = 0,
                index = 0;
            boolean response = true;
            LinkedListNode node;
            try{
                switch(choice){
                    case 0:
                        help();
                        break;
                    case 1:
                        System.out.print("Enter Value of the element you want to add : ");
                        elem = scan.nextInt();
                        list.add(elem);
                        System.out.println('\n' + "Element added" + '\n');
                        break;
                    case 2:
                        System.out.print("Enter Value of the element you want to add : ");
                        elem = scan.nextInt();
                        list.addFront(elem);
                        System.out.println('\n' + "Element added" + '\n');
                        break;
                    case 3:
                        node  = list.getFirst();
                        System.out.print("Node is ");
                        if(node == null){
                            System.out.println("null" +'\n');
                        }else{
                            System.out.println(node + " and the value is " + node.data + '\n');
                        }
                        break;
                    case 4:
                        node  = list.getLast();
                        System.out.print("Node is ");
                        if(node == null){
                            System.out.println("null" +'\n');
                        }else{
                            System.out.println(node + " and the value is " + node.data + '\n');
                        }
                        break;
                    case 5:
                        list.print();
                        System.out.println();
                        break;
                    case 6:
                        list.pop();
                        System.out.println("Operation Performed" +'\n');
                        break;
                    case 7:
                        list.popFront();
                        System.out.println("Operation Performed" +'\n');
                        break;
                    case 8:
                        System.out.print("Enter Index : ");
                        index = scan.nextInt();
                        response = list.removeByIndex(index);
                        if(response == true){
                            System.out.println('\n'+"Element Removed" + '\n');
                        }else {
                            System.out.println('\n' + "Element Dosen't Exist, Try valid index" + '\n');
                        }
                        break;
                    case 9:
                        list.sort();
                        System.out.println('\n' + "List Sorted" + '\n');
                    case 10:
                        break;         
                }
            }
            catch(InputMismatchException ex){
                System.out.println(ex.getMessage());
            }
        }
        while(choice != 10);
        System.out.println('\n' + "Program Exited" + '\n' + "Thank You Very Much");
    }
}

