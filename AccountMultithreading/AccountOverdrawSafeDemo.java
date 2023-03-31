class AccountOverdrawSafeDemo extends Thread{
  String person;
  Account account;

  AccountOverdrawSafeDemo(Account account, String name){
    this.person=name;
    this.account=account;
  }

  public void run(){

    int totalWithdrawalAmount=0;

    for (int transaction = 1; transaction <= 10; transaction++) {
      
      int withdrawalAmount = 100;
      synchronized(account){
        if(account.withdraw(withdrawalAmount, person)) totalWithdrawalAmount+=withdrawalAmount;
        else{
          break;
        }
      }
    }

    System.out.println(person+ " total withdrawal amount: "+ totalWithdrawalAmount);
  }
}
