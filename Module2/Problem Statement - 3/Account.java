public class Account 
{
    private String name;
    private double balance;
    /*
     * Account class constructor which will initialize the name and balance field.
     */
    public Account()
    {
        this.name="";
        this.balance=0.0;
    }

    /*
     * Parameterised contructor which will accept two fields
     * name which a string field and the second is balance which is a double field.
     * Using this a user can set the account balance and name while creating the instance (object ) of Account class.
     */
    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }
    // This method is used for setting the name field after creating the instance.
    public void setName(String name)
    {
        this.name=name;
    }

    // This method is used for setting the balance.
    public void setBalance(double balance)
    {
        this.balance=balance;
    }
    // This method is used to get the name of the customer
    public String getName()
    {
        return this.name;
    }
    // This method is used to get the balance of the customer
    public double getBalance()
    {
        return this.balance;
    }

    /*
     * This method is used to withdraw amount.
     * The method accepts two argument they are amount and name
     * This method is a synchronized.
     */
    public synchronized void withdraw(double amount, String name) 
    {
        System.out.println(name + " is trying to withdraw $" + amount);
        if(balance>=amount) 
        {
            try 
            {
                Thread.sleep(1000); // simulate time required to process the withdrawal
            }catch(InterruptedException ex) 
            {
                System.out.println(ex.getMessage());
            }
            balance-=amount;
            System.out.println(name + " has withdrawn $" + amount);
            System.out.println(name + "'s remaining balance is $" + balance);
        } else 
        {
            System.out.println(name + " does not have sufficient balance to withdraw $" + amount);
        }
    }
}
 