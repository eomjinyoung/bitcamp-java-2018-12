/* 주제: 흐름 제어문 - 조건문 if ~ else ~
- 문법
if (식)   <-- 식의 결과가 참이면 '명령문1'이 실행되고, 거짓이면 '명령문2'가 실행된다.
  명령문1;
else
  명령문2
*/
"use strict"

var value;

// else 문법 도입 전
value = 11;
if ((value % 2) == 0)
  console.log("짝수입니다.");
if ((value % 2) != 0)
  console.log("홀수입니다.");
console.log("---------------------------");

// else 문법 도입 후
value = 11;
if ((value % 2) == 0)
  console.log("짝수입니다."); // <-- if에 소속된 문장
else // <-- if 조건이 거짓일 때 else에 소속된 문장을 실행한다.
  console.log("홀수입니다."); // <-- else에 소속된 문장
console.log("---------------------------");

// else는 if 없이 실행할 수 없다.
// - else는 항상 if문과 이어져야 한다.
/*
value = 11;
if ((value % 2) == 0)
  console.log("짝수입니다."); // <-- if에 소속된 문장
console.log("-------------"); // <-- 이 문장이 들어가는 순간 if 문은 종료된 것이다.
else // <-- 이 else와 관련된 if 문이 없다. 바로 위에 있던 if는 이미 종결되었기 때문이다.
  console.log("홀수입니다."); // <-- else에 소속된 문장
console.log("---------------------------");
*/
// 위의 문장이 오류가 발생하지 않도록 해결하는 방법
value = 11;
if ((value % 2) == 0) { // <-- 중괄호를 사용하여 if가 끊기기 않도록 한다.
  console.log("짝수입니다."); // <-- if에 소속된 문장
  console.log("-------------"); // <-- 이 문장도 if에 소속된 문장이 된다.
}
else // <-- 이 else는 위의 if와 연결될 수 있다. 그래서 오류가 아니게 된다.
  console.log("홀수입니다."); // <-- else에 소속된 문장
console.log("---------------------------");











//
