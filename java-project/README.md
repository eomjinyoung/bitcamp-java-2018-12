# eomcs-java-project-30

직렬화와 역직렬화를 이용하여 객체를 통째로 읽고 쓰기

- 직렬화 하는 방법과 역직렬화 하는 방법
- `java.io.Serializable` 인터페이스와 `serialVersionUID` 스태틱 변수 
  
## 프로젝트 - 수업관리 시스템  

### 과제 1: 객체 단위로 읽고 출력하라.

- Lesson.java
    - `java.io.Serializable` 인터페이스를 구현한다.
    - `serialVersionUID` 스태틱 변수의 값을 설정한다.
- Member.java
    - `java.io.Serializable` 인터페이스를 구현한다.
    - `serialVersionUID` 스태틱 변수의 값을 설정한다.
- Board.java
    - `java.io.Serializable` 인터페이스를 구현한다.
    - `serialVersionUID` 스태틱 변수의 값을 설정한다.
- App.java (App.java.01)
    - 파일에서 데이터를 읽을 때 ObjectInputStream을 사용한다.
    - 파일에서 데이터를 쓸 때 ObjectOutputStream을 사용한다.

#### 실행 결과

`App`의 실행 결과는 이전 버전과 같다.

### 과제 2: ArrayList/LinkedList 객체를 통째로 읽고 출력하라.

- App.java
    - `java.io.Serializable` 구현체라면 직렬화/역직렬화가 가능하다.
    - 따라서 ArrayList, LinkedList 객체를 통째로 읽고 쓸 수 있다.

#### 실행 결과

`App`의 실행 결과는 이전 버전과 같다.

## 실습 소스

- src/main/java/com/eomcs/lms/App.java 변경
- src/main/java/com/eomcs/lms/domain/Lesson.java 변경
- src/main/java/com/eomcs/lms/domain/Member.java 변경
- src/main/java/com/eomcs/lms/domain/Board.java 변경