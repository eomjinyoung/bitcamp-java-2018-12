// connection 객체를 준비
"use strict"
const mysql = require('mysql')
const con = mysql.createConnection({
  database: 'webappdb',
  user: 'webapp',
  password: '1111'
})
con.connect()

/* 주석에 작성된 코드와 그 아래의 코드는 같다.
module.exports.getConnection = function() {
  return con
}
*/
module.exports = {
  getConnection() {
    return con
  }
}
