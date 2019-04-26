/* 주제: 흐름 제어문 - switch와 default
- case 에 해당되는 값이 없을 경우 실행한다. else와 같은 용도이다.
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

level = "경비원";
switch (level) {
  case "팀장":
    console.log("석사 학위자");
    break;
  case "개발자":
    console.log("정보처리기사 자격증 소지자");
    break;
  case "사원":
    console.log("워드 자격증 소지자");
    break;
  default:
    console.log("자격증이 필요 없습니다.");
}
console.log("----------------------------");











//
