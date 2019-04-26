/* 주제: 객체 - 객체에 함수를 등록하는 또 다른 방법
- 문법
  var obj = {
    함수명: 익명함수정의,
    함수명: 익명함수정의,
    ...
  }

  var obj = {
    이름있는함수정의,
    이름있는함수정의,
    ...
  }

*/
"use strict"

// 방법1)
var obj = {
  plus: function(a, b) {return a + b},
  minus: function(a, b) {return a - b},
  multiple: function(a, b) {return a * b},
  divide: function(a, b) {return a / b}
}

// 방법2)
var obj2 = {
  plus(a, b) {return a + b},
  minus(a, b) {return a - b},
  multiple(a, b) {return a * b},
  divide(a, b) {return a / b}
}

console.log(obj.plus(10, 20))
console.log(obj2.plus(10, 20))












//
