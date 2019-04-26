/* 주제: 흐름 제어문 - 연습
- 구구단을 출력하는 프로그램을 작성하시오.
- 예)
> node test17.js
prompt: step: 5
5 * 1 = 5
5 * 2 = 10
...
5 * 9 = 45
*/
"use strict"

var prompt = require('prompt');

prompt.start();


prompt.get(['step'], function(err, result) {
  var step = parseInt(result.step); // string --> number
  for (var i = 1; i <= 9; i++)
    console.log(step + " * " + i + " = " + (step * i));
});








//
