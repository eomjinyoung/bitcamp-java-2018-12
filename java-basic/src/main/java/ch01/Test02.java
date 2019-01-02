// 자바 패키지 - 패키지 소속 클래스

// 클래스를 패키지에 소속시키려면 다음 명령을 소스 파일의 첫 명령으로 작성해야 한다.
// package 패키지명.패키지명.패키지명;
package ch01; // 이 소스 파일에 작성하는 모든 클래스는 ch01 패키지에 소속된다.

public class Test02 {
    public static void main(String[] args) {
        System.out.println("Hello!");
    }
}

/*
# 패키지 소속 클래스 컴파일
- 일반 클래스를 컴파일 하는 것과 같다.
- 다만 컴파일로 생성된 .class 파일은 패키지 이름에 해당하는 폴더에 놓인다.

  java-basic$ javac -d bin src/main/java/ch01/Test02.java

'''
java-basic/
  bin/
    ch01/
      Test02.class
  src/
'''

# 패키지 소속 클래스 실행
- 실행할 때 반드시 패키지 이름을 지정해야 한다.
  $ java -cp bin ch01.Test02
  $ java -cp bin ch01/Test02  <== . 대신 /를 사용해도 된다. 비추!

  $ java -cp bin/ch01 Test02  <== 실행 오류!


# 패키지 소속 클래스의 소스 파일 관리
- 소스 파일도 자신이 소속된 패키지 폴더에 놓아라!
- 그래야 소스 파일을 관리하기 쉽다.
- 패키지는 디렉토리로 표현한다.

예) a.b.c.Hello 
    ==> src/main/java/a/b/c/Hello.java
    ==> bin/a/b/c/Hello.class
    ==> $ java -cp bin a.b.c.Hello

*/