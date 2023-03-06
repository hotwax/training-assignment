// class AccountOverdrawSafeDemo
class AccountOverdrawSafeDemo {
    public static void main(String[] args) {
      // creating an object of class Account
      Account account = new Account("Rohit and Rhea");
      // Creating first Thread
      Thread thread1 = new Thread(() -> {
        {
          for (int i = 0; i < 5; i++) {
            try {
                account.synchronisedWithdraw(100, "Rhea");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
          }
        }
      });
      // Creating second Thread
      Thread thread2 = new Thread(() -> {
         {
          for (int i = 0; i < 5; i++) {
            try {
                account.synchronisedWithdraw(100, "Rohit");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
          }
        }
      });
      thread1.start();
      thread2.start();
    }
  }