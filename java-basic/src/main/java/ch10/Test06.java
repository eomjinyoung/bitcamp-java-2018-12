// 인스턴스 필드의 초기화 - 생성자를 통해 필드를 초기화 하기 
package ch10;

class Monitor4 {
  int bright; // 밝기 (0% ~ 100%)
  int contrast; // 명암 (0% ~ 100%)
  int widthRes; // 해상도 너비
  int heightRes; // 해상도 높이
  
  Monitor4() { // 생성자 <== 파라미터를 받지 않는 생성자를 기본 생성자(default constructor)라 부른다.
    this.bright = 50;
    this.contrast = 50;
    this.widthRes = 1920;
    this.heightRes = 1080;
  }
  
  void display() {
    System.out.println("----------------------------------");
    System.out.printf("밝기(%d)\n", this.bright);
    System.out.printf("명암(%d)\n", this.contrast);
    System.out.printf("해상도(%d x %d)\n", this.widthRes, this.heightRes);
    System.out.println("----------------------------------");
  }
}

public class Test06 {
  public static void main(String[] args) {
    // 모니터 인스턴스 생성
    Monitor4 m1 = new Monitor4();
    
    // 인스턴스 필드의 값이 생성자를 통해 유효한 기본 값들로 
    // 미리 초기화 되었기 때문에 바로 사용할 수 있다.
    m1.display(); 
    
    // 물론 특정 속성의 값을 바꾼 후에 사용해도 된다.
    m1.bright = 40;
    
    m1.display();
  }
}
















