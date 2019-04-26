/* 주제: 흐름 제어문 - 연습
- 다음 소스를 변경하여 사용자로부터 숫자 a, b와 연산자 op 값을 받아서
  계산 결과를 출력하는 프로그램을 작성하라.
- 실행결과:
> node test11.js
prompt: a: 10
prompt: b: 20
prompt: op: -
계산 결과:
10 - 20 = -10
*/
"use strict"

var prompt = require('prompt');

prompt.start();

prompt.get(['a', 'b', 'op'], function(err, result) {
  var a = parseInt(result.a); // string --> number
  var b = parseInt(result.b); // string --> number
  var op = result.op;

  console.log('계산결과:');
  switch (op) {
  case "+":
    console.log(a, op, b, "=", a + b);
    break;
  case "-":
    console.log(a, op, b, "=", a - b);
    break;
  case "*":
    console.log(a, op, b, "=", a * b);
    break;
  case "/":
    console.log(a, op, b, "=", a / b);
    break;
  case "%":
    console.log(a, op, b, "=", a % b);
    break;
  }
});











//
