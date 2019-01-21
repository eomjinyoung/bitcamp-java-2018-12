// 추상 메서드의 효용성 - 서브 클래스에서 구현하도록 강제하는 효과가 있다.
package ch17.e;

public class Test01 {

  public static void main(String[] args) {
    int[] values = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    int[] values2 = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    int[] values3 = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    
    display(new BubbleSort(), values);
    display(new QuickSort(), values2);
    
    // 이제 MergeSort는 Sorter의 추상 메서드인 sort()를 구현했다.
    // 정상적으로 동작할 것이다.
    
    display(new MergeSort(), values3);
    
  }

  static void display(Sorter sorter, int[] values) {
    
    // 정렬 객체의 클래스가 뭔지 상관없다.
    // 그 클래스를 사용할 때는 공통 분모가 되는 
    // 수퍼 클래스의 메서드를 호출한다.
    sorter.sort(values);
    
    // 정렬된 값을 출력한다.
    for (int  value : values) {
      System.out.print(value + ",");
    }
    System.out.println();
  }
}






