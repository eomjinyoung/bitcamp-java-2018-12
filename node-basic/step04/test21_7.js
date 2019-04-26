/* 주제: 함수 - 연습
> node test21.js
--------------------------
prompt: command: list
[학생 성적 목록]
0: "홍길동1", 100, 90, 80, 270, 90
1: "홍길동2", 100, 90, 80, 270, 90
2: "홍길동3", 100, 90, 80, 270, 90
3: "홍길동4", 100, 90, 80, 270, 90
--------------------------
prompt: command: add
[학생 성적 입력]
prompt: name: 홍길동5
prompt: kor: 100
prompt: eng: 100
prompt: math: 100
성적
이름: 홍길동5
국어: 100
영어: 100
수학: 100
합계: 300
평균: 100
--------------------------
prompt: command: list
[학생 성적 목록]
0: "홍길동1", 100, 90, 80, 270, 90
1: "홍길동2", 100, 90, 80, 270, 90
2: "홍길동3", 100, 90, 80, 270, 90
3: "홍길동4", 100, 90, 80, 270, 90
4: "홍길동5", 100, 100, 100, 300, 100
--------------------------
prompt: command: delete
prompt: no: 2
"홍길동3"의 정보가 삭제되었습니다.
--------------------------
prompt: command: list
[학생 성적 목록]
0: "홍길동1", 100, 90, 80, 270, 90
1: "홍길동2", 100, 90, 80, 270, 90
2: "홍길동4", 100, 90, 80, 270, 90
3: "홍길동5", 100, 100, 100, 300, 100
--------------------------
prompt: command: quit
안녕히 가세요!
>
*/
"use strict"

/* 7단계:
+ 사용자로부터 command 값 입력 받기
+ 반복하기
+ 'quit' 명령 처리하기
+ 'list', 'add', 'delete' 명령 구분하기
+ 명령을 구분할 때 if 문 대신 switch 문 사용
+ 'list' 명령 처리하기
+ 'add' 명령 처리하기
*/
var prompt = require('prompt')

var studentList = [
  ['홍길동1', 100, 100, 100, 300, 100],
  ['홍길동2', 90, 90, 90, 270, 90],
  ['홍길동3', 80, 80, 80, 240, 80],
  ['홍길동4', 70, 70, 70, 210, 70],
  ['홍길동5', 60, 60, 60, 180, 60]
];

prompt.start()

inputCommand()

function inputCommand() {
  prompt.get(['command'], function(err, result) {
    switch (result.command) {
    case 'quit':
      console.log('안녕히 가세요!')
      return; // 함수 실행을 끝낸다.
    case 'list':
      console.log('[학생 목록]')
      for (var student of studentList) {
        console.log(student[0] + "," +
          student[1] + "," +
          student[2] + "," +
          student[3] + "," +
          student[4] + "," +
          student[5]);
      }
      break;
    case 'add':
      console.log('[학생 입력]')
      prompt.get(['name', 'kor', 'eng', 'math'], function(err, result) {
        var student = [result.name,
          parseInt(result.kor),
          parseInt(result.eng),
          parseInt(result.math)]
        student[4] = student[1] + student[2] + student[3]
        student[5] = student[4] / 3

        studentList[studentList.length] = student
        console.log('입력 되었습니다.')
        console.log()
        
        inputCommand();
      })
      return;
    case 'delete':
      console.log('[학생 삭제]')
      break;
    default:
      console.log('해당 명령은 지원하지 않습니다.')
    }
    inputCommand();
  })
}









//
