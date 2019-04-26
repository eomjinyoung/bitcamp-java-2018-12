/* 주제: 함수 - arrow function II
- 함수 정의를 축약한 문법
*/
"use strict"


// Arrow 함수에서는 마지막 문장의 실행 결과가 리턴 값이다.
var f1 = () => "hello";
var value = f1();
console.log(value);

// 만약 마지막 문장의 실행 결과가 값이 없을 경우, 값을 리턴하지 않는다.
var f2 = () => console.log("okok");
var value = f2();
console.log(value);

var f3 = () => {
  console.log("------------------")
  console.log("f3()... 호출됨");
  console.log("Arrow 함수임.")
  return "hello2"; // 블록 안에서 값을 리턴할 때는 반드시 return 명령을 사용해야 한다.
}
value = f3();
console.log(value);

// 파라미터를 받는 arrow 함수
var f4 = (a, b) => a + b;
/*
var f4 = function(a, b) { return a + b;}
*/
value = f4(10, 20);
console.log(value);












//
