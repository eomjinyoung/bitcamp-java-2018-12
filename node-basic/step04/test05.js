/* 주제: 함수 - 익명 함수로 정의하기
- 익명 함수를 정의한 후 변수에 저장할 수 있다.
*/
"use strict"

// 1) 일반적인 함수 정의
function f1() {
  console.log("okok");
}

// 2) 익명함수를 정의하기
var f2 = function() {
  console.log("익명함수....");
}

var f3 = function() {
  console.log("익명함수2....");
}

var f4 = function ok() {
  console.log("일반함수인데 익명함수처럼 변수에 저장할 수 있다.");
}

f1();
f2();
f3();
f4();









//
