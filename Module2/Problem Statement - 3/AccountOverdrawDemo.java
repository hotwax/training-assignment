public class AccountOverdrawDemo 
{
    public static void main(String[] args) 
    {
        // Creating an instance of account class. With name and balance.
        Account account = new Account("John Smith", 1000);

        Runnable withdrawal1=()->{
            for(int i=0;i<5;i++) account.withdraw(200,"John");
        };
        Runnable withdrawal2=()->{
            for(int i=0;i<5;i++) account.withdraw(200, "smith");
        };

        // Creating thread for both the runnables.
        Thread thread1=new Thread(withdrawal1);
        Thread thread2=new Thread(withdrawal2);

        // Starting the thread.
        thread1.start();
        thread2.start();
    }
}
 