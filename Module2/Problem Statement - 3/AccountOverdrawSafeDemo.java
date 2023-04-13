public class AccountOverdrawSafeDemo 
{
    public static void main(String[] args) 
    {
         
        Account account=new Account("Bruce Wayne", 1000);
        Runnable withdrawal1=()->{
            synchronized(account){
                for (int i=0;i<5;i++) account.withdraw(200, "John");
            }
        };
        Runnable withdrawal2=()->{
            synchronized(account) {
                for (int i=0;i<5;i++) account.withdraw(200, "Smith");
            }
        };

        // Creating two threads for both the runnables
        Thread thread1=new Thread(withdrawal1);
        Thread thread2=new Thread(withdrawal2);

        // Starting both the thread.
        thread1.start();
        thread2.start();
    }
}
 