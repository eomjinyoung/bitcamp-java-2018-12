"use strict"

module.exports = {
  setConnection(con) {
    this.connection = con
  },

  selectNameList(successFn, errorFn) {
    this.connection.query(
      'select crmno, name from croom order by name asc',
      function(error, results) {
        if (error) {
          errorFn(error)
        } else {
          successFn(results)
        }
      }) // connection.query()
  },//selectNameList()


}//exports
