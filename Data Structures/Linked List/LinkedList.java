import java.util.*;

public class LinkedList {

    Node head;
    int len;
    Node sorted;

    class Node{

        int data;
        Node next;

        Node( int d){

            this.data= d;
            this.next= null;

        }

    }



// Insertion at start

    void insertStart(int data)
    {   

        Node newnode= new Node(data);
        newnode.next= head;
        head= newnode;
        len+=1;

    }   


// Insertion at end

    void insertEnd( int data)
    {  
        

        if(head==null)
        {
            head= new Node(data);
            len+=1;
            return;
        }

        Node newnode= new Node(data);
        Node ptr= head;

        while (ptr.next!= null)
        {  
            ptr= ptr.next;
        }
        ptr. next= newnode;
        len+=1;
    }


// deletion at start

    void deleteStart()
    {
        head= head.next;
        len-=1;
    }

// deletion at end

    void deleteEnd()
    {
        Node ptr= head;
        if (ptr==null)
        {
            return;
        }
        if(ptr.next==null)
        {
            head= head.next;
            return;
        }


        while ((ptr.next).next!= null)
        {
            ptr= ptr.next;
        }

        ptr.next= null;
        len-=1;
    }



// display

    void display()
    {
        if (head==null)
        {
            System.out.print("List is Empty");
        }

        Node ptr= head;
        while( ptr!= null)
        {
            System.out.print(ptr.data );
            if (ptr.next!= null)
                System.out.print("->");
                
            ptr= ptr.next;
        }

        System.out.println();

    }


// update by position

    void updateByPos(int pos,int data)
    {

        if (head==null){
            System.out.print("List is Empty");
            return;
        }

        if (pos>len || pos<=0)
        {
            System.out.println("Invalid Position");
            return;
        }

        

        int i=1;
        Node ptr= head;
        while (i!= pos)
        {
            ptr= ptr.next;
            i+=1;
        }
        ptr.data= data;

    }




// Sorting

    void sort()
    {
        sorted= null;
        Node current= head;

        while (current!= null )
        {
            Node next;
            next= current.next;
            sortedInsert(current);
            current= next;
        }

        head= sorted;
    }


    void sortedInsert(Node newnode){

        if( (sorted==null) || (newnode.data<= sorted.data ) )
        {
            newnode.next= sorted;
            sorted = newnode;
        }

        else{

            Node curr= sorted;
            while ( curr.next!=null && curr.next.data< newnode.data && curr.next!= null)
            {
                curr= curr.next;
            }

        newnode.next= curr.next;
            curr.next= newnode;

        }
    }


// Search

    int search( int data){

        Node ptr= head;
        int index=0;
        while( ptr!= null)
        {

            if (ptr.data== data){

                // System.out.println("Element found at %s".formatted(index));
                return index;
            }

            index+=1;

            ptr= ptr.next;

        }

        System.out.println("Element not found");
        return -1;

    }



    public static void main(String[] args) 
    {
    try (Scanner sc = new Scanner(System.in)) {
        LinkedList linkedList = new LinkedList();
        int choice = 0;

               
            do {

                System.out.println("Menu:");
                System.out.println("1. Insert at start");
                System.out.println("2. Insert at end");
                System.out.println("3. Delete from start");
                System.out.println("4. Delete from end");
                System.out.println("5. Display");
                System.out.println("6. Update by position");
                System.out.println("7. Sort");
                System.out.println("8. Search");
                System.out.println("9. Exit");
                System.out.print("Enter your choice: ");

                
                
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter data to insert at start: ");
                        int dataStart = sc.nextInt();
                        linkedList.insertStart(dataStart);
                        System.out.println("Data Inserted");

                        break;
                    case 2:
                        System.out.print("Enter data to insert at end: ");
                        int dataEnd = sc.nextInt();
                        linkedList.insertEnd(dataEnd);
                        System.out.println("Data Inserted");

                        break;
                    case 3:
                        linkedList.deleteStart();
                        System.out.println("Data deleted from start");

                        break;
                    case 4:
                        linkedList.deleteEnd();
                        System.out.println("Data deleted from end");

                        break;
                    case 5:
                        linkedList.display();
                        System.out.print("Enter your choice: ");

                        break;
                    case 6:
                        System.out.print("Enter position to update: ");
                        int pos = sc.nextInt();
                        System.out.print("Enter new data: ");
                        int dataUpdate = sc.nextInt();
                        linkedList.updateByPos(pos, dataUpdate);
                        System.out.print("Data updated");

                        break;
                    case 7:
                        linkedList.sort();
                        System.out.println("List sorted.");
                        System.out.print("List sorted");

                        break;
                    case 8:
                        System.out.print("Enter data to search: ");
                        int searchData = sc.nextInt();
                        int res= linkedList.search(searchData);

                        if (res!=-1)
                        System.out.println("Element found at %s".formatted(res));                       
    

                        break;
                    case 9:
                        System.out.println("Exiting...");

                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } while (choice != 9);
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());}

}
    
}
