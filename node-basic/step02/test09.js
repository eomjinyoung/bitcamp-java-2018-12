/* 주제: 연산자 - 조건 연산자(Conditional (ternary) operator)
- 간단하게 조건 명령문을 작성할 때 사용한다.
- 문법
(조건) ? 식1 : 식2
조건이 참이면 식1이 놓이고, 조건이 거짓이면 식2가 놓인다.
*/
"use strict"
var a = 11;
console.log((a % 2) == 0 ? "짝수입니다." : "홀수입니다.");

(a % 2) == 0 ? console.log("짝수입니다.") : console.log("홀수입니다.");

//
