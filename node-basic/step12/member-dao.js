// MEMB 테이블 데이터 처리
"use strict"

module.exports = {
  setConnection(con) {
    this.connection = con
  },

  insert: function(member, successFn, errorFn) {
    this.connection.query(
      'insert into memb(name,tel,email,pwd) values(?,?,?,password(?))',
      [ member.name, member.tel, member.email, member.password],
      function(error, result) {
        if (error) {
          errorFn(error)
        } else {
          successFn(result)
        }
      }) //connection.query()
  }, //insert()

  update: function(member, successFn, errorFn) {
    this.connection.query(
      'update memb set name=?, tel=?, email=?, pwd=password(?) where mno=?',
      [member.name, member.tel, member.email, member.password, member.no],
      function(error, result) {
        if (error) {
          errorFn(error)
        } else {
          successFn(result)
        }
      })
  }, // update()

  delete: function(no, successFn, errorFn) {
    this.connection.query(
      'delete from memb where mno=?',
      [no],
      function(error, result) {
        if (error) {
          errorFn(error)
        } else {
          successFn(result)
        }
      }) //connection.query()
  }//delete()

}// exports
