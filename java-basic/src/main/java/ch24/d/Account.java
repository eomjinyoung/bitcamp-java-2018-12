package ch24.d;

public class Account {
  String name;
  double balance;
  
  public Account(String name, double balance) {
    this.name = name;
    this.balance = balance;
  }

  public double withdraw(double money) {
    double temp = this.balance;
    
    if (temp - money < 0)
      return 0;
    
    temp = temp - money;
    
    this.balance = temp;
    
    return money;
  }
  
  
}
