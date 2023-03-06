// class AccountOverdrawDemo
class AccountOverdrawDemo {

    public static void main(String[] args) {
        // creating an object of class Account
        Account account = new Account("Rohit and Rhea");

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        account.withdraw(100, "Rohit");
                    } catch (InterruptedException e) {
                        
                        e.printStackTrace();
                    }
                  }            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
              try {
                account.withdraw(100, "Rhea");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            }
          });

        thread1.start();
        thread2.start();
    }
}