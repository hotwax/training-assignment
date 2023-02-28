import java.util.Scanner;
public class DoubleHashing
{
  private int capacity;
  private int[] keys;
  private int[] values;
  private int size;
  int numCollisions;

  public DoubleHashing (int capacity)
  {
    this.capacity = capacity;
    this.keys = new int[capacity];
      this.values = new int[capacity];
      this.size = 0;
      this.numCollisions = 0;
  }

  public int hash1 (int key)
  {
    return key % capacity;
  }

  public int hash2 (int key)
  {
    return 2 - (key % 2);	// 2 is a prime number smaller than the capacity
  }

  public void put (int key, int value)
  {
    if (size == capacity)
      {
	System.out.println ("Hashmap is full");
	return;
      }

    int index = hash1 (key);
    int offset = hash2 (key);

    while (keys[index] != 0 && keys[index] != key)
      {
	index = (index + offset) % capacity;
	numCollisions++;
      }

    if (keys[index] == key)
      {
	values[index] = value;	// updating existing value
      }
    else
      {
	keys[index] = key;
	values[index] = value;
	size++;
      }
    System.out.println ("Inserted key " + key + " with value " + value);
  }
  // get the value
  public int get (int key)
  {
    int index = hash1 (key);
    int offset = hash2 (key);
    int numCollisions = 0;

    while (keys[index] != 0 && keys[index] != key)
      {
	index = (index + offset) % capacity;
	numCollisions++;
      }

    if (keys[index] == key)
      {
	System.out.println ("Found key " + key + " with value " +
			    values[index]);
	return values[index];
      }
    else
      {
	System.out.println ("Key " + key + " not found with");
	return -1;		// return -1 if key is not found
      }
  }
  //display
  public void display ()
  {
    System.out.println ("Hashmap ");
    for (int i = 0; i < capacity; i++)
      {
	if (keys[i] != 0)
	  {
	    System.out.println (keys[i] + " => " + values[i]);
	  }
      }
  }

  public static void main (String[]args)
  {
    Scanner scanner = new Scanner (System.in);
    System.out.print ("Enter capacity of Hashmap: ");
    int capacity = scanner.nextInt ();
    DoubleHashing hashmap = new DoubleHashing (capacity);
    int choice;
    do{
	System.out.println ("1. Insert element");
	System.out.println ("2. Get element");
	System.out.println ("3. Display Hashmap");
	System.out.println ("4.Number of collision");
	System.out.println ("5.Exit");
	System.out.print ("Enter choice: ");
	choice = scanner.nextInt ();
	switch (choice)
	  {
	  case 1:
	    System.out.print ("Enter key:");
	    int key = scanner.nextInt ();
	    System.out.print ("Enter value: ");
	    int value = scanner.nextInt ();
	    hashmap.put (key, value);
	    break;
	  case 2:
	    System.out.print ("Enter key: ");
	    key = scanner.nextInt ();
	    hashmap.get (key);
	    break;
	  case 3:
	    hashmap.display ();
	    break;

	  case 4:
	    System.out.println ("collision - " + hashmap.numCollisions);
	    break;
	  case 5:
	    System.out.println ("Exit");
	    break;
	  default:
	    System.out.println ("Invalid choice");
	    break;
	  }
      }while (choice != 5);
  }
}
