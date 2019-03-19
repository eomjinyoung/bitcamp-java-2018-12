package ch29.g;

import java.beans.PropertyEditorSupport;

// 스프링 IoC 컨테이너에서 사용할 프로퍼티 에디터 만들기
// => java.beans.PropertyEditor 인터페이스를 구현하면 된다.
//    문제는 인터페이스에 선언된 메서드가 너무 많아 클래스로 구현하기가 매우 번거롭다.
// => java.beans.PropertyEditorSupport 클래스를 상속 받으면 된다.
//    PropertyEditor 인터페이스를 구현한 클래스이다.
//    이 클래스를 상속 받아 필요한 메서드만 오버라이딩 하는 것이 
//    인터페이스를 직접 구현하는 것 보다 편하다!
// 
public class MyCustomBlackBoxEditor extends PropertyEditorSupport {
  
  // 스프링 IoC 컨테이너는 String을 ch29.g.BlackBox 클래스로 바꾸기 위해 이 메서드를 먼저 호출한다.
  // 그리고 getValue()를 호출하여 변환된 값을 꺼내 쓴다.
  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    // XML 파일에 입력한 문자열 값을 분석하여 
    String[] abcd = text.split(",");
    
    // 바꾸고자 하는 객체로 만든다.
    BlackBox blackBox = new BlackBox();
    blackBox.setMaker(abcd[0]);
    blackBox.setModel(abcd[1]);
    
    // 내부에 저장한다.
    this.setValue(blackBox);
  }

}









