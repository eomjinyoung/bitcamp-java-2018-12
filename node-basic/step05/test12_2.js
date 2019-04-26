/* 주제: 객체 - 함수와 생성자
- 그냥 사용하면 함수이고,
- new 다음에 호출하면 생성자로서 사용하겠다는 의미다.
*/
"use strict"

function Calculator() {
  if (this) {
    this.result = 0
    this.plus = function(value) {this.result += value}
  } else {
    var obj = {}
    obj.result = 0;
    obj.plus = function(value) {this.result += value}
    return obj;
  }
}

var c1 = Calculator();
c1.plus(100);
console.log(c1.result);

var c2 = new Calculator();
c2.plus(200);
console.log(c2.result);




//
