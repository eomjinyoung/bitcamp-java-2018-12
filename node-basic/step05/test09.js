/* 주제: 객체 - this 변수를 통해 객체를 초기화시키기
- this는 빈 객체 주소를 저장하고 있다.
*/
"use strict"

function init() {
  // 계산기로서 역할을 할 수 있도록 객체를 초기화시켜 보자!
  this.result = 0; // 빈 객체에 result 이름으로 0 값을 저장한다.
                   // 만약 이미 result라는 이름으로 저장된 값이 있다면,
                   // 그 값을 덮어쓴다.
  this.plus = function(value) {this.result += value}
  this.minus = function(value) {this.result -= value}
  this.multiple = function(value) {this.result *= value}
  this.divide = function(value) {this.result /= value}
}

var calc = new init() // new 다음에 곧바로 함수 호출하기.
                      // 호출이 끝나면 그 자리에 초기화시킨 객체 주소가 놓인다.
console.log(calc)

// 10 + 20 * 3 - 7 / 2 = ?
// 이 계산기는 연산자 우선순위를 처리하지 않는다.
// 그냥 계속 계산할 뿐이다.
calc.plus(10) // result = 10
calc.plus(20) // result = 30
calc.multiple(3) // result = 90
calc.minus(7) // result = 83
calc.divide(2) // result = 41.5
console.log(calc.result)


// 에이~ {} 로 해도 될 것 같은데요? 제가 해볼게요.
var calc2 = {
  result: 0,
  plus: function(value) {this.result += value},
  minus: function(value) {this.result -= value},
  multiple: function(value) {this.result *= value},
  divide: function(value) {this.result /= value}
}
console.log(calc2)
calc2.plus(10) // result = 10
calc2.plus(20) // result = 30
calc2.multiple(3) // result = 90
calc2.minus(7) // result = 83
calc2.divide(2) // result = 41.5
console.log(calc2.result)

// 내가 답변해 보겠다.
// => 계산기를 여러 개 생성해봐! 그러면 알거야?

var c1 = new init()
c1.plus(20)
var c2 = new init()
c2.plus(30)
var c3 = new init()
c3.plus(40)
console.log(c1.result, c2.result, c3.result)


var c4 = {
  result: 0,
  plus: function(value) {this.result += value},
  minus: function(value) {this.result -= value},
  multiple: function(value) {this.result *= value},
  divide: function(value) {this.result /= value}
}
c4.plus(200)

var c5 = {
  result: 0,
  plus: function(value) {this.result += value},
  minus: function(value) {this.result -= value},
  multiple: function(value) {this.result *= value},
  divide: function(value) {this.result /= value}
}
c5.plus(300)

var c6 = {
  result: 0,
  plus: function(value) {this.result += value},
  minus: function(value) {this.result -= value},
  multiple: function(value) {this.result *= value},
  divide: function(value) {this.result /= value}
}
c6.plus(400)
console.log(c4.result, c5.result, c6.result)

// 객체를 한 개만 만들어서 사용할 경우, {} 사용하여 객체를 생성하는 것이 편하다.
// 그러나 같은 객체를 여러 개 만들 경우, {}을 사용하는 것 보다
// 생성자를 사용하여 초기화시키는 것이 훨씬 더 편하고, 코드가 간결해진다. 






//
