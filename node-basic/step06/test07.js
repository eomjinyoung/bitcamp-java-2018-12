/* 주제: exports 를 사용자가 다른 객체로 대체하기
- 기존에 존재하는 exports 객체에 뭔가를 담아서 리턴할 수 있지만,
  아예 exports 객체를 다른 객체로 대체해서 리턴할 수 있다.
*/
"use strict"

var m1 = require('./test07_m1.js')

console.log(m1.plus(10, 20))
console.log(m1.minus(10, 20))
console.log(m1.multiple(10, 20))
console.log(m1.divide(10, 20))

// require()가 리턴한 객체에서 plus()와 minus() 함수 주소만 꺼내 변수에 저장한다.
var {plus, minus} = require('./test07_m1.js')
/*
var plus = require('./test07_m1.js').plus
var minus = require('./test07_m1.js').minus
*/
/*
var m = require('./test07_m1.js')
var plus = m.plus
var minus = m.minus
*/
console.log(plus(10, 20))
console.log(minus(10, 20))





//
