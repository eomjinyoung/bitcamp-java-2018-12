/* 주제: exports 를 함수 객체로 대체하기
- 기존에 존재하는 exports 객체를 함수 객체로 대체하여 리턴할 수 있다.
*/
"use strict"

// require()가 리턴한 것은 함수 객체이다.
var m1 = require('./test08_m1.js')

// 따라서 함수로서 호출하면 된다.
m1();









//
