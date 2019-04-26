"use strict"

module.exports = {
  setClassroomDao(dao) {
    this.classroomDao = dao
  },

  listName(success, error) {
    this.classroomDao.selectNameList(success, error)
  },//listName()
} // exports
