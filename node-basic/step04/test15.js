/* 주제: 함수 - 함수의 아규먼트를 받아 저장하는 내장 변수 "arguments"
- arguments 변수는 모든 함수에 있다.
- 함수가 호출될 때 받은 값을 모두 보관한다.
- 배열이다.
- 파라미터 개수와 상관없이 모든 값을 보관한다.
*/
"use strict"

function f1() {
  console.log(arguments.length)
  console.log(arguments)
  console.log("----------------------");
}

f1()
f1(10)
f1(10, 20)
f1(10, 20, 30)
f1(10, 20, 30, 40)

function f2(a, b) {
  console.log(arguments.length)
  console.log(arguments)
  console.log(a, b)
  console.log("-------------------------")
}

f2()
f2(10)
f2(10, 20)
f2(10, 20, 30)
f2(10, 20, 30, 40)

function sum() {
  var result = 0;
  for (var value of arguments) {
    result += value;
  }
  return result;
}

console.log(sum());
console.log(sum(10, 20));
console.log(sum(10, 20, 30, 40, 50));







//
