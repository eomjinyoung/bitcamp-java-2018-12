/* 주제: exports 객체를 다른 객체로 대체하기
*/
"use strict"

// 기존에 module에 들어있던 exports 객체를 우리가 만든 객체로 대체한다.
module.exports = {
  plus(a, b) {return a + b},
  minus(a, b) {return a - b},
  multiple(a, b) {return a * b},
  divide(a, b) {return a / b}
}






//
