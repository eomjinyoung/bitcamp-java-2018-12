/* 주제: 연산자 - null, undefined, NaN, Infinity, -Infinity 값 비교
-

*/
"use strict"


// x는 존재하지 않는 변수이기 때문에 비교 연산자를 사용할 수는 없다.
//if (x == undefined) console.log("x는 undefined이다.");  // 실행 오류!

// undefined의 경우, 변수는 존재하지만 값을 지정하지 않아서 타입을 모를 경우!
var v0;
if (v0 == undefined) console.log("v0는 변수는 존재하지만 값이 없는 상태이다.");

var v1 = 20;
v1 = undefined; // 존재하는 변수를 undefined 로 만들 수 있다. 타입이 없는 상태로 만들 수 있다.
console.log("v1의 타입 = ", typeof v1);
if (v1 == undefined) console.log("v1은 변수는 존재하지만 값이 없는 상태이다.");

// if 문 조건식은 반드시 boolean 값이어야 한다. 만약 boolean 값이 아니라면,
// 암시적 형변환이 자동으로 수행된다.
// v1 변수는 undefined 이다. undefined는 암시적 형변환을 통해 false로 바뀐다.
if (v1) console.log("v1는 false로 바뀐다.");

var v2 = null; // v2는 특정 객체를 가리키지 않음을 지정할 때 null을 사용한다.
console.log("v2의 타입 = ", typeof v2);
if (v2 == null) console.log("v2는 null 이다.");

var v3 = NaN; // v3는 number 타입이다. 숫자가 아닌 상태를 가리킨다.
console.log("v3의 타입 = ", typeof v3);
// NaN의 상태를 알고 싶을 때 다음과 같이 직접 비교할 수 없다.
if (v3 == NaN) console.log("v3는 NaN 이다.");
// 해결책? NaN 상태를 알고 싶다면, 특별한 함수를 사용해야 한다.
if (isNaN(v3)) console.log("v3는 isNaN(v3) == true 이다.");

var v4 = Infinity;
if (v4 == Infinity) console.log("v4는 Inifinity이다."); // 직접 비교 가능!





//
