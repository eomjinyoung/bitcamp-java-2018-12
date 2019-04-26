/* 주제: 함수 - 파라미터로 arrow 함수(의 주소) 넘기기
- 간단한 함수인 경우는 arrow 함수를 이용하여 파라미터로 넘길 수 있다.
*/
"use strict"

// 함수(주소)를 받을 수 있다.
function caller(f) {
  var value = f(20, 30);
  console.log(value);
}

// 함수의 이름이 함수를 주소를 저장한 변수이다.
function plus(a, b) {
  return a + b;
}
caller(plus);

var plus2 = function(a, b) {
  return a + b;
}
caller(plus2);

// plus3()라는 함수를 정의한 후 그 자리에 그 함수의 주소를 놓는다.
// 그리고 caller() 호출하여 그 자리에 놓인 주소를 넘긴다.
caller(function plus3(a, b) {
  return a + b;
});

// 파라미터로 넘기는 함수는 굳이 이름을 가질 필요가 없다.
// 보통 익명함수로 정의한다.
caller(function(a, b) {
  return a + b;
});

// ECMAScript 2015 버전부터는 익명함수를 간단하게 정의할 수 있는 문법을 제공한다.
// 그 문법이 arrow function
caller((a, b) => {
  return a + b;
});

caller((a, b) => {return a + b;});

// 명령문의 끝을 명확하게 구분할 수 있다면, 세미콜론(;)을 생략할 수 있다.
caller((a, b) => {return a + b});

// 함수를 호출하는 문장은 다음 문장과 구별이 되기 때문에 보통 세미콜론(;)을 생략한다.
caller((a, b) => {return a + b})

// 함수에 들어 있는 명령문이 한 개 일 때는 블록{}을 생략할 수 있다.
// 블록을 생략했을 때는 값을 리턴할 때 return 을 사용하지 말아야 한다.
// 블록을 생략했을 때는 명령문 뒤에 세미콜론(;)을 사용하지 말아야 한다.
caller((a, b) => a + b)




//
