package ch24.d;

public class Test01 {

  public static void main(String[] args) {
    Account acc = new Account("홍길동", 1000000);
    
    ATM kang101 = new ATM("강남-101", acc);
    ATM kang102 = new ATM("강남-102", acc);
    ATM kang103 = new ATM("강남-103", acc);
    
    kang101.start();
    kang102.start();
    kang103.start();

  }

}
