/* 주제: 객체 - 일반 함수와 인스턴스 함수
- 일반함수
  => 객체에 들어있는 값에 의존하지 않고 실행하는 함수
- 인스턴스 함수
  => "객체"를 다른 말로 "인스턴스"라고 부른다.
     "사람"과 "청소년"의 관계와 같다.
     즉 "객체"는 값, 배열, 함수 등을 가리키는 일반 용어이고,
     "인스턴스"는 new 명령 또는 {}을 통해 생성된 객체를 가리키는 용어이다.
  => 인스턴스에 들어있는 값을 다루는 함수
  => 인스턴스 없이 호출할 수 없다.
*/
"use strict"

function Calculator() {
  this.result = 0
}

Calculator.prototype.plus = function(value) {this.result += value} // 인스턴스 함수
Calculator.prototype.plus2 = function(a, b) {return a + b} // 인스턴스 함수

var c1 = new Calculator()
var c2 = new Calculator()

c1.plus(100) // c1이 가리키는 인스턴스의 result 변수에 100을 더한다.
var r1 = c1.plus2(100, 200) // 두 개의 아규먼트 값을 더해서 그 결과를 리턴한다.
console.log(c1.result, r1)

c2.plus(200)
var r2 = c2.plus2(1000, 2000)
console.log(c2.result, r2)

/*
plus() 함수는 실행할 때 인스턴스의 변수를 사용한다.
따라서 plus() 함수는 prototype 객체에 보관하는 것이 맞다.
왜? prototype에는 인스턴스에 대해서 작업을 수행하는 함수를 두는 장소이기 때문이다.

plus2() 함수는 실행할 때 인스턴스를 사용하지 않는다.
내부 코드를 보면 this 변수를 사용하지 않는다는 것을 알 수 있다.
이런 함수는 굳이 prototype에 저장할 필요는 없다.

어떻게 해야 하는가?
*/
//선택1) 함수를 글로벌 함수로 만든다.
function plus2(a, b) {
  return a + b
}

//선택2) plus2() 함수는 Calculator와 관련된 기능을 수행하는 것은 맞다.
//       그러나 글로벌 함수로 만드는 것은 유지보수에 안좋다.
//       아무래도 서로 관련된 함수끼로 모아두는 것이 유지보수에 좋다.
//       이런 경우,
//       서로 관련된 함수끼로 모아두고 싶고, 그러나 인스턴스를 사용하는 것인 아닐 때
//       prototype 객체에 보관하지 맣고, "함수 객체"에 바로 보관하라!
Calculator.plus2 = function(a, b) {return a + b}

// 호출 예)
var r3 = Calculator.plus2(100, 200)
console.log(r3)





//
