-- 수업 예제 데이터 
insert into lms_lesson(titl, conts, sdt, edt, tot_hr, day_hr)
values('자바 프로그래밍', '자바 프로그래밍 배우기', '2019-1-1', '2019-2-28', 200, 6);
insert into lms_lesson(titl, conts, sdt, edt, tot_hr, day_hr)
values('C/C++ 프로그래밍', '프로그래밍 배우기', '2019-2-1', '2019-3-28', 200, 6);
insert into lms_lesson(titl, conts, sdt, edt, tot_hr, day_hr)
values('파이썬 프로그래밍', '프로그래밍 배우기', '2019-3-1', '2019-4-28', 300, 7);
insert into lms_lesson(titl, conts, sdt, edt, tot_hr, day_hr)
values('웹 프로그래밍', '프로그래밍 배우기', '2019-1-4', '2019-5-28', 300, 7);
insert into lms_lesson(titl, conts, sdt, edt, tot_hr, day_hr)
values('IoT 프로그래밍', '프로그래밍 배우기', '2019-5-1', '2019-6-28', 400, 8);

-- 회원 예제 데이터
insert into lms_member(name, email, pwd) 
values('user1', 'user1@test.com', password('1111'));
insert into lms_member(name, email, pwd) 
values('user2', 'user2@test.com', password('1111'));
insert into lms_member(name, email, pwd) 
values('user3', 'user3@test.com', password('1111'));

-- 게시물 예제 데이터
insert into lms_board(conts) values('내용1');
insert into lms_board(conts) values('내용2');
insert into lms_board(conts) values('내용3');
insert into lms_board(conts) values('내용4');
insert into lms_board(conts) values('내용5');












