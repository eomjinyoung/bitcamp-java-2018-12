/* 주제: 흐름 제어문 - switch와 break
- case 블록에서 break 명령을 주지 않으면 계속 아래로 실행을 한다.
- 문법
switch(식) {
case 값1:
  식1;
  식2;
  ...
  break;
case 값2:
  ...
  break;
default:
  ...
}
*/
"use strict"

var level;

level = "팀장";
switch (level) {
  case "팀장":
    console.log("석사 학위자");
    //break; // <-- case 블록의 실행을 멈추고 switch 문을 나간다.
  case "개발자":
    console.log("정보처리기사 자격증 소지자");
    break;
  case "사원":
    console.log("워드 자격증 소지자");
    break;
}
console.log("----------------------------");











//
