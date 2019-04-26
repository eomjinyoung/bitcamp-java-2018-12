/* 주제: 객체 - 생성자 함수의 이름
- 생성자 함수는 어떤 역할의 객체를 만드는 지
  그 이름 속에서 추측할 수 있도록
  함수 이름을 짓도록 하라!
*/
"use strict"

function initCalculator() {
  this.result = 0
  this.plus = function(value) {this.result += value}
  this.minus = function(value) {this.result -= value}
  this.multiple = function(value) {this.result *= value}
  this.divide = function(value) {this.result /= value}
}

var calc = new initCalculator()
calc.plus(10) // result = 10
calc.plus(20) // result = 30
calc.multiple(3) // result = 90
calc.minus(7) // result = 83
calc.divide(2) // result = 41.5
console.log(calc.result)
//
