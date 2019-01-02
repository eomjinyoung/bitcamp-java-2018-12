// 컴파일 옵션 실행 옵션
public class Test06 {
    public static void main(String[] args) {
        System.out.println("Hello!");
    }
}

/*
# 컴파일
  $ javac 소스파일명
  => 현재 폴더에 .class 파일이 생성된다.

- 소스 파일과 .class 파일의 관리를 쉽게 하기 위해 디렉토리를 구분한다.
- 컴파일 할 때 .class 파일을 둘 폴더를 지정하라.

  $ javac -d [.class 파일을 둘 폴더 경로] 소스파일명
  java-basic $ javac -d bin src/main/java/Test06.java

# 다른 폴더에 있는 .class 파일 실행
  java-basic$ java -classpath [클래스파일이있는경로] 클래스명
  java-basic$ java -cp [클래스파일이있는경로] 클래스명
  java-basic$ java -classpath bin Test06


*/