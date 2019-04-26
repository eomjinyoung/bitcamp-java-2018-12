"use strict"

module.exports = {
  setConnection(con) {
    this.connection = con
  },

  selectList(pageNo, pageSize, successFn, errorFn) {
    this.connection.query(
      'select m.mno, m.name, m.tel, m.email, s.work \
      from stud s inner join memb m on s.sno=m.mno  \
      order by m.name asc \
      limit ?, ?',
      [(pageNo - 1) * pageSize, pageSize],
      function(error, results) {
        if (error) {
          errorFn(error)
        } else {
          successFn(results)
        }
      }) // connection.query()
  },//selectList()

  countAll(successFn, errorFn) {
    this.connection.query(
      'select count(*) cnt from stud',
      function(error, results) {
        if (error) {
          errorFn(error)
        } else {
          successFn(results)
        }
      }) //connection.query()
  },//countAll()

  selectOne(no, successFn, errorFn) {
    this.connection.query(
      'select m.mno, m.name, m.tel, m.email, s.work, s.schl_nm \
      from stud s inner join memb m on s.sno=m.mno \
      where s.sno=?',
      [no],
      function(error, results) {
        if (error) {
          errorFn(error)
        } else {
          successFn(results[0])
        }
      }) // connection.query()
  },//selectOne()

  insert(student, successFn, errorFn) {
    this.connection.query(
      'insert into stud(sno,work,schl_nm) values(?,?,?)',
      [ student.no, student.working, student.schoolName],
      function(error, result) {
        if (error) {
          errorFn(error)
        } else {
          successFn(result)
        }
      }) //connection.query()
  }, //insert

  update(student, successFn, errorFn) {
    this.connection.query(
      'update stud set work=?, schl_nm=? where sno=?',
      [student.working, student.schoolName, student.no],
      function(error, result) {
        if (error) {
          errorFn(error)
        } else {
          successFn(result)
        }
      }) //connection.query()
  }, //update()

  delete(no, successFn, errorFn) {
    this.connection.query(
      'delete from stud where sno=?',
      [no],
      function(error, result) {
        if (error) {
          errorFn(error)
        } else {
          successFn(result)
        }
      })
  }//delete()

}//exports
