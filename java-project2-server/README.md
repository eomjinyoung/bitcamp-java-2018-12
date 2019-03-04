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

### ver 4.7.1 - `수업 사진 게시판`에 사진 파일을 첨부하는 기능을 추가하라.

- PhotoFile.java
    - 사진 파일의 데이터 타입을 정의한다.
- PhotoFileDao.java
    - 사진 파일의 CRUD 관련 메서드를 정의한다.
    - 단 사진 파일은 사진 게시물과 함께 사용되기에 변경,상세조회는 제외한다.
- PhotoBoardDao.java
    - 게시글을 등록한 후 자동 생성된 게시물 번호를 리턴하도록 코드를 변경한다.
    - 이 게시글 등록 번호가 있어야만 사진 파일을 등록할 수 있다.
- PhotoBoardAddCommand.java
    - 사진 파일을 다룰 때 사용할 `PhotoFileDao` 객체를 생성자의 파라미터로 주입한다.
    - 사진 게시물에 사진 파일을 첨부하는 기능을 추가한다.
- PhotoBoardDetailCommand.java
    - 사진 파일을 다룰 때 사용할 `PhotoFileDao` 객체를 생성자의 파라미터로 주입한다.
    - 사진 게시물을 출력할 때 첨부된 사진 파일명도 함께 출력한다.
- PhotoBoardUpdateCommand.java
    - 사진 파일을 다룰 때 사용할 `PhotoFileDao` 객체를 생성자의 파라미터로 주입한다.
    - 사진 게시물을 변경할 때 첨부할 사진을 변경한다.
- PhotoBoardDeleteCommand.java
    - 사진 파일을 다룰 때 사용할 `PhotoFileDao` 객체를 생성자의 파라미터로 주입한다.
    - 사진 게시물을 삭제할 때 첨부한 사진 파일도 함께 삭제한다.
- DataLoaderListener.java
    - `PhotoFileDao` 객체를 생성하여 맵 객체에 보관한다.
- App.java
    - 사진 게시물 관련 `Command` 객체를 생성할 때 사진 데이터를 다룰 수 있는 `PhotoFileDao`객체를 주입한다.

##### 실행 결과

`eomcs-java-project-server` 프로젝트의 `App` 클래스를 실행한다.
```
DataLoaderListener.contextInitialized() 실행!
MariaDB에 연결했습니다.
서버 실행!
...
```

`eomcs-java-project-client`프로젝트의 `ClientApp`을 실행한다.
```
명령> /photoboard/list
  1, 수업 오리엔테이션           , 2018-11-14, 0, 1
  2, 1차 과제 발표            , 2018-11-14, 0, 1
  3, null                , 2018-11-14, 0, 2
  4, 과제 발표회              , 2018-11-14, 0, 3
  6, 발표2                 , 2018-11-14, 0, 1
  7, okok2               , 2018-11-14, 0, 2
  8, test1               , 2018-11-15, 0, 2

명령> /photoboard/detail
번호?
7
제목: okok2
작성일: 2018-11-14
조회수: 0
수업: 2
사진 파일:
> aaa1.jpeg
> aaa2.jpeg

명령> /photoboard/add
제목?
ok
수업?
1
최소 한 개의 사진 파일을 등록해야 합니다.
파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.
사진 파일?

최소 한 개의 사진 파일을 등록해야 합니다.
사진 파일?
a1.gif
사진 파일?
a2.gif
사진 파일?
a3.gif
사진 파일?

사진을 저장했습니다.

명령> /photoboard/update
번호?
7
제목(okok2)?
최종 발표
사진 파일:
> aaa1.jpeg
> aaa2.jpeg

사진은 일부만 변경할 수 없습니다.
전체를 새로 등록해야 합니다.
사진을 변경하시겠습니까?(y/N)
y
최소 한 개의 사진 파일을 등록해야 합니다.
파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.
사진 파일?

최소 한 개의 사진 파일을 등록해야 합니다.
사진 파일?
ppt1.jpeg
사진 파일?
pp2.jpeg
사진 파일?
pp3.jpeg
사진 파일?

사진을 변경했습니다.

명령> /photoboard/detail
번호?
7
제목: 최종 발표
작성일: 2018-11-14
조회수: 0
수업: 2
사진 파일:
> ppt1.jpeg
> pp2.jpeg
> pp3.jpeg

명령> /photoboard/delete
번호?
99
해당 사진을 찾을 수 없습니다.

명령> /photoboard/delete
번호?
7
사진을 삭제했습니다.

명령> /photoboard/list
  1, 수업 오리엔테이션           , 2018-11-14, 0, 1
  2, 1차 과제 발표            , 2018-11-14, 0, 1
  3, null                , 2018-11-14, 0, 2
  4, 과제 발표회              , 2018-11-14, 0, 3
  6, 발표2                 , 2018-11-14, 0, 1
  8, test1               , 2018-11-15, 0, 2

명령> 
```


## 실습 소스

- src/main/java/com/eomcs/lms/domain/PhotoFile.java 추가
- src/main/java/com/eomcs/lms/dao/PhotoFileDao.java 추가
- src/main/java/com/eomcs/lms/dao/PhotoBoardDao.java 변경
- src/main/java/com/eomcs/lms/handler/PhotoBoardAddCommand.java 변경
- src/main/java/com/eomcs/lms/handler/PhotoBoardUpdateCommand.java 변경
- src/main/java/com/eomcs/lms/handler/PhotoBoardDetailCommand.java 변경
- src/main/java/com/eomcs/lms/handler/PhotoBoardDeleteCommand.java 변경
- src/main/java/com/eomcs/lms/DataLoaderListener.java 변경
- src/main/java/com/eomcs/lms/App.java 변경
