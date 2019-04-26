/* 템플릿 엔진 사용하기 - 기본 사용법
*/

const handlebars = require('handlebars')

var str = '나는 {{name}}입니다.'
var data = {
  name: '홍길동'
}

//1) 템플릿 엔진을 이용하여 템플릿 소스를 변환할 함수를 준비한다.
var template = handlebars.compile(str)

//2) 템플릿 엔진으로부터 리턴 받은 함수에게 데이터를 전달한다.
//=> 템플릿 소스에 데이터가 적용된 결과 문자열을 얻는다.
var result = template(data)

console.log(result)
