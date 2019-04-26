/* 주제: 모듈 정의
- 모듈로 관리할 함수나 객체를 정의한 후
  module.exports 라는 객체에 그 주소를 저장한다.
*/
"use strict"

function plus(a, b) {return a + b}
function minus(a, b) {return a - b}
function multiple(a, b) {return a * b}
function divide(a, b) {return a / b}

// module.exports.모듈을사용하는측에서호출할이름 = 함수주소
// module 객체에 exports 라는 객체가 미리 등록되어 있다.
// 그래서 미리 등록된 exports 라는 객체에 함수나 또 다른 객체를 저장하는 것이다.
module.exports.plus = plus;

// module.exports 에서 module을 생략할 수 있다.
exports.minus = minus;
exports.multiple = multiple;





//
