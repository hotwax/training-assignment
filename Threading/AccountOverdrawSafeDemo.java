class AccountOverdrawSafeDemo {
    public static void main(String[] args) {
      Account account = new Account("Rajesh and Ranita");
      Thread t1 = new Thread(() -> {
        {
          for (int i = 0; i < 5; i++) {
            try {
                account.synchronisedWithdraw(100, "Rajesh");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
          }
        }
      });
      Thread t2 = new Thread(() -> {
         {
          for (int i = 0; i < 5; i++) {
            try {
                account.synchronisedWithdraw(100, "Ranita");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
          }
        }
      });
      t1.start();
      t2.start();
    }
  }
  