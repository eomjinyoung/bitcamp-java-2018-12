/* 주제: 객체 - 생성자 함수의 이름 II
- 생성자 함수를 일반 함수처럼 오해하지 않도록
  이름의 첫 알파벳을 대문자로 보통 쓴다.
*/
"use strict"

function Calculator() {
  this.result = 0
  this.plus = function(value) {this.result += value}
  this.minus = function(value) {this.result -= value}
  this.multiple = function(value) {this.result *= value}
  this.divide = function(value) {this.result /= value}
}

// 함수의 이름이 소문자일 경우 소스 코드를 해석하다가
// 무심고 일반 함수로 오인해서 new 없이 사용할 수 있다.
// 예를 들어 다름과 같이!
// initCalculator();

// 그래서 함수의 이름을 대문자로 시작하면,
// 개발자가 그 함수를 호출할 때 주목을 하게 되고,
// "아~ 이 함수는 객체를 초기화시키는 함수이구나.
// 따라서 new 연산자를 먼저 사용하여 빈 객체를 생성해야 하는구나." 라고
// 바로 알아볼 수 있다.
var calc = new Calculator()
calc.plus(10) // result = 10
calc.plus(20) // result = 30
calc.multiple(3) // result = 90
calc.minus(7) // result = 83
calc.divide(2) // result = 41.5
console.log(calc.result)
//
