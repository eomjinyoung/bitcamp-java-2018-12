// 학생 정보를 다루는 서비스를 정의한다.
const express = require('express')
const datasource = require('../util/datasource')
const studentDao = require('../dao/student-dao')
const memberDao = require('../dao/member-dao')
const studentService = require('../service/student-service')

const connection = datasource.getConnection()
studentDao.setConnection(connection)
memberDao.setConnection(connection)
studentService.setStudentDao(studentDao)
studentService.setMemberDao(memberDao)

const router = express.Router()

router.get('/list.do', (request, response) => {
  var pageNo = 1,
      pageSize = 3;
  if (request.query.pageNo) {
    pageNo = parseInt(request.query.pageNo)
  }
  if (request.query.pageSize) {
    pageSize = parseInt(request.query.pageSize)
  }
  studentService.list(pageNo, pageSize, function(results, totalCount) {
    var lastPageNo = parseInt(totalCount / pageSize) + (totalCount % pageSize > 0 ? 1 : 0)

    response.render('student/index', {
      'data': results,
      'pageNo': pageNo,
      'nextPageNo': pageNo + 1,
      'prevPageNo': pageNo - 1,
      'disabledPrevBtn': (pageNo == 1) ? 'disabled' : '',
      'disabledNextBtn': (pageNo == lastPageNo ? 'disabled' : '')
    })
  }, function(error) {
    response.render('error', {
      'message': '학생 목록 데이터를 가져오는 중 오류가 발생했습니다.'})
    console.log(error)
  })
})

router.get('/detail.do', function(request, response) {
  var no = parseInt(request.query.no)
  studentService.detail(no, function(result) {
    response.render('student/view', {
      'detail': true,
      'data': result,
      'checkedWorking': (result.work == 'Y' ? 'checked' : '')
    })
  }, function(error) {
    response.render('error', {
      'message': '학생 데이터를 가져오는 중 오류가 발생했습니다.'})
    console.log(error)
  })
})

router.post('/update.do', function(request, response) {
  studentService.update({
    no: request.body.no,
    working: (request.body.working == undefined ? 'N' : 'Y'),
    schoolName: request.body.schoolName,
    name: request.body.name,
    tel: request.body.tel,
    email: request.body.email,
    password: '1111'
  }, function(result) {
    // 웹브라우저에게 응답 내용은 보내지 않고,
    // 대신 list.do로 다시 요청하라고 응답한다.
    // 그러면 웹브라우저는 list.do로 다시 요청한다.
    response.redirect('list.do')

  }, function(error) {
    response.render('error', {
      'message': '학생 데이터를 변경하는 중 오류가 발생했습니다.'})
    console.log(error)
  })
})

router.get('/delete.do', function(request, response) {
  var no = parseInt(request.query.no)
  studentService.delete(no, function(result) {
    response.redirect('list.do')
  }, function(error) {
    response.render('error', {
      'message': '학생 데이터를 삭제하는 중 오류가 발생했습니다.'})
    console.log(error)
  })
})

router.get('/form.do', function(request, response) {
  response.render('student/view')
})

router.post('/add.do', function(request, response) {
  studentService.insert({
    working: (request.body.working == undefined ? 'N' : 'Y'),
    schoolName: request.body.schoolName,
    name: request.body.name,
    tel: request.body.tel,
    email: request.body.email,
    password: '1111'
  }, function(result) {
    response.redirect('list.do')

  }, function(error) {
    response.render('error', {
      'message': '학생 데이터를 등록하는 중 오류가 발생했습니다.'})
    console.log(error)
  })
})





module.exports = router
