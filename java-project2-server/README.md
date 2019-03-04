# eomcs-java-project-4.7-server

트랜잭션이 필요한 이유!

- 여러 개의 DB 변경 작업을 한 작업 단위로 묶는 방법
- `commit`과 `rollback`의 의미

## 프로젝트 - 수업관리 시스템  

### ver 4.7.0 - `수업 사진 게시판`을 만들라.

- PhotoBoard.java
    - 사진 게시물의 데이터 타입을 정의한다.
- PhotoBoardDao.java
    - 사진 게시물의 CRUD 관련 메서드를 정의한다.
- PhotoBoardListCommand.java
    - 사진 게시물의 목록을 출력한다.
- PhotoBoardDetailCommand.java
    - 특정 사진 게시물의 상세 정보를 출력한다.
- PhotoBoardAddCommand.java
    - 사진 게시물을 등록한다.
- PhotoBoardUpdateCommand.java
    - 사진 게시물을 변경한다. 
- PhotoBoardDeleteCommand.java
    - 사진 게시물을 삭제한다. 
- DataLoaderListener.java
    - `PhotoBoardDao` 객체를 생성하여 맵 객체에 보관한다.
- App.java
    - 사진 게시물 관련 `Command` 객체를 생성하여 커맨드 맵에 보관한다.


##### 실행 결과

`eomcs-java-project-server` 프로젝트의 `App` 클래스를 실행한다.
```
DataLoaderListener.contextInitialized() 실행!
MariaDB에 연결했습니다.
서버 실행!
클라이언트와 연결됨.
클라이언트와 요청 처리 중...
클라이언트와 요청 처리 중...
클라이언트와 요청 처리 중...
클라이언트와 요청 처리 중...
클라이언트와 요청 처리 중...
클라이언트와 요청 처리 중...
클라이언트와 요청 처리 중...
클라이언트와 요청 처리 중...
클라이언트와 요청 처리 중...
클라이언트와 연결 종료!
```

`eomcs-java-project-client`프로젝트의 `ClientApp`을 실행한다.
```
명령> /photoboard/list
  1, 수업 오리엔테이션           , 2018-11-14, 0, 1
  2, 1차 과제 발표            , 2018-11-14, 0, 1
  3, null                , 2018-11-14, 0, 2
  4, 과제 발표회              , 2018-11-14, 0, 3

명령> /photoboard/add
제목?
test1
수업?
2
사진을 저장했습니다.

명령> /photoboard/detail
번호?
5
해당 사진을 찾을 수 없습니다.

명령> /photoboard/list
  1, 수업 오리엔테이션           , 2018-11-14, 0, 1
  2, 1차 과제 발표            , 2018-11-14, 0, 1
  3, null                , 2018-11-14, 0, 2
  4, 과제 발표회              , 2018-11-14, 0, 3
  6, test1               , 2018-11-14, 0, 2

명령> /photoboard/detail
번호?
6
제목: test1
작성일: 2018-11-14
조회수: 0
수업: 2

명령> /photoboard/update
번호?
6
제목(test1)?
test1...xx
사진을 변경했습니다.

명령> /photoboard/detail
번호?
6
제목: test1...xx
작성일: 2018-11-14
조회수: 0
수업: 2

명령> /photoboard/delete
번호?
6
사진을 삭제했습니다.

명령> /photoboard/list
  1, 수업 오리엔테이션           , 2018-11-14, 0, 1
  2, 1차 과제 발표            , 2018-11-14, 0, 1
  3, null                , 2018-11-14, 0, 2
  4, 과제 발표회              , 2018-11-14, 0, 3

명령> 
```


## 실습 소스

- src/main/java/com/eomcs/lms/domain/PhotoBoard.java 추가
- src/main/java/com/eomcs/lms/dao/PhotoBoardDao.java 추가
- src/main/java/com/eomcs/lms/handler/PhotoBoardListCommand.java 추가
- src/main/java/com/eomcs/lms/handler/PhotoBoardAddCommand.java 추가
- src/main/java/com/eomcs/lms/handler/PhotoBoardUpdateCommand.java 추가
- src/main/java/com/eomcs/lms/handler/PhotoBoardDetailCommand.java 추가
- src/main/java/com/eomcs/lms/handler/PhotoBoardDeleteCommand.java 추가
- src/main/java/com/eomcs/lms/DataLoaderListener.java 변경
- src/main/java/com/eomcs/lms/App.java 변경
