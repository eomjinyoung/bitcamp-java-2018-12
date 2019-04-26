/* 주제: 흐름 제어문 - switch의 조건 값
- case 에 지정할 수 있는 값의 종류

*/
"use strict"


switch ("문자열") {
  case "문자열":
    console.log("문자열 가능!");
    break;
  case 1:
    console.log("숫자 가능");
    break;
  case 3.14159:
    console.log("부동소수점 가능");
    break;
  case true:
    console.log("boolean 값 가능");
    break;
  case undefined: // <-- 정의된 변수가 없음
    console.log("undefined 가능");
    break;
  case null: // <-- 변수는 있지만, 값이 없음
    console.log("null 가능");
    break;
  case NaN: // NaN는 직접 비교할 수 없다. isNaN()으로 확인해야 한다.
    console.log("NaN 가능");
    break;
}
console.log("----------------------------");











//
