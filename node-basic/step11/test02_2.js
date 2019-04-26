/* 데이터 추가 명령: insert
insert into 테이블명(컬럼명, 컬럼명, ...)
values(값, 값, ...)
*/

const mysql = require('mysql')
const connection = mysql.createConnection({
  database: 'webappdb',
  user: 'webapp',
  password: '1111'
})

connection.connect()

connection.query('insert into memb(name, email, tel, pwd)' +
  ' values("오호라", "ohora@test.com", "111-1111", password("1111"))', function(error, results) {
  console.log('결과를 가져왔음!')
  if (error) throw error;

  console.log(results)
})

connection.end()
console.log('MySQL 서버와의 연결 끊기가 예약되었음!')









/* */
