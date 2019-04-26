// 강의 정보를 다루는 서비스를 정의한다.
const express = require('express')
const datasource = require('../util/datasource')
const lectureDao = require('../dao/lecture-dao')
const managerDao = require('../dao/manager-dao')
const classroomDao = require('../dao/classroom-dao')
const lectureService = require('../service/lecture-service')
const managerService = require('../service/manager-service')
const classroomService = require('../service/classroom-service')

const connection = datasource.getConnection()
lectureDao.setConnection(connection)
lectureService.setLectureDao(lectureDao)
managerDao.setConnection(connection)
managerService.setManagerDao(managerDao)
classroomDao.setConnection(connection)
classroomService.setClassroomDao(classroomDao)

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
  lectureService.list(pageNo, pageSize, function(results, totalCount) {
    var lastPageNo = parseInt(totalCount / pageSize) + (totalCount % pageSize > 0 ? 1 : 0)

    response.render('lecture/index', {
      'data': results,
      'pageNo': pageNo,
      'nextPageNo': pageNo + 1,
      'prevPageNo': pageNo - 1,
      'disabledPrevBtn': (pageNo == 1) ? 'disabled' : '',
      'disabledNextBtn': (pageNo == lastPageNo ? 'disabled' : '')
    })
  }, function(error) {
    response.render('error', {
      'message': '강의 목록 데이터를 가져오는 중 오류가 발생했습니다.'})
    console.log(error)
  })
})

router.get('/detail.do', function(request, response) {
  var no = parseInt(request.query.no)
  lectureService.detail(no, function(result) {
    classroomService.listName(function(classrooms) {
      managerService.listName(function(managers) {
        response.render('lecture/view', {
          'detail': true,
          'data': result,
          'classrooms': classrooms,
          'managers': managers
        })

      }, function(error) {
        response.render('error', {
          'message': '강의실 데이터를 가져오는 중 오류가 발생했습니다.'})
        console.log(error)
      })
    }, function(error) {
      response.render('error', {
        'message': '강의실 데이터를 가져오는 중 오류가 발생했습니다.'})
      console.log(error)
    })
  }, function(error) {
    response.render('error', {
      'message': '강의 데이터를 가져오는 중 오류가 발생했습니다.'})
    console.log(error)
  })
})

router.post('/update.do', function(request, response) {
  lectureService.update({
    no: request.body.no,
    title: request.body.title,
    content: request.body.content,
    startDate: request.body.startDate,
    endDate: request.body.endDate,
    quantity: request.body.quantity,
    hours: request.body.hours,
    price: request.body.price,
    classroom: (request.body.classroom == 0 ? null : request.body.classroom),
    manager: (request.body.manager == 0 ? null : request.body.manager)
  }, function(result) {
    response.redirect('list.do')

  }, function(error) {
    response.render('error', {
      'message': '강의 데이터를 변경하는 중 오류가 발생했습니다.'})
    console.log(error)
  })
})

router.get('/delete.do', function(request, response) {
  var no = parseInt(request.query.no)
  lectureService.delete(no, function(result) {
    response.redirect('list.do')
  }, function(error) {
    response.render('error', {
      'message': '강의 데이터를 삭제하는 중 오류가 발생했습니다.'})
    console.log(error)
  })
})

router.get('/form.do', function(request, response) {
  classroomService.listName(function(classrooms) {
    managerService.listName(function(managers) {
      response.render('lecture/view', {
        'classrooms': classrooms,
        'managers': managers
      })

    }, function(error) {
      response.render('error', {
        'message': '매니저 데이터를 가져오는 중 오류가 발생했습니다.'})
      console.log(error)
    })
  }, function(error) {
    response.render('error', {
      'message': '강의실 데이터를 가져오는 중 오류가 발생했습니다.'})
    console.log(error)
  })
})

router.post('/add.do', function(request, response) {
  lectureService.insert({
    title: request.body.title,
    content: request.body.content,
    startDate: request.body.startDate,
    endDate: request.body.endDate,
    quantity: request.body.quantity,
    hours: request.body.hours,
    price: request.body.price,
    classroom: (request.body.classroom == 0 ? null : request.body.classroom),
    manager: (request.body.manager == 0 ? null : request.body.manager)
  }, function(result) {
    response.redirect('list.do')

  }, function(error) {
    response.render('error', {
      'message': '강의 데이터를 등록하는 중 오류가 발생했습니다.'})
    console.log(error)
  })
})





module.exports = router
