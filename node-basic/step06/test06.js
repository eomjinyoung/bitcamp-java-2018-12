/* 주제: 모듈 만들기
- 함수들이나 객체를 별도의 파일로 분리하여 관리하기
- 즉 몇 백개, 몇 천개, 몇 만개의 함수나 객체를 한 개의 파일에서 관리할 수 없다.
- 함수나 객체들을 역할이나 기능에 따라 적절히 분류하여
  여러 개의 파일에 분산해 두는 것이 유지보수에 좋다.
  이렇게 분리된 함수나 객체들의 묶음을 "모듈(module)"이라 부른다.
1) 모듈 정의
   => test06_m1.js
2) 모듈 사용
   => require('모듈명 또는 모듈 경로')
*/
"use strict"

// require(모듈 경로)
// => module 객체에 보관된 exports 객체를 리턴한다.
var m1 = require('./test06_m1.js')

// m1 <= module.exports 객체를 가리킨다.
console.log(m1.plus(10, 20))
console.log(m1.minus(10, 20))
console.log(m1.multiple(10, 20))
// divide() 함수는 exports 객체에 등록되지 않았기 때문에 호출할 수 없다.
//console.log(m1.divide(10, 20))


//
