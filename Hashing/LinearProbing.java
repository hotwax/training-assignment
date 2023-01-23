import java.util.Scanner;

class hashTable {

    private int size, elementCount;
    private int[] table;
    public Scanner sc = new Scanner(System.in);

    // Initialize hash Table
    hashTable() {
        System.out.print("Enter the size of the Table : ");
        this.size = sc.nextInt();

        this.table = new int[size];

        for (int i = 0; i < size; i++) {
            this.table[i] = 0;
        }
        this.elementCount = 0;
    }

    // method that checks if the hash table is full or not
    boolean isFull() {
        if (this.elementCount == this.size) {
            return true;
        } else {
            return false;
        }
    }

    // method that returns position for a given element
    int hashFunction(int element) {
        return element % this.size;
    }

    // Linear Probing
    void linearProbing(int element, int position, boolean isStored) {
        System.out.println("Collision has occured for element " + element + " at position " + position
                + " finding new Position.");
        int i = 1;
        while (this.table[position] != 0) {
            position += i;
            if (position >= this.size) {
                position = 0;
            }
            i += 1;
        }
        this.table[position] = element;
        isStored = true;
        this.elementCount += 1;
    }

    // method that inserts element inside the hash table
    void insert(int element) {
        int position;
        // checking if the this.table is full
        if (this.isFull()) {
            System.out.println("Hash Table Full");
            return;
        }

        boolean isStored = false;

        position = this.hashFunction(element);

        // checking if the position is empty
        if (this.table[position] == 0) {
            this.table[position] = element;
            System.out.println("Element " + element + " at position " + position);
            isStored = true;
            this.elementCount += 1;
        }

        // collision occured hence we do linear probing
        else {
            this.linearProbing(element, position, isStored);
        }
        return;
    }

    // method that searches for an element in the table
    // returns position of element if found
    // else returns false
    int search(int element) {
        boolean found = false;

        int position = this.hashFunction(element);

        if (this.table[position] == element) {
            found = true;
            return position;
        }

        // if element is not found at position returned hash function
        // then first we search element from position+1 to end
        // if not found then we search element from position-1 to 0
        else {
            int temp = position - 1;
            // check if the element is stored between position+1 to size
            while (position < this.size) {
                if (this.table[position] != element) {
                    position += 1;
                } else {
                    return position;
                }
            }

            // now checking if the element is stored between position-1 to 0
            position = temp;
            while (position >= 0) {
                if (this.table[position] != element) {
                    position -= 1;
                } else {
                    return position;
                }
            }
        }
        if (!found) {
            System.out.println("Element not found");
            return -1;
        }
        return -1;
    }

    // method to remove an element from the table
    void remove(int element) {
        int position = this.search(element);
        if (position != -1) {
            this.table[position] = 0;
            System.out.println("Element " + element + " is Deleted");
            this.elementCount -= 1;
        } else {
            System.out.println("Element is not present in the Hash Table");
        }
        return;
    }

    // method to display the hash table
    void display() {
        for (int i = 0; i < this.size; i++) {
            System.out.println(i + " = " + this.table[i]);
        }
        System.out.println("The number of element is the Table are : " + this.elementCount);
    }

    public static void main(String arg[]) {
        hashTable ht = new hashTable();
        Boolean flag = true;
        while (flag) {
            System.out.println("Select an option from the menu below");
            System.out.println("1. Insert a new element in the Hash Table");
            System.out.println("2. Delete an element from the Hash Table");
            System.out.println("3. Exit");
            Scanner input = new Scanner(System.in);
            int choice = input.nextInt();
            if (choice < 1 || choice > 3) {
                System.out.println("Invalid choice");
            } else {
                switch (choice) {
                    case 1:
                        System.out.println("Enter the value to be inserted");
                        int value = input.nextInt();
                        ht.insert(value);
                        break;
                    case 2:
                        System.out.println("Enter the key to be deleted");
                        int d_key = input.nextInt();
                        ht.remove(d_key);
                        break;
                    case 3:
                        flag = false;
                        break;
                }
                ht.display();
                if (flag == false) {
                    System.out.println("Exiting the program");
                }
            }
        }
    }

};
