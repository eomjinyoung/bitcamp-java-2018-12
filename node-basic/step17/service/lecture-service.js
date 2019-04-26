"use strict"

module.exports = {
  setLectureDao(dao) {
    this.lectureDao = dao
  },

  list(pageNo, pageSize, success, error) {
    var obj = this
    this.lectureDao.selectList(pageNo, pageSize, function(lectures) {
      obj.lectureDao.countAll(function(result) {
        success(lectures, result[0].cnt)
      }, error)
    }, error)
  },//list()

  detail(no, success, error) {
    this.lectureDao.selectOne(no, success, error)
  },//detail()

  insert(lecture, success, error) {
    this.lectureDao.insert(lecture, success, error)
  },//insert()

  update(lecture, success, error) {
    this.lectureDao.update(lecture, success, error)
  }, // update()

  delete(no, success, error) {
    this.lectureDao.delete(no, success, error)
  } // delete()
} // exports
