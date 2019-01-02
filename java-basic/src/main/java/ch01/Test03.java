// 주석

// 한 줄 주석 - 줄 끝까지 주석으로 취급한다.
package ch01; // 여기서 줄 끝까지 주석이다.

/**
 java doc 주석이라 한다.
 즉 자바 API 문서(HTML)를 생성할 때 참고하는 주석이다.
 클래스, 변수, 메서드 선언에 붙인다.
 */


public class Test03 { // 주석

    /**
        이 메서드는 Object 클래스의 메서드를 재정의한 것이다.
     */
    @Override  // 애노테이션이라 부르는 주석이다. 프로그램에서 사용한다.
    public String toString() {
        return "ok";
    }

    public static void main(String[] args) {
        /* 주석
        주석..
        주석
        */
        System.out.println("Hello!");
    }
}

/*
여러 줄 주석 
주석이다.
주석이다.
가끔 주석을 이쁘게 표현하기 위해 다음과 같이 시작 줄에 *를 쓰는 경우가 있다.
 * 주석이다.
 * 주석이다.
 * 앞에 *는 의미없다. 그냥 꾸미는 것이다.
 */

 /* 
 # javadoc 사용법

   $ javadoc -d [문서를저장할폴더] -sourcepath [소스폴더] 패키지명
   $ javadoc -d doc -sourcepath src/main/java ch01
*/