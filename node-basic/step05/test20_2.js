/* 주제: 객체 - defineProperty() 응용
- 'result' 변수의 값을 임의적으로 바꾸는 문제를 해결
  => 클로저 기법을 사용하여 result 변수를 객체에서 감춘다.
*/
"use strict"

function Calculator() {
}
let result = 0;
Calculator.prototype.plus = function(value) {
  // 함수에서 바깥 쪽의 변수를 사용할 때
  // 그 변수가 "로컬 변수"이거나 "let"으로 선언된 변수인 경우,
  // 언제든(함수 호출이 끝나거나 블록을 벗어날 때) 제거될 수 있는 위험이 있다.
  // 이런 경우를 해소하기 위해 그 변수의 값을 클로저가 관리하는 별도의 영역에 복제해 둔다.
  result += value
}
Calculator.prototype.minus = function(value) {
  // 같은 영역에서 만든 함수는 클로저의 메모리 영역을 공유한다.
  // plus()나 minus()는 같은 클로저의 메모리 영역을 사용한다.
  // plus()가 복제한 result나 minus()가 복제한 result는 같은 변수이다.
  result -= value
}
Calculator.prototype.getResult = function(value) {
  return result
}

var calc1 = new Calculator()

calc1.plus(100)
calc1.minus(20)
calc1.plus(10)
//console.log(calc1.result) // 실행 오류! calc1 객체에는 result 프로퍼티가 없다.
// result 값을 알고 싶다면 getResult() 함수를 호출해야 한다.
console.log(calc1.getResult())











//
