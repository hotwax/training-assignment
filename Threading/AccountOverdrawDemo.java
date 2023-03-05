
class AccountOverdrawDemo {


    public static void main(String[] args) {
        
        Account account = new Account("Rajesh and Ranita");

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        account.withdraw(100, "Rajesh");
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                  }            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
              try {
                account.withdraw(100, "Ranita");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            }
          });

        t1.start();
        t2.start();




    }
    
}
