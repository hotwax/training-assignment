import java.util.*;
class Quadratic
{
     int size, max;       
     Integer[] keys;   
     Integer[] value;    //arrays to store data
	 static int c;
 
     Quadratic(int capacity) //constructor to initize
    {
        size = 0;
        max = capacity;
        keys = new Integer[max];
        value = new Integer[max];
    }  
 
     boolean contains(Integer key)  // contains key or not
    {
        return get(key) !=  null;
    }
 
     int hash(Integer key)  //hash code generation method
    {
        return key.hashCode() % max;
    }    
 
     void insert(Integer key, Integer val)  //insertion of an key value
    {                
        int tmp = hash(key);
        int i = tmp, h = 1;
        do
        {
            if (keys[i] == null)
            {
                keys[i] = key;
                value[i] = val;
                size++;
                return;
            }
            if (keys[i].equals(key)) 
            { 
                value[i] = val; 
				c++;
                return; 
            }            
            i = (i + h * h++) % max;            
        } while (i != tmp);       
    }
 
     Integer get(Integer key) //to get value from an index
    {
        int i = hash(key), h = 1;
        while (keys[i] != null)
        {
            if (keys[i].equals(key))
                return value[i];
            i = (i + h * h++) % max;
            System.out.println("i "+ i);
        }            
        return null;
    }
 
     void remove(Integer key) // to remove key value pair 
    {
        if (!contains(key)) 
            return;
 
        int i = hash(key), h = 1;
        while (!key.equals(keys[i])) 
            i = (i + h * h++) % max;        
        keys[i] = value[i] = null;
 
        size--;        
    }       
 
     void showAll() //display all
    {
        for (int i = 0; i < max; i++)
            if (keys[i] != null)
                System.out.println(keys[i] +" "+ value[i]);
			
        System.out.println();
    }   
	
	int getCollision() // find out all collision
	{
		return c;
	}
}
 
class Demo
{
	public static void main(String[] args)
	{
	int a,b;
	Quadratic c=new Quadratic(30);
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