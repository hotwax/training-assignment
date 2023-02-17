public class Stack {
    private int curIndex;
    int storage[];

    public int top(){
        //minus infinity
        if(curIndex == -1) return Integer.MIN_VALUE; 
        return storage[curIndex];
    }

    public boolean isEmpty(){
        return (curIndex == -1);
    }

    public void pop(){
        if(curIndex == -1)return;
        curIndex--;
    }

    // Doubling the size of current storage, replicating default nature of ArrayList
    private void increaseSize(){
        int temp[] = new int[storage.length * 2];
        for(int i = 0; i < storage.length; ++i) temp[i] = storage[i];
        storage = temp;
    }

    public void push(int val){
        if(curIndex == storage.length - 1) increaseSize();
        storage[curIndex + 1] = val;
        curIndex++;
    }

    public int size(){
        return curIndex + 1;
    }

    public Stack(){
        curIndex = -1;
        storage = new int[1];
    }
}
