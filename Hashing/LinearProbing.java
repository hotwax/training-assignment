import java.util.*;
class Linear
{
     int size, max;       
     Integer[] keys;   //table to store values
     Integer[] vals;    
    static int c=0;
     Linear(int capacity)  //constructor to initize
    {
        size = 0;
        max = capacity;
        keys = new Integer[max];
        vals = new Integer[max];
    }  
 
    /** Fucntion to check if hash table contains a key **/
     boolean contains(Integer key) 
    {        
	return get(key) !=  null;
    }
 
    /** Function  to get hash code of a given key **/
     int hash(Integer key) 
    {
        return key.hashCode() % max;
    }    
 
     void insert(Integer key, Integer val)   // function to insert
    {                
        int tmp = hash(key);
        int i = tmp;
        do
        {
            if (keys[i] == null)
            {
                keys[i] = key;
                vals[i] = val;
                size++;
                return;
            }
            if (keys[i].equals(key)) 
            { 
                vals[i] = val;
				c++;
                return; 
            }            
            i = (i + 1) % max;            
        } while (i != tmp);     
		
    }
 
     Integer get(Integer key) // function to search for value
    {
        int i = hash(key);
        while (keys[i] != null)
        {
            if (keys[i].equals(key))
                return vals[i];
            i = (i + 1) % max;
        }            
        return null;
    }
 
     void remove(Integer key)  // function to remove value
    {
        if (!contains(key)) 
            return;
 
        int i = hash(key);
        while (!key.equals(keys[i])) 
            i = (i + 1) % max;        
        keys[i] = vals[i] = null;
 
        size--;        
    }       
	int getCollision() // to count number of collision
	{
		return c;
	}
 
    /** Function to print HashTable **/
     void showAll()
    {
        for (int i = 0; i < max; i++)
            if (keys[i] != null)
                System.out.println(keys[i] +" "+ vals[i]);
        System.out.println();
    }   
}
class Demo
{
	public static void main(String[] args)
	{
	int a,b;
	Linear c=new Linear(20);
	long l1=System.currentTimeMillis();
	while(true)
	{
	System.out.println("===========================");
	System.out.println("*****Enter your choice*****");
	System.out.println("1.Put / Update");
	System.out.println("2.Remove");
	System.out.println("3.ShowAll");
	System.out.println("4.Search");
	System.out.println("5.Get collision");
	System.out.println("6.Time");
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
		System.out.println("No of collision : "+c.getCollision());
		break;
		
		case 6:		
		long l2=System.currentTimeMillis();
		System.out.println("Time in mili seconds "+(l2-l1));
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