class AccountOverdrawDemo extends Thread {
  Account account;
  String person;

  AccountOverdrawDemo(Account account, String person) {
    this.account = account;
    this.person = person;
  }

  public void run() {
    int totalWithdrawalAmount=0;

    for (int transaction = 1; transaction <= 10; transaction++) {  //10 because 1000/100=10
      
      int withdrawalAmount = 100;
      if(account.withdraw(withdrawalAmount, person)) totalWithdrawalAmount+=withdrawalAmount;
      else{
        break;
      }
    }

    System.out.println(person+ " total withdrawal amount: "+ totalWithdrawalAmount);
    
  }

}
