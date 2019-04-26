/* 주제: 객체 - 일반 객체와 함수 객체 응용
*/
"use strict"

// 함수 객체 응용편
// 1) 역할
// 이 함수 객체는 계산 결과를 저장할 수 있도록 객체를 초기화시키는 일을 한다.
// 즉 생성자 역할을 수행한다.
function Calculator() {
  this.result = 0;
}

// 2) 인스턴스 함수 추가
// 초기화시킨 인스턴스의 값을 다루는 함수를 보관한다.
Calculator.prototype.plus = function(value) {this.result += value}
Calculator.prototype.minus = function(value) {this.result -= value}

// 3) 일반 함수 추가
// 인스턴스를 사용하지 않고 작업을 수행하는 함수를 보관한다.
// 물론 Calculator와 관련된 함수를 보관하는 것이 유지보수에 좋을 것이다.
Calculator.plus = function(a, b) {return a + b}
Calculator.minus = function(a, b) {return a - b}

// => 함수 객체에 보관된 함수 코드를 실행해보자!
var calc1 = new Calculator()

// => 함수 객체의 prototype에 보관된 함수를 호출해보자!
calc1.plus(100) // 처음에는 calc1 객체에서 plus()를 찾는다.
calc1.minus(20) // 없으면, 이 객체를 초기화시킨 함수(생성자)의 prototype에서 찾는다.
calc1.plus(10)
console.log(calc1.result)

// => 함수 객체에 보관된 일반 함수를 호출해보자!
var r1 = Calculator.plus(100, 200)
var r2 = Calculator.minus(100, 200)
console.log(r1, r2)












//
