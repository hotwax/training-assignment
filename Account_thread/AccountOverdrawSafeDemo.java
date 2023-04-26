// In the AccountOverdrawSafeDemo class, the withdraw method is synchronized, which means that only one thread can access the method at a time. This ensures that the balance will never go negative, even if two threads try to access the Account object simultaneously.

class Account  
{
    String name ;  // Name of the Account
    int balance ; // Balance of the Accout

    int person1_Total_Withdrawl = 0 ; // to calculate the total withdrawl of person1 
    int person2_Total_Withdrawl = 0 ; // to calculate the total withdrawl of person2 

    public Account(String name)  // Constructor 
    {
        balance = 1000 ;
        this.name = name ;
    }
    public synchronized int withdraw(int amount , String person) {  // Make the method sychronized to make sure only one thread can access 
                                                                     // method at a time 
        if (balance - amount >= 0) // to avoid negative transcations 
        {  
            try {
                Thread.sleep(100);  // for taking thread to waiting state 
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            balance -= amount; // deduct balance by amount(100)

            // to calculate the total withdrawl of both persons
            if(person=="person1") 
            {
                person1_Total_Withdrawl+=100 ; 
            }
            else
            {
                person2_Total_Withdrawl+=100 ;
            }
            
            System.out.println(person + " has withdrawn " + amount + " from " + name + " account");
            System.out.println("Balance Left in Account is " + balance);
            return 0 ;
        } 
        else 
        {
            System.out.println("Hey "+person+ " Your " + name + " does not have sufficient balance");
            return 1 ;
        }
    }
    
}

class AccountOverdrawSafeDemo {

    // Main Method 
    public void Operations()
    {
        Account account = new Account("SBI Account");
        //  Overide and Define the run() method of Runnable Interface 
            Runnable person1 = new Runnable() {
                public void run()
                {
                    for(int i=0 ; i<10 ; i++)  
                    {
                        int isAccountNull = account.withdraw(100, "person1"); // Withdraw 100 rupees by person 1
                        if(isAccountNull==1)
                        {
                            return ;
                        }
                    }
                }
            };

            // Overide and Define the run() method of Runnable Interface 
            Runnable person2 = new Runnable() {
                public void run()
                {
                    for(int i=0 ; i<10 ; i++)
                    {
                        int isAccountNull = account.withdraw(100, "person2"); // Withdraw 100 rupees by person 1
                        if(isAccountNull==1)
                        {
                            return ;
                        }
                    }
                }
            };

            // Create Two Threads  which takes runnable object as Argument 
            Thread thread1 = new Thread(person1);  
            Thread thread2 = new Thread(person2);

            thread1.start(); // call start method of thread class
            thread2.start(); 


            // .join() = after the complete excution of both the thread , Main resume its Execution
            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Total Amount withdraw by the person1 and person2 
            int total_Withdrawl = (int)account.person1_Total_Withdrawl + (int)account.person2_Total_Withdrawl ;

            System.out.println("Total Amount was 1000 initially "); 
            System.out.println("Total Amount Withdrawl by Person 1 "+ account.person1_Total_Withdrawl);
            System.out.println("Total Amount Withdrawl by Person 2 "+ account.person2_Total_Withdrawl);
            System.out.println("Total Amount Withdraw is "+ total_Withdrawl  );

            
    }
        
}