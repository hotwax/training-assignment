import java.util.Scanner;
import java.util.InputMismatchException;
class Stack {
    private int top;
    private int maxSize;
    private int stack[];

    //Constructor for Stack class intialize stack with given maximum size
    public Stack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
        this.top = -1;  // stack is empty
    }
    
    // adding element to stack 
    public void push(int element) {
        if (top == maxSize-1) 
        { //to check stack is full
            System.out.println("-1");
            return;
        }
        stack[++top] = element; // increasing the top and adding element
       }
    //remvoing element from stack
    public void pop() { 
        if (top == -1) { // to check stack is empty
            System.out.println("-1");
            return;
        }
          top--; // decrement top and get element at top of stack
    }

    // to get top of stack
    public int peek() {
        if (top == -1) { // to check stack is empty
            return -1;
        }
        return stack[top]; // get top element of stack
    }

    // method to check empty stack 
    public boolean isEmpty() {
        return top == -1;
    }
    // method to check stack is Full 
    public boolean isFull(){
        return top == maxSize -1;
    }

    // to display the stack
    public void display() { 
        if (top == -1) { //to check stack is empty
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.print(stack[i] + " ");
        }
    }

    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in))
        {
        System.out.println("Enter size of stack you want: ");
        int maxSize = sc.nextInt(); //user input for max size
        Stack stack = new Stack(maxSize);
        int choice;
        do {
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Is empty");
            System.out.println("5. Display");
            System.out.println("6. Exit");
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();  // get user's choice
            switch (choice) {
                case 1:
                    System.out.println("Enter element to push: ");
                    int element = sc.nextInt();

                    if(stack.isFull()){
                        System.out.println("stack is full");
                        break;
                    }
                    stack.push(element);
                    break;
                case 2:
                    if(stack.isEmpty()){
                        System.out.println("Stack is empty");
                        break;
                    }
                    int top=stack.peek();
                    stack.pop();
                    System.out.println("Deleted element is "+ top);
                    break;
                case 3:
                    if(stack.isEmpty()){ 
                        System.out.println("Stack is empty");
                        break;
                    }
                   System.out.println("Top element is "+ stack.peek());
                    break;
                case 4:
                    if (stack.isEmpty()) {
                        System.out.println("Stack is empty");
                    } else {
                        System.out.println("Stack is not empty");
                    }
                    break;
                case 5:
                    if(stack.isEmpty()){
                        System.out.println("stack is empty");
                        break;
                    }
                  
                    stack.display();
                    break;
                case 6:
                    System.out.println("Exit done");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 6);
    }catch(InputMismatchException ex)
    {
        System.out.println(ex);
    }
}
}