package ch13;

public class Calculator {
  
  private int result;
  
  public int getResult() {
    return this.result;
  }
  
  public void plus(int value) {
    this.result += value;
  }
  
  public void minus(int value) {
    this.result -= value;
  }
}
