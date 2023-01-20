import java.util.*;
class Node  //Node to store data
{
    Integer key;
    int value;    
 
    Node(Integer key, int value) 
    {
        this.key = key;
        this.value = value;        
    }
}
class DoublePro
{
	int c;
	 int CAPACITY; //capacity
     int size; 
     Node[] table;
     int prime;
 
     /* Constructor */
     DoublePro(int x)  //constructor
    {
        size = 0;
        CAPACITY = x;
        table = new Node[CAPACITY];
        for (int i = 0; i < CAPACITY; i++)
            table[i] = null;
        prime = getPrime();       
    } 
    /* Function to get prime number */
     int getPrime()
    {
        for (int i = CAPACITY - 1; i >= 1; i--)
        {
            int fact = 0;
            for (int j = 2; j <= (int) Math.sqrt(i); j++)
                if (i % j == 0)
                    fact++;
            if (fact == 0)
                return i;
        }
        /* Return a prime number */
        return 3;
    }
	
     int get(Integer key)  //function to get values by key
    {
        int hash1 = hash1( key );
        int hash2 = hash2( key );
 
        while (table[hash1] != null && !table[hash1].key.equals(key))
        {
            hash1 += hash2;
            hash1 %= CAPACITY;
        }
        return table[hash1].value;
    }
	
     void insert(Integer key, int value)  //to insert a key value pair
    {
        if (size == CAPACITY)
        {
            System.out.println("Table full"); 
            return;
        }           
        int hash1 = hash1( key );
        int hash2 = hash2( key );    
       
        while (table[hash1] != null)
        {
			c++;
            hash1 += hash2;
            hash1 %= CAPACITY;
        }
        table[hash1] = new Node(key, value);        
        size++;
    }

     void remove(Integer key)  // to remove a value
    {
        int hash1 = hash1( key );
        int hash2 = hash2( key );        
        while (table[hash1] != null && !table[hash1].key.equals(key))
        {
            hash1 += hash2;
            hash1 %= CAPACITY;
        }
        table[hash1] = null;
        size--;
    }
	
     int hash1(Integer x ) // hash function
    {
        int hashVal = x.hashCode( );
        hashVal %= CAPACITY;
        if (hashVal < 0)
            hashVal += CAPACITY;
        return hashVal;
    }
	
     int hash2(Integer x )//hash function
    {
        int hashVal = x.hashCode( );
        hashVal %= CAPACITY;
        if (hashVal < 0)
            hashVal += CAPACITY;
        return prime - hashVal % prime;
    }
	
     void showAll()  //print the table
    {
        for (int i = 0; i < CAPACITY; i++)
            if (table[i] != null)
                System.out.println(table[i].key +" "+table[i].value);
    }
	int getCollisions()
	{
		return c;
	}
}
 
class Demo
{
	public static void main(String[] args)
	{
	int a,b;
	DoublePro c=new DoublePro(50);
	while(true)
	{
	System.out.println("===========================");
	System.out.println("*****Enter your choice*****");
	System.out.println("1.Put / Update");
	System.out.println("2.Remove");
	System.out.println("3.ShowAll");
	System.out.println("4.Search");
	System.out.println("5. Get collision");
	System.out.println("6. Time");
	System.out.println("7.Exit");
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
		c.insert(a,b);
		System.out.println("Done ");
		break;
		
		case 2:
		System.out.println("Enter key ");
		a=sc.nextInt();
		c.remove(a);
		System.out.println("Done ");
		break;
		
		case 3:
		c.showAll();
		break;
		
		case 4:
		System.out.println("Enter key ");
		a=sc.nextInt();
		System.out.println("key="+a+"     Value="+c.get(a));
		break;
		
		case 5:
		System.out.println("No of collision : "+c.getCollisions());
		break;
		
		case 6:		
		System.out.println("Time in mili seconds"+System.currentTimeMillis());
		break;
		
		case 7:		
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
