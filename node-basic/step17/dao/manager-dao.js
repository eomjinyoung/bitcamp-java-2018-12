"use strict"

module.exports = {
  setConnection(con) {
    this.connection = con
  },

  selectNameList(successFn, errorFn) {
    this.connection.query(
      'select mr.mrno, m.name \
      from mgr mr inner join memb m on mr.mrno=m.mno \
      order by m.name asc',
      function(error, results) {
        if (error) {
          errorFn(error)
        } else {
          successFn(results)
        }
      }) // connection.query()
  },//selectNameList()

}//exports
