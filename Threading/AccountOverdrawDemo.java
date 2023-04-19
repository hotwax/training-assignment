class AccountOverdrawDemo {


// In this program we are trying to withdraw money from the account of two people at the same time.
// The program is not thread safe because the withdraw method is not synchronised.
// The withdraw method is not synchronised, so the two threads can execute the withdraw method at the same time.
// The two threads can execute the withdraw method at the same time, so the balance can be less than the amount to be withdrawn.


    public static void main(String[] args) {
        
        Account account = new Account("Rajesh and Ranita");

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        account.withdraw(100, "Rajesh");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    catch (Exception e) {
                        System.out.println("Exception occured: " + e+ "");
                    }
                  }            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
              try {
                account.withdraw(100, "Ranita");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            catch (Exception e) {
                System.out.println("Exception occured: " + e+ "");
            
            }}
          });


        System.out.println("\nOverdraw demo:");

        t1.start();
        t2.start();


    }
    
}
