import java.util.Scanner;
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
        if (top == maxSize-1) { // to check stack is full
            System.out.println("Stack Overflow");
            return;
        }
        stack[++top] = element; // increasing the top and adding element
        System.out.println("Pushed");
    }
    //remvoing element from stack
    public void pop() { 
        if (top == -1) { // to check stack is empty
            System.out.println("Stack Underflow");
            return;
        }
        int element=stack[top--]; // decrement top and get element at top of stack
        System.out.println("Popped" + " "+ element);
    }

    // to get top of stack
    public void peek() {
        if (top == -1) { // to check stack is empty
            System.out.println("Stack is empty");
            return;
        }
        int element = stack[top]; // get top element of stack
        System.out.println("Top element is: " + element);
    }

    // method to check empty stack 
    public boolean isEmpty() {
        return top == -1;
    }

    // to display the stack
    public void display() { 
        if (top == -1) { //to check stack is empty
            System.out.println("Stack is empty");
            return;
        }
        System.out.print("Stack:");
        for (int i = top; i >= 0; i--) {
            System.out.print(stack[i] + " ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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
                    stack.push(element);
                    break;
                case 2:
                    stack.pop();
                    break;
                case 3:
                    stack.peek();
                    break;
                case 4:
                    if (stack.isEmpty()) {
                        System.out.println("Stack is empty");
                    } else {
                        System.out.println("Stack is not empty");
                    }
                    break;
                case 5:
                    stack.display();
                    break;
                case 6:
                    System.out.println("Exit done");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 6);
        sc.close();
    }
}