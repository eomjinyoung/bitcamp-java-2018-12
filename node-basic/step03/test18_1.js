/* 주제: 예외처리 - throw
=> 프로그램을 실행하는 도중 예외 상황이 발생했을 때,
  예외 정보를 호출할 쪽에 알리는 명령어
*/
"use strict"

// throw 적용 전
function divide(a, b) {
  return a / b
}

var result = divide(10, 2)
console.log(result)

result = divide(10, 0)
console.log(result)













//
