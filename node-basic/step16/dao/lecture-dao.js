"use strict"

module.exports = {
  setConnection(con) {
    this.connection = con
  },

  selectList(pageNo, pageSize, successFn, errorFn) {
    this.connection.query(
      "select lno, titl, date_format(sdt,'%Y-%m-%d') sdt2 , \
      date_format(edt,'%Y-%m-%d') edt2, thrs, pric \
      from lect \
      order by sdt desc \
      limit ?, ?",
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
      'select count(*) cnt from lect',
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
      "select lno, titl, dscp, \
      date_format(sdt,'%Y-%m-%d') sdt2, \
      date_format(edt,'%Y-%m-%d') edt2, \
      qty, pric, thrs, crmno, mrno \
      from lect \
      where lno=?",
      [no],
      function(error, results) {
        if (error) {
          errorFn(error)
        } else {
          successFn(results[0])
        }
      }) // connection.query()
  },//selectOne()

  insert(lecture, successFn, errorFn) {
    this.connection.query(
      'insert into lect(titl,dscp,sdt,edt,qty,pric,thrs,crmno,mrno) \
       values(?,?,?,?,?,?,?,?,?)',
      [lecture.title, lecture.content, lecture.startDate,
       lecture.endDate, lecture.quantity, lecture.price,
       lecture.hours, lecture.classroom, lecture.manager],
      function(error, result) {
        if (error) {
          errorFn(error)
        } else {
          successFn(result)
        }
      }) //connection.query()
  }, //insert

  update(l, successFn, errorFn) {
    this.connection.query(
      'update lect set titl=?, dscp=?, sdt=?, edt=?, qty=?, pric=?, thrs=?, crmno=?, mrno=? \
      where lno=?',
      [l.title, l.content, l.startDate, l.endDate, l.quantity,
      l.price, l.hours, l.classroom, l.manager, l.no],
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
      'delete from lect where lno=?',
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
