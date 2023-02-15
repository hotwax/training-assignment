class AccountOverdrawSafeDemo extends Thread{
  String person;
  Account account;

  AccountOverdrawSafeDemo(Account account, String name){
    this.person=name;
    this.account=account;
  }

  public void run(){

    int totalWithdrawalAmount=0;

    for (int transaction = 1; transaction <= 20; transaction++) {
      
      int withdrawalAmount = 50;
      synchronized(account){
        if(account.withdraw(withdrawalAmount, person)) totalWithdrawalAmount+=withdrawalAmount;
      }
    }

    System.out.println("Total withdrawal amount: "+ totalWithdrawalAmount);
  }
}
