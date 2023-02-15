class AccountOverdrawDemo extends Thread {
  Account account;
  String person;

  AccountOverdrawDemo(Account account, String person) {
    this.account = account;
    this.person = person;
  }

  public void run() {
    int totalWithdrawalAmount=0;

    for (int transaction = 1; transaction <= 20; transaction++) {  //20 because 1000/50=20
      
      int withdrawalAmount = 50;
      if(account.withdraw(withdrawalAmount, person)) totalWithdrawalAmount+=withdrawalAmount;
    }

    System.out.println("Total withdrawal amount: "+ totalWithdrawalAmount);
    
  }

}
