# 10 - 클래스로 메서드를 분류하기

## 학습 목표

- 클래스를 이용하여 관련 메서드를 묶어 관리할 수 있다.
- 리팩토링 기법 중에서 '클래스 추출(Extract Class)'을 수행할 수 있다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/App.java 변경
- src/main/java/com/eomcs/lms/LessonHandler.java 추가
- src/main/java/com/eomcs/lms/MemberHandler.java 추가
- src/main/java/com/eomcs/lms/BoardHandler.java 추가

## 실습

### 작업1) 수업 데이터 처리와 관련된 메서드를 별도의 클래스로 분리하라.

- LessonHandler.java
    - 수업 관리와 관련된 메서드를 담을 클래스를 만든다.
    - `App.java` 에서 수업관리와 관련된 변수와 메서드를 `LessonHandler` 클래스로 옮긴다.
- App.java (App.java.01)
    - `LessonHandler` 클래스 사용한다.


### 작업2) 회원 데이터 처리와 관련된 메서드를 별도의 클래스로 분리하라.

- MemberHandler.java
    - 회원 관리와 관련된 메서드를 담을 클래스를 만든다.
    - `App.java` 에서 회원관리와 관련된 변수와 메서드를 `MemberHandler` 클래스로 옮긴다.
- App.java (App.java.02)
    - `MemberHandler` 클래스 사용한다.


### 작업3) 게시물 데이터 처리와 관련된 메서드를 별도의 클래스로 분리하라.

- BoardHandler.java
    - 게시물 관리와 관련된 메서드를 담을 클래스를 만든다.
    - `App.java` 에서 게시물관리와 관련된 변수와 메서드를 `BoardHandler` 클래스로 옮긴다.
- App.java
    - `BoardHandler` 클래스 사용한다.
