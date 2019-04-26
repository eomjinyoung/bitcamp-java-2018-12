"use strict"

module.exports = {
  setManagerDao(dao) {
    this.managerDao = dao
  },

  listName(success, error) {
    this.managerDao.selectNameList(success, error)
  },//listName()

} // exports
