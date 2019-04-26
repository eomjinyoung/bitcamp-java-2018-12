/* 주제: 함수 - 로컬 변수와 스택메모리
- 로컬 변수는 함수가 호출되는 동안 생성되어 호출이 끝나면 제건된다.
- 함수에서 함수를 호출할 때 마다 이전으로 되돌아 올 수 있도록
  스택 방식으로 메모리를 관리하다.
*/
"use strict"

var v1 = 100;

function f1() {
  var v1 = 200;
  console.log("f1():", v1);
  f2();
  console.log("f1():", v1);
}

function f2() {
  var v1 = 300;
  console.log("f2():", v1);
  f3();
  console.log("f2():", v1);
}

function f3() {
  var v1 = 400;
  console.log("f3():", v1);
}

f1()






//
