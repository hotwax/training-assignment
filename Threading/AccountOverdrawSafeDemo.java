class AccountOverdrawSafeDemo {


  // In this program we are trying to withdraw money from the account of two people at the same time.
  // The withdraw method is synchronised, so the two threads cannot execute the withdraw method at the same time.

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
            catch (Exception e) {
              System.out.println("Exception occured: " + e+ "");
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
            catch (Exception e) {
              System.out.println("Exception occured: " + e+ "");
            }
          }
        }
      });

      System.out.println("\nOverdraw Safe demo:\n");

      t1.start();
      t2.start();
    }
  }
  