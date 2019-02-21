-- 수업 테이블 삭제 
drop table if exists lms_lesson;

-- 회원 테이블 삭제
drop table if exists lms_member;

-- 게시판 테이블 삭제 
drop table if exists lms_board;

-- 수업 테이블 생성
create table lms_lesson (
  lesson_id, -- 수업 데이터 식별 번호
  titl,      -- 수업명
  conts,     -- 수업 내용
  sdt,       -- 수업 시작일
  edt,       -- 수업 종료일
  tot_hr,    -- 총 수업 시간
  day_hr     -- 일 수업 시간
); 








