"use strict"

module.exports = {
  setTeacherDao(dao) {
    this.teacherDao = dao
  },

  setMemberDao(dao) {
    this.memberDao = dao
  },

  list(pageNo, success, error) {
    var obj = this
    this.teacherDao.selectList(pageNo, function(teachers) {
      obj.teacherDao.countAll(function(result) {
        success(teachers, result[0].cnt)
      }, error)
    }, error)
  },//list()

  detail(no, success, error) {
    this.teacherDao.selectOne(no, success, error)
  },//detail()

  insert(teacher, success, error) {
    var obj = this
    this.memberDao.insert(teacher, function(result) {
      teacher.no = result.insertId
      obj.teacherDao.insert(teacher, success, error)
    }, error)
  },//insert()

  update(teacher, success, error) {
    var obj = this
    this.memberDao.update(teacher, function(result) {
      obj.teacherDao.update(teacher, success, error)
    }, error)
  }, // update()

  delete(no, success, error) {
    var obj = this
    this.teacherDao.delete(no, function(result) {
      obj.memberDao.delete(no, success, error)
    }, error)
  } // delete()
} // exports
