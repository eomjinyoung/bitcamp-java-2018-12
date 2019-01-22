package ch18.a;

public class Test01 {

  public static void main(String[] args) {
    // 도구 사용하기
    
    // 어떤 규칙에 따르는 도구인지 
    // => A 규칙에 따라 만든 도구를 사용하겠다. 
    A tool;
    
    // A 규칙에 따라 만든 도구를 사용한다.
    use(new ToolA());
    use(new ToolB());
    
    // A 규칙을 따르지 않은 객체를 파라미터에 넘길 수 없다.
    //use(new String("Hello")); // 컴파일 오류!

  }
  
  static void use(A tool) {
    // tool 레퍼런스가 가리키는 인스턴스에 대해 A 규칙에 정의된 메서드를 호출한다.
    // 그러면 해당 인스턴스의 클래스를 찾아 메서드를 호출한다.
    tool.m1();
  }

}







