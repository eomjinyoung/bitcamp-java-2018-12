-- 회원 데이터
insert into memb(mno,email,pwd,name,tel) values(1,'user01@test.com',password('1111'),'학생1','1111-1111');
insert into memb(mno,email,pwd,name,tel) values(2,'user02@test.com',password('1111'),'학생2','1111-1111');
insert into memb(mno,email,pwd,name,tel) values(3,'user03@test.com',password('1111'),'학생3','1111-1111');
insert into memb(mno,email,pwd,name,tel) values(4,'user04@test.com',password('1111'),'학생4','1111-1111');
insert into memb(mno,email,pwd,name,tel) values(5,'user05@test.com',password('1111'),'학생5','1111-1111');
insert into memb(mno,email,pwd,name,tel) values(6,'user06@test.com',password('1111'),'학생6','1111-1111');
insert into memb(mno,email,pwd,name,tel) values(7,'user07@test.com',password('1111'),'학생7','1111-1111');
insert into memb(mno,email,pwd,name,tel) values(8,'user08@test.com',password('1111'),'학생8','1111-1111');
insert into memb(mno,email,pwd,name,tel) values(9,'user09@test.com',password('1111'),'학생9','1111-1111');
insert into memb(mno,email,pwd,name,tel) values(10,'user10@test.com',password('1111'),'학생10','1111-1111');

insert into memb(mno,email,pwd,name,tel) values(21,'user21@test.com',password('1111'),'강사1','1111-1111');
insert into memb(mno,email,pwd,name,tel) values(22,'user22@test.com',password('1111'),'강사2','1111-1111');
insert into memb(mno,email,pwd,name,tel) values(23,'user23@test.com',password('1111'),'강사3','1111-1111');
insert into memb(mno,email,pwd,name,tel) values(24,'user24@test.com',password('1111'),'강사4','1111-1111');
insert into memb(mno,email,pwd,name,tel) values(25,'user25@test.com',password('1111'),'강사5','1111-1111');

insert into memb(mno,email,pwd,name,tel) values(31,'user31@test.com',password('1111'),'매니저1','1111-1111');
insert into memb(mno,email,pwd,name,tel) values(32,'user32@test.com',password('1111'),'매니저2','1111-1111');
insert into memb(mno,email,pwd,name,tel) values(33,'user33@test.com',password('1111'),'매니저3','1111-1111');

-- 학생 데이터
insert into stud(sno,work,lst_schl,schl_nm) values(1,'Y','학사','비트대학교');
insert into stud(sno,work,lst_schl,schl_nm) values(2,'Y','학사','한국대학교');
insert into stud(sno,work,lst_schl,schl_nm) values(3,'Y','학사','하버드대학교');
insert into stud(sno,work,lst_schl,schl_nm) values(4,'N','고졸','비트고등학교');
insert into stud(sno,work,lst_schl,schl_nm) values(5,'N','고졸','성남고등학교');
insert into stud(sno,work,lst_schl,schl_nm) values(6,'Y','고졸','부산고등학교');
insert into stud(sno,work,lst_schl,schl_nm) values(7,'Y','학사',null);
insert into stud(sno,work,lst_schl,schl_nm) values(8,'N','학사',null);
insert into stud(sno,work,lst_schl,schl_nm) values(9,'Y',null,'비트고등학교');
insert into stud(sno,work,lst_schl,schl_nm) values(10,'N',null,'성남고등학교');

-- 강사 데이터
insert into tcher(tno,hmpg,fcbk,twit) values(21,'http://www.user21.com',null,null);
insert into tcher(tno,hmpg,fcbk,twit) values(22,null,'www.facebook.com/user22',null);
insert into tcher(tno,hmpg,fcbk,twit) values(23,null,'www.facebook.com/user23','www.twitter.com/user23');
insert into tcher(tno,hmpg,fcbk,twit) values(24,null,null,'www.twitter.com/user24');
insert into tcher(tno,hmpg,fcbk,twit) values(25,null,null,null);

-- 매니저이면서 강사
insert into tcher(tno,hmpg,fcbk,twit) values(31,null,null,null);

-- 강사 사진 데이터
insert into tch_phot(tpno,tno,path) values(1,21,'t21_1.gif');
insert into tch_phot(tpno,tno,path) values(2,21,'t21_2.gif');
insert into tch_phot(tpno,tno,path) values(3,22,'t22_1.gif');
insert into tch_phot(tpno,tno,path) values(4,23,'t23_1.gif');
insert into tch_phot(tpno,tno,path) values(5,24,'t24_1.gif');
insert into tch_phot(tpno,tno,path) values(6,24,'t24_2.gif');
insert into tch_phot(tpno,tno,path) values(7,24,'t24_3.gif');
insert into tch_phot(tpno,tno,path) values(8,25,'t25_1.gif');

-- 매니저 데이터
insert into mgr(mrno,posi,fax,path) values(31,'대리','111-1111','m31.gif');
insert into mgr(mrno,posi,fax,path) values(32,'주임','111-1111','m32.gif');
insert into mgr(mrno,posi,fax,path) values(33,'과장','111-1111','m33.gif');

-- 강사이면서 매니저
insert into mgr(mrno,posi,fax,path) values(23,'부장','111-1111','m23.gif');

-- 강의실 데이터
insert into croom(crmno,name) values(1, '강남301');
insert into croom(crmno,name) values(2, '강남303');
insert into croom(crmno,name) values(3, '강남401');
insert into croom(crmno,name) values(4, '강남402');
insert into croom(crmno,name) values(5, '강남403');
insert into croom(crmno,name) values(6, '강남404');
insert into croom(crmno,name) values(7, '강남501');
insert into croom(crmno,name) values(8, '강남502');
insert into croom(crmno,name) values(9, '강남503');
insert into croom(crmno,name) values(10, '강남504');
insert into croom(crmno,name) values(11, '강남601');
insert into croom(crmno,name) values(12, '강남602');
insert into croom(crmno,name) values(13, '강남603');
insert into croom(crmno,name) values(14, '강남701');
insert into croom(crmno,name) values(15, '종로1');
insert into croom(crmno,name) values(16, '종로2');
insert into croom(crmno,name) values(17, '종로3');
insert into croom(crmno,name) values(18, '종로4');

-- 강의실 사진 데이터
insert into croom_phot(cpno,crmno,path) values(1, 1, 'cr1_1.gif');
insert into croom_phot(cpno,crmno,path) values(2, 1, 'cr1_2.gif');
insert into croom_phot(cpno,crmno,path) values(3, 3, 'cr3_1.gif');
insert into croom_phot(cpno,crmno,path) values(4, 4, 'cr4_1.gif');
insert into croom_phot(cpno,crmno,path) values(5, 4, 'cr4_2.gif');
insert into croom_phot(cpno,crmno,path) values(6, 4, 'cr4_3.gif');
insert into croom_phot(cpno,crmno,path) values(7, 10, 'cr10_1.gif');
insert into croom_phot(cpno,crmno,path) values(8, 11, 'cr11_1.gif');
insert into croom_phot(cpno,crmno,path) values(9, 15, 'cr15_1.gif');
insert into croom_phot(cpno,crmno,path) values(10, 17, 'cr17_1.gif');
insert into croom_phot(cpno,crmno,path) values(11, 17, 'cr17_2.gif');

-- 강의 데이터
insert into lect(lno,crmno,mrno,titl,dscp,sdt,edt,qty,pric,thrs)
values(1,null,null,'자바기초','자바기초배우기','2017-01-02','2017-01-31',30,350000,120);

insert into lect(lno,crmno,mrno,titl,dscp,sdt,edt,qty,pric,thrs)
values(2,null,null,'자바기초','자바기초배우기','2017-02-01','2017-02-28',30,350000,120);

insert into lect(lno,crmno,mrno,titl,dscp,sdt,edt,qty,pric,thrs)
values(3,null,null,'자바기초','자바기초배우기','2017-03-02','2017-03-31',30,350000,120);

insert into lect(lno,crmno,mrno,titl,dscp,sdt,edt,qty,pric,thrs)
values(4,null,null,'HTML5프로그래밍','HTML배우기','2017-01-02','2017-01-20',30,150000,40);

insert into lect(lno,crmno,mrno,titl,dscp,sdt,edt,qty,pric,thrs)
values(5,null,null,'HTML5프로그래밍','HTML배우기','2017-02-15','2017-03-05',30,150000,40);

insert into lect(lno,crmno,mrno,titl,dscp,sdt,edt,qty,pric,thrs)
values(6,null,null,'웹실무양성과정','웹개발배우기','2017-03-02','2017-08-30',30,6000000,960);

insert into lect(lno,crmno,mrno,titl,dscp,sdt,edt,qty,pric,thrs)
values(7,null,null,'웹&앱양성과정','웹과앱배우기','2017-04-01','2017-09-30',30,6000000,960);

insert into lect(lno,crmno,mrno,titl,dscp,sdt,edt,qty,pric,thrs)
values(8,null,null,'.NET과정','.NET배우기','2017-05-01','2017-10-30',30,6000000,960);

insert into lect(lno,crmno,mrno,titl,dscp,sdt,edt,qty,pric,thrs)
values(9,null,null,'IoT과정','IoT배우기','2017-06-01','2017-11-30',30,6000000,960);

insert into lect(lno,crmno,mrno,titl,dscp,sdt,edt,qty,pric,thrs)
values(10,null,null,'자바기초(주말반) 1차','자바기초','2017-06-01','2017-07-30',30,450000,40);

insert into lect(lno,crmno,mrno,titl,dscp,sdt,edt,qty,pric,thrs)
values(11,null,null,'자바기초(주말반) 2차','자바기초','2017-06-01','2017-07-30',30,450000,40);

insert into lect(lno,crmno,mrno,titl,dscp,sdt,edt,qty,pric,thrs)
values(12,null,null,'자바기초(주말반) 3차','자바기초','2017-07-01','2017-08-30',30,450000,40);

insert into lect(lno,crmno,mrno,titl,dscp,sdt,edt,qty,pric,thrs)
values(13,null,null,'자바기초(주말반) 4차','자바기초','2017-07-01','2017-08-30',30,450000,40);

-- 강의실 배정
update lect set crmno=1 where lno=1;
update lect set crmno=2 where lno=2;
update lect set crmno=5 where lno=4;
update lect set crmno=5 where lno=5;
update lect set crmno=15 where lno=6;
update lect set crmno=16 where lno=7;
update lect set crmno=7 where lno=9;
update lect set crmno=1 where lno=10;
update lect set crmno=15 where lno=13;

-- 매니저 배정
update lect set mrno=31 where lno=1;
update lect set mrno=32 where lno=2;
update lect set mrno=33 where lno=3;
update lect set mrno=31 where lno=6;
update lect set mrno=32 where lno=7;
update lect set mrno=32 where lno=9;

-- 강사 배정
insert into tchr_lect(lno, tno) values(1, 21);
insert into tchr_lect(lno, tno) values(1, 22);
insert into tchr_lect(lno, tno) values(1, 24);
insert into tchr_lect(lno, tno) values(2, 23);
insert into tchr_lect(lno, tno) values(3, 24);
insert into tchr_lect(lno, tno) values(4, 24);
insert into tchr_lect(lno, tno) values(5, 22);
insert into tchr_lect(lno, tno) values(8, 21);
insert into tchr_lect(lno, tno) values(8, 23);
insert into tchr_lect(lno, tno) values(8, 24);

-- 수강신청 데이터
insert into lect_appy(lano, lno, sno, rdt) values(1, 1, 1, '2016-11-28');
insert into lect_appy(lano, lno, sno, rdt) values(2, 1, 2, '2016-11-28');
insert into lect_appy(lano, lno, sno, rdt) values(3, 1, 3, '2016-12-01');
insert into lect_appy(lano, lno, sno, rdt) values(4, 1, 4, '2016-12-02');
insert into lect_appy(lano, lno, sno, rdt) values(5, 1, 7, '2016-12-05');
insert into lect_appy(lano, lno, sno, rdt) values(6, 1, 8, '2016-12-25');

insert into lect_appy(lano, lno, sno, rdt) values(7, 5, 1, '2016-11-28');
insert into lect_appy(lano, lno, sno, rdt) values(8, 5, 2, '2016-11-29');
insert into lect_appy(lano, lno, sno, rdt) values(9, 5, 3, '2016-11-30');
insert into lect_appy(lano, lno, sno, rdt) values(10, 5, 5, '2016-12-01');
insert into lect_appy(lano, lno, sno, rdt) values(11, 5, 6, '2016-12-01');
insert into lect_appy(lano, lno, sno, rdt) values(12, 5, 7, '2016-12-02');
insert into lect_appy(lano, lno, sno, rdt) values(13, 5, 9, '2016-12-03');

insert into lect_appy(lano, lno, sno, rdt) values(14, 8, 1, '2016-12-03');
insert into lect_appy(lano, lno, sno, rdt) values(15, 8, 2, '2016-12-03');
insert into lect_appy(lano, lno, sno, rdt) values(16, 8, 3, '2016-12-04');
insert into lect_appy(lano, lno, sno, rdt) values(17, 8, 4, '2016-12-04');
insert into lect_appy(lano, lno, sno, rdt) values(18, 8, 5, '2016-12-12');
insert into lect_appy(lano, lno, sno, rdt) values(19, 8, 6, '2016-12-12');
insert into lect_appy(lano, lno, sno, rdt) values(20, 8, 7, '2016-12-12');
insert into lect_appy(lano, lno, sno, rdt) values(21, 8, 8, '2016-12-30');
insert into lect_appy(lano, lno, sno, rdt) values(22, 8, 9, '2016-12-30');
insert into lect_appy(lano, lno, sno, rdt) values(23, 8, 10, '2017-01-05');

-- 게시판 데이터
insert into content(cono,mno,rdt,vw_cnt) values(1,1,now(),0);
insert into content(cono,mno,rdt,vw_cnt) values(2,1,now(),7);
insert into content(cono,mno,rdt,vw_cnt) values(3,2,now(),0);
insert into content(cono,mno,rdt,vw_cnt) values(4,2,now(),0);
insert into content(cono,mno,rdt,vw_cnt) values(5,2,now(),12);
insert into content(cono,mno,rdt,vw_cnt) values(6,3,now(),35);
insert into content(cono,mno,rdt,vw_cnt) values(7,6,now(),0);
insert into content(cono,mno,rdt,vw_cnt) values(8,7,now(),1);
insert into content(cono,mno,rdt,vw_cnt) values(9,8,now(),0);
insert into content(cono,mno,rdt,vw_cnt) values(10,21,now(),0);

insert into board(bdno,titl,conts) values(1,'제목1','내용1');
insert into board(bdno,titl,conts) values(2,'제목1','내용1');
insert into board(bdno,titl,conts) values(3,'제목1','내용1');
insert into board(bdno,titl,conts) values(4,'제목1','내용1');
insert into board(bdno,titl,conts) values(5,'제목1','내용1');
insert into board(bdno,titl,conts) values(6,'제목1','내용1');
insert into board(bdno,titl,conts) values(7,'제목1','내용1');
insert into board(bdno,titl,conts) values(8,'제목1','내용1');
insert into board(bdno,titl,conts) values(9,'제목1','내용1');
insert into board(bdno,titl,conts) values(10,'제목1','내용1');

-- 프로젝트 데이터
insert into content(cono,mno,rdt,vw_cnt) values(11,1,now(),0);
insert into content(cono,mno,rdt,vw_cnt) values(12,1,now(),7);
insert into content(cono,mno,rdt,vw_cnt) values(13,2,now(),0);

insert into proj(pjno,titl,conts,sdt,edt) values(11,'프로젝트1','내용1','2016-10-01','2016-12-30');
insert into proj(pjno,titl,conts,sdt,edt) values(12,'프로젝트2','내용2','2016-11-01','2017-02-20');
insert into proj(pjno,titl,conts,sdt,edt) values(13,'프로젝트3','내용3','2016-12-01','2017-03-30');

-- 할 일 데이터
insert into content(cono,mno,rdt,vw_cnt) values(14,1,now(),0);
insert into content(cono,mno,rdt,vw_cnt) values(15,1,now(),7);
insert into content(cono,mno,rdt,vw_cnt) values(16,2,now(),0);

insert into todo(tdno, seq, conts, mno) values(14, 1, '할일1', 8);
insert into todo(tdno, seq, conts, mno) values(15, 2, '할일1', 8);
insert into todo(tdno, seq, conts, mno) values(16, 1, '할일1', 9);


-- 팔로잉 데이터
insert into follow(fowr_no, fwng_no) values(1, 5);
insert into follow(fowr_no, fwng_no) values(5, 1);
insert into follow(fowr_no, fwng_no) values(1, 7);
insert into follow(fowr_no, fwng_no) values(1, 21);
insert into follow(fowr_no, fwng_no) values(1, 22);
insert into follow(fowr_no, fwng_no) values(1, 32);
insert into follow(fowr_no, fwng_no) values(32, 1);
insert into follow(fowr_no, fwng_no) values(32, 21);
