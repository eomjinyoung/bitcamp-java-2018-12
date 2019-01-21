package ch17.b;

public class Test01 {

  public static void main(String[] args) {
    
    int[] values = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    
    BubbleSort s1 = new BubbleSort();
    
    display(s1, values);
  }

  // 정렬을 수행하는 객체와 값을 주면 
  // 그 값을 정렬한 후 출력하는 메서드이다.
  static void display(BubbleSort sorter, int[] values) {
    
    // 출력하기 전에 정렬을 먼저 수행한다.
    sorter.run(values);
    
    // 정렬된 값을 출력한다.
    for (int  value : values) {
      System.out.print(value + ",");
    }
  }
}






