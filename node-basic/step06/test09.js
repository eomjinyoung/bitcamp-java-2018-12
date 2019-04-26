/* 주제: exports 를 함수 객체로 대체하기 II
- require()가 리턴한 함수를 호출하고, 그 함수가 리턴한 값을 사용하기
*/
"use strict"

// require()가 리턴한 것은 함수 객체이다.
var m1 = require('./test09_m1.js')

// 따라서 함수로서 호출하면 된다. 이 함수는 객체를 리턴한다.
var calc = m1();

calc.plus(10)
calc.minus(5)
calc.plus(100)
console.log(calc.result)









//
