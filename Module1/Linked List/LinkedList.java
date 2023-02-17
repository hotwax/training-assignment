public class LinkedList {

    LinkedListNode head;
    LinkedListNode tail;

    public void add(int val){
        if(head == null){
            head = new LinkedListNode(val);
            tail = head;
        }else{
            LinkedListNode newNode = new LinkedListNode(val);
            tail.next = newNode;
            tail = tail.next;
        }
    }

    public void addFront(int val){
        if(head == null){
            head = new LinkedListNode(val);
            tail = head;
        }else{
            LinkedListNode temp = new LinkedListNode(val);
            temp.next = head;
            head = temp;
        }
    }

    public LinkedListNode getFirst(){
        return head;
    }

    public LinkedListNode getLast(){
        return tail;
    }

    public void pop(){
        if(head == null) return;
        if(head.next == null){
            head = null;
            return;
        }
        LinkedListNode current = head, prev = null;
        while(current.next != null){
            prev = current;
            current = current.next;
        }
        prev.next = null;
    }

    public void popFront(){
        if(head == null) return;
        head = head.next;
    }

    // indexing based on 0
    public boolean removeByIndex(int index){
        if(head == null) return false;
        if(index == 0){
            head = head.next;
            return true;
        }
        LinkedListNode llIterator = head;
        LinkedListNode llIteratorPrev = null;
        int itertor = 0;
        while(itertor < index){
            itertor++;
            if(llIterator == null) return false;
            llIteratorPrev = llIterator;
            llIterator = llIterator.next;
        }
        if(llIterator == null) return false;
        llIteratorPrev.next = llIterator.next;
        return true;
    }

    public void print(){
        LinkedListNode llIterator = head;
        while(llIterator != null){
            System.out.print(llIterator.data + " ");
            llIterator = llIterator.next;
        }
        System.out.println();
    }

    //---Sorting

    public LinkedListNode sortedMerge(LinkedListNode a, LinkedListNode b){
        LinkedListNode result = null;
        if(a == null) return b;
        if(b == null) return a;

        if(a.data <= b.data){
            result = a;
            result.next = sortedMerge(a.next, b);
        }else{
            result = b;
            result.next = sortedMerge(a, b.next);
        }
        return result;
    }

    public LinkedListNode getMiddle(LinkedListNode head){
        if(head == null) return head;

        LinkedListNode slow = head, fast = head;

        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public LinkedListNode mergeSort(LinkedListNode head){
        if(head == null || head.next == null) return head;

        LinkedListNode middle = getMiddle(head);
        LinkedListNode nextOfMiddle = middle.next;

        middle.next = null;

        LinkedListNode left = mergeSort(head);
        LinkedListNode right = mergeSort(nextOfMiddle);

        LinkedListNode sortedList = sortedMerge(left, right);
        return sortedList;
    }

    public void sort(){
        if(this.head == null)return;
        this.mergeSort(this.head);
        tail = head;
        while(this.tail.next != null) this.tail = this.tail.next;
    }
    //Sorting---

    public LinkedList(){
        head = null;
        tail = null;
    }
    public static void main(String args[])   {
        LinkedList ll = new LinkedList();
        ll.add(0);
        ll.print();
    }
}


