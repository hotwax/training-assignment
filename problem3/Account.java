public class Account 
{
    private String name;
    private double balance;
    public Account()
    {
        this.name="";
        this.balance=0.0;
    }
    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public void setBalance(double balance)
    {
        this.balance=balance;
    }
    public String getName()
    {
        return this.name;
    }
    public double getBalance()
    {
        return this.balance;
    }
    public synchronized void withdraw(double amount) 
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
 