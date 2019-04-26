-- 수강신청
DROP TABLE IF EXISTS LECT_APPY RESTRICT;

-- 강의
DROP TABLE IF EXISTS LECT RESTRICT;

-- 강사
DROP TABLE IF EXISTS TCHER RESTRICT;

-- 매니저
DROP TABLE IF EXISTS MGR RESTRICT;

-- 강의실
DROP TABLE IF EXISTS CROOM RESTRICT;

-- 학생
DROP TABLE IF EXISTS STUD RESTRICT;

-- 강의실사진
DROP TABLE IF EXISTS CROOM_PHOT RESTRICT;

-- 강사사진
DROP TABLE IF EXISTS TCH_PHOT RESTRICT;

-- 강사배정
DROP TABLE IF EXISTS TCHR_LECT RESTRICT;

-- 회원
DROP TABLE IF EXISTS MEMB RESTRICT;

-- 코드조각
DROP TABLE IF EXISTS CODE RESTRICT;

-- 콘텐츠
DROP TABLE IF EXISTS CONTENT RESTRICT;

-- 게시판
DROP TABLE IF EXISTS BOARD RESTRICT;

-- 피드
DROP TABLE IF EXISTS FEED RESTRICT;

-- 태그
DROP TABLE IF EXISTS TAG RESTRICT;

-- 팔로잉
DROP TABLE IF EXISTS FOLLOW RESTRICT;

-- 프로젝트
DROP TABLE IF EXISTS PROJ RESTRICT;

-- 프로젝트회원
DROP TABLE IF EXISTS PROJ_MEMB RESTRICT;

-- 자료실
DROP TABLE IF EXISTS DOWNLOAD RESTRICT;

-- 할일
DROP TABLE IF EXISTS TODO RESTRICT;

-- 콘텐츠파일링크
DROP TABLE IF EXISTS FIL_LK RESTRICT;

-- 수강신청
CREATE TABLE LECT_APPY (
	LANO INTEGER  NOT NULL COMMENT '수강신청일련번호', -- 수강신청일련번호
	LNO  INTEGER  NOT NULL COMMENT '강의일련번호', -- 강의일련번호
	SNO  INTEGER  NOT NULL COMMENT '학생일련번호', -- 학생일련번호
	RDT  DATETIME NOT NULL COMMENT '신청일', -- 신청일
	STAT INTEGER  NULL     COMMENT '상태' -- 상태
)
COMMENT '수강신청';

-- 수강신청
ALTER TABLE LECT_APPY
	ADD CONSTRAINT PK_LECT_APPY -- 수강신청 Primary key
		PRIMARY KEY (
			LANO -- 수강신청일련번호
		);

-- 수강신청 Unique Index
CREATE UNIQUE INDEX UIX_LECT_APPY
	ON LECT_APPY ( -- 수강신청
		LNO ASC, -- 강의일련번호
		SNO ASC  -- 학생일련번호
	);

ALTER TABLE LECT_APPY
	MODIFY COLUMN LANO INTEGER NOT NULL AUTO_INCREMENT COMMENT '수강신청일련번호';

-- 강의
CREATE TABLE LECT (
	LNO   INTEGER      NOT NULL COMMENT '강의일련번호', -- 강의일련번호
	CRMNO INTEGER      NULL     COMMENT '강의실일련번호', -- 강의실일련번호
	MRNO  INTEGER      NULL     COMMENT '매니저일련번호', -- 매니저일련번호
	TITL  VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
	DSCP  TEXT         NOT NULL COMMENT '설명', -- 설명
	SDT   DATE         NOT NULL COMMENT '시작일', -- 시작일
	EDT   DATE         NOT NULL COMMENT '종료일', -- 종료일
	QTY   INTEGER      NOT NULL COMMENT '수강가능인원', -- 수강가능인원
	PRIC  INTEGER      NULL     COMMENT '수업료', -- 수업료
	THRS  INTEGER      NOT NULL COMMENT '총시간' -- 총시간
)
COMMENT '강의';

-- 강의
ALTER TABLE LECT
	ADD CONSTRAINT PK_LECT -- 강의 Primary key
		PRIMARY KEY (
			LNO -- 강의일련번호
		);

-- 강의 Index
CREATE INDEX IX_LECT
	ON LECT( -- 강의
		TITL ASC -- 제목
	);

ALTER TABLE LECT
	MODIFY COLUMN LNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '강의일련번호';

-- 강사
CREATE TABLE TCHER (
	TNO  INTEGER      NOT NULL COMMENT '강사일련번호', -- 강사일련번호
	HMPG VARCHAR(255) NULL     COMMENT '홈페이지', -- 홈페이지
	FCBK VARCHAR(255) NULL     COMMENT '페이스북', -- 페이스북
	TWIT VARCHAR(255) NULL     COMMENT '트위터' -- 트위터
)
COMMENT '강사';

-- 강사
ALTER TABLE TCHER
	ADD CONSTRAINT PK_TCHER -- 강사 Primary key
		PRIMARY KEY (
			TNO -- 강사일련번호
		);

-- 매니저
CREATE TABLE MGR (
	MRNO INTEGER      NOT NULL COMMENT '매니저일련번호', -- 매니저일련번호
	POSI VARCHAR(100) NULL     COMMENT '직급', -- 직급
	FAX  VARCHAR(30)  NULL     COMMENT '팩스', -- 팩스
	PATH VARCHAR(255) NOT NULL COMMENT '사진파일경로' -- 사진파일경로
)
COMMENT '매니저';

-- 매니저
ALTER TABLE MGR
	ADD CONSTRAINT PK_MGR -- 매니저 Primary key
		PRIMARY KEY (
			MRNO -- 매니저일련번호
		);

-- 강의실
CREATE TABLE CROOM (
	CRMNO INTEGER      NOT NULL COMMENT '강의실일련번호', -- 강의실일련번호
	NAME  VARCHAR(100) NOT NULL COMMENT '이름' -- 이름
)
COMMENT '강의실';

-- 강의실
ALTER TABLE CROOM
	ADD CONSTRAINT PK_CROOM -- 강의실 Primary key
		PRIMARY KEY (
			CRMNO -- 강의실일련번호
		);

-- 강의실 Unique Index
CREATE UNIQUE INDEX UIX_CROOM
	ON CROOM ( -- 강의실
		NAME ASC -- 이름
	);

ALTER TABLE CROOM
	MODIFY COLUMN CRMNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '강의실일련번호';

-- 학생
CREATE TABLE STUD (
	SNO      INTEGER      NOT NULL COMMENT '학생일련번호', -- 학생일련번호
	WORK     CHAR(1)      NOT NULL COMMENT '재직여부', -- 재직여부
	LST_SCHL VARCHAR(100) NULL     COMMENT '최종학력', -- 최종학력
	SCHL_NM  VARCHAR(100) NULL     COMMENT '학교명', -- 학교명
	PST_NO   VARCHAR(10)  NULL     COMMENT '우편번호', -- 우편번호
	BAS_ADR  VARCHAR(255) NULL     COMMENT '기본주소', -- 기본주소
	DET_ADR  VARCHAR(255) NULL     COMMENT '상세주소', -- 상세주소
	PATH     VARCHAR(255) NULL     COMMENT '사진파일경로' -- 사진파일경로
)
COMMENT '학생';

-- 학생
ALTER TABLE STUD
	ADD CONSTRAINT PK_STUD -- 학생 Primary key
		PRIMARY KEY (
			SNO -- 학생일련번호
		);

-- 강의실사진
CREATE TABLE CROOM_PHOT (
	CPNO  INTEGER      NOT NULL COMMENT '강의실사진일련번호', -- 강의실사진일련번호
	CRMNO INTEGER      NOT NULL COMMENT '강의실일련번호', -- 강의실일련번호
	PATH  VARCHAR(255) NOT NULL COMMENT '파일경로' -- 파일경로
)
COMMENT '강의실사진';

-- 강의실사진
ALTER TABLE CROOM_PHOT
	ADD CONSTRAINT PK_CROOM_PHOT -- 강의실사진 Primary key
		PRIMARY KEY (
			CPNO -- 강의실사진일련번호
		);

ALTER TABLE CROOM_PHOT
	MODIFY COLUMN CPNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '강의실사진일련번호';

-- 강사사진
CREATE TABLE TCH_PHOT (
	TPNO INTEGER      NOT NULL COMMENT '강사사진일련번호', -- 강사사진일련번호
	TNO  INTEGER      NOT NULL COMMENT '강사일련번호', -- 강사일련번호
	PATH VARCHAR(255) NOT NULL COMMENT '파일경로' -- 파일경로
)
COMMENT '강사사진';

-- 강사사진
ALTER TABLE TCH_PHOT
	ADD CONSTRAINT PK_TCH_PHOT -- 강사사진 Primary key
		PRIMARY KEY (
			TPNO -- 강사사진일련번호
		);

ALTER TABLE TCH_PHOT
	MODIFY COLUMN TPNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '강사사진일련번호';

-- 강사배정
CREATE TABLE TCHR_LECT (
	LNO INTEGER NOT NULL COMMENT '강의일련번호', -- 강의일련번호
	TNO INTEGER NOT NULL COMMENT '강사일련번호' -- 강사일련번호
)
COMMENT '강사배정';

-- 강사배정
ALTER TABLE TCHR_LECT
	ADD CONSTRAINT PK_TCHR_LECT -- 강사배정 Primary key
		PRIMARY KEY (
			LNO, -- 강의일련번호
			TNO  -- 강사일련번호
		);

-- 회원
CREATE TABLE MEMB (
	MNO   INTEGER      NOT NULL COMMENT '회원일련번호', -- 회원일련번호
	NAME  VARCHAR(100) NOT NULL COMMENT '이름', -- 이름
	TEL   VARCHAR(30)  NOT NULL COMMENT '전화', -- 전화
	EMAIL VARCHAR(40)  NOT NULL COMMENT '이메일', -- 이메일
	PWD   VARCHAR(50)  NOT NULL COMMENT '암호' -- 암호
)
COMMENT '회원';

-- 회원
ALTER TABLE MEMB
	ADD CONSTRAINT PK_MEMB -- 회원 Primary key
		PRIMARY KEY (
			MNO -- 회원일련번호
		);

-- 회원 Unique Index
CREATE UNIQUE INDEX UIX_MEMB
	ON MEMB ( -- 회원
		EMAIL ASC -- 이메일
	);

-- 회원 Index
CREATE INDEX IX_MEMB
	ON MEMB( -- 회원
		NAME ASC -- 이름
	);

ALTER TABLE MEMB
	MODIFY COLUMN MNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원일련번호';

-- 코드조각
CREATE TABLE CODE (
	CDNO  INTEGER     NOT NULL COMMENT '콘텐츠일련번호', -- 콘텐츠일련번호
	CONTS TEXT        NOT NULL COMMENT '코드', -- 코드
	PL    VARCHAR(10) NULL     COMMENT '프로그래밍언어' -- 프로그래밍언어
)
COMMENT '코드조각';

-- 코드조각
ALTER TABLE CODE
	ADD CONSTRAINT PK_CODE -- 코드조각 Primary key
		PRIMARY KEY (
			CDNO -- 콘텐츠일련번호
		);

-- 콘텐츠
CREATE TABLE CONTENT (
	CONO   INTEGER  NOT NULL COMMENT '콘텐츠일련번호', -- 콘텐츠일련번호
	MNO    INTEGER  NOT NULL COMMENT '회원일련번호', -- 회원일련번호
	RDT    DATETIME NOT NULL COMMENT '등록일', -- 등록일
	VW_CNT INTEGER  NOT NULL COMMENT '조회수' -- 조회수
)
COMMENT '콘텐츠';

-- 콘텐츠
ALTER TABLE CONTENT
	ADD CONSTRAINT PK_CONTENT -- 콘텐츠 Primary key
		PRIMARY KEY (
			CONO -- 콘텐츠일련번호
		);

ALTER TABLE CONTENT
	MODIFY COLUMN CONO INTEGER NOT NULL AUTO_INCREMENT COMMENT '콘텐츠일련번호';

-- 게시판
CREATE TABLE BOARD (
	BDNO  INTEGER      NOT NULL COMMENT '콘텐츠일련번호', -- 콘텐츠일련번호
	TITL  VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
	CONTS TEXT         NOT NULL COMMENT '내용' -- 내용
)
COMMENT '게시판';

-- 게시판
ALTER TABLE BOARD
	ADD CONSTRAINT PK_BOARD -- 게시판 Primary key
		PRIMARY KEY (
			BDNO -- 콘텐츠일련번호
		);

-- 피드
CREATE TABLE FEED (
	FDNO  INTEGER NOT NULL COMMENT '콘텐츠일련번호', -- 콘텐츠일련번호
	CONTS TEXT    NOT NULL COMMENT '피드내용' -- 피드내용
)
COMMENT '피드';

-- 피드
ALTER TABLE FEED
	ADD CONSTRAINT PK_FEED -- 피드 Primary key
		PRIMARY KEY (
			FDNO -- 콘텐츠일련번호
		);

-- 태그
CREATE TABLE TAG (
	TGNO   INTEGER     NOT NULL COMMENT '태그일련번호', -- 태그일련번호
	CONO   INTEGER     NOT NULL COMMENT '콘텐츠일련번호', -- 콘텐츠일련번호
	TAG_NM VARCHAR(50) NOT NULL COMMENT '태그명' -- 태그명
)
COMMENT '태그';

-- 태그
ALTER TABLE TAG
	ADD CONSTRAINT PK_TAG -- 태그 Primary key
		PRIMARY KEY (
			TGNO -- 태그일련번호
		);

-- 태그 Index
CREATE INDEX IX_TAG
	ON TAG( -- 태그
		TAG_NM ASC -- 태그명
	);

ALTER TABLE TAG
	MODIFY COLUMN TGNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '태그일련번호';

-- 팔로잉
CREATE TABLE FOLLOW (
	FOWR_NO INTEGER NOT NULL COMMENT '회원일련번호', -- 회원일련번호
	FWNG_NO INTEGER NOT NULL COMMENT '팔로잉대상회원일련번호' -- 팔로잉대상회원일련번호
)
COMMENT '팔로잉';

-- 팔로잉
ALTER TABLE FOLLOW
	ADD CONSTRAINT PK_FOLLOW -- 팔로잉 Primary key
		PRIMARY KEY (
			FOWR_NO, -- 회원일련번호
			FWNG_NO  -- 팔로잉대상회원일련번호
		);

-- 프로젝트
CREATE TABLE PROJ (
	PJNO  INTEGER      NOT NULL COMMENT '콘텐츠일련번호', -- 콘텐츠일련번호
	TITL  VARCHAR(255) NOT NULL COMMENT '프로젝트명', -- 프로젝트명
	CONTS TEXT         NOT NULL COMMENT '내용', -- 내용
	SDT   DATE         NOT NULL COMMENT '시작일', -- 시작일
	EDT   DATE         NOT NULL COMMENT '종료일', -- 종료일
	PATH  VARCHAR(255) NULL     COMMENT '로고' -- 로고
)
COMMENT '프로젝트';

-- 프로젝트
ALTER TABLE PROJ
	ADD CONSTRAINT PK_PROJ -- 프로젝트 Primary key
		PRIMARY KEY (
			PJNO -- 콘텐츠일련번호
		);

-- 프로젝트 Index
CREATE INDEX IX_PROJ
	ON PROJ( -- 프로젝트
		TITL ASC -- 프로젝트명
	);

-- 프로젝트회원
CREATE TABLE PROJ_MEMB (
	MNO  INTEGER     NOT NULL COMMENT '회원일련번호', -- 회원일련번호
	PJNO INTEGER     NOT NULL COMMENT '콘텐츠일련번호', -- 콘텐츠일련번호
	ROL  VARCHAR(10) NULL     COMMENT '역할' -- 역할
)
COMMENT '프로젝트회원';

-- 프로젝트회원
ALTER TABLE PROJ_MEMB
	ADD CONSTRAINT PK_PROJ_MEMB -- 프로젝트회원 Primary key
		PRIMARY KEY (
			MNO,  -- 회원일련번호
			PJNO  -- 콘텐츠일련번호
		);

-- 자료실
CREATE TABLE DOWNLOAD (
	DNNO INTEGER      NOT NULL COMMENT '콘텐츠일련번호', -- 콘텐츠일련번호
	PATH VARCHAR(255) NOT NULL COMMENT '파일경로' -- 파일경로
)
COMMENT '자료실';

-- 자료실
ALTER TABLE DOWNLOAD
	ADD CONSTRAINT PK_DOWNLOAD -- 자료실 Primary key
		PRIMARY KEY (
			DNNO -- 콘텐츠일련번호
		);

-- 할일
CREATE TABLE TODO (
	TDNO  INTEGER      NOT NULL COMMENT '할일일련번호', -- 할일일련번호
	MNO   INTEGER      NULL     COMMENT '회원일련번호', -- 회원일련번호
	SEQ   INTEGER      NOT NULL COMMENT '순서', -- 순서
	CONTS VARCHAR(255) NOT NULL COMMENT '내용', -- 내용
	STAT  VARCHAR(10)  NULL     COMMENT '상태', -- 상태
	STDT  DATETIME     NULL     COMMENT '상태설정일', -- 상태설정일
	PJNO  INTEGER      NULL     COMMENT '콘텐츠일련번호' -- 콘텐츠일련번호
)
COMMENT '할일';

-- 할일
ALTER TABLE TODO
	ADD CONSTRAINT PK_TODO -- 할일 Primary key
		PRIMARY KEY (
			TDNO -- 할일일련번호
		);

-- 콘텐츠파일링크
CREATE TABLE FIL_LK (
	CONO INTEGER NOT NULL COMMENT '콘텐츠일련번호', -- 콘텐츠일련번호
	DNNO INTEGER NOT NULL COMMENT '자료실파일일련번호' -- 자료실파일일련번호
)
COMMENT '콘텐츠파일링크';

-- 콘텐츠파일링크
ALTER TABLE FIL_LK
	ADD CONSTRAINT PK_FIL_LK -- 콘텐츠파일링크 Primary key
		PRIMARY KEY (
			CONO, -- 콘텐츠일련번호
			DNNO  -- 자료실파일일련번호
		);

-- 수강신청
ALTER TABLE LECT_APPY
	ADD CONSTRAINT FK_LECT_TO_LECT_APPY -- 강의 -> 수강신청
		FOREIGN KEY (
			LNO -- 강의일련번호
		)
		REFERENCES LECT ( -- 강의
			LNO -- 강의일련번호
		);

-- 수강신청
ALTER TABLE LECT_APPY
	ADD CONSTRAINT FK_STUD_TO_LECT_APPY -- 학생 -> 수강신청
		FOREIGN KEY (
			SNO -- 학생일련번호
		)
		REFERENCES STUD ( -- 학생
			SNO -- 학생일련번호
		);

-- 강의
ALTER TABLE LECT
	ADD CONSTRAINT FK_MGR_TO_LECT -- 매니저 -> 강의
		FOREIGN KEY (
			MRNO -- 매니저일련번호
		)
		REFERENCES MGR ( -- 매니저
			MRNO -- 매니저일련번호
		);

-- 강의
ALTER TABLE LECT
	ADD CONSTRAINT FK_CROOM_TO_LECT -- 강의실 -> 강의
		FOREIGN KEY (
			CRMNO -- 강의실일련번호
		)
		REFERENCES CROOM ( -- 강의실
			CRMNO -- 강의실일련번호
		);

-- 강사
ALTER TABLE TCHER
	ADD CONSTRAINT FK_MEMB_TO_TCHER -- 회원 -> 강사
		FOREIGN KEY (
			TNO -- 강사일련번호
		)
		REFERENCES MEMB ( -- 회원
			MNO -- 회원일련번호
		);

-- 매니저
ALTER TABLE MGR
	ADD CONSTRAINT FK_MEMB_TO_MGR -- 회원 -> 매니저
		FOREIGN KEY (
			MRNO -- 매니저일련번호
		)
		REFERENCES MEMB ( -- 회원
			MNO -- 회원일련번호
		);

-- 학생
ALTER TABLE STUD
	ADD CONSTRAINT FK_MEMB_TO_STUD -- 회원 -> 학생
		FOREIGN KEY (
			SNO -- 학생일련번호
		)
		REFERENCES MEMB ( -- 회원
			MNO -- 회원일련번호
		);

-- 강의실사진
ALTER TABLE CROOM_PHOT
	ADD CONSTRAINT FK_CROOM_TO_CROOM_PHOT -- 강의실 -> 강의실사진
		FOREIGN KEY (
			CRMNO -- 강의실일련번호
		)
		REFERENCES CROOM ( -- 강의실
			CRMNO -- 강의실일련번호
		);

-- 강사사진
ALTER TABLE TCH_PHOT
	ADD CONSTRAINT FK_TCHER_TO_TCH_PHOT -- 강사 -> 강사사진
		FOREIGN KEY (
			TNO -- 강사일련번호
		)
		REFERENCES TCHER ( -- 강사
			TNO -- 강사일련번호
		);

-- 강사배정
ALTER TABLE TCHR_LECT
	ADD CONSTRAINT FK_TCHER_TO_TCHR_LECT -- 강사 -> 강사배정
		FOREIGN KEY (
			TNO -- 강사일련번호
		)
		REFERENCES TCHER ( -- 강사
			TNO -- 강사일련번호
		);

-- 강사배정
ALTER TABLE TCHR_LECT
	ADD CONSTRAINT FK_LECT_TO_TCHR_LECT -- 강의 -> 강사배정
		FOREIGN KEY (
			LNO -- 강의일련번호
		)
		REFERENCES LECT ( -- 강의
			LNO -- 강의일련번호
		);

-- 코드조각
ALTER TABLE CODE
	ADD CONSTRAINT FK_CONTENT_TO_CODE -- 콘텐츠 -> 코드조각
		FOREIGN KEY (
			CDNO -- 콘텐츠일련번호
		)
		REFERENCES CONTENT ( -- 콘텐츠
			CONO -- 콘텐츠일련번호
		);

-- 콘텐츠
ALTER TABLE CONTENT
	ADD CONSTRAINT FK_MEMB_TO_CONTENT -- 회원 -> 콘텐츠
		FOREIGN KEY (
			MNO -- 회원일련번호
		)
		REFERENCES MEMB ( -- 회원
			MNO -- 회원일련번호
		);

-- 게시판
ALTER TABLE BOARD
	ADD CONSTRAINT FK_CONTENT_TO_BOARD -- 콘텐츠 -> 게시판
		FOREIGN KEY (
			BDNO -- 콘텐츠일련번호
		)
		REFERENCES CONTENT ( -- 콘텐츠
			CONO -- 콘텐츠일련번호
		);

-- 피드
ALTER TABLE FEED
	ADD CONSTRAINT FK_CONTENT_TO_FEED -- 콘텐츠 -> 피드
		FOREIGN KEY (
			FDNO -- 콘텐츠일련번호
		)
		REFERENCES CONTENT ( -- 콘텐츠
			CONO -- 콘텐츠일련번호
		);

-- 태그
ALTER TABLE TAG
	ADD CONSTRAINT FK_CONTENT_TO_TAG -- 콘텐츠 -> 태그
		FOREIGN KEY (
			CONO -- 콘텐츠일련번호
		)
		REFERENCES CONTENT ( -- 콘텐츠
			CONO -- 콘텐츠일련번호
		);

-- 팔로잉
ALTER TABLE FOLLOW
	ADD CONSTRAINT FK_MEMB_TO_FOLLOW -- 회원 -> 팔로잉
		FOREIGN KEY (
			FOWR_NO -- 회원일련번호
		)
		REFERENCES MEMB ( -- 회원
			MNO -- 회원일련번호
		);

-- 팔로잉
ALTER TABLE FOLLOW
	ADD CONSTRAINT FK_MEMB_TO_FOLLOW2 -- 회원 -> 팔로잉2
		FOREIGN KEY (
			FWNG_NO -- 팔로잉대상회원일련번호
		)
		REFERENCES MEMB ( -- 회원
			MNO -- 회원일련번호
		);

-- 프로젝트
ALTER TABLE PROJ
	ADD CONSTRAINT FK_CONTENT_TO_PROJ -- 콘텐츠 -> 프로젝트
		FOREIGN KEY (
			PJNO -- 콘텐츠일련번호
		)
		REFERENCES CONTENT ( -- 콘텐츠
			CONO -- 콘텐츠일련번호
		);

-- 프로젝트회원
ALTER TABLE PROJ_MEMB
	ADD CONSTRAINT FK_PROJ_TO_PROJ_MEMB -- 프로젝트 -> 프로젝트회원
		FOREIGN KEY (
			PJNO -- 콘텐츠일련번호
		)
		REFERENCES PROJ ( -- 프로젝트
			PJNO -- 콘텐츠일련번호
		);

-- 프로젝트회원
ALTER TABLE PROJ_MEMB
	ADD CONSTRAINT FK_MEMB_TO_PROJ_MEMB -- 회원 -> 프로젝트회원
		FOREIGN KEY (
			MNO -- 회원일련번호
		)
		REFERENCES MEMB ( -- 회원
			MNO -- 회원일련번호
		);

-- 자료실
ALTER TABLE DOWNLOAD
	ADD CONSTRAINT FK_CONTENT_TO_DOWNLOAD -- 콘텐츠 -> 자료실
		FOREIGN KEY (
			DNNO -- 콘텐츠일련번호
		)
		REFERENCES CONTENT ( -- 콘텐츠
			CONO -- 콘텐츠일련번호
		);

-- 할일
ALTER TABLE TODO
	ADD CONSTRAINT FK_PROJ_MEMB_TO_TODO -- 프로젝트회원 -> 할일
		FOREIGN KEY (
			MNO,  -- 회원일련번호
			PJNO  -- 콘텐츠일련번호
		)
		REFERENCES PROJ_MEMB ( -- 프로젝트회원
			MNO,  -- 회원일련번호
			PJNO  -- 콘텐츠일련번호
		);

-- 할일
ALTER TABLE TODO
	ADD CONSTRAINT FK_CONTENT_TO_TODO -- 콘텐츠 -> 할일
		FOREIGN KEY (
			TDNO -- 할일일련번호
		)
		REFERENCES CONTENT ( -- 콘텐츠
			CONO -- 콘텐츠일련번호
		);

-- 콘텐츠파일링크
ALTER TABLE FIL_LK
	ADD CONSTRAINT FK_CONTENT_TO_FIL_LK -- 콘텐츠 -> 콘텐츠파일링크
		FOREIGN KEY (
			CONO -- 콘텐츠일련번호
		)
		REFERENCES CONTENT ( -- 콘텐츠
			CONO -- 콘텐츠일련번호
		);

-- 콘텐츠파일링크
ALTER TABLE FIL_LK
	ADD CONSTRAINT FK_DOWNLOAD_TO_FIL_LK -- 자료실 -> 콘텐츠파일링크
		FOREIGN KEY (
			DNNO -- 자료실파일일련번호
		)
		REFERENCES DOWNLOAD ( -- 자료실
			DNNO -- 콘텐츠일련번호
		);
