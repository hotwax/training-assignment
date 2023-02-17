import java.util.InputMismatchException;
import java.util.Scanner;

public class Solution {

    static Scanner scan = new Scanner(System.in);

    static void help(){
        System.out.println(
            "Choose One of the given below options" + '\n' + '\n' +
            "0. Help" + '\n' +
            "1. Add Element to Stack" + '\n' +
            "2. Peak at Top Element" + '\n' +
            "3. Remove Top most element" + '\n' +
            "4. is the Stack Empty?? " + '\n' +
            "5. Size of the stack" + '\n' +
            "6. Exit Program" + '\n'
        );
    }

    public static void main(String args[])   {
        Stack stack =  new Stack();
        int choice = 0;
        help();
        do {
            choice = scan.nextInt();
            int elem = 0;
            boolean response = true;
            try{
                switch(choice){
                    case 0:
                        help();
                        break;
                    case 1:
                        System.out.print("Enter Value of the element you want to add : ");
                        elem = scan.nextInt();
                        stack.push(elem);
                        System.out.println('\n' + "Element added" + '\n');
                        break;
                    case 2:
                        elem = stack.top();
                        if(elem == Integer.MIN_VALUE){
                            System.out.println("Stack is empty, Cannot Peak" + '\n');
                        }else{
                            System.out.println("Value is :" + elem + '\n');
                        }
                        break;
                    case 3:
                        stack.pop();
                        System.out.println("Operation Performed");
                        break;
                    case 4:
                        response = stack.isEmpty();
                        if(response){
                            System.out.println("Yes Stack is Empty" + '\n');
                        }else{
                            System.out.println("No Stack is not Empty" + '\n');
                        }
                        break;
                    case 5:
                        System.out.println("Size of the stack is " + stack.size() + '\n');
                        break;
                    case 6:
                        break;         
                }
            }
            catch(InputMismatchException ex){
                System.out.println(ex.getMessage());
            }
        }
        while(choice != 6);
        System.out.println('\n' + "Program Exited" + '\n' + "Thank You Very Much");
    }
}

