/* 데이터베이스 프로그래밍
=> JavaScript + NodeJS + DBMS(MySQL)

DBMS(Database Management System)
=> 데이터를 전문적으로 관리해주는 소프트웨어
=> DBMS 도입 전
  - 개발자가 직접 File I/O 코드를 작성해야 했다.
  - 데이터를 입출력하는 코드를 작성하는 것은 매우 번거롭고
    버그를 많이 생상하는 일이었다.
  - 또한 데이터와 데이터 사이에 관계를 다루기 힘들었다.
=> DBMS 도입 후
  - 개발자는 File I/O 프로그래밍을 하지 않고,
    대신 DBMS에게 명령(SQL)을 내려서 데이터를 처리한다.
  - DBMS가 개발자를 대신하여 File I/O를 처리한다.

SQL(Stuctured Query Language)
=> 데이터를 처리할 때 DBMS에게 내리는 명령의 문법
=> select.. from.. where 와 같이 명령어의 문법이
  이해하기 쉽도록 구조화 되어 있다.
=> SQL은 DBMS와 별도로 정의된 표준 질의 언어이다.
  그러나 DBMS마다 고유의 문법이 존재한다.

NodeJS와 DBMS
=> 특정 DBMS와 연결하여 SQL을 전달하고 결과를 받을 수 있는 서드파티 모듈이 존재한다.
  NodeJS에게 기본으로 제공하는 모듈은 아니다.
=> 사용하려면, DBMS 제품에 맞는 모듈을 설치해야 한다.

MySQL NodeJS 모듈 사용하기
1) 모듈 설치
  > npm install mysql
2) 모듈 로딩
  var mysql = require('mysql')
*/

// mysql 모듈 로딩
const mysql = require('mysql')

// MySQL 서버에 연결을 수행할 도구를 준비한다.
var connection = mysql.createConnection({
  // host: 'localhost',  // 기본 값: localhost
  // port: 3306,         // 기본 값: 3306
  database: 'webappdb',
  user: 'webapp',
  password: '1111'
})
console.log('MySQL 서버에 연결할 도구 생성완료!')

// MySQL 서버에 연결한다.
connection.connect()
console.log('MySQL 서버와 연결됨!')

// MySQL 서버와 연결 끊기를 예약한다.
// => 서버에 시킨일이 모두 끝나면 자동으로 연결을 끝는다.
// => 만약 진행중인 작업이 있다면, 기다린다.
connection.end()
console.log('MySQL 서버와의 연결 끊기가 예약되었음!')









/* */
