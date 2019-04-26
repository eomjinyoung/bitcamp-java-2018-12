/* 주제: Array() - 기본 사용법
*/

//1) 함수로 사용해보자!
// - 빈 객체에 배열을 다루는 데 필요한 프로퍼티를 등록한다.
var arr1 = new Array()
console.log(arr1)
console.log(typeof arr1)

var arr2 = [] // new Array()의 단축문법
console.log(arr2)
console.log(typeof arr2)

var arr3 = ['홍길동', '임꺽정', '유관순', '안중근'] // 배열을 생성할 때 값을 미리 설정할 수 있다.

// Array() 인스턴스 함수를 사용해보자!
console.log("배열 길이 = ", arr3.length) // 배열 객체의 저장된 항목의 개수를 알아내기

arr3.push('윤봉길') // 배열의 끝에 값 추가하기
arr3.push('김구', '신채호') // 배열의 끝에 값 여러 개를 추가하기
console.log("배열 길이 = ", arr3.length)

var value = arr3.pop() // 배열 끝에 있는 값을 꺼내어 리턴한다. 물론 꺼낸 값은 배열에서 제거된다.
console.log(arr3)

value = arr3.shift() // 배열 맨 앞에 있는 값을 꺼내어 리턴한다. 물론 꺼낸 값은 배열에서 제거된다.
console.log(arr3)

value = arr3.splice(2, 1) // 2번 방에 있는 값을 꺼내어 리턴한다. 물론 꺼낸 값은 배열에서 제거된다.
console.log(arr3)

value = arr3.splice(1, 3) // 1번 방부터 3개 값을 배열에서 꺼내 리턴. 물론 제거.
console.log(arr3)
console.log('---------------------------')








//
