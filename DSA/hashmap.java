import java.util.*;

class Node<K, V> 
{
	K key;
	V value;
	final int hashCode;
	Node<K, V> next;

	 Node(K key, V value, int hashCode) //constructor for initialization
	{
		this.key = key;
		this.value = value;
		this.hashCode = hashCode;
	}
	public String toString() // overriding method for displaying data
	{
		return "key="+key+"  value="+value;
	}
}

class hashmap<K, V> //genric types for each type of key value pair
{
	 ArrayList<Node<K, V> > arr;
	 int CAPACITY;// Current capacity of list
	 int size;
	 hashmap()// constructor to initialize variables
	{
		arr = new ArrayList<>();
		CAPACITY = 10;
		size = 0;
		for (int i = 0; i < CAPACITY; i++)
			arr.add(null);
	}

	 int size() 
	{ return size; }
	
	 boolean isEmpty() 
	{ return size() == 0; }
	
	 final int hashCode(K key) 
	{
		return Objects.hashCode(key);
	}

	 int getIndex(K key) //finding index of key
	{
		int hashCode = hashCode(key);
		int index = hashCode % CAPACITY;
		index = index < 0 ? index * -1 : index;
		return index;
	}

	 V remove(K key) // Method to remove a given key
	{
		int i = getIndex(key); //finding index of key
		int hashCode = hashCode(key);
		Node<K, V> head = arr.get(i); // to get starting point of list

		// to search for key 
		Node<K, V> prev = null;
		while (head != null) 
		{
			if (head.key.equals(key) && hashCode == head.hashCode) //// if key is present
				break;

			prev = head;
			head = head.next;
		}
		
		if (head == null)// If key is not present
			return null;
		size--;
		if (prev != null)		// Remove key
			prev.next = head.next;
		else
			arr.set(i, head.next);

		return head.value;
	}

	 V get(K key) // to get a value corresponding to key
	{
		int i = getIndex(key);
		int hashCode = hashCode(key);
		Node<K, V> head = arr.get(i);
		while (head != null) 
		{
			if (head.key.equals(key) && head.hashCode == hashCode)
				return head.value;
			head = head.next;
		}
		return null;
	}

	 void put(K key, V value) //to add values to hashmap
	{
		int i = getIndex(key);
		int hashCode = hashCode(key);
		Node<K, V> head = arr.get(i);
		while (head != null)// To check if key is already present 
		{
			if (head.key.equals(key) && head.hashCode == hashCode) 
			{
				head.value = value;
				return;
			}
			head = head.next;
		}
		size++;
		head = arr.get(i);
		Node<K, V> h= new Node<K, V>(key, value, hashCode);
		h.next = head;
		arr.set(i, h);
	}	
	void showAll()
	{
		for(int i=0;i<arr.size();i++)
		{
			if(arr.get(i)!=null)
			System.out.println(arr.get(i));
		}
		System.out.println();
	}
}
class Demo
{
	public static void main(String[] args)
	{
	int a,b;
	hashmap<Integer, Integer> map= new hashmap<>();
	
	
	while(true)
	{
	System.out.println("===========================");
	System.out.println("*****Enter your choice*****");
	System.out.println("1.Put / Update");
	System.out.println("2.Remove");
	System.out.println("3.ShowAll");
	System.out.println("4.Search");
	System.out.println("5.IsEmpty");
	System.out.println("6.Exit");
	System.out.println("===========================");
	
	Scanner sc=new Scanner(System.in);
	int x=sc.nextInt();
	switch(x)
	{
		case 1:
		System.out.println("Enter key ");
		a=sc.nextInt();
		System.out.println("Enter value ");
		b=sc.nextInt();
		map.put(a,b);
		System.out.println("Done ");
		break;
		
		case 2:
		System.out.println("Enter key ");
		a=sc.nextInt();
		map.remove(a);
		System.out.println("Done ");
		break;
		
		case 3:
		map.showAll();
		break;
		
		case 4:
		System.out.println("Enter key ");
		a=sc.nextInt();
		System.out.println("key="+a+"     Value="+map.get(a));
		break;
		
		case 5:
		System.out.println("HashMap is empty : "+map.isEmpty());
		break;
		
		case 6:		
		System.out.println("Thank you");
		System.exit(0);
		break;
		
		default:
		System.out.println("Invalid");
		break;
	}
	}
	}
}
