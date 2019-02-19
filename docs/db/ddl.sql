-- 수업
DROP TABLE IF EXISTS LESSON RESTRICT;

-- 회원
DROP TABLE IF EXISTS MEMBER RESTRICT;

-- 수강생
DROP TABLE IF EXISTS LES_MEMB RESTRICT;

-- 사진게시판
DROP TABLE IF EXISTS PHOTO RESTRICT;

-- 수업자료
DROP TABLE IF EXISTS LES_FILE RESTRICT;

-- 게시판
DROP TABLE IF EXISTS BOARD RESTRICT;

-- 댓글
DROP TABLE IF EXISTS COMMENT RESTRICT;

-- 사진파일
DROP TABLE IF EXISTS PHO_FILE RESTRICT;

-- 수업
CREATE TABLE LESSON (
    LNO    INTEGER      NOT NULL COMMENT '수업번호', -- 수업번호
    TITLE  VARCHAR(255) NOT NULL COMMENT '수업명', -- 수업명
    CONT   TEXT         NOT NULL COMMENT '수업내용', -- 수업내용
    SDT    DATE         NOT NULL COMMENT '시작일', -- 시작일
    EDT    DATE         NOT NULL COMMENT '종료일', -- 종료일
    TOT_HR INTEGER      NOT NULL COMMENT '총수업시간', -- 총수업시간
    DAY_HR INTEGER      NOT NULL COMMENT '일수업시간', -- 일수업시간
    MNO    INTEGER      NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT '수업';

-- 수업
ALTER TABLE LESSON
    ADD CONSTRAINT PK_LESSON -- 수업 기본키
        PRIMARY KEY (
            LNO -- 수업번호
        );

ALTER TABLE LESSON
    MODIFY COLUMN LNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '수업번호';

-- 회원
CREATE TABLE MEMBER (
    MNO   INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
    NAME  VARCHAR(50)  NOT NULL COMMENT '이름', -- 이름
    EMAIL VARCHAR(40)  NOT NULL COMMENT '이메일', -- 이메일
    PWD   VARCHAR(100) NOT NULL COMMENT '암호', -- 암호
    PHOTO VARCHAR(255) NULL     COMMENT '사진', -- 사진
    TEL   VARCHAR(30)  NULL     COMMENT '전화', -- 전화
    CDT   DATE         NOT NULL DEFAULT current_timestamp COMMENT '가입일' -- 가입일
)
COMMENT '회원';

-- 회원
ALTER TABLE MEMBER
    ADD CONSTRAINT PK_MEMBER -- 회원 기본키
        PRIMARY KEY (
            MNO -- 회원번호
        );

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX UIX_MEMBER
    ON MEMBER ( -- 회원
        EMAIL ASC -- 이메일
    );

ALTER TABLE MEMBER
    MODIFY COLUMN MNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원번호';

-- 수강생
CREATE TABLE LES_MEMB (
    MNO  INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
    LNO  INTEGER NOT NULL COMMENT '수업번호', -- 수업번호
    ROLE CHAR(1) NOT NULL COMMENT '역할' -- 역할
)
COMMENT '수강생';

-- 수강생
ALTER TABLE LES_MEMB
    ADD CONSTRAINT PK_LES_MEMB -- 수강생 기본키
        PRIMARY KEY (
            MNO, -- 회원번호
            LNO  -- 수업번호
        );

-- 사진게시판
CREATE TABLE PHOTO (
    PNO   INTEGER      NOT NULL COMMENT '사진게시물번호', -- 사진게시물번호
    TITLE VARCHAR(255) NULL     COMMENT '제목', -- 제목
    CDT   DATETIME     NOT NULL DEFAULT current_timestamp COMMENT '등록일', -- 등록일
    VIEW  INTEGER      NOT NULL DEFAULT 0 COMMENT '조회수', -- 조회수
    LNO   INTEGER      NOT NULL COMMENT '수업번호' -- 수업번호
)
COMMENT '사진게시판';

-- 사진게시판
ALTER TABLE PHOTO
    ADD CONSTRAINT PK_PHOTO -- 사진게시판 기본키
        PRIMARY KEY (
            PNO -- 사진게시물번호
        );

ALTER TABLE PHOTO
    MODIFY COLUMN PNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '사진게시물번호';

-- 수업자료
CREATE TABLE LES_FILE (
    LFNO INTEGER      NOT NULL COMMENT '수업자료번호', -- 수업자료번호
    PATH VARCHAR(255) NOT NULL COMMENT '파일경로', -- 파일경로
    CONT TEXT         NULL     COMMENT '설명', -- 설명
    CDT  DATETIME     NOT NULL DEFAULT current_timestamp COMMENT '등록일', -- 등록일
    DOWN INTEGER      NOT NULL DEFAULT 0 COMMENT '다운로드수', -- 다운로드수
    LNO  INTEGER      NOT NULL COMMENT '수업번호' -- 수업번호
)
COMMENT '수업자료';

-- 수업자료
ALTER TABLE LES_FILE
    ADD CONSTRAINT PK_LES_FILE -- 수업자료 기본키
        PRIMARY KEY (
            LFNO -- 수업자료번호
        );

-- 게시판
CREATE TABLE BOARD (
    BNO  INTEGER  NOT NULL COMMENT '게시물번호', -- 게시물번호
    CONT TEXT     NOT NULL COMMENT '내용', -- 내용
    CDT  DATETIME NOT NULL DEFAULT current_timestamp COMMENT '등록일', -- 등록일
    VIEW INTEGER  NOT NULL DEFAULT 0 COMMENT '조회수', -- 조회수
    MNO  INTEGER  NOT NULL COMMENT '회원번호', -- 회원번호
    LNO  INTEGER  NOT NULL COMMENT '수업번호' -- 수업번호
)
COMMENT '게시판';

-- 게시판
ALTER TABLE BOARD
    ADD CONSTRAINT PK_BOARD -- 게시판 기본키
        PRIMARY KEY (
            BNO -- 게시물번호
        );

ALTER TABLE BOARD
    MODIFY COLUMN BNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '게시물번호';

-- 댓글
CREATE TABLE COMMENT (
    CNO  INTEGER  NOT NULL COMMENT '댓글번호', -- 댓글번호
    CONT TEXT     NOT NULL COMMENT '내용', -- 내용
    CDT  DATETIME NOT NULL DEFAULT current_timestamp COMMENT '등록일', -- 등록일
    BNO  INTEGER  NOT NULL COMMENT '게시물번호', -- 게시물번호
    MNO  INTEGER  NOT NULL COMMENT '회원번호', -- 회원번호
    LNO  INTEGER  NOT NULL COMMENT '수업번호' -- 수업번호
)
COMMENT '댓글';

-- 댓글
ALTER TABLE COMMENT
    ADD CONSTRAINT PK_COMMENT -- 댓글 기본키
        PRIMARY KEY (
            CNO -- 댓글번호
        );

ALTER TABLE COMMENT
    MODIFY COLUMN CNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '댓글번호';

-- 사진파일
CREATE TABLE PHO_FILE (
    PFNO INTEGER      NOT NULL COMMENT '사진파일번호', -- 사진파일번호
    PATH VARCHAR(255) NOT NULL COMMENT '사진경로', -- 사진경로
    PNO  INTEGER      NOT NULL COMMENT '사진게시물번호' -- 사진게시물번호
)
COMMENT '사진파일';

-- 사진파일
ALTER TABLE PHO_FILE
    ADD CONSTRAINT PK_PHO_FILE -- 사진파일 기본키
        PRIMARY KEY (
            PFNO -- 사진파일번호
        );

ALTER TABLE PHO_FILE
    MODIFY COLUMN PFNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '사진파일번호';

-- 수업
ALTER TABLE LESSON
    ADD CONSTRAINT FK_MEMBER_TO_LESSON -- 회원 -> 수업
        FOREIGN KEY (
            MNO -- 회원번호
        )
        REFERENCES MEMBER ( -- 회원
            MNO -- 회원번호
        );

-- 수강생
ALTER TABLE LES_MEMB
    ADD CONSTRAINT FK_MEMBER_TO_LES_MEMB -- 회원 -> 수강생
        FOREIGN KEY (
            MNO -- 회원번호
        )
        REFERENCES MEMBER ( -- 회원
            MNO -- 회원번호
        );

-- 수강생
ALTER TABLE LES_MEMB
    ADD CONSTRAINT FK_LESSON_TO_LES_MEMB -- 수업 -> 수강생
        FOREIGN KEY (
            LNO -- 수업번호
        )
        REFERENCES LESSON ( -- 수업
            LNO -- 수업번호
        );

-- 사진게시판
ALTER TABLE PHOTO
    ADD CONSTRAINT FK_LESSON_TO_PHOTO -- 수업 -> 사진게시판
        FOREIGN KEY (
            LNO -- 수업번호
        )
        REFERENCES LESSON ( -- 수업
            LNO -- 수업번호
        );

-- 수업자료
ALTER TABLE LES_FILE
    ADD CONSTRAINT FK_LESSON_TO_LES_FILE -- 수업 -> 수업자료
        FOREIGN KEY (
            LNO -- 수업번호
        )
        REFERENCES LESSON ( -- 수업
            LNO -- 수업번호
        );

-- 게시판
ALTER TABLE BOARD
    ADD CONSTRAINT FK_LES_MEMB_TO_BOARD -- 수강생 -> 게시판
        FOREIGN KEY (
            MNO, -- 회원번호
            LNO  -- 수업번호
        )
        REFERENCES LES_MEMB ( -- 수강생
            MNO, -- 회원번호
            LNO  -- 수업번호
        );

-- 댓글
ALTER TABLE COMMENT
    ADD CONSTRAINT FK_BOARD_TO_COMMENT -- 게시판 -> 댓글
        FOREIGN KEY (
            BNO -- 게시물번호
        )
        REFERENCES BOARD ( -- 게시판
            BNO -- 게시물번호
        );

-- 댓글
ALTER TABLE COMMENT
    ADD CONSTRAINT FK_LES_MEMB_TO_COMMENT -- 수강생 -> 댓글
        FOREIGN KEY (
            MNO, -- 회원번호
            LNO  -- 수업번호
        )
        REFERENCES LES_MEMB ( -- 수강생
            MNO, -- 회원번호
            LNO  -- 수업번호
        );

-- 사진파일
ALTER TABLE PHO_FILE
    ADD CONSTRAINT FK_PHOTO_TO_PHO_FILE -- 사진게시판 -> 사진파일
        FOREIGN KEY (
            PNO -- 사진게시물번호
        )
        REFERENCES PHOTO ( -- 사진게시판
            PNO -- 사진게시물번호
        );