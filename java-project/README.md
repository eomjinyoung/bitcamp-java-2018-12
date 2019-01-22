# 20 - 배열 대신 연결 리스트 자료구조 사용하기

## 학습 목표

- 연결 리스트(linked list) 자료구조를 구현할 수 있다.
- 연결 리스트의 구동 원리를 이해한다.
- 배열 방식과 연결 리스트의 장단점을 안다.
- 중첩 클래스의 활용할 수 있다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/util/LinkedList.java 추가
- src/main/java/com/eomcs/lms/handler/LessonHandler.java 변경
- src/main/java/com/eomcs/lms/handler/MemberHandler.java 변경
- src/main/java/com/eomcs/lms/handler/BoardHandler.java 변경

## 실습

### 훈련1. 연결 리스트 자료구조를 구현하라.

- LinkedList.java.01
    - 연결 리스트 자료 구조를 구현한 클래스를 정의한다.


### 훈련2. LinkedList 클래스에 제네릭을 적용하라.

- LinkedList.java
    - ArrayList처럼 특정 타입의 값을 다루도록 제네릭을 적용한다.


### 훈련3. 핸들러 클래스는 ArrayList 대신 LinkedList를 사용하라.

- LessonHandler.java
    - 수업 데이터를 저장하는 ArrayList를 LinkedList로 교체한다.
- MemberHandler.java
    - 회원 데이터를 저장하는 ArrayList를 LinkedList로 교체한다.
- BoardHandler.java
    - 게시글 데이터를 저장하는 ArrayList를 LinkedList로 교체한다.
